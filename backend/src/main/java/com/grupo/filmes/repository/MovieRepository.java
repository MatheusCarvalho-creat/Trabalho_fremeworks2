package com.grupo.filmes.repository;

import com.grupo.filmes.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Genero, Long> {
}
