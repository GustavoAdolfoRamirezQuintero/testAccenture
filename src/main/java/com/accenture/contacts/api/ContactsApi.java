package com.accenture.contacts.api;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.contacts.dto.Contact;
import com.accenture.contacts.service.ContactService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ContactsApi {

    @Autowired
    ContactService contactService;

    // Inyecta mapper de Dozer
    @Autowired
    Mapper mapper;

    /**
     * save contact
     *
     * @param contactRequest
     * @return
     */
    @RequestMapping(value = "/contact/saveOrUpdate", method = RequestMethod.POST)
    public ContactResponse updateOrSave(@RequestBody @Valid ContactRequest contactRequest) {
        // Mapeo request dto ==> entity
        Contact contact = mapper.map(contactRequest, Contact.class);

        // Invoca logica de negocio
        Contact updatedContact = contactService.save(contact);

        // Mapeo entity ==> response dto
        ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class);

        return contactResponse;
    }

    /**
     * list contact by id
     *
     * @return
     */
    @RequestMapping(value = "/contact/list/{id}", method = RequestMethod.GET)
    public Contact contactGetById(@PathVariable Long id) {
        Contact contact = contactService.list(id);
        return contact;
    }

    /**
     * delete by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/contact/delete/{id}", method = RequestMethod.GET)
    public Map<String, String> delteById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<String, String>();
        contactService.delete(id);
        response.put("success", "true");
        response.put("message", "El contacto ha sido eliminado");
        return response;
    }

    /**
     *
     *find all contacts
     * @return
     */
    @RequestMapping(value = "/contact/findAllContacts", method = RequestMethod.GET)
    public List<Contact> findAllContacts() {
        return contactService.findAllContacts();
    }

}
