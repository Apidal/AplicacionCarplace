package vehiculos.vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import CarPlace.VistaGui;
import transfers.TVehiculo;
import vehiculos.control.ControlVehiculo;
import enumerados.Carburantes;
import enumerados.Marcas;
import enumerados.NumPlazas;
import enumerados.TipoVehiculo;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para poder añadir un vehículo a la BBDD.
 * @author CARPLACE
 *
 */
public class GUIAltaVehiculo extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lMarca;
	private JLabel lModelo;
	private JLabel lMatricula;
	private JLabel lPrecio;
	private JLabel lNumPlazas;
	private JLabel lCarburante;
	private JLabel lCategoria;

	private JComboBox<Marcas> cMarca;
	private JTextField tModelo;
	private JTextField tMatricula;
	private JTextField tPrecio;
	private JComboBox<NumPlazas> cNumPlazas;
	private JComboBox<Carburantes> cCarburante;
	private JComboBox<TipoVehiculo> cCategoria;

	private JButton guardar;
	private JButton cancelar;
	
	private JPanel pIzq;
	private JPanel pDer;
	private JPanel pInf;
	
	private ControlVehiculo control;
	private VistaGui ventana;
	
	/**
	 * Construye una instancia de esta clase.
	 * @param c ControladorVehiculo
	 * @param vp VentanaPrincipal
	 */
	public GUIAltaVehiculo(ControlVehiculo c, VistaGui vp){
		this.control = c;
		this.ventana = vp;
		initGUI();
		confEventos();
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
		this.pIzq = panelIzquierdo();
		this.add(pIzq, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 1; 
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHEAST;
		this.pDer = panelDerecho();
		this.add(pDer, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 0; 
		cons.gridy = 1;
		cons.gridwidth = 2;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.EAST;
		this.pInf = panelInferior();
		this.add(pInf, cons);
		
	}
	
	private JPanel panelDerecho(){
		JPanel pd = new JPanel();
		pd.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
		
		this.lMarca = new JLabel("Marca:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 30, 25, 0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lMarca, constraints);
		
		this.lModelo = new JLabel("Modelo:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 30, 25, 0);
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lModelo, constraints);
		
		this.lMatricula = new JLabel("Matricula:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 30, 25, 0);
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lMatricula, constraints);
		
		this.lPrecio = new JLabel("Precio:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 30, 25, 0);
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lPrecio, constraints);
		
		this.lNumPlazas = new JLabel("Número de plazas:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 30, 25, 0);
		constraints.gridx = 0; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lNumPlazas, constraints);
		
		this.lCarburante = new JLabel("Carburante:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 30, 25, 0);
		constraints.gridx = 0; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lCarburante, constraints);
		
		this.lCategoria = new JLabel("Categoria:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 30, 25, 0);
		constraints.gridx = 0; 
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.lCategoria, constraints);
		
		//-------------------COLUMNA 2--------------------//
			
		this.cMarca = new JComboBox<Marcas>();;
		this.cMarca.addItem(Marcas.Marca);
		this.cMarca.addItem(Marcas.BMW);
		this.cMarca.addItem(Marcas.OPEL);
		this.cMarca.addItem(Marcas.PEUGEOT);
		this.cMarca.addItem(Marcas.RENAULT);
		this.cMarca.addItem(Marcas.SEAT);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 150;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.cMarca, constraints);
		
		this.tModelo = new JTextField();
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tModelo, constraints);
		
		this.tMatricula = new JTextField();
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tMatricula, constraints);
		
		this.tPrecio = new JTextField();
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tPrecio, constraints);
		
		this.cNumPlazas = new JComboBox<NumPlazas>();
		this.cNumPlazas.addItem(NumPlazas.NumPlazas);
		this.cNumPlazas.addItem(NumPlazas.UNO);
		this.cNumPlazas.addItem(NumPlazas.DOS);
		this.cNumPlazas.addItem(NumPlazas.TRES);
		this.cNumPlazas.addItem(NumPlazas.CUATRO);
		this.cNumPlazas.addItem(NumPlazas.CINCO);
		this.cNumPlazas.addItem(NumPlazas.MASCINCO);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.cNumPlazas, constraints);
		
		this.cCarburante = new JComboBox<Carburantes>();
		this.cCarburante.addItem(Carburantes.Carburante);
		this.cCarburante.addItem(Carburantes.DIESEL);
		this.cCarburante.addItem(Carburantes.GASOLINA);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.cCarburante, constraints);
		
		this.cCategoria = new JComboBox<TipoVehiculo>();
		this.cCategoria.addItem(TipoVehiculo.Tipo);
		this.cCategoria.addItem(TipoVehiculo.COCHE);
		this.cCategoria.addItem(TipoVehiculo.MOTO);
		this.cCategoria.addItem(TipoVehiculo.LIMUSINA);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.cCategoria, constraints);
		
		return pd;
	}
	
	private JPanel panelInferior(){
		JPanel pi = new JPanel();
		
		pi.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
			
		this.guardar  = new JButton(" Guardar vehículo ");
		this.guardar.setIcon(new ImageIcon(getClass().getResource("imagenes/disquete.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 250, 5, 5);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.guardar, constraints);
		
		this.cancelar = new JButton("Cancelar operación");
		this.cancelar.setIcon(new ImageIcon(getClass().getResource("imagenes/cruz.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 0);
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
		url_icono = getClass().getResource("imagenes/h.png");
		icono = new ImageIcon(url_icono);
		
		i.setIcon(icono);
		pinf.add(i);
		
		pinf.setBackground(new Color(135,206,250));
		
		return pinf;
	}
	
	private void confEventos(){
		eventoGuardar();
		eventoCancelar();
		
	}
	
	private void eventoGuardar(){
		guardar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean floatMal = false;
				
				Float  p = null;
				try{
				p               = (Float)        Float.parseFloat(tPrecio.getText());
				}
				catch(NumberFormatException n){
					floatMal = true;
					JOptionPane.showMessageDialog(null, "El valor del precio ha de ser un número", "Error...", JOptionPane.INFORMATION_MESSAGE);	
				};
				
				if(!floatMal){
					TVehiculo v = new TVehiculo(((Marcas) cMarca.getSelectedItem()).getSimbolo(),
												(String) tModelo.getText(), 
												(String) tMatricula.getText(), 
												p, 
												((NumPlazas) cNumPlazas.getSelectedItem()).getNumPlazas(), 
												((Carburantes)  cCarburante.getSelectedItem()).getSimbolo(), 
												((TipoVehiculo) cCategoria.getSelectedItem()).getSimbolo());
						
					
					
					if(control.altaVehiculo(v)){
						int i = JOptionPane.showConfirmDialog(null, "El vehículo ha sido dado de alta correctamente." + "\n" + "¿Desea seguir añadiendo vehículos?", "Éxito", JOptionPane.YES_NO_OPTION);
						if(i == JOptionPane.NO_OPTION){
							remove();
							ventana.add(ventana.sacaPila());
							revalidate();
							ventana.pack();
							ventana.setLocationRelativeTo(null);
						}
						else{
							cMarca.setSelectedIndex(0);
							tModelo.setText("");
							tMatricula.setText("");
							tPrecio.setText("");
							cCarburante.setSelectedIndex(0);
							cCategoria.setSelectedIndex(0);
							cNumPlazas.setSelectedIndex(0);
						}
					}
					else
						JOptionPane.showMessageDialog(null, "El vehículo ya existe o hay campos vacios.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
	}
	
	private void eventoCancelar(){
		cancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
				ventana.add(ventana.sacaPila());
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				
			}
			
		});
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
}