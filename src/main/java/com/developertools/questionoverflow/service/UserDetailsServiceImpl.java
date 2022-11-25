package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        var role = Stream.of(user.getRole())
                .map(roles -> new SimpleGrantedAuthority(roles.name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), role);
    }
}
