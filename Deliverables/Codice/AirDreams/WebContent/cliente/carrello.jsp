<%@page import="java.util.HashMap"%>
<%@page import="gestioneutente.Account"%>
<%@page import="gestioneutente.Ruolo"%>
<%@page import="gestionecarrello.*"%>
<%@page import="gestionevolo.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% 
	Boolean mod=(Boolean)request.getSession().getAttribute("mod");
System.out.println("MOD SESSIONE: " + mod);
	if(mod==null)
		mod=true;
	String message=(String) request.getAttribute("message");
	
	Carrello carrello=(Carrello)request.getSession().getAttribute("carrello");
	
	HashMap<Volo,Integer> voliCarrello=carrello.getVoli();
	
	int seats=0;
	for(Map.Entry<Volo, Integer> entry : voliCarrello.entrySet()) 
   		 seats=entry.getValue();
	
	System.out.println("I posti sono "+seats);
	
%>
<html>
<head>
<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Carrello</title>
    
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
									  <a href="DettagliAccountServlet">Il mio profilo</a>
									  <a href="CarrelloServlet">Il mio carrello</a>
									  </div>
									</li>
                       					
                       					<% } else if(ruolo.equals(Ruolo.gestoreCompagnie)){ 
                       					
                       						if(mod==true){
                       					%>	
                           			   <li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="../ListaAccountServlet?page=1">Visualizza gli account</a>
									  <a href="gestoreCompagnie/aggiungiCompagnia.jsp">Aggiungi compagnia aerea</a>
									  <a href="../ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else {%>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="DettagliAccountServlet">Il mio profilo</a>
									  <a href="CarrelloServlet">Il mio carrello</a>
									  	  <a href="../ChangeMod?mod=true">Passa alla mod. gestoreCompagnie</a>
									  </div>
									</li>
                           			
									<% } %>
                       				
                       					
               					<% } else if(ruolo.equals(Ruolo.gestoreVoli)) {
               												
                           			 					
                       						if(mod==true){
                       					%>	
                           			   <li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="gestoreVoli/ListaVoliServlet?page=1&action=null">Visualizza voli</a>
									  <a href="gestoreVoli/aggiungiVolo.jsp">Aggiungi volo</a>
									  <a href="../ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else { %>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="DettagliAccountServlet">Il mio profilo</a>
									  <a href="carrello.jsp">Il mio carrello</a>
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
                <!-- /Top Navbar -->
            </div>
            
            <div class="tm-section tm-bg-img" id="tm-section-1">
                <div class="tm-bg-white">
                	<div class="container">
                    	<div>
                     	<% if (message!=null){ %>
                    		<p class="d-flex justify-content-center"><%=message%></p>
                   	 	<% } %>
                   	 	</div>
      					
                    	<div class="container ie-h-align-center-fix">
                    		<div class="form-row tm-search-form-row d-flex justify-content-center">	
                                	<div class="form-group tm-form-element tm-form-element-100">
                                		<h3 class="tm-color-primary tm-article-title-1">Il tuo carrello</h3>
                                	</div>
                            </div>
                    	    <div class="row">
                            	<div class="form-row tm-search-form-row d-flex justify-content-center">
                  					<div class="pl-5">
                  						<%
                  							if (voliCarrello.isEmpty()) {
                  						%>
                  						<div class="d-flex justify-content-center">
                  							<p>Non sono presenti voli nel carrello</p>
                  						</div>
                  						<%		
                  							} else  {
                                   				for(Map.Entry<Volo, Integer> entry : voliCarrello.entrySet()) { 
                                   		%>
                                		<div class="p-3 border border-light rounded bg-light">
                                			<div class="form-row align-items-center">
                                				<h4><%=entry.getValue()%> Biglietti/o</h4>
                                				<form action="RimuoviDalCarrelloServlet" method="post">
    												<input type="hidden" name="idVolo" value="<%=entry.getKey().getId()%>">
    												<button class="fa fa-trash" aria-hidden="true" style="margin-left: 30px;"></button>
    											</form>
    										</div>
      										<div class="form-row align-items-center">
      											<h5><%=entry.getKey().getCa().getNome() %></h5>
  									 			<h3 style="margin-left: 100px;"><%=entry.getKey().getOrarioPartenza()%></h3> 
  												<img alt="" src="../img/icon-route.png" width="200px" height="80px">
  												<h3><%=entry.getKey().getOrarioArrivo()%></h3>
  												<span style="margin-left: 10px;"> Totale : &euro; <%=entry.getKey().getPrezzo() %></span>
  											</div>
    										<b><span style="margin-left:177px;"><%=entry.getKey().getAeroportoP().getCodice()%></span> </b>
    										<span style="margin-left:100px;"><%=entry.getKey().getDurataVolo() %></span> 
    										<b><span style="margin-left:115px;"><%=entry.getKey().getAeroportoA().getCodice() %></span></b><br>
    									</div><br>
   									</div><br> 
                                    <% 		} 
                                   		} 
                                   	%> 
                                   	<br><br>
                                   	<div class="form-row tm-search-form-row">                                  
                                        <div class="form-group tm-form-element tm-form-element-2">
                                        	<%
                                        		if (!voliCarrello.isEmpty()) {
                                        	%>
                                        	<div class="form-row tm-search-form-row d-flex justify-content-center">
                                				<div class="form-group tm-form-element tm-form-element-2">
                                    				<a class="btn btn-primary tm-btn-search" href="nominativiBiglietti.jsp?seats=<%=seats%>">Procedi all'acquisto</a>
                                    			</div>
                                			</div>
                           					<% } %>
	                                    </div>
                                    </div>
                                </div>                      
                        	</div>      
                    	</div>
                	</div>                  
           		</div>
			</div>
        </div>
           <footer class="tm-bg-dark-blue">
                <div class="container">
                    <div class="row">
                        <p class="col-sm-12 text-center tm-font-light tm-color-white p-4 tm-margin-b-0">
                        Copyright &copy; <span class="tm-current-year">2019</span>      
                    </div>
                </div>                
            </footer>
       
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
          		var r = confirm("Confermi di voler annullare l'inserimento della carta di credito?");
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