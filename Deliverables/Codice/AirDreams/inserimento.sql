SET GLOBAL innodb_lock_wait_timeout = 10000;
use airdreamstest;
SET SQL_SAFE_UPDATES = 0;
delete from utente;
delete from volo;
delete from compagniaAerea;
delete from cartaDiCredito;
delete from ordine;
delete from biglietto;
delete from carrello;
ALTER table volo AUTO_INCREMENT = 1;
ALTER table ordine AUTO_INCREMENT = 1;
ALTER table biglietto AUTO_INCREMENT = 1;

INSERT INTO compagniaAerea(nome,sito) VALUE ("Ryanair","www.ryanair.com");
INSERT INTO politicaBagaglioMano VALUE (10,'55x40x20','Ryanair');
INSERT INTO politicaBagaglioStiva VALUE (20,'119x81x119','25','Ryanair');

INSERT INTO utente(nome,cognome,email,passwordUtente,compagniaAerea,ruolo)
values
	('Rosaria','Rossi','rosaria@gmail.com','Rosaria1998',null,null),
	('Teresa','Elia','teresa@hotmail.it','Teresa1998',null,null),
    ("Marco","Bianchi","gestoreVoli@gmail.com","gestoreVoli1","Ryanair",'gestoreVoli'),
	('Noemi','Cipriano','noemi@gmail.com','Noemi1998',null,null);
    
INSERT INTO volo(compagniaAerea,dataPart,prezzo,postiDisponibili,durata,orarioPart,bagaglioStivaCompreso,aeroportoPart,aeroportoArr)
values
    ("Ryanair","10/02/2020",26,3,"02:20","08:55",false,"NAP","TXL"), 
	("Ryanair","12/02/2020",26,3,"02:15","06:05",false,"TXL","NAP"),
    
	("Ryanair","10/02/2020",55,3,"00:50","06:35",false,"NAP","FCO"),
	("Ryanair","10/02/2020",55,3,"02:10","09:15",false,"FCO","TXL"),
    
	("Ryanair","12/02/2020",55,3,"02:05","12:15",false,"TXL","FCO"),
	("Ryanair","12/02/2020",55,3,"00:50","17:55",false,"FCO","NAP"),
    
	("Ryanair","12/02/2020",55,3,"01:25","14:30",false,"TXL","AMS"),
	("Ryanair","12/02/2020",55,3,"02:15","16:55",false,"AMS","FCO"),
	("Ryanair","12/02/2020",55,3,"00:50","21:45",false,"FCO","NAP");
    
insert into cartaDiCredito(nCarta,titolare,dataScadenza, cvc,utente) values ("1111 1111 1111 1111","Rosaria Rossi","12/22","123","rosaria@gmail.com");

insert into ordine(dataAcquisto,cartaDiCredito,email) values ("13/02/2020","1111 1111 1111 1111","rosaria@gmail.com");

insert into biglietto(nome,cognome,sesso,prezzoBiglietto,volo,ordine) values ("Rosaria","Rossi","F",26,1,1);
insert into bagaglioMano(peso,dimensioni,biglietto) values(1,"12x12x12",1);
insert into bagaglioStiva(peso,dimensioni,prezzo, quantity,biglietto) values(1,"12x12x12",23,1,1);

insert into carrello (utente,volo,quantity) values("rosaria@gmail.com",1,2);