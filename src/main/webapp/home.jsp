<!DOCTYPE html>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="com.adriana.prado.pojo.Usuario"%>
<%@page import="com.adriana.prado.pojo.Alert"%>
<%@page import="com.adriana.prado.controller.HomeController"%>
<%@page import="com.adriana.prado.pojo.Video"%>
<%@page import="java.util.ArrayList"%>
<html lang="es">

<head>

<!-- Comienza todas las URLs desde el href indicado -->
<base href="<%=request.getContextPath()%>/">

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="videos youtube">
<meta name="author" content="Adriana">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

<link rel="stylesheet" href="css/styles.css">

<title>Youtube</title>

<!-- Bootstrap core CSS -->
    <link href="https://blackrockdigital.github.io/startbootstrap-shop-item/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="https://blackrockdigital.github.io/startbootstrap-shop-item/css/shop-item.css" rel="stylesheet">

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Youtube Playlist</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon">0</span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
					
					<%
						//Gestion Usuario Loggeado
						Usuario usuario = (Usuario) session.getAttribute("usuario");
						if(session.getAttribute("usuario")==null){
							
					%>
					
					
					<!-- Formulario de login -->
					<form class="form-inline navbar-nav ml-auto" action="login"
						method="post">
						<input required type="text" class="form-control mb-1 mr-sm-2" id="inlineFormInputName2" placeholder="Usuario"
							name="user" pattern=".{3,30}">
						<div class="input-group mb-1 mr-sm-2">
						<input required type="password" class="form-control" id="inlineFormInputGroupUsername2"
							placeholder="Contraseña" name="pswd" pattern=".{2,50}">
						</div>
						<button type="submit" class="btn btn-primary mb-1">Entrar</button>
					</form> 
					
					<%		
					
						}else{
						
					%>
					<div class="">
						<span class="text-center" style="color:#FFF">
							<i class="fas fa-user mr-1 ml-2"></i> 
							<%=usuario.getNombre() %> |
							<a class="ml-1 mr-4" href="logout">Cerrar sesión</a>
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
									placeholder="Título (mínimo 2 letras)" name="titulo">
							</div>
							<button type="submit" class="btn btn-primary mb-1">Añadir</button>
						</form> 
						
					<%		
					
						}
						
					%>
					</il>
				</ul>

			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
	
		<%
			//Gestion de alertas para el usuario
			Alert alert = (Alert) request.getAttribute("alert");
			
			if (alert == null){
				alert = (Alert) session.getAttribute("alert");
				session.setAttribute("alert", null); 
				//Eliminar atributo. Sino al recargar vuelve a sacar la misma alerta
			}
										
			if(alert != null){
				
		%>
			<div class="alert <%=alert.getTipo()%> alert-dismissible fade show mt-4" role="alert">
				<p><%=alert.getTexto() %></p>
			 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			 		<span aria-hidden="true">&times;</span>
			 	</button>
			</div>
		<%
			}
		%>

		<div class="row">

			<div class="col-lg-3">
				<h1 class="my-4">Lista de reproducción</h1>
				<ul class="list-group">
					<%
						ArrayList<Video> videos = (ArrayList<Video>) request.getAttribute("videos");
						Video videoInicio = (Video)request.getAttribute("videoInicio");

						for (Video v : videos) {
					%>
					<li class="list-group-item d-flex justify-content-between align-items-center">
						<a href="inicio?id=<%=v.getId()%>"><%=v.getTitulo()%></a>
		          	  	<a href="inicio?id=<%=v.getId()%>&op=<%=HomeController.OP_ELIMINAR%>"><i style="color:red;" class="float-right fas fa-trash-alt"></i></a>
	           		</li>
					<%
						} //end for
					%>

				</ul>
				
				<%
					//Gestion Usuario Loggeado
					if(session.getAttribute("usuario")!=null){
							
				%>
				
				<h1 class="my-4">Vídeos reproducidos</h1>
				<ul class="list-group">
					<%
						ArrayList<Video> videosVistos = (ArrayList<Video>) session.getAttribute("videosVistos");
						
						if(videosVistos != null){
							
						for (Video vv : videosVistos) {
					%>
						<li class="list-group-item d-flex justify-content-between align-items-center">     
			          	  	<a href="?id=<%=vv.getId()%>"><%=vv.getTitulo()%></a>	          	  	
			            </li>
					<%
							} //end for
						}else{
						//end if != null
						%>
		          			<li class="list-group-item d-flex justify-content-between align-items-center">
		          				<p>*Por favor Inicia Sesion para guardar tus video reproducidos</p>
		          			</li>
		          		<%
						}
					}//end if session
					%>

				</ul>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div class="card mt-4">

					<iframe id="iframe" width="823" height="415" src="https://www.youtube.com/embed/<%=videoInicio.getId()%>?autoplay=1" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
           
					<div class="card-body">
						<h3 class="card-title">
							<%=videoInicio.getTitulo()%>
						</h3>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Sapiente dicta fugit fugiat hic aliquam itaque
							facere, soluta. Totam id dolores, sint aperiam sequi pariatur
							praesentium animi perspiciatis molestias iure, ducimus!</p>
					</div>
				</div>
				<!-- /.card -->

				<div class="card card-outline-secondary my-4">
					<div class="card-header">Comentarios</div>
					<div class="card-body">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Omnis et enim aperiam inventore, similique necessitatibus neque
							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum.
							Sequi mollitia, necessitatibus quae sint natus.</p>
						<small class="text-muted">Posted by Anonymous on 3/1/17</small>
						<hr>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Omnis et enim aperiam inventore, similique necessitatibus neque
							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum.
							Sequi mollitia, necessitatibus quae sint natus.</p>
						<small class="text-muted">Posted by Anonymous on 3/1/17</small>
						<hr>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Omnis et enim aperiam inventore, similique necessitatibus neque
							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum.
							Sequi mollitia, necessitatibus quae sint natus.</p>
						<small class="text-muted">Posted by Anonymous on 3/1/17</small>
						<hr>
						<div hidden="">
							<p><% %></p>
							<small class="text-muted">Posted by <% %> on <% %></small>
							<hr>
						</div>
						<%if(session.getAttribute("usuario")!=null){ %>
						<form action="comentar" method="post" id="cmtform">
							<textarea rows="4" cols="105" name="comentario" form="cmtform" 
							placeholder="Escribe aquí tu comentario..."></textarea>
							<%
								DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
							    LocalDateTime now = LocalDateTime.now();
							    Usuario user;
							    
							    	user = (Usuario)session.getAttribute("usuario");
							    
							%>
							<input hidden type="text" name="fecha" value="<%=now%>">
							<input hidden type="text" name="user" value="<%=user%>">
							<%
							    }else{
							%>
								<p>****Por favor inicia sesión para comentar en el vídeo****</p>
							<%
							    }
							%>
							<div>
								<button type="submit" class="btn btn-success">Comentar</button>
							</div>
						</form>
					</div>
				</div>
				<!-- /.card -->

			</div>
			<!-- /.col-lg-9 -->

		</div>

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2017</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script>
		function reproducir(id){
			console.info('reproducir video: '+id);
			var iframe = document.getElementById("iframe");
			iframe.src = "https://www.youtube.com/embed/" + id;
		}
	</script>

</body>

</html>
