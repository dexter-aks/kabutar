package com.dexter.kabutar.service;

import com.dexter.kabutar.domain.Message;
import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.InvalidRequestException;

import java.util.List;

public interface MessageService {

    void send(Message message) throws InvalidRequestException;

    List<String>  viewSentMessage(User sender);

    List<String> viewReceivedMessage(Long senderId, Long receiverId);

    List<Message> viewMessage();
}
