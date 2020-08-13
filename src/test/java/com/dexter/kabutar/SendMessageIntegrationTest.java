package com.dexter.kabutar;

import com.dexter.kabutar.dao.UserRepository;
import com.dexter.kabutar.domain.User;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SendMessageIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgressTestContainer.getInstance();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void successfullySendMessage() throws Exception{
        addUsers();
        List<User> users = userRepository.findAll();
        System.out.println("UsersCreated:"+users);
        String request = "{\"content\":\"whatsup?\",\"senderNickName\":\"Abhishek\",\"receiverNickName\":\"Akrit\"}";
        this.mockMvc.perform(post("/message/send")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void throwInvalidRequestException_WhenSenderId_IsSameAs_ReceiverId() throws Exception {
        String request = "{\"content\":\"whatsup?\",\"senderNickName\":\"Abhishek\",\"receiverNickName\":\"Abhishek\"}";
        this.mockMvc.perform(post("/message/send")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("You cannot send message to yourself"));
    }

    private void addUsers(){
        List<User> users = new ArrayList<>();
        User user1 = new User("Abhishek");
        User user2 = new User("Akrit");
        users.add(user1);
        users.add(user2);
        userRepository.saveAll(users);
    }
}
