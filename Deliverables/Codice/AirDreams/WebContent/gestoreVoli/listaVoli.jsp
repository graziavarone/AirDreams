<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneutente.*,gestionevolo.*,java.time.*,java.time.format.*"%>
<!DOCTYPE html>

<% 
	Boolean mod=(Boolean)request.getSession().getAttribute("mod");
System.out.println("MOD SESSIONE: " + mod);
	if(mod==null)
		mod=true;
	
	Account account= (Account) request.getSession().getAttribute("account");
	DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>AirDreams</title>
    
    <!-- load stylesheets -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" type="text/css" href="../slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="../slick/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="../css/datepicker.css"/>
    <link rel="stylesheet" href="../css/tooplate-style.css"> 
    <link rel="stylesheet" href="../css/prova.css">
    
    
</head>
<body>
		<%
			int pagina=Integer.parseInt(request.getParameter("page"));
			String action=request.getParameter("action");
			ArrayList<Volo> voli= (ArrayList<Volo>) request.getAttribute("voli");
			
			System.out.println("I voli sono" +voli);

			
			pagina= (int) request.getAttribute("page");
		%>
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
                            		account=(Account)request.getSession().getAttribute("account");
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
									  <a href="../ListaAccountServlet?page=1">Visualizza gli account</a>
									  <a href="gestoreCompagnie/aggiungiCompagnia.jsp">Aggiungi compagnia aerea</a>
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
									  <a href="ListaVoliServlet?page=1&action=null">Visualizza voli</a>
									  <a href="aggiungiVolo.jsp">Aggiungi volo</a>
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
           
           	<div class="d-flex">
           		<!-- Left Navbar -->
           		<nav id="sidebar">
	  				<div class="img bg-wrap text-center py-4" style="background-image: url(../img/bg-img-1.jpg);">
	  					<div class="user-logo">
	  						<div class="img"></div>
	  						<h3>Ricerca voli per</h3>
	  					</div>
	  				</div>
        			<ul class="list-unstyled components mb-5">
          				<li>
              				<a href="javascript:visiblePartenza()"><span class="fa fa-plane mr-3"></span>Aeroporto di partenza</a>
          				</li>
         			 	<li>
            				<a href="javascript:visibleDestinazione()"><span class="fa fa-plane mr-3"></span>Aeroporto di destinazione</a>
          				</li>
          				<li>
            				<a href="javascript:visibleData()"><span class="fa fa-calendar mr-3"></span>Data volo</a>
          				</li>
        			</ul>
				</nav>
				<!-- /Left Navbar -->
           
           		<!-- Information Sections -->
           		<div class="justify-content-center">
           			<div>
           				<!--  verranno inseriti i form per i criteri di ricerca -->
           				<form action="ListaVoliServlet"  method="post" class="tm-search-form tm-section-pad-2">
           					<input type="hidden" name="page" value="1">
           					<input type="hidden" name="action" value="ricerca">
                        	<div class="form-group row" id="formPartenza" hidden="true">
   					 			<label class="col-sm-6 col-form-label">Inserire aeroporto di partenza</label>
   					 			<div class="col-sm-5 tm-form-element ">
                                	<i class="fa fa-plane fa-2x tm-form-element-icon"></i>
                                    <input name="city" type="text" class="form-control" value="" id="partenza" list="ricerca-datalist" onkeyup="checkAeroporto(this)" onkeyup="ricerca(this.value,this.name)">
                                	<datalist id="ricerca-datalist"></datalist>
                                </div>
                            </div>
                          	<div class="form-group row" id="formDestinazione" hidden="true">
   					 			<label class="col-sm-6 col-form-label">Inserire aeroporto di destinazione</label>
   					 			<div class="col-sm-5 tm-form-element ">
                                	<i class="fa fa-plane fa-2x tm-form-element-icon"></i>
                                    <input name="cityArrivals" type="text" class="form-control" value="" id="destinazione" list="ricerca-datalist" onkeyup="checkAeroporto(this)" onkeyup="ricerca(this.value,this.name)">
                               		<datalist id="ricerca-datalist"></datalist>
                                </div>	
                            </div>
                            <div class="form-group row" id="formData" hidden="true">
                            	<label class="col-sm-6 col-form-label">Inserire data del volo</label>
   					 			<div class="col-sm-5 tm-form-element">
                                	<i class="fa fa-calendar fa-2x tm-form-element-icon"></i>
                                    <input name="data" type="text" class="form-control" id="start" onkeyup="checkData(this)">
                                </div>
  							</div>			
  							<button id="buttonForm" type="submit" class="btn btn-primary" hidden="true">Ricerca </button>
                         </form>
           			</div>
                	<div class="pl-5" id="info">
      					<h2> Lista voli </h2>
      					<div>
      						<% if (request.getAttribute("message")!=null) { %>
				    		<p><%=request.getAttribute("message")%></p>
				    		<% } %>
      					</div>

      					<div class="pl-5"><br><!-- contiene le varie grid voli -->
      						<%
      							//calcolo indici di inizio e fine della griglia di visualizzazione
    							int inizio=(pagina-1)*4;
    							int fine=pagina*4;
      						
    							System.out.println("FINE E"+fine);
    							System.out.println("VOLI è"+voli);
    							if (fine>voli.size()) {
    								fine=voli.size();
    							}
    							System.out.println("INIZIO " + inizio);
    							System.out.println("FINE " + fine);
      							for(int i=inizio;i<fine;i=i+1) {
      						%>
      						<!-- grid voli -->
      						<div class="p-3 border border-light rounded bg-light">
      							<div class="form-row align-items-center">
  									<label class="col-sm-1.5 col-form-label font-weight-bold"> Ora partenza</label>
   	 								<div class="col-sm-3">
      									<input type="text" value="<%=voli.get(i).getOrarioPartenza()%>" class="form-control-plaintext form-control-sm">
    								</div>
    								<label class="col-sm-1.5 col-form-label font-weight-bold">Ora arrivo</label>
    								<div class="col-sm-2">
      									<input type="text" value="<%=voli.get(i).getOrarioArrivo()%>" class="form-control-plaintext form-control-sm">
    								</div>
    							</div>
    							<div class="form-row align-items-center">
  									<label class="col-sm-1.5 col-form-label font-weight-bold"> Durata volo</label>
   	 								<div class="col-sm-2">
      									<input type="text" value="<%=voli.get(i).getDurataVolo()%>" class="form-control-plaintext form-control-sm">
    								</div>
    							</div><br>
    							<div class="form-row align-items-center">
  									<label class="col-sm-1.5 col-form-label font-weight-bold"> Aeroporto partenza</label>
   	 								<div class="col-sm-3">
      									<input type="text" value="<%=voli.get(i).getAeroportoP().getNome()%>" class="form-control-plaintext form-control-sm">
    								</div>
    							</div>
    							<div class="form-row align-items-center">
    								<label class="col-sm-1.5 col-form-label font-weight-bold">Aeroporto arrivo</label>
    								<div class="col-sm-4">
      									<input type="text" value="<%=voli.get(i).getAeroportoA().getNome()%>" class="form-control-plaintext form-control-sm">
    								</div>
    							</div>
    							<div class="form-row align-items-center">
    								<label class="col-sm-1.5 col-form-label font-weight-bold">Data partenza</label>
    								<div class="col-sm-4">
      									<input type="text" value="<%=voli.get(i).getDataPartenza().format(FORMATO_DIA)%>" class="form-control-plaintext form-control-sm">
    								</div>
    							</div><br>
    							<div class="form-row align-items-center">
    								<label class="col-sm-1.5 col-form-label badge badge-info text-wrap text-white"> Prezzo biglietto</label>
    								<div class="col-sm-1">
      									<input type="text" value="<%=voli.get(i).getPrezzo()%>" class="form-control-plaintext form-control-sm">
    								</div>
    							</div>
    							<div class="form-row align-items-center">
   	 								<div class="col-sm-4"></div>
    								<div class="col-sm-5">
    									<form action="dettagliVoloServlet">
    										<input type="hidden" id="custId" name="idVolo" value="<%=voli.get(i).getId()%>">
    										<button type="submit" class="btn btn-primary">Visualizza dettagli volo</button>
    									</form>
    								</div>
    								<div class="col-sm-1"></div>
    								<div class="col-sm-2">
										<form action="EliminaVoloServlet" id="form1">
    										<input type="hidden"  id="custId" name="idVolo" value="<%=voli.get(i).getId()%>">
    										<button type="submit" class="btn btn-primary">
    											<i class="fa fa-trash" aria-hidden="true"></i>
    										</button>
    									</form>
    								</div>
    							</div> 
      						</div>
      						<br><br>
      						<!-- /grid voli -->
      						<%  }  %>      					
      					</div>
      				</div>
      				<div class="d-flex justify-content-center">
      					<% if (((pagina-1)*4)>0) { %>
      					<h2 class="p-2"><a href="ListaVoliServlet?page=<%=(pagina-1)%>&action=<%=action%>"><i class="fa fa-arrow-left"></i></a></h2>
      					<% } %>
      					<% if (((pagina)*4)<voli.size()) { %>
      					<h2 class="p-2"><a href="ListaVoliServlet?page=<%=(pagina+1)%>&action=<%=action%>"><i class="fa fa-arrow-right"></i></a></h2>
      					<% } %>
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
        </div>
        
        <!-- load JS files -->
        <script src="../js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="../js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="../js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="../js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="../js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="../slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
		
        <script src="http://code.jquery.com/jquery-1.8.2.js"> </script>
        <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"> </script>
        <script src="../scripts/validaAeroporti.js"> </script> 
        <script src="../scripts/validaVolo.js"> </script>
        <script src="../scripts/ricercaAeroporti.js"> </script>
                   <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
		<script>
		
		
	        $("#form1").submit(function(e) {    
	        	if(!confirm("sei sicuro di voler eliminare il volo?")){      
	        		e.preventDefault();  
	            } 
	        }); 
	        
			function visiblePartenza() {
				document.getElementById("formPartenza").removeAttribute("hidden");
				document.getElementById("buttonForm").removeAttribute("hidden");
			}
			
			function visibleDestinazione() {
				document.getElementById("formDestinazione").removeAttribute("hidden");
				document.getElementById("buttonForm").removeAttribute("hidden");				
			}
			
			function visibleData() {
				document.getElementById("formData").removeAttribute("hidden");
				document.getElementById("buttonForm").removeAttribute("hidden");			
			}
			
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