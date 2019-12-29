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
		name.classList.remove("error");
		name.classList.add("success");
	}
	
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
		name.classList.remove("error");
		name.classList.add("success");
	}
	
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
		name.classList.remove("error");
		name.classList.add("success");
	}
	
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
		name.classList.remove("error");
		name.classList.add("success");
	}
	
}

function controllaPassword(inputtxt){
	var valid = true;	
	
	var name = document.getElementsByName("password")[0];
	var name2 = document.getElementsByName("Cpassword")[0];
	
	if(name.value==inputtxt) {
		name2.classList.remove("error");
		name2.classList.add("success");
	}
	else {
		name2.classList.remove("success");
		name2.classList.add("error");
	}
}






