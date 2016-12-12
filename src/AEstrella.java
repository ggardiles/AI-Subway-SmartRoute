import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by gabriel on 25/11/16.
 */

/**
 * Clase que implementa A*
 *
 * Al instanciarlo con un constructor se introducen las paradas inciales y finales
 */
public class AEstrella extends EstacionesMonterrey {

    private double gCost = 0;
    private HashMap<Double, Estacion> listaAbierta;
    private HashMap<Double, Estacion> listaCerrada;
    private ArrayList<Estacion> caminoMin;
    private Estacion paradaInicial;
    private Estacion paradaMeta;
    private double segkm = 115; //segundos en recorrer 1 km
    private double segTransbordo; //segundos que gastamos en un transbordo

    public AEstrella(){
    }

    public AEstrella(Estacion paradaInicial, Estacion paradaMeta) {

        // Crear Listas cerrada y abierta
        this.listaCerrada = new HashMap<>();
        this.listaAbierta = new HashMap<>();
        this.caminoMin = new ArrayList<>();

        // Guardar estaciones inicio y fin
        this.paradaInicial = paradaInicial;
        this.paradaMeta = paradaMeta;
    }

    /**
     *
     * @param estacionInicio
     * @param estacionFin
     * @return Kms recorrido entre dos estaciones específicas
     */
    public double getDistanciaEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        double distanciaI = (estacionInicio.getxPos()-estacionFin.getxPos()) * 111145.91;
        double distanciaJ = (estacionInicio.getyPos()-estacionFin.getyPos()) * 78254.86;
        return Math.sqrt(Math.pow(distanciaI, 2) + Math.pow(distanciaJ,2)) /1000 ;
    }

    /**
     * Método que calcula el tiempo que se tarda entre paradas
     * (recorrido + espera en estaciones intermedias)
     * Estimación del tiempo: 115 segundos cada 1 km recorridos y 180 segundos por transbordo
     * @param estacionInicio
     * @param estacionFin
     * @return tiempo en segundos
     */
    public double getTiempoEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        if (estacionInicio.isTransbordo()){
            return (getDistanciaEntreParadas(estacionInicio,estacionFin) * segkm) + segTransbordo;
        }
        else{
            return getDistanciaEntreParadas(estacionInicio,estacionFin) * segkm;
        }
    }

    /**
     * Método que calcula la distancia recorrida estacion por estación hasta llegar
     * al destino. Utiliza la estación inicial y final guardadas en la variable global
     * @return
     */
    public double getDistanciaRecorridaTotal(){
        double dist = 0;
        for(int i = 0; i < caminoMin.size() - 1; i++){
            dist += getDistanciaEntreParadas(caminoMin.get(i),caminoMin.get(i+1));
        }
        return dist;
    }

    /**
     * Método que calcula el tiempo recorrido total utilizando las estaciones inicial
     * y final guardadas como variables globales
     * @return
     */
    public double getTiempoRecorridoTotal(){
        double tiempo = 0;
        for(int i = 0; i < caminoMin.size() - 1; i++){
            tiempo += getTiempoEntreParadas(caminoMin.get(i),caminoMin.get(i+1));
        }
        return tiempo;
    }


    /**
     * Método que implementa el algorimo A* con las listas abiertas y cerradas
     * @return Una lista con el camino óptimo
     */
    public ArrayList<Estacion> calcularMejorCamino(){
        // Calcular distancia h y g iniciales
        double gDistance = 0;
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);
        paradaInicial.sethCost((int)hDistance);
        paradaInicial.setgCost((int)gDistance);
        this.listaAbierta.put(gDistance+hDistance, paradaInicial);
        this.listaCerrada.put(gDistance+hDistance, paradaInicial);
        caminoMin.add(paradaInicial);
        Estacion paradaActual = paradaInicial;
        Estacion fMin = paradaActual;
        Object [] estConectadas = paradaActual.getEstacionesConectadas();
        System.out.println("La listaAbierta es: " + listaAbierta);

        while(paradaActual != paradaMeta) {
            if (this.listaAbierta.isEmpty()) {
                System.err.println("Error, la lista abierta está vacía");
            }
            double fDistance = 2 * (getDistanciaEntreParadas(MetroMonterrey.paradas.get("Talleres"), MetroMonterrey.paradas.get("Exposición")));
            //String [] estConectadas = paradaActual.getEstacionesConectadas();
            estConectadas = unirArrays(estConectadas,paradaActual.getEstacionesConectadas());

            System.out.println("estConectadas de " + paradaActual.getNombre() + " son: " + estConectadas.toString());

            for(int j = 0; j < estConectadas.length ; j++) { //bucle para determinar las h y g de los hijos, y elegir el de menor f.
                if (!this.listaCerrada.containsValue(MetroMonterrey.paradas.get(estConectadas[j]))) {
                    MetroMonterrey.paradas.get(estConectadas[j]).setgCost((int)
                            (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))));
                    MetroMonterrey.paradas.get(estConectadas[j]).sethCost((int)
                            (getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta))); //pongo h y g en cada EstConectada

                    this.listaAbierta.put((double) (paradaActual.getgCost() + paradaActual.gethCost()), MetroMonterrey.paradas.get(estConectadas[j]));
                    //Metemos las analizadas en la lista abierta

                    if (((int) (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                            + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta)) <= (int) fDistance)
                            && !this.listaCerrada.containsKey(MetroMonterrey.paradas.get(estConectadas[j]))) {
                        fDistance = (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                                + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta));
                        fMin = MetroMonterrey.paradas.get(estConectadas[j]);

                    }
                }
            }
            System.out.println("La fMin es: " + fMin.getNombre());
            paradaActual = fMin;             //elegir el hijo con la f más pequeña

            System.out.println("(!listaCerrada.containsValue(paradaActual)) " + !listaCerrada.containsValue(paradaActual));
            if(!listaCerrada.containsValue(paradaActual)) {
                System.out.println("Se añade a caminoMin: " + fMin.getNombre());
                caminoMin.add(paradaActual);
                this.listaCerrada.put((double) (paradaActual.getgCost() + paradaActual.gethCost()), paradaActual); //meto en listaCerrada la elegida
            }

            System.out.println("el camino minimo es: " + caminoMin + "\n y la listaCerrada es : " + listaCerrada);

        }

        caminoMin = devuelveCamino();

        return caminoMin;
    }

    public ArrayList<Estacion> devuelveCamino(){
        ArrayList<Estacion> resultado = new ArrayList<>();
        Estacion aux = paradaInicial;
        paradaInicial = paradaMeta;
        paradaMeta = paradaInicial;
        aux = paradaInicial;
        Estacion fMin = aux;

        double gDistance = 0;
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);
        Object [] estConectadas = aux.getEstacionesConectadas();

        while (aux != paradaMeta){
            double fDistance = 2 * (getDistanciaEntreParadas(MetroMonterrey.paradas.get("Talleres"), MetroMonterrey.paradas.get("Exposición")));
            //String [] estConectadas = paradaActual.getEstacionesConectadas();
            estConectadas = unirArrays(estConectadas,aux.getEstacionesConectadas());


            for(int j = 0; j < estConectadas.length ; j++) { //bucle para determinar las h y g de los hijos, y elegir el de menor f.
               // if (!this.listaCerrada.containsValue(MetroMonterrey.paradas.get(estConectadas[j]))) {
                    MetroMonterrey.paradas.get(estConectadas[j]).setgCost((int)
                            (gDistance + getDistanciaEntreParadas(aux, MetroMonterrey.paradas.get(estConectadas[j]))));
                    MetroMonterrey.paradas.get(estConectadas[j]).sethCost((int)
                            (getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta))); //pongo h y g en cada EstConectada

                    //this.listaAbierta.put((double) (aux.getgCost() + aux.gethCost()), MetroMonterrey.paradas.get(estConectadas[j]));
                    //Metemos las analizadas en la lista abierta

                    if (((int) (gDistance + getDistanciaEntreParadas(aux, MetroMonterrey.paradas.get(estConectadas[j]))
                            + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta)) <= (int) fDistance)
                            && !this.listaCerrada.containsKey(MetroMonterrey.paradas.get(estConectadas[j]))) {
                        fDistance = (gDistance + getDistanciaEntreParadas(aux, MetroMonterrey.paradas.get(estConectadas[j]))
                                + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta));
                        fMin = MetroMonterrey.paradas.get(estConectadas[j]);

                    }
                //}
            }
            aux = fMin;             //elegir el hijo con la f más pequeña

            if(!resultado.contains(aux)) {
                resultado.add(aux);
            }

        }

        return resultado;
    }

    public Object[] unirArrays (Object [] array1, Object[] array2){
        ArrayList<Object> resul = new ArrayList<>();
        for(int i = 0; i < array2.length; i++){
            if(!this.listaCerrada.containsValue(array2[i].toString())) {
                resul.add(array2[i]);
            }
        }
        for(int i =0; i < array1.length; i++){
            if(!this.listaCerrada.containsValue(array1[i].toString())) {
                resul.add(array1[i]);
            }
        }
        return resul.toArray();
    }

    public static void main(String[] args){
        AEstrella a = new AEstrella(MetroMonterrey.paradas.get("Adolfo Prieto"), MetroMonterrey.paradas.get("Sendero"));
        a.calcularMejorCamino();
    }


}
