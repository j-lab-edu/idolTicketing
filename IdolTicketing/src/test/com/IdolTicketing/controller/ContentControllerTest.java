package com.IdolTicketing.controller;

import com.IdolTicketing.dto.ContentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    @Test
    void createContents() throws Exception {
        ContentDTO contentDTO = ContentDTO.builder()
                .userId("admin")
                .build();
        String goodString = objectMapper.writeValueAsString(contentDTO);

        MvcResult result = mockMvc.perform(post("/contents/")
                        .param("userId", "admin")
                        .param("isAdmin","true")
                        .content(goodString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        ContentDTO response = objectMapper.readValue(content, ContentDTO.class);

        assertEquals("같습니다.", contentDTO, response);
    }
    @Test
    void createContentsFail() throws Exception {
        ContentDTO contentDTO = ContentDTO.builder()
                .userId("admin")
                .build();
        String contentString = objectMapper.writeValueAsString(contentDTO);

        MvcResult result = mockMvc.perform(post("/contents/")
                        .param("userId", "user1")
                        .param("isAdmin","fail")
                        .content(contentString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertNotEquals("같지 않습니다.", contentDTO, content);
    }
}