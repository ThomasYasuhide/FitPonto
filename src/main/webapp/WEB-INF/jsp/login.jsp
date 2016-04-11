<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="PT-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css' />" />

<title>FitPonto</title>
</head>

<body>

	<div class="container-fluid">

		

		<div id="loginbox" class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4">
		
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title text-center">Fit Ponto</div>
				</div>

				<div class="panel-body">

					<form action="doLogin" name="form" id="form" class="form-horizontal" method="POST">
						<!-- Campo de nome do usuário -->
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<input id="user" type="text" class="form-control" name="username" placeholder="Insira o nome de usuário.">
						</div>
						<!-- Campo da senha do usuário -->
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> <input id="password"
								type="password" class="form-control" name="password" placeholder="Insira sua senha.">
						</div>
						<div class="form-group">
							<!-- Campo para lembrar senha # Não implementado -->
							<div class="col-sm-6 controls">
								<input type="checkbox" name="rememberMe" /> Lembrar usuário?
							</div>
							
							<!-- Campo recuperar senha # Não implementado -->
							<div class="col-sm-6 controls text-right">
								<a href="#">Esqueceu a senha?</a>
							</div>
						</div>
						<!-- Botão de autenticação -->
						<div class="input-group">
							<input class="btn btn-primary" type="submit" value="Login">
						</div>

					</form>

				</div>
			</div>
			
			<c:if test="${status == 'loginreprovado'}">
				<div class="alert alert-danger alert-dismissible" role="alert" >
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Login ou senha incorreta</strong>
				</div>
			</c:if>
		</div>
	</div>

	<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>