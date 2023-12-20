package com.mia.xrs.controller;

import com.mia.xrs.dto.JwtResponse;
import com.mia.xrs.dto.JwtTokenDto;
import com.mia.xrs.dto.LoginRequest;
import com.mia.xrs.dto.UserDto;
import com.mia.xrs.entity.User;
import com.mia.xrs.security.JwtUtils;
import com.mia.xrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(JwtResponse.builder()
                .jwt(jwt)
                .username(userDetails.getUsername())
                .roles(roles)
                .build());
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyJwtToken(@RequestBody JwtTokenDto token) {

        try{
            userDetailsService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token.getJwt()));
            jwtUtils.validateJwtToken(token.getJwt());
            return ResponseEntity.ok("JWT token is valid");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token is invalid");
        }
    }
}
