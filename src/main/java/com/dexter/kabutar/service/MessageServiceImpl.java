package com.dexter.kabutar.service;

import com.dexter.kabutar.dao.MessageRepository;
import com.dexter.kabutar.domain.Message;
import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void send(Message message)
            throws InvalidRequestException {
        if (message.getSender().equals(message.getReceiver())) throw new InvalidRequestException();
        messageRepository.save(message);
    }

    @Override
    public List<String> viewSentMessage(User sender) {
        System.out.println("Calling View Sent");
        List<Message> messages = messageRepository.findBySenderId(sender.getId());
        System.out.println("Messages:"+messages);
        List<String> list = new ArrayList<>();
        messages.forEach(m -> list.add(m.getContent()));
        return list;
    }

    @Override
    public List<String> viewReceivedMessage(Long senderId, Long receiverId) {
        return null;
    }

    @Override
    public List<Message> viewMessage() {
        return messageRepository.findAll();
    }
}