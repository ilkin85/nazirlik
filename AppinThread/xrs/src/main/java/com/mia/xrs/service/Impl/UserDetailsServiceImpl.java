package com.mia.xrs.service.Impl;

import com.mia.xrs.entity.User;
import com.mia.xrs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameAndIsEnabled(username,true).orElseThrow(
                ()-> new UsernameNotFoundException("User by Username : " + username + " not found")
        );

        return user;
    }
}
