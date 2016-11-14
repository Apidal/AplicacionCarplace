package alquileres.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import transfers.TAlquiler;
import transfers.TVehiculo;
import vehiculos.control.ControlVehiculo;
import CarPlace.VistaGui;
import alquileres.control.ControlAlquiler;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para consultar los alquileres de un usuario.
 * @author CARPLACE
 *
 */
public class GUIConsultarAlquileres extends JPanel{
	
	private JLabel lblListaDeAlquileres;
	private JLabel imagen;

	private JList lista_alquileres;	
	private DefaultListModel<TAlquiler> modListaPanel;
	
	private JButton mostrar;
	private JButton eliminar;
	private JButton volver;
	
	private JPanel panelDcho;
	private JPanel panelIzdo;
	private JPanel panelInfer;
	
	private ControlAlquiler cAlquiler;
	private ControlVehiculo cVehiculo;
	private VistaGui ventana;
	private String nick;
	
	/**
	 * Construye una instancia de esta clase
	 * @param cAlq ControladorAlquileres
	 * @param cVeh ControladorVehiculos
	 * @param ventana VentanaPrincipal
	 * @param usuario identificador de usuario
	 */
	public GUIConsultarAlquileres(ControlAlquiler cAlq,ControlVehiculo cVeh,VistaGui ventana,String usuario){
		this.cAlquiler = cAlq;
		this.cVehiculo = cVeh;
		this.ventana = ventana;
		this.nick =  usuario;
		this.inicializar();
		this.confEventos();
	}

	private void inicializar() {
		this.setLayout(new GridBagLayout());

		//PANEL DERECHO
		this.panelDcho = new JPanel(new GridBagLayout());
		this.panelDcho.setBorder(BorderFactory.createTitledBorder("Consultar alquileres"));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5,5,5,5);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(this.panelDcho,constraints);
		
		this.lblListaDeAlquileres = new JLabel("Lista de alquileres del usuario: " + this.nick);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.lblListaDeAlquileres,constraints);
		
		this.modListaPanel = new DefaultListModel();
		this.lista_alquileres = new JList(this.modListaPanel);	
		JScrollPane scrollLista = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista.setViewportView(this.lista_alquileres);
		this.lista_alquileres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollLista.setPreferredSize(new Dimension(350, 200));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.fill = GridBagConstraints.BOTH;
		this.panelDcho.add(scrollLista,constraints); 
		
		ArrayList<TAlquiler> listaAlquileres = this.cAlquiler.consultaAlquileresUsuario(nick);

		for(TAlquiler it: listaAlquileres){
			modListaPanel.addElement(it);
		}
		

		
		this.mostrar= new JButton("Mostrar alquiler");
		this.mostrar.setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/menu48.png");
		icono = new ImageIcon(url_icono);
		this.mostrar.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,10);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.mostrar,constraints);
		
		this.eliminar= new JButton("Eliminar alquiler");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/garbage21.png");
		icono = new ImageIcon(url_icono);
		this.eliminar.setIcon(icono);
		this.eliminar.setHorizontalAlignment(SwingConstants.LEFT);;
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,21,0,10);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.eliminar,constraints);
		
		
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
		url_icono = getClass().getResource("imagenes/phone2.png");
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
		
		this.mostrar.setEnabled(false);
		this.eliminar.setEnabled(false);
	
	}
	
	void confEventos(){
		this.volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 remove();
				 ventana.add(VistaGui.sacaPila());
				 revalidate();
				 ventana.pack();
				 ventana.setLocationRelativeTo(null);
			}
				
		});
		
		this.eliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i1 = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el alquiler?." + "\n", "Confirmación", JOptionPane.YES_NO_OPTION);
				if(i1 == JOptionPane.YES_OPTION){
					int i = lista_alquileres.getSelectedIndex();
					TAlquiler tAlq = modListaPanel.getElementAt(i);
					if(cAlquiler.bajaAlquiler(tAlq.getId())){
						modListaPanel.removeElementAt(i);
						mostrar.setEnabled(false);
						eliminar.setEnabled(false);
					}
					else
						System.out.println("ERROR");
				}
			}
				
		});
		
	    this.mostrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				meteAlaPila();
				remove();
				int i = lista_alquileres.getSelectedIndex();
				TAlquiler tAlquiler = modListaPanel.getElementAt(i);
				GUIMostrarAlquiler res = new GUIMostrarAlquiler(tAlquiler.getId(),cAlquiler,cVehiculo, ventana);
				ventana.add(res);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
			}
		});
	    
	    this.lista_alquileres.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mostrar.setEnabled(true);
				eliminar.setEnabled(true);
			}
		});
	    
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
	
	private void meteAlaPila(){
		VistaGui.metePila(this);
	}
}