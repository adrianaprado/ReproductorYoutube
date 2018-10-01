<%@include file="includes/taglibs.jsp"%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="com.adriana.prado.pojo.Usuario"%>
<%@page import="com.adriana.prado.pojo.Alert"%>
<%@page import="com.adriana.prado.controller.HomeController"%>
<%@page import="com.adriana.prado.pojo.Video"%>
<%@page import="java.util.ArrayList"%>


<%@include file="includes/header.jsp"%>

<%@include file="includes/navbar.jsp"%>

<div class="container mt-4 mb-4">
	<c:if test="${empty alert }">
		${alert = null }
	</c:if>
	
	<c:if test="${not empty alert }">
		<div class="alert ${alert.tipo } alert-dismissible fade show mt-4 mb-4" role="alert">
			<p>${alert.texto }</p>
		 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		 		<span aria-hidden="true">&times;</span>
		 	</button>
		</div>
		${alert = null }
	</c:if>
		
	<h1 class="text-center">Registro nuevos usuarios</h1>
	
	<form action="registro" method="post" class="formularioregistro">
		<div class="form-row">
			<div class="col">
				<div class="form-group">
				   <label class="required" for="usuario">Usuario: </label>
				   <input class="form-control" type="text" name="usuario" placeholder="M�nimo 3 caracteres y m�ximo 10" minlength="4" maxlength="10" autofocus tabindex="1" required>
			   </div>
			</div>
		</div>
		
		<div class="form-row">
			<div class="col">
				<div class="form-group">
				    <label class="required" for="pswd">Contrase�a: </label>
				    <input class="form-control" type="password" name="pswd" placeholder="M�nimo 4 caracteres" minlength="4" tabindex="2" required>
				</div>
			</div>	
			<div class="col">
				<div class="form-group">
				    <label class="required" for="pswd">Repite la contrase�a: </label>
				    <input class="form-control" type="password" name="pswdRepe" placeholder="Repite la contrase�a anterior" minlength="4" tabindex="3" required>
				</div>
			</div>
		</div>
		
		<input class="form-control btn btn-outline-primary" type="submit" value="Dar de Alta">
	</form>
</div>
<!-- /.container -->

<%@include file="includes/footer.jsp"%>