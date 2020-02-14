use airdreamstest;
SET SQL_SAFE_UPDATES = 0;
delete from utente;
delete from volo;
delete from cartaDiCredito;


INSERT INTO utente(nome,cognome,email,passwordUtente)
values ('Rosaria','Rossi','rosaria@gmail.com','Rosaria1998'),
	('Teresa','Elia','teresa@hotmail.it','Teresa1998'),
	('Noemi','Cipriano','noemi@gmail.com','Noemi1998');
    
	INSERT INTO volo(compagniaAerea,dataPart,prezzo,postiDisponibili,durata,orarioPart,bagaglioStivaCompreso,aeroportoPart,
    aeroportoArr)
    VALUES
    ("Ryanair","10/02/2020",26,3,"02:20","08:55",false,"NAP","TXL"), 
	("Ryanair","12/02/2020",26,3,"02:15","06:05",false,"TXL","NAP"),
    
	("Ryanair","10/02/2020",55,3,"00:50","06:35",false,"NAP","FCO"),
	("Ryanair","10/02/2020",55,3,"02:10","09:15",false,"FCO","TXL"),
    
	("Ryanair","12/02/2020",55,3,"02:05","12:15",false,"TXL","FCO"),
	("Ryanair","12/02/2020",55,3,"00:50","17:55",false,"FCO","NAP"),
    
	("Ryanair","12/02/2020",55,3,"01:25","14:30",false,"TXL","AMS"),
	("Ryanair","12/02/2020",55,3,"02:15","16:55",false,"AMS","FCO"),
	("Ryanair","12/02/2020",55,3,"00:50","21:45",false,"FCO","NAP");
    
	
insert into cartaDiCredito values("1111 1111 1111 1111","prova prova","12/22","123","rosaria@gmail.com");
    
    