package by.kopetcev.university.model;

import java.util.Objects;

public class LessonView {

    private Long id;
    private Course course;
    private Group group;
    private Teacher teacher;
    private String dayOfWeek;
    private LessonTime lessonTime;
    private LessonRoom lessonRoom;

    public LessonView(Long id, Course course, Group group, Teacher teacher, String dayOfWeek, LessonTime lessonTime, LessonRoom lessonRoom) {
        this.id = id;
        this.course = course;
        this.group = group;
        this.teacher = teacher;
        this.dayOfWeek = dayOfWeek;
        this.lessonTime = lessonTime;
        this.lessonRoom = lessonRoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LessonTime getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LessonTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public LessonRoom getLessonRoom() {
        return lessonRoom;
    }

    public void setLessonRoom(LessonRoom lessonRoom) {
        this.lessonRoom = lessonRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonView that = (LessonView) o;
        return Objects.equals(id, that.id) && course.equals(that.course) && group.equals(that.group) && teacher.equals(that.teacher) && dayOfWeek.equals(that.dayOfWeek) && lessonTime.equals(that.lessonTime) && lessonRoom.equals(that.lessonRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, group, teacher, dayOfWeek, lessonTime, lessonRoom);
    }

    @Override
    public String toString() {
        return "LessonView{" +
                "id=" + id +
                ", course=" + course +
                ", group=" + group +
                ", teacher=" + teacher +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", lessonTime=" + lessonTime +
                ", lessonRoom=" + lessonRoom +
                '}';
    }
}
