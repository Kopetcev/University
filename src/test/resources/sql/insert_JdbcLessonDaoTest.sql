INSERT INTO groups (group_name)
VALUES ('group101'),
       ('group102'),
       ('group103'),
       ('group104'),
       ('group105'),
       ('group106');


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
       ('10:00', '11:00'),
       ('11:00', '12:00'),
       ('12:00', '13:00'),
       ('13:00', '14:00');

INSERT INTO lesson_rooms (lesson_room_name)
VALUES ('room101'),
       ('room102'),
       ('room103'),
       ('room104'),
       ('room104');

INSERT INTO lessons (course_id, group_id, teacher_id, day_of_week_id, lesson_time_id, lesson_room_id)
VALUES (1, 1, 1, 7, 5, 3),
       (2, 6, 2, 1, 2, 4),
       (3, 3, 3, 4, 5, 3),
       (1, 5, 3, 3, 3, 2),
       (1, 1, 1, 5, 1, 5),
       (3, 2, 1, 6, 3, 3);
