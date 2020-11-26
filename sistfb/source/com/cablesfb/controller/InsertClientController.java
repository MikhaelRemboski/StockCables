package com.cablesfb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.model.Client;
import com.cablesfb.modeldao.ClientDAO;

@WebServlet("/addclient")

public class InsertClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String name = request.getParameter("name");
		String adress = request.getParameter("adress");
		long cuit = Long.parseLong(request.getParameter("cuit"));
		String email = request.getParameter("email");
		String transport = request.getParameter("transport");
		try {
			Client c = new Client();
			c.setName(name);
			c.setAdress(adress);
			c.setCuit(cuit);
			c.setEmail(email);
			c.setTransport(transport);
			ClientDAO cdao = new ClientDAO();
			cdao.insert(c);
			request.getSession().setAttribute("exito", "exito");
			request.getRequestDispatcher("/clienteagregar.jsp").forward(request, response);
		} catch (Exception ex) {
			System.out.println("Error al cargar un clinete desde el controlador");
			System.out.println(ex.getMessage());
		}
		
		
	}

}
