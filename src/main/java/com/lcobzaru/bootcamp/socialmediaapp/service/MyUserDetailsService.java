package com.lcobzaru.bootcamp.socialmediaapp.service;

import com.lcobzaru.bootcamp.socialmediaapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return  new User("foo", "foo", new ArrayList<>());
        Optional<com.lcobzaru.bootcamp.socialmediaapp.models.User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));;
        return new User(user.get().getUsername(),user.get().getPassword(),emptyList());
    }
}
