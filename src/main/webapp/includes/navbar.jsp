	<!-- Navigation -->
	<%@page import="java.net.URLDecoder"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Youtube Playlist</a>
			<%
				String user ="";
				String fecha = "";
				Cookie[] cookies = request.getCookies();
				for(Cookie c: cookies){
					if(c.getName().equals("cVisita")){
						fecha = URLDecoder.decode(c.getValue(), "UTF-8");
					}
					if(c.getName().equals("cSesion")){
						user = c.getValue();
					}
				%>
			<span class="text-warning mr-auto"> �ltima visita <%=fecha %></span>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon">0</span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
						<!-- Usuario no loggeado -->
						<c:if test="${empty usuario}">
							<!-- Formulario de login -->
							<form class="form-inline navbar-nav ml-auto" action="login"
								method="post">
								<input required type="text" class="form-control mb-1 mr-sm-2" id="inlineFormInputName2" placeholder="Usuario"
									name="user" pattern=".{3,30}">
								<div class="input-group mb-1 mr-sm-2">
								<input required type="password" class="form-control" id="inlineFormInputGroupUsername2"
									placeholder="Contrase�a" name="pswd" pattern=".{2,50}">
								</div>
								<button type="submit" class="btn btn-primary mb-1 mr-2">Entrar</button>
								<input type="checkbox" id="check" value="recordar" name="recordar"> <label for="check" class="text-light" <%
								if(!user.equals("")){
										
								%>
									checked
								<% 
									}					
								%>>Recordar sesi�n</label> 
							</form> 
						<%}
			%>
						</c:if>
						
						<!-- Usuario Loggeado -->
						<c:if test="${not empty usuario}">
							</div>
							<div class="">
								<span class="text-center" style="color:#FFF">
									<i class="fas fa-user mr-1 ml-2"></i> 
									${usuario.nombre} |
									<a class="ml-1 mr-4" href="logout">Cerrar sesi�n</a>
									<a href="backoffice/index.jsp">Acceder al Backoffice</a>
								</span> 
							</div>
							<!-- Formulario para crear video -->
							<form class="form-inline navbar-nav ml-auto" action="inicio"
								method="post">
								<input
									required type="text" class="form-control mb-1 mr-sm-2"
									id="inlineFormInputName2" placeholder="ID (11 caracteres)"
									name="id" pattern=".{11,11}">
								<div class="input-group mb-1 mr-sm-2">
									<input required type="text" class="form-control"
										id="inlineFormInputGroupUsername2"
										placeholder="T�tulo (m�nimo 2 letras)" name="titulo">
								</div>
								<button type="submit" class="btn btn-primary mb-1">A�adir</button>
							</form> 
						</c:if>
					</li>
				</ul>
			</div>
		</div>
	</nav>