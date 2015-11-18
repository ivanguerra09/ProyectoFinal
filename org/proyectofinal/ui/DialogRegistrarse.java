package org.proyectofinal.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;

import java.sql.Timestamp;
//import java.text.SimpleDateFormat;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.proyectofinal.bo.impl.PersonaBoImpl;
import org.proyectofinal.bo.impl.UsuarioBoImpl;
import org.proyectofinal.bo.interfaces.PersonaBo;
import org.proyectofinal.bo.interfaces.UsuarioBo;
import org.proyectofinal.dao.ex.PersonNotValidException;
import org.proyectofinal.dao.ex.UserNotValidException;
import org.proyectofinal.dao.impl.PersonaDaoImpl;
import org.proyectofinal.dao.impl.UsuarioDaoImpl;
import org.proyectofinal.dao.interfaces.PersonaDao;
import org.proyectofinal.dao.interfaces.UsuarioDao;
import org.proyectofinal.model.impl.PersonaImpl;
import org.proyectofinal.model.impl.UsuarioImpl;
import org.proyectofinal.model.interfaces.Persona;
import org.proyectofinal.model.interfaces.Usuario;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class DialogRegistrarse extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8710372283429686677L;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtPais;
	private JTextField txtCiudad;
	private JTextField txtNombreUsuario;
	private JPasswordField txtContrasea;
	private JPasswordField txtReescribirContrasea;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbTipoUsuario;
	private JDateChooser birthDateChooser;
	private Usuario u;
	private Persona p;
	private UsuarioBo uBo;
	private PersonaBo pBo;
	private UsuarioDao uDao;
	private PersonaDao pDao;
	private JPanel panelDatosPersonales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogRegistrarse dialog = new DialogRegistrarse();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error de DialogRegistrarse");
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DialogRegistrarse() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
			
				if (cmbTipoUsuario.getSelectedIndex() == 0){
					u.setTipoUsuario(1);
				}else if (cmbTipoUsuario.getSelectedIndex() == 1){
					u.setTipoUsuario(0);	
				}
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		
		u = new UsuarioImpl();
		p = new PersonaImpl();
		
		uBo = new UsuarioBoImpl();
		pBo = new PersonaBoImpl();
		
		uDao = new UsuarioDaoImpl();
		pDao = new PersonaDaoImpl();
		
		setResizable(false);
		setTitle("Registrarse");
		setModal(true);
		setBounds(100, 100, 400, 580);
		getContentPane().setLayout(null);
		
		birthDateChooser = new JDateChooser();
		birthDateChooser.setBounds(187, 150, 177, 20);
		
		panelDatosPersonales = new JPanel();
		panelDatosPersonales.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Datos personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosPersonales.setBounds(12, 12, 376, 280);
		panelDatosPersonales.add(birthDateChooser);
		getContentPane().add(panelDatosPersonales);
		panelDatosPersonales.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(20, 82, 170, 27);
		panelDatosPersonales.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(20, 30, 170, 15);
		panelDatosPersonales.add(lblNombre);
			
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(20, 56, 170, 15);
		panelDatosPersonales.add(lblApellido);
	
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(20, 120, 170, 15);
		panelDatosPersonales.add(lblEmail);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento: ");
		lblFechaDeNacimiento.setBounds(20, 150, 170, 15);
		panelDatosPersonales.add(lblFechaDeNacimiento);
	
		JLabel lblPais = new JLabel("Pais: ");
		lblPais.setBounds(20, 210, 170, 15);
		panelDatosPersonales.add(lblPais);
	
		JLabel lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setBounds(20, 180, 170, 15);
		panelDatosPersonales.add(lblTelefono);
	
		JLabel lblCiudad = new JLabel("Ciudad: ");
		lblCiudad.setBounds(20, 240, 170, 15);
		panelDatosPersonales.add(lblCiudad);
	
		txtDni = new JTextField();
		txtDni.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (txtDni.getText().trim().length() > 0){
					p.setDni(txtDni.getText());
				}
			}
		});
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
		
				char c = e.getKeyChar();
				
				if ((c < '0' || c > '9') && (c != KeyEvent.VK_KP_LEFT) && c != (KeyEvent.VK_KP_RIGHT) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		txtDni.setBounds(187, 87, 177, 20);
		panelDatosPersonales.add(txtDni);
		txtDni.setColumns(10);
	
		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			
				if (txtNombre.getText().trim().length() > 0){
					p.setNombre(txtNombre.getText());
				}
			}
		});
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != KeyEvent.VK_KP_LEFT) && c != (KeyEvent.VK_KP_RIGHT) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(187, 27, 177, 20);
		panelDatosPersonales.add(txtNombre);
	
		txtApellido = new JTextField();
		txtApellido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (txtApellido.getText().trim().length() > 0){
					p.setApellido(txtApellido.getText());
				}
			}
		});
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != KeyEvent.VK_KP_LEFT) && c != (KeyEvent.VK_KP_RIGHT) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
						}
					}
				});
		txtApellido.setColumns(10);
		txtApellido.setBounds(187, 56, 177, 20);
		panelDatosPersonales.add(txtApellido);		
		
		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (txtEmail.getText().trim().length() > 0){
					p.setEmail(txtEmail.getText());
				}
			}
		});
		txtEmail.setColumns(10);
		txtEmail.setBounds(187, 120, 177, 20);
		panelDatosPersonales.add(txtEmail);

		txtTelefono = new JTextField();
		txtTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			
				if (txtTelefono.getText().trim().length() > 0){
					p.setTelefono(txtTelefono.getText());
				}
			}
		});
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if ((c < '0' || c > '9') && (c != KeyEvent.VK_KP_LEFT) && c != (KeyEvent.VK_KP_RIGHT) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(187, 180, 177, 20);
		panelDatosPersonales.add(txtTelefono);

		txtPais = new JTextField();
		txtPais.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (txtPais.getText().trim().length() > 0){
					p.setPais(txtPais.getText());
				}
			}
		});
		txtPais.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != KeyEvent.VK_KP_LEFT) && c != (KeyEvent.VK_KP_RIGHT) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		txtPais.setColumns(10);
		txtPais.setBounds(187, 210, 177, 20);
		panelDatosPersonales.add(txtPais);
			
		txtCiudad = new JTextField();
		txtCiudad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (txtCiudad.getText().trim().length() > 0){
					p.setCiudad(txtCiudad.getText());
				}
			}
		});
		txtCiudad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c != KeyEvent.VK_KP_LEFT) && c != (KeyEvent.VK_KP_RIGHT) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
			}
		});
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(187, 240, 177, 20);
		panelDatosPersonales.add(txtCiudad);
		
		cmbTipoUsuario = new JComboBox();
		cmbTipoUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (cmbTipoUsuario.getSelectedIndex() == 0){
					u.setTipoUsuario(1);
				}else if (cmbTipoUsuario.getSelectedIndex() == 1){
					u.setTipoUsuario(0);	
				}
			}
		});
		cmbTipoUsuario.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbTipoUsuario.getSelectedIndex() == 0){
					u.setTipoUsuario(1);
				}else if (cmbTipoUsuario.getSelectedIndex() == 1){
					u.setTipoUsuario(0);	
				}
			}
		});
		cmbTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Com�n", "Administrador"}));
		cmbTipoUsuario.setBounds(187, 120, 177, 20);
		
		JPanel panelDatosUsuario = new JPanel();
		panelDatosUsuario.setBorder(new TitledBorder(null, "Datos del usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosUsuario.setBounds(12, 304, 376, 160);
		panelDatosUsuario.add(cmbTipoUsuario);
		getContentPane().add(panelDatosUsuario);
		panelDatosUsuario.setLayout(null);
			
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario: ");
		lblNombreUsuario.setBounds(20, 30, 170, 20);
		panelDatosUsuario.add(lblNombreUsuario);
	
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (txtNombreUsuario.getText().trim().length() > 0){
					u.setNombreUsuario(txtNombreUsuario.getText());
				}
			}
		});
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(187, 30, 177, 20);
		panelDatosUsuario.add(txtNombreUsuario);
	
		JLabel lblContrasea = new JLabel("Contrase�a: ");
		lblContrasea.setBounds(20, 60, 170, 20);
		panelDatosUsuario.add(lblContrasea);
	
		JLabel lblTipoUsuario = new JLabel("Tipo de usuario: ");
		lblTipoUsuario.setBounds(20, 120, 170, 20);
		panelDatosUsuario.add(lblTipoUsuario);
	
		JLabel lblReescribirContrasea = new JLabel("Reescribir contrase�a: ");
		lblReescribirContrasea.setBounds(20, 90, 170, 20);
		panelDatosUsuario.add(lblReescribirContrasea);
	
		txtContrasea = new JPasswordField();
		txtContrasea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String pass = new String(txtContrasea.getPassword());
				
				if (pass.trim().length() > 0){
					u.setPassword(pass);					
				}
				
			}
		});
		txtContrasea.setBounds(187, 60, 177, 20);
		panelDatosUsuario.add(txtContrasea);
	
		txtReescribirContrasea = new JPasswordField();
		txtReescribirContrasea.setBounds(187, 90, 177, 20);
		panelDatosUsuario.add(txtReescribirContrasea);
	
		final JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {	
				
				
				if (e.getSource() == btnRegistrarse){
					
					java.util.Date now = new java.util.Date();
					Timestamp fechaActual = new Timestamp(now.getTime());
					
					Date date = new Date(birthDateChooser.getDate().getTime());
					
					p.setFechaNacimiento(date);
					
					u.setFechaInicio(fechaActual);
					
					p.setUsuario(u);	
					
					try {
													
						uBo.verificar(u);
						pBo.verificar(p);
						
						uDao.alta(u);
						pDao.alta(p);

						JOptionPane.showMessageDialog(null, "Se ha registrado el usuario con exito!"); setVisible(false);


					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
						limpiar();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Ocurrio en error en la base de datos. Compruebe su conexi�n y vuelva a intentarlo.");
						limpiar();
					} catch (UserNotValidException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						limpiarUsuario();
					} catch (PersonNotValidException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						limpiarPersona();
					}

				}
			
			}

			
		});
		btnRegistrarse.setBounds(12, 476, 174, 25);
		getContentPane().add(btnRegistrarse);

		final JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
			
				if (e.getSource() == btnCancelar){
					dispose();
				}
			}
		});
		btnCancelar.setBounds(271, 476, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblTodosLos = new JLabel("* Todos los campos son obligatorios");
		lblTodosLos.setBounds(12, 513, 366, 28);
		getContentPane().add(lblTodosLos);
	
	}
	
	private void limpiarPersona() {
	
		txtDni.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		birthDateChooser.setDate(null);
		txtPais.setText("");
		txtCiudad.setText("");
		
		txtDni.requestFocus();
	}
	
	private void limpiarUsuario() {

		txtNombreUsuario.setText("");
		txtContrasea.setText("");
		txtReescribirContrasea.setText("");
		cmbTipoUsuario.setSelectedIndex(0);
		
		txtNombreUsuario.requestFocus();
	
	}
	
	private void limpiar() {

		txtDni.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		birthDateChooser.setDate(null);
		txtPais.setText("");
		txtCiudad.setText("");
		txtNombreUsuario.setText("");
		txtContrasea.setText("");
		txtReescribirContrasea.setText("");
		cmbTipoUsuario.setSelectedIndex(0);
		
		txtDni.requestFocus();	
	}
}