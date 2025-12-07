package com.grupo.filmes.controller;

// ...
// Remova import de UserService ou UserRepository
import com.grupo.filmes.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    // Remova qualquer referência a UserService

    public MovieController(MovieService movieService) { // Apenas MovieService
        this.movieService = movieService;
    }
    // ...
}