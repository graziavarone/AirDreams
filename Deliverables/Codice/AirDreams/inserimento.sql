use airdreamstest;
SET SQL_SAFE_UPDATES = 0;
delete from utente;
INSERT INTO utente(nome,cognome,email,passwordUtente,ruolo)
values ('Rosaria','Rossi','rosaria@gmail.com','Rosaria1998',null),
	('Teresa','Elia','teresa@hotmail.it','Teresa1998',null),
	('Noemi','Cipriano','noemi@gmail.com','Noemi1998','gestoreVoli'),
	('Grazia','Varone','grazia@virgilio.it','Grazia1998',null);