package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.RoleDao;
import by.kopetcev.university.dao.jdbc.mappers.RoleMapper;
import by.kopetcev.university.exception.DaoException;

import by.kopetcev.university.model.Role;
import by.kopetcev.university.model.User;
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

    private static final String TABLE_NAME_ROLES = "roles";

    private static final String TABLE_NAME_USER_ROLES = "user_roles";

    private static final String ROLE_ID ="role_id";

    private static final String USER_ID ="user_id";

    private static final String ROLE_NAME = "role_name";

    private static final String UPDATE = "UPDATE roles SET role_name=? WHERE role_id =?";

    private static final String FIND_ALL = "SELECT role_id, role_name FROM roles";

    private static final String FIND_BY_ID = "SELECT role_id, role_name FROM roles WHERE role_id = ?";

    private static final String FIND_BY_USER_ID = "SELECT roles.role_id, role_name FROM roles,user_roles where roles.role_id = user_roles.role_id AND user_roles.user_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM roles WHERE role_id =?";

    private static final String DELETE_BY_ID_FROM_USER = "DELETE FROM user_roles WHERE role_id =? AND user_id = ?";

    private final RoleMapper roleMapper;

    private final SimpleJdbcInsert roleInsert;

    private final SimpleJdbcInsert userRoleInsert;

    @Autowired
    protected JdbcRoleDao(DataSource dataSource, RoleMapper roleMapper) {
        super(dataSource);
        this.roleMapper = roleMapper;
        this.roleInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME_ROLES).usingGeneratedKeyColumns(ROLE_ID);
        this.userRoleInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME_USER_ROLES);
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

    @Override
    public boolean assignUser(Role role, User user) {
        Map<String, Object> params = new HashMap<>();
        params.put(ROLE_ID, role.getId());
        params.put(USER_ID, user.getId());
        if (userRoleInsert.execute(params) == 1) {
            return true;
        } else {
            throw new DaoException("Unable to assign role with id = " + role.getId() + " to user with id = " + user.getId());

        }
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return jdbcTemplate.query(FIND_BY_USER_ID, roleMapper, userId);
    }

    @Override
    public boolean deleteByIdFromUser(Long roleId, Long userId) {
        return jdbcTemplate.update(DELETE_BY_ID_FROM_USER, roleId, userId ) == 1;
    }
}
