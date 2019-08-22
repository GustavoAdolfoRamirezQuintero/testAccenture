package com.accenture.contacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.contacts.dao.ContactRepository;
import com.accenture.contacts.dto.Contact;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepository dao;

    public Contact save(Contact contact) {
        return dao.save(contact);

    }

    public Contact list(Long id) {
        return dao.findOne(id);

    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public List<Contact> findAllContacts() {
        return dao.findAll();    
    }

}
