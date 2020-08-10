package com.dexter.kabutar.controller;

import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.NickNameAlreadyExistException;
import com.dexter.kabutar.model.UserInfo;
import com.dexter.kabutar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody UserInfo userInfo){
        try{
            User user = userService.save(userInfo);
            return ok(user);
        }catch(NickNameAlreadyExistException existException){
            Map<String, String> error = new HashMap<>();
            error.put("message","NickName already exits");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @GetMapping(path = "/search/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable(name = "id") Long id){
        try{
            return ResponseEntity.ok(userService.findById(id));
        }catch(NoSuchElementException elementException){
            Map<String, String> error = new HashMap<>();
            error.put("message","User Not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    }
}
