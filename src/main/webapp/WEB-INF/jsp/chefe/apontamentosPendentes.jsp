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
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> ${sessionName} <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="doLogout"><i class="fa fa-fw fa-power-off"></i>
								Sair</a></li>
					</ul></li>
			</ul>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="listarApontamentoPendente">Aprovar
							apontamentos</a></li>
					<li><a href="listaUsuario">Usuários</a></li>
					<li><a href="listaTarefa">Tarefas</a></li>
					<li><a href="dashboard">Relatórios</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<section>
		<div class="container">
		
			<c:if test="${status == 'apontamentoaprovado'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Apontamento aprovado com sucesso!!!</strong>
				</div>
			</c:if>
			<c:if test="${status == 'apontamentoreprovado'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Apontamento reprovado com sucesso!!!</strong>
				</div>
			</c:if>
		
			<h4>Lista de apontamentos pendentes de aprovação</h4>

			<table class="table table-striped">
				<thead>
					<tr>
						<th><h5>Usuário</h5></th>
						<th><h5>Tarefa</h5></th>
						<th><h5>Início</h5></th>
						<th><h5>Fim</h5></th>
						<th><h5>Total</h5></th>
						<th><h5>Obs</h5></th>
						<th></th>
						<th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="apontamento" items="${apontamentos}">
						<form method="POST">
							<tr>
								<input type="text" value='${apontamento.id}' name="id"
									class="form-control" style="display:none" readonly />
								<td><input type="text" value='${apontamento.user}'
									name="user" class="form-control" disabled/></td>
								<td><input type="text" value='${apontamento.tarefa}'
									name="tarefa" class="form-control" disabled/></td>
								<td><input type="datetime-local"
									value='${apontamento.inicio}' class="form-control"
									name="inicio" disabled /></td>
								<td><input type="datetime-local" value='${apontamento.fim}'
									class="form-control" name="fim" disabled /></td>
								<td><input type="text" value='${apontamento.horaTotal}'
									name="horaTotal" class="form-control" disabled /></td>
								<td><input type="text" value='${apontamento.obs}'
									name="obs" class="form-control" disabled/></td>
								
								<td><input type="submit" value="Aprovar" formaction="aprovarApondamentoPendente" class="btn btn-primary"></td>
								<td><input type="submit" value="Reprovar" formaction="reprovarApondamentoPendente" class="btn btn-danger"></td>
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>