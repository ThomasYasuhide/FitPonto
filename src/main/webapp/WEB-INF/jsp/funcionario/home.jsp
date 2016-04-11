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
	<jsp:forward page="../../../apontamentos" />

	<script src="<c:url value='/resources/js/jquery-1.11.3.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>