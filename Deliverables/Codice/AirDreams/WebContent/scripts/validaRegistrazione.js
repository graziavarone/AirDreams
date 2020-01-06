$(document).ready(function() {
	$("#form1").submit(function(){
		var ris = true;
		$("#form1").find('input').each(function() {
			if (!validate($(this).attr("name"))){
				ris=false;
			}
			

		});
		return ris;
	});
	
	function validate(namef){
		if (namef=="nome")
			return checkName();
		if (namef=="cognome")
			return checkCognome();
		if (namef=="email")
			return checkEmail();
		if (namef=="password")
			return checkPassword();
		if (namef=="Cpassword")
			return controllaPassword();
	}


function checkName(inputtxt) {
	var name = /^[A-Za-z]{1,}$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}



function checkCognome(inputtxt) {
	var name = /^[A-Za-z' ]{1,}$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}


function checkEmail(inputtxt) {
	var name = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}



function checkPassword(inputtxt) {
	var name = /^(?=.{6,}$)(?=.*[A-Z])(?=.*[0-9])([\.-]?\w+)*.*$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}


function controllaPassword(inputtxt){
	var valid = true;	
	
	var name = document.getElementsByName("password")[0];
	var confermaPassword = document.getElementsByName("Cpassword")[0];
	
	if(name.value==inputtxt) {
		valid=true;
	}
	else {
		valid=false;
	}
	return valid;
	}
});











