<%@page import="java.util.HashMap"%>
<%@page import="gestioneutente.Account"%>
<%@page import="gestioneutente.Ruolo"%>
<%@page import="gestionecarrello.*"%>
<%@page import="gestionevolo.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% Boolean mod=(Boolean)request.getAttribute("mod");

if(mod==null)
	mod=true;
	String message=(String) request.getAttribute("message");
	
	 int seats=Integer.parseInt(request.getParameter("seats"));

	System.out.println("Numero biglietti "+seats);
	Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
	HashMap<Volo, Integer> voliCarrello=carrello.getVoli();
%>
<html>
<head>
<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Biglietti</title>
    
    	<!-- load stylesheets -->
    	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    	<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    	<link rel="stylesheet" href="../css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    	<link rel="stylesheet" type="text/css" href="../slick/slick.css"/>
    	<link rel="stylesheet" type="text/css" href="../slick/slick-theme.css"/>
    	<link rel="stylesheet" type="text/css" href="../css/datepicker.css"/>
    	<link rel="stylesheet" href="../css/tooplate-style.css">                                   <!-- Templatemo style -->
</head>
<body>
<div class="tm-main-content" id="top">
            <div class="tm-top-bar-bg"></div>
            <div class="tm-top-bar" id="tm-top-bar">
                <!-- Top Navbar -->
                <div class="container">
                    <div class="row">
                        <nav class="navbar navbar-expand-lg narbar-light">
                            <a class="navbar-brand mr-auto" href="../index.jsp">
                                <img src="../img/logo.png" alt="Site logo">
                            </a>
                            <button type="button" id="nav-toggle" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#mainNav" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div id="mainNav" class="collapse navbar-collapse tm-bg-white">
                            
                            <ul class="navbar-nav ml-auto">
                            	<% if (request.getSession().getAttribute("account")==null){ %>
                            		<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                            	<% } %>
                            	<%  if (request.getSession().getAttribute("account")!=null){
                            		Account account=(Account)request.getSession().getAttribute("account");
                            		Ruolo ruolo=account.getRuolo();
                            		
                            		if(ruolo==null){
                            		%>
                            		
                           			   <li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="#">Il mio profilo</a>
									  <a href="CarrelloServlet">Il mio carrello</a>
									  </div>
									</li>
                       					
                       					<% } else if(ruolo.equals(Ruolo.gestoreCompagnie)){ 
                       					
                       						if(mod==true){
                       					%>	
                           			   <li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="ListaAccountServlet">Visualizza gli account</a>
									  <a href="#">Aggiungi compagnia aerea</a>
									  <a href="../ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else {%>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="#">Il mio profilo</a>
									  <a href="#">Il mio carrello</a>
									  	  <a href="./ChangeMod?mod=true">Passa alla mod. gestoreCompagnie</a>
									  </div>
									</li>
                           			
									<% } %>
                       				
                       					
               					<% } else if(ruolo.equals(Ruolo.gestoreVoli)) {
               												
                           			 					
                       						if(mod==true){
                       					%>	
                           			   <li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="#">Visualizza voli</a>
									  <a href="#">Aggiungi volo</a>
									  <a href="../ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else { %>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="#">Il mio profilo</a>
									  <a href="CarrelloServlet">Il mio carrello</a>
									  	  <a href="../ChangeMod?mod=true">Passa alla mod. gestoreVoli</a>
									  </div>
									</li>
                           			
									<% } %>
               					
               					<% } %>
                       					
									<li class="nav-item"><a class="nav-link" href="../LogoutServlet">Logout</a></li>
                       	
								<% } %>
                            
                        	</ul> 
                        	 
                        	</div>                         
                        </nav>            
                    </div>
                </div>
            </div>
            
            <div class="tm-section tm-bg-img" id="tm-section-1">
                <div class="tm-bg-white ie-container-width-fix-2">
              
                    <div class="container ie-h-align-center-fix">
            
                        <div class="row">
                            <div class="col-xs-12 ml-auto mr-auto ie-container-width-fix">
                           		<%
      						if (request.getAttribute("message")!=null && !request.getAttribute("message").equals("")) {
      					%>
      					<div class="alert alert-primary" role="alert">
      						<h6><%=request.getAttribute("message")%></h6>
      						</div>
      							<% } %>
      							
                  				<form action="BigliettiServlet" method="post">
                                   		<div class="pl-5">
                                   		
                                   		<% for(int i=0;i<seats;i+=1) { %>
                                <div class="p-3 border border-light rounded bg-light">
                                	<h4>Dettagli del passeggero <%=i+1 %></h4>
                                		
    									 <div class="form-row tm-search-form-row">
                                		<div class="form-group tm-form-element tm-form-element-50">
                                           	<input name="nomePasseggero<%=i+1%>" type="text" class="form-control"  placeholder="Nome" required>
                                        	<input name="cognomePasseggero<%=i+1%>" type="text" class="form-control"  placeholder="Cognome" required>
                                        	<input type="radio" name="sesso<%=i+1%>" required="required" value="F">F
                                        	<input type="radio" name="sesso<%=i+1%>" required="required" value="M">M
                                        	
                                        	<% 
                                        	boolean bagaglioCompreso=false;
                                        	for(Map.Entry<Volo, Integer> entry : voliCarrello.entrySet()) { 
                                        	
                                        		if (entry.getKey().isCompreso()) {%>
                                        
                                        		<%  bagaglioCompreso=true ;} %>
                                        	<% } %>
                                        
                                      	Aggiungi bagaglio<input type="number" 
                                      	<% if(bagaglioCompreso==true){ %>
                                      	min="1"
                                      	value=1
                                      	<% } else { %>
                                      	min="0"
                                      	value=0
                                      	<% } %>
                                      	 name="bagaglioStiva<%=i+1%>" required="required">
                                        		
                                        </div>
    							<br>
    							</div>
    						
    						
    							<br>
   					
   					<% } %>
   					
   					</div>
   								
                                            <button type="submit" class="btn btn-primary tm-btn-search" >Continua</button>
                                    
   							</form>		
    					
    							
                             
                                    
                              
                            </div>                        
                        </div>      
                    </div>
                </div>                  
            </div>

            
        </div>
        
         <!-- load JS files -->
        <script src="../js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="../js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="../js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="../js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="../js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="../slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
			<script type="text/javascript">
		
			$('#card1').submit(function(e) {
				if(!confirm("sicuro di voler proseguire?")) {
					e.preventDefault();
				}
			});
			
        	function annullaInserimentoCarta() {
          		var r = confirm("Cofermi di voler annullare l'inserimento della carta di credito?");
          		if (r == true) 
          			location.href = 'AggiungiCartaServlet';
          	}
          						
          	function editabiliInfo() {
          		document.getElementById("nCarta").removeAttribute("readonly");
          		document.getElementById("titolare").removeAttribute("readonly");
          		document.getElementById("dataScadenza").removeAttribute("readonly");
          		document.getElementById("cvc").removeAttribute("readonly");
          		document.getElementById("email").removeAttribute("hidden");
          	}
        </script>

</body>
</html>