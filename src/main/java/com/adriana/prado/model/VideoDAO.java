package com.adriana.prado.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.adriana.prado.pojo.Video;

public class VideoDAO implements Crudable<Video> {
	
	private static VideoDAO INSTANCE = null;
	
	private VideoDAO() {
		super();
	}
	
	public static synchronized VideoDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new VideoDAO();
		}

		return INSTANCE;
	}

	@Override
	public boolean insert(Video pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Video> getAll() {
		ArrayList<Video> videos = new ArrayList<Video>();
		try {
			//Obtener conexion bbdd
			Connection con =  ConnectionManager.getConnection();
			
			//Ejecutar sql
			String sql = "select id, codigo, nombre from video order by id desc;";
			PreparedStatement ps = con.prepareStatement(sql);
			
			//Obtener resultados
			ResultSet rs = ps.executeQuery();
			
			//Mapear ResultSet a ArrayList
			Video v;
			while(rs.next()) {
				v = new Video();
				v.setId(rs.getString("codigo"));
				v.setTitulo(rs.getString("nombre"));
				videos.add(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return videos;
	}

	@Override
	public Video getById(String id) {
		Video video = new Video();
		try {
			//Obtener conexion bbdd
			Connection con =  ConnectionManager.getConnection();
			
			//Ejecutar sql
			String sql = "select id, codigo, nombre from video where codigo = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			//Obtener resultados
			ResultSet rs = ps.executeQuery();
			
			//Mapear ResultSet al objeto o array objetos
			while( rs.next() ) {				
							
				video.setId( rs.getString("codigo")  );
				video.setTitulo(rs.getString("nombre"));							
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return video;
	}

	@Override
	public boolean update(Video pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
