package com.cablesfb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.model.Product;
import com.cablesfb.modeldao.ProductDAO;

@WebServlet("/modifystock")
public class ModifyStockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		int id = 0;
		int sku = 0;
		String name = "";
		double metersByType = 0;
		double unitys = 0;
		double price = 0;
		String discountType = "";
		String type = "";
		try {
			String idString = request.getSession().getAttribute("idstockmodify").toString();
			id = Integer.parseInt(idString);
			sku = Integer.parseInt(request.getParameter("sku"));
			name = request.getParameter("name");
			metersByType = Double.parseDouble(request.getParameter("metersbytype"));
			unitys = Double.parseDouble(request.getParameter("unitys"));
			price = Double.parseDouble(request.getParameter("price"));
			discountType = request.getParameter("discounttype");
			type = request.getParameter("type");
			 if(request.getParameter("sku") == null || name == null ||
					 request.getParameter("metersbytype") == null ||  
					 request.getParameter("unitys")== null || 
					 request.getParameter("price") == null 
					 || discountType == null || type == null) {
					response.getWriter().append("<h1>BOLUDO!!!!!! COMPLETA BIEN!!!! YA  TE EXPLIQUE COMO SE CARGAN LOS PRODUCTOS.<h1>"
							+ "<a class=\"btn btn-primary\" href=\"../sistfb/modifyproduct.jsp\" role=\"button\">intentar de nuevo</a");				 
				 
			 }
			Product p = new Product();
			ProductDAO pdao = new ProductDAO();
			p.setId(id);
			p.setName(name);
			p.setDiscountType(discountType);
			p.setMetersByType(metersByType);
			p.setPrice(price);
			p.setSku(sku);
			p.setType(type);
			p.setUnitys(unitys);
			pdao.modifyById(p);
			request.getSession().setAttribute("exito", "exito");
			request.getRequestDispatcher("/modifyproduct.jsp").forward(request, response);
			System.out.println(p.getName());
		} catch (Exception ex) {
			response.getWriter().append("<h1>BOLUDO!!!!!! COMPLETA BIEN!!!! YA  TE EXPLIQUE COMO SE CARGAN LOS PRODUCTOS.<h1>"
					+ "<a class=\"btn btn-primary\" href=\"../sistfb/modifyproduct.jsp\" role=\"button\">intentar de nuevo</a");				 
		 
			System.out.println(ex.getMessage());
		}

	}

}
