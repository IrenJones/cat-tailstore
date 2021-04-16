package com.cattailstore.store.controller.integration;

import com.cattailstore.store.configuration.BasicTest;
import com.cattailstore.store.controller.BookController;
import com.cattailstore.store.dto.FullBookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest extends BasicTest {

    @Autowired
    private BookController bookController;

    MockMvc mvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = standaloneSetup(bookController)
            .build();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
        FullBookDto dto = new FullBookDto();
        String body = objectMapper.writeValueAsString(dto);

        mvc.perform(put("/books/1")
            .contentType("application/json")
            .content(body))
            .andExpect(status().isBadRequest());
    }
}
