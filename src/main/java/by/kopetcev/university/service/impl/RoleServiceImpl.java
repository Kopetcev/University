package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.RoleDao;
import by.kopetcev.university.model.Role;
import by.kopetcev.university.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.save(role);
    }

    @Override
    public boolean deleteRole(Long roleId) {
        return roleDao.deleteById(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Optional<Role> findById(Long roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public boolean assignUser(Long roleId, Long userId) {
        return roleDao.assignUser(roleId, userId);
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleDao.findByUserId(userId);
    }

    @Override
    public boolean deleteByIdFromUser(Long roleId, Long userId) {
        return roleDao.deleteByIdFromUser(roleId, userId);
    }
}
