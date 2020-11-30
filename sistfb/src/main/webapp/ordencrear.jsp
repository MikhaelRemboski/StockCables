<%@page import="com.cablesfb.model.Product"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.cablesfb.modeldao.ProductDAO"%>
<%@page import="com.cablesfb.helper.Rounder"%>
<%@page import="com.cablesfb.helper.Discounter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cablesfb.model.Client"%>
<%@page import="java.util.List"%>
<%@page import="com.cablesfb.modeldao.ClientDAO"%>
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

			<form action="search" method="GET" class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Nombre, ID o Cuit" aria-label="Search" name="search">
				<button class="btn btn-outline-success my-2 my-sm-0" name="accion"
					value="searchClient" type="submit">Buscar Cliente</button>
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
	<%
		if (request.getSession().getAttribute("clientOrder") == null) {
	%>
	<form method="GET" action="order">
		<table class="table" id="testTable">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Seleccionar</th>
					<th scope="col">Nombre</th>
					<th scope="col">Cuit</th>
					<th scope="col">Direccion</th>
					<th scope="col">Correo electronico</th>
				</tr>
			</thead>
			<tbody>
				<%
					ClientDAO cdao = new ClientDAO();
				List<Client> listC = new ArrayList<Client>();
				listC = cdao.selectAll();

				for (int i = 0; i < listC.size(); i++) {
					Client c = new Client();
					c = listC.get(i);
				%>
				<tr>
					<th scope="row">
						<div class="form-check">
							<input class="form-check-input" name="idClient" type="checkbox"
								value=<%=c.getId()%> id="defaultCheck1">
						</div>
					</th>
					<td><%=c.getName()%></td>
					<td><%=c.getCuit()%></td>
					<td><%=c.getAdress()%></td>
					<td><%=c.getEmail()%></td>
				</tr>

				<%
					}
				%>
				<tr>
					<th scope="row">
						<button class="btn btn-outline-success my-2 my-sm-0"  name="accion" value="clientSelect" type="submit">Seleccionar
							Cliente</button>
					</th>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form>
	<%
		} else {
	%>

	<form method="GET" action="order">
		<table class="table" id="testTable">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Seleccionar</th>
					<th scope="col">sku</th>
					<th scope="col">nombre</th>
					<th scope="col">tipo</th>
					<th scope="col">metros por tipo</th>
					<th scope="col">Unidades</th>
					<th scope="col">metros totales</th>
					<th scope="col">metros totales disp</th>
					<th scope="col">Precio por metro</th>
					<th scope="col">Precio total</th>
				</tr>
			</thead>
			<tbody>


				<%
					Product p = new Product();
				ProductDAO pdao = new ProductDAO();
				ResultSet rs2 = pdao.searchAll();
				double price = 0;
				String discountType = "";

				double totalMeters = 0;
				double totalAllMeters = 0;

				double totalPrice = 0;
				double totalAllPrice = 0;

				while (rs2.next()) {
					p.setId(rs2.getInt("id"));
					p.setName(rs2.getString("name"));
					p.setPrice(rs2.getDouble("price"));
					p.setSku(rs2.getInt("sku"));
					p.setUnitys(rs2.getDouble("unitys"));
					p.setType(rs2.getString("type"));
					p.setMetersByType(rs2.getDouble("metersbytype"));
					p.setDisponibleMeters(rs2.getDouble("disponiblemeters"));
					p.setDiscountType(rs2.getString("discounttype"));

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
					<th scope="row">
							<div class="form-check">
								<input class="form-check-input" name="idProduct" type="checkbox"
									value=<%=p.getId()%> id="defaultCheck1">
							</div>
					</th>

					<td><%=p.getSku()%></td>
					<td><%=p.getName()%></td>
					<td><%=p.getType()%></td>
					<td><%=p.getMetersByType()%> mts</td>
					<td><%=p.getUnitys()%></td>
					<td><%=totalMeters%> mts</td>
					<td>Not Working yet</td>
					<td><%=price%> usd</td>
					<td><%=totalPrice%> usd</td>
				</tr>
				<%
					}
				%>
				<%
					totalAllPrice = Rounder.roundByFourZeroes(totalAllPrice);
				%>
				<tr>
					<th scope="row">
						<button class="btn btn-outline-success my-2 my-sm-0" name="accion" value="productSelect" type="submit">Seleccionar
							Productos</button>
					</th>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><strong>TOTAL: </strong></td>
					<td><%=totalAllMeters%> mts</td>
					<td></td>
					<td><strong>TOTAL: </strong></td>
					<td><%=totalAllPrice%> usd</td>
					
				</tr>
				
			</tbody>
		</table>
	</form>

	<%

		}
	%>
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