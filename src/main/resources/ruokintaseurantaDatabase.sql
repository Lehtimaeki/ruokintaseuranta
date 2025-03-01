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
    ruokaId BIGSERIAL PRIMARY KEY,
    ruokaNimi VARCHAR(500) NOT NULL,
    ruokaPisteet DOUBLE PRECISION NOT NULL,
    valmistajaId BIGINT,
    FOREIGN KEY (valmistajaId) REFERENCES Valmistaja(valmistajaId)
);

-- Ruoka_Raakaaine-taulu (monen-moneen -suhde)
CREATE TABLE Ruoka_Raakaaine (
    ruokaId BIGINT,
    raakaaineId BIGINT,
    PRIMARY KEY (ruokaId, raakaaineId),
    FOREIGN KEY (ruokaId) REFERENCES Ruoka(ruokaId),
    FOREIGN KEY (raakaaineId) REFERENCES Raakaaine(raakaaineId)
);

-- Ruokinta-taulu
CREATE TABLE Ruokinta (
    ruokintaId BIGSERIAL PRIMARY KEY,
    ruokintaAika DATE NOT NULL,
    taimiMaistui BOOLEAN NOT NULL,
    lempiMaistui BOOLEAN NOT NULL,
    ateriaId BIGINT,
    ruokaId BIGINT,
    FOREIGN KEY (ateriaId) REFERENCES Ateria(ateriaId),
    FOREIGN KEY (ruokaId) REFERENCES Ruoka(ruokaId)
);
