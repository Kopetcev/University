package by.kopetcev.university.service.impl;

import by.kopetcev.university.dao.GroupDao;
import by.kopetcev.university.model.Group;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {GroupServiceImpl.class})
class GroupServiceImplTest {

    @MockBean
    private GroupDao groupDaoMock;

    @Autowired
    private GroupServiceImpl service;

    @Test
    void shouldInvokeSave() {
        Group newGroup = new Group("group");
        Group expected = new Group( "group");
        when(groupDaoMock.save(newGroup)).thenReturn(expected);
        assertThat(service.add(newGroup), equalTo(expected));
        Mockito.verify(groupDaoMock, Mockito.times(1)).save(newGroup);
    }

    @Test
    void shouldInvokeFindById() {
        Optional<Group> expected = Optional.of(new Group(1L, "group"));
        when(groupDaoMock.findById(expected.get().getId())).thenReturn(expected);
        assertThat(service.findById(expected.get().getId()), equalTo(expected.get()));
        Mockito.verify(groupDaoMock, Mockito.times(1)).findById(expected.get().getId());
    }

    @Test
    void shouldInvokeDeleteById() {
        Long id = 1L;
        when(groupDaoMock.deleteById(id)).thenReturn(true);
        assertThat(service.deleteGroup(id), is(true));
        Mockito.verify(groupDaoMock, Mockito.times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeFindAll() {
        List<Group> expected = new ArrayList<>();
        expected.add(new Group("101421"));
        expected.add(new Group("101422"));
        when(groupDaoMock.findAll()).thenReturn(expected);
        assertThat(service.findAll(), equalTo(expected));
        Mockito.verify(groupDaoMock, Mockito.times(1)).findAll();
    }
}
