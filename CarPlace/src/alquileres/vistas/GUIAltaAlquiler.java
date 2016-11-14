package alquileres.vistas;

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
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import reservas.control.ControlReserva;
import reservas.vistas.GUIConsultarReservas;
import transfers.TAlquiler;
import transfers.TReserva;
import transfers.TVehiculo;
import vehiculos.control.ControlVehiculo;
import vehiculos.vista.GUIMostrarVehiculo;
import CarPlace.VistaGui;
import alquileres.control.ControlAlquiler;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import enumerados.Carburantes;
import enumerados.Marcas;
import enumerados.NumPlazas;
import enumerados.TipoVehiculo;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para dar de alta un alquiler.
 * @author CARPLACE
 *
 */
@SuppressWarnings("serial")
public class GUIAltaAlquiler extends JPanel {
	
	private JLabel inicio;
	private JLabel fin;
	private JLabel imagen;
	private JLabel lblPrecio;
	private JLabel metPago;
	private JTextField txtPrecio;


	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	
	private JComboBox tipoDeVehiculo;
	private JComboBox filtro;
	private JComboBox clase;
	private JComboBox<Marcas> marca;

	private JButton mostrarVehiculo;
	private JButton buscarVehiculo;
	private JButton alquilar;
	private JButton cancelar;
	private JRadioButton opcEfectivo;
	private JRadioButton opcTarjeta;
	private ButtonGroup grupoBtns;

	private JPanel panelDcho;
	private JPanel panelIzdo;
	private JPanel panelInfer;
	private JPanel panelPago;

	private DefaultListModel<TVehiculo> modListaPanel;
	private JList lista_vehiculos;
		
	private TReserva tReserva;
	
	private String nick;
	private ControlVehiculo cVeh;
	private ControlAlquiler cAlq;
	private ControlReserva cRes;
	private VistaGui ventana;
	private int idRes;

	/**
	 * Crea una instancia de esta clase.
	 * @param cAlq ControladorAlquileres
	 * @param cVeh ControladorVehiculos
	 * @param cRes ControladorReservas
	 * @param ventana VentanaPrincipal
	 * @param nick identificador de usuario
	 * @param idRes identificador de reserva, -1 si no hay reserva
	 */
	public GUIAltaAlquiler(ControlAlquiler cAlq, ControlVehiculo cVeh,ControlReserva cRes,  VistaGui ventana, String nick,int idRes){ 
		this.ventana = ventana;
		this.nick = nick;
		this.cAlq = cAlq;
		this.cVeh = cVeh;
		this.cRes = cRes;
		this.idRes = idRes;
		
		if(this.idRes != -1)
			this.tReserva = cRes.mostrarReserva(this.idRes);
		
		this.initialize();
		this.configuracionEventos();
	}

	private void initialize() {
		this.setLayout(new GridBagLayout());
		//PANEL DERECHO
		this.panelDcho = new JPanel(new GridBagLayout());
		this.panelDcho.setBorder(BorderFactory.createTitledBorder("Alta alquiler"));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5,5,5,5);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(this.panelDcho,constraints);
		
		this.inicio = new JLabel("Fecha de inicio:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(50,10,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;

		this.panelDcho.add(this.inicio,constraints);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String fechaact = dateFormat.format(cal.getTime());
		this.dateChooser_1 = new JDateChooser();
		this.dateChooser_1.setFont(new Font("Dialog", 1, 12));
		this.dateChooser_1.getDateEditor().getUiComponent().setEnabled(false);
		((JTextFieldDateEditor)dateChooser_1.getDateEditor()).setDisabledTextColor(Color.darkGray);
		this.dateChooser_1.setDateFormatString("dd-MM-yyyy");
		try {
			this.dateChooser_1.setMinSelectableDate((dateFormat.parse(fechaact)));
			this.dateChooser_1.setDate(dateFormat.parse(fechaact));
		} catch (ParseException e) {
		}
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(50,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 15;
		this.panelDcho.add(this.dateChooser_1,constraints);
		
		this.fin = new JLabel("Fecha de fin:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panelDcho.add(this.fin,constraints);
		
		this.dateChooser = new JDateChooser();
		this.dateChooser.setFont(new Font("Dialog", 1, 12));
		this.dateChooser.getDateEditor().getUiComponent().setEnabled(false);
		this.dateChooser.setDateFormatString("dd-MM-yyyy");
		cal.setTime(dateChooser_1.getDate());
		this.dateChooser.getDateEditor().getUiComponent().setEnabled(false);
		((JTextFieldDateEditor)dateChooser.getDateEditor()).setDisabledTextColor(Color.darkGray);
		cal.add(Calendar.DATE, 1);
		fechaact = dateFormat.format(cal.getTime());
		try {
			this.dateChooser.setMinSelectableDate((dateFormat.parse(fechaact)));
		} catch (ParseException e) {
		}
		
		this.dateChooser.setDateFormatString("dd-MM-yyyy");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 15;
		this.panelDcho.add(this.dateChooser, constraints);
		
		this.tipoDeVehiculo = new JComboBox(TipoVehiculo.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,10,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.tipoDeVehiculo,constraints);
		
		this.filtro = new JComboBox(Carburantes.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.filtro,constraints);
		
		this.clase = new JComboBox(NumPlazas.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,10,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.clase,constraints);
		
		this.marca = new JComboBox(Marcas.values());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.marca,constraints);
		
		this.buscarVehiculo = new JButton("Buscar vehiculo");
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/magnifying-glass32.png");
		icono = new ImageIcon(url_icono);
		this.buscarVehiculo.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,0,10);
		constraints.gridx = 0; 
		constraints.gridy = 4;
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
		scrollLista.setPreferredSize(new Dimension(300,200));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(50,21,0,0);
		constraints.gridx = 2; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 6;
		constraints.fill = GridBagConstraints.BOTH;
		this.panelDcho.add(scrollLista,constraints); 
		

		
		this.mostrarVehiculo = new JButton("Mostrar automovil");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/menu48.png");
		icono = new ImageIcon(url_icono);
		this.mostrarVehiculo.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,20,10,0);
		constraints.gridx = 2; 
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelDcho.add(this.mostrarVehiculo,constraints);
		
		this.panelPago = new JPanel(new GridBagLayout());
		this.panelPago.setBorder(BorderFactory.createTitledBorder("Información de pago"));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelDcho.add(this.panelPago, constraints);
		
		this.metPago = new JLabel("Método de pago");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;

		this.panelPago.add(this.metPago, constraints);
		
		this.grupoBtns = new ButtonGroup();
		
		
		this.opcEfectivo = new JRadioButton("Efectivo");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.grupoBtns.add(this.opcEfectivo);
		this.panelPago.add(this.opcEfectivo,constraints);
		
		this.opcTarjeta = new JRadioButton("Tarjeta");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.grupoBtns.add(this.opcTarjeta);
		this.panelPago.add(this.opcTarjeta, constraints);
		
		this.grupoBtns.setSelected(this.opcEfectivo.getModel(), true);
		
		this.lblPrecio = new JLabel("Precio");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		this.panelPago.add(this.lblPrecio,constraints);
		
		this.txtPrecio = new JTextField();
		this.txtPrecio.setFont(new Font("Dialog", 1, 12));
		this.txtPrecio.setEditable(false);
		constraints.insets = new Insets(20,0,0,0);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.panelPago.add(this.txtPrecio, constraints);
		
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
		url_icono = getClass().getResource("imagenes/cars25.png");
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
		
		this.alquilar = new JButton("Alquilar");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/shopping102.png");
		icono = new ImageIcon(url_icono);
		this.alquilar.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(15,550,20,20);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		this.panelInfer.add(this.alquilar,constraints);
		
		this.cancelar = new JButton("Cancelar");
		icono = null;
		url_icono = null;
		url_icono = getClass().getResource("imagenes/cancel30.png");
		icono = new ImageIcon(url_icono);
		this.cancelar.setIcon(icono);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(15,0,20,20);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;	
		constraints.anchor = GridBagConstraints.EAST;
		this.panelInfer.add(this.cancelar,constraints);
		
		if(this.idRes != -1){
			try {
				DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				this.dateChooser_1.setDate(dateFormat2.parse(tReserva.getInicio()));
				this.dateChooser.setDate(dateFormat2.parse(this.tReserva.getFin()));
				this.dateChooser.setEnabled(false);
				this.dateChooser_1.setEnabled(false);
				TVehiculo tVehiculo = cVeh.mostrarVehiculo(tReserva.getMatricula());
				modListaPanel.addElement(tVehiculo);
				this.buscarVehiculo.setEnabled(false);
				this.mostrarVehiculo.setEnabled(false);
				this.alquilar.setEnabled(false);
			} catch (ParseException e) {

			}
		}
		else{
			this.mostrarVehiculo.setEnabled(false);
			this.alquilar.setEnabled(false);
		}
		
	}
		
	private void configuracionEventos(){
		this.alquilar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				try{
					String tipoPago;
					if(lista_vehiculos.isSelectionEmpty())
						throw new Exception();
					
					if(opcTarjeta.isSelected()){
						tipoPago = "Tarjeta";
					}
					else{
						tipoPago = "Efectivo";
					}
					
					int i1 = lista_vehiculos.getSelectedIndex();
					TVehiculo tVehiculo = modListaPanel.get(i1);
					String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser_1.getDate());
					String fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
					TAlquiler tAlquiler = new TAlquiler(fechaInicio,fechaFin, 0, tVehiculo.getMatricula(),nick, tipoPago, Float.parseFloat(txtPrecio.getText()));
					
					if(idRes != -1){
						cRes.bajaReserva(idRes);
					}
						
					if(cAlq.altaAlquiler(tAlquiler)){	
						int i = JOptionPane.showConfirmDialog(null, "El alquiler ha sido dado de alta correctamente." + "\n" + "¿Desea continuar?", "Éxito", JOptionPane.YES_NO_OPTION);
						if(i == JOptionPane.NO_OPTION){
							remove();
							if(idRes != -1){
								ventana.sacaPila();
								ventana.add(new GUIConsultarReservas(cAlq, cRes, cVeh, ventana, nick));
							}
							else
								ventana.add(ventana.sacaPila());

							revalidate();
							ventana.pack();
							ventana.setLocationRelativeTo(null);
						}
						else{
							modListaPanel.remove(i1);
							marca.setSelectedIndex(0);
							tipoDeVehiculo.setSelectedItem(0);
							filtro.setSelectedItem(0);
							clase.setSelectedItem(0);
							txtPrecio.setText("");
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
		
		this.mostrarVehiculo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				meteAlaPila();
				remove();
				int i =  lista_vehiculos.getSelectedIndex();
				TVehiculo tVehiculo = modListaPanel.get(i);
				GUIMostrarVehiculo res = new GUIMostrarVehiculo(tVehiculo.getMatricula(), cVeh, ventana);
				ventana.add(res);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
			}
		});

		this.buscarVehiculo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modListaPanel.clear();
				TipoVehiculo tipo = (TipoVehiculo) tipoDeVehiculo.getSelectedItem();
				String tipoS = tipo.getSimbolo();
				
				Carburantes tipoC = (Carburantes) filtro.getSelectedItem();
				String tipoCS = tipoC.getSimbolo();
				
				Marcas tipoM = (Marcas) marca.getSelectedItem();
				String tipoMS = tipoM.getSimbolo();
				
				NumPlazas tipoP = (NumPlazas) clase.getSelectedItem();
				int tipoPS = tipoP.getNumPlazas();
				
				try{
					String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser_1.getDate());
					String fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
					ArrayList<TVehiculo> listaVehiculos = cVeh.consultarVehiculosFechas(fechaInicio, fechaFin, tipoMS, tipoCS,tipoS, tipoPS);
					for(TVehiculo it: listaVehiculos){
						modListaPanel.addElement(it);
					}
				}
				catch(Exception exc){
					JOptionPane.showMessageDialog(null, "Tiene que seleccionar las fechas", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.lista_vehiculos.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(lista_vehiculos.isSelectionEmpty()){
					mostrarVehiculo.setEnabled(false);
					alquilar.setEnabled(false);
				}
				else{
					mostrarVehiculo.setEnabled(true);
					alquilar.setEnabled(true);
					int i =  lista_vehiculos.getSelectedIndex();
					TVehiculo tVehiculo = modListaPanel.getElementAt(i);
					long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
					long dias = (dateChooser.getDate().getTime() - dateChooser_1.getDate().getTime())/MILLSECS_PER_DAY;
					Float precio = (int) dias * tVehiculo.getPrecio();
					txtPrecio.setText(precio.toString());
				}	
			}
		});
		
		this.dateChooser_1.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
	            if ("date".equals(evt.getPropertyName())) {
	        		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        		Calendar cal = Calendar.getInstance();
	        		cal.setTime(dateChooser_1.getDate());
	        		cal.add(Calendar.DATE, 1);
	        		String fechaact = dateFormat.format(cal.getTime());
	        		try {
	        			dateChooser.setMinSelectableDate((dateFormat.parse(fechaact)));
	        		} catch (ParseException e) {
	        		}
	            }				
			}
		});
		
		this.cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(idRes == -1){
				 	remove();
				    ventana.add(VistaGui.sacaPila());
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				}
				else{
					remove();
					VistaGui.sacaPila();
					ventana.add(new GUIConsultarReservas(cAlq, cRes, cVeh, ventana, nick));
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				}
			}
		});
		
		this.dateChooser.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					cal.setTime(dateChooser.getDate());
					cal.add(Calendar.DATE, -1);
					String fechaact = dateFormat.format(cal.getTime());
					try {
						dateChooser_1.setMaxSelectableDate((dateFormat.parse(fechaact)));
					} catch (ParseException e) {

					}
				}
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