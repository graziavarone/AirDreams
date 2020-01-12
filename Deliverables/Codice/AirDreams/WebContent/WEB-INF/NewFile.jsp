										<div class="row">
                                        <div class="form-group tm-form-element tm-form-element-100">
                                             <i class="sr-only"></i> 
                                            <input type="text" class="form-control" id="inputCity" placeholder="Aeroporto di partenza">
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="sr-only"></i>
                                            <input  type="text" class="form-control"  placeholder="Aeroporto di arrivo">
                                        </div>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="sr-only"></i>
                                            <input type="text" class="form-control"  placeholder="Compagnia aerea">
                                        </div>
                                        </div> 
                                        
                                        
                                        
                                        
                                        <div class="row">
                                    
                                           <div class="form-group">                                            
                                             <select name="ore">
                                            	<option>Ore</option>
                                               <% for(int i=0;i<=9;i++) { %>
                                            	<option>0<%=i%></option>
                                                <% } %> 
                                                <% for(int i=10;i<=24;i++) { %>
                                            	<option><%=i%></option>
                                                <% } %> 
                                             </select>
                                            </div>:        
                                             <div class="form-group">                                     
                                       			<select name="minuti">
                                            	<option>Minuti</option>
                                                <% for(int i=0;i<=9;i++) { %>
                                            	<option>0<%=i%></option>
                                                <% } %> 
                                                <% for(int i=10;i<=59;i++) { %>
                                            	<option><%=i%></option>
                                                <% } %> 
                                            	</select>
                                           </div>
                                       
                                        
                                        <div class="form-group">                                          
                                            <select name="ore">
                                            	<option>Ore</option>
                                                <% for(int i=0;i<=9;i++) { %>
                                            	<option>0<%=i%></option>
                                                <% } %> 
                                                <% for(int i=10;i<=24;i++) { %>
                                            	<option><%=i%></option>
                                                <% } %> 
                                            </select>
                                        </div>:  
                                        <div class="form-group">                                            
                                       		   <select name="minuti"> 
                                               <option>Minuti</option>
                                                <% for(int i=0;i<=9;i++) { %>
                                            	<option>0<%=i%></option>
                                                <% } %> 
                                                <% for(int i=10;i<=59;i++) { %>
                                            	<option><%=i%></option>
                                                <% } %> 
                                          	 	</select>
                                         </div>
	                                        <h6>Data del volo:</h6>
	                                        <div>
	                                        <div class="form-group tm-form-element tm-form-element-50">
	                                            <i class="fa fa-calendar fa-2x tm-form-element-icon"></i>
	                                            <input name="check-in" type="text" class="form-control" id="inputCheckIn" placeholder="Check In">
	                                        </div>
	                                        </div>
	                                        <div class="form-group tm-form-element tm-form-element-50">
	                                        <div>
	                                            <i class="fa fa-calendar fa-2x tm-form-element-icon"></i>
	                                            <input name="check-out" type="text" class="form-control" id="inputCheckOut" placeholder="Check Out">
	                                        </div>
	                                     </div>
	                                     </div>
	                                    	
                                           	<br>
                                           
                                           
                                        <h6>Prezzo:</h6>
                                        <div class="form-group tm-form-element tm-form-element-50">
                                            <i class="sr-only"></i>
                                            <input type="text" class="form-control"  placeholder="">
                                        </div>
                                     <div class="w-50 mt-5">
									<label for="inputNumber1" class="input-number-label">Posti disponibili:</label>
									   <input type="number" id="inputNumber2" name="inputNumber2" value="1" min="1" max="20">
							        </div>s
                                        <div class="row">
                                        <h6>Bagaglio da stiva:</h6>
										    <div class="form-group">                                            
                                            <select name="ore">
                                            	<option>non compreso</option>
										        <option>compreso</option>
										        </select>
                                        
                                        <div class="form-group tm-form-element tm-form-element-2">
                                            <button type="submit" class="btn btn-primary tm-btn-search">Aggiungi volo</button>
                                        	<button type="submit" class="btn btn-primary tm-btn-search">Annulla inserimento</button>
                                       </div>
                                            </div>
  						               </div>
  						               
  						               
  						               
  						               <script>
		
       
            $(document).ready(function(){
            	
          


                // Date Picker
                const pickerCheckIn = datepicker('#inputCheckIn');
                const pickerCheckOut = datepicker('#inputCheckOut');
                
                          
            });

        </script>   