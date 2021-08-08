DROP DATABASE IF EXISTS bananaschooltest ;
CREATE DATABASE bananaschooltest;
DROP TABLE IF EXISTS bananaschooltest.public.courses,
    bananaschool.public.groups , bananaschool.public.students;
CREATE TABLE IF NOT EXISTS bananaschooltest.public.courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR UNIQUE NOT NULL,
    discription VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS bananaschooltest.public.groups
(
    id   SERIAL PRIMARY KEY ,
    name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS bananaschooltest.public.students
(
    id       SERIAL PRIMARY KEY,
    group_id INTEGER,
    name     VARCHAR(50)  NOT NULL,
    lastname VARCHAR(50)  NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS bananaschooltest.public.students_courses
(
    student_id INTEGER ,
    course_id INTEGER,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE ,
    FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE
);
INSERT INTO bananaschooltest.public.courses (name,discription)
VALUES ('math','onion'),
       ('oop','fish'),
       ('biology','potatto'),
       ('history','spagetti'),
       ('english','nudles')
    ;
INSERT INTO bananaschooltest.public.groups (name)
VALUES ('fg-12'),
       ('gt-24'),
       ('fh-94'),
       ('og-72'),
       ('se-01')
;
INSERT INTO bananaschooltest.public.students (name,lastname,group_id)
VALUES ('Peter','Tarantino',1),
       ('Dmitri','Tesco',2),
       ('Sakura','Mob',3),
       ('Timofey','Upi',1),
       ('Timofey','Tarantino',3)
;
INSERT INTO bananaschooltest.public.students_courses (student_id, course_id)
VALUES (1,1),
       (1,2),
       (2,1),
       (3,1),
       (2,2)
;