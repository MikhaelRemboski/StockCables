<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

		<%	
	if (session.getAttribute("email") == null && session.getAttribute("name") == null) {
		out.print("<script>location.replace('/sistfb/index.jsp');</script>");
		System.out.println("no se encontraron");
		System.out.println(session.getId()+ "addproduct");

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
						<a class="dropdown-item" href="../sistfb/principal.jsp">Ver Stock</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item"
							href="../sistfb/addproduct.jsp">Agregar
							Producto</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Recorte</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Modificar producto</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Eliminar producto</a>
					</div></li>
			</ul>

		
			<ul class="navbar-nav mr-right">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="../inicio/stock.html"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Mi Cuenta </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item"> <%=request.getSession().getAttribute("email")%></a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item"> <%=request.getSession().getAttribute("name")%></a>
						<div class="dropdown-divider"></div>
						<form action="logued" method="POST">
						<button type="submit" class="btn btn-link" name="close" value="close">cerrar sesion</button>
						</form>
					</div></li>
			</ul>
		</div>

	</nav>
	<br>      

    <form action="stockadded" method="POST" name="addStock" class="was-validated" >
        <div class="row">
            <div class="col">
              <input type="text" name="name" class="form-control" placeholder="Nombre de producto">
            </div>
            <div class="col">
              <input type="text" name="sku" class="form-control" placeholder="Sku de producto">   
            </div>
            <div class="col">
                <input type="text" name="metersbytype" class="form-control" placeholder="Metros por tipo">
              </div>
              <div class="col">
                <input type="text" name="unitys" class="form-control" placeholder="Cantidad de unidades del tipo">
              </div>
              <div class="col">
                <input type="text" name="price" class="form-control" placeholder="Precio de producto">
              </div>
              <div class="col">
                <input type="text" name="discounttype" class="form-control" placeholder="Tipo de dto">
              </div>
          </div>
          <br>
          <p>Tipo de producto</p>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="type" id="inlineRadio1" value="bobina">
            <label class="form-check-label" for="inlineRadio1">Bobina</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="type" id="inlineRadio2" value="rollo">
            <label class="form-check-label" for="inlineRadio2">Rollo</label>
          </div>
              <button type="submit" class="btn btn-primary btn-lg btn-block">AGREGAR PRODUCTO</button>
          
    </form> 
    
    <%if(session.getAttribute("exito")!= null){
    	%>
	<div class="alert alert-success" role="alert">
	Los datos fueron cargados de manera exitosa.
</div>
	    
    <% session.removeAttribute("exito");
    	
    }%>	
    
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    
</body>
</html>