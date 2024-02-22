CREATE SCHEMA `gritacademy` ;

CREATE USER 'grit'@'localhost';
GRANT SELECT ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';
GRANT INSERT ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';
GRANT UPDATE ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';
GRANT DELETE ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';

CREATE USER 'grituser'@'localhost';
GRANT SELECT ON gritacademy.* To 'grituser'@'localhost' IDENTIFIED BY 'grituser';

CREATE TABLE students (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   town VARCHAR(100) NOT NULL,
   hobby TEXT
);

CREATE TABLE courses (
 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 description TEXT,
 yhp INT NOT NULL
);

ALTER TABLE students ADD COLUMN lname VARCHAR(100) NOT NULL;
ALTER TABLE students DROP COLUMN name;
ALTER TABLE students ADD COLUMN fname VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN email VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN phone VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN username VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN password VARCHAR(255) NOT NULL;
ALTER TABLE students ADD CONSTRAINT UC_students_email UNIQUE (email);
ALTER TABLE students ADD CONSTRAINT UC_students_username UNIQUE (username);

CREATE TABLE attendance (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL REFERENCES students(id),
    course_id INT NOT NULL REFERENCES courses(id)
);
ALTER TABLE attendance ADD CONSTRAINT unique_combo_att UNIQUE (student_id, course_id);

CREATE TABLE teachers (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   fname VARCHAR(100) NOT NULL,
   lname VARCHAR(100) NOT NULL,
   town VARCHAR(100) NOT NULL,
   hobby TEXT,
   email VARCHAR(100) NOT NULL,
   phone VARCHAR(100) NOT NULL,
   username VARCHAR(100) NOT NULL,
   password VARCHAR(255) NOT NULL,
   privilage_type ENUM('user', 'admin', 'superadmin') NOT NULL DEFAULT 'user'
);

ALTER TABLE teachers ADD CONSTRAINT UC_teachers_email UNIQUE (email);
ALTER TABLE teachers ADD CONSTRAINT UC_teachers_username UNIQUE (username);

CREATE TABLE teacher_courses (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    teachers_id INT NOT NULL REFERENCES teachers(id),
    course_id INT NOT NULL REFERENCES courses(id)
);

ALTER TABLE teacher_courses ADD CONSTRAINT unique_combo_sc UNIQUE (teachers_id, course_id);

INSERT INTO courses (name, description, yhp) VALUES ('Programmering 101', 'Learn to program in C#', 100);
INSERT INTO courses (name, description, yhp) VALUES ('Programmering 102', 'Learn to program in Java#', 100);
INSERT INTO courses (name, description, yhp) VALUES ('English', 'Learn engrish', 50);
INSERT INTO courses (name, description, yhp) VALUES ('Polish', 'Learn polska', 50);

#Password is "test" here on all
INSERT INTO students (username, town, hobby, fname, lname, email, phone, password) VALUES ('Max', 'Malmö', 'Programming', 'Max', 'Ulfson', 'linus@gmail.com', '123456', '9dc77f50f0cd4a240f64da417026aee60a96732c2b809f49659922098fe3c18b90170ff2b5151a15c493d1550fbc00e8');
INSERT INTO students (username, town, hobby, fname, lname, email, phone, password) VALUES ('Maria', 'Malmö', 'Gaming', 'Maria', 'Lundberg', 'maria@gmail.com', '123456', '9dc77f50f0cd4a240f64da417026aee60a96732c2b809f49659922098fe3c18b90170ff2b5151a15c493d1550fbc00e8');
INSERT INTO students (username, town, hobby, fname, lname, email, phone, password) VALUES ('Sara', 'Helsingborg', 'Gym', 'Sara', 'Tveit', 'sara@gmail.com', '123456', '9dc77f50f0cd4a240f64da417026aee60a96732c2b809f49659922098fe3c18b90170ff2b5151a15c493d1550fbc00e8');

INSERT INTO attendance (student_id, course_id) VALUES (1,1);
INSERT INTO attendance (student_id, course_id) VALUES (1,2);
INSERT INTO attendance (student_id, course_id) VALUES (2,1);
INSERT INTO attendance (student_id, course_id) VALUES (3,1);

INSERT INTO teachers (username, town, hobby, fname, lname, email, phone, password, privilage_type) VALUES ('Leif', 'Lund', 'Gym', 'Leif', 'Benkson', 'leif@gmail.com', '123456', '9dc77f50f0cd4a240f64da417026aee60a96732c2b809f49659922098fe3c18b90170ff2b5151a15c493d1550fbc00e8', 'user');
INSERT INTO teachers (username, town, hobby, fname, lname, email, phone, password, privilage_type) VALUES ('Stiv', 'Malmö', 'Gym', 'Stiv', 'Alhamaan', 'stiv@gmail.com', '123456', '9dc77f50f0cd4a240f64da417026aee60a96732c2b809f49659922098fe3c18b90170ff2b5151a15c493d1550fbc00e8', 'admin');
INSERT INTO teachers (username, town, hobby, fname, lname, email, phone, password, privilage_type) VALUES ('Rektor', 'Malmö', 'Crossfit', 'Rektor', 'Hard', 'rektor@gmail.com', '123456', '9dc77f50f0cd4a240f64da417026aee60a96732c2b809f49659922098fe3c18b90170ff2b5151a15c493d1550fbc00e8', 'superadmin');

INSERT INTO teacher_courses (teachers_id, course_id) VALUES (1,1);
INSERT INTO teacher_courses (teachers_id, course_id) VALUES (1,2);
INSERT INTO teacher_courses (teachers_id, course_id) VALUES (2,3);
INSERT INTO teacher_courses (teachers_id, course_id) VALUES (2,4);
INSERT INTO teacher_courses (teachers_id, course_id) VALUES (3,2);