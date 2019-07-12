package com.sinbugs.contacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinbugs.contacts.api.ContactResponse;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactsWsApplicationTests {
    
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void contextLoads() {
    }

    /**
     * Send GET Request with Path Variable
     *
     * @throws Exception
     */
    @Test
    public void givenSedeURIWithPathVariable_whenMockMVC_thenResponseOK() throws Exception {
        this.mockMvc
                .perform(get("/contact/findAllContacts.json")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
                //.andExpect(jsonPath("$.message").value("OK"));
    }
    
    /**
     * Send POST Request con Formdata
     *
     * @throws Exception
     */
    @Test
    public void givenGreetURIWithPostAndFormData_whenMockMVC_thenResponseOK() throws Exception {
        String nombre = "Zrii restfull " + new Date().getTime();
        ContactResponse empresa = new ContactResponse();
        empresa.setId(1l);
        empresa.setFirstName(nombre);
        empresa.setEmail("www.zrii.com");
        //pruebo servicio rest
        this.mockMvc.perform(post("/contact/save.json")
                //.session(sessionHolder.getSession())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(empresa)))
//                                .param("id", "1")
//                                .param("nombre", nombre)
//                                .param("web", "www.zrii.com"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.datos.id").value(1))
                .andExpect(jsonPath("$.datos.nombre").value(nombre));
               

                
        //comparo contra lo que tengo en bd usando servicio spring
        //ContactResponse empresa2 = servicioAdminEmpresa.cargarEntidad(1l).getObjetoRespuesta();
        //assertEquals(nombre, empresa2.getNombre());
    }
    
    /**
     * converts a Java object into JSON representation
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
