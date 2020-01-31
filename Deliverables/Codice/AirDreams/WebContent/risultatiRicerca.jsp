<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneutente.*"%>
<!DOCTYPE html>

<% 
	Boolean mod=(Boolean)request.getAttribute("mod");
	if(mod==null)
		mod=true;
	
	Account account= (Account) request.getSession().getAttribute("account");
	System.out.println("ACCOUNT: " + account);
	
	System.out.println("MESSAGE: " + request.getAttribute("message"));
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
                            <a class="navbar-brand mr-auto" href="index.jsp">
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
                            		account=(Account)request.getSession().getAttribute("account");
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
									  <a href="listaVoli.jsp">Visualizza voli</a>
									  <a href="#">Aggiungi volo</a>
									  <a href="ChangeMod?mod=false">Passa alla mod. Cliente</a>
									  </div>
									</li>
									<% } else { %>
									<li class="nav-item dropdown">
									  <a class="nav-link dropbtn"><%=account.getNome() %></a>
									  <div class="dropdown-content">
									  <a href="profilo.jsp">Il mio profilo</a>
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
           
           	<div class="d-flex">
           		<!-- Left Navbar -->
           		<nav id="sidebar">
	  				<div class="img bg-wrap text-center py-4" style="background-image: url(img/bg-img-1.jpg);">
	  					<div class="user-logo">
	  						<div class="img"></div>
	  						<h3>Ricerca voli per</h3>
	  					</div>
	  				</div>
        			<ul class="list-unstyled components mb-5">
          				<li>
              				<a href=""><span class="fa fa-plane mr-3"></span>Aeroporto di partenza</a>
          				</li>
         			 	<li>
            				<a href=""><span class="fa fa-plane mr-3"></span>Aeroporto di destinazione</a>
          				</li>
          				<li>
            				<a href=""><span class="fa fa-calendar mr-3"></span>Data volo</a>
          				</li>
        			</ul>
				</nav>
				<!-- /Left Navbar -->
           
           		<!-- Information Sections -->
           		<div class="justify-content-center">
           			<div>
           				<!-- verranno inseriti i form per i criteri di ricerca -->
           			</div>
                	<div class="pl-3" id="info">
      					<h2> Lista voli </h2>
      				
      					<div class="pl-5"><br><!-- contiene le varie grid voli -->
      						<% for(int i=0;i<4;i++){ %>
      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5>Ryanair</h5>
  									<h3 style="margin-left: 100px;">13:20</h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3>14:00</h3>
  							
    						</div>
    									<b><span style="margin-left:177px;">NAP</span> </b>
    					<span style="margin-left:100px;">3h 25min</span> 
    					<b><span style="margin-left:115px;">TXL</span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Uno scalo</span>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<span style="color:red; margin-left: 35px;">Solo 5 posti rimasti</span>
 
    								<div class="form-row align-items-center">
  									 <h5>Ryanair</h5>
  									<h3 style="margin-left: 100px;">13:20</h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3>14:00</h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; 54 </h3>
    						</div>
    									<b><span style="margin-left:177px;">NAP</span> </b>
    					<span style="margin-left:100px;">3h 25min</span> 
    					<b><span style="margin-left:115px;">TXL</span></b><br>
    					
    					<span style="color: green; margin-left: 160px; ">Diretto</span>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<span style="color:red; margin-left: 35px;">Solo 5 posti rimasti</span>
    					<button type="submit" style="margin-left: 60px;" class="btn btn-primary">Dettagli</button>
   						
   
    						</div>
    						<hr>
  							<% } %>
      						</div>
      						<br><br>
      			 			  	
      					</div>
      							<div class="d-flex justify-content-center">
      					<h2 class="p-2"><i class="fa fa-arrow-left"></i></h2>
      					<h2 class="p-2"><i class="fa fa-arrow-right"></i></h2>
      					 
      				</div>
      				</div>
      				
      		
      			</div>
      			<!-- /Information Sections -->
      			  
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
        <script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
	</body>
</html>