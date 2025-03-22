-- Drop tables if they exist
DROP TABLE IF EXISTS Ruokinta CASCADE;
DROP TABLE IF EXISTS Ruoka_Raakaaine CASCADE;
DROP TABLE IF EXISTS Ruoka CASCADE;
DROP TABLE IF EXISTS Valmistaja CASCADE;
DROP TABLE IF EXISTS Raakaaine CASCADE;
DROP TABLE IF EXISTS Ateria CASCADE;


-- Ateria-taulu
CREATE TABLE Ateria (
    ateria_id SERIAL PRIMARY KEY,
    ateria_nimi VARCHAR(50) NOT NULL
);

-- Raakaaine-taulu
CREATE TABLE Raakaaine (
    raakaaine_id SERIAL PRIMARY KEY,
    raakaaine_nimi VARCHAR(50) NOT NULL
);

-- Valmistaja-taulu
CREATE TABLE Valmistaja (
    valmistaja_id SERIAL PRIMARY KEY,
    valmistaja_nimi VARCHAR(200) NOT NULL
);

-- Ruoka-taulu
CREATE TABLE Ruoka (
    ruoka_id SERIAL PRIMARY KEY,
    ruoka_nimi VARCHAR(500) NOT NULL,
    ruoka_pisteet DOUBLE PRECISION NOT NULL,
    valmistaja_id BIGINT,
    FOREIGN KEY (valmistaja_id) REFERENCES Valmistaja(valmistaja_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Ruoka_Raakaaine-taulu (monen-moneen -suhde)
CREATE TABLE Ruoka_Raakaaine (
    ruoka_id BIGINT,
    raakaaine_id BIGINT,
    PRIMARY KEY (ruoka_id, raakaaine_id),
    FOREIGN KEY (ruoka_id) REFERENCES Ruoka(ruoka_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (raakaaine_id) REFERENCES Raakaaine(raakaaine_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Ruokinta-taulu
CREATE TABLE Ruokinta (
    ruokinta_id SERIAL PRIMARY KEY,
    ruokinta_aika DATE NOT NULL,
    taimi_maistui BOOLEAN NOT NULL,
    lempi_maistui BOOLEAN NOT NULL,
    ateria_id BIGINT,
    ruoka_id BIGINT,
    FOREIGN KEY (ateria_id) REFERENCES Ateria(ateria_id),
    FOREIGN KEY (ruoka_id) REFERENCES Ruoka(ruoka_id)
);

--Lisää esimerkkidataa Ateria-tauluun
INSERT INTO Ateria (ateria_nimi) VALUES ('Aamuruoka');
INSERT INTO Ateria (ateria_nimi) VALUES ('Iltaruoka');
INSERT INTO Ateria (ateria_nimi) VALUES ('Päiväruoka');

-- Lisää esimerkkidataa Raakaaine-tauluun
INSERT INTO Raakaaine (raakaaine_nimi) VALUES ('Kana');
INSERT INTO Raakaaine (raakaaine_nimi) VALUES ('Kalkkuna');
INSERT INTO Raakaaine (raakaaine_nimi) VALUES ('Lohi');
INSERT INTO Raakaaine (raakaaine_nimi) VALUES ('Nauta');
INSERT INTO Raakaaine (raakaaine_nimi) VALUES ('Sika');
INSERT INTO Raakaaine (raakaaine_nimi) VALUES ('Turska');
INSERT INTO Raakaaine (raakaaine_nimi) VALUES ('Siipikarja');

-- Lisää esimerkkidataa Valmistaja-tauluun
INSERT INTO Valmistaja (valmistaja_nimi) VALUES ('Coshida');
INSERT INTO Valmistaja (valmistaja_nimi) VALUES ('PrimaCat');
INSERT INTO Valmistaja (valmistaja_nimi) VALUES ('Pure Natural');
INSERT INTO Valmistaja (valmistaja_nimi) VALUES ('Pirkka');

-- Lisää esimerkkidataa Ruoka-tauluun
INSERT INTO Ruoka (ruoka_nimi, ruoka_pisteet, valmistaja_id) VALUES ('Kana-Kalkkunapatee', 0, 1);
INSERT INTO Ruoka (ruoka_nimi, ruoka_pisteet, valmistaja_id) VALUES ('Poultry in Gravy', 0, 2);
INSERT INTO Ruoka (ruoka_nimi, ruoka_pisteet, valmistaja_id) VALUES ('Kana annosateria', 0, 4);

-- Lisää esimerkkidataa Ruoka_Raakaaine-tauluun
INSERT INTO Ruoka_Raakaaine (ruoka_id, raakaaine_id) VALUES (1, 1); -- Kanakalkkunapatee sisältää kanaa
INSERT INTO Ruoka_Raakaaine (ruoka_id, raakaaine_id) VALUES (1, 2); -- Kanakalkkunapatee sisältää kalkkunaa
INSERT INTO Ruoka_Raakaaine (ruoka_id, raakaaine_id) VALUES (2, 7); -- Poultry in Gravy sisältää siipikarjaa
INSERT INTO Ruoka_Raakaaine (ruoka_id, raakaaine_id) VALUES (3, 1); -- Pirkka Kana annosateria sisältää kanaa

-- Lisää esimerkkidataa Ruokinta-tauluun
INSERT INTO Ruokinta (ruokinta_aika, taimi_maistui, lempi_maistui, ateria_id, ruoka_id) VALUES ('2025-03-09', true, false, 1, 1);

