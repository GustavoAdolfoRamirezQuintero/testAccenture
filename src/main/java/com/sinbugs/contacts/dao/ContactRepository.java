package com.sinbugs.contacts.dao;


import com.sinbugs.contacts.dto.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, Long> {

}
