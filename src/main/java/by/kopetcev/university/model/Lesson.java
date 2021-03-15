package by.kopetcev.university.model;

import java.util.Objects;

public class Lesson implements Entity<Long> {
    private Long id;
    private Long courseId;
    private Long groupId;
    private Long teacherId;
    private Long lessonTimeId;
    private Long lessonRoomId;

    public Lesson(Long courseId, Long groupId, Long teacherId, Long lessonTimeId, Long lessonRoomId) {
        this.courseId = courseId;
        this.groupId = groupId;
        this.teacherId = teacherId;
        this.lessonTimeId = lessonTimeId;
        this.lessonRoomId = lessonRoomId;
    }

    public Lesson(Long id, Long courseId, Long groupId, Long teacherId, Long lessonTimeId, Long lessonRoomId) {
        this.id = id;
        this.courseId = courseId;
        this.groupId = groupId;
        this.teacherId = teacherId;
        this.lessonTimeId = lessonTimeId;
        this.lessonRoomId = lessonRoomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getLessonTimeId() {
        return lessonTimeId;
    }

    public void setLessonTimeId(Long lessonTimeId) {
        this.lessonTimeId = lessonTimeId;
    }

    public Long getLessonRoomId() {
        return lessonRoomId;
    }

    public void setLessonRoomId(Long lessonRoomId) {
        this.lessonRoomId = lessonRoomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                courseId == lesson.courseId &&
                groupId == lesson.groupId &&
                teacherId == lesson.teacherId &&
                lessonTimeId == lesson.lessonTimeId &&
                lessonRoomId == lesson.lessonRoomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, groupId, teacherId, lessonTimeId, lessonRoomId);
    }
}
