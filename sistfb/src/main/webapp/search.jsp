<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.cablesfb.helper.Rounder"%>
<%@page import="com.cablesfb.helper.Discounter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.cablesfb.modeldao.ProductDAO"%>
<%@page import="com.cablesfb.model.Product"%>
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
						<a class="dropdown-item" href="#">Ver Pedidos</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Cargar Pedidos</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Despachar Pedidos</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Modificar Pedidos</a>
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
			</ul>

			<form action="search" method="GET" class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Nombre, ID o sku" aria-label="Search" name="search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar
					Producto</button>
			</form>
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
	<table class="table" id="testTable">
		<thead class="thead-dark">
			<tr>
				<th scope="col">id</th>
				<th scope="col">sku</th>
				<th scope="col">nombre</th>
				<th scope="col">tipo</th>
				<th scope="col">metros por tipo</th>
				<th scope="col">Unidades</th>
				<th scope="col">metros totales</th>
				<th scope="col">metros totales disp</th>
				<th scope="col">Precio por metro</th>
				<th scope="col">Precio total</th>
				<th scope="col">Accion</th>
			</tr>
		</thead>
		<tbody>


			<%
				List<Product> productL = new ArrayList<Product>();
			productL = (List<Product>) session.getAttribute("search");

			double price = 0;
			String discountType = "";

			double totalMeters = 0;
			double totalAllMeters = 0;

			double totalPrice = 0;
			double totalAllPrice = 0;

			for (int i = 0; i < productL.size(); i++) {
				Product p = new Product();
				p = productL.get(i);
				price = p.getPrice();
				discountType = p.getDiscountType();
				price = Discounter.discount(discountType, price);

				totalMeters = p.getMetersByType() * p.getUnitys();
				totalAllMeters += totalMeters;

				totalPrice = price * totalMeters;
				totalPrice = Rounder.roundByFourZeroes(totalPrice);
				totalAllPrice += totalPrice;
			%>
			<tr>
				<th scope="row"><%=p.getId()%></th>
				<td><%=p.getSku()%></td>
				<td><%=p.getName()%></td>
				<td><%=p.getType()%></td>
				<td><%=p.getMetersByType()%> mts</td>
				<td><%=p.getUnitys()%></td>
				<td><%=totalMeters%> mts</td>
				<td>Not Working yet</td>
				<td><%=price%> usd</td>
				<td><%=totalPrice%> usd</td>
				<td>
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> Opciones </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<form action="stockmodify" method="POST">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="modify"
											id="inlineRadio1" value=<%="delete"+p.getId()%>> <label
											class="form-check-label" for="inlineRadio1">Eliminar</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="modify"
											id="inlineRadio2" value=<%="productmodify"+p.getId()%>> <label
											class="form-check-label" for="inlineRadio2">Modificar producto</label>
									</div>
									<div class="col-auto my-1">
										<button type="submit" class="btn btn-primary">Aceptar</button>
									</div>
								</form>
							</div></li>
					</ul>
				</td>
			</tr>
			<%
				}
				
				totalAllPrice = Rounder.roundByFourZeroes(totalAllPrice);
				session.removeAttribute("search");
			%>
			<tr>
				<th scope="row"></th>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><strong>TOTAL: </strong></td>
				<td><%=totalAllMeters%> mts</td>
				<td></td>
				<td><strong>TOTAL: </strong></td>
				<td><%=totalAllPrice%> usd</td>
				<td><input type="button"
					onclick="tableToExcel('testTable', 'W3C Example Table')"
					value="Exportar a Excel">
					</td>
			</tr>

		</tbody>
	</table>
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