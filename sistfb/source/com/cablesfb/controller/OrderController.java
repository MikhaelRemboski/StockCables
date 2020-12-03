package com.cablesfb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cablesfb.helper.Discounter;
import com.cablesfb.model.Client;
import com.cablesfb.model.Order;
import com.cablesfb.model.Product;
import com.cablesfb.modeldao.ClientDAO;
import com.cablesfb.modeldao.OrderDAO;
import com.cablesfb.modeldao.ProductDAO;

@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		if (accion.equalsIgnoreCase("clientSelect")) {
			try {

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

			} catch (Exception ex) {
				request.getSession().removeAttribute("clientOrder");
				request.getSession().removeAttribute("productOrder");
				request.getRequestDispatcher("principal.jsp").forward(request, response);
			}

		} else if (accion.equalsIgnoreCase("productSelect")) {
			try {
				String idProductStr[] = request.getParameterValues("idProduct");
				if (idProductStr.length < 1) {
					request.getRequestDispatcher("/ordencrear.jsp").forward(request, response);

				}
				List<Product> list = new ArrayList<Product>();
				for (String aux : idProductStr) {
					int idProduct = Integer.parseInt(aux);
					ProductDAO pdao = new ProductDAO();
					Product p = new Product();
					p = pdao.searchById(idProduct);
					list.add(p);
				}
				request.getSession().setAttribute("productOrder", list);
				request.getRequestDispatcher("ordenfinalizar.jsp").forward(request, response);

			} catch (Exception ex) {
				request.getSession().removeAttribute("clientOrder");
				request.getSession().removeAttribute("productOrder");
				request.getRequestDispatcher("principal.jsp").forward(request, response);

			}

		} else if (accion.equalsIgnoreCase("finishorder")) {
			OrderDAO oDAO = new OrderDAO();
			Order oMaxNumber = oDAO.getMaxOrder();
			long orderId = oMaxNumber.getOrderId() + 1;
			String[] unitysOrderStr = request.getParameterValues("unitysOrder");
			List<Product> list = new ArrayList<Product>();
			list = (List<Product>) request.getSession().getAttribute("productOrder");
			int i = 0;
			Client c = new Client();
			c = (Client) request.getSession().getAttribute("clientOrder");
			int clientId = c.getId();
			double price = 0;
			for (String aux2 : unitysOrderStr) {
				Product p = new Product();
				p = list.get(i);
				double unitysPerProduct = Double.parseDouble(aux2);
				price += p.getPrice() * unitysPerProduct;
				System.out.println(price + " " + p.getPrice() + " " + unitysPerProduct + " " + p.getType());
				i++;
			}
			i = 0;
			for (String aux : unitysOrderStr) {
				Product p = new Product();
				p = list.get(i);
				int unitysPerProduct = Integer.parseInt(aux);
				String descriptionProduct = p.getName();
				int idProduct = p.getId();
				String state = "reservado";
				Order o = new Order();
				o.setOrderId(orderId);
				o.setProductDescription(descriptionProduct);
				o.setProductId(idProduct);
				o.setState(state);
				o.setUnitys(unitysPerProduct);
				o.setClientId(clientId);
				o.setTotalPrice(price);
				oDAO.insertOrder(o);
				i++;
			}
			request.getSession().removeAttribute("clientOrder");
			request.getSession().removeAttribute("productOrder");
			request.getRequestDispatcher("principal.jsp").forward(request, response);

		}else if(accion.equalsIgnoreCase("modifyOrder")) {
			String modifyAction = request.getParameter("modify");
			OrderDAO oDAO = new OrderDAO();
			if(modifyAction.charAt(0)=='d') {
				String orderIdStr = modifyAction.substring(6);
				long orderId = Integer.parseInt(orderIdStr);
				oDAO.deleteByOrderId(orderId);
				request.getRequestDispatcher("ordenver.jsp").forward(request, response);
			}else if(modifyAction.charAt(0) == 'm') {
				
			}else if(modifyAction.charAt(0) == 'c') {
				
			}else if(modifyAction.charAt(0) == 'p') {
				List<Order> oList = new ArrayList<Order>();
				String orderIdStr = modifyAction.substring(7);
				long orderId  = Integer.parseInt(orderIdStr);
				oList = oDAO.getOrdersByOrderId(orderId);
				for(Order aux : oList) {
					Order o = aux;
					ProductDAO pDAO = new ProductDAO(); 
					int productId = o.getProductId();
					Product p = pDAO.searchById(productId);
					double unitys = p.getUnitys() - o.getUnitys();
					p.setUnitys(unitys);
					pDAO.modifyById(p);
				}
				oDAO.packOffOrder(orderId);
				request.getRequestDispatcher("ordenver.jsp").forward(request, response);
				
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
