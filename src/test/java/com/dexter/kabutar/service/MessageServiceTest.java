package com.dexter.kabutar.service;

import com.dexter.kabutar.dao.MessageRepository;
import com.dexter.kabutar.dao.UserRepository;
import com.dexter.kabutar.domain.Message;
import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.InvalidRequestException;
import com.dexter.kabutar.model.MessageInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageService messageService;

    @Before
    public void init(){
        messageService = new MessageServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void successfullySendMessage() throws InvalidRequestException {
        String senderNickName = "Amit";
        String receiverNickName = "Srikant";
        String content = "Hello";

        MessageInfo messageInfo = new MessageInfo(content, senderNickName, receiverNickName);

        User sender = new User(senderNickName);
        User receiver = new User(receiverNickName);

        Message expected = new Message(content, sender, receiver);
        when(userRepository.findByNickName(any())).thenReturn(sender);
        when(userRepository.findByNickName(any())).thenReturn(receiver);

        when(messageRepository.save(any())).thenReturn(expected);

        Message actual = messageService.send(messageInfo);

        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    public void throwInvalidRequestException_WhenSenderAndReceiverIsSame() throws InvalidRequestException {
        String senderNickName = "Amit";
        String receiverNickName = "Amit";
        String content = "Hello";

        MessageInfo messageInfo = new MessageInfo(content, senderNickName, receiverNickName);

        assertThrows(InvalidRequestException.class, () -> messageService.send(messageInfo));
    }

    @Test
    public void throwInvalidRequestException_WhenRequestIsNull() throws InvalidRequestException {

        assertThrows(InvalidRequestException.class, () -> messageService.send(null));
    }

    @Test
    public void successfullyFindMesssage(){
        String senderNickName = "Amit";
        String message = "Hello";
        List<String> expected = new ArrayList<>();
        expected.add(message);

        when(messageRepository.findBySenderNickName(senderNickName)).thenReturn(expected);

        List<String> actual = messageService.viewSentMessage(senderNickName);

        assertTrue(actual.contains(message));
    }
}
