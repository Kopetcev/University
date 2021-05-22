package by.kopetcev.university.model;

public interface Entity<T> {
    T getId();

    void setId(T t);
}
