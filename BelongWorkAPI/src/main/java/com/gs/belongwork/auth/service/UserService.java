package com.gs.belongwork.auth.service;

import com.gs.belongwork.auth.model.User;
import com.gs.belongwork.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repo;
    public UserService(UserRepository repo){ this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

    public User save(User u){ return repo.save(u); }
}
