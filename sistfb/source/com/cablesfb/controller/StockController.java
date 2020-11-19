package com.cablesfb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/stockadded")
public class StockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
			int sku = 0;
			String name = "";
			double metersByType= 0;
			double unitys = 0;
			double price = 0;
			String discountType = "";
			String type = "";
		try {
			 sku = Integer.parseInt(request.getParameter("sku"));
			 name = request.getParameter("name");
			 metersByType = Double.parseDouble(request.getParameter("metersbytype"));
			 unitys = Double.parseDouble(request.getParameter("unitys"));
			 price = Double.parseDouble(request.getParameter("price"));
			 discountType = request.getParameter("discounttype");
			 type = request.getParameter("type");

		} catch (Exception ex) {
			response.getWriter().append("<h1>BOLUDO!!!!!! COMPLETA BIEN!!!! YA  TE EXPLIQUE COMO SE CARGAN LOS PRODUCTOS.<h1>");
			System.out.println(ex.getMessage());
		}
		
		System.out.println(sku + name + metersByType + unitys + price + discountType + type);
		
		
		
	}

}
