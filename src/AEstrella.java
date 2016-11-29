import java.util.ArrayList;

/**
 * Created by gabriel on 25/11/16.
 */

//Esqueleto de versión mejorada

public class AEstrella {

    private double gCost = 0;
    private ArrayList<Estacion> listaAbierta;
    private ArrayList<Estacion> listaCerrada;

    public ArrayList<Estacion> AEstrella(Estacion paradaInicial, Estacion paradaMeta) {

        // Crear Listas cerrada y abierta
        this.listaCerrada = new ArrayList<Estacion>();
        this.listaAbierta = new ArrayList<Estacion>();
        this.listaAbierta.add(paradaInicial);

        return calcularMejorCamino(paradaInicial, paradaMeta);
    }

    public double getDistanciaBetweenParadas(Estacion estacionInicio, Estacion estacionFin){
        return 0;
    }

    public double getTiempo(){
        return 0;
    }

    private ArrayList<Estacion> calcularMejorCamino(Estacion paradaInicial, Estacion paradaMeta){
        return null;
    }
}
