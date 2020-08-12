package com.dexter.kabutar.controller;

import com.dexter.kabutar.domain.Message;
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
    public ResponseEntity sendMessage(@RequestBody Message message){
        try{
            messageService.send(message);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception exception){
            Map<String, String> error = new HashMap<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping(path = "/view/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewMessage(){
        try{

            List<Message> messages = messageService.viewMessage();
            return ResponseEntity.ok(messages);

        }catch(Exception exception){
            Map<String, String> error = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
        }
    }

    @GetMapping(path = "/view/receive", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewReceivedMessage(
            @RequestParam(name = "receiver") Long receiverId,
            @RequestParam(name = "sender", required = false) Long senderId
            ){
        try{

            List<String> messages = messageService.viewReceivedMessage(receiverId, senderId);
            return ResponseEntity.ok(messages);

        }catch(Exception exception){
            Map<String, String> error = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
        }
    }

    @GetMapping(path = "/view/sent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewSentMessage(@RequestParam(name = "sender") Long senderId){
        try{

            List<String> messages = messageService.viewSentMessage(senderId);
            return ResponseEntity.ok(messages);

        }catch(Exception exception){
            Map<String, String> error = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
        }
    }
}
