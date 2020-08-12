package com.dexter.kabutar.service;

import com.dexter.kabutar.domain.Message;
import com.dexter.kabutar.exception.InvalidRequestException;

import java.util.List;

public interface MessageService {

    void send(Message message) throws InvalidRequestException;

    List<String>  viewSentMessage(Long senderId);

    List<String> viewReceivedMessage(Long receiverId, Long senderId);

}
