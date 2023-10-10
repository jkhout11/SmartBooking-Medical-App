INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('1', '3', 'This place is just ok', '08/12/2023', '1', 'Average')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('2', '5', 'Best doctor office I have ever been to!', '08/13/2023', '2', 'Excellent!')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('3', '4', 'Very good doctor just poor bedside manner', '08/14/2023', '3', 'Pretty good')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('4', '2', 'What was all that writing in my chart?  I do not trust this doctor', '08/15/2023', '4', 'Do not come here')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('5', '1', 'The worst place I have ever been, unprofessional, rude, incompetent', '08/11/2023', '5', 'Terrible')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('1', '4', 'Pretty decent, everyone is nice', '08/10/2023', '1', 'Thumbs Up')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('4', '3', 'Average doctor, average office', '08/12/2023', '4', 'Average')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('1', '5', 'The happiest place on earth because Disney is too expensive', '08/15/2023', '5', '5 stars')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('2', '3', 'This place is just ok', '08/09/2023', '2', 'Average')
RETURNING review_id;

INSERT INTO review (reviewer, review_rating, review_content, review_date, reviewed_office, review_title)
VALUES ('5', '5', 'Fantastic is all I can say', '08/12/2023', '3', 'Great')
RETURNING review_id;