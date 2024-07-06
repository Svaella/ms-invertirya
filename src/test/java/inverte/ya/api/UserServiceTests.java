package inverte.ya.api;

import inverte.ya.api.model.User;
import inverte.ya.api.repo.IUserRepo;
import inverte.ya.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserServiceTests
{
    @MockBean
    private UserServiceImpl service;

    @MockBean
    private IUserRepo repo;

    private User USER_1;
    private User USER_2;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);

        this.service = new UserServiceImpl(repo);

        USER_1 = new User(1, "paoloantunez", "123456", true, new ArrayList<>());
        USER_2 = new User(2, "joseant", "123456", false, new ArrayList<>());

        List<User> users = List.of(USER_1, USER_2);

        Mockito.when(repo.findAll()).thenReturn(users);
        Mockito.when(repo.findById(1)).thenReturn(java.util.Optional.of(USER_1));
        Mockito.when(repo.findById(2)).thenReturn(java.util.Optional.of(USER_2));
        Mockito.when(repo.save(USER_1)).thenReturn(USER_1);
        Mockito.when(repo.save(USER_2)).thenReturn(USER_2);
    }

    @Test
    void readAllTest() throws Exception{
        List<User> response = service.readAll();
        assertEquals(response.size(), 2);
    }

    @Test
    void readByIdTest() throws Exception{
        User response = service.readById(2);
        assertNotNull(response);
    }

    @Test
    void save() throws Exception{
        User response = service.save(USER_2);
        assertNotNull(response);
    }

    @Test
    void update() throws Exception{
        User response = service.update(USER_1, 1);
        assertNotNull(response);
    }

    @Test
    void delete() throws Exception{
        repo.deleteById(1);
        repo.deleteById(1);

        verify(repo, times(2)).deleteById(1);
    }
}

