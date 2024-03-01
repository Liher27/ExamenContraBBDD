package bbdd;

import java.util.Date;
import java.util.Objects;

public class ordenador {
	
	private String numSerie = null;
	private String marcaString = null;
	private String modelo = null;
	private Date fechaCompra = new Date();
	private String memoria = null;
	private String disco = null;
	private String funciona = null;
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getMarcaString() {
		return marcaString;
	}
	public void setMarcaString(String marcaString) {
		this.marcaString = marcaString;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getMemoria() {
		return memoria;
	}
	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	public String getDisco() {
		return disco;
	}
	public void setDisco(String disco) {
		this.disco = disco;
	}
	public String getFunciona() {
		return funciona;
	}
	public void setFunciona(String funciona) {
		this.funciona = funciona;
	}
	@Override
	public int hashCode() {
		return Objects.hash(disco, fechaCompra, funciona, marcaString, memoria, modelo, numSerie);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ordenador other = (ordenador) obj;
		return Objects.equals(disco, other.disco) && Objects.equals(fechaCompra, other.fechaCompra)
				&& Objects.equals(funciona, other.funciona) && Objects.equals(marcaString, other.marcaString)
				&& Objects.equals(memoria, other.memoria) && Objects.equals(modelo, other.modelo)
				&& Objects.equals(numSerie, other.numSerie);
	}
	@Override
	public String toString() {
		return "ordenador [numSerie=" + numSerie + ", marcaString=" + marcaString + ", modelo=" + modelo
				+ ", fechaCompra=" + fechaCompra + ", memoria=" + memoria + ", disco=" + disco + ", funciona="
				+ funciona + "]";
	}
	

	}
