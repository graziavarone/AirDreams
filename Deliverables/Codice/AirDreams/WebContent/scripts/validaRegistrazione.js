function checkName(inputtxt) {
	var name = /^[A-Za-z]{1,}$/;
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}

function checkCognome(inputtxt) {
	var name = /^[A-Za-z' ]{1,}$/;
	
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}


function checkEmail(inputtxt) {
	var name = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}

function checkPassword(inputtxt) {
	var name = /^(?=.{6,}$)(?=.*[A-Z])(?=.*[0-9])([\.-]?\w+)*.*$/;

	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}

function controllaPassword(inputtxt){
	var name = document.getElementsByName("password")[0];
	var confermaPassword = document.getElementsByName("Cpassword")[0];
	
	if(name.value==inputtxt.value) {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
	else {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	}
}