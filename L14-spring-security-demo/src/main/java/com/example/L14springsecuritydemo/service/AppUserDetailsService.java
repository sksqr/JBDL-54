package com.example.L14springsecuritydemo.service;

import com.example.L14springsecuritydemo.entity.AppUser;
import com.example.L14springsecuritydemo.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByUsername(username);
        if(appUser == null){
            throw  new UsernameNotFoundException("User does not exist");
        }
        return appUser;
    }

    public void changePassword(UserDetails userDetails, String newPassword){
        AppUser user = appUserRepo.findByUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(newPassword));
        appUserRepo.save(user);
    }
}
