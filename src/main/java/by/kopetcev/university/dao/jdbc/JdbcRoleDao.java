package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.RoleDao;
import by.kopetcev.university.dao.jdbc.mappers.RoleMapper;
import by.kopetcev.university.exception.DaoException;

import by.kopetcev.university.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcRoleDao extends AbstractCrudDao<Role, Long> implements RoleDao {

    private static final String TABLE_NAME = "roles";

    private static final String ROLE_ID ="role_id";

    private static final String ROLE_NAME = "role_name";

    private static final String UPDATE = "UPDATE roles SET role_name=? WHERE role_id =?";

    private static final String FIND_ALL = "SELECT role_id, role_name FROM roles";

    private static final String FIND_BY_ID = "SELECT role_id, role_name FROM roles WHERE role_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM roles WHERE role_id =?";

    private final RoleMapper roleMapper;

    private final SimpleJdbcInsert roleInsert;

    @Autowired
    protected JdbcRoleDao(DataSource dataSource, RoleMapper roleMapper) {
        super(dataSource);
        this.roleMapper = roleMapper;
        this.roleInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ROLE_ID);
    }

    @Override
    protected Role create(Role entity) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(ROLE_NAME, entity.getName());
            Number id = roleInsert.executeAndReturnKey(params);
            return new Role(id.longValue(), entity.getName());
        } catch (DataAccessException e) {
            throw new DaoException("Unable to create a new role", e);
        }
    }

    @Override
    protected Role update(Role entity) {
        if (jdbcTemplate.update(UPDATE, entity.getName(), entity.getId()) == 1) {
            return new Role(entity.getId(), entity.getName());
        } else {
            throw new DaoException("Unable to update role with id=" + entity.getId());
        }
    }

    @Override
    public Optional<Role> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, roleMapper, id));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query(FIND_ALL, roleMapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) == 1;
    }
}
