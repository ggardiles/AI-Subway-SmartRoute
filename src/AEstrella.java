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
public class AEstrella {

    private double gDistance = 0;
    private TreeMap<Double,Estacion> listaAbierta;
    private TreeMap<String,Estacion> listaCerrada;
    private ArrayList<Estacion> caminoMin;
    private Estacion paradaInicial;
    private Estacion paradaMeta;
    private static final double kmPerH= 30; //segundos en recorrer 1 km
    private static final double HoursPerTransbordo = 1 / 60; //segundos que gastamos en un transbordo
    private static final double pixelToKmRatio = 20;

    public AEstrella(Estacion paradaInicial, Estacion paradaMeta) {

        // Crear Listas cerrada y abierta
        this.listaCerrada = new TreeMap<>();
        this.listaAbierta = new TreeMap<>();
        this.caminoMin = new ArrayList<>();

        // Guardar estaciones inicio y fin
        this.paradaInicial = paradaInicial;
        this.paradaMeta = paradaMeta;
    }


    /**
     * Método que calcula la distancia recorrida desde la estación inicial
     * hasta la final guardadas globalmente
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
     * y la final guardadas globalmente
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
     * Método que implementa el algorimo A* con las listas abiertas y cerradas para
     * averiguar el camino óptimo en un grafo de estaciones
     * @return Una lista con el camino óptimo
     *
     */
    public ArrayList<Estacion> getMejorCamino(){
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);
        paradaInicial.sethCost(hDistance);
        paradaInicial.setgCost(0);

        //Initial station is put into open list
        listaAbierta.put(paradaInicial.getTotalCost(), paradaInicial);

        Estacion paradaActual = paradaInicial;

        while (!paradaActual.getNombre().equals(paradaMeta.getNombre())){

            //Move smallest to closed list
            moveToClosed(paradaActual);

            //Advance to next possible stations
            for(String nombreEstacion: paradaActual.getEstacionesConectadas()){
                if (isAlreadyInClosed(nombreEstacion)){
                    continue;
                }

                //Get station
                Estacion sigEstacion = MetroMonterrey.paradas.get(nombreEstacion);

                //Update costs for this station
                double gCost = gDistance + getDistanciaEntreParadas(paradaActual, sigEstacion);
                hDistance = getDistanciaEntreParadas(sigEstacion, paradaMeta);
                sigEstacion.sethCost(hDistance);
                sigEstacion.setgCost(gCost);

                //Set parent station
                sigEstacion.setWhoWasMyParent(paradaActual.getNombre());

                //Add to open list
                addToOpen(sigEstacion);
            }

            //Update paradaActual to next Estacion
            Estacion next = nextEstacionWithSmallestCost();
            if (next == null){
                moveToClosed(next);
                System.out.println("No more stations in Open list");
                break;
            }

            gDistance = next.getgCost();
            paradaActual = next;

        }

        //Meter la ultima estación que debería ser la meta
        moveToClosed(paradaActual);

        return listaCerradaToArray();

    }

    /**
     * Recorre la lista cerrada de final a principio. Va navegando siguiendo el puntero
     * whoWasMyParent desde Meta a parada Inicial. Finalmente, revierte la lista para
     * ordenarla de Inicio a Fin
     * @return
     */
    private ArrayList<Estacion> listaCerradaToArray(){
        ArrayList<Estacion> minimoCamino = new ArrayList<>();

        Estacion prevParada = listaCerrada.get(paradaMeta.getNombre());
        minimoCamino.add(prevParada);

        while (!prevParada.getNombre().equals(paradaInicial.getNombre())){
             prevParada = MetroMonterrey.paradas.get(prevParada.getWhoWasMyParent());
             minimoCamino.add(prevParada);
        }
        //Reverse
        Collections.reverse(minimoCamino);

        //save global variable
        caminoMin = minimoCamino;
        return minimoCamino;
    }

    /**
     * Añade la estación a la lista abierta si no está ya en la cerrada
     * @param estacion
     */
    private void addToOpen(Estacion estacion){
        if (isAlreadyInClosed(estacion)){
            return;
        }
        listaAbierta.put(estacion.getTotalCost(), estacion);
    }

    /**
     * Devuelve la estación con menor coste en la lista abierta
     * @return
     */
    private Estacion nextEstacionWithSmallestCost(){
        return listaAbierta.firstEntry().getValue();
    }

    /**
     * Mueve una determinada estación a la lista cerrada si no está ya en ella
     * y la elimina de la lista abierta
     * @param estacion Estacio a mover a la lista cerrada
     */
    private void moveToClosed(Estacion estacion){
        if (isAlreadyInClosed(estacion)){
            System.out.println("Added an already existing estacion to closed");
            return;
        }
        listaCerrada.put(estacion.getNombre(), estacion);
        listaAbierta.remove(estacion.getTotalCost(), estacion);
    }

    /**
     * Averigua si la estación está ya en la lista cerrada
     * @param estacionName String con el nombre de la estación
     * @return
     */
    private boolean isAlreadyInClosed(String estacionName){
        return listaCerrada.get(estacionName) != null;
    }

    /**
     * Averigua si la estación está ya en la lista cerrada
     * @param estacion Objeto Estacion
     * @return
     */
    private boolean isAlreadyInClosed(Estacion estacion){
        return isAlreadyInClosed(estacion.getNombre());
    }

    /**
     * Devuelve la distancia recorrida entre dos paradas intermedias
     * @param estacionInicio
     * @param estacionFin
     * @return Kms recorrido entre dos estaciones específicas
     */
    private double getDistanciaEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        double distanciaI = (double)(estacionInicio.getxPos()-estacionFin.getxPos());// * 11114.591;
        double distanciaJ = (double)(estacionInicio.getyPos()-estacionFin.getyPos());// * 7825.486;
        return Math.sqrt(Math.pow(distanciaI, 2) + Math.pow(distanciaJ,2)) / pixelToKmRatio;// /1000 ;
    }

    /**
     * Método que calcula el tiempo que se tarda entre paradas
     * (recorrido + espera en estaciones intermedias)
     * Estimación del tiempo: 115 segundos cada 1 km recorridos y 180 segundos por transbordo
     * @param estacionInicio
     * @param estacionFin
     * @return tiempo en segundos
     */
    private double getTiempoEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        if (estacionInicio.isTransbordo()){
            return (getDistanciaEntreParadas(estacionInicio,estacionFin) / kmPerH)
                    + HoursPerTransbordo;
        }
        return getDistanciaEntreParadas(estacionInicio,estacionFin) / kmPerH;
    }

    public static void main(String[] args){
        AEstrella a = new AEstrella(MetroMonterrey.paradas.get("Adolfo Prieto"),
                MetroMonterrey.paradas.get("Sendero"));

        ArrayList<Estacion> c = a.getMejorCamino();
        double dist = a.getDistanciaRecorridaTotal();
        double time = a.getTiempoRecorridoTotal();
        System.out.println(c);
    }


}
