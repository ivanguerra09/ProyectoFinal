package org.proyectofinal.model.interfaces;

import java.sql.Timestamp;

public interface ViajeCabecera extends Cloneable {
	
	public Integer getCodigoViaje();
	public void setCodigoViaje(Integer codigoViaje);

	public String getCiudadOrigen();
	public void setCiudadOrigen(String ciudadOrigen);
	
	public String getPaisOrigen();
	public void setPaisOrigen(String paisOrigen);
	
	public String getCiudadDestino();
	public void setCiudadDestino(String ciudadDestino);
	
	public String getPaisDestino();
	public void setPaisDestino(String paisDestino);
	
	public Timestamp getFechaSalida();
	public void setFechaSalida(Timestamp fechaSalida);
	
	public Timestamp getFechaLlegada();
	public void setFechaLlegada(Timestamp fechaLlegada);
	
	@Override
	public int hashCode();
	
	@Override
	public boolean equals(Object obj);
	
	@Override
	public String toString();
	
	public ViajeCabecera clone() throws CloneNotSupportedException;
}