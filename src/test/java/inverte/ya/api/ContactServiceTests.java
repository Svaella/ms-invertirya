package inverte.ya.api;

import inverte.ya.api.model.Contact;
import inverte.ya.api.repo.IContactRepo;
import inverte.ya.api.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ContactServiceTests
{
    @MockBean
    private ContactServiceImpl service;

    @MockBean
    private IContactRepo repo;

    private Contact CONTACT_1;
    private Contact CONTACT_2;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);

        this.service = new ContactServiceImpl(repo);

        CONTACT_1 = new Contact(1, "Paolo Antunez", "123456@gmail.com", "...", LocalDateTime.now());
        CONTACT_2 = new Contact(2, "Jampier Ventura", "545@gmail.com", "...", LocalDateTime.now().plusDays(1L));

        List<Contact> contacts = List.of(CONTACT_1, CONTACT_2);

        Mockito.when(repo.findAll()).thenReturn(contacts);
        Mockito.when(repo.findById(1)).thenReturn(java.util.Optional.of(CONTACT_1));
        Mockito.when(repo.findById(2)).thenReturn(java.util.Optional.of(CONTACT_2));
        Mockito.when(repo.save(CONTACT_1)).thenReturn(CONTACT_1);
        Mockito.when(repo.save(CONTACT_2)).thenReturn(CONTACT_2);
    }

    @Test
    void readAllTest() throws Exception{
        List<Contact> response = service.readAll();
        assertEquals(response.size(), 2);
    }

    @Test
    void readByIdTest() throws Exception{
        Contact response = service.readById(2);
        assertNotNull(response);
    }

    @Test
    void save() throws Exception{
        Contact response = service.save(CONTACT_1);
        assertNotNull(response);
    }

    @Test
    void update() throws Exception{
        Contact response = service.update(CONTACT_1, 1);
        assertNotNull(response);
    }

    @Test
    void delete() throws Exception{
        repo.deleteById(1);

        verify(repo, times(1)).deleteById(1);
    }
}
