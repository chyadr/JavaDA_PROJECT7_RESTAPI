package com.nnk.springboot.service;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserServiceDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceDetailsTest {



        @InjectMocks
        private UserServiceDetails userServiceDetails;

        @Mock
        private UserRepository userRepository;



    @Test
    public void givenUsername_whenLoadUserByUsername_thenReturnUserDetails(){
        when(userRepository.findByUsername(anyString())).thenReturn(ConstantsTest.user);
        UserDetails userDetails= userServiceDetails.loadUserByUsername("email");
        assertNotNull(userDetails);

    }


    }
