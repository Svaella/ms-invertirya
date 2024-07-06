package inverte.ya.api.service.impl;

import inverte.ya.api.model.Contact;
import inverte.ya.api.repo.IContactRepo;
import inverte.ya.api.repo.IGenericRepo;
import inverte.ya.api.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl extends CRUDImpl<Contact, Integer> implements IContactService
{
    private final IContactRepo repo;

    @Override
    protected IGenericRepo<Contact, Integer> getRepo() {
        return repo;
    }

    @Override
    public Contact save(Contact contact) {
        contact.setCreatedAt(LocalDateTime.now());
        return super.save(contact);
    }
}

