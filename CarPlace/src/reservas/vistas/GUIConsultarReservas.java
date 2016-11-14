package reservas.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import alquileres.control.ControlAlquiler;
import alquileres.vistas.GUIAltaAlquiler;
import CarPlace.VistaGui;
import reservas.control.ControlReserva;
import transfers.TReserva;
import vehiculos.control.ControlVehiculo;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para consultar las reservas de un usuario.
 * @author CARPLACE
 *
 */
public class GUIConsultarReservas  extends JPanel{
	
	private JLabel lblListaDeReservas;
	private JList lista_reservas;	
	private DefaultListModel<TReserva> modListaPanel;
	
	private JButton alquilar;
	private JButton mostrar;
	private JButton eliminar;
	private JButton volver;
	private JLabel imagen;	
	private ControlReserva cReserva;
	
	private JPanel panelDcho;
	private JPanel panelIzdo;
	private JPanel panelInfer;
	
	private VistaGui ventana;
	private ControlVehiculo cVehiculo;
	private ControlAlquiler cAlquiler;
	private String usuario;
	
	/**
	 * Construye una instancia de esta clase
	 * @param cAlq ControladorAlquileres
	 * @param cRes ControladorReservas
	 * @param cVeh ControladorVehiculos
	 * @param ventana VentanaPrincipal
	 * @param usuario identificador del usuario
	 */
	public GUIConsultarReservas(ControlAlquiler cAlq, ControlReserva cRes,ControlVehiculo cVeh,VistaGui ventana,String usuario) {
		this.cReserva = cRes;
		this.cVehiculo = cVeh;
		this.cAlquiler = cAlq;
		this.ventana = ventana;
		this.usuario =  usuario;
		
		this.inicializar();
		this.confEventos();
	}

	private void inicializar() {
		this.setLayout(new GridBagLayout());
		
		//PANEL DERECHO
		this.panelDcho = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		this.panelDcho.setBorder(BorderFactory.createTitledBorder("Consultar reservas"));
		constraints.insets = new Insets(5,5,5,5);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(this.panelDcho,constraints);
		
		this.lblListaDeReservas = new JLabel("Lista de reservas del usuario: " + this.usuario);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.lblListaDeReservas,constraints);
		
		this.modListaPanel = new DefaultListModel();
		this.lista_reservas = new JList(this.modListaPanel);
		
		JScrollPane scrollLista = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista.setPreferredSize(new Dimension(350, 200));
		scrollLista.setViewportView(this.lista_reservas);
		this.lista_reservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.fill = GridBagConstraints.BOTH;
		this.panelDcho.add(scrollLista,constraints);
		
		ArrayList<TReserva> listaReservas = this.cReserva.consultarReservas(usuario);
		for(TReserva it: listaReservas){
			modListaPanel.addElement(it);
		}
		
		this.alquilar = new JButton("Alquilar");
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/shopping102.png");
		icono = new ImageIcon(url_icono);
		this.alquilar.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,10);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.alquilar,constraints);
		
		this.mostrar= new JButton("Mostrar reserva");
		this.mostrar.setHorizontalAlignment(SwingConstants.LEFT);
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/menu48.png");
		icono = new ImageIcon(url_icono);
		this.mostrar.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,10);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.mostrar,constraints);
		
		this.eliminar= new JButton("Eliminar reserva");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/garbage21.png");
		icono = new ImageIcon(url_icono);
		this.eliminar.setIcon(icono);
		this.eliminar.setHorizontalAlignment(SwingConstants.LEFT);;
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,10);
		constraints.gridx = 1; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.eliminar,constraints);
		
		//PANEL IZQUIERDO
		this.panelIzdo = new JPanel(new GridBagLayout());
		this.panelIzdo.setBackground(new Color(255,255,204));
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
		url_icono = getClass().getResource("imagenes/search102.png");
		icono = new ImageIcon(url_icono);
		this.imagen.setIcon(icono);
		this.panelIzdo.add(this.imagen);
		
		//PANEL INFERIOR
		this.panelInfer = new JPanel(new GridBagLayout());
		this.panelInfer.setBackground(new Color(255,255,204));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.panelInfer,constraints);
		
		this.volver = new JButton("Volver");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/cancel30.png");
		icono = new ImageIcon(url_icono);
		this.volver.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,620,20,0);
		constraints.gridx = 1; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelInfer.add(this.volver,constraints);
		
		mostrar.setEnabled(false);
		alquilar.setEnabled(false);
		eliminar.setEnabled(false);
	
	}

	void confEventos(){
		this.volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				 remove();
				 if(!ventana.pilaVacia()){
					 ventana.add(VistaGui.sacaPila());
				 	 revalidate();
					 ventana.pack();
					 ventana.setLocationRelativeTo(null);
				 }
				 else{
				 	 revalidate();
					 ventana.pack();
					 ventana.setLocationRelativeTo(null);
				 }
	
			}
				
		});
		
		this.eliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i1 = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la reserva?." + "\n", "Confirmación", JOptionPane.YES_NO_OPTION);
				if(i1 == JOptionPane.YES_OPTION){
					int i = lista_reservas.getSelectedIndex();
					TReserva tReserva = modListaPanel.get(i);
					if(cReserva.bajaReserva(tReserva.getId())){
						modListaPanel.removeElementAt(i);
						alquilar.setEnabled(false);
						mostrar.setEnabled(false);
						eliminar.setEnabled(false);
					}
				}	
			}
			
				
		});
		
	    this.mostrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				meteAlaPila();
				remove();
				int i = lista_reservas.getSelectedIndex();
				GUIMostrarReserva reservap = new GUIMostrarReserva(cReserva,cVehiculo,modListaPanel.getElementAt(i).getId(), ventana);
				ventana.add(reservap);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
			}
				
		});
	    
	    this.alquilar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				meteAlaPila();
				remove();
				int i = lista_reservas.getSelectedIndex();
				GUIAltaAlquiler res = new GUIAltaAlquiler(cAlquiler, cVehiculo, cReserva, ventana, usuario, modListaPanel.getElementAt(i).getId());
				ventana.add(res);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
			}
				
		});
	    
	    this.lista_reservas.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mostrar.setEnabled(true);
				alquilar.setEnabled(true);
				eliminar.setEnabled(true);
			}
		});
	}
	
	private void meteAlaPila(){
		VistaGui.metePila(this);
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
	
}