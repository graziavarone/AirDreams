<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,gestioneutente.*"%>

<% Boolean mod=(Boolean)request.getAttribute("mod");

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
									  <a href="#">Visualizza voli</a>
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
                        <div class="row">
                            <div class="col-xs-12 ml-auto mr-auto ie-container-width-fix">
                                <form action="index.html" method="get" class="tm-search-form tm-section-pad-2">
                                    <div class="form-row tm-search-form-row">
                                        <div class="form-group tm-form-element tm-form-element-100">
                                             <i class="sr-only"></i> 
                                            <input type="text" class="form-control" id="inputCity" placeholder="Aeroporto di partenza">
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="sr-only"></i>
                                            <input  type="text" class="form-control" id="inputCheckIn" placeholder="Aeroporto di arrivo">
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="sr-only"></i>
                                            <input type="text" class="form-control" id="inputCheckOut" placeholder="Compagnia aerea">
                                        </div>
                                    </div>
                                    <h6>Orario di partenza:</h6>
                                    
                                    <div class="row">
                                        <div class="form-group">                                            
                                            <select class="form-control">
                                                <option>00</option>
                                                <option>01</option>
                                                <option>02</option>
                                                <option>03</option>
                                                <option>04</option>
                                                <option>05</option>
                                                <option>06</option>
                                                <option>07</option>
                                                <option>08</option>
                                                <option>09</option>
                                                <option>10</option>
                                                <option>11</option>
                                                <option>12</option>
                                                <option>13</option>
                                                <option>14</option>
                                                <option>15</option>
                                                <option>16</option>
                                                <option>17</option>
                                                <option>18</option>
                                                <option>19</option>
                                                <option>20</option>
                                                <option>21</option>
                                                <option>22</option>
                                                <option>23</option>
                                                <option>24</option>
                                            </select>
                                            </div>:        
                                             <div class="form-group">                                     
                                       <select class="form-control"> 
                                                <option value="">00</option>
                                                <option value="1">01</option>
                                                <option value="2">02</option>
                                                <option value="3">03</option>
                                                <option value="4">04</option>
                                                <option value="5">05</option>
                                                <option value="6">06</option>
                                                <option value="7">07</option>
                                                <option value="8">08</option>
                                                <option value="9">09</option>
                                                <option value="10">10</option>
                                                <option value="10">11</option>
                                                <option value="10">12</option>
                                                <option value="10">13</option>
                                                <option value="10">14</option>
                                                <option value="10">15</option>
                                                <option value="10">16</option>
                                                <option value="10">17</option>
                                                <option value="10">18</option>
                                                <option value="10">19</option>
                                                <option value="10">20</option>
                                                <option value="10">21</option>
                                                <option value="10">22</option>
                                                <option value="10">23</option>
                                                <option value="10">24</option>
                                            	<option value="10">25</option>
                                            	<option value="10">24</option>
                                            	<option value="10">26</option>
                                            	<option value="10">27</option>
                                            	<option value="10">28</option>
                                            	<option value="10">29</option>
                                            	<option value="10">30</option>
                                            	<option value="10">31</option>
                                            	<option value="10">32</option>
                                            	<option value="10">33</option>
                                            	<option value="10">34</option>
                                            	<option value="10">35</option>
                                            	<option value="10">36</option>
                                           		<option value="10">37</option>
                                           	    <option value="10">38</option>
                                            	<option value="10">39</option>
                                           	 	<option value="10">40</option>
                                            	<option value="10">41</option>
                                           		<option value="10">42</option>
                                            	<option value="10">43</option>
                                            	<option value="10">44</option>
                                                <option value="10">45</option>
                                            	<option value="10">46</option>
                                            	<option value="10">47</option>
                                            	<option value="10">48</option>
                                            	<option value="10">49</option>
                                            	<option value="10">50</option>
                                            	<option value="10">51</option>
                                            	<option value="10">52</option>
                                            	<option value="10">53</option>
                                            	<option value="10">54</option>
                                            	<option value="10">55</option>
                                            	<option value="10">56</option>
                                            	<option value="10">57</option>
                                            	<option value="10">58</option>
                                            	<option value="10">59</option>
                                           </select>
                                          
                                         
                                           </div>
                                       
                                         <h6>Orario di arrivo:</h6>
                                        <div class="form-group">                                            
                                            <select class="form-control">
                                                <option value="">00</option>
                                                <option value="1">01</option>
                                                <option value="2">02</option>
                                                <option value="3">03</option>
                                                <option value="4">04</option>
                                                <option value="5">05</option>
                                                <option value="6">06</option>
                                                <option value="7">07</option>
                                                <option value="8">08</option>
                                                <option value="9">09</option>
                                                <option value="10">10</option>
                                                <option value="10">11</option>
                                                <option value="10">12</option>
                                                <option value="10">13</option>
                                                <option value="10">14</option>
                                                <option value="10">15</option>
                                                <option value="10">16</option>
                                                <option value="10">17</option>
                                                <option value="10">18</option>
                                                <option value="10">19</option>
                                                <option value="10">20</option>
                                                <option value="10">21</option>
                                                <option value="10">22</option>
                                                <option value="10">23</option>
                                                <option value="10">24</option>
                                            </select> 
                                        </div>:
                                        
                                        <div class="form-group">                                            
                                       <select class="form-control"> 
                                                <option value="">00</option>
                                                <option value="1">01</option>
                                                <option value="2">02</option>
                                                <option value="3">03</option>
                                                <option value="4">04</option>
                                                <option value="5">05</option>
                                                <option value="6">06</option>
                                                <option value="7">07</option>
                                                <option value="8">08</option>
                                                <option value="9">09</option>
                                                <option value="10">10</option>
                                                <option value="10">11</option>
                                                <option value="10">12</option>
                                                <option value="10">13</option>
                                                <option value="10">14</option>
                                                <option value="10">15</option>
                                                <option value="10">16</option>
                                                <option value="10">17</option>
                                                <option value="10">18</option>
                                                <option value="10">19</option>
                                                <option value="10">20</option>
                                                <option value="10">21</option>
                                                <option value="10">22</option>
                                                <option value="10">23</option>
                                                <option value="10">24</option>
                                            	<option value="10">25</option>
                                            	<option value="10">24</option>
                                            	<option value="10">26</option>
                                            	<option value="10">27</option>
                                            	<option value="10">28</option>
                                            	<option value="10">29</option>
                                            	<option value="10">30</option>
                                            	<option value="10">31</option>
                                            	<option value="10">32</option>
                                            	<option value="10">33</option>
                                            	<option value="10">34</option>
                                            	<option value="10">35</option>
                                            	<option value="10">36</option>
                                           		<option value="10">37</option>
                                           	    <option value="10">38</option>
                                            	<option value="10">39</option>
                                           	 	<option value="10">40</option>
                                            	<option value="10">41</option>
                                           		<option value="10">42</option>
                                            	<option value="10">43</option>
                                            	<option value="10">44</option>
                                                <option value="10">45</option>
                                            	<option value="10">46</option>
                                            	<option value="10">47</option>
                                            	<option value="10">48</option>
                                            	<option value="10">49</option>
                                            	<option value="10">50</option>
                                            	<option value="10">51</option>
                                            	<option value="10">52</option>
                                            	<option value="10">53</option>
                                            	<option value="10">54</option>
                                            	<option value="10">55</option>
                                            	<option value="10">56</option>
                                            	<option value="10">57</option>
                                            	<option value="10">58</option>
                                            	<option value="10">59</option>
                                           </select>
                                           </div>
                                           <h6>Data del volo:</h6>
                                           <div class="form-group tm-form-element tm-form-element-50">
                                           		<i class="fa fa-calendar fa-2x tm-form-element-icon"></i>
                                           		<input name="check-in" type="text" class="form-control" id="inputCheckIn" placeholder="Calendar">
                                           </div>
                                           <br>
                                           
                                            <div class="form-row tm-search-form-row">
                                            <h6>Durata volo:</h6>
                                            <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="sr-only"></i>
                                            <input  type="text" class="form-control" id="inputCheckIn" placeholder="">
                                        </div>
                                        <h6>Prezzo:</h6>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="sr-only"></i>
                                            <input type="text" class="form-control" id="inputCheckOut" placeholder="">
                                        </div>
                                        </div>
                                        
                                        <div class="row">
                                        <h6>Bagaglio da stiva:</h6>
										    <div class="form-check form-check-inline">
										      <input id="checkbox2" type="checkbox">
										      <label for="checkbox2">Bagaglio da stiva compreso</label>
										    </div>
										    <div class="form-check form-check-inline">
										      <input id="checkbox3" type="checkbox" checked="checked">
										      <label for="checkbox3">Bagaglio da stiva non compreso</label>
										      <input type="text" class="form-control"  placeholder="prezzo">
										    </div>
										  
                                        
                                        <div class="form-group tm-form-element tm-form-element-2">
                                            <button type="submit" class="btn btn-primary tm-btn-search">Aggiungi volo</button>
                                        	<button type="submit" class="btn btn-primary tm-btn-search">Annulla inserimento</button>
                                       </div>
                                            </div>
  									
                                    </div>
                                </form>
                            </div>                        
                        </div>      
                    </div>
                </div>                  
            </div>
          
           	<div class="tm-section tm-position-relative">
             
                <div class="container tm-pt-5 tm-pb-4">
                    <div class="row text-center">
                        <article class="col-sm-12 col-md-4 col-lg-4 col-xl-4 tm-article">                            
                            <i class="fa tm-fa-6x fa-legal tm-color-primary tm-margin-b-20 "></i>
                            <h3 class="tm-color-primary tm-article-title-1">Go everywhere</h3>
                            <p>It is your world and we will help you explore it. Find the best prices from millions of flight deals to organize your perfect trip.</p>
                          
                        </article>
                        <article class="col-sm-12 col-md-4 col-lg-4 col-xl-4 tm-article">                            
                            <i class="fa tm-fa-6x fa-plane tm-color-primary tm-margin-b-20"></i>
                            <h3 class="tm-color-primary tm-article-title-1">Simple and safe</h3>
                            <p>No extra charge. No nasty surprises. Easily manage your travel expenses, so you can relax even before departure.</p>
                                                     
                        </article>
                        <article class="col-sm-12 col-md-4 col-lg-4 col-xl-4 tm-article">                           
                            <i class="fa tm-fa-6x fa-life-saver tm-color-primary tm-margin-b-20"></i>
                            <h3 class="tm-color-primary tm-article-title-1">Travel your own way</h3>
                            <p>Do you already know where to go? Find out the best time to book. Find offers for a relaxing short weekend or for an unforgettable adventure..</p>
                                                    
                        </article>
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