package com.adriana.prado.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adriana.prado.pojo.Alert;
import com.adriana.prado.pojo.Usuario;
import com.adriana.prado.pojo.Video;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/login")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW_HOME = "home.jsp";
	
	//Parametros
	private static String user = "";
	private static String pswd = "";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Alert alert = new Alert();
		HttpSession session = request.getSession();
		
		try {
			user = request.getParameter("user");
			pswd = request.getParameter("pswd");
			
			if(user.equals("admin") && pswd.equals("admin")) {
				alert = new Alert(Alert.ALERT_PRIMARY, "Bienvenido "+user);
				
				//Guardar Usuario en session
				
				session.setAttribute("usuario", new Usuario(user, pswd));
				session.setMaxInactiveInterval(60*5); //5 minutos
			}else {
				alert = new Alert(Alert.ALERT_WARNING, "Credenciales incorrectas.");
			}
			
			session.setAttribute("alert", alert);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.setAttribute("alert", alert);
			//request.getRequestDispatcher("/").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/inicio");
		}	
	}
}
