package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        }));

    }
}