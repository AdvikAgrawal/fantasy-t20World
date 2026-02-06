package com.advik.fantasyt20.Model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyAppUserService implements UserDetailsService{

    // @Autowired
    private MyAppUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String emailInput) throws UsernameNotFoundException {
        MyAppUser user = repository.findByEmail(emailInput); 
        
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + emailInput);
        }
        
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }    
}
