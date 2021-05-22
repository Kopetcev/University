DELETE
FROM lessons;
DELETE
FROM students;
DELETE
FROM teacher_courses;
DELETE
FROM teachers;
DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM roles;
DELETE
FROM users;
DELETE
FROM lesson_times;
DELETE
FROM lesson_rooms;
DELETE
FROM courses;
DELETE
FROM groups;

ALTER SEQUENCE courses_course_id_seq RESTART WITH 1;
ALTER SEQUENCE groups_group_id_seq RESTART WITH 1;
ALTER SEQUENCE lesson_rooms_lesson_room_id_seq RESTART WITH 1;
ALTER SEQUENCE lesson_times_lesson_time_id_seq RESTART WITH 1;
ALTER SEQUENCE lessons_lesson_id_seq RESTART WITH 1;
ALTER SEQUENCE roles_role_id_seq RESTART WITH 1;
ALTER SEQUENCE users_user_id_seq RESTART WITH 1;
