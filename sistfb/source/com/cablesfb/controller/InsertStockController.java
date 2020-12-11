package com.cablesfb.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.model.Product;
import com.cablesfb.model.Registers;
import com.cablesfb.modeldao.ProductDAO;
import com.cablesfb.modeldao.RegistersDAO;
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
			 if(request.getParameter("sku") == null || name == null ||
					 request.getParameter("metersbytype") == null ||  
					 request.getParameter("unitys")== null || 
					 request.getParameter("price") == null 
					 || discountType == null || type == null) {
					response.getWriter().append("<h1>BOLUDO!!!!!! COMPLETA BIEN!!!! YA  TE EXPLIQUE COMO SE CARGAN LOS PRODUCTOS.<h1>"
							+ "<a class=\"btn btn-primary\" href=\"../sistfb/addproduct.jsp\" role=\"button\">intentar de nuevo</a");				 
				 
			 }
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
			 Date date = new Date();
			 Registers r = new Registers();
			 RegistersDAO rDAO = new RegistersDAO();
			 r.setAction("Agregar producto nuevo");
			 r.setDate(date);
			 r.setDescription("Se ha agregado el producto: " + p.toString() );
			 r.setUser(request.getSession().getAttribute("name").toString());
			 rDAO.insert(r);
			 request.getSession().setAttribute("exito", "exito");
			 request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
		} catch (Exception ex) {
			response.getWriter().append("<h1>BOLUDO!!!!!! COMPLETA BIEN!!!! YA  TE EXPLIQUE COMO SE CARGAN LOS PRODUCTOS.<h1>"
					+ "<a class=\"btn btn-primary\" href=\"../sistfb/addproduct.jsp\" role=\"button\">intentar de nuevo</a");
			System.out.println(ex.getMessage());
		}
		
		
	}


}
// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss"); para mostrar la fecha bonita luego formatter.format(fecha);