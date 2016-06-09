DROP SCHEMA IF EXISTS AntiMess;
CREATE SCHEMA AntiMess DEFAULT CHARACTER SET utf8;

USE AntiMess;

CREATE TABLE Lagerort
(
	LagerortID INT AUTO_INCREMENT,
    Container VARCHAR(255),
    PRIMARY KEY (LagerortID)
);

CREATE TABLE Benutzer
(
	UserID INT AUTO_INCREMENT,
    BenutzerName VARCHAR (255),
    Password VARCHAR(255),
    PRIMARY KEY (UserID)
);

CREATE TABLE Gegenstand
(
	GegenstandID INT AUTO_INCREMENT,
    GegenstandName VARCHAR(255) NOT NULL,
    Lagerdatum DATE,
    Icon VARCHAR(255),
	LagerortID_FK INT,
    UserID_FK INT,
    FOREIGN KEY (LagerortID_FK) REFERENCES Lagerort(LagerortID),
    FOREIGN KEY (UserID_FK) REFERENCES Benutzer(UserID),
	PRIMARY KEY (GegenstandID)
);

CREATE TABLE USER_LAGERORT
(
    LagerortID_FK INT,
	UserID_FK INT,
    FOREIGN KEY (LagerortID_FK) REFERENCES Lagerort(LagerortID),
    FOREIGN KEY (UserID_FK) REFERENCES Benutzer(UserID)
);

CREATE TABLE Adresse
(
	Straße VARCHAR(255),
    hausnummer INT NOT NULL,
    Postleitzahl INT NOT NULL,
    ORT VARCHAR(255)
);



