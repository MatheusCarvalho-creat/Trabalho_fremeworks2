package com.grupo.filmes.controller;

import com.grupo.filmes.model.Movie;
import com.grupo.filmes.model.User;
import com.grupo.filmes.service.MovieService;
import com.grupo.filmes.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService service;
    private final UserService userService;
    public MovieController(MovieService service, UserService userService){ this.service=service; this.userService=userService; }

    @GetMapping
    public Page<Movie> list(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size){
        return service.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> get(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody Movie m, Principal principal){
        // set createdBy if authenticated
        if(principal != null){
            userService.findByUsername(principal.getName()).ifPresent(m::setCreatedBy);
        }
        Movie saved = service.save(m);
        return ResponseEntity.created(URI.create("/api/movies/"+saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie m){
        return service.findById(id).map(existing -> {
            m.setId(existing.getId());
            Movie updated = service.save(m);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.findById(id).map(existing -> {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
