package com.adriana.prado.pojo;

public class Video {
	private String id;
	private String titulo;
	private String descripcion;

	public static final int ID_LENGTH = 11;
	public static final String ID_LENGTH_EXCEPTION = "La longitud del ID tiene que ser " + ID_LENGTH;

	public Video() {
		super();
		id = "UmYKPY_-ejc";
		titulo = "Game of Thrones - Season 7 - Main Titles";
		descripcion = "Esta es una descripción de ejemplo para el vídeo por defecto.";
		
	}

	public Video(String id, String titulo) throws Exception {
		this();
		setId(id);
		this.titulo = titulo;
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

	@Override
	public String toString() {
		return "VideoYoutube [id=" + id + ", titulo=" + titulo + "]";
	}
}
