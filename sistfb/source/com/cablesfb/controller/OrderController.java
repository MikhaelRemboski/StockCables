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
import com.cablesfb.helper.Rounder;
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
		String recortedAcccion = accion.substring(0, 10);
		System.out.println(recortedAcccion);
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
				price += p.getPrice() * unitysPerProduct * p.getMetersByType();
				Rounder.roundByFourZeroes(price);
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

		} else if (accion.equalsIgnoreCase("modifyOrder")) {
			String modifyAction = request.getParameter("modify");
			OrderDAO oDAO = new OrderDAO();
			if (modifyAction.charAt(0) == 'd') {// delete
				String orderIdStr = modifyAction.substring(6);
				long orderId = Integer.parseInt(orderIdStr);
				oDAO.deleteByOrderId(orderId);
				request.getRequestDispatcher("ordenver.jsp").forward(request, response);

			} else if (modifyAction.charAt(0) == 's') {// seeorder
				String orderIdStr = modifyAction.substring(8);
				request.getSession().setAttribute("orderIdToSee", orderIdStr);
				request.getRequestDispatcher("ordenverpedido.jsp").forward(request, response);

			} else if (modifyAction.charAt(0) == 'c') {// clientmodify
				String orderIdStr = modifyAction.substring(12);
				long orderId = Integer.parseInt(orderIdStr);
				request.getSession().setAttribute("orderIdToModifyClient", orderId);
				request.getSession().removeAttribute("clientOrder");
				request.getRequestDispatcher("ordencrear.jsp").forward(request, response);

			} else if (modifyAction.charAt(0) == 'p') {// packoff
				List<Order> oList = new ArrayList<Order>();
				String orderIdStr = modifyAction.substring(7);
				long orderId = Integer.parseInt(orderIdStr);
				oList = oDAO.getOrdersByOrderId(orderId);
				for (Order aux : oList) {
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
		} else if (accion.equalsIgnoreCase("clientModify")) {
			try {
				long orderId = Integer.parseInt(request.getSession().getAttribute("orderIdToModifyClient").toString());
				int clientId = Integer.parseInt(request.getParameter("idClient"));
				OrderDAO oDAO = new OrderDAO();
				oDAO.modifyClient(clientId, orderId);
				request.getSession().removeAttribute("orderIdToModifyClient");
				request.getRequestDispatcher("ordenver.jsp").forward(request, response);

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				request.getRequestDispatcher("ordenver.jsp").forward(request, response);

			}

		} else if (accion.equalsIgnoreCase("deleteproducts")) {
			try {
				String[] idProducts = request.getParameterValues("idProduct");
				Long orderId = Long.parseLong(request.getSession().getAttribute("orderIdToSee").toString());
				for (String aux : idProducts) {
					int productId = Integer.parseInt(aux);
					OrderDAO oDAO = new OrderDAO();
					oDAO.deleteByProductId(orderId, productId);
				}
				request.getRequestDispatcher("ordenverpedido.jsp").forward(request, response);

			} catch (Exception ex) {
				request.getRequestDispatcher("ordenverpedido.jsp").forward(request, response);
				System.out.println(ex.getMessage());
			}
		} 
		else if (recortedAcccion.equalsIgnoreCase("addproduct")) {
			try {
				int idClient = Integer.parseInt(accion.substring(11));
				Client c = new Client();
				ClientDAO cdao = new ClientDAO();
				c = cdao.searchById(idClient);
				request.getSession().setAttribute("clientOrder", c);

				request.getRequestDispatcher("ordencrear.jsp").forward(request, response);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} 
		else if (accion.equalsIgnoreCase("productFinishAdd")) {
			try {
				Long orderId = Long.parseLong(request.getSession().getAttribute("orderIdToSee").toString());
				String idProductStr[] = request.getParameterValues("idProduct");
				System.out.println("el id del producto es " + idProductStr[0]);
				String unitysOrderStr[] = request.getParameterValues("unitysOrder");
				System.out.println("La cantidad de unidades del producto es: " + unitysOrderStr[0]);
				if (idProductStr.length < 1) {
					request.getRequestDispatcher("/ordencrear.jsp").forward(request, response);

				}
				OrderDAO oDAO = new OrderDAO();
				Order o = new Order();
				o = oDAO.getOrderByOrderId(orderId);
				System.out.println(o.toString());
				ArrayList<Integer> unitysOrderInt = new ArrayList<Integer>();
				for(String aux : unitysOrderStr) {
					if(aux != "") {
						int idProduct = Integer.parseInt(aux);
						unitysOrderInt.add(idProduct);
					}
				}

				
				Double actuallyPrice = o.getTotalPrice();

				for (int i = 0; i < idProductStr.length; i++) {
					Order oNew = new Order();
					oNew = o;
					int unitysOrder = unitysOrderInt.get(i);
					System.out.println(unitysOrder);
					int idProduct = Integer.parseInt(idProductStr[i]);
					ProductDAO pdao = new ProductDAO();
					Product p = new Product();
					p = pdao.searchById(idProduct);
					oNew.setProductDescription(p.getName());
					oNew.setUnitys(unitysOrder);
					oNew.setProductId(idProduct);
					Double priceToSum = Discounter.discount(p.getDiscountType(), p.getPrice());
					actuallyPrice += priceToSum;
					System.out.println(oNew.toString());
					oDAO.insertOrder(o);
				}
				oDAO.updateTotalPrice(orderId, actuallyPrice);
				request.getSession().removeAttribute("clientOrder");
				request.getRequestDispatcher("ordenverpedido.jsp").forward(request, response);
			} catch (Exception ex) {
				System.out.println("error" + ex.getMessage());
				request.getRequestDispatcher("ordenverpedido.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
