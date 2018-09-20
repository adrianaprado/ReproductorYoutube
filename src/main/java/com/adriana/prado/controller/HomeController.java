package com.adriana.prado.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adriana.prado.model.ComentarioArrayDAO;
import com.adriana.prado.model.VideoArrayDAO;
import com.adriana.prado.pojo.Video;
import com.adriana.prado.pojo.Alert;
import com.adriana.prado.pojo.Comentario;
import com.adriana.prado.pojo.Usuario;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/inicio")
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
	private static ComentarioArrayDAO daoComentarios;
	private static ArrayList<Video> videos;
	private static ArrayList<Comentario> comentarios;
	private Video videoInicio;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//Se ejecuta solo con la primera peticion. El resto van al service
		super.init(config);
		dao = VideoArrayDAO.getInstance();
		daoComentarios = ComentarioArrayDAO.getInstance();
	}
	
	@Override
	public void destroy() {
		//Se ejecuta al parar el servidor
		super.destroy();
		dao = null;
	}
	
	/**
	 * Cada request de un cliente se ejecuta en un hilo (thread)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Estas lineas se ejecutaran antes de GET o POST
		System.out.println("Antes de realizar Get o post");
		
		//Gestionar cookies de ultima visita
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date fechaActual = new Date();
//		System.out.println(dateFormat.format(fechaActual));
//		Cookie cVisita = new Cookie("cVisita", dateFormat.format(new Date()));
		Cookie cVisita = new Cookie("cVisita", URLEncoder.encode(dateFormat.format(new Date()), "UTF-8") );
		cVisita.setMaxAge(60*60*24*365); //Un año
		resp.addCookie(cVisita);
		
		//Recuperar todas las cookies
		Cookie[] cookies = req.getCookies();
		
		HttpSession session = req.getSession();
		
		
//		for(int i = 0; i < cookies.length; i++) {
//			System.out.println(cookies[i].getName());
//			if(cookies[i].getName().equals("cSesion")) {
//				Cookie cookie = cookies[i];
//				if(cookie.getMaxAge() == 0) {
//					System.out.println("Cierra sesión");
//				}
//				String s = cookies[i].getValue();
//				
//				String nombre = s.substring(0, s.indexOf("-"));
//				String pswd =  s.substring(s.indexOf("-")+1);
//				session.setAttribute("usuario", new Usuario(nombre, pswd));
//			}
//		}
		
		super.service(req, resp); //llama a los metodos GET y POST
		
		//despues de Get y Post
		req.setAttribute("videos", videos);
		req.setAttribute("videoInicio", videoInicio);
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			id = request.getParameter("id");
			op = request.getParameter("op");
			
			
			//Eliminar video
			if ( op != null && OP_ELIMINAR.equals(op) ) {
				dao.delete(id);
			}
			
			//listar videos
			comentarios = (ArrayList<Comentario>) daoComentarios.getAll();
			videos = (ArrayList<Video>) dao.getAll();
			
			anyadirComentarios(videos, comentarios);
			
			
			//Video inicio
			videoInicio = new Video();
			
			if(id!= null && !OP_ELIMINAR.equals(op)) {
				videoInicio = dao.getById(id);
				
				//Guardar video reproducido si el user esta en sesion
				HttpSession session = request.getSession();
				Usuario usuario = (Usuario) session.getAttribute("usuario");
				
				//Si esta loggeado
				if(usuario != null) {
					ArrayList<Video> videosReproducidos = (ArrayList<Video>) session.getAttribute("videosVistos");
					
					if(videosReproducidos == null ) {
						videosReproducidos = new ArrayList<Video>();
					}
					
					videosReproducidos.add(videoInicio);
					session.setAttribute("videosVistos", videosReproducidos);				
					
				}
				
			}else if(!videos.isEmpty()) {
				videoInicio = videos.get(0);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	private void anyadirComentarios(ArrayList<Video> videos2, ArrayList<Comentario> comentarios2) {
		Video video;
		Comentario comentario;
		for(int i = 0;i < videos2.size() ; i++) {
			for(int j = 0; j < comentarios2.size(); j++) {
				video = videos2.get(i);
				comentario = comentarios2.get(j);
				System.out.println("Id Video: " + video.getId() + ", Id Comentario-Video: " + comentario.getIdVideo());
				if(video.getId().equals(comentarios2.get(j).getIdVideo())) {
					videos2.get(i).getComentarios().add(comentario);
				}
			}
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
			videos = (ArrayList<Video>) dao.getAll();
			
			id = request.getParameter("id");
			titulo = request.getParameter("titulo");
						
			if(id != null && titulo != null) {
				if(!id.equals("") && !titulo.equals("")){
					for(int i = 0; i<dao.getAll().size();i++) {
						if(dao.getById(id).equals(null)) {
							Alert alert = new Alert(Alert.ALERT_SUCCESS, "Registro dado de alta correctamente.");
							request.setAttribute("alert", alert);
							dao.insert(new Video(id, titulo));
						}else
							msg = "La canción a insertar ya está en la lista.";
							request.setAttribute("alert", new Alert(Alert.ALERT_WARNING , "La canción a insertar ya existe.") );
					}
					
				}else
					msg = "Debe introducir un ID y un título.";
			}
		} catch (Exception e) {
			request.setAttribute("alert", new Alert(Alert.ALERT_WARNING , 
					"Error al introducir el video. El ID no contiene 11 caracteres."));
			msg = "Error al introducir el video. El ID no contiene 11 caracteres.";
		} finally {			
			request.setAttribute("msg", msg);
			request.setAttribute("videos", videos);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

}
