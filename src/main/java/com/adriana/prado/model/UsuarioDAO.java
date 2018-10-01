package com.adriana.prado.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.adriana.prado.pojo.Usuario;
import com.mysql.jdbc.Statement;

public class UsuarioDAO implements Crudable<Usuario>{
	private static UsuarioDAO INSTANCE = null;
	private final String SQL_GET_ALL = "SELECT id, nombre, password FROM usuario ORDER BY id DESC LIMIT 1000";
	private final String SQL_GET_BY_ID = "SELECT nombre, password FROM usuario WHERE id = ? LIMIT 1000";
	private final String SQL_GET_BY_NOMBRE = "SELECT id, nombre, password FROM usuario WHERE nombre = ? LIMIT 1000";
	private final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, password = ? WHERE id = ?;";
	private final String SQL_INSERT = "INSERT INTO usuario (nombre, password) VALUES (?, ?);";
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
		try (Connection con =  ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
				){
			if(pojo != null) {
				ps.setString(1, pojo.getNombre());
				ps.setString(2, pojo.getContrasena());
				
				int affectedRows = ps.executeUpdate();
				
				if(affectedRows == 1) {
					//Conseguir id generado
					try (ResultSet rs = ps.getGeneratedKeys();) {
						while(rs.next()) {
							pojo.setId(rs.getLong(1));
							resul = true;
						}
					}
				}				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public List<Usuario> getAll() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try (Connection con =  ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = ps.executeQuery();
				){
			
			//Mapear ResultSet a ArrayList
			while(rs.next()) {
				usuarios.add(rowMapper(rs));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

	@Override
	public Usuario getById(String id2) {
		Long id = (long) 0;
		if(id2 != null) {
			id = Long.parseLong(id2);
		}
		Usuario u = null;
		try (Connection con =  ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_GET_BY_ID);
				){
			
			ps.setLong(1, id);
			
			try (ResultSet rs = ps.executeQuery();){
				//Obtener resultados
//				ResultSet rs = ps.executeQuery();
				
				//Mapear ResultSet al objeto o array objetos
				while( rs.next() ) {
					u = rowMapper(rs);					
				}	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	//Metodo extra para encontrar un usuario registrado por su nombre, ya que es UK
	public Usuario getByNombre(String nombre) {
		Usuario u = null;
		try (Connection con =  ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_GET_BY_NOMBRE);
				){
			
			ps.setString(1, nombre);
			
			try (ResultSet rs = ps.executeQuery();){
				//Obtener resultados
//				ResultSet rs = ps.executeQuery();
				
				//Mapear ResultSet al objeto o array objetos
				while( rs.next() ) {
					u = rowMapper(rs);					
				}	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public boolean update(Usuario pojo) {
		boolean resul = false;
		try (Connection con =  ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);){
				
				ps.setString(1, pojo.getNombre());
				ps.setString(2, pojo.getContrasena());
				ps.setLong(3, pojo.getId());
				
				int affectedRows = ps.executeUpdate();
				
				if(affectedRows == 1) {
					resul = true;
				}	
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		return resul;
	}

	@Override
	public boolean delete(String id2) {
		boolean resul = false;
		Long id = Long.parseLong(id2);
		
		try (Connection con =  ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_DELETE);){
			
			ps.setLong(1, id.longValue());
			
			int affectedRows = ps.executeUpdate();
			
			if(affectedRows == 1) {
				resul = true;
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resul;
	}

	private Usuario rowMapper(ResultSet rs) throws Exception{
		Usuario u = new Usuario();
		if(rs != null) {
			u.setId(rs.getLong("id"));
			u.setNombre( rs.getString("nombre"));
			u.setContrasena(rs.getString("password"));
		}
		return u;
	}
}
