package by.kopetcev.university.data;

import by.kopetcev.university.model.*;

import by.kopetcev.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TestData {
    private static final String[] NAMES = {"Giovanna", "Dina", "Francine", "Dulce", "Jenna", "Zarina", "Tamara",
            "Scarlette", "Valencia", "Lola", "Gibson", "Uria", "Nick", "Josiah", "Vermont", "Nathanael", "Aaron",
            "Omer", "Leonard", "Yoshiaki"};
    private static final String[] LASTNAMES = {"Taylor", "Gonzales", "Sanders", "Green", "Simmons", "Bennett",
            "Johnson", "Kelly", "Washington", "Garcia", "Hall", "Thomas", "Parker", "Barnes", "Rodriguez", "Ramirez",
            "Brooks", "Gonzalez", "Anderson", "Brown"};
    private static final String[] COURSE_NAMES = {"Biology", "Architecture", "Economics", "Geography", "Mathematics",
            "Music", "Philosophy", "Physics", "Sociology", "Pharmacy"};
    private static final LocalTime[] TIMES = {LocalTime.of(8, 0), LocalTime.of(8, 45),
            LocalTime.of(8, 55), LocalTime.of(9, 40),
            LocalTime.of(9, 45), LocalTime.of(10, 30),
            LocalTime.of(10, 35), LocalTime.of(11, 20),
            LocalTime.of(11, 25), LocalTime.of(12, 15),
            LocalTime.of(12, 20), LocalTime.of(13, 5),
            LocalTime.of(13, 10), LocalTime.of(13, 55)};
    private static final String[] ROOMS = {"101", "102", "103", "104", "105",
            "201", "202", "203", "204", "205",
            "301", "302", "303", "304", "305"};
    private static final String[] ROLES = {"Stuff", "UserManager", "ScheduleManager", "Admin", "Rector", "Deccan", "Cleaner"};

    public static final String CHARACTERS_PASSWORD = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int MIN_NUMBER_OF_COURSES = 8;

    private static final int MIN_NUMBER_OF_GROUPS = 8;

    private static final int MIN_NUMBER_OF_ROLES = 6;

    private static final int MIN_NUMBER_OF_LESSON_ROOMS = 10;

    private static final int MIN_NUMBER_OF_USERS = 150;

    private static final int NUMBER_OF_USERS = 200;

    private static final int MIN_NUMBER_OF_LESSONS = 150;

    private static final int NUMBER_OF_LESSON_PER_DAY = MIN_NUMBER_OF_LESSON_ROOMS - 1;

    private static final int NUMBER_OF_LESSON_TIMES = 6;

    private static final int NUMBER_DAY_OF_WEEK = 5;

    private final GroupService groupService;

    private final CourseService courseService;

    private final LessonRoomService lessonRoomService;

    private final LessonService lessonService;

    private final LessonTimeService lessonTimeService;

    private final RoleService roleService;

    private final UserService userService;

    private final Random random;

    @Autowired
    public TestData(CourseService courseService, GroupService groupService, LessonRoomService lessonRoomService, LessonService lessonService, LessonTimeService lessonTimeService, RoleService roleService, UserService userService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.lessonRoomService = lessonRoomService;
        this.lessonService = lessonService;
        this.lessonTimeService = lessonTimeService;
        this.roleService = roleService;
        this.userService = userService;
        this.random = new Random();
    }

    public void addData() {
        if (!isTableCoursesFull()) {
            addCourseData();
        }
        if (!isTableGroupsFull()) {
            addGroups();
        }
        if (!isTableLessonRoomsFull()) {
            addLessonRooms();
        }
        if (!isTableRolesFull()) {
            addRoles();
        }
        if (!isTableLessonTimesFull()) {
            addLessonTimes();
        }
        if (!isTableUsersFull()) {
            addUsers(NUMBER_OF_USERS);
            assignTeacherAndCourses();
            assignRole();
            assignStudent();
        }
        if (!isTableLessonsFull()) {
            addLesson();
        }
    }

    private void addCourseData() {
        Course newCourse;
        for (int i = 0; i < COURSE_NAMES.length; i++) {
            newCourse = new Course(COURSE_NAMES[i]);
            try {
                courseService.add(newCourse);
            } catch (Exception e) {
                System.out.println("Unnable to add " + newCourse + " " + e);
            }
        }
    }

    private void addGroups() {
        Group newGroup;
        for (int i = 0; i < 10; i++) {
            newGroup = new Group(String.format("%s-%d", getTwoRandomLettersGroup(), getRandomNumberGroup()));
            try {
                groupService.add(newGroup);
            } catch (Exception e) {
                System.out.println("Unnable to add " + newGroup + " " + e);
            }
        }
    }

    private void addLessonTimes() {
        LessonTime newLessonTime;
        for (int i = 0; i < TIMES.length; i += 2) {
            newLessonTime = new LessonTime(TIMES[i], TIMES[i + 1]);
            try {
                lessonTimeService.add(newLessonTime);
            } catch (Exception e) {
                System.out.println("Unnable to add " + newLessonTime + " " + e);
            }
        }
    }

    private void addLessonRooms() {
        LessonRoom newLessonRoom;
        for (int i = 0; i < ROOMS.length; i++) {
            newLessonRoom = new LessonRoom(ROOMS[i]);
            try {
                lessonRoomService.add(newLessonRoom);
            } catch (Exception e) {
                System.out.println("Unnable to add " + newLessonRoom + " " + e);
            }
        }
    }

    private void addRoles() {
        Role newRole;
        for (int i = 0; i < ROLES.length; i++) {
            newRole = new Role(ROLES[i]);
            try {
                roleService.add(newRole);
            } catch (Exception e) {
                System.out.println("Unnable to add " + newRole + " " + e);
            }
        }
    }

    private void addUsers(int countOfStudents) {
        User newUser;
        for (int i = 0; i < countOfStudents; i++) {
            String firstName = NAMES[random.nextInt(NAMES.length - 1)];
            String lastName = LASTNAMES[random.nextInt(LASTNAMES.length - 1)];
            newUser = new User(lastName + firstName.charAt(0), getPassword(), firstName + lastName.substring(0, 2) + "@mail.com", firstName, lastName);
            try {
                userService.add(newUser);
            } catch (Exception e) {
                System.out.println("Unnable to add " + newUser + " " + e);
            }
        }
    }

    private void assignTeacherAndCourses() {
        List<User> users = userService.findAll();
        List<Course> courses = courseService.findAll();
        for (int i = 0; i < courses.size(); i++) {

            try {
                userService.assignTeacher(users.get(i).getId());
                courseService.assignTeacher(courses.get(i).getId(), users.get(i).getId());
            } catch (Exception e) {
                System.out.println("Unnable to assign " + users.get(i) + " to Teacher with " + courses.get(i) + " " + e);
            }
        }
    }

    private void assignRole() {
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        for (int i = 0; i < roles.size(); i++) {
            try {
                roleService.assignUser(roles.get(i).getId(), users.get(i + COURSE_NAMES.length).getId());
            } catch (Exception e) {
                System.out.println("Unnable to assign " + roles.get(i) + " to " + users.get(i) + " " + e);
            }
        }
    }

    private void assignStudent() {
        List<User> users = userService.findAll();
        List<Group> groups = groupService.findAll();
        for (int i = 0; i < groups.size(); i++) {
            try {
                userService.assignStudent(users.get(i + COURSE_NAMES.length + ROLES.length).getId(), groups.get(i).getId());
            } catch (Exception e) {
                System.out.println("Unnable to assign " + users.get(i) + " to Student with " + groups.get(i) + " " + e);
            }
        }
    }

    private void addLesson() {
        List<User> teachers = userService.findAllTeacher();
        List<Course> courses = courseService.findAll();
        List<Group> groups = groupService.findAll();
        List<LessonTime> lessonTimes = lessonTimeService.findAll();
        List<LessonRoom> lessonRooms = lessonRoomService.findAll();
        Lesson newLesson;

        for (int i = 1; i <= NUMBER_DAY_OF_WEEK; i++) {
            for (int j = 0; j < NUMBER_OF_LESSON_TIMES; j++) {
                for (int k = 0; k < NUMBER_OF_LESSON_PER_DAY; k++) {
                    newLesson = new Lesson(courses.get(random.nextInt(courses.size() - 1)).getId(),
                            groups.get(random.nextInt(groups.size() - 1)).getId(),
                            teachers.get(random.nextInt(teachers.size() - 1)).getId(),
                            LocalDate.of(2021, 9, i),
                            lessonTimes.get(j).getId(),
                            lessonRooms.get(random.nextInt(lessonRooms.size() - 1)).getId());
                    try {
                        lessonService.add(newLesson);
                    } catch (Exception e) {
                        System.out.println("Unnable to add " + newLesson + " " + e);
                    }
                }
            }


        }
    }


    private String getTwoRandomLettersGroup() {
        return random.ints(65, 90).mapToObj(i -> (char) i).limit(2)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    private int getRandomNumberGroup() {
        return random.nextInt(89) + 10;
    }

    private String getPassword() {

        char[] password = new char[random.nextInt(5) + 8];
        for (int i = 0; i < password.length; i++) {
            password[i] = CHARACTERS_PASSWORD.charAt(random.nextInt(CHARACTERS_PASSWORD.length() - 1));
        }
        return String.valueOf(password);
    }

    private boolean isTableCoursesFull() {
        return courseService.findAll().size() > MIN_NUMBER_OF_COURSES;
    }

    private boolean isTableGroupsFull() {
        return groupService.findAll().size() > MIN_NUMBER_OF_GROUPS;
    }

    private boolean isTableLessonRoomsFull() {
        return lessonRoomService.findAll().size() > MIN_NUMBER_OF_LESSON_ROOMS;
    }

    private boolean isTableRolesFull() {
        return roleService.findAll().size() > MIN_NUMBER_OF_ROLES;
    }

    private boolean isTableLessonTimesFull() {
        return lessonTimeService.findAll().size() >= TIMES.length;
    }

    private boolean isTableLessonsFull() {
        return lessonService.findAll().size() > MIN_NUMBER_OF_LESSONS;
    }

    private boolean isTableUsersFull() {
        return userService.findAll().size() > MIN_NUMBER_OF_USERS;
    }
}
