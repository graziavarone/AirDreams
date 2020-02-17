function checkName(inputtxt) {
	var name = /^[A-Za-z  ]{1,}$/;
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
		
}

function checkSito(inputtxt) {
	var name = /^www.[a-z]{1,}.com$/;
	
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}

function checkDimensioni(inputtxt) {
	var name = /^\d{2,4}x\d{2,4}x\d{2,4}$/;

	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}












