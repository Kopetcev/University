package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class GroupMapper implements RowMapper<Group> {

    public static final String GROUP_ID = "group_id";
    public static final String GROUP_NAME = "group_name";
    private static final Logger logger = LoggerFactory.getLogger(
            GroupMapper.class);

    @Override
    public Group mapRow(ResultSet resultSet, int i) {
        try {
            return new Group(resultSet.getLong(GROUP_ID), resultSet.getString(GROUP_NAME));

        } catch (Exception e) {
            logger.warn("Unable to map group", e);
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
