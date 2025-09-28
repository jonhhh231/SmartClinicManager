# MySQL Schema Design

## Doctor
- id INT PRIMARY KEY AUTO_INCREMENT
- first_name VARCHAR(50)
- last_name VARCHAR(50)
- specialization VARCHAR(50)
- email VARCHAR(100)
- phone VARCHAR(20)

## Patient
- id INT PRIMARY KEY AUTO_INCREMENT
- first_name VARCHAR(50)
- last_name VARCHAR(50)
- email VARCHAR(100)
- phone VARCHAR(20)

## Appointment
- id INT PRIMARY KEY AUTO_INCREMENT
- doctor_id INT
- patient_id INT
- appointment_time DATETIME
- FOREIGN KEY (doctor_id) REFERENCES Doctor(id)
- FOREIGN KEY (patient_id) REFERENCES Patient(id)

## Prescription
- id INT PRIMARY KEY AUTO_INCREMENT
- appointment_id INT
- details TEXT
- FOREIGN KEY (appointment_id) REFERENCES Appointment(id)
