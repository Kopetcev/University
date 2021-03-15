package by.kopetcev.university.model;

import java.util.Objects;

public class LessonRoom implements Entity<Long> {
    private Long id;
    private String name;
    private String address;

    public LessonRoom(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public LessonRoom(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonRoom lessonRoom = (LessonRoom) o;
        return id == lessonRoom.id &&
                Objects.equals(name, lessonRoom.name) &&
                Objects.equals(address, lessonRoom.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
