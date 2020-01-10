<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneutente.*"%>
<!DOCTYPE html>

<% Boolean mod=(Boolean)request.getAttribute("mod");

if(mod==null)
	mod=true;
%>

<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>AirDreams</title>
    
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/datepicker.css"/>
    <link rel="stylesheet" href="css/tooplate-style.css"> 
    <link rel="stylesheet" href="css/prova.css">
</head>
<body>
        <div class="tm-main-content" id="top">
            <div class="tm-top-bar-bg"></div>
            <div class="tm-top-bar" id="tm-top-bar">
                <!-- Top Navbar -->
                <div class="container">
                    <div class="row">
                        <nav class="navbar navbar-expand-lg narbar-light">
                            <a class="navbar-brand mr-auto" href="#">
                                <img src="img/logo.png" alt="Site logo">
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
									  <a href="profilo.jsp">Il mio profilo</a>
									  <a href="#">Il mio carrello</a>
									  </div>
									</li>
                       					
                       					<% } else if(ruolo.equals(Ruolo.gestoreCompagnie)){ 
                       					
                       						if(mod==true){
                       					%>	
                           			   <li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="#">Visualizza gli account</a>
									  <a href="#">Aggiungi compagnia aerea</a>
									  <a href="ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else {%>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="profilo.jsp">Il mio profilo</a>
									  <a href="#">Il mio carrello</a>
									  	  <a href="ChangeMod?mod=true">Passa alla mod. gestoreCompagnie</a>
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
									  <a href="ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else { %>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="#">Il mio profilo</a>
									  <a href="#">Il mio carrello</a>
									  	  <a href="ChangeMod?mod=true">Passa alla mod. gestoreVoli</a>
									  </div>
									</li>
                           			
									<% } %>
               					
               					<% } %>
                       					
									<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
                       	
								<% } %>
                            
                        	</ul> 
                        	 
                        	</div>                         
                        </nav>            
                    </div>
                </div>
            </div>
           
           	<div class="d-inline-flex">
           		<!-- Left Navbar -->
           		<nav id="sidebar">
	  				<div class="img bg-wrap text-center py-4" style="background-image: url(img/bg-img-1.jpg);">
	  					<div class="user-logo">
	  						<div class="img"></div>
	  						<%
	  							if (request.getSession().getAttribute("account")==null){  
	  						%>
	  						<h3>Nome utente</h3>
	  						<% } else { 
	  							Account account=(Account)request.getSession().getAttribute("account");
	  						%>
	  						<h3><%=account.getNome()%></h3>
	  						<% } %>
	  					</div>
	  				</div>
        			<ul class="list-unstyled components mb-5">
          				<li>
            				<a href=""><span class="fa fa-user mr-3"></span>Informazioni personali</a>
          				</li>
          				<li>
              				<a href=""><span class="fa fa-credit-card-alt mr-3"></span>Metodi di pagamento</a>
          				</li>
         			 	<li>
            				<a href=""><span class="fa fa-cube mr-3"></span>Ordini</a>
          				</li>
        			</ul>
				</nav>
				<!-- /Left Navbar -->
           
           		<div>
           			<div>
           				<br><br>
           			</div>
                	<div class="p-2" id="info">
      					<h2>Informazioni personali</h2>
      					<form action="DettagliAccountServlet" method="post" class="tm-search-form tm-section-pad-2">
                        	<div class="form-group row">
   					 			<label class="col-sm-3 col-form-label">Nome</label>
   					 			<div class="col-sm-5">
   					 				<input type="text" class="form-control" id="nome">
   					 			</div>
  							</div>
							<div class="form-group row">
   					 			<label class="col-sm-3 col-form-label">Cognome</label>
   					 			<div class="col-sm-5">
   					 				<input type="text" class="form-control form-control-sm" id="nome">
   					 			</div>
  							</div>
							<div class="form-group row">
   					 			<label class="col-sm-3 col-form-label">Email</label>
   					 			<div class="col-sm-5">
   					 				<input type="text" class="form-control form-control-sm" id="nome">
   					 			</div>
  							</div>
							<div class="form-group row">
   					 			<label class="col-sm-3 col-form-label">Password</label>
   					 			<div class="col-sm-5">
   					 				<input type="text" class="form-control form-control-sm" id="nome">
   					 			</div>
  							</div>			
  				 							
  							<button type="submit" class="btn btn-primary">Modifica </button>
                         </form>	
      				</div>
      				<div class="p-2" id="pagamenti">
      					<h2>Metodi di pagamento</h2>
      					<form action="" method="post" class="tm-search-form tm-section-pad-2">
  							<div class="form-row">
  								<label class="col-sm-2 col-form-label">Numero carta</label>
   	 							<div class="col-sm-3">
      								<input type="text" class="form-control form-control-sm">
    							</div>
    							<label class="col-sm-1 col-form-label">Titolare</label>
    							<div class="col-sm-2">
      								<input type="text" class="form-control form-control-sm">
    							</div>
    							<label class="col-sm-1 col-form-label">Scadenza</label>
    							<div class="col-sm-2">
      								<input type="text" class="form-control form-control-sm">
    							</div>
    						</div>
						</form>
      				</div>
      				<div class="p-2" id="ordini">
      					<h2>Ordini</h2>
      					<form action="" method="post" class="tm-search-form tm-section-pad-2">
  							<div class="form-row">
  								<label class="col-sm-2 col-form-label">Codice ordine</label>
   	 							<div class="col-sm-2">
      								<input type="text" class="form-control form-control-sm">
    							</div>
    							<label class="col-sm-2 col-form-label">Lista biglietti</label>
    							<div class="col-sm-2">
      								<select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
    									<option selected>Lista Biglietti</option>
   		 								<option value="1">One</option>
    									<option value="2">Two</option>
    									<option value="3">Three</option>
  									</select>
    							</div>
    							<label class="col-sm-2 col-form-label">Totale spesa</label>
    							<div class="col-sm-2">
      								<input type="text" class="form-control form-control-sm">
    							</div>
    						</div>
						</form>
      				</div>
      				<div class="tm-section tm-position-relative">
      					<div class="container tm-pt-3 tm-pb-3">
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
        <script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
	</body>
</html>