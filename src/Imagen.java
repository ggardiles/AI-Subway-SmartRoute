import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Imagen extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Point> puntosRecorrido;
	private ArrayList<Estacion> estacionesOptimas;
	private final static int RADIUS = 15;

	public Imagen(){
		this.puntosRecorrido = new ArrayList<Point>();
		this.estacionesOptimas = new ArrayList<Estacion>();
		this.setBackground(Color.yellow);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Comment-out to redebug points in image
				/*int x = e.getX();
				int y = e.getY();
				System.out.println(String.format("x: %d    y:  %d",x,y));
				puntosRecorrido.add(new Point(x, y));
				repaint();*/
			}
		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		Image imagen = new ImageIcon(getClass().getResource("metro_monterrey.png")).getImage();
		//Pintamos desde 0 ,0  hasta la maxima logitud y anchura del panel
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
		/**
		 * Commented out the functionality used to debug the specic station points
		 */
		/*
		for (Point point : puntosRecorrido) {
			g.fillOval(point.x, point.y, RADIUS, RADIUS);
		}
		*/
		for (Estacion i : estacionesOptimas){
			if (i==null)
				continue;
			g.setColor(Color.YELLOW);
			if (i.getLinea() == 2)
				g.setColor(Color.GREEN);
			else if(i.getLinea() == 3)
				g.setColor(Color.PINK);

			if (i.isTransbordo()){
				g.fillRect(i.getxPos(), i.getyPos(), RADIUS, RADIUS);
			}else{
				g.fillOval(i.getxPos(), i.getyPos(), RADIUS, RADIUS);
			}
		}
	}

	public void dibujarPuntosCamino(ArrayList<Estacion> estaciones){
		this.estacionesOptimas = estaciones;
		repaint();
	}
}
