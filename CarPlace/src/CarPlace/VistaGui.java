package CarPlace;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import reservas.control.ControlReserva;
import reservas.vistas.GUIAltaReserva;
import reservas.vistas.GUIConsultarReservas;
import sesion.control.ControlSesion;
import sesion.vista.GUILogIn;
import transfers.TUsuario;
import usuarios.control.ControlUsuario;
import usuarios.vista.GUIAltaUsuario;
import usuarios.vista.GUIConsultaUsuarios;
import usuarios.vista.GUIMostrarUsuario;
import vehiculos.control.ControlVehiculo;
import vehiculos.vista.GUIAltaVehiculo;
import vehiculos.vista.GUIConsultarVehiculos;
import alquileres.control.ControlAlquiler;
import alquileres.vistas.GUIAltaAlquiler;
import alquileres.vistas.GUIConsultarAlquileres;





public class VistaGui extends JFrame{
	// Atributos.
	private Container contentPane;
	private static Stack<JPanel> paneles;
	
	private JMenuBar mb;
	private JMenu menuConexion;

	private JMenuItem desconectar;
	
	private JMenu usuarios;
	private JMenu vehiculos;
	private JMenu alquileres;
	private JMenu reservas;
	
	private JMenuItem altaReserva;
	private JMenuItem consultarReservas;
	private JMenuItem consultarVehiculos;
	private JMenuItem altaVehiculo;
	private JMenuItem altaAlquiler;
	private JMenuItem consultarAlquileres;
	private JMenuItem altaUsuario;
	private JMenuItem consultarUsuarios;
	private JMenuItem mostrarUsuario;
	
	private JLabel logo;
	private Thread hebra;
	private JLabel anuncio1;
	private JLabel anuncio2;
	private JLabel anuncio3;
	
	private ControlReserva cRes;
	private ControlVehiculo cVeh;
	private ControlSesion cSes;
	private ControlAlquiler cAlq;
	private ControlUsuario cUsu;
	
	private JPanel panelPrincipal;
	public static boolean esAdmin;
	public static TUsuario usuLog;
	
	public VistaGui(ControlReserva cRes, ControlAlquiler cAlq, ControlVehiculo cVeh,ControlUsuario cUsu,ControlSesion cSes){			
		// Titulo
		super("CarPlace");
		paneles = new Stack<JPanel>();
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/35638.png"));
	    setIconImage(icon);

		this.cRes = cRes;
		this.cVeh = cVeh;
		this.cSes = cSes;
		this.cUsu = cUsu;
		this.cAlq = cAlq;
		
		initGUI();
		
		confEventos();
	}


	private void initGUI(){
		
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setLayout(new GridBagLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		this.contentPane.setLayout(new GridBagLayout());
		this.setResizable(true);
		this.setContentPane(this.contentPane);
		this.setMinimumSize(new Dimension(400, 300));
		
		this.setLayout(new GridBagLayout());
		
		this.mb = new JMenuBar();
		this.setJMenuBar(this.mb);
		
		this.menuConexion = new JMenu("Conexión");
		this.mb.add(this.menuConexion);
		
		this.desconectar = new JMenuItem("Desconectar");
		this.menuConexion.add(this.desconectar);
		

		
		this.reservas = new JMenu("Reservas");
		this.mb.add(this.reservas);
		
		this.altaReserva = new JMenuItem("Alta reserva");
		this.reservas.add(this.altaReserva);
		
		this.consultarReservas = new JMenuItem("Consultar reservas");
		this.reservas.add(this.consultarReservas);
		
		this.vehiculos = new JMenu("Vehiculos");
		this.mb.add(this.vehiculos);


		
		this.altaVehiculo = new JMenuItem("Alta vehículo");
		this.vehiculos.add(this.altaVehiculo);
		
		this.consultarVehiculos = new JMenuItem("Consultar vehículos");
		this.vehiculos.add(this.consultarVehiculos);
		
		this.alquileres = new JMenu("Alquileres");
		this.mb.add(this.alquileres);
		
		this.altaAlquiler = new JMenuItem("Alta alquiler");
		this.alquileres.add(this.altaAlquiler);
		
		this.consultarAlquileres = new JMenuItem("Consultar alquileres");
		this.alquileres.add(this.consultarAlquileres);
		
		this.usuarios = new JMenu("Usuarios");
		this.mb.add(this.usuarios);
		
		this.altaUsuario =  new JMenuItem("Alta usuario");
		this.usuarios.add(this.altaUsuario);
		
		this.consultarUsuarios = new JMenuItem("Consultar usuarios");
		this.usuarios.add(this.consultarUsuarios);
		
		this.mostrarUsuario = new JMenuItem("Mostrar usuario");
		this.usuarios.add(this.mostrarUsuario);
				
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(new GUILogIn(this.cSes, this.cUsu, this), constraints);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private JPanel panelSuperior(){
		JPanel pS = new JPanel();
		
		this.logo = new JLabel();
		
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/logo.png");
		icono = new ImageIcon(url_icono);
		
		this.logo.setIcon(icono);
		pS.add(this.logo);
		this.logo.setVisible(true);
		
		
		
		return pS;
	}
	
	private JPanel panelInferior(){
		JPanel iN = new JPanel();
		
		this.anuncio1 = new JLabel();
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/anuncio1.png");
		icono = new ImageIcon(url_icono);
		this.anuncio1.setIcon(icono);
		iN.add(this.anuncio1);
		this.anuncio1.setVisible(false);
		
		this.anuncio2 = new JLabel();
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/anuncio2.png");
		icono = new ImageIcon(url_icono);
		this.anuncio2.setIcon(icono);
		iN.add(this.anuncio2);
		this.anuncio2.setVisible(false);
		
		this.anuncio3 = new JLabel();
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/anuncio3.png");
		icono = new ImageIcon(url_icono);
		this.anuncio3.setIcon(icono);
		iN.add(this.anuncio3);
		this.anuncio3.setVisible(false);
		
		return iN;
	}
	
	public static void metePila(JPanel panel){
		paneles.push(panel);
	}
	
	public static JPanel sacaPila(){
		return paneles.pop();
		
	}
	
	private void confEventos(){
		this.altaReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPAltaReserva();
			}
		});
		
		this.consultarReservas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPConsultarReservas();
			}
		});
		
		this.altaVehiculo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPAltaVehiculo();
				
			}
		});
		
		this.consultarVehiculos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPConsultarVehiculos();
				
			}
		});
		
		this.altaAlquiler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPAltaAlquiler();	
			}
		});
		
		this.consultarAlquileres.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPConsultarAlquileres();
			}
		});
		
		this.altaUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPAltaUsuario();	
			}
		});
		
		this.consultarUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPConsultarUsuarios();
			}
		});
		
		this.mostrarUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ponerPMostrarUsuario();
				
			}
		});
				
		this.desconectar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cSes.logOut(usuLog.getNick());
				
				JOptionPane optionPane = new JOptionPane("Hasta pronto! :) " + VistaGui.usuLog.getNick(), JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

				final JDialog dialog = new JDialog();
				dialog.setTitle("Message");
				dialog.setModal(true);

				dialog.setContentPane(optionPane);

				dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				dialog.pack();

				//create timer to dispose of dialog after 5 seconds
				Timer timer = new Timer(1500, new AbstractAction() {
				    @Override
				    public void actionPerformed(ActionEvent ae) {
				        dialog.dispose();
				    }
				});
				timer.setRepeats(false);//the timer should only go off once

				//start timer to close JDialog as dialog modal we must start the timer before its visible
				timer.start();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				ponerPLogin();
				usuLog = null;
			}
		});
		
	}
	
	private void ponerPLogin(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUILogIn pLogIn = new GUILogIn(cSes, cUsu, this);
		this.add(pLogIn);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
		
	};
		
	private void ponerPMostrarUsuario(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIMostrarUsuario pModUsu = new GUIMostrarUsuario(usuLog.getNick(), cUsu, this);
		VistaGui.metePila(this.panelPrincipal);
		this.add(pModUsu);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void ponerPAltaUsuario(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIAltaUsuario pAltaUsuario = new GUIAltaUsuario(cUsu, this);
		VistaGui.metePila(this.panelPrincipal);
		this.add(pAltaUsuario);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void ponerPConsultarUsuarios(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIConsultaUsuarios pConsUsuarios = new GUIConsultaUsuarios(cUsu, cAlq, cRes, cVeh, this);
		VistaGui.metePila(this.panelPrincipal);
		this.add(pConsUsuarios);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	
	private void ponerPConsultarAlquileres(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		
		this.contentPane.removeAll();
		GUIConsultarAlquileres pConsAlqu = new GUIConsultarAlquileres(cAlq, cVeh, this, usuLog.getNick());
		VistaGui.metePila(this.panelPrincipal);
		this.add(pConsAlqu);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	
	private void ponerPConsultarVehiculos(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIConsultarVehiculos pConsVehiculos = new GUIConsultarVehiculos(cVeh, this);
		VistaGui.metePila(this.panelPrincipal);
		this.add(pConsVehiculos);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
	}

	private void ponerPAltaVehiculo(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIAltaVehiculo pAltaVehiculo = new GUIAltaVehiculo(cVeh, this);
		VistaGui.metePila(this.panelPrincipal);
		this.add(pAltaVehiculo);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);	
	}
	
	
	private void ponerPConsultarReservas() {
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIConsultarReservas pConsultaReservas = new GUIConsultarReservas(this.cAlq, this.cRes,this.cVeh,this,VistaGui.usuLog.getNick());
		VistaGui.metePila(this.panelPrincipal);
		this.add(pConsultaReservas);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);		
	}
	
	private void ponerPAltaReserva(){
		
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIAltaReserva pAltaReserva = new GUIAltaReserva(this.cRes,this.cVeh ,this, usuLog.getNick());
		VistaGui.metePila(this.panelPrincipal);
		this.add(pAltaReserva);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	private void ponerPAltaAlquiler(){
		if(!paneles.empty()){
			while(!paneles.empty()){
				paneles.pop();
			}
		}
		this.contentPane.removeAll();
		GUIAltaAlquiler pAltaAlquiler = new GUIAltaAlquiler(cAlq, cVeh,cRes, this, usuLog.getNick(),-1);
		VistaGui.metePila(this.panelPrincipal);
		this.add(pAltaAlquiler);
		revalidate();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	
	public boolean pilaVacia(){
		return paneles.isEmpty();
	}
	
	public void ocultarMenu(){
		this.mb.setVisible(false);
		if(usuLog != null)
			this.logo.setVisible(false);
	}
	
	public void verMenu(){
		this.mb.setVisible(true);
		accionesPanelPricipal();
	}
	
	public void activDesItemMenu(){
		this.add(this.panelPrincipal);
		if(!usuLog.isAdmin()){
			this.vehiculos.setVisible(false);
			this.consultarUsuarios.setVisible(false);
			this.altaUsuario.setVisible(false);
		}
		else{
			this.vehiculos.setVisible(true);
			this.consultarUsuarios.setVisible(true);
			this.altaUsuario.setVisible(true);
		}
	}
	
	private void accionesPanelPricipal(){
		
		GridBagConstraints cons;
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 0; 
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.panelPrincipal.add(panelSuperior(), cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 0; 
		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.panelPrincipal.add(panelInferior(), cons);
		
		
		this.hebra = new Thread(){
			@Override
			public void run() {
				while(true){
					cambioAnuncio1();
					
					try {
						Thread.sleep(3000);
						cambioAnuncio2();
					} catch (InterruptedException e) {}
					
					try {
						Thread.sleep(3000);
						cambioAnuncio3();
						Thread.sleep(3000);
					} catch (InterruptedException e) {}
				}
				
			};
		};
		
		this.hebra.start();

	}
	
	private void cambioAnuncio1(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				anuncio1.setVisible(true);
				anuncio2.setVisible(false);
				anuncio3.setVisible(false);
			}
		});
	}
	
	private void cambioAnuncio2(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				anuncio1.setVisible(false);
				anuncio2.setVisible(true);
				anuncio3.setVisible(false);
			}
		});
	}
	
	private void cambioAnuncio3(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				anuncio1.setVisible(false);
				anuncio2.setVisible(false);
				anuncio3.setVisible(true);
			}
		});
	}

}
