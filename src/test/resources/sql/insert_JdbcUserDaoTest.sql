INSERT INTO public.groups(
    group_name)
VALUES (101),
       (102);

INSERT INTO public.users(login, password, email, first_name, last_name)
VALUES ('q', 'password0', 'q3@mail', 'Giovanna', 'Garcia'),
       ('w', 'password1', 'w3@mail', 'Francine', 'Parker'),
       ('e', 'password2', 'e3@mail', 'Kelly', 'Hall'),
       ('find_me', 'password3', 'r3@mail', 'Dulce', 'Barnes'),
       ('delete_me', 'password4', 't@mail', 'Lola', 'Rodriguez'),
       ('update_me', 'password5', 'y@mail', 'Valencia', 'Simmons'),
       ('student', 'password6', 'student@mail', 'Alex', 'Taylor'),
       ('teacher', 'password7', 'teach@mail', 'Aaron', 'Sanders'),
       ('user', 'password8', 'user@mail', 'Nick', 'Bennett');

INSERT INTO public.students(student_user_id, group_id)
VALUES (7, 1);

INSERT INTO public.teachers(
    teacher_user_id)
VALUES (8);

