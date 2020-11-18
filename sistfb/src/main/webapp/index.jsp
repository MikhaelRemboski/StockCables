<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Login - Cables fb Control De Stock</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,400italic"><link rel='stylesheet' href='https://cdn.gitcdn.xyz/cdn/angular/bower-material/v1.0.0-rc3/angular-material.css'>
<link rel='stylesheet' href='https://localhost:8080/docs.css'><link rel="stylesheet" href="./style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div ng-controller="DemoCtrl" ng-cloak="" class="md-inline-form" ng-app="MyApp" layout="column" layout-sm="row" layout-align="center center" layout-align-sm="start start" layout-fill>
	<md-content  id="SignupContent" class="md-whiteframe-10dp" flex-sm>
		<md-toolbar flex id="materialToolbar">
			<div class="md-toolbar-tools">
				<span flex=""></span>
				<span class="md-headline" align="center">CABLES FB SRL</span>
				<span flex=""></span>
			</div>
		</md-toolbar>
		<div layout-padding="">
			<div></div>
			<form name="userForm" method="POST" action="logued" ng-submit="user.submit(userForm.$valid)" >
				<input type="hidden" name="action" value="login" />
				<div layout="row" layout-sm="column">
					<md-input-container flex-gt-sm="">
						<label>Email</label>
						<input required type="email" name="email" ng-model="user.email" ng-pattern="/^[_a-z0-9-+]+(\.[_a-z0-9-+]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/" placeholder="anonimo@ejemplo.com" />
						<div ng-if="userForm.email.$dirty" ng-messages="userForm.email.$error" role="alert">
							<div ng-message="required" class="my-message">No puedes loguearte sin el correo electronico.</div>
							<div ng-message-exp="['email', 'pattern']">No puedes loguearte con un pattern incorrecto.</div>
						</div>
					</md-input-container>
				</div>
				<div layout="row" layout-sm="column">
					<md-input-container flex-gt-sm="">
						<label>Contraseña</label>
						<input name="password" ng-model="user.password" type="password" minlength="8" maxlength="100" ng-pattern="/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/" required placeholder="********">
						<div ng-if="userForm.password.$dirty" ng-messages="userForm.password.$error" role="alert" multiple>
							<div ng-message="required">No podes loguear sin tu contraseña.</div>
							<div ng-message-exp="['minlength', 'maxlength', 'pattern']">No existe una clave asi. Todas las contraseñas contienen como minimo una mayuscula, una minuscula y un numero.</div>
						</div>
					</md-input-container>
				</div>
				<md-button class="md-raised md-primary" style="width:100%; margin: 0px 0px;" type="submit" name="login" value="login" ng-disabled="userForm.$invalid">Login</md-button>
			</form>
		</div>
	</md-content>
</div>
<!-- partial -->
  <script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-animate.min.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-route.min.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-aria.min.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-messages.min.js'></script>
<script src='https://cdn.gitcdn.xyz/cdn/angular/bower-material/v1.0.0-rc3/angular-material.js'></script>
<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-114/assets-cache.js'></script><script  src="./script.js"></script>

</body>
</html>
