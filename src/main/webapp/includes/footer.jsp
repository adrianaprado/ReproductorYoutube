<%@include file="taglibs.jsp"%>
	<!-- Footer -->
<%@page import="java.util.Date"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.net.URLDecoder"%>

<footer class="py-5 bg-dark">
		<div class="container">
		<%
				String user ="";
				String fecha = "";
				Cookie[] cookies = request.getCookies();
				for(Cookie c: cookies){
					if(c.getName().equals("cVisita")){
						fecha = URLDecoder.decode(c.getValue(), "UTF-8");
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						Cookie cVisita = new Cookie("cVisita", URLEncoder.encode(dateFormat.format(new Date()), "UTF-8"));
					}
				}
				%>
<%-- 			<span class="text-warning mr-5"> Última visita <%=fecha %></span> --%>
			<!-- https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html -->
			<c:set var="now" value="<%= new java.util.Date() %>"/>
			<span class="text-warning">Última visita el </span>
			<span class="text-warning"> <fmt:formatDate type="both" pattern="dd MM yyyy HH:mm" timeStyle="medium" value="${now }"/></span>
			<p class="m-0 text-center text-white">Copyright &copy; Youtube 2018</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>