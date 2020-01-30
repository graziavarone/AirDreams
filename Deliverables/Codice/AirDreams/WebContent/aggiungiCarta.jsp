<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="AggiungiCartaServlet" method="post" class="tm-search-form tm-section-pad-2">
                            		
                            <div class="form-row">
  								<label class="col-sm-1.5 col-form-label">Numero Carta</label>
   	 							<div class="form-group col-md">
      								<input type="text" name="nCarta" class="form-control-plaintext form-control-sm">
    							</div>
    							<label class="col-sm-1.5 col-form-label">Titolare</label>
    							<div class="form-group col-md">
      								<input type="text" name="titolare" class="form-control-plaintext form-control-sm">
    							</div>
    							<label class="col-sm-1.5 col-form-label">Data Scadenza</label>
    							<div class="form-group col-md">
      								<input type="text" name="dataScadenza" class="form-control-plaintext form-control-sm">
    							</div>
    							<label class="col-sm-1.5 col-form-label">CVC</label>
    							<div class="form-group col-md">
      								<input type="text" name="cvc" class="form-control-plaintext form-control-sm">
    							</div>
    							<div class="form-group col-md">
  								<input type="submit" value="submit">
    							</div>
    						</div>
                                </form>

</body>
</html>