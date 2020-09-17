<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>



<tags:pageTemplate titulo="Lista de Usuários">

	<c:url value="/resources/css" var="cssPath" />
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />

	<section id="index-section" class="container middle">

		<form:form action="${s:mvcUrl('UC#alterar').arg(1,usuario.email).build() }"  method="POST" commandName="usuario">
		<h1>Cadastro de Permissões para ${usuario.nome} </h1>
		<br>
		<br>

			<div>
				Permissões:
				<form:checkboxes path="roles" items="${lista }" />
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Atualizar</button>
		</form:form>

	</section>

</tags:pageTemplate>