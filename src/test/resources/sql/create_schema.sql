CREATE TABLE groups
(
    group_id   SERIAL             NOT NULL PRIMARY KEY,
    group_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE courses
(
    course_id   SERIAL             NOT NULL PRIMARY KEY,
    course_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE lesson_rooms
(
    lesson_room_id   SERIAL      NOT NULL PRIMARY KEY,
    lesson_room_name VARCHAR(50) NOT NULL
);

CREATE TABLE lesson_time
(
    lesson_time_id SERIAL              NOT NULL PRIMARY KEY,
    lesson_start   TIME WITH TIME ZONE NOT NULL,
    lesson_end     TIME WITH TIME ZONE NOT NULL
);

CREATE TABLE roles
(
    role_id   SERIAL             NOT NULL PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users
(
    user_id    SERIAL              NOT NULL PRIMARY KEY,
    login      VARCHAR(50) UNIQUE  NOT NULL,
    password   VARCHAR(50)         NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    first_name varchar(255)        NOT NULL,
    last_name  varchar(255)        NOT NULL
);

CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE teachers
(
    teacher_user_id INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (teacher_user_id) REFERENCES users
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE teacher_courses
(
    teacher_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (teacher_id, course_id),
    FOREIGN KEY (teacher_id) REFERENCES users
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT teachers_courses_unique UNIQUE (teacher_id,course_id)
);


CREATE TABLE staff
(
    staff_user_id INT UNIQUE NOT NULL PRIMARY KEY,
    FOREIGN KEY (staff_user_id) REFERENCES users
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE students
(
    student_user_id  INT UNIQUE NOT NULL PRIMARY KEY,
    group_id INT,
    FOREIGN KEY (student_user_id) REFERENCES users,
    FOREIGN KEY (group_id) REFERENCES groups
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE lessons
(
    lesson_id      SERIAL NOT NULL PRIMARY KEY,
    lesson_time_id INT    NOT NULL,
    date           DATE   NOT NULL,
    group_id       INT    NOT NULL,
    course_id      INT    NOT NULL,
    teacher_id     INT    NOT NULL,
    lesson_room_id INT    NOT NULL,
    FOREIGN KEY (lesson_time_id) REFERENCES lesson_time,
    FOREIGN KEY (group_id) REFERENCES groups,
    FOREIGN KEY (course_id) REFERENCES courses,
    FOREIGN KEY (teacher_id) REFERENCES teachers,
    FOREIGN KEY (lesson_room_id) REFERENCES lesson_time,
    CONSTRAINT teacher_unique UNIQUE (teacher_id, lesson_time_id, date),
    CONSTRAINT groups_unique UNIQUE (group_id, lesson_time_id, date),
    CONSTRAINT lesson_rooms_unique UNIQUE (lesson_room_id, lesson_time_id, date)
);