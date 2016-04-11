<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="PT-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />">
<link type="text/css" rel="stylesheet"
	href="<c:url value='/resources/css/style.css' />" />

<title>FitPonto</title>
</head>

<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="dashboard">Fit Ponto</a>
			</div>

			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown"><a href="dashboard" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> ${sessionName} <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="doLogout"><i class="fa fa-fw fa-power-off"></i>
								Sair</a></li>
					</ul></li>
			</ul>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="listarApontamentoPendente">Aprovar
							apontamentos</a></li>
					<li><a href="listaUsuario">Usuários</a></li>
					<li><a href="listaTarefa">Tarefas</a></li>
					<li class="active"><a href="dashboard">Relatórios</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<section>
		<div class="container">
			<!-- <table class = "table table-striped">
				<tr>
					<td colspan = 6><h2>Balanço de Tarefas</h2></td>
				</tr>
				<tr>
					<td><h3>NumAprovados</h3></td>
					<td><h3>PercAprovados</h3></td>
					<td><h3>NumReprovados</h3></td>
					<td><h3>PercReprovados</h3></td>
					<td><h3>NumPendentes</h3></td>
					<td><h3>PercPendentes</h3></td>
				</tr>
				<c:forEach var="usuarioAprovacoes" items="${usuariosAprovacoes}">
					<tr>
						<td width=80px>${usuarioAprovacoes.nome}</td>
						<td width=100px>${usuarioAprovacoes.numAprovados}</td>
						<td width=100px>${usuarioAprovacoes.percAprovados}</td>
						<td width=100px>${usuarioAprovacoes.numReprovados}</td>
						<td width=100px>${usuarioAprovacoes.percReprovados}</td>
						<td width=100px>${usuarioAprovacoes.numPendentes}</td>
						<td width=100px>${usuarioAprovacoes.percPendentes}</td>
					</tr>
				</c:forEach>
			</table> -->


			<h4>Dashboard Geral</h4>

			<div class="boxLeft">
				<table class="table table-striped">
					<tr>
						<td colspan=2><h2>Horas Trabalhadas</h2></td>
					</tr>
					<c:forEach var="usuarioHoras" items="${usuariosHoras}">
						<tr>
							<td width=160px>${usuarioHoras.nome}</td>
							<td>
								<div class="progress" style="background-color: LightGray;">
									<div class="progress-bar progress-bar-striped active"
										role="progressbar" aria-valuenow="60" aria-valuemin="0"
										aria-valuemax="100"
										style="width:${usuarioHoras.horasTrabPerc};">
										${usuarioHoras.horasTrabForm}</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="boxRight">
				<table class="table table-striped">
					<tr>
						<td colspan=2><h2>Balanço de Tarefas</h2></td>
					</tr>
					<c:forEach var="usuarioAprovacoes" items="${usuariosAprovacoes}">
						<tr>
							<td width=160px>${usuarioAprovacoes.nome}</td>
							<td>
								<div class="progress">
									<div
										class="progress-bar progress-bar-success progress-bar-striped active"
										style="width: ${usuarioAprovacoes.percAprovados}">
										${usuarioAprovacoes.numAprovados}
									</div>
									<div
										class="progress-bar progress-bar-warning progress-bar-striped active"
										style="width: ${usuarioAprovacoes.percPendentes}">
										${usuarioAprovacoes.numPendentes}
									</div>
									<div
										class="progress-bar progress-bar-danger progress-bar-striped active"
										style="width: ${usuarioAprovacoes.percReprovados}">
										${usuarioAprovacoes.numReprovados}
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</section>

	<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>