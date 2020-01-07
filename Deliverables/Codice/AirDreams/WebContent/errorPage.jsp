<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title> Error </title>
    
    	<!-- load stylesheets -->
    	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    	<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    	<link rel="stylesheet" href="css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    	<link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    	<link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    	<link rel="stylesheet" type="text/css" href="css/datepicker.css"/>
    	<link rel="stylesheet" href="css/tooplate-style.css">  
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
                        </nav>            
                    </div>
                </div>
            </div>
            
            <div class="tm-section tm-bg-img" id="tm-section-1">
                <div class="tm-bg-white w-50">
                    <div>
                        <div class="row">
                            <div class="d-flex align-items-center">
                                <form action="LoginServlet" method="post" class="tm-search-form tm-section-pad-2">
                                	<div>
                                		<h3 class="tm-color-primary tm-article-title-1">Si è verificato un errore</h3>
                                		<%
											//controlliamo se è arrivato un messaggio di errore dalla request
											String message=(String) request.getParameter("message");
											if (message!=null) {	
										%> 
                                		<p><%=message%></p>
                                		<% } %>
                                		<p> Riprendi la tua esperienza su AirDreams tornando alla <a href="index.jsp">homepage</a> del sistema.</p>
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
	</body>
</html>