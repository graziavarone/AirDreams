<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="gestionevolo.*"%>
    
    <%
    	ArrayList<Volo> voliAndataDiretti=(ArrayList<Volo>) request.getAttribute("voliAndataDiretti");
    	ArrayList<Volo> voliRitornoDiretti=(ArrayList<Volo>) request.getAttribute("voliRitornoDiretti");
    	
    	ArrayList<Volo[]> voliAndataUno=(ArrayList<Volo[]>)request.getAttribute("voliAndataUno");
    	ArrayList<Volo[]> voliRitornoUno=(ArrayList<Volo[]>)request.getAttribute("voliRitornoUno");
    	
    	ArrayList<Volo[]> voliAndataDue=(ArrayList<Volo[]>)request.getAttribute("voliAndataDue");
    	ArrayList<Volo[]> voliRitornoDue=(ArrayList<Volo[]>)request.getAttribute("voliRitornoDue");


    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Voli andata diretti
<% for(Volo volo: voliAndataDiretti){ %>
	<p><%=volo.getId()%></p>

<% } %>

Voli ritorno diretti
<% for(Volo volo: voliRitornoDiretti){ %>
	<p><%=volo.getId()%></p>

<% } %>

Voli andata uno scalo
<% for(Volo[] volo: voliAndataUno){ %>
	<p><%=volo[0].getId()%> <%=volo[1].getId() %></p>

<% } %>

Voli ritorno uno scalo
<% for(Volo[] volo: voliRitornoUno){ %>
	<p><%=volo[0].getId()%> <%=volo[1].getId() %></p>

<% } %>

Voli andata due scalo
<% for(Volo[] volo: voliAndataDue){ %>
	<p><%=volo[0].getId()%> <%=volo[1].getId() %> <%=volo[2].getId() %></p>

<% } %>


Voli ritorno due scalo
<% for(Volo[] volo: voliRitornoDue){ %>
	<p><%=volo[0].getId()%> <%=volo[1].getId() %> <%=volo[2].getId() %></p>

<% } %>

</body>
</html>