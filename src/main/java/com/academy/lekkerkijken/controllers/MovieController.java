package com.academy.lekkerkijken.controllers;

import com.academy.lekkerkijken.models.Movie;
import com.academy.lekkerkijken.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies/")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

//    @PostConstruct
//    //this method will be called every time the application is started
//    public void addSomeMovies() {
//        for(int i = 1; i < 6; i++) {
//            Movie movie = new Movie();
//            this.movieRepository.save(movie);
//        }
//    }

    @PostMapping
    public Movie create(@RequestBody Movie newMovie) {
        this.movieRepository.save(newMovie);
        return newMovie;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping
    public Iterable<Movie> getAll() {
        return this.movieRepository.findAll();
    }

    @GetMapping({"id"})
    public Movie getMovie(@PathVariable long id) {
        Optional<Movie> result= this.movieRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
}
