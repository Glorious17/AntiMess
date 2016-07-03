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

ALTER TABLE Gegenstand ADD Keywords VARCHAR(255);
ALTER TABLE Lagerort ADD Ersteller VARCHAR(255) NOT NULL;
ALTER TABLE Lagerort ADD FOREIGN KEY (Ersteller) REFERENCES Benutzer(Benutzername);
DROP TABLE user_lagerort;

CREATE TABLE Aktive_Session
(
	Session_ID VARCHAR(255) PRIMARY KEY,
    BenutzerName_FK VARCHAR(255),
    FOREIGN KEY (BenutzerName_FK) REFERENCES Benutzer(BenutzerName)
);

ALTER TABLE Lagerort DROP FOREIGN KEY lagerort_ibfk_1;
ALTER TABLE Lagerort DROP Lagerort_berechtigt;

CREATE TABLE Lagerort_Berechtigt
(
	LagerortID_FK INT NOT NULL,
    Berechtigt VARCHAR(255),
    FOREIGN KEY (LagerortID_FK) REFERENCES Lagerort(LagerortID),
    FOREIGN KEY (Berechtigt) REFERENCES Benutzer(BenutzerName)
);

INSERT INTO Benutzer VALUES ('a@a', 'aa', 'Jens');
INSERT INTO Benutzer VALUES ('b@b', 'bb', 'Pascal');
INSERT INTO Benutzer VALUES ('c@c', 'cc', 'Janusz');

INSERT INTO Lagerort VALUES(DEFAULT, 'Keller', DEFAULT, 'b@b');
INSERT INTO Lagerort VALUES(DEFAULT, 'Küchenschrank', DEFAULT, 'a@a');
INSERT INTO Lagerort VALUES(DEFAULT, 'Zimmer', DEFAULT, 'c@c');

INSERT INTO Gegenstand VALUES ('Schweizer Taschenmesser', '2016-6-15', '././img/taschenmesser.jpg', 1, DEFAULT, 'b@b', 'Schweizer, Taschenmesser');
INSERT INTO Gegenstand VALUES ('Bluetooth Maus', '2016-6-15', '././img/maus.jpg', 2, DEFAULT, 'a@a', 'Bluetooth, Maus, Computer');
INSERT INTO Gegenstand VALUES ('Rucksack', '2016-6-15', '././img/rucksack.jpg', 2, DEFAULT, 'a@a', 'Rucksack, Tasche');
INSERT INTO Gegenstand VALUES ('Bluetooth Kopfhörer', '2016-6-15', '././img/bluetoothkopfhoerer.jpg', 1, DEFAULT, 'b@b', 'Bluetooth, Kopfhörer, Computer');

INSERT INTO Gegenstand VALUES ('Kopfhörer', '2016-6-15', '././img/kopfhörer.jpg', 1, DEFAULT, 'b@b', 'Kopfhörer, Computer');
INSERT INTO Gegenstand VALUES ('Mülleimer', '2016-6-15', '././img/muell.jpg', 2, DEFAULT, 'a@a', 'Mülleimer, Abfall');
INSERT INTO Gegenstand VALUES ('Laptoptasche', '2016-6-15', '././img/laptoptasche.jpg', 1, DEFAULT, 'b@b', 'Laptop, Tasche');
INSERT INTO Gegenstand VALUES ('Portmonaie', '2016-6-15', '././img/portmonaie.jpg', 2, DEFAULT, 'a@a', 'Geldbeutel, Portmonaie');

INSERT INTO Gegenstand VALUES ('Stuhl', '2016-6-15', '././img/stuhl.jpg', 3, DEFAULT, 'c@c', 'Stuhl');
INSERT INTO Gegenstand VALUES ('Tischverkleidung', '2016-6-15', '././img/tischverkleidung.jpg', 3, DEFAULT, 'c@c', 'Tischverkleidung');

INSERT INTO Lagerort_Berechtigt VALUES (1, 'a@a');
INSERT INTO Lagerort_Berechtigt VALUES (2, 'b@b');