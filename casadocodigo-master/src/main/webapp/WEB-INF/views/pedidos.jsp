<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<%-- <link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" /> --%>

<tags:pageTemplate titulo="Lista de Pedidos">

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />

	<section id="index-section" class="container middle">
	<br>
	<br>
	
	<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data Pedido</th> 
				<th>Titulos </th>
			</tr>

			<c:forEach items="${pedidos }" var="pedido">
				<tr>
					<td>${pedido.id }</td>
					<td>${pedido.valor }</td>
					<td><fmt:formatDate value="${pedido.data.time }" pattern="dd/MM/yyyy"/></td>
					<td>
							<c:forEach items="${pedido.produtos }" var="produtos">
							${produtos.titulo },
							</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
	
	
		
	</section>	
		
		
		

	
</tags:pageTemplate>