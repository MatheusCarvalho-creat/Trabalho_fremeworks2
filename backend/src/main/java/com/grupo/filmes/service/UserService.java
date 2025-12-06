package com.grupo.filmes.service;

import com.grupo.filmes.model.User;
import com.grupo.filmes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder){
        this.repo=repo; this.passwordEncoder=passwordEncoder;
    }

    public User register(User u){
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return repo.save(u);
    }

    public Optional<User> findByUsername(String username){ return repo.findByUsername(username); }
}
