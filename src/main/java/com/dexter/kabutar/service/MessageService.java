package com.dexter.kabutar.service;

import com.dexter.kabutar.exception.InvalidRequestException;
import com.dexter.kabutar.model.MessageInfo;

import java.util.List;

public interface MessageService {

    void send(MessageInfo messageInfo) throws InvalidRequestException;

    List<String>  viewSentMessage(String senderNickName);

    List<String> viewReceivedMessage(String receiverNickName, String senderNickName);

}
