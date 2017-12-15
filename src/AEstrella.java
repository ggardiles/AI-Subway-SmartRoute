import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * Created by gabriel on 25/11/16.
 */

/**
 * Class implementing A*
 *
 * When instantiated initial and end stations must be supplied
 */
public class AEstrella implements IAEstrella {

    // Global Constant Fields
    private static final double KM_HOUR = 30;
    private static final double HOURS_TRANSBORDO = 1 / 60;
    private static final double PIXEL_TO_KM = 20;

    // Global Fields
    private TreeMap<Double,Estacion> listaAbierta = new TreeMap<>();
    private TreeMap<String,Estacion> listaCerrada = new TreeMap<>();
    private ArrayList<Estacion> optimalCamino = new ArrayList<>();
    private Estacion paradaInicial, paradaMeta;
    private double gDistance = 0;

    public AEstrella(Estacion paradaInicial, Estacion paradaMeta) {
        // Store start and end stations
        this.paradaInicial = paradaInicial;
        this.paradaMeta = paradaMeta;

        // Calculates best route and stores in global variables for retrieval
        computeMejorCamino();
    }


    /**
     * Calculate trip distance
     * @return distance traveled
     */
    public double getDistanciaRecorridaTotal() {
        if (optimalCamino.isEmpty()){
            return 0.0d;
        }
        
        double dist = 0;
        for(int i = 0; i < optimalCamino.size() - 1; i++){
            dist += getDistanciaEntreParadas(optimalCamino.get(i),optimalCamino.get(i+1));
        }
        return dist;
    }


    /**
     * Calculate trip time
     * @return time
     */
    public double getTiempoRecorridoTotal() {
        if (optimalCamino.isEmpty()){
            return 0.0d;
        }
        
        double tiempo = 0;
        for(int i = 0; i < optimalCamino.size() - 1; i++){
            tiempo += getTiempoEntreParadas(optimalCamino.get(i),optimalCamino.get(i+1));
        }
        return tiempo;
    }

    /**
     * Method that returns best path
     *
     */
    public ArrayList<Estacion> getMejorCamino(){
        return this.optimalCamino;
    }

    /**
     * Method that implements the A* algorithm with open and closed lists to find
     * optimal route in a graph of metro stations
     *
     */
    private void computeMejorCamino(){
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);
        paradaInicial.sethCost(hDistance);
        paradaInicial.setgCost(0);

        // Initial station is put into open list
        listaAbierta.put(paradaInicial.getTotalCost(), paradaInicial);

        Estacion paradaActual = paradaInicial;

        while (!paradaActual.getNombre().equals(paradaMeta.getNombre())){

            // Move smallest to closed list
            moveToClosed(paradaActual);

            // Advance to next possible stations
            for(String nombreEstacion: paradaActual.getEstacionesConectadas()){
                if (isInClosed(nombreEstacion)){
                    continue;
                }

                // Get station
                Estacion sigEstacion = MetroMonterrey.paradas.get(nombreEstacion);

                // Update costs for this station
                double gCost = gDistance + getDistanciaEntreParadas(paradaActual, sigEstacion);
                hDistance = getDistanciaEntreParadas(sigEstacion, paradaMeta);
                sigEstacion.sethCost(hDistance);
                sigEstacion.setgCost(gCost);

                // Set parent station
                sigEstacion.setWhoWasMyParent(paradaActual.getNombre());

                // Add to open list
                addToOpen(sigEstacion);
            }

            // Update paradaActual to next Estacion
            Estacion next = nextEstacionWithSmallestCost();
            if (next == null){
                System.out.println("No more stations in Open list");
                break;
            }

            gDistance = next.getgCost();
            paradaActual = next;

        }

        // Introduce finish station in closed list
        moveToClosed(paradaActual);

        // Save optimal route
        optimalCamino = traverseClosedList();
    }

    /**
     * Traverses closed list from end to start. It navigates backwards following the pointer
     * 'whoWasMyParent'. Finally, reverses the list to get a clean optimal route from start to finish.
     * @return ArrayList of Optimal routes
     */
    private ArrayList<Estacion> traverseClosedList(){
        ArrayList<Estacion> minimoCamino = new ArrayList<>();

        Estacion prevParada = listaCerrada.get(paradaMeta.getNombre());
        minimoCamino.add(prevParada);

        while (!prevParada.getNombre().equals(paradaInicial.getNombre())){
             prevParada = MetroMonterrey.paradas.get(prevParada.getWhoWasMyParent());
             minimoCamino.add(prevParada);
        }

        //Reverse
        Collections.reverse(minimoCamino);
        
        return minimoCamino;
    }

    /**
     * Add station to open list if not in closed
     * @param estacion station to be added
     */
    private void addToOpen(Estacion estacion){
        if (isInClosed(estacion)){
            return;
        }
        listaAbierta.put(estacion.getTotalCost(), estacion);
    }

    /**
     * Return next station with smallest cost in open list
     * @return estacion
     */
    private Estacion nextEstacionWithSmallestCost(){
        return listaAbierta.firstEntry().getValue();
    }

    /**
     * Moves a estation to closed list and deletes from open
     * @param estacion estation to be moved
     */
    private void moveToClosed(Estacion estacion){
        if (isInClosed(estacion)){
            System.out.println("Attempt to add an already existing estacion to closed");
            return;
        }
        listaCerrada.put(estacion.getNombre(), estacion);
        listaAbierta.remove(estacion.getTotalCost(), estacion);
    }

    /**
     * Check if station is in closed list by string
     * @param estacionName station name
     * @return boolean
     */
    private boolean isInClosed(String estacionName){
        return listaCerrada.get(estacionName) != null;
    }

    /**
     * Check if station is in closed list by Object
     * @param estacion Objeto Estacion
     * @return boolean
     */
    private boolean isInClosed(Estacion estacion){
        return isInClosed(estacion.getNombre());
    }

    /**
     * Returns distance between two stations
     * @param estacionInicio first station
     * @param estacionFin second station
     * @return Kms recorrido entre dos estaciones específicas
     */
    private double getDistanciaEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        double distanciaI = (double) (estacionInicio.getxPos() - estacionFin.getxPos());
        double distanciaJ = (double) (estacionInicio.getyPos() - estacionFin.getyPos());
        return Math.sqrt(Math.pow(distanciaI, 2) + Math.pow(distanciaJ,2)) / PIXEL_TO_KM;
    }

    /**
     * Calculates time between stations
     * (travel time + wait time in crossings)
     * @param estacionInicio first station
     * @param estacionFin second station
     * @return time in seconds
     */
    private double getTiempoEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        if (estacionInicio.isTransbordo()){
            return (getDistanciaEntreParadas(estacionInicio,estacionFin) / KM_HOUR)
                    + HOURS_TRANSBORDO;
        }
        return getDistanciaEntreParadas(estacionInicio,estacionFin) / KM_HOUR;
    }

}
