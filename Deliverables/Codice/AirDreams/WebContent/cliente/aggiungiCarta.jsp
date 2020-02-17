<%@page import="gestioneutente.Account"%>
<%@page import="gestioneutente.Ruolo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% 
Boolean mod=(Boolean)request.getSession().getAttribute("mod");
System.out.println("MOD SESSIONE: " + mod);

if(mod==null)
	mod=true;
	String message=(String) request.getAttribute("message");
%>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
   
    <title>Carta di credito </title>
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
          <![endif]-->                                <!-- Templatemo style -->
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
                <div class="tm-bg-white ie-container-width-fix-2">
		        	<div>
      					<% if (message!=null) { %>
				    	<p><%=message%></p>
				    	<% } %>
				   	</div>
                    <div class="container ie-h-align-center-fix">
                        <div class="row">
                            <div class="col-xs-12 ml-auto mr-auto ie-container-width-fix">
                            	<div class="form-row tm-search-form-row d-flex justify-content-center">	
                            		<div class="form-group tm-form-element tm-form-element-100">
                                		<h3 class="tm-color-primary tm-article-title-1">Aggiungi carta di credito</h3>
                               		</div>
                            	</div>
                  				<form action="AggiungiCartaServlet" method="post" class="tm-search-form tm-section-pad-2" id="card1">
                                    <div class="form-row tm-search-form-row d-flex justify-content-center">
                                        <div class="form-group tm-form-element tm-form-element-100">
                                        	<i class="fa fa-credit-card fa-2x tm-form-element-icon"></i>
                                            <input name="nCarta" type="text" onkeyup="checkNumeroCarta(this)" class="form-control" id="inputnCarta" placeholder="Inserire numero carta" required="required">
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                        	<i class="fa fa-user fa-2x tm-form-element-icon"></i>
                                            <input name="titolare" type="text" onkeyup="checkTitolare(this)" class="form-control" id="inputTitolare" placeholder="Titolare della carta" required="required">
                                        </div>
                                    </div>
                                    <div class="form-row tm-search-form-row d-flex justify-content-center">
                                        <div class="form-group tm-form-element tm-form-element-50">   
                                        	<i class="fa fa-calendar fa-2x tm-form-element-icon"></i>                                   
                                            <input name="dataScadenza" type="text" onkeyup="checkData(this)" class="form-control" id="inputDataScadenza" placeholder="data scadenza mm/yy" required="required">
                                        </div>                                      
                                        <div class="form-group tm-form-element tm-form-element-50">
                                        	<i class="fa fa-check fa-2x tm-form-element-icon"></i>                                    
                                            <input name="cvc" type="text" onkeyup="checkCVC(this)" class="form-control" id="inputCvc" placeholder="CVC" required="required">
                                       		<br>
                                        </div>
                                    </div>
                                    <div class="form-row tm-search-form-row d-flex justify-content-center">                                  
                                        <div class="form-group tm-form-element tm-form-element-2">
                                            <button type="submit" class="btn btn-primary tm-btn-search" value="aggiungiCarta">Aggiungi</button>
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
        <script src="../scripts/validaCarta.js"></script>
        <script src="../js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="../js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="../js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="../js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="../js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="../slick/slick.min.js"></script>  
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
			<script type="text/javascript">
		
			$('#card1').submit(function(e) {
				if(!confirm("sicuro di voler proseguire?")) {
					e.preventDefault();
					location.href='DettagliAccountServlet';
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