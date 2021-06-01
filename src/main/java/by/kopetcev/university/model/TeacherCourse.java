package by.kopetcev.university.model;

public class TeacherCourse {

    private Long teacherId;

    private Long courseId;

    public TeacherCourse() {
    }

    public TeacherCourse(Long teacherId, Long courseId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
