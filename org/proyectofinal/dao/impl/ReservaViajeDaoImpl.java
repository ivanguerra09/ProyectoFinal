package org.proyectofinal.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.proyectofinal.dao.abstracts.AbstractDao;
import org.proyectofinal.dao.interfaces.ReservaViajeDao;
import org.proyectofinal.model.interfaces.ReservaViaje;

public class ReservaViajeDaoImpl extends AbstractDao implements ReservaViajeDao {
	
	private Connection conexion = null;
	
	public void conectar() throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
		
//		this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3305/ReservasAvion", "root", "asd123");
		this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ReservasAvion", "root", "genius34");
	}
	
	public void desconectar() throws SQLException{
		getConexion().close();
	}
	
	public Connection getConexion() {
		return this.conexion;
	}
	
	public ResultSet consultar() throws ClassNotFoundException, SQLException{
		
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("SELECT * FROM ReservaViaje");
		
		ResultSet resultado = sentencia.executeQuery();
		
		desconectar();
		
		return resultado;
	}
	
	public ResultSet consultarPorPasajero(ReservaViaje rV) throws ClassNotFoundException, SQLException{
		
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("SELECT * FROM ReservaViaje WHERE dni = ?");
		
		sentencia.setString(1, rV.getPasajero().getDni());
		
		ResultSet resultado = sentencia.executeQuery();
		
		desconectar();
		
		return resultado;
	}
	
//	public ResultSet consultarPorDniPersona() throws ClassNotFoundException, SQLException{
//		
//		PreparedStatement sentencia = getConexion().prepareStatement("SELECT DISTINCT dniPersona FROM ReservaViaje WHERE dniPersona = ?");
//			
//	}
	
	public ResultSet consultarPorPersonaQueReserva(String dni) throws ClassNotFoundException, SQLException{
		
		PreparedStatement sentencia = getConexion().prepareStatement("SELECT * FROM ReservaViaje WHERE dniPersona = ?");
		
		sentencia.setString(1, dni);
		
		ResultSet resultado = sentencia.executeQuery();
		
		return resultado;
	}
	
	public ResultSet consultarPorViaje(ReservaViaje rV) throws ClassNotFoundException, SQLException{
		
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("SELECT * FROM ReservaViaje WHERE codViaje = ?");
		
		sentencia.setInt(1, rV.getViaje().getCodigoViaje());
		
		ResultSet resultado = sentencia.executeQuery();
		
		desconectar();
		
		return resultado;
	}
	
	public ResultSet consultarAsientosPorViaje(Integer codViaje) throws ClassNotFoundException, SQLException{
		
		PreparedStatement sentencia = getConexion().prepareStatement("select distinct asiento from ReservaViaje where codViaje = ?");
		
		sentencia.setInt(1, codViaje);
		
		ResultSet resultado = sentencia.executeQuery();
		
		return resultado;
	}
	
	public void alta(ReservaViaje rV) throws SQLException, ClassNotFoundException{
		
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("INSERT INTO ReservaViaje (codViaje, dniPasajero, fechaReserva, dniPersona, asiento, precio) VALUES (?,?,?,?,?,?)");
		
		sentencia.setInt(1, rV.getViaje().getCodigoViaje());
		sentencia.setString(2, rV.getPasajero().getDni());
		sentencia.setTimestamp(3, rV.getFechaReserva());
		sentencia.setString(4, rV.getDniPersona());
		sentencia.setInt(5, rV.getAsiento());
		sentencia.setFloat(6, rV.getPrecio());
		
		sentencia.executeUpdate();
		
		desconectar();
	}
	
	public void baja(ReservaViaje rV) throws SQLException, ClassNotFoundException{
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("DELETE FROM ReservaViaje WHERE codViaje = ? AND dni = ? AND fechaReserva = ?");
		
		sentencia.setInt(1, rV.getViaje().getCodigoViaje());
		sentencia.setString(2, rV.getPasajero().getDni());
		sentencia.setTimestamp(3, rV.getFechaReserva());
		
		sentencia.executeUpdate();
		
		desconectar();
	}
	
	public void modificacion(String dniNuevo, String dniAnterior) throws SQLException, ClassNotFoundException {
		
		this.conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("update ReservaViaje set dniPersona = ? where dniPersona = ?");

		sentencia.setString(1, dniNuevo);
		sentencia.setString(2, dniAnterior);
		
		sentencia.executeUpdate();
		
		this.desconectar();
	}	

}