package com.cablesfb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.model.Client;
import com.cablesfb.modeldao.ClientDAO;

@WebServlet("/clientmodify")
public class ModifyClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		String accion = request.getParameter("accion");
		System.out.println(accion);
		if (accion.equalsIgnoreCase("modifyclient")) {
			String idStr = request.getParameter("modify");
			char c = idStr.charAt(0);
			if (c == 'm') {
				int id = Integer.parseInt(idStr.substring(12));
				request.getSession().setAttribute("idClientModify", id);
				System.out.println(id);
				request.getRequestDispatcher("/clientemodificar.jsp").forward(request, response);
			}else if(c == 'd') {
				int id = Integer.parseInt(idStr.substring(6));
				System.out.println(id);
				ClientDAO cdao = new ClientDAO();
				cdao.deleteById(id);
				request.getRequestDispatcher("/clientever.jsp").forward(request, response);
				
				
			}

		} else if (accion.equalsIgnoreCase("modifyclientexecute")) {
			try {
				String name = request.getParameter("name");
				long cuit = Long.parseLong(request.getParameter("cuit"));
				String email = request.getParameter("email");
				String adress = request.getParameter("adress");
				String transport = request.getParameter("transport");
				Client c = new Client();
				ClientDAO cdao = new ClientDAO();
				c.setName(name);
				c.setCuit(cuit);
				c.setEmail(email);
				c.setAdress(adress);
				c.setTransport(transport);
				c.setId(Integer.parseInt(request.getSession().getAttribute("idClientModify").toString()));
				cdao.update(c);
				request.getSession().setAttribute("exito", "exito");
				request.getRequestDispatcher("/clientemodificar.jsp").forward(request, response);
				
				

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				request.getRequestDispatcher("/principal.jsp").forward(request, response);
			}

		}

	}
}
