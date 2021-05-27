package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.GroupDao;
import by.kopetcev.university.exception.ServiceException;
import by.kopetcev.university.model.Group;
import by.kopetcev.university.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    private static final Logger logger = LoggerFactory.getLogger(
            GroupServiceImpl.class);

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
    public Group findById(Long groupId) {
        Optional<Group> optionalGroup = groupDao.findById(groupId);
        if (optionalGroup.isPresent()) {
            return optionalGroup.get();
        } else
            logger.warn("Group with id = {} not found", groupId);
        throw new ServiceException("Group with id = " + groupId + " not found");
    }
}
