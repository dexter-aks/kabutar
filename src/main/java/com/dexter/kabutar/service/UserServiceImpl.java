package com.dexter.kabutar.service;

import com.dexter.kabutar.dao.UserRepository;
import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.NickNameAlreadyExistException;
import com.dexter.kabutar.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserInfo userInfo) throws NickNameAlreadyExistException {
        try{
            return userRepository.save(new User(userInfo.getNickName()));
        }catch(Exception exception){
            throw new NickNameAlreadyExistException();
        }

    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }else{
            throw new NoSuchElementException();
        }
    }
}
