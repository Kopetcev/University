package by.kopetcev.university.service.impl;

import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.*;
import by.kopetcev.university.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static java.util.Map.entry;

@Component
@Transactional
public class LessonViewServiceImpl implements LessonViewService {

    private final CourseService courseService;

    private final GroupService groupService;

    private final UserService userService;

    private final LessonTimeService lessonTimeService;

    private final LessonRoomService lessonRoomService;

    private static final Logger logger = LoggerFactory.getLogger(
            LessonViewServiceImpl.class);

    private static final Map<Long, String> DAY_OF_WEEK;


    @Autowired
    public LessonViewServiceImpl(CourseService courseService, GroupService groupService, UserService userService, LessonTimeService lessonTimeService, LessonRoomService lessonRoomService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.userService = userService;
        this.lessonTimeService = lessonTimeService;
        this.lessonRoomService = lessonRoomService;

        }
    static {
        DAY_OF_WEEK = Map.ofEntries(
                entry(1L, "Monday"),
                entry(2L, "Tuesday"),
                entry(3L, "Wednesday"),
                entry(4L, "Thursday"),
                entry(5L, "Friday"),
                entry(6L, "Saturday"),
                entry(7L, "Sunday")
        );
    }

    @Override
    public LessonView map(Lesson lesson) {

        Course course = courseService.findById(lesson.getCourseId());

        Group group = groupService.findById(lesson.getGroupId());

        User user = userService.findById(lesson.getTeacherId());

        Teacher teacher;
        if(user.getClass() == Teacher.class){
           teacher = (Teacher) user;
        }else{
            logger.warn("Can not map LessonView. Teacher with id = {} not found", lesson.getTeacherId());
            throw new ServiceException("Can not map LessonView");
        }

        String dayOfWeek = DAY_OF_WEEK.get(lesson.getDayOfWeek());

        LessonTime lessonTime = lessonTimeService.findById(lesson.getLessonTimeId());

        LessonRoom lessonRoom = lessonRoomService.findById(lesson.getLessonRoomId());

        return new LessonView(lesson.getId(),course,group,teacher, dayOfWeek, lessonTime, lessonRoom);
    }
}
