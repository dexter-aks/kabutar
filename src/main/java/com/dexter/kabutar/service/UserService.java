package com.dexter.kabutar.service;

import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.NickNameAlreadyExistException;
import com.dexter.kabutar.model.UserInfo;

import java.util.List;

public interface UserService {

    User create(UserInfo userInfo) throws NickNameAlreadyExistException;

    List<User> findUsers();
}
