import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gabriel on 25/11/16.
 */

/**
 * Clase que implementa A*
 *
 * Al instanciarlo con un constructor se introducen las paradas inciales y finales
 */
public class AEstrella {

    private double gCost = 0;
    private HashMap<Double, Estacion> listaAbierta;
    private HashMap<Double, Estacion> listaCerrada;
    private Estacion paradaInicial;
    private Estacion paradaMeta;


    public AEstrella(Estacion paradaInicial, Estacion paradaMeta) {

        // Crear Listas cerrada y abierta
        this.listaCerrada = new HashMap<>();
        this.listaAbierta = new HashMap<>();

        // Guardar estaciones inicio y fin
        this.paradaInicial = paradaInicial;
        this.paradaMeta = paradaMeta;
    }

    /**
     *
     * @param estacionInicio
     * @param estacionFin
     * @return Tiempo recorrido entre dos estaciones específicas
     */
    public double getDistanciaEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        return 0;
    }

    /**
     * Método que calcula el tiempo que se tarda entre paradas
     * (recorrido + espera en estaciones intermedias)
     * @param estacionInicio
     * @param estacionFin
     * @return
     */
    public double getTiempoEntreParadas(Estacion estacionInicio, Estacion estacionFin){
        return 0.0;
    }

    /**
     * Método que calcula la distancia recorrida estacion por estación hasta llegar
     * al destino. Utiliza la estación inicial y final guardadas en la variable global
     * @return
     */
    public double getDistanciaRecorridaTotal(){
        return 0.6;
    }

    /**
     * Método que calcula el tiempo recorrido total utilizando las estaciones inicial
     * y final guardadas como variables globales
     * @return
     */
    public double getTiempoRecorridoTotal(){
        return 0;
    }

    /**
     * Método que implementa el algorimo A* con las listas abiertas y cerradas
     * @return Una lista con el camino óptimo
     */
    public ArrayList<Estacion> calcularMejorCamino(){

        // Calcular distancia h y g iniciales
        double gDistance = 0;
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);
        this.listaAbierta.put(gDistance+hDistance, paradaInicial);

        //TODO - Implementar A*

        return null;
    }


}
