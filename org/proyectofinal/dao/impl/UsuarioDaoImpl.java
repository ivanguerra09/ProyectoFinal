package org.proyectofinal.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.proyectofinal.dao.abstracts.AbstractDao;
import org.proyectofinal.dao.ex.UserNotExistsException;
import org.proyectofinal.dao.interfaces.UsuarioDao;
import org.proyectofinal.model.interfaces.Usuario;

public class UsuarioDaoImpl extends AbstractDao implements UsuarioDao {

	public ResultSet consultar() throws ClassNotFoundException, SQLException {
		
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("select * from Usuario");
		
		ResultSet resultado = sentencia.executeQuery();
		
		return resultado;
	}
	
	public ResultSet consultarPorUsuario(Usuario u) throws ClassNotFoundException, SQLException, UserNotExistsException {
		
		conectar();

		PreparedStatement sentencia = getConexion().prepareStatement("select * from Usuario where usuario = ?");
		
		sentencia.setString(1, u.getNombreUsuario());
		
		ResultSet resultado = sentencia.executeQuery();
		
//		if (!resultado.next()) throw new UserNotExistsException();
//		else 
		return resultado;
	}
	
	public ResultSet consultarPorUsuario(String usuario) throws ClassNotFoundException, SQLException, UserNotExistsException {
		
		conectar();

		PreparedStatement sentencia = getConexion().prepareStatement("select * from Usuario where usuario = ?");
		
		sentencia.setString(1, usuario);
		
		ResultSet resultado = sentencia.executeQuery();
		
		if (!resultado.next()) throw new UserNotExistsException();

		return resultado;
	}

	public void alta(Usuario u) throws SQLException, ClassNotFoundException{
		
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("insert into Usuario (usuario, contrasenia, tipoUsuario, fechaInicio) values (?,?,?,?)");
		
		sentencia.setString(1, u.getNombreUsuario());
		sentencia.setString(2, u.getPassword());
		sentencia.setInt(3, u.getTipoUsuario());
		sentencia.setTimestamp(4, u.getFechaInicio());
		
		sentencia.executeUpdate();
		
		desconectar();
	}

	public void baja(Usuario u) throws SQLException, ClassNotFoundException{
			
		conectar();
		
		PreparedStatement sentencia = getConexion().prepareStatement("delete from usuario where usuario = ?");

		sentencia.setString(1, u.getNombreUsuario());
		
		sentencia.executeUpdate();
		
		desconectar();
	}

	public void modificacion(Usuario u) throws SQLException, ClassNotFoundException{
		conectar();
		desconectar();
	}

}