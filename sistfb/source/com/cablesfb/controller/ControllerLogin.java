package com.cablesfb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cablesfb.model.Person;
import com.cablesfb.modeldao.PersonDAO;

/**
 * Servlet implementation class ControllerLogin
 */
@WebServlet("/logued")
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonDAO pdao = new PersonDAO();
	Person p = new Person();
	int r = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		HttpSession session = request.getSession(true);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		p.setEmail(email);
		p.setPassword(password);
		r = pdao.validate(p);
		if (r == 1) {
			
			session.setAttribute("name", "perroraton");
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(0);
			request.getRequestDispatcher("/principal.jsp").forward(request, response);
			System.out.println(session.getId()+ "controller login");
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		}

		String close = request.getParameter("close");
		if(close != null) {
			session.invalidate();
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			System.out.println("CERRANDO SESION");
		}
	}
}
