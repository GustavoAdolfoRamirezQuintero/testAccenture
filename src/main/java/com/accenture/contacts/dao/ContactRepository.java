package com.accenture.contacts.dao;


import com.accenture.contacts.dto.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, Long> {

    
    
}
