package usuarios.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import alquileres.control.ControlAlquiler;
import alquileres.vistas.GUIConsultarAlquileres;
import CarPlace.VistaGui;
import reservas.control.ControlReserva;
import reservas.vistas.GUIConsultarReservas;
import transfers.TUsuario;
import transfers.TVehiculo;
import usuarios.control.ControlUsuario;
import vehiculos.control.ControlVehiculo;
import vehiculos.vista.GUIMostrarVehiculo;

public class GUIConsultaUsuarios extends JPanel{
	private JPanel pIzq;
	private JPanel pDer;
	private JPanel pDerArriba;
	private JPanel pDerMedio;
	private JPanel pDerAbajo;
	private JPanel pFiltro;
	
	private JLabel labelTitulo;
	private JTextField cFiltro;
	
	private JScrollPane listaUsu;
	
	private JButton bMostrar;
	
	private JButton bEliminar;
	private JButton bCancelar;
	private JButton bConsReservas;
	private JButton bConsAlquileres;
	private JButton bFiltro;
	
	private JList listaNicks;
	
	private ControlUsuario controlUsu;
	
	private VistaGui ventanaPrincipal;
	private ControlAlquiler controlAlq;
	private ControlReserva controlRes;
	private ControlVehiculo controlVeh;
	
	/**
	 * Construye una instancia de esta clase.
	 * @param controlUsu controlador del usuario
	 * @param controlAlq controlador de alquileres
	 * @param controlRes controlador de reservas
	 * @param controlVeh controlador de vehculos
	 * @param v ventana principal
	 */
	public GUIConsultaUsuarios(ControlUsuario controlUsu,ControlAlquiler controlAlq,ControlReserva controlRes,ControlVehiculo controlVeh, VistaGui v){

		this.controlUsu = controlUsu;
		this.controlAlq = controlAlq;
		this.controlRes = controlRes;
		this.controlVeh = controlVeh;
		this.ventanaPrincipal = v;
		
		configuraPanelPrincipal();
		configurarEventos();

	}
	
	private void configuraPanelPrincipal(){
		this.setLayout(new GridBagLayout());
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
		this.add(PanelIzquierdo(), cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 1; 
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHEAST;
		this.add(PanelDerecho(), cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 0; 
		cons.gridy = 1;
		cons.gridwidth = 3;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.EAST;
		this.add(panelInferior(), cons);
		
		bMostrar.setEnabled(false);
		bConsReservas.setEnabled(false);
		bConsAlquileres.setEnabled(false);
		bEliminar.setEnabled(false);
	}
	
	private JPanel PanelIzquierdo(){
		JPanel pinf = new JPanel();
		
		JLabel i = new JLabel();
		
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/li.png");
		icono = new ImageIcon(url_icono);
		
		i.setIcon(icono);
		pinf.add(i);
		
		pinf.setBackground(new Color(240,128,128));
		
		return pinf;
	}
	
	private JPanel panelInferior(){
		JPanel pi = new JPanel();
		pi.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
			
		this.bCancelar  = new JButton("Volver");
		this.bCancelar.setIcon(new ImageIcon(getClass().getResource("imagenes/atras.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 250, 5, 5);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.bCancelar, constraints);
		
		pi.setBackground(new Color(240,128,128));
		
		return pi;
	}
	
	private JPanel PanelDerecho(){
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
		
		panelIzquierdo();	
		p.add(pIzq);
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 10, 0, 0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		p.add(this.pIzq, constraints);
		
		panelDerecho();
		p.add(pDer);
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(25, 0, 0, 10);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		p.add(this.pDer, constraints);
		
		
		return p;
	}
	
	private void panelIzquierdo(){
		pIzq = new JPanel();
		pIzq.setLayout(new BoxLayout(pIzq, BoxLayout.Y_AXIS));

		etiquetaTitulo();
		panelfiltro();
		panelScroll();
	}
	
	private void etiquetaTitulo(){
		labelTitulo = new JLabel();
		labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 20));
		labelTitulo.setText("Lista de usuarios");
		pIzq.add(labelTitulo);
		pIzq.add(Box.createVerticalStrut(5));
	}
	
	private void panelfiltro(){
		pFiltro = new JPanel();
		pFiltro.setLayout(new BoxLayout(pFiltro, BoxLayout.X_AXIS));
		campoFiltro();
		botonFiltro();
		pIzq.add(pFiltro);
	}
	
	private void panelScroll(){
		listaUsu = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listaNicks = controlUsu.consultarUsuarios();
		listaUsu.setPreferredSize(new Dimension(200, 150));
		listaUsu.setViewportView(listaNicks);
		
		pIzq.add(listaUsu);
	}
	
	private void panelDerecho(){
		pDer = new JPanel();
		Dimension dimension180_30 = new Dimension(180,20);
		pDer.setLayout(new BoxLayout(pDer, BoxLayout.Y_AXIS));
		
		pDer.add(Box.createVerticalStrut(10));
		panelDerArriba(dimension180_30);
		panelDerMedio(dimension180_30);
		panelDerAbajo(dimension180_30);
	
		
	}
	
	private void panelDerArriba(Dimension dimension180_30){
		pDerArriba = new JPanel();
		pDerArriba.setLayout(new BoxLayout(pDerArriba, BoxLayout.X_AXIS));
		pDerArriba.add(Box.createHorizontalStrut(10));
		botonMostrar(dimension180_30);
		pDerArriba.add(Box.createHorizontalStrut(10));
		botonconsultarAlquileres(dimension180_30);
		pDer.add(pDerArriba);
	}
	
	private void panelDerMedio(Dimension dimension180_30){
		pDerMedio = new JPanel();
		pDerMedio.setLayout(new BoxLayout(pDerMedio, BoxLayout.X_AXIS));
		pDerMedio.add(Box.createHorizontalStrut(10));
		botonConsultarReservas(dimension180_30);
		pDer.add(pDerMedio);
	}
	
	private void panelDerAbajo(Dimension dimension180_30){
		pDerAbajo = new JPanel();
		pDerAbajo.setLayout(new BoxLayout(pDerAbajo, BoxLayout.X_AXIS));
		pDerMedio.add(Box.createHorizontalStrut(10));
		botonEliminar(dimension180_30);
		pDer.add(pDerAbajo);
	}
	
	private void botonMostrar(Dimension dimension180_30){
		bMostrar = new JButton("   Mostrar usuario ");
		bMostrar.setAlignmentX(0.3f);
		pDerArriba.add(bMostrar);
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/menu.png");
		icono = new ImageIcon(url_icono);
		bMostrar.setIcon(icono);
		pDer.add(Box.createRigidArea(dimension180_30));
	}
	
	private void botonconsultarAlquileres(Dimension dimension180_30){
		bConsAlquileres = new JButton("Consultar Alquileres");
		bConsAlquileres.setAlignmentX(0.3f);
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/lupa.png");
		icono = new ImageIcon(url_icono);
		bConsAlquileres.setIcon(icono);
		pDerArriba.add(bConsAlquileres);
		pDer.add(Box.createRigidArea(dimension180_30));
	}
		
	private void botonConsultarReservas(Dimension dimension180_30){
		bConsReservas = new JButton("Consultar Reservas");
		bConsReservas.setAlignmentX(0.3f);
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/lupa.png");
		icono = new ImageIcon(url_icono);
		bConsReservas.setIcon(icono);
		pDerMedio.add(bConsReservas);
		pDer.add(Box.createRigidArea(dimension180_30));
	}
	
	private void botonEliminar(Dimension dimension180_30){
		bEliminar = new JButton("   Eliminar usuario   ");
		bEliminar.setAlignmentX(0.3f);
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/cubo.png");
		icono = new ImageIcon(url_icono);
		bEliminar.setIcon(icono);
		pDerAbajo.add(bEliminar);
		pDer.add(Box.createRigidArea(dimension180_30));
	}
	
	private void botonFiltro(){
		bFiltro = new JButton("Filtrar");
		bFiltro.setAlignmentX(0.3f);
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/lupa.png");
		icono = new ImageIcon(url_icono);
		bFiltro.setIcon(icono);
		pFiltro.add(bFiltro);
		pIzq.add(Box.createRigidArea(new Dimension(180,30)));
	}
	
	private void campoFiltro(){
		cFiltro = new JTextField();
		pFiltro.add(cFiltro);
	}
	
	private void configurarEventos(){
		ponerEscuchaBotonMostrar();
		ponerEscuchaBotonEliminar();
		ponerEscuchaBotonCancelar();
		ponerEscuchaBotonConsulReservas();
		ponerEscuchaBotonConsulAlquileres();
		ponerEscuchaLista();
		ponerEscuchaBotonFiltro();
	}
	
	
	private void ponerEscuchaLista(){
		listaNicks.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				bMostrar.setEnabled(true);
				bConsReservas.setEnabled(true);
				bConsAlquileres.setEnabled(true);
				bEliminar.setEnabled(true);
			}
		});
	}
	
	private void ponerEscuchaBotonConsulReservas(){
		bConsReservas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String e = (String) listaNicks.getSelectedValue();
				GUIConsultarReservas consReservas = new GUIConsultarReservas(controlAlq, controlRes, controlVeh, ventanaPrincipal, e);
				meteAlaPila();
				remove();
				ventanaPrincipal.add(consReservas);
				revalidate();
				ventanaPrincipal.pack();
				ventanaPrincipal.setLocationRelativeTo(null);
			}
        });
	}
	
	private void ponerEscuchaBotonConsulAlquileres(){
		bConsAlquileres.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String e = (String) listaNicks.getSelectedValue();
				GUIConsultarAlquileres consAlquileres = new GUIConsultarAlquileres(controlAlq, controlVeh, ventanaPrincipal, e);
				meteAlaPila();
				remove();
				ventanaPrincipal.add(consAlquileres);
				revalidate();
				ventanaPrincipal.pack();
				ventanaPrincipal.setLocationRelativeTo(null);			
			}
        });
	}
	
	private void ponerEscuchaBotonMostrar(){
		bMostrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String e = (String) listaNicks.getSelectedValue();
				GUIMostrarUsuario muestraUsuario = new GUIMostrarUsuario(e,controlUsu,ventanaPrincipal);
				meteAlaPila();
				remove();
				ventanaPrincipal.add(muestraUsuario);
				revalidate();
				ventanaPrincipal.pack();
				ventanaPrincipal.setLocationRelativeTo(null);
			}
        });
	}
	
		
	private void ponerEscuchaBotonEliminar(){
		bEliminar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String nick = null;
				nick = (String) listaNicks.getSelectedValue();
				if(nick != null){
					int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar al usuario?." + "\n", "Confirmación", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION){
						if(controlUsu.bajaUsuario(nick))
							JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado", "Correcto", JOptionPane.CLOSED_OPTION);
					}
				}
				
				listaNicks = controlUsu.consultarUsuarios();
				listaUsu.setViewportView(listaNicks);	
						
			}
        });
	}
	
	private void ponerEscuchaBotonCancelar() {
		bCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				remove();
			    ventanaPrincipal.add(ventanaPrincipal.sacaPila());
				revalidate();
				ventanaPrincipal.pack();
				ventanaPrincipal.setLocationRelativeTo(null);				
			}
        });
		
	}
	
	private void ponerEscuchaBotonFiltro() {
		bFiltro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String filtro = cFiltro.getText();
				listaNicks = controlUsu.consultarUsuariosFiltro(filtro);
				listaUsu.setViewportView(listaNicks);
			}
        });
		
	}
	
	private void remove(){
		this.ventanaPrincipal.remove(this);
	}
	
	private void meteAlaPila(){
		VistaGui.metePila(this);
	}
}
