package com.dexter.kabutar.service;

import com.dexter.kabutar.dao.UserRepository;
import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.NickNameAlreadyExistException;
import com.dexter.kabutar.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(UserInfo userInfo) throws NickNameAlreadyExistException {
        try{
            return userRepository.save(new User(userInfo.getNickName()));
        }catch(Exception exception){
            throw new NickNameAlreadyExistException();
        }
    }
}
