package com.example.creea.security;

import com.example.creea.persistence.user.UserRepository;
import com.example.creea.persistence.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user =userRepository.findByUserName(userName);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
