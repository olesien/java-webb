CREATE SCHEMA `grit` ;

CREATE USER 'grit'@'localhost';
GRANT SELECT ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';
GRANT INSERT ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';
GRANT UPDATE ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';
GRANT DELETE ON gritacademy.* To 'grit'@'localhost' IDENTIFIED BY 'grit';

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

#TRUNCATE students;

ALTER TABLE students ADD COLUMN lname VARCHAR(100) NOT NULL;
ALTER TABLE students DROP COLUMN name;
ALTER TABLE students ADD COLUMN fname VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN email VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN phone VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN username VARCHAR(100) NOT NULL;
ALTER TABLE students ADD COLUMN password VARCHAR(255) NOT NULL;

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
   privilage_type ENUM("user", "admin", "superadmin") NOT NULL DEFAULT "user"
);

CREATE TABLE teacher_courses (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    teachers_id INT NOT NULL REFERENCES teachers(id),
    course_id INT NOT NULL REFERENCES courses(id)
);