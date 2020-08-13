package com.dexter.kabutar;

import com.dexter.kabutar.dao.MessageRepository;
import com.dexter.kabutar.dao.UserRepository;
import com.dexter.kabutar.domain.Message;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FindMessageIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgressTestContainer.getInstance();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void findAllSentMessage() throws Exception{
        addUserAndMessages("Ravi", "Abhinav");
        List<Message> messages = messageRepository.findAll();
        System.out.println("sendMessages:"+messages);
        this.mockMvc.perform(
                get("/message/view/sent")
                .param("sender", "Ravi"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Hello"));

    }

    @Test
    public void findAllReceivedMessage() throws Exception{
        addUserAndMessages("Ria", "Shruti");
        List<Message> messages = messageRepository.findAll();
        System.out.println("sendMessages:"+messages);
        this.mockMvc.perform(
                get("/message/view/receive")
                        .param("receiver", "Shruti"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Hello"));

    }

    private void addUserAndMessages(String nickName1, String nickName2){
        List<User> users = new ArrayList<>();
        User user1 = new User(nickName1);
        User user2 = new User(nickName2);
        users.add(user1);
        users.add(user2);
        userRepository.saveAll(users);

        List<Message> request = new ArrayList<>();
        Message message1 = new Message("Hello", user1, user2);
        Message message2 = new Message("How are you", user2, user1);
        request.add(message1);
        request.add(message2);
        messageRepository.saveAll(request);
    }
}
