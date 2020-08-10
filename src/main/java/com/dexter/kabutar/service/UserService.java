package com.dexter.kabutar.service;

import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.NickNameAlreadyExistException;
import com.dexter.kabutar.model.UserInfo;

public interface UserService {

    User save(UserInfo userInfo) throws NickNameAlreadyExistException;

    User findById(Long id);
}
