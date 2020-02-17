
function checkNumeroCarta(inputtxt) {
	var name = /^([0-9]{4}( |\\-)){3}[0-9]{4}$/;
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
		
}



function checkTitolare(inputtxt) {
	var name = /^[A-Za-z' ]{1,}$/;
	
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}


function checkData(inputtxt) {
	var name = /^\s*(1[012]|0[1-9])\/\d{2}\s*$/;

	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}



function checkCVC(inputtxt) {
	var name = /^[0-9]{3}$/;

	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}














