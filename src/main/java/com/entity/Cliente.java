package com.entity;

public class Cliente {
	
	private Long id;
	private String login;
	private String password;
	private String nombre;
	private String apellidos;
	private String ci;
	private String telefono;
	private double saldo;
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", login=" + login + ", password=" + password + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", ci=" + ci + ", telefono=" + telefono + ", saldo=" + saldo + "]";
	}
	
	

}
