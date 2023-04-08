package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class SuperfrogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuperfrogService superfrogService;

    List<Superfrog> superfrogs;




    @BeforeEach
    void setUp() {
        this.superfrogs = new ArrayList<>();
        Superfrog s1 = new Superfrog();
        s1.setId(1);
        s1.setUsername("superfrog1");
        s1.setFirstName("John");
        s1.setLastName("Mejyen");
        s1.setEmail("example@gmail.com");
        s1.setPassword("password");
        s1.setActive(true);
        this.superfrogs.add(s1);

        Superfrog s2 = new Superfrog();
        s2.setId(2);
        s2.setUsername("superfrog2");
        s2.setFirstName("Joey");
        s2.setLastName("Quinn");
        s2.setEmail("iamacat@gmail.com");
        s2.setPassword("password2");
        s2.setActive(true);
        this.superfrogs.add(s2);

        Superfrog s3 = new Superfrog();
        s3.setId(3);
        s3.setUsername("superfrog3");
        s3.setFirstName("Ethan");
        s3.setLastName("Edinboro");
        s3.setEmail("jo@gmail.com");
        s3.setPassword("password3");
        s3.setActive(false);
        this.superfrogs.add(s2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindSuperfrogByIdSuccess() throws Exception {
        // Given
        given(this.superfrogService.findById(1)).willReturn(this.superfrogs.get(0));

        // When and then
        this.mockMvc.perform(get("/api/superfrogs/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.firstName").value("John"));

    }

    @Test
    void testFindSuperfrogByIdNotFound() throws Exception {
        // Given
        given(this.superfrogService.findById(1)).willThrow(new ObjectNotFoundException("superfrog", 1));

        // When and then
        this.mockMvc.perform(get("/api/superfrogs/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find superfrog with Id 1 :("))
                .andExpect(jsonPath("$.data").isEmpty());


    }
}