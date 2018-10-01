package com.adriana.prado.pojo;

public class Usuario {
	private long id;
	private String nombre;
	private String contrasena;
	private int rol;
	
	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
		this.contrasena = "";
		this.rol = 1;
	}

	public Usuario(String nombre, String contraseña) {
		this();
		this.nombre = nombre;
		this.contrasena = contraseña;
	}
	
	public Usuario(String nombre, String contraseña, int rol) {
		this();
		this.nombre = nombre;
		this.contrasena = contraseña;
		this.rol = rol;
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

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return id + "- "+ nombre + "-" + contrasena;
	}
	
}
