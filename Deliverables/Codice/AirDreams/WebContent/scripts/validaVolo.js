
function checkAeroporto(inputtxt) {
	var name = /^[A-Z]{3} - [A-Za-z  ]{1,}, [A-Za-z ]{1,}$/;
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
		
}



function checkData(inputtxt) {
	var name = /^\s*(0?[1-9]|1[1-9]|2[2-9]|3[01])\/\s*(1[012]|0?[1-9])\/\d{4}\s*$/;
	
	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}


function checkPrezzo(inputtxt) {
	var name = /^([0-9]){1,}[.]([0-9]){1,2}$/;

	if(!inputtxt.value.match(name)) {
		inputtxt.classList.remove("success");
		inputtxt.classList.add("error");	
	} else {
		inputtxt.classList.remove("error");
		inputtxt.classList.add("success");	
	}
}
















