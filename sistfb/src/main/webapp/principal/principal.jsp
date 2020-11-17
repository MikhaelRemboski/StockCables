
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>

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
	if(request.getSession().getAttribute("email") == null && request.getSession().getAttribute("name") == null){
		out.print("<script>location.replace('/sistfb/login/index.jsp');</script>");
		}%>







    <!-- navbar abajo -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Inicio</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="../inicio/stock.html" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Pedidos
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Ver Pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Cargar Pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Despachar Pedidos</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Modificar Pedidos</a>
              </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Control Stock
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="../inicio/stock.html">Ver Stock</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="../control de stock/agregarproducto.html">Agregar Producto</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#">Recorte</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#">Modificar producto</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#">Eliminar producto</a>
                </div>
              </li>
          </ul>
          
          <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Nombre o ID" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar Producto </button>
          </form>
          <ul class="navbar-nav mr-right">
			<li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="../inicio/stock.html" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Mi Cuenta
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" >
                <%=request.getSession().getAttribute("email")%></a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" >
                <%=request.getSession().getAttribute("name")%></a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/sistfb/login/index.jsp?<%request.getSession().invalidate();%>">Cerrar Sesion</a>
              </div>
            </li>
          </ul>
        </div>
        
      </nav>
      <br>
    <!--  tabla abajo -->
    <table class="table">
        <thead class="thead-dark">
          <tr>
            <th scope="col">id</th>
            <th scope="col">sku</th>
            <th scope="col">nombre</th>
            <th scope="col">tipo</th>
            <th scope="col">metros por tipo</th>
            <th scope="col">cantidad de unidades</th>
            <th scope="col">metros totales</th>
            <th scope="col">metros totales disp</th>
            <th scope="col">Precio por metro</th>
            <th scope="col">Precio total</th>
            <th scope="col">Accion</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>1010</td>
            <td>UNIPOLAR ROJO 4MM</td>
            <td>ROLLO</td>
            <td>100 MTS</td>
            <td>20</td>
            <td>2000  MTS</td>
            <td>1500  MTS</td>
            <td>5  usd</td>
            <td> 5000  usd</td>
            <td>
                <ul class="navbar-nav mr-auto">            
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Opciones
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Modificar</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Eliminar</a>
                    </div>
                </li>
                </ul>
            </td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>2010</td>
            <td>UNIPOLAR ROJO 4MM</td>
            <td>BOBINA</td>
            <td>450 MTS</td>
            <td>2</td>
            <td>900 MTS</td>
            <td>450 MTS</td>
            <td>5 usd</td>
            <td> 5000  usd</td>
            <td>      
                <ul class="navbar-nav mr-auto">            
                    <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Opciones
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Modificar</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Eliminar</a>
                    </div>
                </li>
                </ul>
            </td>
          </tr>
        </tbody>
      </table>  
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    
</body>
</html>