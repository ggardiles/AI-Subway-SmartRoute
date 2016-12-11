import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
        double distanciaI = (estacionInicio.getxPos()-estacionFin.getxPos()) * 111.14591;
        double distanciaJ = (estacionInicio.getyPos()-estacionFin.getyPos()) * 78.25486;
        return (int) Math.sqrt(Math.pow(distanciaI, 2) + Math.pow(distanciaJ,2)) ;
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
        int dist = 0;
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
        int tiempo = 0;
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
        ArrayList<Estacion> resultado = new ArrayList<>();
        double gDistance = 0;
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);
        paradaInicial.sethCost((int)hDistance);
        paradaInicial.setgCost((int)gDistance);
        this.listaAbierta.put(gDistance+hDistance, paradaInicial);
        resultado.add(paradaInicial);
        Estacion paradaActual = paradaInicial;
        Estacion fMin = paradaActual;
        int i = 0;

        System.out.println("La listaAbierta es: " + listaAbierta);

        while(paradaActual != paradaMeta) {
            if (this.listaAbierta.isEmpty()) {
                System.err.println("Error, la lista abierta está vacía");
            }
            double fDistance = 1000000000;
            String [] estConectadas = paradaActual.getEstacionesConectadas();

            System.out.println("estConectadas de " + paradaActual.getNombre() + " son: " + estConectadas.toString());

            for(int j = 0; j < estConectadas.length ; j++){ //bucle para determinar las h y g de los hijos, y elegir el de menor f.
                MetroMonterrey.paradas.get(estConectadas[j]).setgCost((int)
                        (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))));
                MetroMonterrey.paradas.get(estConectadas[j]).sethCost((int)
                        (getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta))); //pongo h y g en cada EstConectada

                System.out.println("estamos en la parada con nombre: " + MetroMonterrey.paradas.get(estConectadas[j]).getNombre());

                System.out.println("la suma es: " + (int) (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                        + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta)));

                System.out.println("fDistance es: " + fDistance);

                if(((int) (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                        + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta)) < fDistance)
                        && !listaAbierta.containsKey(MetroMonterrey.paradas.get(estConectadas[j])))
                {
                    fDistance = (int) (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                            + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta));
                    fMin = MetroMonterrey.paradas.get(estConectadas[j]);
                    //System.out.println("La fMin en la iteracion " + j + " es: " + fMin.getNombre());
                }
            }
            paradaActual = fMin;             //elegir el hijo con la f más pequeña
            resultado.add(paradaActual);
            listaAbierta.put((double)(paradaActual.getgCost() + paradaActual.gethCost()), paradaActual);
        }

        caminoMin = resultado;
        return caminoMin;
    }

    public static void main(String[] args){
        AEstrella a = new AEstrella(MetroMonterrey.paradas.get("Central"), MetroMonterrey.paradas.get("Regina"));
        a.calcularMejorCamino();
    }
}
