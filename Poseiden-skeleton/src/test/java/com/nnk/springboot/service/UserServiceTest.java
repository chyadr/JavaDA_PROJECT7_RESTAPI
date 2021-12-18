package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WithMockUser(username = "user", authorities = { "USER" })
public class UserServiceTest {

     @InjectMocks
     private UserService userService;
     @Mock
     private UserRepository userRepository;


     @Test
     public void givenBidList_whenFindAllBidList_thenReturnList() {
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
    public void givenBidList_whenDeleteBidList_thenVerify() {
        doNothing().when(userRepository).delete(any());
        userService.deleteUser(ConstantsTest.user);
        verify(userRepository,times(1)).delete(ConstantsTest.user);
    }

    @Test
    public void givenBidList_whenFindById_thenReturnUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(ConstantsTest.user));
        Optional<User> user =userService.findById(1);
        assertNotNull(user);
        assertTrue(user.isPresent());
    }

    @Test
    public void givenBidList_whenFindByUsername_thenReturnUser() {
        when(userRepository.findByUsername(anyString())).thenReturn(ConstantsTest.user);
        User user =userService.findByUsername("username");
        assertNotNull(user);
    }

    @Test
    public void givenNothing_whenIsNotOAuth2_thenFalse() {
        boolean result = userService.isOAuth2();
        assertFalse(result);
    }

    @Test
    public void givenNothing_whenCheckIfUserExist_thenUser() {
         when(userRepository.findByUsername(anyString())).thenReturn(ConstantsTest.user);
        User user = userService.checkIfUserExist();
        assertNotNull(user);
    }

    @Test
    public void givenNothing_whenOAuth2UserInfos_thenNotFoundUser() {
        User user = userService.getOAuth2UserInfos();
        assertNull(user);
    }


}
