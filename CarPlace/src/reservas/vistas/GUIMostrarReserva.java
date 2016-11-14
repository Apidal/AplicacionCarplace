package reservas.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import reservas.control.ControlReserva;
import transfers.TReserva;
import transfers.TVehiculo;
import vehiculos.control.ControlVehiculo;
import vehiculos.vista.GUIMostrarVehiculo;
import CarPlace.VistaGui;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import enumerados.Carburantes;
import enumerados.Marcas;
import enumerados.NumPlazas;
import enumerados.TipoVehiculo;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para modificar una reserva.
 * @author CARPLACE
 *
 */
@SuppressWarnings("serial")
public class GUIMostrarReserva extends JPanel {

	private JTextField usuario;
	private JTextField vehiculo;
	
	private JDateChooser inicio;
	private JDateChooser fin;
	private JLabel lblUsuario;
	private JLabel lblVehiculo;
	private JLabel lblInicio; 
	private JLabel lblFin;
	private JLabel imagen;

	private JComboBox tipoVehiculo;
	private JComboBox filtro;
	private JComboBox clase;
	private JComboBox marca;
	
	private JButton btnVolver;
	private JButton modificar;
	private JButton buscarVehiculo;
	private JButton mostrarVehiculo;
	
	private JList lista_vehiculos;
	private DefaultListModel<TVehiculo> modListaPanel;


	private JPanel panelIzdo;
	private JPanel panelDcho;
	private JPanel panelInfer;
	
	private ControlReserva cReserva;
	private ControlVehiculo cVehiculo;
	private VistaGui ventana;
	private TReserva res;
	private JButton btnVerVehiculo;
	
	/**
	 * Construye una instancia de esta clase
	 * @param cReserva ControladorReservas
	 * @param cVehiculo ControladorVehiculos
	 * @param idRes identificador de la reserva a modificar
	 * @param ventana Ventana principal
	 */
	public GUIMostrarReserva(ControlReserva cReserva,ControlVehiculo cVehiculo,int idRes,VistaGui ventana){
		this.cReserva = cReserva;
		this.cVehiculo = cVehiculo;
		this.res = this.cReserva.mostrarReserva(idRes);
		this.ventana = ventana;
		this.inicializar();
		this.confEventos();
	}

	
	
	private void inicializar(){
		setLayout(new GridBagLayout());
		
		//PANEL DERECHO
		this.panelDcho = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		this.panelDcho.setBorder(BorderFactory.createTitledBorder("Mostrar reserva"));
		constraints.insets = new Insets(5,5,5,5);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(this.panelDcho,constraints);
		
		this.lblUsuario = new JLabel("Usuario:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.lblUsuario,constraints);
		
		this.usuario = new JTextField(res.getNick());
		this.usuario.setEditable(false);
		this.usuario.setFont(new Font("Dialog", 1, 12));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,20);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
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
		
		TVehiculo tVehiculo = this.cVehiculo.mostrarVehiculo(this.res.getMatricula());
		this.vehiculo = new JTextField(tVehiculo.getMarca() + " " + tVehiculo.getModelo());
		this.vehiculo.setEditable(false);
		this.vehiculo.setFont(new Font("Dialog", 1, 12));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.vehiculo,constraints);
		
		this.btnVerVehiculo =  new JButton("Ver vehículo");
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/binoculars11.png");
		icono = new ImageIcon(url_icono);
		this.btnVerVehiculo.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 2; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.btnVerVehiculo, constraints);
		
		this.lblInicio = new JLabel("Fecha de inicio:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(30,10,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;

		this.panelDcho.add(this.lblInicio,constraints);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String fechaact = dateFormat.format(cal.getTime());
		this.inicio = new JDateChooser();
		this.inicio.setFont(new Font("Dialog", 1, 12));
		this.inicio.getDateEditor().getUiComponent().setEnabled(false);
		this.inicio.setEnabled(false);
		((JTextFieldDateEditor)inicio.getDateEditor()).setDisabledTextColor(Color.darkGray);
		this.inicio.setDateFormatString("dd-MM-yyyy");
		try {
			this.inicio.setMinSelectableDate((dateFormat.parse(fechaact)));
			this.inicio.setDate(dateFormat.parse(res.getInicio()));
		} catch (ParseException e) {
		}
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(30,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 15;
		this.panelDcho.add(this.inicio,constraints);
		
		this.lblFin = new JLabel("Fecha de fin:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.lblFin,constraints);
		
		this.fin = new JDateChooser();
		this.fin.setFont(new Font("Dialog", 1, 12));
		this.fin.getDateEditor().getUiComponent().setEnabled(false);
		this.fin.setDateFormatString("dd-MM-yyyy");
		this.fin.setEnabled(false);
		cal.setTime(inicio.getDate());
		((JTextFieldDateEditor)fin.getDateEditor()).setDisabledTextColor(Color.darkGray);
		cal.add(Calendar.DATE, 1);
		fechaact = dateFormat.format(cal.getTime());
		try {
			this.fin.setMinSelectableDate((dateFormat.parse(fechaact)));
			this.fin.setDate(dateFormat.parse(res.getFin()));
		} catch (ParseException e) {
			
		}
		
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 15;
		this.panelDcho.add(this.fin, constraints);
		
		this.tipoVehiculo = new JComboBox(TipoVehiculo.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,10,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.tipoVehiculo,constraints);
		
		this.filtro = new JComboBox(Carburantes.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.filtro,constraints);
		
		this.clase = new JComboBox(NumPlazas.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,10,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.clase,constraints);
		
		this.marca = new JComboBox(Marcas.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.marca,constraints);
		
		this.buscarVehiculo = new JButton("Buscar vehiculo");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/magnifying-glass32.png");
		icono = new ImageIcon(url_icono);
		this.buscarVehiculo.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,10,10);
		constraints.gridx = 0; 
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.buscarVehiculo,constraints);
		
		this.modListaPanel = new DefaultListModel();
		this.lista_vehiculos = new JList(this.modListaPanel);	
		JScrollPane scrollLista = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista.setViewportView(this.lista_vehiculos);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(30,21,0,20);
		constraints.gridx = 2; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.fill = GridBagConstraints.BOTH;
		this.panelDcho.add(scrollLista,constraints);
		
		scrollLista.setPreferredSize(new Dimension(200, 200));
	
		this.mostrarVehiculo = new JButton("Mostrar automovil");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/menu48.png");
		icono = new ImageIcon(url_icono);
		this.mostrarVehiculo.setIcon(icono);
		this.mostrarVehiculo.setEnabled(false);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,10,20);
		constraints.gridx = 2; 
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.mostrarVehiculo,constraints);
		
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
		url_icono = getClass().getResource("imagenes/edit32.png");
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
		
		this.modificar = new JButton("Modificar");
		this.modificar.setEnabled(false);
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/diskette18.png");
		icono = new ImageIcon(url_icono);
		this.modificar.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(15,456,20,20);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		this.panelInfer.add(this.modificar,constraints);
		
		this.btnVolver = new JButton("Cancelar");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/cancel30.png");
		icono = new ImageIcon(url_icono);
		this.btnVolver.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(15,0,20,20);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;	
		constraints.anchor = GridBagConstraints.EAST;
		this.panelInfer.add(this.btnVolver,constraints);
	}
	
	private void confEventos() {
		this.modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				
				try{
					if(lista_vehiculos.isSelectionEmpty())
						throw new Exception();
					
					int i1 = lista_vehiculos.getSelectedIndex();
					TVehiculo tVehiculo = modListaPanel.get(i1);
					String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(inicio.getDate());
					String fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(fin.getDate());
					res.setInicio(fechaInicio);
					res.setFin(fechaFin);
					res.setMatricula(tVehiculo.getMatricula());
						
					if(cReserva.modificarReserva(res)){	
						int i = JOptionPane.showConfirmDialog(null, "La reserva ha sido modificada correctamente." + "\n" + "¿Desea modificar otra vez?", "Éxito", JOptionPane.YES_NO_OPTION);
						if(i == JOptionPane.NO_OPTION){
							remove();
							revalidate();
							ventana.pack();
							ventana.setLocationRelativeTo(null);
						}
						else{
							modListaPanel.remove(i1);
							marca.setSelectedIndex(0);
							tipoVehiculo.setSelectedItem(0);
							filtro.setSelectedItem(0);
							clase.setSelectedItem(0);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Fechas mal introducidas", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un vehículo", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
				
		});
		
		this.btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 	remove();
				    ventana.add(VistaGui.sacaPila());
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				
			}
		});
		
		this.buscarVehiculo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modListaPanel.clear();
				lista_vehiculos.revalidate();
				TipoVehiculo tipo = (TipoVehiculo) tipoVehiculo.getSelectedItem();
				String tipoS = tipo.getSimbolo();
				
				Carburantes tipoC = (Carburantes) filtro.getSelectedItem();
				String tipoCS = tipoC.getSimbolo();
				
				
				Marcas tipoM = (Marcas) marca.getSelectedItem();
				String tipoMS = tipoM.getSimbolo();
				
				NumPlazas tipoP = (NumPlazas) clase.getSelectedItem();
				int tipoPS = tipoP.getNumPlazas();
				
				String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(inicio.getDate());
				String fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(fin.getDate());
				
				ArrayList<TVehiculo> listaVeh = cVehiculo.consultarVehiculosFechas(fechaInicio, fechaFin, tipoMS, tipoCS,tipoS, tipoPS);

				
				for(TVehiculo it: listaVeh){
					modListaPanel.addElement(it);
				}	
			}
		});
		
		this.mostrarVehiculo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				meteAlaPila();
				remove();
				int i = lista_vehiculos.getSelectedIndex();
				TVehiculo tVehiculo = modListaPanel.get(i);
				GUIMostrarVehiculo res = new GUIMostrarVehiculo(tVehiculo.getMatricula(),cVehiculo, ventana);
				ventana.add(res);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				
			}
		});
		
		this.lista_vehiculos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(lista_vehiculos.isSelectionEmpty()){
					mostrarVehiculo.setEnabled(false);
					modificar.setEnabled(false);
				}
				else{
					mostrarVehiculo.setEnabled(true);
					modificar.setEnabled(true);
				}	
			}
		});
		
		this.inicio.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
	            if ("date".equals(evt.getPropertyName())) {
	        		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        		Calendar cal = Calendar.getInstance();
	        		cal.setTime(inicio.getDate());
	        		cal.add(Calendar.DATE, 1);
	        		String fechaact = dateFormat.format(cal.getTime());
	        		try {
	        			fin.setMinSelectableDate((dateFormat.parse(fechaact)));
	        		} catch (ParseException e) {
	        			
	        		}
	            }
			}
		});
		
		this.btnVerVehiculo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			    remove();			    
				meteAlaPila();
				GUIMostrarVehiculo pnel = new GUIMostrarVehiculo(res.getMatricula(), cVehiculo, ventana);
				ventana.add(pnel);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
			}
				
		});
	}
	
	private void remove(){
		ventana.remove(this);
	}
	
	private void meteAlaPila(){
		VistaGui.metePila(this);
	}
}