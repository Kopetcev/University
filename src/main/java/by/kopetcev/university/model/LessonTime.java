package by.kopetcev.university.model;

import java.time.LocalTime;
import java.util.Objects;

public class LessonTime implements Entity<Long> {
    private Long id;
    private LocalTime start;
    private LocalTime end;

    public LessonTime(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LessonTime(Long id, LocalTime start, LocalTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTime that = (LessonTime) o;
        if (null == id) {
            return (id == that.id);
        } else {
            if (!id.equals(that.id)) {
                return false;
            }
        }
        if (null == start) {
            return (start == that.start);
        } else {
            if (!start.equals(that.start)) {
                return false;
            }
        }
        if (null == id) {
            return (end == that.end);
        } else {
            if (!end.equals(that.end)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end);
    }

    @Override
    public String toString() {
        return "LessonTime{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
