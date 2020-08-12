package com.dexter.kabutar.service;

import com.dexter.kabutar.dao.MessageRepository;
import com.dexter.kabutar.domain.Message;
import com.dexter.kabutar.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Message> viewMessage() {
        return messageRepository.findAll();
    }

    @Override
    public List<String> viewSentMessage(Long senderId) {
        List<String> messages = messageRepository.findBySenderId(senderId);
        return messages;
    }

    @Override
    public List<String> viewReceivedMessage(Long receiverId, Long senderId) {
        if(senderId == null) return viewReceivedMessage(receiverId);
        return messageRepository.findByReceiverSenderId(receiverId, senderId);
    }

    private List<String> viewReceivedMessage(Long receiverId){
        return messageRepository.findByReceiverId(receiverId);
    }
}
