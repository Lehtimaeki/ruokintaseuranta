-- Drop tables if they exist
DROP TABLE IF EXISTS Ruokinta CASCADE;
DROP TABLE IF EXISTS Ruoka_Raakaaine CASCADE;
DROP TABLE IF EXISTS Ruoka CASCADE;
DROP TABLE IF EXISTS Valmistaja CASCADE;
DROP TABLE IF EXISTS Raakaaine CASCADE;
DROP TABLE IF EXISTS Ateria CASCADE;


-- Ateria-taulu
CREATE TABLE Ateria (
    ateriaId SERIAL PRIMARY KEY,
    ateriaNimi VARCHAR(50) NOT NULL
);

-- Raakaaine-taulu
CREATE TABLE Raakaaine (
    raakaaineId SERIAL PRIMARY KEY,
    raakaaineNimi VARCHAR(50) NOT NULL
);

-- Valmistaja-taulu
CREATE TABLE Valmistaja (
    valmistajaId SERIAL PRIMARY KEY,
    valmistajaNimi VARCHAR(200) NOT NULL
);

-- Ruoka-taulu
CREATE TABLE Ruoka (
    ruokaId SERIAL PRIMARY KEY,
    ruokaNimi VARCHAR(500) NOT NULL,
    ruokaPisteet DOUBLE PRECISION NOT NULL,
    valmistajaId BIGINT,
    FOREIGN KEY (valmistajaId) REFERENCES Valmistaja(valmistajaId) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Ruoka_Raakaaine-taulu (monen-moneen -suhde)
CREATE TABLE Ruoka_Raakaaine (
    ruokaId BIGINT,
    raakaaineId BIGINT,
    PRIMARY KEY (ruokaId, raakaaineId),
    FOREIGN KEY (ruokaId) REFERENCES Ruoka(ruokaId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (raakaaineId) REFERENCES Raakaaine(raakaaineId) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Ruokinta-taulu
CREATE TABLE Ruokinta (
    ruokintaId SERIAL PRIMARY KEY,
    ruokintaAika DATE NOT NULL,
    taimiMaistui BOOLEAN NOT NULL,
    lempiMaistui BOOLEAN NOT NULL,
    ateriaId BIGINT,
    ruokaId BIGINT,
    FOREIGN KEY (ateriaId) REFERENCES Ateria(ateriaId),
    FOREIGN KEY (ruokaId) REFERENCES Ruoka(ruokaId)
);

-- Lisää esimerkkidataa Ateria-tauluun
INSERT INTO Ateria (ateriaNimi) VALUES ('Aamuruoka');
INSERT INTO Ateria (ateriaNimi) VALUES ('Iltaruoka');

-- Lisää esimerkkidataa Raakaaine-tauluun
INSERT INTO Raakaaine (raakaaineNimi) VALUES ('Kana');
INSERT INTO Raakaaine (raakaaineNimi) VALUES ('Kalkkuna');

-- Lisää esimerkkidataa Valmistaja-tauluun
INSERT INTO Valmistaja (valmistajaNimi) VALUES ('Coshida');

-- Lisää esimerkkidataa Ruoka-tauluun
INSERT INTO Ruoka (ruokaNimi, ruokaPisteet, valmistajaId) VALUES ('Kana-Kalkkunapatee', 0, 1);

-- Lisää esimerkkidataa Ruoka_Raakaaine-tauluun
INSERT INTO Ruoka_Raakaaine (ruokaId, raakaaineId) VALUES (1, 1); -- Kanakalkkunapatee sisältää kanaa
INSERT INTO Ruoka_Raakaaine (ruokaId, raakaaineId) VALUES (1, 2); -- Kanakalkkunapatee sisältää kalkkunaa

-- Lisää esimerkkidataa Ruokinta-tauluun
INSERT INTO Ruokinta (ruokintaAika, taimiMaistui, lempiMaistui, ateriaId, ruokaId) VALUES ('2025-03-09', true, false, 1, 1);

