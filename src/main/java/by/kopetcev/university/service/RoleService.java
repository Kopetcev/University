package by.kopetcev.university.service;

import by.kopetcev.university.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role add(Role role);

    boolean deleteRole(Long roleId);

    List<Role> findAll();

    Optional<Role> findById(Long roleId);

    boolean assignUser(Long roleId, Long userId);

    List<Role> findByUserId(Long userId);

    boolean deleteByIdFromUser(Long roleId, Long userId);
}
