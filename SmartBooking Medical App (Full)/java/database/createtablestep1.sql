DROP TABLE IF EXISTS doctor;
CREATE TABLE doctor (
doctor_id SERIAL NOT NULL,
doctor_npi VARCHAR (50) NOT NULL UNIQUE,
user_id INT NOT NULL,
doctor_first_name VARCHAR (100),
doctor_last_name VARCHAR (100),
doctor_email VARCHAR (50),
active BOOLEAN,
CONSTRAINT fk_doctor_user FOREIGN KEY (user_id) REFERENCES users (user_id),
CONSTRAINT pk_doctor PRIMARY KEY (doctor_id)
);

DROP TABLE IF EXISTS office;
CREATE TABLE office (
office_id SERIAL NOT NULL,
office_name VARCHAR NOT NULL,
office_street_address VARCHAR (100) NOT NULL,
office_city VARCHAR (50) NOT NULL,
office_state VARCHAR (50) NOT NULL,
office_zip_code VARCHAR (15) NOT NULL,
office_phone VARCHAR (20) NOT NULL,
office_open_time TIME NOT NULL,
office_close_time TIME NOT NULL,
office_open_days VARCHAR (15) NOT NULL,
CONSTRAINT pk_office PRIMARY KEY (office_id)
);

DROP TABLE IF EXISTS doctor_office;
CREATE TABLE doctor_office(
office_id INT NOT NULL,
doctor_id INT NOT NULL,
CONSTRAINT pk_doctor_office PRIMARY KEY (office_id, doctor_id),
CONSTRAINT fk_office FOREIGN KEY (office_id) REFERENCES office (office_id),
CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id)
);

DROP TABLE IF EXISTS patient;
CREATE TABLE patient(
patient_id SERIAL,
user_id INT NOT NULL,
patient_first_name VARCHAR (100),
patient_last_name VARCHAR (100),
patient_street_address VARCHAR (100),
patient_city VARCHAR (50),
patient_state VARCHAR (50),
patient_zip_code VARCHAR (20),
patient_phone_number VARCHAR (20),
patient_email VARCHAR (50),
patient_dob DATE,
active BOOLEAN,
CONSTRAINT pk_patient PRIMARY KEY (patient_id),
CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);
--ALTER TABLE patient ADD COLUMN active BOOLEAN;
--ALTER TABLE patient ALTER COLUMN  DROP NOT NULL;

DROP TABLE IF EXISTS schedule;
CREATE TABLE schedule(
schedule_id SERIAL,
day_of_the_week VARCHAR,
time_slot TIME,
CONSTRAINT pk_schedule PRIMARY KEY (schedule_id)
);



DROP TABLE IF EXISTS doctor_schedule;
CREATE TABLE doctor_schedule(
doctor_schedule_id SERIAL,
doctor_id INT,
schedule_id INT,
CONSTRAINT pk_doctor_schedule PRIMARY KEY (doctor_schedule_id),
CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),
CONSTRAINT fk_schedule_id FOREIGN KEY (schedule_id) REFERENCES schedule (schedule_id)
);

DROP TABLE IF EXISTS appointment;
CREATE TABLE appointment(
	appointment_id SERIAL,
	patient_id INT NOT NULL,
	doctor_schedule_id INT NOT NULL,
	date_selected DATE NOT NULL,
	CONSTRAINT pk_appointment PRIMARY KEY (appointment_id),
	CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
	CONSTRAINT fk_doctor_schedule FOREIGN KEY (doctor_schedule_id) REFERENCES doctor_schedule (doctor_schedule_id)
);

DROP TABLE IF EXISTS schedule_appointment;
CREATE TABLE schedule_appointment(
booked_id SERIAL,
schedule_id INT,
appointment_id INT,
slot_available BOOLEAN,
CONSTRAINT pk_booked PRIMARY KEY (booked_id),
CONSTRAINT fk_schedule FOREIGN KEY (schedule_id) REFERENCES schedule (schedule_id),
CONSTRAINT fk_appointment FOREIGN KEY (appointment_id) REFERENCES appointment (appointment_id)
);

DROP TABLE IF EXISTS booked_doctor;
CREATE TABLE booked_doctor(
doctor_id INT,
booked_id INT,
CONSTRAINT pk_booked_doctor PRIMARY KEY (doctor_id, booked_id),
CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),
CONSTRAINT fk_booked_id FOREIGN KEY (booked_id) REFERENCES schedule_appointment (booked_id)
);

DROP TABLE IF EXISTS review;
CREATE TABLE review(
	review_id SERIAL,
	reviewer INT,
	review_rating INT,
	review_content VARCHAR (10000),
	review_date DATE,
	CONSTRAINT pk_review PRIMARY KEY (review_id),
	CONSTRAINT fk_reviewer FOREIGN KEY (reviewer) REFERENCES users (user_id)
);

DROP TABLE IF EXISTS review_response;
CREATE TABLE review_response(
	response_id SERIAL,
	review_id INT,
	responder INT,
	response_content VARCHAR (10000),
	CONSTRAINT pk_response PRIMARY KEY (response_id),
	CONSTRAINT fk_review FOREIGN KEY (review_id) REFERENCES review (review_id),
	CONSTRAINT fk_responder FOREIGN KEY (responder) REFERENCES users (user_id)
);

DROP TABLE IF EXISTS doctor_patient;
CREATE TABLE doctor_patient(
doctor_id INT,
patient_id INT,
CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),
CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
CONSTRAINT pk_doctor_patient PRIMARY KEY (doctor_id, patient_id)
);

