<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneutente.*,gestionevolo.*, java.time.*, java.time.temporal.*"%>
<!DOCTYPE html>

<% 
	Boolean mod=(Boolean)request.getAttribute("mod");
	if(mod==null)
		mod=true;
	
		Account account= (Account) request.getSession().getAttribute("account");
		
	  ArrayList<Volo> voliAndataDiretti=(ArrayList<Volo>)request.getSession().getAttribute("voliAndataDiretti");
	  ArrayList<Volo> voliRitornoDiretti=(ArrayList<Volo>)request.getSession().getAttribute("voliRitornoDiretti");
	
	  ArrayList<Volo[]> voliAndataUnoScalo=(ArrayList<Volo[]>)request.getSession().getAttribute("voliAndataUno");
	  ArrayList<Volo[]> voliRitornoUnoScalo=(ArrayList<Volo[]>)request.getSession().getAttribute("voliRitornoUno");
	  
	  ArrayList<Volo[]> voliAndataDueScali=(ArrayList<Volo[]>)request.getSession().getAttribute("voliAndataDue");
	  ArrayList<Volo[]> voliRitornoDueScali=(ArrayList<Volo[]>)request.getSession().getAttribute("voliRitornoDue");
	  
	  boolean testVoliAndata=false;
	  if(voliAndataDiretti!=null && voliAndataUnoScalo!=null && voliAndataDueScali!=null){
	   testVoliAndata=voliAndataDiretti.size()==0 && voliAndataUnoScalo.size()==0 && voliAndataDueScali.size()==0;
	  }
	  boolean testVoliRitorno=false;
	  boolean soloVoliDirettiRitorno=false;
	  if(voliRitornoDiretti!=null && voliRitornoUnoScalo!=null && voliRitornoDueScali!=null){
	   testVoliRitorno= voliRitornoDiretti.size()==0 && voliRitornoUnoScalo.size()==0 && voliRitornoDueScali.size()==0;
	   soloVoliDirettiRitorno=voliRitornoDiretti!=null&&voliRitornoUnoScalo==null&&voliRitornoDueScali==null;
	  }
	  
	  boolean soloVoliDirettiAndata=voliAndataDiretti!=null && voliAndataUnoScalo==null && voliAndataDueScali==null;
	  System.out.println(soloVoliDirettiAndata);
		
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
	  				
	  				<div>
	  				<form method="post" action="FiltraRisultatiServlet" >
							<%  if(voliRitornoDiretti!=null && voliRitornoUnoScalo!=null && voliRitornoDueScali!=null){%>
              			<input type="checkbox"  name="DirettoAndata" value="DirettoAndata" style="margin-left: 30px;">Solo voli diretti (Andata)<br>
          			
          						<input type="checkbox"  name="DirettoRitorno" value="DirettoRitorno" style="margin-left: 30px;">Solo voli diretti (Ritorno)
          				
            				<div class="slidecontainer">
						  Durata volo (Andata) <input type="range" name="durataAndata" min="0" max="24" value="3" class="slider" id="myRangeDurata">
						    <p style="color: white;">Ore: <span id="demoAndata"></span>h</p>
						</div>
						<div class="slidecontainer">
						  Durata volo (Ritorno) <input type="range" name="durataRitorno" min="0" max="24" value="3" class="slider" id="myRangeDurata2">
						    <p style="color: white;">Ore: <span id="demoRitorno"></span>h</p>
						</div>
          		
            			<div class="slidecontainer">
						  Prezzo volo (Andata)<input type="range" name="prezzoAndata" min="0" max="3000" value="100" class="slider" id="myRangePrezzo">
						    <p style="color: white;">Prezzo: &euro; <span id="demoAndata2"></span></p>
						</div>
							<div class="slidecontainer">
						  Prezzo volo (Ritorno)<input type="range" name="prezzoRitorno" min="0" max="3000" value="100" class="slider" id="myRangePrezzo2">
						    <p style="color: white;">Prezzo: &euro; <span id="demoRitorno2"></span></p>
						</div>
						<% } else { %> 
							<input type="checkbox"  name="DirettoAndata" value="DirettoAndata" style="margin-left: 30px;">Solo voli diretti<br>
          			
          					
            				<div class="slidecontainer">
						  Durata volo  <input type="range" name="durataAndata" min="0" max="24" value="3" class="slider" id="myRangeDurata">
						    <p style="color: white;">Ore: <span id="demoAndata"></span>h</p>
						</div>
					
            			<div class="slidecontainer">
						  Prezzo volo <input type="range" name="prezzoAndata" min="0" max="3000" value="100" class="slider" id="myRangePrezzo">
						    <p style="color: white;">Prezzo: &euro; <span id="demoAndata2"></span></p>
						</div>
						
						<% } %>
          			
          					<button type="submit"  class="btn btn-primary">Filtra</button>
          				</form>
	  				
	  				
	  				
	  				</div>
        		

        		
				</nav>
				<!-- /Left Navbar -->
           	
           		<!-- Information Sections -->
           		<div class="justify-content-center">
           			<div>
           				<!-- verranno inseriti i form per i criteri di ricerca -->
           			</div>
           			</div>
           			
                	<div class="pl-3" id="info">
                		<% if((testVoliAndata==true && testVoliRitorno==true) || (testVoliAndata==true && testVoliRitorno==false)){ %>
                		<p> Non ci sono voli disponibili </p>
                		<% } else if(voliRitornoDiretti==null && voliRitornoUnoScalo==null && voliRitornoDueScali==null) {%>
                		
                		<div class="pl-5"><!-- contiene le varie grid voli -->
                			<% for(Volo voloAndata: voliAndataDiretti){
      	
      							int oreDurataAndata=voloAndata.getDurataVolo().getHour();
  								int minutiDurataAndata=voloAndata.getDurataVolo().getMinute();
  								String durataFormatAndata=oreDurataAndata+"h"+" "+minutiDurataAndata+"min";
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5><%=voloAndata.getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata.getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata.getOrarioArrivo().toString()%></h3>
  								<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata.getPrezzo()%>    </h3>
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata.getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata.getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: green; margin-left: 155px; ">Diretto</span>
    						<% 
    					int posti;
    					if((posti=voloAndata.getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
    					
    					<% if(voloAndata.isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    				
 						
  						<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
  					
   					
   									</div> 
   									<br>
   									
   								<%  }%>
   									</div>
   									
   								<% if(soloVoliDirettiAndata==false){ %>
   								<div class="pl-5"><!-- contiene le varie grid voli -->
   						        <% for(Volo[] voloAndata: voliAndataUnoScalo){
   						        	LocalTime totale;
   									
   									LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
   									
   									LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
   									LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
   									
   									LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
   									
   									long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
   									
   									
   									totale=durataPrimoVolo.plusMinutes(durataScalo1);
   									
   									LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
   									
   									totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
   									
   									
   									String durataFormatAndata=totale.getHour()+"h"+" "+totale.getMinute()+"min";
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[1].getOrarioArrivo().toString()%></h3>
  								<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()%>    </h3>
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[1].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Uno scalo</span>
    						<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
    					
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    				
 						
  						<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
  							</div> 
    							<br>
   								<%  }%>
   						</div>			
    							<div>
                			</div>
                			
                			
                			<div class="pl-5"><!-- contiene le varie grid voli -->
   						        <% for(Volo[] voloAndata: voliAndataDueScali){
   						        	LocalTime totale;
   									
   									LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
   									
   									LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
   									LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
   									
   									LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
   									
   									long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
   									
   									
   									totale=durataPrimoVolo.plusMinutes(durataScalo1);
   									
   									LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
   									
   									totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
   									
   									LocalDateTime dataArrivoSecondoVolo=voloAndata[1].getDataArrivo();
   	  								LocalDateTime dataPartenzaTerzoVolo=LocalDateTime.of(voloAndata[2].getDataPartenza(),voloAndata[2].getOrarioPartenza());
   	  								
   	  								LocalDateTime tempDateTime2 = LocalDateTime.from( dataArrivoSecondoVolo );
   	  								
   	  								long durataScalo2 = tempDateTime2.until( dataPartenzaTerzoVolo, ChronoUnit.MINUTES);
   	  								
   	  								totale=totale.plusMinutes(durataScalo2);
   	  								
   	  								LocalTime durataTerzoVolo=voloAndata[2].getDurataVolo();
   	  								
   	  								totale=totale.plusMinutes(durataTerzoVolo.getHour()*60+durataTerzoVolo.getMinute());
   	  						
   	  							
   									String durataFormatAndata=totale.getHour()+"h"+" "+totale.getMinute()+"min";
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[2].getOrarioArrivo().toString()%></h3>
  								<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()+voloAndata[2].getPrezzo()%>    </h3>
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[2].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Due scali</span>
    						<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
    					
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso() || voloAndata[2].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    				
 						
  						<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
  							</div> 
    							<br>
   					
   								<%  }%>
   									</div>
   									<br>
    							<div>
                			<% } %>
                			</div>
                			
                		<% } else { %>
      					<h2> Lista voli </h2>
      							<div class="pl-5"><!-- contiene le varie grid voli -->
      					<% for(Volo voloAndata: voliAndataDiretti){
      	
      							int oreDurataAndata=voloAndata.getDurataVolo().getHour();
  								int minutiDurataAndata=voloAndata.getDurataVolo().getMinute();
  								String durataFormatAndata=oreDurataAndata+"h"+" "+minutiDurataAndata+"min";
  								
  						
      							for(Volo voloRitorno: voliRitornoDiretti){
      								int oreDurataRitorno=voloRitorno.getDurataVolo().getHour();
      								int minutiDurataRitorno=voloRitorno.getDurataVolo().getMinute();
      								String durataFormatRitorno=oreDurataRitorno+"h"+" "+minutiDurataRitorno+"min";
      							%>
      							
      			

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5><%=voloAndata.getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata.getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata.getOrarioArrivo().toString()%></h3>
  					
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata.getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata.getAeroportoA().getCodice()%></span></b><br>
    							
    					
    					<span style="color: green; margin-left: 155px; ">Diretto</span>
    					<% if(voloAndata.isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata.getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno.getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno.getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno.getOrarioArrivo() %></h3>
  										
  									<h3 style="padding-left: 150px; margin-bottom: 100px;">Totale &euro; <%=voloAndata.getPrezzo()+voloRitorno.getPrezzo()%>    </h3>
    						
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno.getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitorno %></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno.getAeroportoA().getCodice() %></span></b><br>
    			
    					<span style="color: green; margin-left: 160px; ">Diretto</span>
    					<% if(voloRitorno.isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno.getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    							</div>
    				
    							<br>
   					
   								<%  }%>
   									</div> 
    						<% } %>
    							
    					
    					<% if(soloVoliDirettiAndata==false && soloVoliDirettiRitorno==false){ %>
						<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo voloAndata: voliAndataDiretti){
      	
      							int oreDurataAndata=voloAndata.getDurataVolo().getHour();
  								int minutiDurataAndata=voloAndata.getDurataVolo().getMinute();
  								String durataFormatAndata=oreDurataAndata+"h"+" "+minutiDurataAndata+"min";
  								
  							
      							for(Volo[] voloRitorno: voliRitornoUnoScalo){
 									LocalTime totale;
      								
      								LocalTime durataPrimoVolo=voloRitorno[0].getDurataVolo();
      								
      								LocalDateTime dataArrivoPrimoVolo=voloRitorno[0].getDataArrivo();
      								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloRitorno[1].getDataPartenza(),voloRitorno[1].getOrarioPartenza());
      								
      								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
      								
      								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
      								
      								
      								totale=durataPrimoVolo.plusMinutes(durataScalo1);
      								
      								LocalTime durataSecondoVolo=voloRitorno[1].getDurataVolo();
      								
      								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
      								
      								
      								String durataFormatRitorno=totale.getHour()+"h"+" "+totale.getMinute()+"min";
      						
      							%>
      							
      							
      							
      			

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata.getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata.getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata.getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata.getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata.getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: green; margin-left: 155px; ">Diretto</span>
    					<% if(voloAndata.isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata.getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno[0].getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno[1].getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata.getPrezzo()+voloRitorno[0].getPrezzo()+voloRitorno[1].getPrezzo()%>    </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitorno %></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno[1].getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: red; margin-left: 160px; ">Uno scalo</span>
    					<% if(voloRitorno[0].isCompreso() || voloRitorno[1].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<% } %>
   							   
    				
      						</div> 					
  		
    						<% } %>
    						
    						<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo voloAndata: voliAndataDiretti){
      	
      							int oreDurataAndata=voloAndata.getDurataVolo().getHour();
  								int minutiDurataAndata=voloAndata.getDurataVolo().getMinute();
  								String durataFormatAndata=oreDurataAndata+"h"+" "+minutiDurataAndata+"min";
  								
  								
      							for(Volo[] voloRitorno: voliRitornoDueScali){
      								LocalTime totale;
      								
      								LocalTime durataPrimoVolo=voloRitorno[0].getDurataVolo();
      								
      								LocalDateTime dataArrivoPrimoVolo=voloRitorno[0].getDataArrivo();
      								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloRitorno[1].getDataPartenza(),voloRitorno[1].getOrarioPartenza());
      								
      								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
      								
      								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
      								
      								
      								totale=durataPrimoVolo.plusMinutes(durataScalo1);
      								
      								LocalTime durataSecondoVolo=voloRitorno[1].getDurataVolo();
      								
      								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
      								
      								LocalDateTime dataArrivoSecondoVolo=voloRitorno[1].getDataArrivo();
      								LocalDateTime dataPartenzaTerzoVolo=LocalDateTime.of(voloRitorno[2].getDataPartenza(),voloRitorno[2].getOrarioPartenza());
      								
      								LocalDateTime tempDateTime2 = LocalDateTime.from( dataArrivoSecondoVolo );
      								
      								long durataScalo2 = tempDateTime2.until( dataPartenzaTerzoVolo, ChronoUnit.MINUTES);
      								
      								totale=totale.plusMinutes(durataScalo2);
      								
      								LocalTime durataTerzoVolo=voloRitorno[2].getDurataVolo();
      								
      								totale=totale.plusMinutes(durataTerzoVolo.getHour()*60+durataTerzoVolo.getMinute());
      						
      								
      								String durataFormatRitorno=totale.getHour()+"h"+" "+totale.getMinute()+"min";
      							%>
      							
      							
      							
      			

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata.getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata.getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata.getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata.getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata.getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: green; margin-left: 155px; ">Diretto</span>
    					<% if(voloAndata.isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata.getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno[0].getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno[2].getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata.getPrezzo()+voloRitorno[0].getPrezzo()+voloRitorno[1].getPrezzo()+voloRitorno[2].getPrezzo()%>    </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitorno %></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno[2].getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: red; margin-left: 160px; ">Due scali</span>
    					<% if(voloRitorno[0].isCompreso() || voloRitorno[1].isCompreso() || voloRitorno[2].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<%  } %>
   							   
    				
      						</div> 					
  		
    						<% } %>
    						
    					<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo[] voloAndata: voliAndataUnoScalo){
      						LocalTime totale;
								
								LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
								
								LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
								
								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
								
								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
								
								
								totale=durataPrimoVolo.plusMinutes(durataScalo1);
								
								LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
								
								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
								
								
								String durataFormatRitorno=totale.getHour()+"h"+" "+totale.getMinute()+"min";
  								
							
      							for(Volo voloRitorno: voliRitornoDiretti){

          							int oreDurataRitorno=voloRitorno.getDurataVolo().getHour();
      								int minutiDurataRitorno=voloRitorno.getDurataVolo().getMinute();
      								String durataFormatAndata=oreDurataRitorno+"h"+" "+minutiDurataRitorno+"min";
      								
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[1].getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[1].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Uno scalo</span>
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno.getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno.getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno.getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloRitorno.getPrezzo()+voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()%>    </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno.getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitorno %></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno.getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: green; margin-left: 160px; ">Diretto</span>
    					<% if(voloRitorno.isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno.getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<%  } %>
   							   
    				
      						</div> 					
  		
    						<% } %>
    				
      					<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo[] voloAndata: voliAndataUnoScalo){
      							LocalTime totale;
								
								LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
								
								LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
								
								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
								
								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
								
								
								totale=durataPrimoVolo.plusMinutes(durataScalo1);
								
								LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
								
								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
								
								
								String durataFormatAndata=totale.getHour()+"h"+" "+totale.getMinute()+"min";
  								
								
      							for(Volo[] voloRitorno: voliRitornoUnoScalo){
      								LocalTime totaleR;
    								
    								LocalTime durataPrimoVoloR=voloRitorno[0].getDurataVolo();
    								
    								LocalDateTime dataArrivoPrimoVoloR=voloRitorno[0].getDataArrivo();
    								LocalDateTime dataPartenzaSecondoVoloR=LocalDateTime.of(voloRitorno[1].getDataPartenza(),voloRitorno[1].getOrarioPartenza());
    								
    								LocalDateTime tempDateTimeR = LocalDateTime.from( dataArrivoPrimoVoloR );
    								
    								long durataScalo1R = tempDateTimeR.until( dataPartenzaSecondoVoloR, ChronoUnit.MINUTES);
    								
    								
    								totaleR=durataPrimoVoloR.plusMinutes(durataScalo1R);
    								
    								LocalTime durataSecondoVoloR=voloRitorno[1].getDurataVolo();
    								
    								totaleR=totaleR.plusMinutes(durataSecondoVoloR.getHour()*60+durataSecondoVoloR.getMinute());
    								
    								
    								String durataFormatRitornoR=totaleR.getHour()+"h"+" "+totaleR.getMinute()+"min";
      								
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[1].getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[1].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Uno scalo</span>
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno[0].getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno[1].getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()+voloRitorno[0].getPrezzo()+voloRitorno[1].getPrezzo()%>    </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitornoR %></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno[1].getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: red; margin-left: 160px; ">Uno scalo</span>
    					<% if(voloRitorno[0].isCompreso() || voloRitorno[1].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<%  } %>
   							   
    				
      						</div> 					
  		
    						<% } %>
    						
    					<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo[] voloAndata: voliAndataUnoScalo){
      							LocalTime totale;
								
								LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
								
								LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
								
								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
								
								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
								
								
								totale=durataPrimoVolo.plusMinutes(durataScalo1);
								
								LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
								
								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
								
								
								String durataFormatAndata=totale.getHour()+"h"+" "+totale.getMinute()+"min";
  								
								
      							for(Volo[] voloRitorno: voliRitornoDueScali){
								LocalTime totaleR;
      								
      								LocalTime durataPrimoVoloR=voloRitorno[0].getDurataVolo();
      								
      								LocalDateTime dataArrivoPrimoVoloR=voloRitorno[0].getDataArrivo();
      								LocalDateTime dataPartenzaSecondoVoloR=LocalDateTime.of(voloRitorno[1].getDataPartenza(),voloRitorno[1].getOrarioPartenza());
      								
      								LocalDateTime tempDateTimeR = LocalDateTime.from( dataArrivoPrimoVoloR );
      								
      								long durataScalo1R = tempDateTimeR.until( dataPartenzaSecondoVoloR, ChronoUnit.MINUTES);
      								
      								
      								totaleR=durataPrimoVoloR.plusMinutes(durataScalo1R);
      								
      								LocalTime durataSecondoVoloR=voloRitorno[1].getDurataVolo();
      								
      								totaleR=totaleR.plusMinutes(durataSecondoVoloR.getHour()*60+durataSecondoVoloR.getMinute());
      								
      								LocalDateTime dataArrivoSecondoVoloR=voloRitorno[1].getDataArrivo();
      								LocalDateTime dataPartenzaTerzoVoloR=LocalDateTime.of(voloRitorno[2].getDataPartenza(),voloRitorno[2].getOrarioPartenza());
      								
      								LocalDateTime tempDateTime2 = LocalDateTime.from( dataArrivoSecondoVoloR );
      								
      								long durataScalo2 = tempDateTime2.until( dataPartenzaTerzoVoloR, ChronoUnit.MINUTES);
      								
      								totale=totale.plusMinutes(durataScalo2);
      								
      								LocalTime durataTerzoVolo=voloRitorno[2].getDurataVolo();
      								
      								totaleR=totaleR.plusMinutes(durataTerzoVolo.getHour()*60+durataTerzoVolo.getMinute());
      						
      								
      								String durataFormatRitorno=totaleR.getHour()+"h"+" "+totaleR.getMinute()+"min";
      								
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[1].getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[1].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Uno scalo</span>
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno[0].getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno[2].getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()+voloRitorno[0].getPrezzo()+voloRitorno[1].getPrezzo()+voloRitorno[2].getPrezzo()%>   </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitorno %></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno[2].getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: red; margin-left: 160px; ">Due scali</span>
    					<% if(voloRitorno[0].isCompreso() || voloRitorno[1].isCompreso() || voloRitorno[2].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<%   } %>
   							   
    				
      						</div> 					
  		
    						<% } %>
    						
    						
    				<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo[] voloAndata: voliAndataDueScali){
      							LocalTime totale;
								
								LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
								
								LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
								
								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
								
								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
								
								
								totale=durataPrimoVolo.plusMinutes(durataScalo1);
								
								LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
								
								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
								
								LocalDateTime dataArrivoSecondoVoloR=voloAndata[1].getDataArrivo();
  								LocalDateTime dataPartenzaTerzoVoloR=LocalDateTime.of(voloAndata[2].getDataPartenza(),voloAndata[2].getOrarioPartenza());
  								
  								LocalDateTime tempDateTime2 = LocalDateTime.from( dataArrivoSecondoVoloR );
  								
  								long durataScalo2 = tempDateTime2.until( dataPartenzaTerzoVoloR, ChronoUnit.MINUTES);
  								
  								totale=totale.plusMinutes(durataScalo2);
  								
  								LocalTime durataTerzoVolo=voloAndata[2].getDurataVolo();
  								
  								totale=totale.plusMinutes(durataTerzoVolo.getHour()*60+durataTerzoVolo.getMinute());
  						
  							
								String durataFormatAndata=totale.getHour()+"h"+" "+totale.getMinute()+"min";
  								
								
      							for(Volo voloRitorno: voliRitornoDiretti){
								
      								LocalTime durataPrimoVoloR=voloRitorno.getDurataVolo();
      							
      								
      								String durataFormatRitorno=durataPrimoVoloR.getHour()+"h"+" "+durataPrimoVoloR.getMinute()+"min";
      								
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[2].getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[2].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Due scali</span>
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso() || voloAndata[2].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno.getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno.getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno.getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()+voloAndata[2].getPrezzo()+voloRitorno.getPrezzo()%>   </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno.getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitorno %></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno.getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: green; margin-left: 160px; ">Diretto</span>
    					<% if(voloRitorno.isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno.getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<%   } %>
   							   
    				
      						</div> 					
  		
    						<% } %>
    						
    						
    					<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo[] voloAndata: voliAndataDueScali){
      							LocalTime totale;
								
								LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
								
								LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
								
								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
								
								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
								
								
								totale=durataPrimoVolo.plusMinutes(durataScalo1);
								
								LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
								
								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
								
								LocalDateTime dataArrivoSecondoVoloR=voloAndata[1].getDataArrivo();
  								LocalDateTime dataPartenzaTerzoVoloR=LocalDateTime.of(voloAndata[2].getDataPartenza(),voloAndata[2].getOrarioPartenza());
  								
  								LocalDateTime tempDateTime2 = LocalDateTime.from( dataArrivoSecondoVoloR );
  								
  								long durataScalo2 = tempDateTime2.until( dataPartenzaTerzoVoloR, ChronoUnit.MINUTES);
  								
  								totale=totale.plusMinutes(durataScalo2);
  								
  								LocalTime durataTerzoVolo=voloAndata[2].getDurataVolo();
  								
  								totale=totale.plusMinutes(durataTerzoVolo.getHour()*60+durataTerzoVolo.getMinute());
  						
  							
								String durataFormatAndata=totale.getHour()+"h"+" "+totale.getMinute()+"min";
  								
								
      							for(Volo[] voloRitorno: voliRitornoUnoScalo){
									LocalTime totaleR;
    								
    								LocalTime durataPrimoVoloR=voloRitorno[0].getDurataVolo();
    								
    								LocalDateTime dataArrivoPrimoVoloR=voloRitorno[0].getDataArrivo();
    								LocalDateTime dataPartenzaSecondoVoloR=LocalDateTime.of(voloRitorno[1].getDataPartenza(),voloRitorno[1].getOrarioPartenza());
    								
    								LocalDateTime tempDateTimeR = LocalDateTime.from( dataArrivoPrimoVoloR );
    								
    								long durataScalo1R = tempDateTimeR.until( dataPartenzaSecondoVoloR, ChronoUnit.MINUTES);
    								
    								
    								totaleR=durataPrimoVoloR.plusMinutes(durataScalo1R);
    								
    								LocalTime durataSecondoVoloR=voloRitorno[1].getDurataVolo();
    								
    								totaleR=totaleR.plusMinutes(durataSecondoVoloR.getHour()*60+durataSecondoVoloR.getMinute());
    								
    								
    								String durataFormatRitornoR=totaleR.getHour()+"h"+" "+totaleR.getMinute()+"min";
      								
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[2].getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[2].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Due scali</span>
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso() || voloAndata[2].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno[0].getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno[1].getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()+voloAndata[2].getPrezzo()+voloRitorno[0].getPrezzo()+voloRitorno[1].getPrezzo()%>   </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitornoR%></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno[1].getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: red; margin-left: 160px; ">Uno scalo</span>
    					<% if(voloRitorno[0].isCompreso() || voloRitorno[1].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<%   } %>
   							   
    				
      						</div> 					
  		
    						<% } %>
    						
    						
    				<div class="pl-5"> <!-- contiene le varie grid voli -->
      					<% for(Volo[] voloAndata: voliAndataDueScali){
      							LocalTime totale;
								
								LocalTime durataPrimoVolo=voloAndata[0].getDurataVolo();
								
								LocalDateTime dataArrivoPrimoVolo=voloAndata[0].getDataArrivo();
								LocalDateTime dataPartenzaSecondoVolo=LocalDateTime.of(voloAndata[1].getDataPartenza(),voloAndata[1].getOrarioPartenza());
								
								LocalDateTime tempDateTime = LocalDateTime.from( dataArrivoPrimoVolo );
								
								long durataScalo1 = tempDateTime.until( dataPartenzaSecondoVolo, ChronoUnit.MINUTES);
								
								
								totale=durataPrimoVolo.plusMinutes(durataScalo1);
								
								LocalTime durataSecondoVolo=voloAndata[1].getDurataVolo();
								
								totale=totale.plusMinutes(durataSecondoVolo.getHour()*60+durataSecondoVolo.getMinute());
								
								LocalDateTime dataArrivoSecondoVolo=voloAndata[1].getDataArrivo();
  								LocalDateTime dataPartenzaTerzoVolo=LocalDateTime.of(voloAndata[2].getDataPartenza(),voloAndata[2].getOrarioPartenza());
  								
  								LocalDateTime tempDateTime2 = LocalDateTime.from( dataArrivoSecondoVolo );
  								
  								long durataScalo2 = tempDateTime2.until( dataPartenzaTerzoVolo, ChronoUnit.MINUTES);
  								
  								totale=totale.plusMinutes(durataScalo2);
  								
  								LocalTime durataTerzoVolo=voloAndata[2].getDurataVolo();
  								
  								totale=totale.plusMinutes(durataTerzoVolo.getHour()*60+durataTerzoVolo.getMinute());
  						
  							
								String durataFormatAndata=totale.getHour()+"h"+" "+totale.getMinute()+"min";
  								
      							for(Volo[] voloRitorno: voliRitornoDueScali){
									LocalTime totaleR;
      								
      								LocalTime durataPrimoVoloR=voloRitorno[0].getDurataVolo();
      								
      								LocalDateTime dataArrivoPrimoVoloR=voloRitorno[0].getDataArrivo();
      								LocalDateTime dataPartenzaSecondoVoloR=LocalDateTime.of(voloRitorno[1].getDataPartenza(),voloRitorno[1].getOrarioPartenza());
      								
      								LocalDateTime tempDateTimeR = LocalDateTime.from( dataArrivoPrimoVoloR );
      								
      								long durataScalo1R = tempDateTimeR.until( dataPartenzaSecondoVoloR, ChronoUnit.MINUTES);
      								
      								
      								totaleR=durataPrimoVoloR.plusMinutes(durataScalo1R);
      								
      								LocalTime durataSecondoVoloR=voloRitorno[1].getDurataVolo();
      								
      								totaleR=totaleR.plusMinutes(durataSecondoVoloR.getHour()*60+durataSecondoVoloR.getMinute());
      								
      								LocalDateTime dataArrivoSecondoVoloR=voloRitorno[1].getDataArrivo();
      								LocalDateTime dataPartenzaTerzoVoloR=LocalDateTime.of(voloRitorno[2].getDataPartenza(),voloRitorno[2].getOrarioPartenza());
      								
      								LocalDateTime tempDateTime2R = LocalDateTime.from( dataArrivoSecondoVoloR );
      								
      								long durataScalo2R = tempDateTime2R.until( dataPartenzaTerzoVoloR, ChronoUnit.MINUTES);
      								
      								totaleR=totaleR.plusMinutes(durataScalo2R);
      								
      								LocalTime durataTerzoVoloR=voloRitorno[2].getDurataVolo();
      								
      								totaleR=totaleR.plusMinutes(durataTerzoVolo.getHour()*60+durataTerzoVolo.getMinute());
      						
      								
      								String durataFormatRitorno=totaleR.getHour()+"h"+" "+totaleR.getMinute()+"min";
      								
      							%>

      						<div class="p-3 border border-light rounded bg-light">
      						<div class="form-row align-items-center">
  									 <h5 ><%=voloAndata[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloAndata[0].getOrarioPartenza().toString()%></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloAndata[2].getOrarioArrivo().toString()%></h3>
  							
    						</div>
    					<b><span style="margin-left:177px;"><%=voloAndata[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatAndata%></span> 
    					<b><span style="margin-left:115px;"><%=voloAndata[2].getAeroportoA().getCodice()%></span></b><br>
    					
    					<span style="color: red; margin-left: 155px; ">Due scali</span>
    					<% if(voloAndata[0].isCompreso() || voloAndata[1].isCompreso() || voloAndata[2].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti;
    					if((posti=voloAndata[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti%> posti rimasti</span>
    					<% } %>
 
    					<div class="form-row align-items-center">
  									 <h5><%=voloRitorno[0].getCa().getNome() %></h5>
  									<h3 style="margin-left: 100px;"><%=voloRitorno[0].getOrarioPartenza() %></h3> 
  										<img alt="" src="img/icon-route.png" width="250px" height="100px">
  									<h3><%=voloRitorno[2].getOrarioArrivo() %></h3>
  									
  									<h3 style="padding-left: 150px; margin-bottom: 100px;"> &euro; <%=voloAndata[0].getPrezzo()+voloAndata[1].getPrezzo()+voloAndata[2].getPrezzo()+voloRitorno[0].getPrezzo()+voloRitorno[1].getPrezzo()+voloRitorno[2].getPrezzo()%>   </h3>
    						</div>
    									<b><span style="margin-left:177px;"><%=voloRitorno[0].getAeroportoP().getCodice()%></span> </b>
    					<span style="margin-left:100px;"><%=durataFormatRitorno%></span> 
    					<b><span style="margin-left:115px;"><%=voloRitorno[2].getAeroportoA().getCodice() %></span></b><br>
    					
    					<span style="color: red; margin-left: 160px; ">Due scali</span>
    					<% if(voloRitorno[0].isCompreso() || voloRitorno[1].isCompreso() || voloRitorno[2].isCompreso()){ %>
    					<span style="color:green; margin-left: 50px;">Bagaglio stiva incluso</span>
    					<% } %>
    					<% 
    					int posti2;
    					if((posti2=voloRitorno[0].getSeats())<=10){ %>
    					<span style="color:red; margin-left: 35px;">Solo <%=posti2%> posti rimasti</span>
    					<% } %>
    					<button type="submit" style="margin-left: 300px;" class="btn btn-primary">Dettagli</button>
    					
    								</div>
    					
										<br>
   								
   						
   								<% } %>
   							   
    						<% } %>
      						</div> 					
  		
    						<% } %>
      			
      			
      								    						

      			  		<% } %>				

    							<br><br>
    									
      					
      							<div class="d-flex justify-content-center">
      					<h2 class="p-2"><i class="fa fa-arrow-left"></i></h2>
      					<h2 class="p-2"><i class="fa fa-arrow-right"></i></h2>
      			 			  	
      					</div>
    						
      					 
      			
      			
      				
      		
      			
      			<!-- /Information Sections -->
	
	
        
        <!-- load JS files -->
        <script src="js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->       
        <script src="js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <script src="js/jquery.singlePageNav.min.js"></script>      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
		<!-- dove ho cancellato gli script che non facevano funzionare il link sulla barra di navigazione -->
		
		
		<script type="text/javascript">
		var slider = document.getElementById("myRangeDurata");
		var output = document.getElementById("demoAndata");
		output.innerHTML = slider.value; // Display the default slider value

		// Update the current slider value (each time you drag the slider handle)
		slider.oninput = function() {
		  output.innerHTML = this.value;
		}
		
		var slider2 = document.getElementById("myRangeDurata2");
		var output2 = document.getElementById("demoRitorno");
		output2.innerHTML = slider2.value; // Display the default slider value

		// Update the current slider value (each time you drag the slider handle)
		slider2.oninput = function() {
		  output2.innerHTML = this.value;
		}
		
		var slider3 = document.getElementById("myRangePrezzo");
		var output3 = document.getElementById("demoAndata2");
		output3.innerHTML = slider3.value; // Display the default slider value

		// Update the current slider value (each time you drag the slider handle)
		slider3.oninput = function() {
		  output3.innerHTML = this.value;
		}
		
		var slider4 = document.getElementById("myRangePrezzo2");
		var output4 = document.getElementById("demoRitorno2");
		output4.innerHTML = slider4.value; // Display the default slider value

		// Update the current slider value (each time you drag the slider handle)
		slider4.oninput = function() {
		  output4.innerHTML = this.value;
		}
		</script>
	</body>
	
	
	
</html>