-- Vymaže tabuľky ANIMAL a BREED ak existujú
DROP TABLE IF EXISTS animal;
DROP TABLE IF EXISTS breed;

-- Vytvorenie tabuľky BREED
CREATE TABLE breed (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Vytvorenie tabuľky ANIMAL
CREATE TABLE animal (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    age INT NOT NULL CHECK (age > 0),
    gender VARCHAR(6) NOT NULL,
    breed_id BIGINT NOT NULL,
    CONSTRAINT fk_breed FOREIGN KEY (breed_id) REFERENCES breed(id)
);

-- Vloženie inicializačných hodnôt do tabuľky BREED
INSERT INTO breed (id, name) VALUES (1, 'Afganský chrt');
INSERT INTO breed (id, name) VALUES (2, 'Americká akita');
INSERT INTO breed (id, name) VALUES (3, 'Anglický buldog');
INSERT INTO breed (id, name) VALUES (4, 'Belgický ovčiak');
INSERT INTO breed (id, name) VALUES (5, 'Bradáč');
