package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.GroupDao;
import by.kopetcev.university.model.Group;
import by.kopetcev.university.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group add(Group group) {

        return groupDao.save(group);
    }

    @Override
    public boolean deleteGroup(Long groupId) {
        return groupDao.deleteById(groupId);
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public Optional<Group> findById(Long groupId) {
        return groupDao.findById(groupId);
    }
}
