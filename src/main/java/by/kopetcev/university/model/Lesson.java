package by.kopetcev.university.model;

import java.time.LocalDate;
import java.util.Objects;

public class Lesson implements Entity<Long> {
    private Long id;
    private Long courseId;
    private Long groupId;
    private Long teacherId;
    private LocalDate date;
    private Long lessonTimeId;
    private Long lessonRoomId;

    public Lesson(Long courseId, Long groupId, Long teacherId, LocalDate date, Long lessonTimeId, Long lessonRoomId) {
        this.courseId = courseId;
        this.groupId = groupId;
        this.teacherId = teacherId;
        this.date = date;
        this.lessonTimeId = lessonTimeId;
        this.lessonRoomId = lessonRoomId;
    }

    public Lesson(Long id, Long courseId, Long groupId, Long teacherId, LocalDate date, Long lessonTimeId, Long lessonRoomId) {
        this.id = id;
        this.courseId = courseId;
        this.groupId = groupId;
        this.teacherId = teacherId;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        if (null == id) {
            return (id == lesson.id);
        } else {
            if (!id.equals(lesson.id)) {
                return false;
            }
        }
        if (null == courseId) {
            return (courseId == lesson.courseId);
        } else {
            if (!courseId.equals(lesson.courseId)) {
                return false;
            }
        }
        if (null == groupId) {
            return (groupId == lesson.groupId);
        } else {
            if (!groupId.equals(lesson.groupId)) {
                return false;
            }
        }
        if (null == teacherId) {
            return (teacherId == lesson.teacherId);
        } else {
            if (!teacherId.equals(lesson.teacherId)) {
                return false;
            }
        }
        if (null == date) {
            return (date == lesson.date);
        } else {
            if (!date.equals(lesson.date)) {
                return false;
            }
        }
        if (null == lessonTimeId) {
            return (lessonTimeId == lesson.lessonTimeId);
        } else {
            if (!lessonTimeId.equals(lesson.lessonTimeId)) {
                return false;
            }
        }
        if (null == lessonRoomId) {
            return (lessonRoomId == lesson.lessonRoomId);
        } else {
            if (!lessonRoomId.equals(lesson.lessonRoomId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, groupId, teacherId, date, lessonTimeId, lessonRoomId);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", groupId=" + groupId +
                ", teacherId=" + teacherId +
                ", date=" + date +
                ", lessonTimeId=" + lessonTimeId +
                ", lessonRoomId=" + lessonRoomId +
                '}';
    }
}
