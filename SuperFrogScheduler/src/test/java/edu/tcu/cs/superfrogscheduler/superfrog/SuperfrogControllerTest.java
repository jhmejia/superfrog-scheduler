package edu.tcu.cs.superfrogscheduler.superfrog;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        s1.setId(0);
        s1.setUsername("johnnguyen");
        s1.setFirstName("John");
        s1.setLastName("Nguyen");
        s1.setEmail("john.nguyen@tcu.edu");
        s1.setPassword("1234567890");
        s1.setActive(true);
        this.superfrogs.add(s1);

        Superfrog s2 = new Superfrog();
        s2.setId(1);
        s2.setUsername("johnhenry");
        s2.setFirstName("John");
        s2.setLastName("Henry");
        s2.setEmail("john.h.meija@tcu.edu");
        s2.setPassword("1234567890");
        s2.setActive(true);
        this.superfrogs.add(s2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindSuperfrogByIdSuccess() throws Exception {
        // Given
        given(this.superfrogService.findById(0)).willReturn(this.superfrogs.get(0));

        // When and then
        this.mockMvc.perform(get("/api/superfrogs/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value(0))
                .andExpect(jsonPath("$.data.firstName").value("John"));

    }
}