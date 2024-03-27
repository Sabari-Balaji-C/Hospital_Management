create database hospital_management;
use hospital_management;

CREATE TABLE Patient (
    patientId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    gender VARCHAR(10),
    contactNumber VARCHAR(15),
    address VARCHAR(255)
);

INSERT INTO Patient (patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address)
VALUES
    (1, 'John', 'Doe', '1990-05-15', 'Male', '1234567890', '123 Main St'),
    (2, 'Jane', 'Smith', '1985-10-20', 'Female', '9876543210', '456 Elm St'),
    (3, 'Michael', 'Johnson', '1978-07-08', 'Male', '5551234567', '789 Oak Ave'),
    (4, 'Emily', 'Williams', '1995-03-25', 'Female', '9998887777', '321 Pine Rd'),
    (5, 'David', 'Brown', '1980-11-12', 'Male', '1112223333', '555 Maple Ln');
    
select * from Patient;
desc patient;

CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    specialization VARCHAR(100),
    contactNumber VARCHAR(15)
);

INSERT INTO Doctor (doctorId, firstName, lastName, specialization, contactNumber)
VALUES
    (1, 'Dr. Smith', 'Johnson', 'Cardiologist', '5554443333'),
    (2, 'Dr. Lisa', 'Anderson', 'Pediatrician', '6667778888'),
    (3, 'Dr. David', 'White', 'Dermatologist', '7778889999'),
    (4, 'Dr. Sarah', 'Martinez', 'Neurologist', '8889990000'),
    (5, 'Dr. James', 'Taylor', 'Orthopedic Surgeon', '9990001111');
    
select * from Doctor;
desc Doctor;

CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT,
    doctorId INT,
    appointmentDate DATE,
    description VARCHAR(255),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId));
    
    INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description)
VALUES
    (101, 1, 1, '2024-04-01', 'Routine checkup'),
    (102, 2, 3, '2024-04-05', 'Skin consultation'),
    (103, 3, 2, '2024-04-10', 'Pediatric checkup'),
    (104, 4, 4, '2024-04-15', 'Neurology follow-up'),
    (105, 5, 5, '2024-04-20', 'Orthopedic surgery consultation');
    
select * from Appointment;
delete from Appointment where appointmentId=106;
desc appointment;