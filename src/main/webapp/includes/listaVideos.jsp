<%@include file="taglibs.jsp"%>

<div class="col-lg-3">
				<h1 class="my-4"><fmt:message key="lista.reproduccion"/></h1>
				<ul class="list-group">
				
					<c:forEach items="${videos }" var="v">
						<li class="list-group-item d-flex justify-content-between align-items-center">
							<a href="inicio?id=${v.id }">${v.titulo }</a>
			          	  	<a href="inicio?id=${v.id }&op=${HomeController.OP_ELIMINAR}"><i style="color:red;" class="float-right fas fa-trash-alt"></i></a>
	           			</li>
					</c:forEach>

				</ul>
				<c:if test="${not empty usuario}">
				
					<h1 class="my-4"><fmt:message key="lista.visualizados"/></h1>
					<ul class="list-group">
						<c:if test="${not empty videosVistos}">
						
							<c:forEach items="${videosVistos }" var="vv">
							
								<li class="list-group-item d-flex justify-content-between align-items-center">     
					          	  	<a href="?id=${vv.id }">${vv.titulo }</a>	          	  	
					            </li>
					            
							</c:forEach>
						
						</c:if>
						
						<c:if test="${empty videosVistos}">
						
							<li class="list-group-item d-flex justify-content-between align-items-center">
		          				<p>*Por favor Inicia Sesión para guardar tus video reproducidos</p>
		          			</li>
		          			
						</c:if>
					</ul>
				</c:if>
			</div>
			<!-- /col-lg-3 -->