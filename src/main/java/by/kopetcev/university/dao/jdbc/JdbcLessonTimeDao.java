package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.LessonRoomDao;
import by.kopetcev.university.dao.LessonTimeDao;
import by.kopetcev.university.dao.jdbc.mappers.LessonRoomMapper;
import by.kopetcev.university.dao.jdbc.mappers.LessonTimeMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.LessonRoom;
import by.kopetcev.university.model.LessonTime;
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
public class JdbcLessonTimeDao extends AbstractCrudDao<LessonTime, Long> implements LessonTimeDao {

    private static final String TABLE_NAME = "lesson_times";

    private static final String LESSON_TIME_ID ="lesson_time_id";

    private static final String LESSON_TIME_START = "lesson_time_start";

    private static final String LESSON_TIME_END = "lesson_time_end";

    private static final String UPDATE = "UPDATE lesson_times SET lesson_room_name=? WHERE lesson_room_id =?";

    private static final String FIND_ALL = "SELECT lesson_time_id, lesson_time_start, lesson_time_end  FROM lesson_times";

    private static final String FIND_BY_ID = "SELECT lesson_time_id, lesson_time_start, lesson_time_end FROM lesson_times WHERE lesson_time_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM lesson_times WHERE lesson_room_id =?";

    private final LessonTimeMapper lessonTimeMapper;

    private final SimpleJdbcInsert insert;

    @Autowired
    protected JdbcLessonTimeDao(DataSource dataSource, LessonTimeMapper lessonTimeMapper) {
        super(dataSource);
        this.lessonTimeMapper = lessonTimeMapper;
        this.insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(LESSON_TIME_ID);
    }

    @Override
    protected LessonTime create(LessonTime entity) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(LESSON_TIME_START, entity.getStart());
            params.put(LESSON_TIME_END, entity.getEnd());
            Number id = insert.executeAndReturnKey(params);
            return new LessonTime(id.longValue(), entity.getStart(), entity.getEnd());
        } catch (DataAccessException e) {
            throw new DaoException("Unable to create a new lessonTime", e);
        }
    }

    @Override
    protected LessonTime update(LessonTime entity) {
        if (jdbcTemplate.update(UPDATE, entity.getStart(), entity.getEnd(), entity.getId()) == 1) {
            return new LessonTime(entity.getId(), entity.getStart(), entity.getEnd());
        } else {
            throw new DaoException("Unable to update lessonTime with id=" + entity.getId());
        }
    }

    @Override
    public Optional<LessonTime> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, lessonTimeMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<LessonTime> findAll() {
        return jdbcTemplate.query(FIND_ALL, lessonTimeMapper
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) == 1;
    }
}
