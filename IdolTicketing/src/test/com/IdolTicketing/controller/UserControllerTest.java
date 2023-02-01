package com.IdolTicketing.controller;

import com.IdolTicketing.dto.UserDTO;
import com.IdolTicketing.service.UserService;
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
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @InjectMocks
    private UserController UserController;

    @MockBean
    private com.IdolTicketing.service.UserService UserService;

    @MockBean
    private com.IdolTicketing.mapper.UserMapper UserMapper;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void register() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .userId("test1")
                .email("aaa@naver.com")
                .phone(010000111)
                .password("aaa111")
                .build();
        MvcResult result = mockMvc.perform(post("/users/register")
                        .content(objectMapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        UserDTO response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), UserDTO.class);
        assertEquals("같습니다.", userDTO, response);
    }

    @Test
    void registerFail() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .userId("test1")
                .email("aaa@naver.com")
                .phone(010000111)
                .password("aaa111")
                .build();
        String userString = objectMapper.writeValueAsString(userDTO);

        MvcResult result = mockMvc.perform(post("/users/register")
                   .content(userString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertNotEquals("같지 않습니다.", userDTO, content);
    }
}