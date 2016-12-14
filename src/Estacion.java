/**
 * Created by gabriel on 25/11/16.
 */


// Version mejorada del proyecto
public class Estacion {
    private String nombre;
    private int linea;
    private String[] estacionesConectadas;
    private boolean isTransbordo;
    private double gCost;
    private double hCost;
    private int xPos; //Position in image
    private int yPos;
    private String whoWasMyParent; //Leave blank -> will be used in AEstrella

    public Estacion(String nombre, int linea, String[] estacionesConectadas, boolean isTransbordo, int gCost, int hCost, int xPos, int yPos) {
        this.nombre = nombre;
        this.linea = linea;
        this.estacionesConectadas = estacionesConectadas;
        this.isTransbordo = isTransbordo;
        this.gCost = gCost;
        this.hCost = hCost;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String[] getEstacionesConectadas() {
        return estacionesConectadas;
    }

    public void setEstacionesConectadas(String[] estacionesConectadas) {
        this.estacionesConectadas = estacionesConectadas;
    }

    public boolean isTransbordo() {
        return isTransbordo;
    }

    public void setTransbordo(boolean transbordo) {
        isTransbordo = transbordo;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getTotalCost(){
        return gCost + hCost;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public String getWhoWasMyParent() {
        return whoWasMyParent;
    }

    public void setWhoWasMyParent(String whoWasMyParent) {
        this.whoWasMyParent = whoWasMyParent;
    }
}
