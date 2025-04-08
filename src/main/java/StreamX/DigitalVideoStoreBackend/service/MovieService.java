package StreamX.DigitalVideoStoreBackend.service;

import StreamX.DigitalVideoStoreBackend.model.MovieModel;
import StreamX.DigitalVideoStoreBackend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieModel createMovie(MovieModel movie) { 
        return movieRepository.save(movie); 
    }

    public List<MovieModel> getAllMovies() { 
        return movieRepository.findAll(); 
    }

    public Optional<MovieModel> getMovieById(String id) { 
        return movieRepository.findById(id); 
    }

    public List<MovieModel> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<MovieModel> getFeaturedMovies() { 
        return movieRepository.findByFeatured(true); 
    }

    public MovieModel updateMovie(String id, MovieModel movie) {
        movie.setId(id);
        return movieRepository.save(movie);
    }

    public void deleteMovie(String id) {
         movieRepository.deleteById(id); 
    }
}

