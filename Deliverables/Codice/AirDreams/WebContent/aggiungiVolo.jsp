<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,gestioneutente.*"%>



<%
String message=(String)request.getAttribute("message");
Boolean mod=(Boolean)request.getAttribute("mod");

if(mod==null)
	mod=true;
%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Aggiungi volo</title>
<!--

Template 2095 Level

http://www.tooplate.com/view/2095-level

-->
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="css/datepicker.css"/>
    <link rel="stylesheet" href="css/tooplate-style.css">                                   <!-- Templatemo style -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" /> 
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
									  <a href="#">Il mio profilo</a>
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
									  <a href="#">Il mio profilo</a>
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
									  <a href="aggiungiVolo.jsp">Aggiungi volo</a>
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
            
            <div class="tm-section tm-bg-img" id="tm-section-1">
                <div class="tm-bg-white ie-container-width-fix-2">
                    <div class="container ie-h-align-center-fix">
                     <% if (message!=null){ %>
                    	<p><%=message%></p>
                   	 <% } %>
                        <div class="row">
                            <div class="col-xs-12 ml-auto mr-auto ie-container-width-fix">
                                <form action="AggiungiVoloServlet" method="post" class="tm-search-form tm-section-pad-2">
                                    <div class="form-row tm-search-form-row">
                                        <div class="form-group tm-form-element tm-form-element-50">
                                        	<i class="fa fa-plane fa-2x tm-form-element-icon"></i>
                                            <input name="city" type="text" class="form-control"  placeholder="Aeroporto di partenza" list="ricerca-datalist" onkeyup="ricerca(this.value, this.name)" required>
                                            <datalist id="ricerca-datalist"></datalist>
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                        	<i class="fa fa-plane fa-2x tm-form-element-icon"></i>
                                           <input name="cityArrivals" type="text" class="form-control" placeholder="Aeroporto di arrivo" list="ricerca-datalist" onkeyup="ricerca(this.value, this.name)" required>
                                            <datalist id="ricerca-datalist"></datalist>
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                        	<i class="fa fa-flag-o fa-2x tm-form-element-icon"></i>
                                            <input name="airline" type="text" class="form-control" placeholder="Compagnia aerea" readonly="readonly"
                                            <% if (request.getSession().getAttribute("account")!=null){
                                            		Account account=(Account)request.getSession().getAttribute("account"); 
                                            %>
                                            value="<%=account.getCompagniaAerea().getNome() %>"
                                            <% } %>	
                                            >
                                            
                                        </div>
                                    </div>
                                 	<div class="form-row tm-search-form-row">
                                    	<div class="form-group tm-form-element tm-form-element-50">
                                    	 	<i class="fa fa-calendar fa-2x tm-form-element-icon"></i>
                                           	<input name="dateDeparture" type="text" class="form-control" id="start" placeholder="Data partenza" required>
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="fa fa-euro fa-2x tm-form-element-icon"></i>
                                            <input name="price" type="text" class="form-control" placeholder="prezzo base biglietto" required>
                                        </div>
                                     </div>
                                     <div class="form-row tm-search-form-row d-flex justify-content-center">
                                    	<label class="col-sm-1.5 col-form-label">Orario partenza</label>
                                    	<div class="form-group col-auto">   
                                    		<i class="fa fa-clock-o fa-2x tm-form-element-icon"></i>     
                                        	<select name="hDeparture" class="form-control form-control-lg">
                                        		<option>h</option>
                                            	<% for(int i=0;i<=9;i++) { %>
                                           		<option>0<%=i%></option>
                                            	<% } %>
                                            	<% for(int i=10;i<=24;i++) { %>
                                           		<option><%=i%></option>
                                            	<% } %>  
                                       		</select>
                                       	</div>
                                       	<label class="col-sm-1.5 col-form-label">:</label>
                                       	<div class="form-group col-auto">
                                       	    <i class="fa fa-clock-o fa-2x tm-form-element-icon"></i>                    
                                        	<select name="minDeparture" class="form-control form-control-lg">
                                        		<option>min</option>
                                            	<% for(int i=0;i<=9;i++) { %>
                                           		<option>0<%=i%></option>
                                            	<% } %> 
                                            	<% for(int i=10;i<=59;i++) { %>
                                           		<option><%=i%></option>
                                            	<% } %>
                                        	</select>
                                   		</div>
                                   		<label class="col-sm-1.5 col-form-label">Durata volo</label>     
                                        <div class="form-group col-auto">
                                        	<i class="fa fa-clock-o fa-2x tm-form-element-icon"></i> 
                                        	<select name="hFly" class="form-control form-control-lg">
                                           		<option>h</option>
                                                <% for(int i=10;i<24;i++) { %>
                                            	<option><%=i%></option>
                                                <% } %> 
                                            </select>
                                        </div>
                                        <label class="col-sm-1.5 col-form-label">:</label>
                                        <div class="form-group col-auto">  
                                        	<i class="fa fa-clock-o fa-2x tm-form-element-icon"></i>                          
                                       		<select name="minFly" class="form-control form-control-lg">
                                            	<option>min</option>
                                                 <% for(int i=0;i<60;i++) { %>
                                            	<option><%=i%></option>
                                                <% } %> 
                                            </select>
                                        </div>
                                 	</div>
                                    <div class="form-row tm-search-form-row d-flex justify-content-center">
                                        <label class="col-sm-1.5 col-form-label">Bagaglio da stiva</label> 
                                        <div class="form-group tm-form-element tm-form-element-50"> 
                                        	<i class="fa fa-suitcase fa-2x tm-form-element-icon"></i>
                                            <select name="baggage" class="form-control form-control-lg">
                                            	<option>non compreso</option>
										        <option>compreso</option>
										    </select>
										</div>
							        </div>
                                    <div class="form-row tm-search-form-row d-flex justify-content-center">
                                   		<label class="col-sm-1.5 col-form-label">Numero totale di posti disponibili per il volo</label>
                                   		<div class="form-group col-auto">
                                   			<i class="fa fa-group fa-2x tm-form-element-icon"></i>
                                   			<input class="form-control input-group-lg" type="number" id="inputNumber2" name="seats" value="1" min="1" max="100" required>
							        	</div>
							        </div>
                                    <div class="form-row tm-search-form-row d-flex justify-content-center">
										<div class="form-group tm-form-element tm-form-element-50">
                                        	<button onclick="confermaAggiungi()" type="submit" class="btn btn-primary tm-btn-search">Aggiungi volo</button>
                                       	</div>
                                   	</div>
                                </form>
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
        <script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
		
        <script src="http://code.jquery.com/jquery-1.8.2.js"> </script>
        <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"> </script>
        <script src="scripts/ricercaAeroporti.js"> </script>
        
        <script>
      
        $("#start").datepicker({
            defaultDate:"+1w",
            dateFormat:"dd/mm/yy",
            minDate:0,
            changeMonth:false,
            numberOfMonth:1,
            onClose: function(selectedDate){
                $("#return").datepicker("option","minDate",selectedDate);
            }
         })
         
           $("#return").datepicker({
            defaultDate:"+1w",
            dateFormat:"dd/mm/yy",
            changeMonth:false,
            numberOfMonth:1,
            onClose: function(selectedDate){
                $("#start").datepicker("option","maxDate",selectedDate);
            }
         })
         
         </script>
</body>
</html>