INSERT INTO roles (role_name)
VALUES ('manager'),
       ('intern'),
       ('admin'),
       ('find me'),
       ('delete_me'),
       ('update_me'),
       ('assign_me'),
       ('delete_me_from_teacher');

INSERT INTO public.users(login, password, email, first_name, last_name)
VALUES ('q', 'password0', 'q3@mail', 'Giovanna', 'Garcia'),
       ('w', 'password1', 'w3@mail', 'Francine', 'Parker'),
       ('e', 'password2', 'e3@mail', 'Kelly', 'Hall');

INSERT INTO public.user_roles(user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (3, 8);
