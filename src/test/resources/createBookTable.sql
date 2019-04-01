
/**
 * Author:  admin
 * Created: Mar 27, 2019
 */

USE FINALLAB;

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS (
  ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  TITLE varchar(60) NOT NULL,
  FILE_NAME varchar(30)
 ) ENGINE=InnoDB;

INSERT INTO BOOKS (TITLE,  FILE_NAME) VALUES
    ("The Adventures of Sherlock Holmes", "SherlockHolmes.zip"),
    ("Alice's Adventures in Wonderland", "AliceInWonderland.zip"),
    ("Moby Dick; or The Whale", "MobyDick.zip");


