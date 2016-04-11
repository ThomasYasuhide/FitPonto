<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="PT-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/style.css' />" />

<title>FitPonto</title>
</head>

<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="apontamentos">Fit Ponto</a>
			</div>

			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> ${sessionName} <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="doLogout"><i class="fa fa-fw fa-power-off"></i> Sair</a>
						</li>
					</ul>
				</li>
			</ul>

			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="apontamentos">Apontamentos</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
	<section>
		<div class="container">
		
			<c:if test="${status == 'apontamentoalterado'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Apontamento alterado com sucesso!!!</strong>
				</div>
			</c:if>
			<c:if test="${status == 'apontamentocadastrado'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Apontamento cadastrado com sucesso!!!</strong>
				</div>
			</c:if>
			<c:if test="${status == 'apontamentoremovido'}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Apontamento removido com sucesso!!!</strong>
				</div>
			</c:if>
		
			<h4>Lista de apontamentos</h4>
		
			<table class = "table table-striped">
				<thead>
					<tr>
						<th><h5>Tarefa</h5></th>
						<th><h5>Início</h5></th>
						<th><h5>Fim</h5></th>
						<th><h5>Total</h5></th>
						<th><h5>Obs</h5></th>
						<th><h5>Status</h5></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="apontamento" items="${apontamentos}">
						<form method="POST">
							<tr>
								<input type="text" value='${apontamento.id}' name="id" class="form-control" style="display:none" readonly/>
								<input type="text" value='${apontamento.tarefaID}' name="tarefaID" class="form-control" style="display:none" readonly/>
								<td><input type="text" value='${apontamento.tarefa}' name="tarefa" class="form-control" /></td>
								<td><input type="datetime-local" value='${apontamento.inicio}' class="form-control" name="inicio" /></td>
								<td><input type="datetime-local" value='${apontamento.fim}' class="form-control" name="fim" /></td>
								<td><input type="text" value='${apontamento.horaTotal}' name="horaTotal" class="form-control" disabled /></td>
								<td><input type="text" value='${apontamento.obs}' name="obs" class="form-control" /></td>
								<td><input type="text" value='${apontamento.status}' name="status" class="form-control" disabled /></td>
								
								<td><input type="submit" value="Alterar" formaction="alterarApontamento" class="btn btn-primary"></td>
								
								<td>
									<c:if test="${apontamento.status != 'Reprovado'}">
										<input type="submit" value="Deletar" formaction="removerApontamento" class="btn btn-danger">
									</c:if>
								</td>
								
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
		
		
			<div>
				<button type="button"  data-toggle="modal" data-target="#myModal" class="btn btn-primary">Cadastrar novo apontamento</button>
		
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form method="POST">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="myModalLabel">Cadastrar novo apontamento</h4>
							</div>
							<div class="modal-body">
									
									<input type="text" value='${sessionUserID}' name="userID" class="form-control" style="display:none" readonly/>
									
									<table>
										<tr>
											<td>
												Projeto
												<select name="projetoID" class="form-control" >
													<c:forEach var="projeto" items="${projetos}">
														<option value='${projeto.id}'>${projeto.nome}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td>
												Tarefa
												<select name="tarefaID" class="form-control" >
													<c:forEach var="tarefa" items="${tarefas}">
														<option value='${tarefa.id}'>${tarefa.nome}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td>Início da atividade: <input type="datetime-local" class="form-control" name="inicio" required /></td>
										</tr>
										<tr>
											<td>Término da atividade: <input type="datetime-local" class="form-control" name="fim" required /></td>
										</tr>
										<tr>
											<td>Observação: <input type="text" class="form-control" name="obs" required /></td>
										</tr>
									</table>
								</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
								<button type="submit" class="btn btn-primary" formaction="adicionarApontamento">Salvar</button>
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