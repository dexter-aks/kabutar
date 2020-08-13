package com.dexter.kabutar.service;

import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.NickNameAlreadyExistException;
import com.dexter.kabutar.model.UserInfo;

public interface UserService {

    User create(UserInfo userInfo) throws NickNameAlreadyExistException;
}
