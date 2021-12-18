package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.util.WithMockOAuth2User;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WithMockOAuth2User
public class UserOAuth2ServiceTest {

     @InjectMocks
     private UserService userService;
     @Mock
     private UserRepository userRepository;

    @Test
    public void givenNothing_whenCheckIfUserExist_thenUser() {
        when(userRepository.findByUsername(anyString())).thenReturn(ConstantsTest.user);
        User user = userService.checkIfUserExist();
        assertNotNull(user);
    }

    @Test
    public void givenNothing_whenOAuth2UserInfos_thenReturnUser() {
        User user = userService.getOAuth2UserInfos();
        assertNotNull(user);
        assertNotNull(user.getUsername());
    }


}
