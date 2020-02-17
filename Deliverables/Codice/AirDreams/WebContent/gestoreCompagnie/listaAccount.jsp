<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneutente.*"%>
    
<%
	String message=(String)request.getAttribute("message");
	Boolean mod=(Boolean)request.getSession().getAttribute("mod");
	int pagina=Integer.parseInt(request.getParameter("page"));
	ArrayList<Account> allUtenti = (ArrayList<Account>)request.getAttribute("allUtentiAdmin");
	System.out.println(allUtenti);
	
	if((allUtenti==null)||(allUtenti.size() == 0))
		response.sendRedirect("./ListaAccountServlet?message=" +message + "&page=" + pagina);
	
	if(mod==null)
		mod=true;
	String messageRicerca=(String)request.getAttribute("messageRicerca");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
<meta charset="ISO-8859-1">
<title>Lista account registrati</title>

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
									<% } else { %>
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
                    <div class="container">
                    	<div>
		        			<form action="RicercaAccountServlet" method="get" class="tm-search-form tm-section-pad-2">
		            	 		<span class="form-actions no-color">
									<span class="search-items">Nome</span>
									<span class="sigform_fld search-items">
										<select class="search nome" name="searchName">
											 		<option value="-">-</option>
													<option value="A">A</option>
													<option value="B">B</option>
													<option value="C">C</option>
													<option value="D">D</option>
													<option value="E">E</option>
													<option value="F">F</option>
													<option value="G">G</option>
													<option value="H">H</option>
													<option value="I">I</option>
													<option value="J">J</option>
													<option value="K">K</option>
													<option value="L">L</option>
													<option value="M">M</option>
													<option value="N">N</option>
													<option value="O">O</option>
													<option value="P">P</option>
													<option value="Q">Q</option>
													<option value="R">R</option>
													<option value="S">S</option>
													<option value="T">T</option>
													<option value="U">U</option>
													<option value="V">V</option>
													<option value="W">W</option>
													<option value="X">X</option>
													<option value="Y">Y</option>
													<option value="Z">Z</option>
											</select>
									</span>
									<span class="search-items">Cognome</span>
									<span class="sigform_fld search-items">
										<select class="search cognome" name="searchSurname">
													<option value="-">-</option>
													<option value="A">A</option>
													<option value="B">B</option>
													<option value="C">C</option>
													<option value="D">D</option>
													<option value="E">E</option>
													<option value="F">F</option>
													<option value="G">G</option>
													<option value="H">H</option>
													<option value="I">I</option>
													<option value="J">J</option>
													<option value="K">K</option>
													<option value="L">L</option>
													<option value="M">M</option>
													<option value="N">N</option>
													<option value="O">O</option>
													<option value="P">P</option>
													<option value="Q">Q</option>
													<option value="R">R</option>
													<option value="S">S</option>
													<option value="T">T</option>
													<option value="U">U</option>
													<option value="V">V</option>
													<option value="W">W</option>
													<option value="X">X</option>
													<option value="Y">Y</option>
													<option value="Z">Z</option>
     										</select>
									</span>
									<span>
										<input hidden="true" type="text" name="page" value="<%=pagina%>">
									</span>
									<span>
										<input type="submit" value="applica">
									</span>
								</span>
								<div>
      								<% if (messageRicerca!=null) { %>
				    				<p><%=messageRicerca%></p>
				    				<% } %>
				    			</div>
							</form>
                    	</div>
                    	
                        <div class="row">
                        	<%
 							    //calcolo indici di inizio e fine della griglia di visualizzazione
    							int inizio=(pagina-1)*4;
    							int fine=pagina*4;
      						
    							System.out.println("FINE E" + fine);
    							System.out.println("VOLI è" + allUtenti);
    							if (fine>allUtenti.size()) {
    								fine=allUtenti.size();
    							}
    							
    							System.out.println("INIZIO " + inizio);
    							System.out.println("FINE " + fine);                       
                        
                       			for(int i=inizio; i<fine; i++) {
                            		Account a = allUtenti.get(i);   
                            		System.out.println("RUOLO: " + a.getRuolo());
                            		if (a.getRuolo()==null || a.getRuolo().equals(Ruolo.gestoreVoli)) {
                           	%>
                            <form action="ListaAccountServlet" method="get" class="tm-search-form tm-section-pad-1 pl-5">
                            	<div class="form-row">
  									<label class="col-sm-1.5 col-form-label">Nome</label>
   	 								<div class="form-group col-md">
      									<input type="text" value="<%=a.getNome()%>" class="form-control-plaintext form-control-sm">
    								</div>
    								<label class="col-sm-1.5 col-form-label">Cognome</label>
    								<div class="form-group col-md">
      									<input type="text" value="<%=a.getCognome() %>" class="form-control-plaintext form-control-sm">
    								</div>
    								<label class="col-sm-1.5 col-form-label">Email</label>
    								<div class="form-group col-md">
      									<input type="text" value="<%=a.getEmail() %>" class="form-control-plaintext form-control-sm">
    								</div>
    								<div class="form-group col-md">
    									<a href="DettagliUtenteServlet?email=<%=a.getEmail()%>"><span class="fa fa-pencil-square-o fa-2x"></span></a>
    								</div>
    							</div>
                            </form> 
                           	<% 		} 
                       			}
                            %>                   
                        </div>
                        <div class="d-flex justify-content-center">
      						<% if (((pagina-1)*4)>0) { %>
      							<h2 class="p-2"><a href="ListaAccountServlet?page=<%=(pagina-1)%>"><i class="fa fa-arrow-left"></i></a></h2>
      						<% } %>
      						<% if (((pagina)*4)<allUtenti.size()) { %>
      							<h2 class="p-2"><a href="ListaAccountServlet?page=<%=(pagina+1)%>"><i class="fa fa-arrow-right"></i></a></h2>
      						<% } %>
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
        <script src="../scripts/validaRegistrazione.js"></script>
        <script src="../js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="../js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="../js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="../js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="../js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="../slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->

</body>
</html>