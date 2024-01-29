CREATE SCHEMA `grit` ;

CREATE USER 'grit'@'localhost';
GRANT SELECT ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';

CREATE TABLE students (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   town VARCHAR(100) NOT NULL,
   hobby TEXT
);

INSERT INTO students (name, town, hobby) VALUES ('Linus', 'Malmö', 'Programming');
INSERT INTO students (name, town, hobby) VALUES ('Maria', 'Stockholm', 'Painting');

CREATE TABLE courses (
 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 description TEXT
);

ALTER TABLE courses ADD COLUMN yhp INT NOT NULL;

INSERT INTO courses (name, description, yhp) VALUES ('Programmering 101', 'Learn to program in C#', 100);
INSERT INTO courses (name, description, yhp) VALUES ('Programmering 102', 'Learn to program in Java#', 100);

CREATE TABLE attendance (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL REFERENCES students(id),
    course_id INT NOT NULL REFERENCES courses(id)
);

INSERT INTO attendance (student_id, course_id) VALUES (1,2);