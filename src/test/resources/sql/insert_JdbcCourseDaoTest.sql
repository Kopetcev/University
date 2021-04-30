INSERT INTO courses (course_name)
VALUES ('math'),
       ('java'),
       ('literature'),
       ('find me'),
       ('delete_me'),
       ('update_me'),
       ('assign_me'),
       ('delete_me_from_teacher');

INSERT INTO public.users(login, password, email, first_name, last_name)
VALUES ('q', 'password0', 'q3@mail', 'Giovanna', 'Garcia'),
       ('w', 'password1', 'w3@mail', 'Francine', 'Parker');

INSERT INTO public.teachers(teacher_user_id)
VALUES (1),
       (2);


INSERT INTO public.teacher_courses(teacher_id, course_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2,8);

