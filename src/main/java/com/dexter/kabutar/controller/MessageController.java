package com.dexter.kabutar.controller;

import com.dexter.kabutar.exception.InvalidRequestException;
import com.dexter.kabutar.model.MessageInfo;
import com.dexter.kabutar.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessage(@RequestBody MessageInfo messageInfo){
        try{
            messageService.send(messageInfo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(InvalidRequestException exception){
            Map<String, String> error = new HashMap<>();
            error.put("message", "You cannot send message to yourself");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping(path = "/view/receive", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewReceivedMessage(
            @RequestParam(name = "receiver") String receiverNickName,
            @RequestParam(name = "sender", required = false) String senderNickName
            ){

        List<String> messages = messageService.viewReceivedMessage(receiverNickName, senderNickName);
        return ResponseEntity.ok(messages);
    }

    @GetMapping(path = "/view/sent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewSentMessage(@RequestParam(name = "sender") String senderNickName){

        List<String> messages = messageService.viewSentMessage(senderNickName);
        return ResponseEntity.ok(messages);
    }
}
