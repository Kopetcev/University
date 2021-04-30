INSERT INTO groups (group_name)
VALUES ('group101'),
       ('group102'),
       ('group103');

INSERT INTO courses (course_name)
VALUES ('math'),
       ('java'),
       ('literature');

INSERT INTO public.users(login, password, email, first_name, last_name)
VALUES ('q', 'password0', 'q3@mail', 'Giovanna', 'Garcia'),
       ('w', 'password1', 'w3@mail', 'Francine', 'Parker'),
       ('e', 'password2', 'e3@mail', 'Kelly', 'Hall');

INSERT INTO public.teachers(teacher_user_id)
VALUES (1),
       (2),
       (3);

INSERT INTO lesson_times (lesson_start, lesson_end)
VALUES ('8:00', '9:00'),
       ('9:00', '10:00'),
       ('10:00', '11:00');

INSERT INTO lesson_rooms (lesson_room_name)
VALUES ('room101'),
       ('room102'),
       ('room103');

INSERT INTO lessons (course_id, group_id, teacher_id, date, lesson_time_id, lesson_room_id)
VALUES (1, 1, 1, '9/01/2021', 1, 1),
       (2, 2, 2, '9/02/2021', 2, 2),
       (3, 3, 3, '9/03/2021', 3, 3),
       (1, 2, 3, '10/01/2021', 1, 2),
       (1, 1, 1, '11/01/2021', 1, 1),
       (3, 2, 1, '12/14/2021', 3, 3);

