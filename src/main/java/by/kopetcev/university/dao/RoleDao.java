package by.kopetcev.university.dao;

import by.kopetcev.university.model.Role;
import by.kopetcev.university.model.User;

import java.util.List;

public interface RoleDao extends CrudDao<Role, Long> {

    boolean assignUser(Long roleId, Long userId);

    List<Role> findByUserId(Long userId);

    boolean deleteByIdFromUser(Long roleId, Long userId);
}
