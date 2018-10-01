package com.adriana.prado.pojo;

public class Usuario {
	private long id;
	private String nombre;
	private String contrasena;
	
	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
		this.contrasena = "";
	}

	public Usuario(String nombre, String contraseña) {
		this();
		this.nombre = nombre;
		this.contrasena = contraseña;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contraseña) {
		this.contrasena = contraseña;
	}

	@Override
	public String toString() {
		return id + "- "+ nombre + "-" + contrasena;
	}
	
}
