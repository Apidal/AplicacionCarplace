package vehiculos.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import CarPlace.VistaGui;
import transfers.TVehiculo;
import vehiculos.control.ControlVehiculo;
import enumerados.Carburantes;
import enumerados.Marcas;
import enumerados.NumPlazas;
import enumerados.TipoVehiculo;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para consultar todos los vehiculos que contiene la BBDD.
 * @author CARPLACE
 *
 */
public class GUIConsultarVehiculos extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControlVehiculo control;
	private VistaGui ventanaPrincipal;
	
	private JScrollPane lista;
	private JList<TVehiculo> lista_vehiculos;
	private DefaultListModel modListaPanel;
	
	private JLabel lMarca;
	private JLabel lTipoVehiculo;
	private JLabel lNumPlazas;
	private JLabel lCarburante;
	
	private JComboBox<Marcas> cMarca;
	private JComboBox<TipoVehiculo> cCategoria;
	private JComboBox<NumPlazas> cNumPlazas;
	private JComboBox<Carburantes> cCarburante;
	
	private JButton mostrar;
	private JButton eliminar;
	
	private JButton consultar;
	private JButton cancelar;
	private ArrayList<TVehiculo> listaVehiculos;
	
	private boolean consultarAccionado;
	
	/**
	 * Construye una instancia de esta clase
	 * @param c ControladorVehiculos
	 * @param vp VentanaPrincipal
	 */
	
	public GUIConsultarVehiculos(ControlVehiculo c, VistaGui vp){
		this.control = c;
		this.ventanaPrincipal = vp;
		this.listaVehiculos = new ArrayList<TVehiculo>();
		initGUI();
		confEventos();
		this.modListaPanel.addElement("Pulse el botón consultar para");
		this.modListaPanel.addElement("obtener la lista de vehículos");
		this.consultarAccionado = false;
	}
	
	private void initGUI(){
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
		this.add(panelIzquierdo(), cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 1; 
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHEAST;
		this.add(panelDerecho(), cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 0; 
		cons.gridy = 1;
		cons.gridwidth = 3;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.EAST;
		this.add(panelInferior(), cons);
		
	}
	
	private JPanel panelDerecho(){
		JPanel pd = new JPanel();
		pd.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
		
		//----------------COLUMNA 0------------------//
		
		this.modListaPanel = new DefaultListModel();
		this.lista_vehiculos = new JList<TVehiculo>(this.modListaPanel);
		this.lista = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.lista.setPreferredSize(new Dimension(220, 200));
		this.lista.setViewportView(this.lista_vehiculos);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 20);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 8;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		pd.add(this.lista, constraints);
		this.lista.setEnabled(false);
		
		//----------------COLUMNA 1------------------//
		
		this.lMarca  = new JLabel("Marca:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 0, 30, 0);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lMarca, constraints);
		
		this.lTipoVehiculo  = new JLabel("Tipo de vehículo:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 30, 0);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lTipoVehiculo, constraints);
		
		this.lCarburante  = new JLabel("Carburante:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 30, 0);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lCarburante, constraints);
		
		this.lNumPlazas  = new JLabel("Número de plazas:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 30, 0);
		constraints.gridx = 1; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lNumPlazas, constraints);
		
		//----------------COLUMNA 2------------------//
		
		this.cMarca = new JComboBox<Marcas>();;
		this.cMarca.addItem(Marcas.Marca);
		this.cMarca.addItem(Marcas.BMW);
		this.cMarca.addItem(Marcas.OPEL);
		this.cMarca.addItem(Marcas.PEUGEOT);
		this.cMarca.addItem(Marcas.RENAULT);
		this.cMarca.addItem(Marcas.SEAT);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 5, 30, 20);
		constraints.gridx = 2; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 40;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		pd.add(this.cMarca, constraints);
		
		this.cCategoria = new JComboBox<TipoVehiculo>();
		this.cCategoria.addItem(TipoVehiculo.Tipo);
		this.cCategoria.addItem(TipoVehiculo.COCHE);
		this.cCategoria.addItem(TipoVehiculo.MOTO);
		this.cCategoria.addItem(TipoVehiculo.LIMUSINA);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 5, 30, 20);
		constraints.gridx = 2; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 40;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		pd.add(this.cCategoria, constraints);
		
		this.cCarburante = new JComboBox<Carburantes>();
		this.cCarburante.addItem(Carburantes.Carburante);
		this.cCarburante.addItem(Carburantes.DIESEL);
		this.cCarburante.addItem(Carburantes.GASOLINA);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 5, 30, 20);
		constraints.gridx = 2; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 40;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		pd.add(this.cCarburante, constraints);
		
		this.cNumPlazas = new JComboBox<NumPlazas>();
		this.cNumPlazas.addItem(NumPlazas.NumPlazas);
		this.cNumPlazas.addItem(NumPlazas.UNO);
		this.cNumPlazas.addItem(NumPlazas.DOS);
		this.cNumPlazas.addItem(NumPlazas.TRES);
		this.cNumPlazas.addItem(NumPlazas.CUATRO);
		this.cNumPlazas.addItem(NumPlazas.CINCO);
		this.cNumPlazas.addItem(NumPlazas.MASCINCO);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 5, 30, 20);
		constraints.gridx = 2; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 40;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		pd.add(this.cNumPlazas, constraints);
		
		//
		
		this.mostrar  = new JButton("Mostrar vehículo");
		this.mostrar.setEnabled(false);
		this.mostrar.setIcon(new ImageIcon(getClass().getResource("imagenes/menu.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 5, 10, 20);
		constraints.gridx = 2; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		pd.add(this.mostrar, constraints);
		
		this.eliminar  = new JButton("Eliminar vehículo");
		this.eliminar.setIcon(new ImageIcon(getClass().getResource("imagenes/cubo.png")));
		this.eliminar.setEnabled(false);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 5, 10, 20);
		constraints.gridx = 2; 
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		pd.add(this.eliminar, constraints);
		
		
		
		
		
		return pd;
	}
	
	private JPanel panelInferior(){
		JPanel pi = new JPanel();
		
		pi.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
			
		this.consultar  = new JButton("Consultar");
		this.consultar.setIcon(new ImageIcon(getClass().getResource("imagenes/lupa.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 290, 5, 120);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.consultar, constraints);
		
		this.cancelar = new JButton("Volver");
		this.cancelar.setIcon(new ImageIcon(getClass().getResource("imagenes/atras.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 190, 5, 50);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.cancelar, constraints);
		
		pi.setBackground(new Color(135,206,250));
		
		return pi;
	}

	private JPanel panelIzquierdo(){
		JPanel pinf = new JPanel();
		
		JLabel i = new JLabel();
		
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/c.png");
		icono = new ImageIcon(url_icono);
		
		i.setIcon(icono);
		pinf.add(i);
		
		pinf.setBackground(new Color(135,206,250));
		
		return pinf;
	}
	
	private void confEventos(){
		eventoConsultar();
		eventoCancelar();
		eventoEliminar();
		eventoMostrar();
		eventoSeleccionLista();
	}
	
	private void eventoConsultar(){
		this.consultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modListaPanel.clear();
				lista_vehiculos.revalidate();
				
				String tipoS = null, tipoCS = null, tipoMS = null;
				int tipoPS = 0;
				
				TipoVehiculo tipo = (TipoVehiculo) cCategoria.getSelectedItem();
				if(tipo != TipoVehiculo.Tipo){
					tipoS = tipo.getSimbolo();
				}
					
				
				Carburantes tipoC = (Carburantes) cCarburante.getSelectedItem();
				if(tipoC != Carburantes.Carburante)
					tipoCS = tipoC.getSimbolo();
				
				
				Marcas tipoM = (Marcas) cMarca.getSelectedItem();
				if(tipoM != Marcas.Marca)
					tipoMS = tipoM.getSimbolo();
				
				NumPlazas tipoP = (NumPlazas) cNumPlazas.getSelectedItem();
				if(tipoP != NumPlazas.NumPlazas)
					tipoPS = tipoP.getNumPlazas();
				
				listaVehiculos = control.consultarVehiculosFiltros(tipoMS, tipoCS, tipoS, tipoPS);
				
				for(TVehiculo it: listaVehiculos){
					modListaPanel.addElement(it.getMatricula() + "    /    " +  it.getMarca() + "    /    " + it.getModelo());
				}
				consultarAccionado = true;
				
			}
		});
	}
	
	private void eventoCancelar(){
		this.cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
				ventanaPrincipal.add(ventanaPrincipal.sacaPila());
				revalidate();
				ventanaPrincipal.pack();
				ventanaPrincipal.setLocationRelativeTo(null);
			}
		});
	}
	
	
	private void eventoMostrar(){
		this.mostrar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				meteAlaPila();
				remove();
				
				int i = lista_vehiculos.getSelectedIndex();
				TVehiculo tVehiculo = listaVehiculos.get(i);
				GUIMostrarVehiculo res = new GUIMostrarVehiculo(tVehiculo.getMatricula(), control, ventanaPrincipal);
				ventanaPrincipal.add(res);
				revalidate();
				ventanaPrincipal.pack();
				ventanaPrincipal.setLocationRelativeTo(null);
				modListaPanel.clear();
				modListaPanel.addElement("Pulse el botón consultar para");
				modListaPanel.addElement("refrescar la lista de vehículos");
				eliminar.setEnabled(false);
				mostrar.setEnabled(false);
				lista.setEnabled(false);
				consultarAccionado = false;
			}
			
		});
	}
	
	private void eventoSeleccionLista(){
		this.lista_vehiculos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(consultarAccionado){
					eliminar.setEnabled(true);
					mostrar.setEnabled(true);
					lista.setEnabled(true);
				}
			}
		});
	}
	
	private void eventoEliminar(){
		this.eliminar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int i = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el vehículo?" + "\n" + "Esta operación no puede deshacerse", "¿?", JOptionPane.YES_NO_OPTION);
				if(i == JOptionPane.YES_OPTION){
					int j =  lista_vehiculos.getSelectedIndex();
					
					TVehiculo tVehiculo = listaVehiculos.get(j);
					control.bajaVehiculo(tVehiculo.getMatricula());
					listaVehiculos.remove(j);
					modListaPanel.clear();
	
					for(TVehiculo it: listaVehiculos){
						modListaPanel.addElement(it.getMatricula() + "    /    " +  it.getMarca() + "    /    " + it.getModelo());
					}
					eliminar.setEnabled(false);
					mostrar.setEnabled(false);
				}
			}
			
		});
	}
	
	private void remove(){
		this.ventanaPrincipal.remove(this);
	}
	
	private void meteAlaPila(){
		this.ventanaPrincipal.metePila(this);
	}
}
