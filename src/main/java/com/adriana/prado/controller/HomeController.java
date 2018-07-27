package com.adriana.prado.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adriana.prado.model.VideoArrayDAO;
import com.adriana.prado.pojo.Video;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String OP_ELIMINAR = "1";
	
	//parametros
	private static String id = "";
	private static String titulo = "";
	private static String op = "";
	//atributos
	private static String msg = "";
	private static VideoArrayDAO dao;
	private static ArrayList<Video> videos;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			id = request.getParameter("id");
			op = request.getParameter("op");
			
			if ( op != null && OP_ELIMINAR.equals(op) ) {
				dao.delete(id);
			}
			
			dao = VideoArrayDAO.getInstance();
			videos = (ArrayList<Video>) dao.getAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("videos", videos);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			msg = "";
			dao = VideoArrayDAO.getInstance();
			videos = (ArrayList<Video>) dao.getAll();
			
			id = request.getParameter("id");
			titulo = request.getParameter("titulo");
						
			if(id != null && titulo != null) {
				if(!id.equals("") && !titulo.equals("")){
					for(int i = 0; i<dao.getAll().size();i++) {
						if(dao.getById(id).equals(null)) {
							dao.insert(new Video(id, titulo));
						}else
							msg = "La canción a insertar ya está en la lista.";
					}
					
				}else
					msg = "Debe introducir un ID y un título.";
			}
		} catch (Exception e) {
			msg = "Error al introducir el video. El ID no contiene 11 caracteres.";
		} finally {			
			request.setAttribute("msg", msg);
			request.setAttribute("videos", videos);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

}
