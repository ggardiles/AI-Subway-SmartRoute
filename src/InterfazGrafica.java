import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFormattedTextField;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JList;
import java.awt.Scrollbar;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.ScrollPane;


public class InterfazGrafica extends EstacionesMonterrey {
	private AlgoritmoEstrella metro=new AlgoritmoEstrella();

	private JFrame InterfazGrafica;
	public AtributosEstacion Psalida;
	public AtributosEstacion Pllegada;
	public AtributosEstacion[] resultado;
	public JTextArea textArea;
	public String imp="";
	public double minutos;

	public JPanel dibujo,dibujo2;
	public String horario;
	public String tiempo;
	public String metros;
	public int modo=0;
	private JTextField textField;
	public int distancia;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGrafica window = new InterfazGrafica();
					window.InterfazGrafica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfazGrafica() {
		initialize();
	}

	private void initialize() {
		InterfazGrafica = new JFrame();
		InterfazGrafica.getContentPane().setFont(new Font("Century Gothic", Font.BOLD, 15));
		InterfazGrafica.getContentPane().setBackground(new Color(135, 206, 235));
		InterfazGrafica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InterfazGrafica.setBackground(Color.BLUE);
		InterfazGrafica.setForeground(Color.RED);
		InterfazGrafica.setFont(new Font("Century Gothic", Font.BOLD, 15));
		InterfazGrafica.setTitle("Metro de Praga");
		InterfazGrafica.setBounds(300, 25, 1000, 680);
		InterfazGrafica.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setForeground(Color.BLUE);
		panel.setBounds(0, 10, 217, 76); //Bordes
		InterfazGrafica.getContentPane().add(panel); 
		panel.setLayout(null);

		final JComboBox comboBox = new JComboBox(metro.Paradas);
		comboBox.setEditable(true);
		comboBox.setBackground(SystemColor.textHighlight);
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Century Gothic", Font.BOLD, 12));
		comboBox.setBounds(25, 37, 182, 28);
		panel.add(comboBox);

		JLabel lblSeleccionLaEstacion = new JLabel("ORIGEN:");
		lblSeleccionLaEstacion.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblSeleccionLaEstacion.setForeground(new Color(0, 0, 0));
		lblSeleccionLaEstacion.setBounds(25, 11, 104, 15);
		panel.add(lblSeleccionLaEstacion);
		//panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBox, lblSeleccionLaEstacion}));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 235));
		panel_1.setForeground(new Color(135, 206, 250));
		panel_1.setLayout(null);
		panel_1.setBounds(230, 10, 217, 76);
		InterfazGrafica.getContentPane().add(panel_1);

		final JComboBox comboBox_1 = new JComboBox(metro.Paradas);
		comboBox_1.setForeground(new Color(255, 255, 255));
		comboBox_1.setFont(new Font("Century Gothic", Font.BOLD, 12));
		comboBox_1.setEditable(true);
		comboBox_1.setBackground(SystemColor.textHighlight);
		comboBox_1.setBounds(10, 41, 197, 28);
		panel_1.add(comboBox_1);

		JLabel label = new JLabel("DESTINO:");
		label.setFont(new Font("Century Gothic", Font.BOLD, 14));
		label.setBounds(10, 11, 77, 19);
		panel_1.add(label);

		JFrame ventana=new JFrame();


		JButton btnNewButton = new JButton("Hallar ruta");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				Psalida = (AtributosEstacion) comboBox.getSelectedItem();
				Pllegada= (AtributosEstacion) comboBox_1.getSelectedItem();


				
				

				resultado=metro.AlgoritmoAEstrella(Psalida, Pllegada,modo);
				minutos=metro.getTiempo();
				distancia=metro.getDistancia();
				System.out.println("Distancia" + distancia);
				Graphics g = dibujo.getGraphics();
				
				int i=0;
				while(i<resultado.length){

					imp=imp+resultado[i]+"\n";

					//LINEA A
					g.setColor(Color.GREEN);

					if(resultado[i]==Talleres){
						g.fillOval(230, 165, 15, 15);}
					g.setColor(Color.GREEN);

					if(resultado[i]==SanBernabe){
						g.fillOval(230, 165, 15, 15);}
					g.setColor(Color.GREEN);

					if(resultado[i]==UnidadModelo){
						g.fillOval(230, 165, 15, 15);}
					g.setColor(Color.GREEN);

					/* Praga
					if(resultado[i]==Dejvicka){
						g.fillOval(230, 165, 15, 15);}
					g.setColor(Color.GREEN);

					if(resultado[i]==Hradcanska){
						g.fillOval(257, 175, 15, 15);}
					g.setColor(Color.GREEN);

					if(resultado[i]==Malostranska){
						g.fillOval(280, 195, 15, 15);}
					g.setColor(Color.GREEN);

					if(resultado[i]==Staromestska){
						g.fillOval(315,207,  15, 15);}
					
					g.setColor(Color.GREEN);

					if(resultado[i]==Namesti_Miru){
						g.fillOval(400,292, 15, 15);}
					g.setColor(Color.GREEN);

					if(resultado[i]==Jiriho_z_Podebrad){
						g.fillOval(442,292, 15, 15);}	
					g.setColor(Color.GREEN);

					if(resultado[i]==Flora){
						g.fillOval(480,292, 15, 15);}	
					g.setColor(Color.GREEN);

					if(resultado[i]==Zeliveskeho){
						g.fillOval(512,292, 15, 15);}	
					g.setColor(Color.GREEN);

					if(resultado[i]==Strasnicka){
						g.fillOval(552,322, 15, 15);}	
					g.setColor(Color.GREEN);

					if(resultado[i]==Skalka){
						g.fillOval(590,355, 15, 15);}	

					//TRANSBORDO LINEA A
					g.setColor(Color.BLACK);

					if(resultado[i]==Mustek){
						g.fillOval(340,230,20, 15);}	

					if(resultado[i]==Muzeum){
						g.fillOval(372,260, 20, 15);}						

					//LINEA B
					g.setColor(Color.YELLOW);

					if(resultado[i]==Cerny_most){
						g.fillOval(700,175,18,18);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Rajska_zahrada){
						g.fillOval(660,175,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Hloubetin){
						g.fillOval(610,175,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Kolbenova){
						g.fillOval(587,153,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Vysocanska){
						g.fillOval(551,153,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Ceskomoravska){
						g.fillOval(525,185,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Palmovka){
						g.fillOval(495,185,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Invalidovna){
						g.fillOval(450,200,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Krizikova){
						g.fillOval(418,200,15,15);}

					//AQUI IRIRA EL TRANSBORDO DE FLORENC
					g.setColor(Color.YELLOW);

					if(resultado[i]==Namesti_republiky){
						g.fillOval(355,213,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Narodni_trida){
						g.fillOval(315,250,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Karlovo_namesti){
						g.fillOval(317,290,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Andel){
						g.fillOval(275,320,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Smichovske_nadrazi){
						g.fillOval(275,350,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Radlicka){
						g.fillOval(246,385,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Jinonice){
						g.fillOval(212,385,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Nove_Butovice){
						g.fillOval(177,387,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Hurka){
						g.fillOval(147,387,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Luziny){
						g.fillOval(115,410,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Luka){
						g.fillOval(85,410,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Stodulky){
						g.fillOval(50,410,15,15);}
					g.setColor(Color.YELLOW);

					if(resultado[i]==Zlicin){
						g.fillOval(25,410,15,15);}

					//TRANSBORDO LINEA B
					g.setColor(Color.BLACK);

					if(resultado[i]==Florenc){
						g.fillOval(372,194,20,15);}				

					//LINEA C	
					g.setColor(Color.RED);

					if(resultado[i]==Ladvi){
						g.fillOval(460,15,15,15);}
					g.setColor(Color.RED);

					if(resultado[i]==Kobylisy){
						g.fillOval(404,13,15,15);}	

					g.setColor(Color.RED);


					if(resultado[i]==Nadrazi_Holesovice){
						g.fillOval(372,110,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Vltavska){
						g.fillOval(372,150,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Hlavni_nadrazi){
						g.fillOval(372,223,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==IP_Pavlova){
						g.fillOval(372,294,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Vysehrad){
						g.fillOval(372,355,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Prazskeho_povstani){
						g.fillOval(372,390,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Pankrac){
						g.fillOval(392,422,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Budejovicka){
						g.fillOval(420,450,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Kacerov){
						g.fillOval(455,480,15,15);}	
					g.setColor(Color.RED);

					if(resultado[i]==Roztyly){
						g.fillOval(495,500,15,15);}	

					if(resultado[i]==Chodov){
						g.fillOval(535,500,15,15);}	

					if(resultado[i]==Opatov){
						g.fillOval(580,500,15,15);}	

					if(resultado[i]==Haje){
						g.fillOval(615,500,15,15);}	

					*/
					i++;
				}
				textArea.setText(imp);
				tiempo=Double.toString(minutos)+" minutos ";
				textField.setText(tiempo);
				metros=Integer.toString(distancia)+" metros ";
				textField_1.setText(metros);

			}


		});

		btnNewButton.setBounds(455, 50, 117, 25);
		InterfazGrafica.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Nueva b�squeda");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				metro.nuevaBusqueda();
				imp="";
				tiempo="";
				Psalida=null;
				Pllegada=null;
				resultado=null;
				textArea.setText(imp);
				textField.setText(tiempo);
				dibujo.repaint();

			}
		});
		btnNewButton_1.setBounds(582, 50, 148, 25);
		InterfazGrafica.getContentPane().add(btnNewButton_1);
		textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(SystemColor.textHighlight);
		textArea.setFont(new Font("Century Gothic", Font.BOLD, 15));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(750, 50, 217, 300);
		InterfazGrafica.getContentPane().add(scrollPane);
				
				InterfazGrafica.getContentPane().add(scrollPane);
				JPanel panel_4 = new JPanel();
				panel_4.setLayout(null);
				panel_4.setForeground(new Color(135, 206, 250));
				panel_4.setBackground(new Color(135, 206, 235));
				panel_4.setBounds(740, 10, 92, 48);
				InterfazGrafica.getContentPane().add(panel_4);
				
				JLabel label_1 = new JLabel("PARADAS:");
				label_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
				label_1.setBounds(10, 11, 77, 19);
				panel_4.add(label_1);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(891, 198, -91, -105);
				InterfazGrafica.getContentPane().add(scrollPane_1);
				
				dibujo=new Imagen();
				dibujo.setBounds(0, 97, 730, 546);
				InterfazGrafica.getContentPane().add(dibujo);
				dibujo.setBackground(Color.WHITE);
				
				JPanel panel_5 = new JPanel();
				panel_5.setLayout(null);
				panel_5.setForeground(Color.BLUE);
				panel_5.setBackground(new Color(135, 206, 235));
				panel_5.setBounds(740, 421, 217, 76);
				InterfazGrafica.getContentPane().add(panel_5);
				
				JLabel lblTiempoEstimado = new JLabel("Tiempo estimado:");
				lblTiempoEstimado.setForeground(Color.BLACK);
				lblTiempoEstimado.setFont(new Font("Century Gothic", Font.BOLD, 15));
				lblTiempoEstimado.setBounds(25, 11, 162, 15);
				panel_5.add(lblTiempoEstimado);
				
						textField = new JTextField();
						textField.setBackground(SystemColor.textHighlight);
						textField.setForeground(Color.WHITE);
						textField.setFont(new Font("Century Gothic", Font.BOLD, 15));
						textField.setBounds(10, 44, 202, 21);
						panel_5.add(textField);
						textField.setColumns(10);
						
						textField_1 = new JTextField();
						textField_1.setForeground(Color.WHITE);
						textField_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
						textField_1.setColumns(10);
						textField_1.setBackground(SystemColor.textHighlight);
						textField_1.setBounds(750, 539, 202, 21);
						InterfazGrafica.getContentPane().add(textField_1);
						
						JLabel lblDistanciaRecorrido = new JLabel("Distancia recorrida:");
						lblDistanciaRecorrido.setForeground(Color.BLACK);
						lblDistanciaRecorrido.setFont(new Font("Century Gothic", Font.BOLD, 15));
						lblDistanciaRecorrido.setBounds(750, 513, 162, 15);
						InterfazGrafica.getContentPane().add(lblDistanciaRecorrido);
					
			


							
							
							
	}
}
