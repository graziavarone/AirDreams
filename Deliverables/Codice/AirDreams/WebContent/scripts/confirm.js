$("#form1").submit(function(e) {
    if(!confirm("you sure about that?")){
         e.preventDefault();
    }

}); 