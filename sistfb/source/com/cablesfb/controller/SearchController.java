package com.cablesfb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.model.Client;
import com.cablesfb.model.Product;
import com.cablesfb.modeldao.ClientDAO;
import com.cablesfb.modeldao.ProductDAO;

/**
 * Servlet implementation class SearcherController
 */
@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");

		if (accion.equalsIgnoreCase("searchProduct")) {

			try {
				String search = request.getParameter("search");
				ProductDAO pdao = new ProductDAO();
				List<Product> pList = new ArrayList<Product>();
				pList = pdao.searchByOpt(search);
				request.getSession().setAttribute("search", pList);
				System.out.println("Busqueda completada");
				request.getRequestDispatcher("/search.jsp").forward(request, response);

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en busqueda");
				request.getRequestDispatcher("/principal.jsp").forward(request, response);
			}
		}else if (accion.equalsIgnoreCase("searchClient")) {
			try {
				String search = request.getParameter("search");
				ClientDAO cdao = new ClientDAO();
				List<Client> cList = new ArrayList<Client>();
				cList = cdao.search(search);
				request.getSession().setAttribute("search", cList);
				System.out.println("Busqueda completada");
				request.getRequestDispatcher("clientebuscador.jsp").forward(request, response);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la busqueda");
				request.getRequestDispatcher("principal.jsp").forward(request, response);
			}	
			}else if(accion.equalsIgnoreCase("searchClientToCreate")) {
				try {
					String search = request.getParameter("search");
					ClientDAO cdao = new ClientDAO();
					List<Client> cList = new ArrayList<Client>();
					cList = cdao.search(search);
					request.getSession().setAttribute("search", cList);
					System.out.println("Busqueda completada");
					request.getRequestDispatcher("ordencrear.jsp").forward(request, response);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					System.out.println("Error en la busqueda");
					request.getRequestDispatcher("principal.jsp").forward(request, response);
				}	
				
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
