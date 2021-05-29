package by.kopetcev.university.model;

import java.util.Objects;

public class LessonRoom implements Entity<Long> {
    private Long id;
    private String name;

    public LessonRoom(String name) {
        this.name = name;
    }

    public LessonRoom(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var lessonRoom = (LessonRoom) o;
        return id.equals(lessonRoom.id) &&
                Objects.equals(name, lessonRoom.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "LessonRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
