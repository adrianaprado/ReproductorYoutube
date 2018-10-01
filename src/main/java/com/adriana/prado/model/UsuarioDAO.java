package com.adriana.prado.model;

import java.util.ArrayList;
import java.util.List;

import com.adriana.prado.pojo.Usuario;

public class UsuarioDAO implements Crudable<Usuario>{
	private static UsuarioDAO INSTANCE = null;
	private final String SQL_GET_ALL = "SELECT nombre, contrasena FROM usuario ORDER BY id DESC LIMIT 1000";
	private final String SQL_GET_BY_ID = "SELECT nombre, contrasena FROM usuario WHERE id = ? LIMIT 1000";
	private final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, contrasena = ? WHERE id = ?;";
	private final String SQL_INSERT = "INSERT INTO usuario (nombre, contrasena) VALUES (?, ?);";
	private final String SQL_DELETE = "DELETE FROM usuario WHERE id = ?;";
	
	private UsuarioDAO() {
		super();
	}
	
	public static synchronized UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}

		return INSTANCE;
	}

	@Override
	public boolean insert(Usuario pojo) {
		boolean resul = false;
		return resul;
	}

	@Override
	public List<Usuario> getAll() {
		ArrayList<Usuario> videos = new ArrayList<Usuario>();
		return null;
	}

	@Override
	public Usuario getById(String id) {
		
		return null;
	}

	@Override
	public boolean update(Usuario pojo) {
		boolean resul = false;
		return resul;
	}

	@Override
	public boolean delete(String id) {
		boolean resul = false;
		return false;
	}
}
