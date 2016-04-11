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
					<li><a href="listarApontamentoPendente">Aprovar
							apontamentos</a></li>
					<li><a href="listaUsuario">Usuários</a></li>
					<li class="active"><a href="listaTarefa">Tarefas</a></li>
					<li><a href="dashboard">Relatórios</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<section>
		<div class="container">
		
			<c:if test="${status == 'tarefaalterada'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Tarefa alterada com sucesso!!!</strong>
				</div>
			</c:if>
			<c:if test="${status == 'tarefacadastrada'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Tarefa cadastrada com sucesso!!!</strong>
				</div>
			</c:if>
			<c:if test="${status == 'tarefaremovida'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Tarefa removida com sucesso!!!</strong>
				</div>
			</c:if>
		
			<h4>Lista de Tarefas</h4>
			
			<table class="table table-striped">
				<thead>
					<tr>
						<th><h5>Nome</h5></th>
						<th><h5>Projeto</h5></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tarefa" items="${tarefas}">
						<form method="POST">
							<input type="text" value='${tarefa.id}' name="id" class="form-control" readonly style="display:none" />
							<tr>
								<td><input type="text" value='${tarefa.nome}' name="nome" class="form-control" /></td>

								<td><select name="projetoid"
									class="form-control">
										<c:forEach var="projeto" items="${projetos}">
											<c:choose>
												<c:when test="${projeto.id == tarefa.projetoid}">
													<option value='${projeto.id}' selected>${projeto.nome}</option>
												</c:when>
												<c:otherwise>
													<option value='${projeto.id}'>${projeto.nome}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</select></td>

								<td><input type="submit" value="Alterar" class="btn btn-primary" formaction="alterarTarefa"></td>
								<td><input type="submit" value="Remover" class="btn btn-danger" formaction="removerTarefa"></td>
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>

			<div>
				<button type="button" data-toggle="modal" data-target="#myModal"
					class="btn btn-primary">Criar nova Tarefa</button>

				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form method="POST">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h3 class="modal-title" id="myModalLabel">Incluir tarefa</h3>
								</div>
								<div class="modal-body">
									<table class="table table-striped">
										<tr>
											<td width=50px><h4>Nome:</h4></td>
											<td width=200px><input type="text" name="nome"
												class="form-control" /></td>
										</tr>
										<tr>
											<td width=50px><h4>Projeto:</h4></td>
											<td><select name="projetoid" class="form-control"
												style="width: 170px">
													<c:forEach var="projeto" items="${projetos}">
														<option value='${projeto.id}'>${projeto.nome}</option>
													</c:forEach>
											</select></td>
										</tr>
									</table>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Fechar</button>
									<button type="submit" class="btn btn-primary"
										formaction="adicionarTarefa">Salvar</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>