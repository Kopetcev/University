package by.kopetcev.university.service;

import by.kopetcev.university.model.Group;

import java.util.List;

public interface GroupService {

    Group add(Group group);

    boolean deleteGroup(Long groupID);

    List<Group> findAll();

    Group findById(Long groupID);
}
