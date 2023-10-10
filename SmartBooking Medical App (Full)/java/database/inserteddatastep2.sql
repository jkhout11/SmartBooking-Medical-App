INSERT INTO office (office_street_address, office_city, office_state, office_zip_code, 
					office_phone, office_open_time, office_close_time, office_open_days, office_name)
VALUES ('735 Ogletown Stanton Rd, #3302', 'Newark', 'DE', '19713', '302-623-1234', '8:00AM', '4:30PM', 'Monday - Friday', 'Spine Center'),
('14 Sandhill Dr, Unit 101', 'Middletown', 'DE', '19709', '302-378-1234', '8:00AM', '5:00PM', 'Monday - Friday', 'Familycare Associates LLC'),
('401 Brandywine Parkway, Suites 100 & 101', 'Wilmington', 'DE', '19803', '302-655-1234', '9:00AM', '5:00PM', 'Monday - Friday', 'Orthopaedic Specialists, PA'),
('383 Limestone Rd', 'Wilmington', 'DE', '19808', '302-274-1234', '8:00AM', '5:00PM', 'Monday - Friday', 'Plastic Surgery Associates'),
('920 Dupont Parkway', 'Townsend', 'DE', '19734', '302-449-1234', '8:00AM', '5:00PM', 'Monday - Friday', 'Pediatric Doctors of Delaware')
RETURNING office_id;

INSERT INTO users (username,password_hash,role) VALUES ('stephadams','$2a$10$4wgAD/zExbi9B4piSFctCeSMJne0B7UIQzn6.wwWCy596yPZS7hpu','ROLE_USER'),
('ajohn36','$2a$10$sf41DnrOQos5rNgJWVnIluxdbGHachSlwAVIvhJFsiT9JrUAnkw26','ROLE_USER'),
('notbatman','$2a$10$pVrOoQ22IrbQs7sFMwoFkeGHVzIc.GaoH95r.aV5pISoIcnyPAha6','ROLE_USER'),
('notsuperman','$2a$10$YmRoRsn9TmMA4lQA6r5zr.a7XRMvLM2nliwixLWBmbtA29P9MtFyK','ROLE_USER'),
('notspiderman','$2a$10$yjIVQDtkKUStY5N6f0S/m.SUUhzbFqS2gVJDGgtTUt9/8yGiSncnS','ROLE_USER'),
('afreenk','$2a$10$xbZPJXgurArkbfIwuo64TeY3X3uCIK.Iu8jRLj.6GJJUe0jTfODU2','ROLE_USER'),
('batmanbutler','$2a$10$BllZwqw7yPUoKgJ1q87CWOaOklQwbTLo2i4L5XOQqYLI5CKNYx5Mu','ROLE_USER'),
('metropolis#1reporter','$2a$10$Htp4WNVd9.LNCCbvrtNF7eSlWsDDUzh0aMPJN5RNNkYMagvkC5A86','ROLE_USER'),
('iluvspiderman','$2a$10$LgyH/W9E4ID7WiL8af8WPeKafK8HOVQoZHegmo51Rw7ScCrgRK4f6','ROLE_USER'),
('neomorpheus','$2a$10$L2r072IuiV.AjuYm5Ne3Ne/g6WSpbQ.A3y.z06fHxcI83xHQjfyfG','ROLE_USER');



INSERT INTO patient (user_id, patient_first_name, patient_last_name, patient_street_address, patient_city, patient_state, patient_zip_code, patient_phone_number,
					patient_email, patient_dob)
VALUES ('3', 'Stephanie', 'Adams', '271 Adams St', 'Hockessin', 'DE', '19707', '302-123-4567', 'stephanieadamsisfantastic@yahoo.com', '01/01/2000'),
('4', 'Adam', 'Johnson', '313 Johnson St', 'Townsend', 'DE', '19734', '302-098-7654', 'adamjohnsonisgreat@gmail.com', '10/19/1985'),
('5', 'Bruce', 'Wayne', '1007 Mountain Dr', 'Gotham', 'DE', '19700', '302-122-8626', 'iamdefinitelynotbatman@gmail.com', '04/07/1915'),
('6', 'Clark', 'Kent', '344 Clinton St Apt 3D', 'Metropolis', 'DE', '19800', '307-873-7626', 'iamdefinitelynotsuperman@gmail.com', '04/18/1977'),
('7', 'Peter', 'Parker', '20 Ingram St', 'Queens', 'DE', '19900', '377-433-7626', 'iamdefinitelynotspiderman@gmail.com', '08/10/1946');




INSERT INTO schedule (day_of_the_week, time_slot)
VALUES ('Monday', '8:00AM'), ('Monday', '8:30AM'), ('Monday', '9:00AM'), ('Monday', '9:30AM'), ('Monday', '10:00AM'), ('Monday', '10:30AM'),
('Monday', '11:00AM'), ('Monday', '11:30AM'), ('Monday', '12:00PM'), ('Monday', '12:30PM'), ('Monday', '1:00PM'), ('Monday', '1:30PM'), ('Monday', '2:00PM'),
('Monday', '2:30PM'), ('Monday', '3:00PM'), ('Monday', '3:30PM'), ('Monday', '4:00PM'), ('Monday', '4:30PM'), ('Tuesday', '8:00AM'), ('Tuesday', '8:30AM'),
('Tuesday', '9:00AM'), ('Tuesday', '9:30AM'), ('Tuesday', '10:00AM'), ('Tuesday', '10:30AM'), ('Tuesday', '11:00AM'), ('Tuesday', '11:30AM'), ('Tuesday', '12:00PM'),
('Tuesday', '12:30PM'), ('Tuesday', '1:00PM'), ('Tuesday', '1:30PM'), ('Tuesday', '2:00PM'), ('Tuesday', '2:30PM'), ('Tuesday', '3:00PM'), ('Tuesday', '3:30PM'), 
('Tuesday', '4:00PM'), ('Tuesday', '4:30PM'), ('Wednesday', '8:00AM'), ('Wednesday', '8:30AM'), ('Wednesday', '9:00AM'), ('Wednesday', '9:30AM'), ('Wednesday', '10:00AM'),
('Wednesday', '10:30AM'), ('Wednesday', '11:00AM'), ('Wednesday','11:30AM'), ('Wednesday','12:00PM'), ('Wednesday','12:30PM'), ('Wednesday','1:00PM'), ('Wednesday','1:30PM'),
('Wednesday','2:00PM'), ('Wednesday','2:30PM'), ('Wednesday','3:00PM'), ('Wednesday','3:30PM'), ('Wednesday','4:00PM'), ('Wednesday','4:30PM'),
('Thursday', '8:00AM'), ('Thursday', '8:30AM'), ('Thursday', '9:00AM'), ('Thursday', '9:30AM'), ('Thursday', '10:00AM'), ('Thursday','10:30AM'), 
('Thursday', '11:00AM'), ('Thursday', '11:30AM'), ('Thursday', '12:00PM'), ('Thursday', '12:30PM'), ('Thursday', '1:00PM'), ('Thursday','1:30PM'),
('Thursday','2:00PM'), ('Thursday','2:30PM'), ('Thursday','3:00PM'), ('Thursday','3:30PM'), ('Thursday','4:00PM'), ('Thursday','4:30PM'), ('Friday','8:00AM'),
('Friday','8:30AM'), ('Friday','9:00AM'), ('Friday','9:30AM'), ('Friday','10:00AM'), ('Friday','10:30AM'), ('Friday','11:00AM'), ('Friday','11:30AM'),
('Friday','12:00PM'), ('Friday','12:30PM'), ('Friday','1:00PM'), ('Friday','1:30PM'), ('Friday','2:00PM'), ('Friday','2:30PM'), ('Friday','3:00PM'),
('Friday','3:30PM'), ('Friday','4:00PM'), ('Friday','4:30PM');

INSERT INTO doctor (doctor_npi, user_id, doctor_first_name, doctor_last_name, doctor_email)
VALUES ('1', '8', 'Afreen', 'Kazi', 'drafreenkazi@gmail.com'),
('2', '9', 'Alfred', 'Pennyworth', 'brucewayneisnotbatman@yahoo.com'),
('3', '10', 'Lois', 'Lane', 'clarkkentisnotsuperman@gmail.com'),
('4', '11', 'Mary Jane', 'Watson', 'peterparkerisnotspiderman@yahoo.com'),
('5', '12', 'Myron', 'Law', 'thelawdoctor@gmail.com');



--SELECT * FROM doctor_office;

--SELECT day_of_the_week, time_slot FROM schedule;

--SELECT * FROM doctor;

--SELECT * FROM appointment;

--SELECT * FROM schedule;

--SELECT * FROM doctor_schedule;

--SELECT * FROM doctor_office;

--SELECT * FROM users;

--SELECT * FROM schedule;

--SELECT * FROM patient;

--SELECT * FROM office;

--SELECT username FROM users WHERE user_id = (SELECT user_id FROM patient WHERE patient_first_name = 'Stephanie');


--Example to use for doctor agenda
--SELECT patient_first_name, patient_last_name FROM patient WHERE patient_id = (SELECT patient_id FROM appointment WHERE doctor_schedule_id = '3');

--Example to find doctor from appointment
--SELECT doctor_first_name, doctor_last_name FROM doctor WHERE doctor_id = (SELECT doctor_id FROM doctor_schedule WHERE doctor_schedule_id = 
--(SELECT doctor_schedule_id FROM appointment WHERE appointment_id = '1'));

--Get doctor by office
--SELECT doctor_first_name, doctor_last_name FROM doctor WHERE doctor_id = 
