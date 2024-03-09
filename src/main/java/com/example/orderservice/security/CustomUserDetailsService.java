package com.example.orderservice.security;

import com.example.orderservice.externalservices.UserService;
import com.example.orderservice.models.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserService userService;
    public CustomUserDetailsService(UserService userService){
        this.userService=userService;
    }
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity user=userService.getfullUser(Integer.parseInt(userId));
        GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole().getName());
        Set<GrantedAuthority>authorities=new HashSet<>();
        authorities.add(authority);
        return new User(String.valueOf(user.getId()),user.getPassword(),authorities);
    }
}
