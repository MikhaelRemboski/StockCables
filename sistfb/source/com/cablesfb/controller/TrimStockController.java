package com.cablesfb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.model.Product;
import com.cablesfb.modeldao.ProductDAO;

@WebServlet("/trim")
public class TrimStockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] list = request.getParameterValues("idRecorte");
		try {
			if (list.length == 2) {
				Product p1 = new Product();
				Product p2 = new Product();
				ProductDAO pdao = new ProductDAO();
				int id1 = Integer.parseInt(list[0]);
				int id2 = Integer.parseInt(list[1]);
				p1 = pdao.searchById(id1);
				p2 = pdao.searchById(id2);
				List<Product> pList = new ArrayList<Product>();
				pList.add(p1);
				pList.add(p2);
				request.getSession().setAttribute("productToTrim", pList);
				request.getRequestDispatcher("/recortar.jsp").forward(request, response);
				request.getSession().setAttribute("id1", id1);
				request.getSession().setAttribute("id2", id2);
			} else {
				request.getRequestDispatcher("/recorte.jsp").forward(request, response);

			}

		} catch (Exception e) {
			request.getRequestDispatcher("/recorte.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name1 = request.getParameter("name1");
		String name2 = request.getParameter("name2");
		String type1 = request.getParameter("type1");
		String type2 = request.getParameter("type2");
		int sku1 = Integer.parseInt(request.getParameter("sku1").toString());
		int sku2 = Integer.parseInt(request.getParameter("sku2").toString());
		double metersByType1 = Double.parseDouble(request.getParameter("metersbytype1").toString());
		double metersByType2 = Double.parseDouble(request.getParameter("metersbytype2").toString());
		double unitys1 = Double.parseDouble(request.getParameter("unitys1").toString());
		double unitys2 = Double.parseDouble(request.getParameter("unitys2").toString());
		int id1 = Integer.parseInt(request.getSession().getAttribute("id1").toString());
		int id2 = Integer.parseInt(request.getSession().getAttribute("id2").toString());
		request.getSession().removeAttribute("id1");
		request.getSession().removeAttribute("id2");
		try {
			ProductDAO pdao = new ProductDAO();
			Product p1 = pdao.searchById(id1);
			Product p2 = pdao.searchById(id2);
			p1.setName(name1);
			p2.setName(name2);
			p1.setType(type1);
			p2.setType(type2);
			p1.setSku(sku1);
			p2.setSku(sku2);
			p1.setMetersByType(metersByType1);
			p2.setMetersByType(metersByType2);
			p1.setUnitys(unitys1);
			p2.setUnitys(unitys2);

			pdao.modifyById(p1);
			pdao.modifyById(p2);
			request.getRequestDispatcher("/recorte.jsp").forward(request, response);;

		} catch (Exception e) {

		}
	}

}
