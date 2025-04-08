package StreamX.DigitalVideoStoreBackend.controller;

import StreamX.DigitalVideoStoreBackend.model.MovieModel;
import StreamX.DigitalVideoStoreBackend.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Endpoint to create a new movie
    @PostMapping
    public ResponseEntity<MovieModel> createMovie(@RequestBody MovieModel movie) {
        return ResponseEntity.status(201).body(movieService.createMovie(movie));
    }

    // Endpoint to get all movies
    @GetMapping
    public ResponseEntity<List<MovieModel>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // Endpoint to search movies by title
    @GetMapping("/search")
    public ResponseEntity<List<MovieModel>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchMoviesByTitle(title));
    }

    // Endpoint to get featured movies
    @GetMapping("/featured")
    public ResponseEntity<List<MovieModel>> getFeaturedMovies() {
        return ResponseEntity.ok(movieService.getFeaturedMovies());
    }

    // Endpoint to get a movie by ID
    @GetMapping("/id")
    public ResponseEntity<MovieModel> getMovieById(@RequestParam String id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to update a movie by ID
    @PutMapping("/id")
    public ResponseEntity<?> updateMovie(@RequestParam String id, @RequestBody MovieModel movie) {
        try {
            MovieModel updatedMovie = movieService.update(id, movie);
            return ResponseEntity.ok(Map.of("message", "Movie updated successfully", "movie", updatedMovie));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Error updating TV show", "error", e.getMessage()));
        } 
    }

    // Endpoint to delete a movie by ID
    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteMovie(@RequestParam String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
