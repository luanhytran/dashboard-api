package com.luanhydev.dashboard.contact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Contact getContact(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent()) {
            return contact.get();
        }
        throw new IllegalStateException("contact with id " + contactId + " does not exists");
    }

    public void addNewContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(Long contactId) {
        boolean exists = contactRepository.existsById(contactId);
        if(!exists) {
            throw new IllegalStateException("contact with id " + contactId + " does not exists");
        }
        contactRepository.deleteById(contactId);
    }

    @Transactional // Help don't have to write jpql and can use setter to update data
    public void updateContact(Long contactId, Contact contact) {
        Contact currentContact = contactRepository.findById(contactId).orElseThrow(() -> {
            throw new IllegalStateException("contact with id " + contactId + " does not exists");
        });

        if(contact.getFirstName() != null && contact.getFirstName().length() > 0) {
            currentContact.setFirstName(contact.getFirstName());
        }

        if(contact.getLastName() != null && contact.getLastName().length() > 0) {
            currentContact.setLastName(contact.getLastName());
        }

        if(contact.getTitle() != null && contact.getTitle().length() > 0) {
            currentContact.setTitle(contact.getTitle());
        }

        if(contact.getDepartment() != null && contact.getDepartment().length() > 0) {
            currentContact.setDepartment(contact.getDepartment());
        }

        if(contact.getProject() != null && contact.getProject().length() > 0) {
            currentContact.setProject(contact.getProject());
        }

        if(contact.getAvatar() != null && contact.getAvatar().length() > 0) {
            currentContact.setAvatar(contact.getAvatar());
        }

        if(contact.getEmployeeId() != null && contact.getEmployeeId() > 0) {
            currentContact.setEmployeeId(contact.getEmployeeId());
        }
    }
}
