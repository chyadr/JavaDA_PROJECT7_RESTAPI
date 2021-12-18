package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUser(User user) {
            userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User checkIfUserExist() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if(authentication instanceof OAuth2AuthenticationToken){
            //By default its DefaultOAuth2User.
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String,Object> attributes =  defaultOAuth2User.getAttributes();
            return findByUsername(attributes.get("login").toString());

        } else if (authentication instanceof UsernamePasswordAuthenticationToken){
            return findByUsername(authentication.getName());
        }
        return null;
    }

    @Override
    public User getOAuth2UserInfos() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if(authentication instanceof OAuth2AuthenticationToken){
            //By default is DefaultOAuth2User.
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String,Object> attributes =  defaultOAuth2User.getAttributes();
            Collection<? extends GrantedAuthority> authorities =  defaultOAuth2User.getAuthorities();
            String fullname = attributes.get("name") != null ? attributes.get("name").toString(): "";
            String role= authorities.stream().findFirst().get().getAuthority().replace("ROLE_","");
            return new User().username(attributes.get("login").toString()).fullname(fullname).role(role);

        }
        return null;
    }

    @Override
    public boolean isOAuth2() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if(authentication instanceof OAuth2AuthenticationToken){
            //By default its DefaultOAuth2User.
            return true;
        }
        return false;
    }
}
