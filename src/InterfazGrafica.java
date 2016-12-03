import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JTextField;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;


public class InterfazGrafica extends EstacionesMonterrey {
    private ArrayList<String> listaParadas = new ArrayList<String>(MetroMonterrey.paradas.keySet());
    private JFrame InterfazGrafica;
    private JTextArea textAreaParadas;

    private Imagen dibujo = new Imagen();
    private JTextField textTiempo;
    private JTextField textDistancia;

    private static final int RADIUS = 15;
    private static final Color BGCOLOR = Color.LIGHT_GRAY;//new Color(135, 206, 235);//Color.LIGHT_GRAY;

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            try {
                InterfazGrafica window = new InterfazGrafica();
                window.InterfazGrafica.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InterfazGrafica() {
        initialize();
    }

    private void initialize() {
        InterfazGrafica = new JFrame();
        InterfazGrafica.getContentPane().setFont(new Font("Century Gothic", Font.BOLD, 15));
        InterfazGrafica.getContentPane().setBackground(BGCOLOR);
        InterfazGrafica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InterfazGrafica.setFont(new Font("Century Gothic", Font.BOLD, 15));
        InterfazGrafica.setTitle("Metro de Monterrey");
        InterfazGrafica.setBounds(300, 25, 1000, 680);
        InterfazGrafica.getContentPane().setLayout(null);

        JPanel panelOrigen = new JPanel();
        panelOrigen.setBackground(BGCOLOR);
        panelOrigen.setBounds(0, 10, 217, 76); //Bordes
        InterfazGrafica.getContentPane().add(panelOrigen);
        panelOrigen.setLayout(null);

        final JComboBox comboBoxOrigen = new JComboBox(listaParadas.toArray());
        comboBoxOrigen.setEditable(true);
        comboBoxOrigen.setBackground(SystemColor.textHighlight);
        comboBoxOrigen.setForeground(Color.WHITE);
        comboBoxOrigen.setFont(new Font("Century Gothic", Font.BOLD, 12));
        comboBoxOrigen.setBounds(25, 37, 182, 28);
        panelOrigen.add(comboBoxOrigen);

        JLabel labelOrigen = new JLabel("ORIGEN:");
        labelOrigen.setFont(new Font("Century Gothic", Font.BOLD, 15));
        labelOrigen.setForeground(Color.BLACK);
        labelOrigen.setBounds(25, 11, 104, 15);
        panelOrigen.add(labelOrigen);

        JPanel panelDestino = new JPanel();
        panelDestino.setBackground(BGCOLOR);
        panelDestino.setForeground(Color.WHITE);
        panelDestino.setLayout(null);
        panelDestino.setBounds(230, 10, 217, 76);
        InterfazGrafica.getContentPane().add(panelDestino);

        final JComboBox comboBoxDestino = new JComboBox(listaParadas.toArray());
        comboBoxDestino.setForeground(Color.WHITE);
        comboBoxDestino.setFont(new Font("Century Gothic", Font.BOLD, 12));
        comboBoxDestino.setEditable(true);
        comboBoxDestino.setBackground(SystemColor.textHighlight);
        comboBoxDestino.setBounds(10, 41, 197, 28);
        panelDestino.add(comboBoxDestino);

        JLabel labelDestino = new JLabel("DESTINO:");
        labelDestino.setFont(new Font("Century Gothic", Font.BOLD, 14));
        labelDestino.setBounds(10, 11, 77, 19);
        panelDestino.add(labelDestino);

        JButton buttonHallarRuta = new JButton("Hallar ruta");
        buttonHallarRuta.setForeground(Color.BLACK);
        buttonHallarRuta.setFont(new Font("Century Gothic", Font.BOLD, 12));
        buttonHallarRuta.setBackground(Color.GREEN);
        buttonHallarRuta.setBounds(455, 50, 117, 25);
        buttonHallarRuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Estacion estacionInicial = MetroMonterrey.paradas.get(comboBoxOrigen.getSelectedItem());
                Estacion estacionFinal= MetroMonterrey.paradas.get(comboBoxDestino.getSelectedItem());

                AEstrella aEstrella = new AEstrella(estacionInicial, estacionFinal);
                ArrayList<Estacion> estacionesOptimas = aEstrella.calcularMejorCamino();
                if (estacionesOptimas == null){
                    System.out.println("Empty estacionesOptimas");
                    return;
                }
                double tiempoTotal = aEstrella.getTiempoRecorridoTotal();
                double distanciaTotal = aEstrella.getDistanciaRecorridaTotal();
                dibujo.dibujarPuntosCamino(estacionesOptimas);

                String msg = "";
                for (Estacion estacion : estacionesOptimas) {
                    if (estacion != null) {
                        msg += estacion.getNombre() + "\n";
                    }
                }

                textAreaParadas.setText(msg);
                textTiempo.setText(String.format("%.2f minutos", tiempoTotal));
                textDistancia.setText(String.format("%.2f metros", distanciaTotal));
            }
        });

        InterfazGrafica.getContentPane().add(buttonHallarRuta);

        textAreaParadas = new JTextArea();
        textAreaParadas.setForeground(Color.WHITE);
        textAreaParadas.setBackground(SystemColor.textHighlight);
        textAreaParadas.setFont(new Font("Century Gothic", Font.BOLD, 15));
        JScrollPane scrollPaneParadas = new JScrollPane(textAreaParadas);
        scrollPaneParadas.setEnabled(false);
        scrollPaneParadas.setBounds(750, 75, 217, 300);
        InterfazGrafica.getContentPane().add(scrollPaneParadas);

        JPanel panelParadas = new JPanel();
        panelParadas.setLayout(null);
        panelParadas.setForeground(BGCOLOR);
        panelParadas.setBackground(BGCOLOR);
        panelParadas.setBounds(740, 10, 92, 48);
        InterfazGrafica.getContentPane().add(panelParadas);

        JLabel labelParadas = new JLabel("PARADAS:");
        labelParadas.setFont(new Font("Century Gothic", Font.BOLD, 14));
        labelParadas.setBounds(10, 15, 200, 20);
        panelParadas.add(labelParadas);

        //dibujo=new Imagen();
        dibujo.setBounds(0, 97, 730, 546);
        dibujo.setBackground(Color.WHITE);
        InterfazGrafica.getContentPane().add(dibujo);


        JPanel panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setForeground(Color.BLUE);
        panel_5.setBackground(BGCOLOR);
        panel_5.setBounds(740, 421, 217, 76);
        InterfazGrafica.getContentPane().add(panel_5);

        JLabel lblTiempoEstimado = new JLabel("Tiempo estimado:");
        lblTiempoEstimado.setForeground(Color.BLACK);
        lblTiempoEstimado.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblTiempoEstimado.setBounds(10, 11, 162, 15);
        panel_5.add(lblTiempoEstimado);

        textTiempo = new JTextField();
        textTiempo.setBackground(SystemColor.textHighlight);
        textTiempo.setForeground(Color.WHITE);
        textTiempo.setFont(new Font("Century Gothic", Font.BOLD, 15));
        textTiempo.setBounds(10, 44, 202, 21);
        panel_5.add(textTiempo);
        textTiempo.setColumns(10);

        textDistancia = new JTextField();
        textDistancia.setForeground(Color.WHITE);
        textDistancia.setFont(new Font("Century Gothic", Font.BOLD, 15));
        textDistancia.setColumns(10);
        textDistancia.setBackground(SystemColor.textHighlight);
        textDistancia.setBounds(750, 539, 202, 21);
        InterfazGrafica.getContentPane().add(textDistancia);

        JLabel lblDistanciaRecorrido = new JLabel("Distancia recorrida:");
        lblDistanciaRecorrido.setForeground(Color.BLACK);
        lblDistanciaRecorrido.setFont(new Font("Century Gothic", Font.BOLD, 15));
        lblDistanciaRecorrido.setBounds(750, 513, 162, 15);
        InterfazGrafica.getContentPane().add(lblDistanciaRecorrido);
    }
}
