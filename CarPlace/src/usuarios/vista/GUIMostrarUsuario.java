package usuarios.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import CarPlace.VistaGui;
import transfers.TUsuario;
import usuarios.control.ControlUsuario;

public class GUIMostrarUsuario extends JPanel{
 
	private JPanel norte, sur;
	private JTextField cNombre, cApellidos, cNick, cPass, cDni;
	private JLabel  eNombre, eApellidos, eNick, ePass, eDni, eFecha, eAdmin;
	private JButton bCancelar;
	private JButton bAceptar;
	private JPanel pNick;
	private JPanel pPass;
	private JPanel pNombre;
	private JPanel pApellidos;
	private JPanel pDni;
	private JPanel pFNac;
	private JPanel pAdmin;
	private TUsuario usuario;
	private ControlUsuario c;
	private VistaGui ventana;
	private JRadioButton si;
	private JRadioButton no;
	private ButtonGroup grupoBtns;
	private JDateChooser cFecha;
	
	/**
	 * Construye una instancia de esta clase.
	 * @param usuario nick del usuario
	 * @param c2 controlador del usuario
	 * @param v ventana principal
	 */
	public GUIMostrarUsuario(String usuario, ControlUsuario c2, VistaGui v) {
		this.c = c2;
		this.ventana = v;
		this.usuario = c.mostrarUsuario(usuario);
		
		configurarPanel();		
		configurarEventos();
	}
	
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
	
	private JPanel panelInferior(){
		JPanel pi = new JPanel();
		
		pi.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
			
		this.bAceptar  = new JButton("Guardar");
		this.bAceptar.setIcon(new ImageIcon(getClass().getResource("imagenes/disquete.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 250, 5, 5);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.bAceptar, constraints);
		
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
		url_icono = getClass().getResource("imagenes/mod.png");
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
	
	private void panelNorte(){
		norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
		Dimension dimension0_15 = new Dimension(0, 12);
		panelNick();
		norte.add(Box.createRigidArea(dimension0_15));
		panelPass();
		norte.add(Box.createRigidArea(dimension0_15));
		panelNombre();
		norte.add(Box.createRigidArea(dimension0_15));
		panelApellidos();
		norte.add(Box.createRigidArea(dimension0_15));
		panelDni();
		norte.add(Box.createRigidArea(dimension0_15));
		panelFecha();		
		if(ventana.usuLog.isAdmin()){
			norte.add(Box.createRigidArea(dimension0_15));
			panelAdmin();
		}
		norte.add(Box.createRigidArea(dimension0_15));
		
		norte.add(Box.createRigidArea(dimension0_15));
	}
	
	private void panelAdmin() {
		pAdmin = new JPanel();
		
		norte.add(pAdmin);
		pAdmin.setLayout(new BoxLayout(pAdmin, BoxLayout.X_AXIS));
		
		pAdmin.add(Box.createHorizontalStrut(50));
			
		eAdmin = new JLabel("Administrador:");
		pAdmin.add(eAdmin);
	
		pAdmin.add(Box.createHorizontalStrut(29));
	
		campoAdmin();
	
		pAdmin.add(Box.createHorizontalStrut(70));
		
		
	}
	
	private void panelFecha() {
		pFNac = new JPanel();
		
		norte.add(pFNac);
		pFNac.setLayout(new BoxLayout(pFNac, BoxLayout.X_AXIS));
		
		pFNac.add(Box.createHorizontalStrut(50));
			
		eFecha = new JLabel("Fecha Nac:");
		pFNac.add(eFecha);
	
		pFNac.add(Box.createHorizontalStrut(50));
	
		campoFecha();
	
		pFNac.add(Box.createHorizontalStrut(50));
		
		
	}

	private void panelDni() {
		pDni = new JPanel();
		
		norte.add(pDni);
		pDni.setLayout(new BoxLayout(pDni, BoxLayout.X_AXIS));
		
		pDni.add(Box.createHorizontalStrut(50));
			
		eDni = new JLabel("DNI:");
		pDni.add(eDni);
	
		pDni.add(Box.createHorizontalStrut(90));
	
		campoDni();
	
		pDni.add(Box.createHorizontalStrut(70));
	}

	private void panelApellidos() {
		pApellidos = new JPanel();
		
		norte.add(pApellidos);
		pApellidos.setLayout(new BoxLayout(pApellidos, BoxLayout.X_AXIS));
		
		pApellidos.add(Box.createHorizontalStrut(50));
			
		eApellidos = new JLabel("Apellidos:");
		pApellidos.add(eApellidos);
	
		pApellidos.add(Box.createHorizontalStrut(58));
	
		campoApellidos();
	
		pApellidos.add(Box.createHorizontalStrut(70));
		
	}

	private void panelNombre() {
		pNombre = new JPanel();
		
		norte.add(pNombre);
		pNombre.setLayout(new BoxLayout(pNombre, BoxLayout.X_AXIS));
		
		pNombre.add(Box.createHorizontalStrut(50));
			
		eNombre = new JLabel("Nombre:");
		pNombre.add(eNombre);
	
		pNombre.add(Box.createHorizontalStrut(65));
	
		campoNombre();
	
		pNombre.add(Box.createHorizontalStrut(70));
	}
	
	private void panelPass(){
		pPass = new JPanel();
		
		norte.add(pPass);
		pPass.setLayout(new BoxLayout(pPass, BoxLayout.X_AXIS));
		
		pPass.add(Box.createHorizontalStrut(50));
			
		ePass = new JLabel("Password:");
		pPass.add(ePass);
	
		pPass.add(Box.createHorizontalStrut(50));
	
		campoPass();
	
		pPass.add(Box.createHorizontalStrut(70));	
	}

	private void panelNick(){
		pNick = new JPanel();
		
		norte.add(pNick);
		pNick.setLayout(new BoxLayout(pNick, BoxLayout.X_AXIS));
		
		pNick.add(Box.createHorizontalStrut(50));
			
		eNick = new JLabel("Nick:");
		pNick.add(eNick);
	
		pNick.add(Box.createHorizontalStrut(83));
	
		campoNick();
	
		pNick.add(Box.createHorizontalStrut(70));	
	}
	
	
	private void campoNombre(){
		cNombre = new JTextField();
		cNombre.setText(usuario.getNombre());
		cNombre.setEditable(true);
		pNombre.add(cNombre);
	}
	
	private void campoApellidos(){
		cApellidos = new JTextField();
		cApellidos.setText(usuario.getApellidos());
		cApellidos.setEditable(true);
		pApellidos.add(cApellidos);
	}
	
	private void campoNick(){
		cNick = new JTextField();
		cNick.setText(usuario.getNick());
		cNick.setEditable(false);
		pNick.add(cNick);
	}
	
	private void campoPass(){
		cPass = new JTextField();
		cPass.setText(usuario.getPassword());
		cPass.setEditable(true);
		pPass.add(cPass);
	}
	
	private void campoDni(){
		cDni = new JTextField();
		cDni.setText(usuario.getDni());
		cDni.setEditable(true);
		pDni.add(cDni);
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
		} catch (ParseException e) {}
		try {
			cFecha.setDate(dateFormat.parse(usuario.getFechaNac()));
		} catch (ParseException e) {}
		pFNac.add(cFecha);
	}
	
	private void campoAdmin(){
		grupoBtns = new ButtonGroup();
		si = new JRadioButton("Sí");
		no = new JRadioButton("NO");
		
		if(usuario.isAdmin()){
			si.setSelected(true);
		}
		else 
			no.setSelected(true);
		grupoBtns.add(si);
		grupoBtns.add(no);
		pAdmin.add(si);
		pAdmin.add(no);
	}
	
	private void configurarEventos(){
		ponerEscuchaBotonCancelar();
		ponerEscuchaBotonAceptar();
	}
	
	private void ponerEscuchaBotonAceptar(){
		bAceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				try
				{	
					if(ventana.usuLog.isAdmin()){
						if(si.isSelected())
							usuario.setAdmin(true);
						else
							usuario.setAdmin(false);
					}
					recogidaDatosUsu();
					if(c.modificarUsuario(usuario)){
						JOptionPane.showMessageDialog(null, "El usuario ha sido modificado", "Correcto", JOptionPane.CLOSED_OPTION);
						volverAtras();
					}
					else
						JOptionPane.showMessageDialog(null, "Datos erroneos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
	}
	
	private void recogidaDatosUsu(){
		usuario.setNombre(cNombre.getText());
		usuario.setApellidos(cApellidos.getText());
		usuario.setNick(cNick.getText());
		usuario.setPassword(cPass.getText());
		usuario.setDni(cDni.getText());
		usuario.setfNac(new SimpleDateFormat("yyyy-MM-dd").format((cFecha).getDate()));
	}
	
	private void ponerEscuchaBotonCancelar() {
		bCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				volverAtras();		
			}
        });
		
	}
	
	private void volverAtras(){
		remove();
	    ventana.add(ventana.sacaPila());
		revalidate();
		ventana.pack();
		ventana.setLocationRelativeTo(null);
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
	
	

}


