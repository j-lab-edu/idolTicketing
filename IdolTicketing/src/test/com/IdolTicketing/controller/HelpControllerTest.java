package com.IdolTicketing.controller;

import com.IdolTicketing.dto.HelpDTO;
import com.IdolTicketing.mapper.HelpMapper;
import com.IdolTicketing.service.HelpService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = HelpController.class)
class HelpControllerTest {

    @InjectMocks
    private HelpController helpController;

    @MockBean
    private HelpService helpService;

    @MockBean
    private HelpMapper helpMapper;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void helpBoard() throws Exception {
        HelpDTO helpDTO = HelpDTO.builder()
                .userId("test")
                .description("....")
                .title("sample")
                .isAdmin(false)
                .build();
        String helpString = objectMapper.writeValueAsString(helpDTO);

        MvcResult result = mockMvc.perform(post("/helps/")
                        .param("userId","test")
                        .param("isAdmin","false")
                        .content(helpString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        HelpDTO response = objectMapper.readValue(content, HelpDTO.class);

        assertEquals("같습니다.", helpDTO, response);
    }

    @Test
    void helpBoardFail() throws Exception {
        HelpDTO helpDTO = HelpDTO.builder()
                .userId("test1")
                .description("****")
                .title("sample")
                .build();
        String helpString = objectMapper.writeValueAsString(helpDTO);

        MvcResult result = mockMvc.perform(post("/helps/")
                        .param("userId","test")
                        .param("isAdmin","false")
                        .content(helpString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertNotEquals("같지 않습니다.", helpDTO, content);
    }

    @Test
    void updateBoard() throws Exception {
        HelpDTO helpDTO = HelpDTO.builder()
                .title("sample")
                .isAdmin(false)
                .userId("test")
                .id(01)
                .build();
        String helpString = objectMapper.writeValueAsString(helpDTO);

        MvcResult result = mockMvc.perform(patch("/helps/01")
                        .param("userId","test")
                        .param("isAdmin","false")
                        .content(helpString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        HelpDTO response = objectMapper.readValue(content, HelpDTO.class);

        assertEquals("같습니다.", helpDTO, response);
    }

    @Test
    void updateBoardFail() throws Exception {
        HelpDTO helpDTO = HelpDTO.builder()
                .title("sample1")
                .isAdmin(false)
                .userId("test1")
                .id(01)
                .build();
        String helpString = objectMapper.writeValueAsString(helpDTO);

        MvcResult result = mockMvc.perform(patch("/helps/01")
                        .param("userId","test")
                        .param("isAdmin","false")
                        .content(helpString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertNotEquals("같지 않습니다.", helpDTO, content);
    }
}