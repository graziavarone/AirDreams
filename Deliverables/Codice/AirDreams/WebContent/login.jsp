<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Login</title>
    
    	<!-- load stylesheets -->
    	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">  <!-- Google web font "Open Sans" -->
    	<link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    	<link rel="stylesheet" href="css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    	<link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    	<link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    	<link rel="stylesheet" type="text/css" href="css/datepicker.css"/>
    	<link rel="stylesheet" href="css/tooplate-style.css">                                   <!-- Templatemo style -->
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
                                		<h3 class="tm-color-primary tm-article-title-1">Accedi</h3>
                                	</div>
                                    <div>
                                        <div class="form-group tm-form-element tm-form-element-100">
                                             <i class="fa fa-user fa-2x tm-form-element-icon"></i> 
                                            <input name="email" type="text" class="form-control" placeholder="Enter your email...">
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="fa fa-lock fa-2x tm-form-element-icon"></i>
                                            <input name="password" type="password" class="form-control" placeholder="Enter your password...">
                                        </div>
                                    </div>
                                    <div class="form-row tm-search-form-row">
                                        <div class="form-group tm-form-element tm-form-element-2">
                                            <button type="submit" class="btn btn-primary tm-btn-search">Accedi</button>
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
	
	</body>
</html>