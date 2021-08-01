DROP TABLE IF EXISTS bananaschool.public.courses,
    bananaschool.public.groups , bananaschool.public.students;
CREATE TABLE IF NOT EXISTS bananaschool.public.courses
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR UNIQUE NOT NULL,
    discription VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS bananaschool.public.groups
(
    id   SERIAL PRIMARY KEY ,
    name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS bananaschool.public.students
(
    id       SERIAL PRIMARY KEY,
    group_id INTEGER,
    name     VARCHAR(50)  NOT NULL,
    lastname VARCHAR(50)  NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups (id)
);
CREATE TABLE IF NOT EXISTS bananaschool.public.students_courses
(
    student_id INTEGER ,
    course_id INTEGER,
    FOREIGN KEY (student_id) REFERENCES students (id),
    FOREIGN KEY (course_id) REFERENCES courses (id)
);
