package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.GroupDao;
import by.kopetcev.university.dao.jdbc.mappers.GroupMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.Group;
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
public class JdbcGroupDao extends AbstractCrudDao<Group, Long> implements GroupDao {

    private static final String TABLE_NAME = "groups";

    private static final String GROUP_ID ="group_id";

    private static final String GROUP_NAME = "group_name";

    private static final String UPDATE = "UPDATE groups SET group_name=? WHERE group_id =?";

    private static final String FIND_ALL = "SELECT group_id, group_name FROM courses";

    private static final String FIND_BY_ID = "SELECT group_id, group_name FROM courses WHERE group_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM groups WHERE group_id =?";

    private final GroupMapper groupMapper;

    private final SimpleJdbcInsert insert;

    @Autowired
    protected JdbcGroupDao(DataSource dataSource, GroupMapper groupMapper) {
        super(dataSource);
        this.groupMapper = groupMapper;
        this. insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(GROUP_ID);
    }

    @Override
    protected Group create(Group entity) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(GROUP_NAME, entity.getName());
            Number id = insert.executeAndReturnKey(params);
            return new Group(id.longValue(), entity.getName());
        } catch (DataAccessException e) {
            throw new DaoException("Unable to create a new group", e);
        }
    }

    @Override
    protected Group update(Group entity) {
        if (jdbcTemplate.update(UPDATE, entity.getName(), entity.getId()) == 1) {
            return new Group(entity.getId(), entity.getName());
        } else {
            throw new DaoException("Unable to update group with id=" + entity.getId());
        }
    }

    @Override
    public Optional<Group> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, groupMapper, id));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Group> findAll() {
        return jdbcTemplate.query(FIND_ALL, groupMapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) == 1;
    }
}

