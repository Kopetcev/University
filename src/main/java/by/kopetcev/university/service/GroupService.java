package by.kopetcev.university.service;

import by.kopetcev.university.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Group add(Group group);

    boolean deleteGroup(Long groupID);

    List<Group> findAll();

    Optional<Group> findById(Long groupID);
}
