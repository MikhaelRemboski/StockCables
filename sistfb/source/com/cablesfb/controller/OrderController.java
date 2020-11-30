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

@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		if (accion.equalsIgnoreCase("clientSelect")) {
			String idClientStr[] = request.getParameterValues("idClient");
			if (idClientStr.length != 1) {
				request.getRequestDispatcher("/ordencrear.jsp").forward(request, response);
			}
			int idClient = Integer.parseInt(idClientStr[0]);
			Client c = new Client();
			ClientDAO cdao = new ClientDAO();
			c = cdao.searchById(idClient);
			request.getSession().setAttribute("clientOrder", c);
			request.getRequestDispatcher("/ordencrear.jsp").forward(request, response);
			
			
			
			
		} else if (accion.equalsIgnoreCase("productSelect")) {
			String idProductStr[] = request.getParameterValues("idProduct");
			if (idProductStr.length < 1) {
				request.getRequestDispatcher("/ordencrear.jsp").forward(request, response);
			
			}
			List<Product> list = new ArrayList<Product>();
			for(String aux : idProductStr) {
				int idProduct = Integer.parseInt(aux);
				ProductDAO pdao = new ProductDAO();
				Product p = new Product();
				p = pdao.searchById(idProduct);
				list.add(p);		
			}
			request.getSession().setAttribute("productOrder", list);
			request.getRequestDispatcher("/ordenfinalizar.jsp").forward(request, response);
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
