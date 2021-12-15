package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

     @InjectMocks
     private UserService userService;
     @Mock
     private UserRepository userRepository;

     @Test
     public void givenBidList_whenFinAllBidList_thenReturnList() {
         when(userRepository.findAll()).thenReturn(ConstantsTest.users);
         List<User> users =userService.findAll();
         assertNotNull(users);
         assertEquals(1, users.size());
     }

    @Test
    public void givenBidList_whenCreateBidList_thenVerify() {
        when(userRepository.save(any())).thenReturn(ConstantsTest.user);
        userService.createUser(ConstantsTest.user);
        verify(userRepository,times(1)).save(ConstantsTest.user);
    }


    @Test
    public void givenBidList_whenUpdateBidList_thenVerify() {
        when(userRepository.save(any())).thenReturn(ConstantsTest.user);
        userService.updateUser(ConstantsTest.user);
        verify(userRepository,times(1)).save(ConstantsTest.user);
    }

    @Test
    public void givenBidList_whenUDeleteBidList_thenVerify() {
        doNothing().when(userRepository).delete(any());
        userService.deleteUser(ConstantsTest.user);
        verify(userRepository,times(1)).delete(ConstantsTest.user);
    }
}
