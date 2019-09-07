package com.entity;



import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transaccion {
	
	private String login;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp fecha;
	private double deposito;
	private double retiro;
	private double saldo;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public double getDeposito() {
		return deposito;
	}
	public void setDeposito(double deposito) {
		this.deposito = deposito;
	}
	public double getRetiro() {
		return retiro;
	}
	public void setRetiro(double retiro) {
		this.retiro = retiro;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "Transacciones [login=" + login + ", fecha=" + fecha + ", deposito=" + deposito + ", retiro=" + retiro
				+ ", saldo=" + saldo + "]";
	}
	
	

}
