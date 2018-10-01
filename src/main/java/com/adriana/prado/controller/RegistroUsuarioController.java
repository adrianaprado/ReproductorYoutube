package com.adriana.prado.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adriana.prado.model.UsuarioDAO;
import com.adriana.prado.pojo.Alert;
import com.adriana.prado.pojo.Usuario;

/**
 * Servlet implementation class RegistroUsuarioController
 */
@WebServlet("/registro")
public class RegistroUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String usuario = "";
	private static String pswd = "";
	private static String pswdRepe = "";
	
	private static UsuarioDAO dao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Alert alert = null;
		Usuario u = null;
		String vista = "registro.jsp";
		dao = UsuarioDAO.getInstance();
		
		try {
			usuario = request.getParameter("usuario");
			pswd = request.getParameter("pswd");
			pswdRepe = request.getParameter("pswdRepe");
			
			if(usuario != null && pswd != null && pswdRepe != null) {
				u = new Usuario(usuario, pswd);
				if(pswd.equals(pswdRepe)) {
					if(dao.insert(u)) {
						vista = "home.jsp";
						alert = new Alert(Alert.ALERT_SUCCESS, "Usuario registrado correctamente.");
					}else {
						alert = new Alert(Alert.ALERT_DANGER, "Este usuario ya esta registrado.");
					}
				}else {
					alert = new Alert(Alert.ALERT_DANGER, "Las contraseñas no coinciden.");
				}
			}else {
				alert = new Alert(Alert.ALERT_DANGER, "Debes rellenar todos los campos");
			}
			request.setAttribute("alert", alert);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher(vista).forward(request, response);
		}
	}

}
