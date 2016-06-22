DROP SCHEMA IF EXISTS AntiMess;

CREATE SCHEMA AntiMess DEFAULT CHARACTER SET utf8;

USE AntiMess;

CREATE TABLE Lagerort
(
    Container VARCHAR(255),
	LagerortID INT AUTO_INCREMENT,
    PRIMARY KEY (LagerortID)
);

CREATE TABLE Benutzer
(
    BenutzerName VARCHAR (255),
    Password VARCHAR(255),
    UserID INT AUTO_INCREMENT,
    PRIMARY KEY (UserID)
);

CREATE TABLE Gegenstand
(
    GegenstandName VARCHAR(255) NOT NULL,
    Lagerdatum DATE,
    Icon VARCHAR(255),
	LagerortID_FK INT,
    UserID_FK INT,
	GegenstandID INT AUTO_INCREMENT,
    FOREIGN KEY (LagerortID_FK) REFERENCES Lagerort(LagerortID),
    FOREIGN KEY (UserID_FK) REFERENCES Benutzer(UserID) ON DELETE SET NULL,
	PRIMARY KEY (GegenstandID)
);

CREATE TABLE USER_LAGERORT
(
    LagerortID_FK INT,
	UserID_FK INT,
    FOREIGN KEY (LagerortID_FK) REFERENCES Lagerort(LagerortID),
    FOREIGN KEY (UserID_FK) REFERENCES Benutzer(UserID) ON DELETE SET NULL
);

CREATE TABLE Adresse
(
	Straße VARCHAR(255),
    hausnummer INT NOT NULL,
    Postleitzahl INT NOT NULL,
    ORT VARCHAR(255)
);

ALTER TABLE Gegenstand DROP FOREIGN KEY gegenstand_ibfk_2;
ALTER TABLE Gegenstand DROP UserID_FK;
ALTER TABLE Gegenstand ADD BenutzerNameFK VARCHAR(255);
ALTER TABLE Gegenstand ADD Keywords VARCHAR(255);

ALTER TABLE USER_LAGERORT DROP FOREIGN KEY user_lagerort_ibfk_2;
ALTER TABLE USER_LAGERORT DROP UserID_FK;
ALTER TABLE USER_LAGERORT ADD BenutzerName_FK VARCHAR(255);

ALTER TABLE Benutzer MODIFY UserID INT NOT NULL;
ALTER TABLE Benutzer DROP PRIMARY KEY;
ALTER TABLE Benutzer DROP UserID;
ALTER TABLE Benutzer CHANGE BenutzerName BenutzerName VARCHAR(255) PRIMARY KEY;
ALTER TABLE Benutzer ADD NickName VARCHAR(255);

ALTER TABLE Gegenstand ADD FOREIGN KEY (BenutzerNameFK) REFERENCES Benutzer(BenutzerName) ON DELETE SET NULL;
ALTER TABLE USER_LAGERORT ADD FOREIGN KEY (BenutzerName_FK) REFERENCES Benutzer(BenutzerName) ON DELETE SET NULL;

ALTER TABLE Lagerort DROP Container;
ALTER TABLE Lagerort ADD Lagerort_Name VARCHAR(255);
ALTER TABLE Lagerort ADD Lagerort_berechtigt VARCHAR(255);
ALTER TABLE Lagerort ADD FOREIGN KEY (Lagerort_berechtigt) REFERENCES Benutzer(BenutzerName);
ALTER TABLE Lagerort ADD SubContainerID INT DEFAULT NULL;
ALTER TABLE Lagerort ADD FOREIGN KEY (SubContainerID) REFERENCES Lagerort(LagerortID);

ALTER TABLE Adresse ADD Bewohner VARCHAR(255);
ALTER TABLE Adresse ADD FOREIGN KEY (Bewohner) REFERENCES Benutzer(BenutzerName);


CREATE TABLE Aktive_Session
(
	Session_ID VARCHAR(255) PRIMARY KEY,
    BenutzerName_FK VARCHAR(255),
    FOREIGN KEY (BenutzerName_FK) REFERENCES Benutzer(BenutzerName)
);


INSERT INTO Benutzer VALUES ('a@a', 'aa', 'Jens');
INSERT INTO Benutzer VALUES ('b@b', 'bb', 'Pascal');

INSERT INTO Lagerort VALUES(DEFAULT, 'Keller', 'a@a', DEFAULT);
INSERT INTO Lagerort VALUES(DEFAULT, 'Küchenschrank', 'b@b', DEFAULT);

INSERT INTO USER_LAGERORT VALUES(1, 'a@a');
INSERT INTO USER_LAGERORT VALUES(2, 'b@b');

INSERT INTO Gegenstand VALUES ('Schraubenschluessel', '2016-6-15', '././img/mimg01.jpg', 1, DEFAULT, 'a@a', 'Schrauben, Schlüssel');
INSERT INTO Gegenstand VALUES ('Grillwender', '2016-6-15', '././img/fimg02.jpg', 2, DEFAULT, 'a@a', 'Wender, Grill');

INSERT INTO Gegenstand VALUES ('Bügeleisen', '2016-6-15', '././img/fimg06.jpg', 1, DEFAULT, 'b@b', 'Bügel, Eisen');
INSERT INTO Gegenstand VALUES ('Alte Computer Maus', '2016-6-15', '././img/mimg10.jpg', 2, DEFAULT, 'b@b', 'Maus, Computer');

