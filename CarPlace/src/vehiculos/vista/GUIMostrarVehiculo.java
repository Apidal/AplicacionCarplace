package vehiculos.vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale.Category;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enumerados.Carburantes;
import enumerados.Marcas;
import enumerados.NumPlazas;
import enumerados.TipoVehiculo;
import CarPlace.VistaGui;
import transfers.TVehiculo;
import vehiculos.control.ControlVehiculo;

/**
 * Esta clase crea la interfaz grafica de usuario
 * para mostrar un vehiculo al usuario
 * @author CARPLACE
 *
 */
public class GUIMostrarVehiculo extends JPanel{
	/**
	 * 
	 */
	private ControlVehiculo control;
	private VistaGui ventana;
	
	private JLabel lMarca;
	private JLabel lModelo;
	private JLabel lMatricula;
	private JLabel lPrecio;
	private JLabel lNumPlazas;
	private JLabel lCarburante;
	private JLabel lCategoria;
	
	private JComboBox<Marcas> cMarca;
	private JComboBox<NumPlazas> cNumPlazas;
	private JComboBox<Carburantes> cCarburante;
	private JComboBox<TipoVehiculo> cCategoria;
	
	private JTextField tMarca;
	private JTextField tModelo;
	private JTextField tMatricula;
	private JTextField tPrecio;
	private JTextField tNumPlazas;
	private JTextField tCarburante;
	private JTextField tCategoria;
	
	private JLabel imagenIzquierda;
	
	private JButton volver;
	private JButton modificar;
	private JButton guardar;
	private TVehiculo tVehiculo;
	
	private boolean modificarAccionado;
	
	
	/**
	 * Construye una instancia de esta clase
	 * @param matricula identificador del vehiculo
	 * @param c	ControladorVehiculos
	 * @param vp	VentanaPrincipal
	 */
	public GUIMostrarVehiculo(String matricula, ControlVehiculo c, VistaGui vp){
		this.control = c;
		this.tVehiculo = this.control.mostrarVehiculo(matricula);
		this.ventana = vp;
		this.modificarAccionado = false;
		this.initGUI();
		this.confEventos();
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
		
		this.lMarca = new JLabel("Marca:");
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(25, 30, 25, 0);
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
			
		this.cMarca = new JComboBox<Marcas>();
		this.cMarca.addItem(Marcas.Marca);
		this.cMarca.addItem(Marcas.BMW);
		this.cMarca.addItem(Marcas.OPEL);
		this.cMarca.addItem(Marcas.PEUGEOT);
		this.cMarca.addItem(Marcas.RENAULT);
		this.cMarca.addItem(Marcas.SEAT);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(25, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 150;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.cMarca, constraints);
		this.cMarca.setVisible(false);
		
		this.tMarca = new JTextField(this.tVehiculo.getMarca());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(25, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 150;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tMarca, constraints);
		this.tMarca.setEditable(false);
		
		//---
		
		this.tModelo = new JTextField(this.tVehiculo.getModelo());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tModelo, constraints);
		this.tModelo.setEditable(false);
		
		//---
		
		this.tMatricula = new JTextField(this.tVehiculo.getMatricula());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tMatricula, constraints);
		this.tMatricula.setEditable(false);
		
		//---
		
		this.tPrecio = new JTextField(this.tVehiculo.getPrecio().toString());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tPrecio, constraints);
		this.tPrecio.setEditable(false);
		
		//---
		
		this.cNumPlazas = new JComboBox<NumPlazas>();
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
		this.cNumPlazas.setVisible(false);
		
		//---
		String t = "";
		if(this.tVehiculo.getNumplazas() == 6)
			t = "Más de cinco";
		else
			t = Integer.valueOf(this.tVehiculo.getNumplazas()).toString();
		
		this.tNumPlazas = new JTextField(t);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tNumPlazas, constraints);
		this.tNumPlazas.setEditable(false);
		
		//---
		
		this.cCarburante = new JComboBox<Carburantes>();
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
		this.cCarburante.setVisible(false);
		
		this.tCarburante = new JTextField(this.tVehiculo.getCarburante());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tCarburante, constraints);
		this.tCarburante.setEditable(false);
		
		//---
		
		this.cCategoria = new JComboBox<TipoVehiculo>();
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
		this.cCategoria.setVisible(false);
		
		this.tCategoria = new JTextField(this.tVehiculo.getCategoria());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 15, 25, 30);
		constraints.gridx = 1; 
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill =GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		pd.add(this.tCategoria, constraints);
		this.tCategoria.setEditable(false);
	
		
		return pd;
	}
	
	private JPanel panelInferior(){
		JPanel pi = new JPanel();
		pi.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
			
		this.volver  = new JButton("Volver");
		this.volver.setIcon(new ImageIcon(getClass().getResource("imagenes/atras.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 280, 10, 15);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.volver, constraints);
		
		this.modificar  = new JButton("Modificar");
		this.modificar.setIcon(new ImageIcon(getClass().getResource("imagenes/ajustes.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 15, 10, 15);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.modificar, constraints);
		
		this.guardar  = new JButton("Guardar");
		this.guardar.setIcon(new ImageIcon(getClass().getResource("imagenes/disquete.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 15, 10, 9);
		constraints.gridx = 2; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.guardar, constraints);
		this.guardar.setEnabled(false);
		
		pi.setBackground(new Color(135,206,250));
		
		return pi;
	}

	private JPanel panelIzquierdo(){
		JPanel pinf = new JPanel();
		
		this.imagenIzquierda = new JLabel();
		
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/m.png");
		icono = new ImageIcon(url_icono);
		
		this.imagenIzquierda.setIcon(icono);
		pinf.add(this.imagenIzquierda);
		
		pinf.setBackground(new Color(135,206,250));
		
		return pinf;
	}
	
	private void confEventos(){
		this.eventoSalir();
		this.eventoModificar();
		this.eventoGuardar();
	}
	
	private void eventoSalir(){
		this.volver.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			    int i = -5;
				
				if(modificarAccionado){
			    	i = JOptionPane.showConfirmDialog(null, "Las modificaciones no han sido guardadas todavía." + "\n" + "¿Desea salir sin guardar?", "¿?", JOptionPane.YES_NO_OPTION);
			    }

			    if(!modificarAccionado || (i != -5 && i == JOptionPane.YES_OPTION)){
					remove();
				    ventana.add(ventana.sacaPila());
				    ventana.
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
			    }
			}
			
		});
	}
	
	private void eventoModificar(){
		this.modificar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				guardar.setEnabled(true);
				modificar.setEnabled(false);
				
				tMarca.setVisible(false);
				tModelo.setEditable(true);
				tPrecio.setEditable(true);
				tNumPlazas.setVisible(false);
				tCarburante.setVisible(false);
				tCategoria.setVisible(false);
				
				cMarca.setVisible(true);
				cNumPlazas.setVisible(true);
				cCarburante.setVisible(true);
				cCategoria.setVisible(true);
				
				modificarAccionado = true;
				
				cMarca.setSelectedItem(Marcas.Marca.aEnum(tVehiculo.getMarca()));
				cCarburante.setSelectedItem(Carburantes.Carburante.aEnum(tVehiculo.getCarburante()));
				cNumPlazas.setSelectedItem(NumPlazas.NumPlazas.stringAEnum(Integer.toString(tVehiculo.getNumplazas())));
				cCategoria.setSelectedItem(TipoVehiculo.Tipo.aEnum(tVehiculo.getCategoria()));
				
				ImageIcon icono = null;
				java.net.URL url_icono = null;
				url_icono = getClass().getResource("imagenes/mod.png");
				icono = new ImageIcon(url_icono);
				imagenIzquierda.setIcon(icono);
				
				ventana.pack();
			}
			
		});
	}
	
	private void eventoGuardar(){
		this.guardar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				float p = 0;
				boolean floatMal = false;
				
				try{
					p = (Float) Float.parseFloat(tPrecio.getText());
					}
					catch(NumberFormatException n){
						floatMal = true;
						JOptionPane.showMessageDialog(null, "El valor del precio ha de ser un número", "Error...", JOptionPane.WARNING_MESSAGE);
					};
				
				if(!floatMal){
					TVehiculo v = new TVehiculo(
							((Marcas) cMarca.getSelectedItem()).getSimbolo(),
							(String) tModelo.getText(), 
							(String) tMatricula.getText(), 
							p, 
							((NumPlazas) cNumPlazas.getSelectedItem()).getNumPlazas(), 
							((Carburantes)  cCarburante.getSelectedItem()).getSimbolo(), 
							((TipoVehiculo) cCategoria.getSelectedItem()).getSimbolo());
					
					if(control.modificarVehiculo(v)){
						JOptionPane.showMessageDialog(null, "El vehículo ha sido modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						remove();
						ventana.add(ventana.sacaPila());
						revalidate();
						ventana.pack();
						ventana.setLocationRelativeTo(null);
					}
					else
						JOptionPane.showMessageDialog(null, "Imposible guardar. Rellene todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
}
