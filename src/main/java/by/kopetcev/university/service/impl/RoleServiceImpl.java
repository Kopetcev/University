package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.RoleDao;
import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.Role;
import by.kopetcev.university.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    private static final Logger logger = LoggerFactory.getLogger(
            RoleServiceImpl.class);

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
    public Role findById(Long roleId) {
        Optional<Role> optionalRole = roleDao.findById(roleId);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        } else
            logger.warn("Role with id = {} not found", roleId);
        throw new ServiceException("Role with id = " + roleId + " not found");
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
