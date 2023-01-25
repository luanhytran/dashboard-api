package com.luanhydev.dashboard.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.getContacts();
    }

    @GetMapping(path = "{contactId}")
    public Contact getContact(@PathVariable("contactId") Long contactId) {
        return contactService.getContact(contactId);
    }

    @PostMapping
    public void addNewContact(@RequestBody Contact contact) {
        contactService.addNewContact(contact);
    }

    @PutMapping(path = "{contactId}")
    public void updateContact(@PathVariable("contactId") Long contactId, @RequestBody Contact contact) {
        contactService.updateContact(contactId, contact);
    }

    @DeleteMapping(path = "{contactId}")
    public void deleteContact(@PathVariable("contactId") Long contactId) {
        contactService.deleteContact(contactId);
    }
}
