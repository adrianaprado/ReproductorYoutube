package com.adriana.prado.pojo;

import java.util.ArrayList;
import java.util.List;

public class Video {
	private String id;
	private String titulo;
	private String descripcion;
	private static List<Comentario> comentarios;

	public static final int ID_LENGTH = 11;
	public static final String ID_LENGTH_EXCEPTION = "La longitud del ID tiene que ser " + ID_LENGTH;

	public Video() {
		super();
		this.id = "UmYKPY_-ejc";
		this.titulo = "Game of Thrones - Season 7 - Main Titles";
		this.descripcion = "Esta es una descripción de ejemplo para el vídeo por defecto.";
		Video.comentarios = new ArrayList<Comentario>();
	}

	public Video(String id, String titulo) throws Exception {
		this();
		setId(id);
		this.titulo = titulo;
	}
	
	public Video(String id, String titulo, String descripcion, List<Comentario> comentarios) throws Exception {
		this();
		setId(id);
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.comentarios = comentarios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws Exception {
		if (id != null && id.trim().length() == ID_LENGTH)
			this.id = id;
		else
			throw new Exception(ID_LENGTH_EXCEPTION);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static List<Comentario> getComentarios() {
		return comentarios;
	}

	public static void setComentarios(List<Comentario> comentarios) {
		Video.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + "]";
	}
}
