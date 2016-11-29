import java.awt.*;
import javax.swing.*;

public class Imagen extends JPanel {

	private static final long serialVersionUID = 1L;
	EstacionesMonterrey metro2 =new EstacionesMonterrey();


	public Imagen(){
		this.setBackground(Color.yellow);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Image imagen = new ImageIcon(getClass().getResource("metro_monterrey.png")).getImage();
		//Pintamos desde 0 ,0  hasta la maxima logitud y anchura del panel
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
	}

}
