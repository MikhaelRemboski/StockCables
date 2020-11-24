package com.cablesfb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.modeldao.ProductDAO;

@WebServlet("/stockmodify")
public class OptionStockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		String number = "";
		int id = 0;
		try {
			String texto = request.getParameter("modify");
			char c = texto.charAt(0);
			if(c == 'd') {
				number = texto.substring(6);
				id = Integer.parseInt(number);
				ProductDAO pdaod = new ProductDAO();
				pdaod.delete(id);
				request.getRequestDispatcher("/principal.jsp").forward(request, response);
			}else if( c == 's'){
				number = texto.substring(11);
				id = Integer.parseInt(number);	
				request.getSession().setAttribute("idstockmodify", id);
				request.getRequestDispatcher("/stockmodify").forward(request, response);
			}else {
				number = texto.substring(13);
				id = Integer.parseInt(number);	

				request.getSession().setAttribute("idstockmodify", id);
				request.getRequestDispatcher("/modifyproduct.jsp").forward(request, response);
			}
			
			
		} catch (Exception e) {
		}
	}

}
