package by.kopetcev.university.dao.jdbc.mappers;

import by.kopetcev.university.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoleMapper implements RowMapper<Role> {

    public static final String ROLE_ID = "role_id";

    public static final String ROLE_NAME = "role_name";

    private static final Logger logger = LoggerFactory.getLogger(
            RoleMapper.class);

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        try {
            return new Role(resultSet.getLong(ROLE_ID), resultSet.getString(ROLE_NAME));

        } catch (Exception e) {
            logger.warn("Unable to map role", e);

            throw new RuntimeException("Unable to map row", e);
        }
    }
}
