<%@page import="gestionecompagniaaerea.PoliticaBagaglioStiva"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,gestioneutente.*,gestionecompagniaaerea.*"%>
    
<%
    String message=(String)request.getAttribute("message");

     Boolean mod=(Boolean)request.getSession().getAttribute("mod");
     
     PoliticaBagaglioMano politicaMano=(PoliticaBagaglioMano)request.getAttribute("politicaMano");
     PoliticaBagaglioStiva politicaStiva=(PoliticaBagaglioStiva)request.getAttribute("politicaStiva");
     

    if(mod==null)
    	mod=true;
    %>
<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
   
    <title>AirDreams</title>
<!--
Template 2095 Level
http://www.tooplate.com/view/2095-level
-->
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" type="text/css" href="../slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="../css/datepicker.css"/>
    <link rel="stylesheet" href="../css/tooplate-style.css">                                   <!-- Templatemo style -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
          <![endif]-->
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
									  <a href="cliente/DettagliAccountServlet">Il mio profilo</a>
									  <a href="cliente/CarrelloServlet">Il mio carrello</a>
									  </div>
									</li>
                       					
                       					<% } else if(ruolo.equals(Ruolo.gestoreCompagnie)){ 
                       					
                       						if(mod==true){
                       					%>	
                           			   <li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="ListaAccountServlet?page=1&message=null">Visualizza gli account</a>
									  <a href="aggiungiCompagnia.jsp">Aggiungi compagnia aerea</a>
									  <a href="../ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else {%>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="cliente/DettagliAccountServlet">Il mio profilo</a>
									  <a href="cliente/CarrelloServlet">Il mio carrello</a>
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
									  <a href="cliente/DettagliAccountServlet">Il mio profilo</a>
									  <a href="cliente/carrello.jsp">Il mio carrello</a>
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
                <div class="tm-bg-white ie-container-width-fix-2">
                	<div></div>
                    <div>
                     <% if (message!=null){ %>
                    	<p class="d-flex justify-content-center"><%=message%></p>
                   	 <% } %>
                   	 </div>

                    <div class="container ie-h-align-center-fix">
                        <div class="row">
                            <div class="col-xs-12 ml-auto mr-auto ie-container-width-fix">
                            	<div class="form-row tm-search-form-row d-flex justify-content-center">	
                            		<div class="form-group tm-form-element tm-form-element-100">
                                		<h3 class="tm-color-primary tm-article-title-1">Modifica <%=politicaMano.getCompagnia().getNome()%></h3>
                               		</div>
                            	</div>
                  				<form action="AggiornaCompagniaAereaServlet" method="post" class="tm-search-form tm-section-pad-2" id="form1">
                                	<input type="hidden" name="nome" value="<%=politicaMano.getCompagnia().getNome()%>">
                                 		<div class="form-row tm-search-form-row">
                                      		<div class="form-group tm-form-element tm-form-element-50">
                                      			<i class="fa fa-globe fa-2x tm-form-element-icon"></i>
                                      			<input name="sitoCompagnia" type="text" value="<%=politicaMano.getCompagnia().getSito()%>"  class="form-control"placeholder="Type site.." required="required">
                                        		<br>
                                        	</div>
                                  		</div>
            							<div class="form-row tm-search-form-row">
                                        	<div class="form-group tm-form-element tm-form-element-100">
                                        		<i class="fa fa-suitcase fa-2x tm-form-element-icon"></i>
                                           	 	<input name="pesoMano" type="number"  value="<%=politicaMano.getPeso()%>" class="form-control"  placeholder="Peso bagaglio a mano" required="required">
                                   			</div>
                                     		<div class="form-group tm-form-element tm-form-element-50">
                                         		<i class="fa fa-suitcase fa-2x tm-form-element-icon"></i>
                                           		<input name="dimensioniMano" type="text"  value="<%=politicaMano.getDimensioni()%>" class="form-control"placeholder="Dimensioni bagaglio a mano" required="required">
                                        		<br>
                                       	 	</div>
                                		</div>
            							<div class="form-row tm-search-form-row">
                                        	<div class="form-group tm-form-element tm-form-element-100">
                                        		<i class="fa fa-suitcase fa-2x tm-form-element-icon"></i>
                                           		<input name="pesoStiva" type="number" value="<%=politicaStiva.getPeso()%>" class="form-control"  placeholder="Peso bagaglio stiva" required="required">
                                   			</div>
                                     		<div class="form-group tm-form-element tm-form-element-50">
                                     			<i class="fa fa-suitcase fa-2x tm-form-element-icon"></i>
                                         		<input name="dimensioniStiva" type="text"  value="<%=politicaStiva.getDimensioni()%>" class="form-control"placeholder="Dimensioni bagaglio stiva" required="required">
                                        		<br>
                                        	</div>
                                        	<div class="form-group tm-form-element tm-form-element-100">
                                        		<i class="fa fa-euro fa-2x tm-form-element-icon"></i>                                    
                                            	<input type="number" name="prezzoStiva" class="form-control" placeholder="Prezzo bagaglio stiva" value="<%=politicaStiva.getPrezzo()%>" step="0.01" required="required"> 
                                        	<br>
                                        	</div>   
                              			</div>
                              			<h6><a href="../index.jsp">Annulla modifica</a></h6> 
            							<div class="form-row tm-search-form-row d-flex justify-content-center">  
                                        	<div class="form-group tm-form-element tm-form-element-2">
                                        		<input type="submit" class="btn btn-primary tm-btn-search" value="Modifica">
                                        </div>
                                      </div>
                                </form>
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
        </div>
        
        <!-- load JS files -->
        <script src="../js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="../js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="../js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="../js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="../js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="../slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
</body>
</html>