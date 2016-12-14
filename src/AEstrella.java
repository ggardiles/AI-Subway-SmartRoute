import sun.awt.image.ImageWatched;

import java.util.*;

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
    private HashMap<Double,Estacion> listaAbierta;
    private HashMap<Double,Estacion> listaCerrada;
    private ArrayList<Estacion> caminoMin;
    private Estacion paradaInicial;
    private Estacion paradaMeta;
    private double segkm = 115; //segundos en recorrer 1 km
    private double segTransbordo; //segundos que gastamos en un transbordo

    public AEstrella(){}

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
        double distanciaI = (estacionInicio.getxPos()-estacionFin.getxPos()) * 11114.591;
        double distanciaJ = (estacionInicio.getyPos()-estacionFin.getyPos()) * 7825.486;
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

     *
     */
    public ArrayList<Estacion> calcularMejorCamino(){
        ArrayList<String> res = new ArrayList<>();

        double gDistance = 0;
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);

        paradaInicial.sethCost((int)hDistance);
        paradaInicial.setgCost((int)gDistance);

        this.listaAbierta.put(gDistance+hDistance, paradaInicial);
        this.listaCerrada.put(gDistance+hDistance, paradaInicial);
        this.listaAbierta.remove(gDistance+hDistance, paradaInicial);
        caminoMin.add(paradaInicial);
        res.add(paradaInicial.getNombre());

        Estacion paradaActual = paradaInicial;
        Estacion fMin = paradaActual;

        String [] estConectadas = paradaActual.getEstacionesConectadas();
        // System.out.println("La listaAbierta es: " + listaAbierta);

        while(paradaActual != paradaMeta) {
            if (this.listaAbierta.isEmpty()) {
                //System.err.println("Error, la lista abierta está vacía");
            }

            estConectadas = paradaActual.getEstacionesConectadas();
            //estConectadas = unirArrays(estConectadas,paradaActual.getEstacionesConectadas());
            double fDistance = 2 * (getDistanciaEntreParadas(MetroMonterrey.paradas.get("Talleres"), MetroMonterrey.paradas.get("Exposición")));

            System.out.println("estConectadas de " + paradaActual.getNombre() + " son: " + estConectadas.toString());

            for(int j = 0; j < estConectadas.length ; j++) { //bucle para determinar las h y g de los hijos, y elegir el de menor f.
                System.out.println(MetroMonterrey.paradas.get(estConectadas[j]) + MetroMonterrey.paradas.get(estConectadas[j]).getNombre() + "    " + this.listaAbierta);
                //System.out.println("estConectadas[j] es "+ estConectadas[j] + "!this.listaAbierta.containsValue(estConectadas[j])" +!this.listaAbierta.containsValue(estConectadas[j]));
                System.out.println("!this.listaAbierta.containsValue(MetroMonterrey.paradas.get(estConectadas[j]))" + !this.listaAbierta.containsValue(MetroMonterrey.paradas.get(estConectadas[j])));
                if (!this.listaAbierta.containsValue(MetroMonterrey.paradas.get(estConectadas[j]))) {
                    MetroMonterrey.paradas.get(estConectadas[j]).setgCost((int)
                            (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))));

                    MetroMonterrey.paradas.get(estConectadas[j]).sethCost((int)
                            (getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta))); //pongo h y g en cada EstConectada

                    this.listaAbierta.put((double) (MetroMonterrey.paradas.get(estConectadas[j]).getgCost() + MetroMonterrey.paradas.get(estConectadas[j]).gethCost()), MetroMonterrey.paradas.get(estConectadas[j]));

                    if (((int) (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                            + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta)) < (int) fDistance)
                            && !this.listaCerrada.containsValue(MetroMonterrey.paradas.get(estConectadas[j]))) {

                        fDistance = (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                                + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta));
                        fMin = MetroMonterrey.paradas.get(estConectadas[j]);
                        //gDistance = gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]));
                        System.out.println("if if, fMin es: " + fMin.getNombre() + "    " +  fDistance);
                    }
                    else{
                        if (((int) (gDistance + getDistanciaEntreParadas(paradaActual, MetroMonterrey.paradas.get(estConectadas[j]))
                                + getDistanciaEntreParadas(MetroMonterrey.paradas.get(estConectadas[j]), paradaMeta)) < (int) fDistance)
                                && !this.listaCerrada.containsValue(MetroMonterrey.paradas.get(estConectadas[j]))) {
                            fMin = minMap();
                            gDistance = fMin.getgCost();
                        }
                    }
                }
                else{
                    fMin = minMap();
                    gDistance = fMin.getgCost();
                    System.out.println("else fMin, es: " + fMin.getNombre());
                }
            }
            gDistance = gDistance + getDistanciaEntreParadas(paradaActual, fMin);
            paradaActual = fMin;             //elegir el hijo con la f más pequeña
            System.out.println("LA PUTA PARADA ACUTAL ES    " + paradaActual.getNombre());


            if(!this.listaCerrada.containsValue(paradaActual) /*&& this.listaAbierta.containsValue(paradaActual)*/){
                //   System.out.println("Se añade a caminoMin: " + fMin.getNombre());
                this.caminoMin.add(paradaActual);
                res.add(paradaActual.getNombre());
            }
            this.listaAbierta.remove((double) (paradaActual.getgCost() + paradaActual.gethCost()), paradaActual);
            this.listaCerrada.put((double) (paradaActual.getgCost() + paradaActual.gethCost()), paradaActual); //meto en listaCerrada la elegida
        }

        System.out.println("caminoMin es: " + res);
        return this.caminoMin;
    }

    public Estacion minMap (){
        Set<Double> s = this.listaAbierta.keySet();
        Object [] arr = s.toArray();
        double peq = (double) arr[0];
        for (int n = 0; n < arr.length ; n++){
            double valor = (double) arr[n];
            System.out.println("boolean   if de minMap es: " + listaAbierta.get(valor).getNombre() + "  " + !this.listaCerrada.containsKey(valor));
            if(valor < peq && !this.listaCerrada.containsKey(valor)){
                peq = valor;
            }
        }
        return this.listaAbierta.get(peq);
    }

    public static void main(String[] args){
        AEstrella a = new AEstrella(MetroMonterrey.paradas.get("Adolfo Prieto"), MetroMonterrey.paradas.get("Sendero"));
        ArrayList<Estacion> c = a.calcularMejorCamino();
        System.out.println(c);
    }


}
