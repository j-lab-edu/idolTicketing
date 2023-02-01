package com.IdolTicketing.controller;

import com.IdolTicketing.dto.ContentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ContentController.class)
class ContentControllerTest {

    @InjectMocks
    private ContentController ContentController;

    @MockBean
    private com.IdolTicketing.service.ContentService ContentService;

    @MockBean
    private com.IdolTicketing.mapper.ContentMapper ContentMapper;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    ContentDTO contentDTO;
    @BeforeEach
    void initTest(){
        this.contentDTO = ContentDTO.builder()
                .userId("admin")
                .build();

    }

    @Test
    void createContents() throws Exception {
        String goodString = objectMapper.writeValueAsString(this.contentDTO);
        MvcResult result = mockMvc.perform(post("/contents/")
                        .param("isAdmin","true")
                        .content(goodString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals("같습니다.", contentDTO,
                objectMapper.readValue(result.getResponse().getContentAsString(), ContentDTO.class));
    }
    @Test
    void createContentsFail() throws Exception {

        String contentString = objectMapper.writeValueAsString(this.contentDTO);
        MvcResult result = mockMvc.perform(post("/contents/")
                        .param("isAdmin","true")
                        .content(contentString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        ContentDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), ContentDTO.class);
        response.setUserId("user");
        assertNotEquals("같지 않습니다.", contentDTO, response);
    }
}