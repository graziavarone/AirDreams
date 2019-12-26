DROP DATABASE IF EXISTS airdreams;
CREATE DATABASE airdreams;
USE airdreams;

CREATE TABLE compagniaAerea (
	nome varchar(30) not null,
    sito varchar(30) not null,
    
    primary key (nome)
    );
    
CREATE TABLE utente (
	nome varchar(30) not null,
    cognome varchar(30) not null,
    email varchar(50) not null,
    passwordUtente varchar(30) not null,
    compagniaAerea varchar(20),
    ruolo enum('gestoreVoli','gestoreAccount'),
    
    primary key (email),
    foreign key (compagniaAerea) references compagniaAerea (nome)
    on update cascade on delete cascade
    );
    
CREATE TABLE aeroporto (
	codice varchar(4) not null,
    city varchar(30) not null,
    nome varchar(50) not null,
    stato varchar(30) not null,
    
    primary key (codice)
    );
    
        
CREATE TABLE volo (
	idVolo int(11) not null,
    dataPart varchar(12) not null,
    prezzo float(11) not null,
    postiDisponibili int(4) not null, 
    durata varchar(10) not null, 
    orarioPart varchar(10) not null,
    bagaglioStivaCompreso boolean not null,
    aeroportoPart varchar(4) not null,
    aeroportoArr varchar(4) not null,
    
    primary key (idVolo),
    foreign key (aeroportoPart) references aeroporto (codice) on update cascade on delete cascade,
    foreign key (aeroportoArr) references aeroporto (codice) on update cascade on delete cascade
    );
    
CREATE TABLE carrello (
	codiceCarrello int(11) not null auto_increment,
	utente varchar(50) not null,
    volo int(11) not null,
    quantity int(11) not null, 
    
    primary key (codiceCarrello), 
    foreign key (utente) references utente (email) on update cascade on delete cascade,
	foreign key (volo) references volo (idVolo) on update cascade on delete cascade
   );

CREATE TABLE politicaBagaglio (
	peso int(3) not null,
    dimensioni varchar(15) not null,
    prezzo float(10),
    compagniaAerea varchar(30) not null,
    
    foreign key (compagniaAerea) references compagniaAerea (nome) on update cascade on delete cascade
    );
    
CREATE TABLE cartaDiCredito (
	nCarta varchar(25) not null,
    titolare varchar(30) not null,
    dataScadenza Date not null,
    cvc int(4) not null,
    utente varchar(30) not null,
    
    primary key (nCarta),
    foreign key (utente) references utente (email) on update cascade on delete cascade
    );
    
CREATE TABLE ordine (
	codOrdine int(11) not null,
    dataAcquisto Date not null,
    cartaDiCredito varchar(25) not null,
    email varchar(50) not null,
    
    primary key (codOrdine),
    foreign key(cartaDiCredito) references cartaDiCredito(nCarta) on update cascade on delete cascade,
    foreign key(email) references utente(email) on update cascade on delete cascade
    );
    
CREATE TABLE biglietto (
	nome varchar(30) not null,
    cognome varchar(30) not null, 
    sesso enum('M', 'F'),
    prezzoBiglietto float(11) not null,
    codBiglietto int(11) not null,
    ordine int(11) not null,
    
    primary key (codBiglietto),
    foreign key (ordine) references ordine (codOrdine) on update cascade on delete cascade
    );
    
CREATE TABLE bagaglio (
	peso int(3) not null,
    dimensioni varchar(15) not null,
    prezzo float(10),
    quantity int(2),
    biglietto int(11) not null,
    
    foreign key (biglietto) references biglietto (codBiglietto) on update cascade on delete cascade
    );


DROP USER IF EXISTS 'login'@'localhost';
CREATE USER 'login'@'localhost' IDENTIFIED BY 'pwd';
GRANT ALL ON *.* TO 'login'@'localhost';