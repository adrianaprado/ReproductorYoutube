<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
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

<br>

<img src="http://denkaidigital.denkaidigital.netdna-cdn.com/wp-content/uploads/2018/02/back-office-outsourcing-services.png">

<br>

<h2>Usuarios Conectados</h2>

<% 
	HashMap<String,Usuario> usuariosConectados =  (HashMap<String,Usuario>)application.getAttribute("uConectados");
	
	for(HashMap.Entry<String,Usuario> uConectado : usuariosConectados.entrySet()){
		
		
// 	Iterator it = usuariosConectados.entrySet().iterator();
// 	while (it.hasNext()) {
// 	    Entry pair = (Entry)it.next();
// 	    System.out.println(pair.getKey() + " = " + pair.getValue());
// 	    it.remove(); // avoids a ConcurrentModificationException
// 		Usuario uConectado = (Usuario) it.next();
%>
		<li><%=uConectado.getValue().getNombre()%></li>
<%
	}
%>