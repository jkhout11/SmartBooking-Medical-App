SELECT patient_first_name, patient_last_name FROM patient WHERE patient_id = (SELECT appointment_id, date_selected FROM appointment WHERE doctor_schedule_id = 
(SELECT doctor_schedule_id FROM doctor_schedule WHERE doctor_id = '5') RETURNING appointment_id);

SELECT patient_first_name, patient_last_name, date_selected, time_slot
FROM patient 
JOIN appointment ON patient.patient_id = appointment.patient_id
JOIN doctor_schedule ON appointment.doctor_schedule_id = doctor_schedule.doctor_schedule_id
JOIN schedule ON doctor_schedule.schedule_id = schedule.schedule_id
WHERE doctor_id = '5';

SELECT * FROM schedule;

SELECT * FROM doctor;

SELECT * FROM patient;

SELECT * FROM appointment;

SELECT * FROM doctor_schedule;

SELECT * FROM office;

SELECT doctor_first_name, doctor_last_name, office_name, office_street_address, office_city, office_state, office_zip_code, office_phone
FROM doctor
JOIN doctor_office ON doctor.doctor_id = doctor_office.doctor_id
JOIN office ON doctor_office.office_id = office.office_id;

SELECT * FROM doctor_office;

INSERT INTO doctor_office (office_id, doctor_id)
VALUES ('7', '2'), ('8', '3'), ('9', '4'), ('10', '5');

INSERT INTO doctor_schedule (doctor_id, schedule_id)
VALUES ('1', '2'), ('2', '3'), ('5', '9');



