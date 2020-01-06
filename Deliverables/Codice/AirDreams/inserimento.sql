use airdreamstest;
SET SQL_SAFE_UPDATES = 0;
delete from utente;
INSERT INTO utente(nome,cognome,email,passwordUtente)
values ('Rosaria','Rossi','rosaria@gmail.com','Rosaria1998'),
	('Teresa','Elia','teresa@hotmail.it','Teresa1998'),
	('Noemi','Cipriano','noemi@gmail.com','Noemi1998');