<%@page import="com.cablesfb.helper.Discounter"%>
<%@page import="com.cablesfb.helper.Rounder"%>
<%@page import="com.cablesfb.model.Product"%>
<%@page import="com.cablesfb.modeldao.ProductDAO"%>
<%@page import="com.cablesfb.modeldao.OrderDAO"%>
<%@page import="com.cablesfb.model.Order"%>
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
	<table class="table" id="testTable">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Id Pedido</th>
				<th scope="col">Nombre Cliente</th>
				<th scope="col">Monto</th>
				<th scope="col">Estado</th>
				<th scope="col">Accion</th>
			</tr>
		</thead>
		<tbody>
			<%
				OrderDAO oDAO = new OrderDAO();
			List<Order> oList = new ArrayList<Order>();
			oList = oDAO.selectNoRepeatOrderId();
			for (Order aux : oList) {
				Order o = new Order();
				o = aux;
				Client c = new Client();
				ClientDAO cDAO = new ClientDAO();
				c = cDAO.searchById(o.getClientId());
			%>
			<tr>
				<th scope="row"><%=o.getId()%></th>
				<td><%=o.getOrderId()%></td>
				<td><%=c.getName()%></td>
				<td><%=Rounder.roundByFourZeroes(o.getTotalPrice())%></td>
				<td><%=o.getState()%></td>
				<td>
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> Opciones </a>
							<form action="order" method="POST">
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="modify"
											id="inlineRadio1" value=<%="delete" + o.getOrderId()%>>
										<label class="form-check-label" for="inlineRadio1">Eliminar</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="modify"
											id="inlineRadio2" value=<%="packoff" + o.getOrderId()%>>
										<label class="form-check-label" for="inlineRadio2">Despachar</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="modify"
											id="inlineRadio2" value=<%="clientmodify" + o.getOrderId()%>>
										<label class="form-check-label" for="inlineRadio2">Modificar Cliente</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="modify"
											id="inlineRadio2" value=<%="modifyproduct" + o.getOrderId()%>>
										<label class="form-check-label" for="inlineRadio2">Modificar Producto</label>
									</div>
									<div class="col-auto my-1">
										<button type="submit" name="accion" value="modifyOrder"
											class="btn btn-primary">Aceptar</button>
									</div>
								</div>
							</form></li>
					</ul>
				</td>
			</tr>
			<%
				}
			%>





			<tr>
				<th scope="row"></th>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><input type="button"
					onclick="tableToExcel('testTable', 'W3C Example Table')"
					value="Exportar a Excel"></td>
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