package com.sinbugs.contacts.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sinbugs.contacts.dto.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MappingTest {
	
	@Autowired
	Mapper mapper;
	
	@Test
	public void fromRequestToEntity() {
		Contact c = new Contact(1L, "Gustavo", "Ramirez", "+573112223344", "gustavoramirezquintero@gmail.com"); 
		ContactRequest req = mapper.map(c, ContactRequest.class);
		
		assertThat(req)
			.hasFieldOrPropertyWithValue("id", c.getId())
			.hasFieldOrPropertyWithValue("firstName", c.getFirstName())
			.hasFieldOrPropertyWithValue("lastName", c.getLastName())
			.hasFieldOrPropertyWithValue("phoneNumber", c.getPhoneNumber())
			.hasFieldOrPropertyWithValue("email", c.getEmail());
	}

}
