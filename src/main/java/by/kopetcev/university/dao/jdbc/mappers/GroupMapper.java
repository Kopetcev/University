package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Group;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class GroupMapper implements RowMapper<Group> {

    public static final String GROUP_ID = "group_id";
    public static final String GROUP_NAME = "group_name";

    @Override
    public Group mapRow(ResultSet resultSet, int i) {
        try {
            return new Group(resultSet.getLong(GROUP_ID), resultSet.getString(GROUP_NAME));

        } catch (Exception e) {
            throw new RuntimeException("Unable to map row", e);
        }
    }
}
