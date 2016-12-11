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
    private LinkedList<Estacion> caminoMin;
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
        this.caminoMin = new LinkedList<>();

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
        return (int) Math.sqrt(Math.pow(distanciaI, 2) + Math.pow(distanciaJ,2));
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
        double gDistance = 0;
        double hDistance = getDistanciaEntreParadas(paradaInicial, paradaMeta);
        paradaInicial.sethCost((int)hDistance);
        paradaInicial.setgCost((int)gDistance);
        this.listaAbierta.put(gDistance+hDistance, paradaInicial);
        Estacion paradaActual = paradaInicial;
        Estacion aux;
        int i = 0;
        while(paradaActual != paradaMeta) {
            if (this.listaAbierta.isEmpty()) {
                System.err.println("Error en lista abierta");
            }
            String [] estConectadas = paradaActual.getEstacionesConectadas();
            for(int j = 0; j < estConectadas.length ; j++){
                aux = MetroMonterrey.paradas.get(estConectadas[i]);             //hijo de paradaActual
                aux.setgCost((int)(gDistance + getDistanciaEntreParadas(paradaActual, aux)));
                aux.sethCost((int)(getDistanciaEntreParadas(aux, paradaMeta)));        //pongo h y g en cada Estacion
              /*  if(gDistance + getDistanciaEntreParadas(paradaActual,aux) +     //la g de aux
                        getDistanciaEntreParadas(aux, paradaMeta) <             //la h de aux
                        gDistance + hDistance){                                 //la f de paradaActual
                }   */

            }
            paradaActual = MetroMonterrey.paradas.get(paradaActual);

        }

        //TODO - Implementar A*


        // Este return era solo para probar la interfaz gráfica
        return new ArrayList<Estacion>(){{
            add(MetroMonterrey.paradas.get("Talleres"));
            add(MetroMonterrey.paradas.get("San Bernabé"));
            add(MetroMonterrey.paradas.get("Unidad Modelo"));
            add(MetroMonterrey.paradas.get("Cuauhtemoc"));
            add(MetroMonterrey.paradas.get("Sendero"));
            add(MetroMonterrey.paradas.get("Regina"));
            add(MetroMonterrey.paradas.get("Conchello"));
        }};
    }


}
