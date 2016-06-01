package org.proyectofinal.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.proyectofinal.dao.ex.PersonAlreadyExistsException;
import org.proyectofinal.model.interfaces.PersonaRegistrada;
import org.proyectofinal.model.interfaces.Usuario;

public interface PersonaRegistradaDao {
	
	public ResultSet consultar() throws SQLException, ClassNotFoundException;
	public ResultSet consultarPorDni(PersonaRegistrada p) throws SQLException, ClassNotFoundException;
	public ResultSet consultarPorUsuario(Usuario u) throws SQLException, ClassNotFoundException;
	public ResultSet consultarPorPersonaYUsuario(String dni) throws SQLException, ClassNotFoundException;
	public ResultSet consultarPorUsuario(String user) throws SQLException, ClassNotFoundException;
	public ResultSet consultarPersonaPorDni(PersonaRegistrada p) throws SQLException, ClassNotFoundException;
	public ResultSet consultarPersonaPorDni(String dni) throws SQLException, ClassNotFoundException;
	public ResultSet consultarEmail(String dni) throws SQLException, ClassNotFoundException;
	public void altaPersonaRegistrada(PersonaRegistrada p) throws SQLException, ClassNotFoundException, PersonAlreadyExistsException;
	public void bajaPersonaRegistrada(PersonaRegistrada p) throws SQLException, ClassNotFoundException;
	public void modificacionDni(String atr, String valor, String dni) throws SQLException, ClassNotFoundException, PersonAlreadyExistsException;
	public void modificacion(String atr, String valor, String dni) throws SQLException, ClassNotFoundException;
}