package org.proyectofinal.ui.plantillasUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.proyectofinal.model.interfaces.ViajeCabecera;

public class PlantillaLV extends JDialog {
	
	private JPanel panelVuelos;
	private JScrollPane scrollPane;
	private JPanel panelVuelo;
	private JPanel panelImagenes;
	private JLabel lblMostrarImagen;
	private ImageIcon imagen1;
	private ImageIcon imagen2;
	private Icon icono;
	
	public PlantillaLV(){
		
	}
	
	public void inicializarAtributos(){
		setResizable(false);
		setTitle("Busqueda de vuelos");
		getContentPane().setBackground(new Color(245,245,245));
		setModal(true);
		setSize(750, 610);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	}
	
	public void inicializarComponentes(){
		agregarLabels();
		agregarPaneVuelo();		
	}

	private void agregarLabels() {

		JLabel label1 = new JLabel("Vuelo");
		label1.setVerticalAlignment(SwingConstants.CENTER);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Arial", Font.BOLD, 14));
		label1.setForeground(new Color(48, 63, 159));
		label1.setBackground(new Color(0,188,212));
		label1.setBounds(10, 10, 100, 20);
		getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Salida");
		label2.setVerticalAlignment(SwingConstants.CENTER);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Arial", Font.BOLD, 14));
		label2.setForeground(new Color(48, 63, 159));
		label2.setBounds(110,10, 160,20);
		getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Llegada");
		label3.setVerticalAlignment(SwingConstants.CENTER);
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("Arial", Font.BOLD, 14));
		label3.setForeground(new Color(48, 63, 159));
		label3.setBounds(302, 10, 160, 20);
		getContentPane().add(label3);
		
		JLabel label4 = new JLabel("Duracion");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setFont(new Font("Arial", Font.BOLD, 14));
		label4.setForeground(new Color(48, 63, 159));
		label4.setBounds(462, 10, 100, 20);
		getContentPane().add(label4);
	}

	private void agregarPaneVuelo() {

		panelVuelos = new JPanel();
		panelVuelos.setLayout(null);
		panelVuelos.setBounds(10, 30, 730, 240);
		panelVuelos.setPreferredSize(new Dimension(730,300));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 730, 240);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(panelVuelos);
		scrollPane.getViewport().setView(panelVuelos);
		getContentPane().add(scrollPane);
	}

	private void agregarPanel(int i) {
		
		panelVuelo = new JPanel();
		if ( i%2 == 0 ){
			panelVuelo.setBackground(Color.LIGHT_GRAY);
		}else{
			panelVuelo.setBackground(Color.WHITE);			
		}
		panelVuelo.setBounds(0, (60*i), 710, 60);
		panelVuelos.add(panelVuelo);
		panelVuelo.setLayout(null);
	}

	private void agregarInfo(ViajeCabecera viaje) {
		
		JLabel lblVuelo = new JLabel(viaje.getCodigoViaje());
		lblVuelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVuelo.setFont(new Font("Arial", Font.BOLD,14));
		lblVuelo.setBounds(0, 10, 100, 40);
		panelVuelo.add(lblVuelo);
		
		JLabel lblOrigen = new JLabel(viaje.getCiudadOrigen() + " (" + viaje.getPlataformaOrigen() + ")");
		lblOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrigen.setFont(new Font("Arial", Font.BOLD,14));
		lblOrigen.setBounds(100, 10, 160, 20);
		panelVuelo.add(lblOrigen);
		
		String fecha = viaje.getFechaSalida().toString().substring(8,10) + "/" + viaje.getFechaSalida().toString().substring(5,7) + "/" + viaje.getFechaSalida().toString().substring(0,4);
		String hora = viaje.getHoraSalida().toString().substring(0, 5);
		
		JLabel lblFechaSalida = new JLabel(fecha + " -- " + hora);
		lblFechaSalida.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSalida.setFont(new Font("Arial", Font.BOLD,14));
		lblFechaSalida.setBounds(100, 30, 160, 20);
		panelVuelo.add(lblFechaSalida);
		
		JLabel lblFlecha = new JLabel("");
		lblFlecha.setBounds(260, 14, 32, 32);
		lblFlecha.setVerticalAlignment(SwingConstants.CENTER);
		lblFlecha.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/flecha_derecha.png"));
		
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFlecha.getWidth(), lblFlecha.getHeight(), Image.SCALE_DEFAULT));
		
		lblFlecha.setIcon(icono);
		panelVuelo.add(lblFlecha);
		
		JLabel lblDestino = new JLabel(viaje.getCiudadDestino() + " (" + viaje.getPlataformaDestino() + ")");
		lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestino.setFont(new Font("Arial", Font.BOLD,14));
		lblDestino.setBounds(292, 10, 160, 20);
		panelVuelo.add(lblDestino);
		
		fecha = viaje.getFechaLlegada().toString().substring(8,10) + "/" + viaje.getFechaLlegada().toString().substring(5,7) + "/" + viaje.getFechaLlegada().toString().substring(0,4);
		hora = viaje.getHoraLlegada().toString().substring(0, 5);
		
		JLabel lblFechaLlegada = new JLabel(fecha + " -- " + hora);
		lblFechaLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaLlegada.setFont(new Font("Arial", Font.BOLD,14));
		lblFechaLlegada.setBounds(292, 30, 160, 20);
		panelVuelo.add(lblFechaLlegada);
		
		JLabel lblDuracion = new JLabel("01:00");
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracion.setFont(new Font("Arial", Font.BOLD,14));
		lblDuracion.setBounds(452, 10, 100, 20);
		panelVuelo.add(lblDuracion);
		
		JLabel label5 = new JLabel("hs");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setFont(new Font("Arial", Font.BOLD, 14));
		label5.setBounds(452, 30, 100, 20);
		panelVuelo.add(label5);
		
		JLabel label6 = new JLabel("Desde  AR$");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setFont(new Font("Arial", Font.BOLD, 14));
		label6.setBounds(547, 35, 93, 25);
		panelVuelo.add(label6);
		
		JLabel lblPrecio = new JLabel(String.format("%.2f", viaje.getPrecioClaseTur()));
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setFont(new Font("Arial", Font.BOLD,14));
		lblPrecio.setBounds(640, 35, 70, 25);
		panelVuelo.add(lblPrecio);
	}

	private void agregarBoton() {
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(567, 5, 118, 30);
		panelVuelo.add(btnSeleccionar);
	}
	
	private void agregarPanelImagenes(ViajeCabecera viaje) {
		
		panelImagenes = new JPanel();
		panelImagenes.setBackground(new Color(33,33,33));
		panelImagenes.setBounds(10, 280, getWidth()-20, 310);
		getContentPane().add(panelImagenes);
		panelImagenes.setLayout(null);
	
		imagen1 = new ImageIcon(getClass().getResource( viaje.getImagen1() ));
		imagen2 = new ImageIcon(getClass().getResource( viaje.getImagen2() ));

		agregarLabelMostrarImagen();
		agregarBotonesImagenes();
	}
	
	private void agregarLabelMostrarImagen() {

		lblMostrarImagen = new JLabel("");
		lblMostrarImagen.setBounds(90, 10, panelImagenes.getWidth()-100, panelImagenes.getHeight()-20);
		
		icono = new ImageIcon(imagen1.getImage().getScaledInstance(lblMostrarImagen.getWidth(), lblMostrarImagen.getHeight(), Image.SCALE_DEFAULT));
		
		lblMostrarImagen.setIcon(icono);
		
		panelImagenes.add(lblMostrarImagen);
		lblMostrarImagen.setLayout(null);
	}

	private void agregarBotonesImagenes() {
		agregarBotonImagen1();
		agregarBotonImagen2();
	}
	
	private void agregarBotonImagen1() {
		
		JButton btnImagen1 = new JButton("");
		btnImagen1.setBounds(10, 10, 70, 70);
		
		icono = new ImageIcon(imagen1.getImage().getScaledInstance(btnImagen1.getWidth(), btnImagen1.getHeight(), Image.SCALE_DEFAULT));
		
		btnImagen1.setIcon(icono);
		btnImagen1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				icono = new ImageIcon(imagen1.getImage().getScaledInstance(lblMostrarImagen.getWidth(), lblMostrarImagen.getHeight(), Image.SCALE_DEFAULT));
				
				lblMostrarImagen.setIcon(icono);
				
				panelImagenes.validate();
				panelImagenes.repaint();
			}
		});
		panelImagenes.add(btnImagen1);
	}

	private void agregarBotonImagen2() {
		
		JButton btnImagen2 = new JButton("");
		btnImagen2.setBounds(10, 90, 70, 70);

		icono = new ImageIcon(imagen2.getImage().getScaledInstance(btnImagen2.getWidth(), btnImagen2.getHeight(), Image.SCALE_DEFAULT));
		
		btnImagen2.setIcon(icono);
		btnImagen2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				icono = new ImageIcon(imagen2.getImage().getScaledInstance(lblMostrarImagen.getWidth(), lblMostrarImagen.getHeight(), Image.SCALE_DEFAULT));
				
				lblMostrarImagen.setIcon(icono);
				
				panelImagenes.validate();
				panelImagenes.repaint();
			}
		});
		panelImagenes.add(btnImagen2);		
	}
	
	public void mostrarVuelos(List<ViajeCabecera> listaViajes){
		
		int i = 0;

		agregarPanelImagenes(listaViajes.get(0));
		
		for (ViajeCabecera viaje : listaViajes) {
			agregarPanel(i);
			agregarInfo(viaje);
			agregarBoton();
		
			i++;
		}

		scrollPane.setViewportView(panelVuelos);
	}

}