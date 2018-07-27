<!DOCTYPE html>
<%@page import="com.adriana.prado.controller.HomeController"%>
<%@page import="com.adriana.prado.pojo.Video"%>
<%@page import="java.util.ArrayList"%>
<html lang="es">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="videos youtube">
<meta name="author" content="Adriana">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

<title>Youtube</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/shop-item.css" rel="stylesheet">

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
						<form class="form-inline navbar-nav ml-auto" action=""
							method="post">
							<p class="text-danger">${msg}</p>
							<label class="sr-only" for="inlineFormInputName2">ID</label> <input
								required type="text" class="form-control mb-2 mr-sm-2"
								id="inlineFormInputName2" placeholder="ID (11 caracteres)"
								name="id" pattern=".{11,11}"> <label class="sr-only"
								for="inlineFormInputGroupUsername2">Titulo</label>
							<div class="input-group mb-2 mr-sm-2">
								<input required type="text" class="form-control"
									id="inlineFormInputGroupUsername2"
									placeholder="Título (mínimo 2 letras)" name="titulo">
							</div>
							<button type="submit" class="btn btn-primary mb-2">Añadir</button>
						</form> </il>
				</ul>


			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-lg-3">
				<h1 class="my-4">Lista de reproducción</h1>
				<ul class="list-group">
					<%
						ArrayList<Video> videos = (ArrayList<Video>) request.getAttribute("videos");
						Video videoInicio = new Video();
						if(!videos.isEmpty())
							videoInicio = videos.get(0);
						
						for (Video v : videos) {
					%>
					<li class="list-group-item d-flex justify-content-between align-items-center">
						<a href="#" onclick="reproducir('<%=v.getId() %>')" class="list-group-item"> <%=v.getTitulo()%></a> 
						<a href="?id=<%=v.getId()%>&op=<%=1%>"><i style="color:red;" class="float-right fas fa-trash-alt"></i></a>
	           		</li>
					<%
						} //end for
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
						<a href="#" class="btn btn-success">Comentar</a>
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
