package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.LessonRoomDao;
import by.kopetcev.university.dao.jdbc.mappers.LessonRoomMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.LessonRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcLessonRoomDao extends AbstractCrudDao<LessonRoom, Long> implements LessonRoomDao {

    private static final String TABLE_NAME = "lesson_rooms";

    private static final String LESSON_ROOM_ID ="lesson_room_id";

    private static final String LESSON_ROOM_NAME = "lesson_room_name";

    private static final String UPDATE = "UPDATE lesson_rooms SET lesson_room_name=? WHERE lesson_room_id =?";

    private static final String FIND_ALL = "SELECT lesson_room_id, lesson_room_name FROM courses";

    private static final String FIND_BY_ID = "SELECT lesson_room_id, lesson_room_name FROM courses WHERE lesson_room_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM lesson_rooms WHERE lesson_room_id =?";

    private final LessonRoomMapper lessonRoomMapper;

    private final SimpleJdbcInsert insert;

    @Autowired
    protected JdbcLessonRoomDao(DataSource dataSource, LessonRoomMapper lessonRoomMapper) {
        super(dataSource);
        this.lessonRoomMapper = lessonRoomMapper;
        this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(LESSON_ROOM_ID);
    }

    @Override
    protected LessonRoom create(LessonRoom entity) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(LESSON_ROOM_NAME, entity.getName());
            Number id = insert.executeAndReturnKey(params);
            return new LessonRoom(id.longValue(), entity.getName());
        } catch (DataAccessException e) {
            throw new DaoException("Unable to create a new course", e);
        }
    }

    @Override
    protected LessonRoom update(LessonRoom entity) {
        if (jdbcTemplate.update(UPDATE, entity.getName(), entity.getId()) == 1) {
            return new LessonRoom(entity.getId(), entity.getName());
        } else {
            throw new DaoException("Unable to update lessonRoom with id=" + entity.getId());
        }
    }

    @Override
    public Optional<LessonRoom> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, lessonRoomMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<LessonRoom> findAll() {
        return jdbcTemplate.query(FIND_ALL, lessonRoomMapper
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) == 1;
    }
}

