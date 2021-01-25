package com.cablesfb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.model.Product;
import com.cablesfb.modeldao.ProductDAO;
@WebServlet("/stockadded")
public class InsertStockController extends HttpServlet {
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
		
			 Product p = new Product();
			 ProductDAO pdao = new ProductDAO();
			 p.setName(name);
			 p.setDiscountType(discountType);
			 p.setDisponibleMeters(0);
			 p.setMetersByType(metersByType);
			 p.setPrice(price);
			 p.setSku(sku);
			 p.setType(type);
			 p.setUnitys(unitys);
			 pdao.insert(p);
			 request.getSession().setAttribute("exito", "exito");
			 request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}

}
