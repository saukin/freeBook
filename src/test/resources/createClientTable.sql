USE FINALLAB;

DROP TABLE IF EXISTS CLIENTS;
CREATE TABLE CLIENTS (
--  ID int NOT NULL AUTO_INCREMENT,
  EMAIL varchar(60)  UNIQUE default 0,
  PASSWORD varchar(12)  default 0,
  FIRSTNAME varchar(20) default 0,
  LASTNAME varchar(20) default 0,
  ADDRESS varchar(60) default 0,
  CITY varchar(30) default 0,
  PROVINCE varchar(30) default 0,
  COUNTRY varchar(20) default 0,
  POSTALCODE varchar(10) default 0, 
  BOOK varchar(60)  DEFAULT '',
  PRIMARY KEY (EMAIL)
) ENGINE=InnoDB;