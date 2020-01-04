function checkName(inputtxt) {
	var name = /^[A-Za-z]{1,}$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}

function validaNome(input){
	var valid = true;	
	
	var name = document.getElementsByName("nome")[0];
	
	if(!checkName(name)) {
		valid = false;
		name.classList.remove("success");
		name.classList.add("error");
	} else {
		valid=true;
		name.classList.remove("error");
		name.classList.add("success");
	}
	
	return valid;
	
}

function checkCognome(inputtxt) {
	var name = /^[A-Za-z' ]{1,}$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}

function validaCognome(input){
	var valid = true;	
	
	var name = document.getElementsByName("cognome")[0];
	
	if(!checkCognome(name)) {
		valid = false;
		name.classList.remove("success");
		name.classList.add("error");
	} else {
		valid=true;
		name.classList.remove("error");
		name.classList.add("success");
	}
	return valid;
}

function checkEmail(inputtxt) {
	var name = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}

function validaEmail(input){
	var valid = true;	
	
	var name = document.getElementsByName("email")[0];
	
	if(!checkEmail(name)) {
		valid = false;
		name.classList.remove("success");
		name.classList.add("error");
	} else {
		valid=true;
		name.classList.remove("error");
		name.classList.add("success");
	}
	return valid;
}



function checkPassword(inputtxt) {
	var name = /^(?=.{6,}$)(?=.*[A-Z])(?=.*[0-9])([\.-]?\w+)*.*$/;
	if(inputtxt.value.match(name)) 
		return true;

	return false;	
}

function validaPassword(input){
	var valid = true;	
	
	var name = document.getElementsByName("password")[0];
	
	if(!checkPassword(name)) {
		valid = false;
		name.classList.remove("success");
		name.classList.add("error");
	} else {
		valid=true;
		name.classList.remove("error");
		name.classList.add("success");
	}
	return valid;
}

function controllaPassword(inputtxt){
	var valid = true;	
	
	var name = document.getElementsByName("password")[0];
	var confermaPassword = document.getElementsByName("Cpassword")[0];
	
	if(name.value==inputtxt) {
		valid=true;
		confermaPassword.classList.remove("error");
		confermaPassword.classList.add("success");
	}
	else {
		valid=false;
		confermaPassword.classList.remove("success");
		confermaPassword.classList.add("error");
	}
	return valid;
}

function validate(obj){
	var valid = true;	
	
	var name = document.getElementsByName("nome")[0];
	var cognome = document.getElementsByName("cognome")[0];
	var email = document.getElementsByName("email")[0];
	var password=document.getElementsByName("password")[0];
	var confermaPassword = document.getElementsByName("Cpassword")[0];
	
	if(!validaNome(name.value))
		valid=false;
	else
		valid=true;
	
	
	if(!validaCognome(cognome.value))
		valid=false;
	else
		valid=true;
	
	if(!validaEmail(email.value))
		valid=false;
	else
		valid=true;
	
	if(!validaPassword(password.value))
		valid=false;
	else
		valid=true;
	
	if(!controllaPassword(confermaPassword.value))
		valid=false;
	else
		valid=true;
	

	if(valid) obj.submit();
	
}










