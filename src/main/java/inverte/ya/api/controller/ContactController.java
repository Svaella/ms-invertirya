package inverte.ya.api.controller;

import inverte.ya.api.dto.ContactDTO;
import inverte.ya.api.model.Contact;
import inverte.ya.api.service.IContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController
{
    private final IContactService service;
    private final ModelMapper mapper;

    @PostMapping()
    public ResponseEntity<ContactDTO> save(@RequestBody @Valid ContactDTO contact)
    {
        ContactDTO contactDTO = this.convertToDTO(service.save(this.convertToEntity(contact)));
        return new ResponseEntity<>(contactDTO, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ContactDTO> update(@RequestBody @Valid ContactDTO contact)
    {
        ContactDTO contactDTO = this.convertToDTO(service.update(this.convertToEntity(contact), contact.getId()));
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> readById(@PathVariable Integer id)
    {
        ContactDTO contactDTO = this.convertToDTO(service.readById(id));
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ContactDTO>> findAll()
    {
        List<ContactDTO> contacts = service.readAll().stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id)
    {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ContactDTO convertToDTO(Contact contact)
    {
        return mapper.map(contact, ContactDTO.class);
    }

    private Contact convertToEntity(ContactDTO contactDTO)
    {
        return mapper.map(contactDTO, Contact.class);
    }
}
