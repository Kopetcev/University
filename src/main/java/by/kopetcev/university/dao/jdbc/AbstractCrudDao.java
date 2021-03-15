package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.CrudDao;
import by.kopetcev.university.model.Entity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public abstract class AbstractCrudDao<T extends Entity, K> implements CrudDao<T, K> {

    protected final JdbcTemplate jdbcTemplate;

    protected AbstractCrudDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public T save(T entity) {
        return entity.getId() == null ? create(entity) : update(entity);
    }

    protected abstract T create(T entity);

    protected abstract T update(T entity);
}
