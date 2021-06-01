package by.kopetcev.university.data;

import by.kopetcev.university.model.*;

import by.kopetcev.university.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
            "Music", "Philosophy", "Physics", "Sociology", "Pharmacy", "Medicine", "Chinese",};
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

    private static final String[] GROUPS = {"10101", "10102", "10103", "10104", "10105", "10106", "10107"};

    private static final String[] ROLES = {"Stuff", "UserManager", "ScheduleManager", "Admin", "Rector", "Deccan", "Cleaner"};

    public static final String CHARACTERS_PASSWORD = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int NUMBER_OF_USERS = 200;

    private static final int NUMBER_OF_LESSON_PER_DAY = 7;

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

    private static final Logger logger = LoggerFactory.getLogger(
            TestData.class);

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

        addCourses();
        addGroups();
        addLessonRooms();
        addRoles();
        addLessonTimes();
        addUsers();
        assignTeacherAndCourses();
        assignRole();
        assignStudent();
        addLesson();

    }

    private void addCourses() {
        Course newCourse;
        for (String courseName : COURSE_NAMES) {
            newCourse = new Course(courseName);
            try {
                courseService.add(newCourse);
                logger.debug("add course {}", newCourse);
            } catch (Exception e) {
                logger.debug("Unable to add {}", newCourse, e);
            }
        }
    }

    private void addGroups() {
        Group newGroup;
        for (String group : GROUPS) {
            newGroup = new Group(group);
            try {
                groupService.add(newGroup);
                logger.debug("Add group {}", newGroup);
            } catch (Exception e) {
                logger.debug("Unable to add {} ", newGroup, e);
            }
        }
    }

    private void addLessonTimes() {
        LessonTime newLessonTime;
        for (int i = 0; i < TIMES.length; i += 2) {
            newLessonTime = new LessonTime(TIMES[i], TIMES[i + 1]);
            try {
                lessonTimeService.add(newLessonTime);
                logger.debug("Add lesson time{}", newLessonTime);
            } catch (Exception e) {
                logger.debug("Unable to add {}", newLessonTime, e);
            }
        }
    }

    private void addLessonRooms() {
        LessonRoom newLessonRoom;
        for (String room : ROOMS) {
            newLessonRoom = new LessonRoom(room);
            try {
                lessonRoomService.add(newLessonRoom);
                logger.debug("Add lesson room {}", newLessonRoom);
            } catch (Exception e) {
                logger.debug("Unable to add {}", newLessonRoom, e);
            }
        }
    }

    private void addRoles() {
        Role newRole;
        for (String role : ROLES) {
            newRole = new Role(role);
            try {
                roleService.add(newRole);
                logger.debug("Add role {}", newRole);
            } catch (Exception e) {
                logger.debug("Unable to add {}", newRole, e);
            }
        }
    }

    private void assignTeacherAndCourses() {
        List<User> users = userService.findAll();
        List<Course> courses = courseService.findAll();
        for (int i = 0; i < courses.size(); i++) {

            try {
                userService.assignTeacher(users.get(i).getId());
                logger.debug("Assign teacher with id = {}", +users.get(i).getId());
                courseService.assignTeacher(courses.get(i).getId(), users.get(i).getId());
                logger.debug("Assign {} to Teacher with {}", users.get(i), courses.get(i));
            } catch (Exception e) {
                logger.debug("Unable to assign {} to Teacher with {}", users.get(i), courses.get(i), e);
            }
        }
    }

    private void assignRole() {
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        for (int i = 0; i < roles.size(); i++) {
            try {
                roleService.assignUser(roles.get(i).getId(), users.get(i + COURSE_NAMES.length).getId());
                logger.debug("Assign {} to {} ", roles.get(i), users.get(i));
            } catch (Exception e) {
                logger.debug("Unable to assign {} to {} ", roles.get(i), users.get(i), e);
            }
        }
    }

    private void addUsers() {
        Set<String> seen = new HashSet<>();
        User newUser;
        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            String firstName = random(NAMES);
            String lastName = random(LASTNAMES);
            newUser = new User(
                    unique(seen, lastName + firstName.charAt(0) + "%s"),
                    generatePassword(),
                    unique(seen, firstName + lastName.substring(0, 2) + "%s@mail.com"),
                    firstName, lastName);
            try {
                userService.add(newUser);
                logger.debug("Add user {}", newUser);
            } catch (Exception e) {
                logger.debug("Unable to add {}", newUser, e);
            }
        }
    }

    private void assignStudent() {
        List<User> users = userService.findAll();
        List<Group> groups = groupService.findAll();
        User user;
        Group group;
        for (int i = 0; i < groups.size() * 20; i++) {
            user = users.get(i + COURSE_NAMES.length + ROLES.length);
            group = groups.get(random.nextInt(groups.size() - 1));
            try {
                userService.assignStudent(user.getId(), group.getId());
                logger.debug("Assign {} to Student with {}", user, group);
            } catch (Exception e) {
                logger.debug("Unable to assign {} to Student with {}", user, group);
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
        int indexRoom = 0;
        int indexTeacher = 0;
        for (int i = 1; i <= NUMBER_DAY_OF_WEEK; i++) {
            for (int j = 0; j < NUMBER_OF_LESSON_TIMES; j++) {
                for (int k = 0; k < NUMBER_OF_LESSON_PER_DAY; k++) {
                    if (indexTeacher >= teachers.size()) {
                        indexTeacher = 0;
                    }
                    if (indexRoom >= lessonRooms.size()) {
                        indexRoom = 0;
                    }
                    newLesson = new Lesson(courses.get(random.nextInt(courses.size())).getId(),
                            groups.get(k).getId(),
                            teachers.get(indexTeacher).getId(),
                            Long.valueOf(i),
                            lessonTimes.get(j).getId(),
                            lessonRooms.get(indexRoom).getId());
                    indexRoom++;
                    indexTeacher++;
                    try {
                        lessonService.add(newLesson);
                        logger.debug("add lesson {}", newLesson);
                    } catch (Exception e) {
                        k--;
                        logger.debug("Unable to add {} ", newLesson, e);
                    }
                }
            }
        }
    }

    private String generatePassword() {

        char[] password = new char[random.nextInt(5) + 8];
        for (int i = 0; i < password.length; i++) {
            password[i] = CHARACTERS_PASSWORD.charAt(random.nextInt(CHARACTERS_PASSWORD.length() - 1));
        }
        return String.valueOf(password);
    }

    private <T> T random(T[] values) {
        return values[random.nextInt(values.length)];
    }

    private String unique(Set<String> seen, String template) {
        String current = String.format(template, "");
        int num = 0;
        while (seen.contains(current)) {
            num++;
            current = String.format(template, String.valueOf(num));
        }
        seen.add(current);
        return current;
    }


}
