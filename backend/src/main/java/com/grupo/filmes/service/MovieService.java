package com.grupo.filmes.service;

import com.grupo.filmes.model.Genero;
import com.grupo.filmes.model.Filme;
import com.grupo.filmes.repository.MovieRepository;
import com.grupo.filmes.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository repo;
    private final UserRepository userRepo;

    public MovieService(MovieRepository repo, UserRepository userRepo){ this.repo=repo; this.userRepo=userRepo; }

    public Page<Genero> findAll(Pageable pageable){ return repo.findAll(pageable); }
    public Optional<Genero> findById(Long id){ return repo.findById(id); }
    public Genero save(Genero m){ return repo.save(m); }
    public void delete(Long id){ repo.deleteById(id); }
}
