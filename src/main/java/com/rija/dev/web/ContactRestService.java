package com.rija.dev.web;

import com.rija.dev.dao.ContactRepository;
import com.rija.dev.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContactRestService {
    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
    public Optional<Contact> getContact(@PathVariable Long id) {
        return contactRepository.findById(id);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
    public Contact update(@PathVariable Long id, @RequestBody Contact contact) {
        contact.setId(id);
        return contactRepository.save(contact);
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }

    @RequestMapping(value = "/chercherContacts", method = RequestMethod.GET)
    public Page<Contact> chercher(
            @RequestParam(name = "motCle", defaultValue = "") String motCle,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return contactRepository.chercher("%" + motCle + "%", PageRequest.of(page, size));
    }
}
