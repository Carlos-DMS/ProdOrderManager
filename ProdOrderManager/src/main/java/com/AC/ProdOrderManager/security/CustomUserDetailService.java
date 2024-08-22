package com.AC.ProdOrderManager.security;

import com.AC.ProdOrderManager.models.user.UserModel;
import com.AC.ProdOrderManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserModel user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }
}
