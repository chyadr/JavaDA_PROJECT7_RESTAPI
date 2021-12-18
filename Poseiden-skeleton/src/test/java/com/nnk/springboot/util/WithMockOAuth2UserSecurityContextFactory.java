package com.nnk.springboot.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.*;

public class WithMockOAuth2UserSecurityContextFactory
        implements WithSecurityContextFactory<WithMockOAuth2User> {
    @Override
    public SecurityContext createSecurityContext(WithMockOAuth2User customUser) {

        // look on https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/test-method.html
        // section @WithSecurityContext
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("login", customUser.login());
        attributes.put("username", customUser.username());
        attributes.put("name",customUser.name() );
        attributes.put("role", customUser.role());

        GrantedAuthority authority = new OAuth2UserAuthority(attributes);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        DefaultOAuth2User user = new DefaultOAuth2User(authorities, attributes, "username");
        Authentication auth =
                new OAuth2AuthenticationToken(user, user.getAuthorities(), "registrationId");
        context.setAuthentication(auth);
        return context;
    }
}
