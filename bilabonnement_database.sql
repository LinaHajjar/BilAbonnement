CREATE DATABASE bilabonnement;

CREATE TABLE kunde(
telefonnummer VARCHAR(50) PRIMARY KEY,
email VARCHAR(100),
fornavn VARCHAR(200),
efternavn VARCHAR(200),
adresse VARCHAR(200),
postnummer INT,
byen VARCHAR(100),
koerekortnummer VARCHAR(50),
udstedelsdato DATE
);

CREATE TABLE bil (
nummerplade VARCHAR(50) PRIMARY KEY,
maerke VARCHAR(100),
model VARCHAR(100),
braendstoftype ENUM('ELEKTIK', 'BENZIN', 'DIESEL', 'HYBRID'),
odometer INT,
foersteregistrering DATE,
co2udledning INT
);

CREATE TABLE lejekontrakt(
lejekontrakt_id INT AUTO_INCREMENT PRIMARY KEY,
telefonnummer VARCHAR(50),
FOREIGN KEY (telefonnummer) REFERENCES kunde(telefonnummer),
nummerplade VARCHAR(50),
CONSTRAINT fk_lejekontrakt_nummerplade 
FOREIGN KEY (nummerplade) REFERENCES bil(nummerplade) ON DELETE SET NULL,
startdato DATE,
slutdato DATE,
maxKm INT,
pris DOUBLE
);

CREATE TABLE afdeling(
afdeling_id INT PRIMARY KEY,
afdeling_navn VARCHAR(100)
);

CREATE TABLE medarbejde(
afdeling_id INT,
FOREIGN KEY (afdeling_id) REFERENCES afdeling(afdeling_id),
brugernavn VARCHAR(100) PRIMARY KEY,
kode VARCHAR(100)
);

CREATE TABLE skader(
lejekontrakt_id INT,
FOREIGN KEY (lejekontrakt_id) REFERENCES lejekontrakt(lejekontrakt_id),
skade_type VARCHAR(100),
beskrivelse TEXT,
pris DOUBLE);

INSERT INTO kunde (telefonnummer, email, fornavn, efternavn, adresse, postnummer, byen, koerekortnummer, udstedelsdato)
VALUES
('12345678', 'john.doe@example.com', 'John', 'Doe', 'Main Street 1', 1000, 'Copenhagen', 'DK123456', '2020-01-15'),
('87654321', 'jane.smith@example.com', 'Jane', 'Smith', 'Elm Street 5', 8000, 'Aarhus', 'DK654321', '1993-06-25'),
('11223344', 'alice.brown@example.com', 'Alice', 'Brown', 'Birch Avenue 10', 5000, 'Odense', 'DK987654', '2001-03-12'),
('32987654', 'annen@yahoo.com',	'Anne', 'Nielse', 'Nørrebrogade 25', 2200, 'København N', 'DK543210', '2020-02-20'),
('40123456', 'mikkeljensen@hotmail.com', 'Mikkel', 'Jensen', 'Frederiksberg Allé 20', 2000, 'Frederiksberg', 'DK55533',	'1999-03-12'),
('33124567', 'lottehansen@gmail.com', 'Lotte', 'Hansen', 'Østerbrogade 12', 2100, 'København Ø', 'DK123456', '2018-05-15'),
('41239876', 'peter.andersen@outlook.com', 'Peter', 'Andersen', 'Vesterbrogade 56', 1620, 'København V', 'DK987654', '2017-11-23'),
('45236789', 'karenpoulsen@mail.dk', 'Karen', 'Poulsen', 'Amagerbrogade 78', 2300, 'København S', 'DK456789', '2019-08-14'),
('50346781', 'niels.olesen@yahoo.com', 'Niels', 'Olesen', 'H.C. Andersens Boulevard 10', 1553, 'København K', 'DK234567', '2021-03-10'),
('61234578', 'maria.jorgensen@hotmail.com', 'Maria', 'Jørgensen', 'Åboulevard 45', 2200, 'København N', 'DK654321', '2016-06-18'),
('73129867', 'johan.larsen@gmail.com', 'Johan', 'Larsen', 'Sølvgade 30', 1307, 'København K', 'DK876543', '2020-01-30'),
('84012435', 'camilla.frederiksen@live.com', 'Camilla', 'Frederiksen', 'Valby Langgade 50', 2500, 'Valby', 'DK333444', '2015-12-20'),
('91524367', 'simon.petersen@gmail.com', 'Simon', 'Petersen', 'Godthåbsvej 67', 2000, 'Frederiksberg', 'DK555111', '2018-04-05'),
('28437615', 'heidi.schmidt@mail.dk', 'Heidi', 'Schmidt', 'Gl. Kongevej 100', 1850, 'Frederiksberg', 'DK222888', '2019-09-13'),
('37982451', 'anders.mikkelsen@yahoo.com', 'Anders', 'Mikkelsen', 'Nørrebrogade 40', 2200, 'København N', 'DK999777', '2022-07-22');

INSERT INTO bil (nummerplade, maerke, model, braendstoftype, odometer, foersteregistrering, co2udledning)
VALUES
('AB12345', 'Tesla', 'Model 3', 'ELEKTIK', '20000', '2022-05-01', 0),
('CD67890', 'Volkswagen', 'Golf', 'BENZIN', '45000', '2021-09-15', 120),
('EF11223', 'Toyota', 'Corolla', 'HYBRID', '30000', '2020-11-20', 90),
('GH45678', 'BMW', '3-serie', 'DIESEL', 55000, '2019-03-10', 140),
('IJ90123', 'Ford', 'Fiesta', 'BENZIN', 25000, '2023-07-05', 115),
('KL54321', 'Skoda', 'Octavia', 'BENZIN', 38000, '2022-11-25', 130),
('MN78901', 'Mercedes-Benz', 'C-klasse', 'DIESEL', 60000, '2018-09-12', 150),
('OP34567', 'Nissan', 'Leaf', 'ELEKTIK', 15000, '2024-01-18', 0),
('QR90123', 'Audi', 'A4', 'BENZIN', 42000, '2021-05-20', 135),
('ST56789', 'Peugeot', '208', 'BENZIN', 29000, '2022-03-15', 120),
('UV12345', 'Renault', 'Zoe', 'ELEKTIK', 18000, '2023-02-12', 0),
('WX67890', 'Hyundai', 'Ioniq', 'HYBRID', 32000, '2020-08-10', 100),
('YZ45678', 'Kia', 'Sportage', 'DIESEL', 50000, '2019-10-25', 160),
('AB65432', 'Mazda', 'CX-5', 'BENZIN', 41000, '2021-12-01', 130),
('CD78901', 'Honda', 'Civic', 'BENZIN', 37000, '2020-05-30', 110),
('EF23456', 'Tesla', 'Model Y', 'ELEKTIK', 15000, '2024-06-01', 0),
('GH12345', 'Volkswagen', 'Passat', 'DIESEL', 60000, '2019-07-15', 145),
('IJ67890', 'BMW', 'X1', 'BENZIN', 33000, '2021-04-20', 125),
('KL89012', 'Ford', 'Puma', 'HYBRID', 20000, '2023-09-10', 85);

INSERT INTO lejekontrakt (telefonnummer, nummerplade, startdato, slutdato, maxkm, pris)
VALUES
('33124567', 'AB12345', '2023-01-01', '2023-06-30', 10000, 2000.00),
('37982451', 'CD67890', '2023-03-01', '2023-09-01', 15000, 2500.50),
('12345678', 'EF11223', '2023-05-15', '2023-11-15', 12000, 2200.75),
('40123456', 'AB12345', '2023-01-01', '2023-06-30', 10000, 2000.00),
('28437615', 'EF11223', '2023-05-15', '2023-11-15', 12000, 2200.75),
('91524367', 'QR90123', '2023-02-01', '2023-07-31', 8000, 1800.00),
('41239876', 'OP34567', '2023-04-01', '2023-10-01', 20000, 3000.00),
('45236789', 'YZ45678', '2023-06-01', '2023-12-01', 15000, 2500.50),
('50346781', 'CD78901', '2023-08-15', '2024-02-15', 10000, 2000.75),
('61234578', 'IJ67890', '2023-10-01', '2024-03-31', 12000, 2200.50),
('73129867', 'ST56789', '2023-07-01', '2024-01-01', 14000, 2600.25),
('84012435', 'KL89012', '2023-09-01', '2024-03-01', 18000, 2900.00),
('28437615', 'WX67890', '2023-11-01', '2024-05-01', 9000, 2100.00),
('37982451', 'YZ45678', '2023-03-01', '2023-09-01', 16000, 2700.50);


INSERT INTO afdeling (afdeling_id, afdeling_navn) VALUES
(1, 'DATAREGISTRERING'),
(2, 'SKADE'),
(3, 'STATISTIKKER');

INSERT INTO medarbejde (afdeling_id, brugernavn, kode) VALUES
(1, 'datalejsalg', 'bruger123'),
(2, 'forretningsudvikler', 'bruger123'),
(3, 'skadeogrep', 'bruger123');

INSERT INTO skader (lejekontrakt_id, skade_type, beskrivelse, pris) VALUES
(1, 'Skade', 'Bulen på venstre dør', 1500.00),
(4, 'Mangel', 'Manglende reservedæk', 500.00),
(10, 'Fejl', 'Defekt bremse', 3000.00);



CREATE TABLE BilHaandtering (
    haandtering_id INT AUTO_INCREMENT PRIMARY KEY,
    lejekontrakt_id INT NOT NULL,
    nummerplade VARCHAR(15) NOT NULL,
    handlingsdato DATE NOT NULL,                   -- Date for levering eller returnering
    handlingslokation VARCHAR(50) NOT NULL,       -- Location af levering eller returnering
    type ENUM('UDLEVERING', 'RETURNERING') NOT NULL,
    status ENUM('UNDER_LEVERING', 'LEVERET', 'AFVENTER_INSPEKTION', 'INSPICERET', 'KLAR_TIL_SALG') 
        DEFAULT 'UNDER_LEVERING',  -- Default status for ny records

    FOREIGN KEY (lejekontrakt_id) REFERENCES lejekontrakt(lejekontrakt_id),
    FOREIGN KEY (nummerplade) REFERENCES bil(nummerplade)
);

 -- Insert data into BilHaandtering, ensuring foreign key constraints are respected
INSERT INTO BilHaandtering (lejekontrakt_id, nummerplade, handlingsdato, handlingslokation, type, status) 
VALUES 
(1, 'AB12345', '2024-12-01', 'Bilabonnement', 'Udlevering', 'Leveret'),
(1, 'AB12345', '2025-06-01', 'FDM station', 'Returnering', 'Afventer_Inspektion'),
(2, 'CD67890', '2024-12-05', 'DS Salon', 'Udlevering', 'Leveret'),
(2, 'CD67890', '2025-06-15', 'Bilabonnement', 'Returnering', 'Afventer_Inspektion'),
(3, 'EF11223', '2024-12-10', 'Bilabonnement', 'Udlevering', 'Leveret'),
(3, 'EF11223', '2025-06-20', 'FDM station', 'Returnering', 'Inspiceret'),
(4, 'AB12345', '2024-12-15', 'DS Salon', 'Udlevering', 'Leveret'),
(4, 'AB12345', '2025-07-01', 'Bilabonnement', 'Returnering', 'Klar_til_Salg'),
(5, 'EF11223', '2024-12-20', 'Bilabonnement', 'Udlevering', 'Leveret'),
(5, 'EF11223', '2025-07-10', 'FDM station', 'Returnering', 'Klar_til_Salg'),
(6, 'QR90123', '2024-12-25', 'DS Salon', 'Udlevering', 'Leveret'),
(6, 'QR90123', '2025-07-15', 'Bilabonnement', 'Returnering', 'Afventer_Inspektion'),
(7, 'OP34567', '2024-12-30', 'Bilabonnement', 'Udlevering', 'Leveret'),
(7, 'OP34567', '2025-08-01', 'FDM station', 'Returnering', 'Inspiceret'),
(8, 'YZ45678', '2025-01-05', 'DS Salon', 'Udlevering', 'Leveret'),
(8, 'YZ45678', '2025-08-15', 'Bilabonnement', 'Returnering', 'Klar_til_Salg'),
(9, 'CD78901', '2025-02-10', 'Bilabonnement', 'Udlevering', 'Leveret'),
(9, 'CD78901', '2025-09-20', 'FDM station', 'Returnering', 'Afventer_Inspektion'),
(10, 'IJ67890', '2025-03-15', 'DS Salon', 'Udlevering', 'Leveret'),
(10, 'IJ67890', '2025-10-05', 'Bilabonnement', 'Returnering', 'Inspiceret'),
(11, 'ST56789', '2025-04-01', 'Bilabonnement', 'Udlevering', 'Leveret'),
(11, 'ST56789', '2025-11-25', 'FDM station', 'Returnering', 'Klar_til_Salg'),
(12, 'KL89012', '2025-05-10', 'DS Salon', 'Udlevering', 'Leveret'),
(12, 'KL89012', '2025-12-15', 'Bilabonnement', 'Returnering', 'Inspiceret'),
(13, 'WX67890', '2025-06-20', 'Bilabonnement', 'Udlevering', 'Leveret'),
(13, 'WX67890', '2026-01-01', 'FDM station', 'Returnering', 'Afventer_Inspektion'),
(14, 'YZ45678', '2025-07-15', 'DS Salon', 'Udlevering', 'Leveret'),
(14, 'YZ45678', '2026-01-30', 'Bilabonnement', 'Returnering', 'Klar_til_Salg');


ALTER TABLE lejekontrakt DROP FOREIGN KEY lejekontrakt_ibfk_1;

ALTER TABLE lejekontrakt MODIFY telefonnummer VARCHAR(50) NULL;

ALTER TABLE lejekontrakt
ADD CONSTRAINT lejekontrakt_ibfk_1
FOREIGN KEY (telefonnummer) REFERENCES kunde(telefonnummer)
ON DELETE SET NULL;
