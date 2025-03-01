CREATE TABLE Ateria (
    ateriaId INT PRIMARY KEY AUTO_INCREMENT,
    ateriaNimi VARCHAR(50) NOT NULL,
);

INSERT INTO Ateria (ateriaNimi)
VALUES  ('Aamuruoka'),
        ('Iltaruoka');

CREATE TABLE Raakaaine (
    raakaaineId INT PRIMARY KEY AUTO_INCREMENT,
    raakaaineNimi VARCHAR(50) NOT NULL,
);

INSERT INTO Raakaaine (raakaaineNimi)
VALUES  ('Kana'),
        ('Lohi'),
        ('Kalkkuna'),
        (Turska);

CREATE TABLE Valmistaja (
    valmistajaId INT PRIMARY KEY AUTO_INCREMENT,
    valmistajaNimi VARCHAR(200) NOT NULL,
);

INSERT INTO Valmistaja (valmistajaNimi)
VALUES  ('PrimaCat'),
        ('Nutrima'),
        ('Tiikeri'),
        ('Canagan');


CREATE TABLE Ruoka (
    ruokaId BIGINT AUTO_INCREMENT PRIMARY KEY,
    ruokaNimi VARCHAR(500) NOT NULL,
    ruokaPisteet DOUBLE NOT NULL,
    valmistajaId BIGINT,
    FOREIGN KEY (valmistajaId) REFERENCES Valmistaja(valmistajaId)
);

CREATE TABLE Ruoka_Raakaaine (
    ruokaId BIGINT,
    raakaAineId BIGINT,
    PRIMARY KEY (ruokaId, raakaAineId),
    FOREIGN KEY (ruokaId) REFERENCES Ruoka(ruokaId),
    FOREIGN KEY (raakaAineId) REFERENCES Raakaaine(raakaAineId)
);

CREATE TABLE Ruokinta (
    ruokintaId BIGINT AUTO_INCREMENT PRIMARY KEY,
    ruokintaAika DATE NOT NULL,
    taimiMaistui BOOLEAN NOT NULL,
    lempiMaistui BOOLEAN NOT NULL,
    ateriaId BIGINT,
    ruokaId BIGINT,
    FOREIGN KEY (ateriaId) REFERENCES Ateria(ateriaId),
    FOREIGN KEY (ruokaId) REFERENCES Ruoka(ruokaId)
);

