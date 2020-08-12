package com.dexter.kabutar;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgressTestContainer.getInstance();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void successfullyAddUser() throws Exception {
        String request = "{\"nickName\": \"Ankit\"}";
        this.mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.nickName").value("Ankit"));

    }

    @Test
    public void throwNickNameExistException_WhenNickNameIsSame() throws Exception {
        String request = "{\"nickName\": \"Ankit\"}";
        this.mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("NickName already exits"));
    }
}
