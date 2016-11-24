public class AtributosEstacion {
	private String nombre;
	private String[] sigEstacion;
	private String[] antEstacion;
	private int[] tiempoSigEstacion;
	private int[] tiempoAntEstacion;
	private boolean isTransbordo;
	private int tiempoTransbordo;
	private String[] lineas;
	private double i;
	private double j;
	protected int g;
	protected int h;

	@Override
	public String toString() {
		return nombre;
	}

	public AtributosEstacion(String nombre, String sigEstacion1, String antEstacion1, int tiempoSigEstacion1, int tiempoAntEstacion1,	boolean isTransbordo,	int tiempoTransbordo,String linea1,double i,double j){

		this.nombre = nombre;
		String[] a= {sigEstacion1,null,null};
		sigEstacion = a;
		String[] a2 = {antEstacion1,null,null};
		antEstacion = a2;
		int[] b = {tiempoSigEstacion1};
		tiempoSigEstacion = b;
		int[] c = {tiempoAntEstacion1};
		tiempoAntEstacion = c;
		this.isTransbordo = isTransbordo;
		this.tiempoTransbordo = tiempoTransbordo;
		String[] a3 = {linea1};
		this.lineas = a3;
		this.i=i;
		this.j=j;
	}

	public AtributosEstacion(String nombre, String sigEstacion1, String antEstacion1, int tiempoSigEstacion1, int tiempoAntEstacion1,	boolean isTransbordo,	int tiempoTransbordo,String sigEstacion2, String antEstacion2,
			int tiempoSigEstacion2, int tiempoAntEstacion2,String linea1, String linea2,double i,double j){

		this.nombre = nombre;
		String[] a= {sigEstacion1,sigEstacion2,null};
		sigEstacion = a;
		String[] a2 = {antEstacion1,antEstacion2,null};
		antEstacion = a2;
		int[] b = {tiempoSigEstacion1,tiempoSigEstacion2};
		tiempoSigEstacion = b;
		int[] c = {tiempoAntEstacion1,tiempoAntEstacion2};
		tiempoAntEstacion = c;
		this.isTransbordo = isTransbordo;
		this.tiempoTransbordo = tiempoTransbordo;
		String[] a3 = {linea1,linea2};
		this.lineas = a3;
		this.i=i;
		this.j=j;
	}

	public AtributosEstacion(String nombre, String sigEstacion1, String antEstacion1, int tiempoSigEstacion1, int tiempoAntEstacion1,	boolean isTransbordo,	int tiempoTransbordo, String sigEstacion2, String antEstacion2,
			int tiempoSigEstacion2, int tiempoAntEstacion2, String sigEstacion3, String antEstacion3, int tiempoSigEstacion3, int tiempoAntEstacion3, String linea1, String linea2, String linea3,double i,double j) {

		this.nombre = nombre;
		String[] a= {sigEstacion1,sigEstacion2,sigEstacion3};
		sigEstacion = a;
		String[] a2 = {antEstacion1,antEstacion2,antEstacion3};
		antEstacion = a2;
		int[] b = {tiempoSigEstacion1,tiempoSigEstacion2,tiempoSigEstacion3};
		tiempoSigEstacion = b;
		int[] c = {tiempoAntEstacion1,tiempoAntEstacion2,tiempoAntEstacion3};
		tiempoAntEstacion = c;
		this.isTransbordo = isTransbordo;
		this.tiempoTransbordo = tiempoTransbordo;
		String[] a3 = {linea1,linea2,linea3};
		this.lineas = a3;
		this.i=i;
		this.j=j;
	}


	public String getNombre(){
		return nombre;
	}

	public String[] getSigEstacion() {
		return sigEstacion;
	}



	public String[] getAntEstacion() {
		return antEstacion;
	}



	public int[] getTiempoSigEstacion() {
		return tiempoSigEstacion;
	}



	public int[] getTiempoAntEstacion() {
		return tiempoAntEstacion;
	}



	public boolean isTransbordo() {
		return isTransbordo;
	}



	public int getTiempoTransbordo() {
		return tiempoTransbordo;
	}



	public String[] getLineas() {
		return lineas;
	}

	public double getI(){
		return i;
	}

	public double getJ(){
		return j;
	}

	public int getG(){
		return g;
	}

	public int getH(){
		return h;
	}

	public void setG(int a){
		g=a;
	}

	public void setH(int a){
		h=a;
	}
}
