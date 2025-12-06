package com.grupo.filmes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name="movies")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private String genre;
    private Double rating;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name="created_by_id")
    private User createdBy;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public String getGenre(){return genre;}
    public void setGenre(String genre){this.genre=genre;}
    public Double getRating(){return rating;}
    public void setRating(Double rating){this.rating=rating;}
    public LocalDate getReleaseDate(){return releaseDate;}
    public void setReleaseDate(LocalDate releaseDate){this.releaseDate=releaseDate;}
    public User getCreatedBy(){return createdBy;}
    public void setCreatedBy(User u){this.createdBy=u;}
}
