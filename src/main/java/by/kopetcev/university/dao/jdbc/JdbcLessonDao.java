package by.kopetcev.university.dao.jdbc;

import by.kopetcev.university.dao.LessonDao;
import by.kopetcev.university.dao.jdbc.mappers.LessonMapper;
import by.kopetcev.university.exception.DaoException;
import by.kopetcev.university.model.Lesson;
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
public class JdbcLessonDao extends AbstractCrudDao<Lesson, Long> implements LessonDao {

    private static final String TABLE_NAME = "lessons";

    private static final String LESSON_ID = "lesson_id";

    private static final String COURSE_ID = "course_id";

    private static final String GROUP_ID = "group_id";

    private static final String TEACHER_ID = "teacher_id";

    private static final String DATE = "date";

    private static final String LESSON_TIME_ID = "lesson_time_id";

    private static final String LESSON_ROOM_ID = "lesson_room_id";

    private static final String UPDATE = "UPDATE lessons SET course_id = ?, group_id = ?, teacher_id =?, date = ?, lesson_time_id = ?, lesson_room_id = ?    WHERE lesson_id =?";

    private static final String FIND_ALL = "SELECT * FROM lessons";

    private static final String FIND_BY_ID = "SELECT * FROM lessons WHERE lesson_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM lessons WHERE lesson_id =?";

    private final LessonMapper lessonMapper;

    private final SimpleJdbcInsert lessonInsert;

    @Autowired
    protected JdbcLessonDao(DataSource dataSource, LessonMapper lessonMapper) {
        super(dataSource);
        this.lessonMapper = lessonMapper;
        this.lessonInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingGeneratedKeyColumns(LESSON_ID);
    }

    @Override
    protected Lesson create(Lesson entity) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(COURSE_ID, entity.getCourseId());
            params.put(GROUP_ID, entity.getGroupId());
            params.put(TEACHER_ID, entity.getTeacherId());
            params.put(DATE, entity.getDate());
            params.put(LESSON_TIME_ID, entity.getLessonTimeId());
            params.put(LESSON_ROOM_ID, entity.getLessonRoomId());
            Number id = lessonInsert.executeAndReturnKey(params);
            return new Lesson(id.longValue(), entity.getCourseId(), entity.getGroupId(), entity.getTeacherId(), entity.getDate(), entity.getLessonTimeId(), entity.getLessonRoomId());
        } catch (DataAccessException e) {
            throw new DaoException("Unable to create a new lesson", e);
        }
    }

    @Override
    protected Lesson update(Lesson entity) {
        if (jdbcTemplate.update(UPDATE, entity.getCourseId(), entity.getGroupId(), entity.getTeacherId(), entity.getDate(), entity.getLessonTimeId(), entity.getLessonRoomId(), entity.getId()) == 1) {
            return new Lesson(entity.getId(), entity.getCourseId(), entity.getGroupId(), entity.getTeacherId(), entity.getDate(), entity.getLessonTimeId(), entity.getLessonRoomId());
        } else {
            throw new DaoException("Unable to update lesson with id=" + entity.getId());
        }
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, lessonMapper, id));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Lesson> findAll() {
        return jdbcTemplate.query(FIND_ALL, lessonMapper);
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) == 1;
    }
}
