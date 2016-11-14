package sesion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import CarPlace.VistaGui;
import sesion.control.ControlSesion;
import usuarios.control.ControlUsuario;
import usuarios.vista.GUIAltaUsuario;

public class GUILogIn extends JPanel{
	
	private JPanel este = this;
	private JPanel norte = new JPanel();
	private JPanel sur = new JPanel();
	private JPanel bienvenido = new JPanel();
	private JPanel nick = new JPanel();
	private JPanel pass = new JPanel();
	private JPanel botones = new JPanel();
	private JTextField nickField = new JTextField();
	private JPasswordField passField = new JPasswordField();
	private JButton login = new JButton();
	private JButton registro = new JButton();
	private ControlSesion cs;
	private VistaGui window;
	private ControlUsuario cUSu;
	
	
	
	public GUILogIn(ControlSesion control,ControlUsuario cUsuario, VistaGui ventana){
		this.cs = control;
		this.window = ventana;
		this.cUSu = cUsuario;
		this.window.ocultarMenu();
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		configInit();
		
	}
	
	/**
	 * Configura el Panel principal que contendra al resto de elementos del panel de login
	 */
	private void configInit(){
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
		cons.insets = new Insets(20, 0, 0, 0);
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
		cons.gridwidth = 2;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.SOUTH;
		this.add(panelInferior(), cons);
	}
	
	/**
	 * Configura el panel inferior donde se encuentran los botones de logion y registo
	 * @return devuelve el panel inferior ya configurado
	 */
	private JPanel panelInferior(){
		JPanel pi = new JPanel();
		pi.setLayout(new GridBagLayout());
		GridBagConstraints constraints;
			
		this.login  = new JButton("Iniciar sesion");
		this.login.setIcon(new ImageIcon(getClass().getResource("imagenes/inisesion.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 350, 20, 50);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.login, constraints);
		
		this.registro  = new JButton("Registrarse");
		this.registro.setIcon(new ImageIcon(getClass().getResource("imagenes/mas.png")));
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 100, 20, 20);
		constraints.gridx = 1; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;
		pi.add(this.registro, constraints);
		
		pi.setBackground(new Color(176,196,222));
		
		configLogin();
		configReg();
		
		return pi;
	}
	
	/**
	 * Configura el panel izquierdo donde se muestra el logo representativo de nuestra marca
	 * @return devuelve el panel izquierdo ya configurado
	 */
	private JPanel panelIzquierdo(){
		JPanel pinf = new JPanel();
		
		JLabel i = new JLabel();
		
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("imagenes/logo.png");
		icono = new ImageIcon(url_icono);
		
		i.setIcon(icono);
		pinf.add(i);
		
		pinf.setBackground(new Color(176,196,222));
		
		return pinf;
	}
	/**
	 * Configura el panel derecho donde aparecen los campos de datos para el usuario
	 * y el cartel de bienvenida
	 * @return devuelve el panel derecho ya configurado
	 */
	private JPanel panelDerecho(){
		JPanel pd = new JPanel();
		
		configNorte();
		pd.add(norte);
		
		return pd;
	}

	/**
	 * Configura el panel derecho donde aparecen los campos de datos para el usuario
	 * y el cartel de bienvenida
	 */
	private void configNorte() {
		norte.setLayout(new BorderLayout());
		
		configBienvenido();
		configNick();
		configPass();
		
		norte.add(bienvenido, BorderLayout.NORTH);
		norte.add(nick, BorderLayout.CENTER);
		norte.add(pass, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Crea el panel donde aparecen la etiqueta el nick y su campo de texto
	 */
	private void configNick() {
		nick.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		nick.add(new JLabel("Nick:  "));
		nick.add(nickField);
		nickField.setColumns(8);
		nickField.setText("");
		
	}

	/**
	 * Crea el panel donde aparecen la etiqueta de bienvenida
	 */
	private void configBienvenido() {
		bienvenido.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		bienvenido.add(new JLabel("Bienvenido a CarPlace! Introduzca su cuenta, si no tiene pulse el boton 'Registrarse'"));
	}


	/**
	 * Configura el boton de Registro y le añade su actionListener
	 */
	private void configReg() {
		registro.setText("Registrarse");
		
		registro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				meteAlaPila();
				remove();
				GUIAltaUsuario res = new GUIAltaUsuario(cUSu,window);
				window.add(res);
				revalidate();
				window.pack();
				window.setLocationRelativeTo(null);	
			}
			
		});
	}

	/**
	 * Configura el boton de Login y le añade su actionListener
	 */
	private void configLogin() {
		login.setText("Iniciar sesion");
		
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(cs.login(nickField.getText(), getPass())){					
					VistaGui.usuLog = cUSu.mostrarUsuario(nickField.getText());
					final JOptionPane optionPane = new JOptionPane("Bienvenido " + VistaGui.usuLog.getNick(), JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

					final JDialog dialog = new JDialog();
					dialog.setTitle("Message");
					dialog.setModal(true);

					dialog.setContentPane(optionPane);

					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					dialog.pack();

					//create timer to dispose of dialog after 5 seconds
					Timer timer = new Timer(1000, new AbstractAction() {
					    @Override
					    public void actionPerformed(ActionEvent ae) {
					        dialog.dispose();
					    }
					});
					timer.setRepeats(false);//the timer should only go off once

					//start timer to close JDialog as dialog modal we must start the timer before its visible
					timer.start();
					window.verMenu();
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
					window.remove(este);
					window.activDesItemMenu();
					revalidate();
					window.pack();
					window.setLocationRelativeTo(null);
				}
				else{
					JOptionPane.showMessageDialog(null, "Nick o password incorrectos!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	
	/**
	 * Crea el panel donde aparecen la etiqueta de la password y su campo de texto
	 */
	private void configPass() {
		pass.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		pass.add(new JLabel("Pass:  "));
		pass.add(passField);
		
		passField.setColumns(8);
		passField.setText("");
	}
	
	/**
	 * Transforma el contenido JPassword del passField en un String para su futuro uso
	 * @return devuelve un String con la password introducida por el usuario
	 */
	public String getPass(){
		
		char[] c = passField.getPassword();
		String s = "";
		
		for(int i = 0; i < c.length; i++ ){
			
			s = s + c[i];
		}
		
		return s;
	}
	
	/**
	 * Elimina el panel de login de la ventana
	 */
	private void remove(){
		this.window.remove(this);
	}
	
	/**
	 * Añade el panel a una pila de paneles
	 */
	private void meteAlaPila(){
		VistaGui.metePila(this);
	}
	
	

}
