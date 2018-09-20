package com.adriana.prado.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.adriana.prado.pojo.Comentario;
import com.adriana.prado.pojo.Video;

public class VideoArrayDAO implements Crudable<Video> {

	private static VideoArrayDAO INSTANCE = null;
	private static List<Video> lista = null;

	private VideoArrayDAO() {
		lista = new ArrayList<Video>();
		try {
			lista.add(new Video("xuoXkMZvD5Q", "AC/DC - You Shook Me All Night Long", "Esta es la descripción del vídeo de AC/DC - You Shook Me All Night Long", new ArrayList<Comentario>()));
			lista.add(new Video("O71fetlkCZo", "Queen - We Are The Champions", "Esta es la descripción del vídeo de Queen - We Are The Champions", new ArrayList<Comentario>()));
			lista.add(new Video("EOKAnomhHRg", "Bleach Opening - Ranbu No Melody", "Esta es la descripción del vídeo de Bleach Opening - Ranbu No Melody", new ArrayList<Comentario>()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized VideoArrayDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new VideoArrayDAO();
		}

		return INSTANCE;
	}

	@Override
	public boolean insert(Video video) {
		boolean resul = false;
		if (video != null) {
			resul = lista.add(video);
		}
		return resul;
	}

	@Override
	public List<Video> getAll() {
		return lista;
	}

	@Override
	public Video getById(String id) {
		Video resul = null;
		if (id != null) {
			for (Video videoIteracion : lista) {
				if (id.equals(videoIteracion.getId())) {
					resul = videoIteracion;
					break;
				}
			}
		}
		return resul;
	}

	@Override
	public boolean update(Video videoUpdate) {
		boolean resul = false;
		Video videoIteracion = null;
		int i = 0;
		if (videoUpdate != null) {
			// Iterator
			Iterator<Video> it = lista.iterator();
			while (it.hasNext()) {
				videoIteracion = it.next();
				if (videoIteracion.getId() == videoUpdate.getId()) {
					lista.set(i, videoUpdate);
					resul = true;
					break;
				}
				i++;
			}
		}
		return resul;
	}

	@Override
	public boolean delete(String id) {
		boolean resul = false;
		Video vIteracion = null;
		if (id != null) {
			// buscar video a eliminar
			for (int i = 0; i < lista.size(); i++) {
				vIteracion = lista.get(i); // video sobre el que iteramos
				if (id.equals(vIteracion.getId())) { // video encontrado
					resul = lista.remove(vIteracion);
					break;
				}
			}
		}		
		return resul;
	}

	public int length() {
		return lista.size();
	}
}
