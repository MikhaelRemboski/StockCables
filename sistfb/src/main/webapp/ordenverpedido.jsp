<%@page import="com.cablesfb.helper.Rounder"%>
<%@page import="com.cablesfb.modeldao.ProductDAO"%>
<%@page import="com.cablesfb.model.Product"%>
<%@page import="com.cablesfb.helper.Discounter"%>
<%@page import="com.cablesfb.model.Client"%>
<%@page import="com.cablesfb.modeldao.ClientDAO"%>
<%@page import="com.cablesfb.modeldao.OrderDAO"%>
<%@page import="com.cablesfb.model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>
<body>
	<%
		System.out.println(session.getId() + "principal");
	if (session.getAttribute("email") == null && session.getAttribute("name") == null) {
		out.print("<script>location.replace('/sistfb/index.jsp');</script>");
	}
	Long orderId = Long.parseLong(request.getSession().getAttribute("orderIdToSee").toString());
	OrderDAO oDAO = new OrderDAO();
	Order o = new Order();

	//Lista de productos a mostrar:
	List<Order> orderList = new ArrayList<Order>();
	orderList = oDAO.getOrdersByOrderId(orderId);

	o = orderList.get(0);

	//cliente:
	ClientDAO cDAO = new ClientDAO();
	Client c = cDAO.searchById(o.getClientId());

	List<Product> productL = new ArrayList<Product>();
	for (Order aux : orderList) {
		Product p = new Product();
		ProductDAO pDAO = new ProductDAO();
		p = pDAO.searchById(aux.getProductId());
		productL.add(p);
	}
	%>





	<!-- navbar abajo -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="../sistfb/principal.jsp">Inicio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="../inicio/stock.html"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Pedidos </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="../sistfb/ordenver.jsp">Ver
							Pedidos</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="../sistfb/ordencrear.jsp">Cargar
							Pedidos</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Control Stock </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="../sistfb/principal.jsp">Ver
							Stock</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="../sistfb/addproduct.jsp">Agregar
							Producto</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="../sistfb/recorte.jsp">Recorte</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="../inicio/stock.html"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Clientes </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="../sistfb/clienteagregar.jsp">Cargar
							Clientes</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="../sistfb/clientever.jsp">Ver
							Clientes</a>
					</div></li>
			</ul>

			<ul class="navbar-nav mr-right">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="../sistfb/inicio/stock.html"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Mi Cuenta </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item"> <%=request.getSession().getAttribute("email")%></a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item"> <%=request.getSession().getAttribute("name")%></a>
						<div class="dropdown-divider"></div>
						<form action="logued" method="POST">
							<button type="submit" class="btn btn-link" name="close"
								value="close">cerrar sesion</button>
						</form>
					</div></li>
			</ul>
		</div>

	</nav>
	<br>

	<!--  tabla abajo -->


	<div class="container">
		<table class="table-borderless">

			<thead>

				<tr>
					<th scope="row"></th>

					<th scope="row"></th>
					<th scope="row"></th>
					<th scope="row"></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>Cliente:</th>
					<td><%=c.getName()%></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>Direccion:</th>
					<td><%=c.getAdress()%></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><%=c.getEmail()%></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>

	</div>
	<br>
	<br>

	<form action="order" method="post" class="form-inline">
		<table class="table table-borderless">
			<thead>
				<tr>
					<th scope="col">Seleccionar</th>
					<th scope="col">Id de Producto</th>
					<th scope="col">Descripcion</th>
					<th scope="col">Tipo</th>
					<th scope="col">Metros por unidad</th>
					<th scope="col">Unidades</th>
					<th scope="col">Precio</th>
				</tr>
			</thead>
			<tbody>
				<%
					double price = 0;
				double totalPrice = 0;
				String discountType = "";
				int cont = 0;
				for (Product aux : productL) {
					Product p = aux;
					price = p.getPrice();
					discountType = p.getDiscountType();
					price = Discounter.discount(discountType, price) * o.getUnitys() * p.getMetersByType();
					totalPrice += price;
					p.setPrice(price);
					price = Rounder.roundByFourZeroes(price);
					totalPrice = Rounder.roundByFourZeroes(totalPrice);
					o = orderList.get(cont);
				
				%>
				<tr>
					<th scope="row">
						<div>
							<input class="form-check-input-left" name="idProduct"
								type="checkbox" value=<%=p.getId()%> id="defaultCheck1">
						</div>
					</th>
					<td><%=p.getId()%></td>
					<td><%=p.getName()%></td>
					<td><%=p.getType()%></td>
					<td><%=p.getMetersByType()%></td>
					<td><%=o.getUnitys()%></td>
					<td><%=price%></td>
				</tr>
				<%
				cont++;
				}
				%>
				<tr>
					<th scope="row"><button type="submit" name="accion"
							value="deleteproducts"
							class="btn btn-outline-success my-2 my-sm-0">Eliminar
							Productos</button></th>
					<td><button type="submit" name="accion"
							value=<%="addproducts" + c.getId()%>
							class="btn btn-outline-success my-2 my-sm-0">Agregar
							Productos</button></td>
					<td></td>
					<td></td>
					<td></td>
					<td><strong>TOTAL:</strong></td>
					<td><%=totalPrice%></td>
				</tr>
			</tbody>
		</table>
	</form>



	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="tableToExcel.js"></script>
</body>
</html>