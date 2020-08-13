package com.dexter.kabutar.service;

import com.dexter.kabutar.dao.UserRepository;
import com.dexter.kabutar.domain.User;
import com.dexter.kabutar.exception.NickNameAlreadyExistException;
import com.dexter.kabutar.model.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        userService = new UserServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void successfullyCreateUser() throws NickNameAlreadyExistException {
        User user = new User("Amit");
        when(userRepository.save(any())).thenReturn(user);
        UserInfo userInfo = new UserInfo("Amit");
        User expected = userService.create(userInfo);
        assertEquals(expected.getNickName(), userInfo.getNickName());
    }
}
