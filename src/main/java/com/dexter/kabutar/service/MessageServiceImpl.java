package com.dexter.kabutar.service;

import com.dexter.kabutar.dao.MessageRepository;
import com.dexter.kabutar.dao.UserRepository;
import com.dexter.kabutar.domain.Message;
import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.InvalidRequestException;
import com.dexter.kabutar.model.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Message send(MessageInfo messageInfo) throws InvalidRequestException {
        if(messageInfo == null) throw new InvalidRequestException();

        String senderNickName = messageInfo.getSenderNickName();
        String receiverNickName = messageInfo.getReceiverNickName();

        if (senderNickName.equals(receiverNickName))
            throw new InvalidRequestException();

        User sender = userRepository.findByNickName(senderNickName);
        User receiver = userRepository.findByNickName(receiverNickName);
        Message message = new Message(messageInfo.getContent(), sender, receiver);
        return messageRepository.save(message);
    }

    @Override
    public List<String> viewSentMessage(String senderNickName) {
        return messageRepository.findBySenderNickName(senderNickName);
    }

    @Override
    public List<String> viewReceivedMessage(String receiverNickName, String senderNickName) {
        if(senderNickName == null || senderNickName.isEmpty())
            return viewReceivedMessage(receiverNickName);

        return messageRepository.findByReceiverSenderNickName(receiverNickName, senderNickName);
    }

    private List<String> viewReceivedMessage(String receiverId){
        return messageRepository.findByReceiverNickName(receiverId);
    }
}
