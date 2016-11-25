/**
 * Created by gabriel on 25/11/16.
 */


// Version mejorada del proyecto
public class Estacion {
    private String nombre;
    private int linea;
    private int[] nearbyIDs;
    private double latitude;
    private double longitude;
    private boolean isTransbordo;
    private int gCost;
    private int hCost;
    private int xPos; //Position in image
    private int yPos;

    public Estacion(String nombre, int linea, int[] nearbyIDs, double latitude, double longitude,
                    boolean isTransbordo, int gCost, int hCost, int xPos, int yPos) {
        this.nombre = nombre;
        this.linea = linea;
        this.nearbyIDs = nearbyIDs;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public int[] getnearbyIDs() {
        return nearbyIDs;
    }

    public void setnearbyIDs(int[] nearbyIDs) {
        this.nearbyIDs = nearbyIDs;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isTransbordo() {
        return isTransbordo;
    }

    public void setTransbordo(boolean transbordo) {
        isTransbordo = transbordo;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
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
}
