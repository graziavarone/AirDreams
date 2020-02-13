use airdreamstest;
SET SQL_SAFE_UPDATES = 0;
delete from utente;
delete from volo;
delete from cartaDiCredito;

INSERT INTO utente(nome,cognome,email,passwordUtente)
values ('Rosaria','Rossi','rosaria@gmail.com','Rosaria1998'),
	('Teresa','Elia','teresa@hotmail.it','Teresa1998'),
	('Noemi','Cipriano','noemi@gmail.com','Noemi1998');
    
	

    insert into cartaDiCredito values("1111 1111 1111 1111","prova prova","12/22","123","rosaria@gmail.com");
    