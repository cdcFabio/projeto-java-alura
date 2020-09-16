<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Lista de Usuários">

	<c:url value="/resources/css" var="cssPath" />
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />

	<section id="index-section" class="container middle">
		
		<a href="${s:mvcUrl('UC#form').build() }" rel="nofollow">
			<h1>Novo usuário</h1>
		</a> 
		<br>
	
		<h1>Lista de Usuarios</h1>
		<br>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Nome</th>
				<th>Email</th>
			</tr>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
				</tr>
			</c:forEach>
		</table>
		
		<h1 >${message }</h1>
		<br>

	</section>

</tags:pageTemplate>