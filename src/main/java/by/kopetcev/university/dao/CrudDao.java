package by.kopetcev.university.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, K> {

    Optional<T> findById(K id);

    T save(T entity);

    List<T> findAll();

    boolean deleteById(K id);
}
