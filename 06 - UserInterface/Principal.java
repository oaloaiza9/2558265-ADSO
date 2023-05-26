import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Principal{
	public static void main(String[] args) {
		
		JFrame ventana = new JFrame();
		ventana.setTitle("Primer IU");
		ventana.setSize(500, 400);
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);

		JPanel contenedor = new JPanel();
		contenedor.setLayout( new BoxLayout(contenedor, BoxLayout.Y_AXIS) );
		contenedor.setBorder( new EmptyBorder(15,15,15,15) );

		Font fuenteInputs = new Font("Arial", Font.PLAIN, 20);
		Font fuenteLabels = new Font("Arial", Font.PLAIN, 18);
		EmptyBorder borderLabel = new EmptyBorder(15,0,0,0);

		JLabel etq_titulo = new JLabel("REGISTRO");
		etq_titulo.setFont( new Font("Cooper Black", Font.BOLD, 30) );
		etq_titulo.setForeground( Color.decode("#800080") );
		etq_titulo.setBackground( Color.WHITE );
		etq_titulo.setOpaque(true);
		etq_titulo.setBorder( new EmptyBorder(0,25,0,25) );

		JLabel etq_cedula = new JLabel("Cedula:");
		etq_cedula.setFont( fuenteLabels );
		etq_cedula.setBorder( borderLabel );
		JTextField campo_cedula = new JTextField();
		campo_cedula.setFont( fuenteInputs );

		JLabel etq_nombres = new JLabel("Nombres");
		etq_nombres.setFont( fuenteLabels );
		etq_nombres.setBorder( borderLabel );
		JTextField campo_nombres = new JTextField();
		campo_nombres.setFont( fuenteInputs );

		JLabel etq_apellidos = new JLabel("Apellidos");
		etq_apellidos.setFont( new Font("Arial", Font.PLAIN, 18) );
		etq_apellidos.setBorder( borderLabel );
		JTextField campo_apellidos = new JTextField();
		campo_apellidos.setFont( fuenteInputs );
		
		JLabel etq_espacio = new JLabel("");
		etq_espacio.setBorder( new EmptyBorder(20,0,0,0) );

		JButton btn_registrar = new JButton("REGISTRAR");

		JLabel etq_texto = new JLabel(" ------ ");
		etq_texto.setFont( new Font("Arial", Font.BOLD, 25) );
		etq_texto.setBorder( new EmptyBorder(20,0,20,0) );

		contenedor.add( etq_titulo );
		contenedor.add( etq_cedula );
		contenedor.add( campo_cedula );
		contenedor.add( etq_nombres );
		contenedor.add( campo_nombres );
		contenedor.add( etq_apellidos );
		contenedor.add( campo_apellidos );
		contenedor.add( etq_espacio );
		contenedor.add( btn_registrar );
		contenedor.add( etq_texto );

		ventana.add( contenedor );
		
		ventana.setVisible(true);
		ventana.pack();

		
		ActionListener eventoRegistrar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				etq_titulo.setForeground(Color.BLUE);
				
				String cedula = campo_cedula.getText();
				String nombres = campo_nombres.getText();
				String apellidos = campo_apellidos.getText();

				String temporal = cedula+" - "+nombres+" "+apellidos;
				etq_texto.setText( temporal );

				campo_cedula.setText("");
				campo_nombres.setText("");
				campo_apellidos.setText("");

				campo_cedula.requestFocus();
			}
		};
		btn_registrar.addActionListener( eventoRegistrar );

	}
}