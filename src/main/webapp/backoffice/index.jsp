<%@page import="com.adriana.prado.pojo.Usuario"%>

ESTAMOS EN EL BACKOFFICE
*Solo pueden entrar usuarios logeados* 
<br>

<% Usuario u = (Usuario) session.getAttribute("usuario");
	if (u == null){
		%>
			<p style="color:red"> Usuario nulo, se ha saltado el login!</p>
		<%
	}else{
		%>
			Usuario: <%=u.getNombre() %>
		<%
	}
%>

<img src="http://denkaidigital.denkaidigital.netdna-cdn.com/wp-content/uploads/2018/02/back-office-outsourcing-services.png">