package alquileres.vistas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import CarPlace.VistaGui;
import alquileres.control.ControlAlquiler;
import transfers.TAlquiler;
import transfers.TVehiculo;
import vehiculos.control.ControlVehiculo;
import vehiculos.vista.GUIMostrarVehiculo;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para mostrar un alquiler al usuario
 * @author CARPLACE
 *
 */
public class GUIMostrarAlquiler extends JPanel {
	
	private JTextField usuario;
	private JTextField vehiculo;
	private JTextField inicio;
	private JTextField fin;
	private JTextField txtPago;
	private JTextField txtPrecio;
	
	private JLabel lblUsuario;
	private JLabel lblVehiculo;
	private JLabel lblInicio; 
	private JLabel lblFin;
	private JLabel imagen;
	private JLabel lblTipoPago;
	private JLabel lblPrecio;
	
	private JButton btnMostrarVehiculo ;
	private JButton btnVolver;
	
	private JPanel panelIzdo;
	private JPanel panelDcho;
	private JPanel panelInfer;
		
	private TAlquiler alq;
	private TVehiculo tVehiculo;


	private ControlVehiculo cv;
	private ControlAlquiler ca;
	private VistaGui ventana;

	/**
	 * Construye una instancia de esta clase
	 * @param idAlq identificador del alquiler
	 * @param cAlq 	ControladorAlquileres
	 * @param cVeh	ControladorReservas
	 * @param ventana	VentanaPrincipal
	 */
	public GUIMostrarAlquiler(int idAlq,ControlAlquiler cAlq,ControlVehiculo cVeh,VistaGui ventana) {
		this.cv = cVeh;
		this.ca = cAlq;
		this.alq = this.ca.mostrarAlquiler(idAlq);
		this.ventana = ventana;
		this.inicializar();
		this.confEventos();
	}

	private void inicializar(){
		setLayout(new GridBagLayout());
		
		//PANEL DERECHO
		this.panelDcho = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		this.panelDcho.setBorder(BorderFactory.createTitledBorder("Mostrar alquiler"));
		constraints.insets = new Insets(5,5,5,5);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(this.panelDcho,constraints);
		
		this.lblUsuario = new JLabel("Usuario:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(50,20,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;

		this.panelDcho.add(this.lblUsuario,constraints);
		
		this.usuario = new JTextField(alq.getNick());
		this.usuario.setFont(new Font("Dialog", 1, 12));
		this.usuario.setPreferredSize(new Dimension(250, 20));
		this.usuario.setEditable(false);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(50,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.usuario,constraints);
		
		this.lblVehiculo = new JLabel("Vehiculo:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;

		this.panelDcho.add(this.lblVehiculo,constraints);
		
		this.tVehiculo = cv.mostrarVehiculo(this.alq.getMatricula());
		
		this.vehiculo = new JTextField(this.tVehiculo.getMarca() + " " + this.tVehiculo.getModelo());
		this.vehiculo.setFont(new Font("Dialog", 1, 12));
		this.vehiculo.setPreferredSize(new Dimension(250, 20));
		this.vehiculo.setEditable(false);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.vehiculo,constraints);
		
		
		this.btnMostrarVehiculo = new JButton("Mostrar vehiculo");
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/menu48.png");
		icono = new ImageIcon(url_icono);
		this.btnMostrarVehiculo.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,10);
		constraints.gridx = 2; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.btnMostrarVehiculo,constraints);
		
		this.lblInicio = new JLabel("Fecha inicio:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.lblInicio,constraints);
		
		

		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			this.inicio = new JTextField(dateFormat.format(dateFormat1.parse(alq.getInicio())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.inicio.setFont(new Font("Dialog", 1, 12));
		this.inicio.setPreferredSize(new Dimension(250, 20));
		this.inicio.setEditable(false);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.inicio,constraints);
		
		
		this.lblFin = new JLabel("Fecha fin:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.lblFin,constraints);
		
				
		try {
			this.fin = new JTextField(dateFormat.format(dateFormat1.parse(alq.getFin())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fin.setFont(new Font("Dialog", 1, 12));
		this.fin.setPreferredSize(new Dimension(250, 20));
		this.fin.setEditable(false);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.fin,constraints);
		
		this.lblTipoPago = new JLabel("Tipo de pago:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.lblTipoPago, constraints);
		
		this.txtPago = new JTextField(alq.getTipoPago());
		this.txtPago.setFont(new Font("Dialog", 1, 12));
		this.txtPago.setPreferredSize(new Dimension(250, 20));
		this.txtPago.setEditable(false);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.txtPago,constraints);
		
		
		this.lblPrecio = new JLabel("Precio:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.lblPrecio, constraints);
		
		Float precio =  alq.getPrecio();
		this.txtPrecio = new JTextField(precio.toString());
		this.txtPrecio.setFont(new Font("Dialog", 1, 12));
		this.txtPrecio.setEditable(false);
		this.txtPrecio.setPreferredSize(new Dimension(250, 20));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.txtPrecio,constraints);		
		
		
		
		//PANEL IZQUIERDO
		this.panelIzdo = new JPanel(new GridBagLayout());
		this.panelIzdo.setBackground(new Color(255,204,153));
		constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(this.panelIzdo,constraints);
		
		this.imagen = new JLabel();
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/magnifier23.png");
		icono = new ImageIcon(url_icono);
		this.imagen.setIcon(icono);
		this.panelIzdo.add(this.imagen);
		
		
		//PANEL INFERIOR
		this.panelInfer = new JPanel(new GridBagLayout());
		this.panelInfer.setBackground(new Color(255,204,153));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.panelInfer,constraints);
	
		
		this.btnVolver = new JButton("Volver");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/cancel30.png");
		icono = new ImageIcon(url_icono);
		this.btnVolver.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(15,605,20,20);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		this.panelInfer.add(this.btnVolver,constraints);
	}
	
	void confEventos(){
		this.btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			    remove();
			    ventana.add(VistaGui.sacaPila());
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				
			}
				
		});
		this.btnMostrarVehiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();			    
				meteAlaPila();
				remove();
				
				GUIMostrarVehiculo alq = new GUIMostrarVehiculo(tVehiculo.getMatricula(),cv, ventana);
				ventana.add(alq);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);	
			}
		});
	}
	
	private void remove(){
		ventana.remove(this);
	}
	
	private void meteAlaPila() {
		VistaGui.metePila(this);
	}
}