package usuarios.vista;

import java.awt.BorderLayout;
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
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import transfers.TUsuario;
import usuarios.control.ControlUsuario;
import CarPlace.VistaGui;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class GUIAltaUsuario extends JPanel{
 
	private JPanel norte, pEtiquetas, pTCampos;
	private JTextField cNombre, cApellidos, cNick, cDni;
	private JPasswordField cPassword, cRepassword;
	private JDateChooser cFecha;
	private JLabel  eNombre, eApellidos, eNick, ePassword, eRepassword, eDni, eFecha;
	private JButton bRegistro, bCancelar;
	private String nombre = null, apellidos = null, nick = null, dni = null, fecha = null, passwordString = null, repasswordString = null;
	private char[] password = null, repassword = null;
	private TUsuario usuario;
	private ControlUsuario control;
	private VistaGui ventana;
	
	/**
	 * Construye una instancia de esta clase.
	 * @param controlador controlador del usuario
	 * @param v ventana principal
	 */
	public GUIAltaUsuario(ControlUsuario controlador, VistaGui v) {
		this.control = controlador;
		this.ventana = v;
		
		configurarPanel();
		configurarEventos();
	}
	
	/**
	 * Configura el panel para dar de alta al usuario y añade los paneles necesarios
	 */
	private void configurarPanel(){
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
	
	
	private void panelNorte(){
		norte = new JPanel();
		norte.setLayout(new BorderLayout());
		
		panelEtiquetas();
		panelCamposTexto();
		
	}
	
	private JPanel panelInferior(){
		JPanel pi = new JPanel();
		
		pi.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
			
		this.bRegistro  = new JButton("Registrarse");
		this.bRegistro.setIcon(new ImageIcon(getClass().getResource("imagenes/disquete.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 250, 5, 5);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.bRegistro, constraints);
		
		this.bCancelar = new JButton("Cancelar");
		this.bCancelar.setIcon(new ImageIcon(getClass().getResource("imagenes/cruz.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 0);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.bCancelar, constraints);
		
		pi.setBackground(new Color(240,128,128));
		
		return pi;
	}

	private JPanel panelIzquierdo(){
		JPanel pinf = new JPanel();
		
		JLabel i = new JLabel();
		
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/a.png");
		icono = new ImageIcon(url_icono);
		
		i.setIcon(icono);
		pinf.add(i);
		
		pinf.setBackground(new Color(240,128,128));
		
		return pinf;
	}
	
	private JPanel panelDerecho(){
		JPanel pd = new JPanel();
		pd.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
		
		panelNorte();
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 0, 0, 20);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pd.add(this.norte, constraints);
		
		return pd;
	}
	
	/**
	 * Crea un panel donde ira añadiendo todas las etiquetas necesarias
	 * para identificar los campos
	 */
	private void panelEtiquetas(){
		Dimension dimension0_15 = new Dimension(0,15);
		configuracionPanelEtiquetas();		
		
		pEtiquetas.add(Box.createRigidArea(new Dimension(0,5)));
		
		eNombre = new JLabel("Nombre:");
		pEtiquetas.add(eNombre);
				
		pEtiquetas.add(Box.createRigidArea(dimension0_15));
		
		eApellidos = new JLabel("Apellidos:");
		pEtiquetas.add(eApellidos);
		
		pEtiquetas.add(Box.createRigidArea(dimension0_15));
		
		eNick = new JLabel("Nick:");
		pEtiquetas.add(eNick);
		
		pEtiquetas.add(Box.createRigidArea(dimension0_15));
		
		ePassword = new JLabel("Password:");
		pEtiquetas.add(ePassword);
		
		pEtiquetas.add(Box.createRigidArea(dimension0_15));
		
		eRepassword = new JLabel("Repetir password:");
		pEtiquetas.add(eRepassword);
		
		pEtiquetas.add(Box.createRigidArea(dimension0_15));
		
		eDni = new JLabel("DNI:");
		pEtiquetas.add(eDni);
		
		pEtiquetas.add(Box.createRigidArea(new Dimension(100, 15)));
		
		eFecha = new JLabel("F. Nacimiento:");
		pEtiquetas.add(eFecha);
	}
	
	private void configuracionPanelEtiquetas(){
		pEtiquetas = new JPanel();
		norte.add(pEtiquetas, BorderLayout.WEST);
		pEtiquetas.setLayout(new BoxLayout(pEtiquetas, BoxLayout.Y_AXIS));
	}
	
	/**
	 * Crea en el panel todos los campos para poder recoger los datos del usuario
	 */
	private void panelCamposTexto(){
		Dimension dimension0_10 = new Dimension(0,10);
		configuracionPanelCampos();
		
		campoNombre();
		
		pTCampos.add(Box.createRigidArea(dimension0_10));
		
		campoApellidos();
		
		pTCampos.add(Box.createRigidArea(dimension0_10));
		
		campoNick();
		
		pTCampos.add(Box.createRigidArea(dimension0_10));
		
		campoPassword();
		
		pTCampos.add(Box.createRigidArea(dimension0_10));
		
		campoRepassword();
		
		pTCampos.add(Box.createRigidArea(dimension0_10));
		
		campoDni();
		
		pTCampos.add(Box.createRigidArea(dimension0_10));
		
		campoFecha();
	}
	
	private void configuracionPanelCampos(){
		pTCampos = new JPanel();
		norte.add(pTCampos, BorderLayout.EAST);
		pTCampos.setLayout(new BoxLayout(pTCampos, BoxLayout.Y_AXIS));
	}
	
	private void campoNombre(){
		cNombre = new JTextField();
		cNombre.setColumns(20);
		pTCampos.add(cNombre);
	}
	
	private void campoApellidos(){
		cApellidos = new JTextField();
		cApellidos.setColumns(20);
		pTCampos.add(cApellidos);
	}
	
	private void campoNick(){
		cNick = new JTextField();
		cNick.setColumns(20);
		pTCampos.add(cNick);
	}
	
	private void campoPassword(){
		cPassword = new JPasswordField();
		cPassword.setColumns(20);
		pTCampos.add(cPassword);
	}
	
	private void campoRepassword(){
		cRepassword = new JPasswordField();
		cRepassword.setColumns(20);
		pTCampos.add(cRepassword);
	}
	
	private void campoDni(){
		cDni = new JTextField();
		cDni.setColumns(20);
		pTCampos.add(cDni);
	}
	
	private void campoFecha(){
		cFecha = new JDateChooser();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String fechaact = dateFormat.format(cal.getTime());
		this.cFecha.getDateEditor().getUiComponent().setEnabled(false);
		((JTextFieldDateEditor)cFecha.getDateEditor()).setDisabledTextColor(Color.darkGray);
		this.cFecha.setDateFormatString("dd-MM-yyyy");
		try {
			this.cFecha.setMaxSelectableDate((dateFormat.parse(fechaact)));
		} catch (ParseException e) {
		}
		pTCampos.add(cFecha);
	}
	
	/**
	 * Hace la "captura" de los datos que el usuario ha introducido en los campos
	 */
	private void recogidaDatosUsu(){
		nombre = cNombre.getText();
		apellidos = cApellidos.getText();
		nick = cNick.getText();
		password = cPassword.getPassword();
		passwordString = toString(password);
		repassword = cRepassword.getPassword();
		repasswordString = toString(repassword);
		dni = cDni.getText();
		try{
		fecha = new SimpleDateFormat("yyyy-MM-dd").format(cFecha.getDate());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
        
	}
	
	/**
	 * Recibe la password en un array de char y lo devuelve en un String
	 * @param palabra
	 * @return password 
	 */
	private String toString(char[] palabra){
		String password = "";
		for(int i = 0; i < palabra.length;i++)
			password += palabra[i];
		return password;
	}
	
	private void configurarEventos(){
		ponerEscuchaBotonRegistro();
		ponerEscuchaBotonCancelar();
	}
	
	/**
	 * Pone oyente al boton de resgistro, creando un transfer de usuario con los
	 * datos introducidos y llama a altaUsuario
	 */
	private void ponerEscuchaBotonRegistro(){
		bRegistro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				recogidaDatosUsu();
				usuario = new TUsuario(nick, nombre, apellidos, passwordString, repasswordString, dni, fecha, false, false);
				
				if(control.altaUsuario(usuario)){
					JOptionPane.showMessageDialog(null, "El usuario ha sido registrado", "Correcto", JOptionPane.CLOSED_OPTION);
					volverVentanaAnterior();
				}
				else
					JOptionPane.showMessageDialog(null, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
        });
	}
	
	/**
	 * Pone oyente al boton de cancelar, para que si le pulsa vuelva al panel anterior
	 */
	private void ponerEscuchaBotonCancelar(){
		bCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				volverVentanaAnterior();				
			}
        });
		
	}
	
	private void remove(){
		this.ventana.remove(this);
	}

	private void volverVentanaAnterior(){
		remove();
	    ventana.add(ventana.sacaPila());
		revalidate();
		ventana.pack();
		ventana.setLocationRelativeTo(null);
	}
}
