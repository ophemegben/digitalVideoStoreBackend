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

    public MovieModel update(String id, MovieModel updatedMovie) {
        MovieModel existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id: " + id));

        // Validate and update the fields of existingMovie with updatedMovie
        if (!isNullOrEmpty(updatedMovie.getTitle())) {
            existingMovie.setTitle(updatedMovie.getTitle());
        }

        if (!isNullOrEmpty(updatedMovie.getGenre())) {
            existingMovie.setGenre(updatedMovie.getGenre());
        }

        if (updatedMovie.getReleaseYear() != 0) {
            existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
        }

        if (!isNullOrEmpty(updatedMovie.getOverview())) {
            existingMovie.setOverview(updatedMovie.getOverview());
        }

        if (!isNullOrEmpty(updatedMovie.getSmallPoster())) {
            existingMovie.setSmallPoster(updatedMovie.getSmallPoster());
        }

        if (!isNullOrEmpty(updatedMovie.getLargePoster())) {
            existingMovie.setLargePoster(updatedMovie.getLargePoster());
        }

        if (updatedMovie.getRentPrice() != 0) {
            existingMovie.setRentPrice(updatedMovie.getRentPrice());
        }

        if (updatedMovie.getBuyPrice() != 0) {
            existingMovie.setBuyPrice(updatedMovie.getBuyPrice());
        }

        if (updatedMovie.getFeatured() != null) {
            existingMovie.setFeatured(updatedMovie.getFeatured());
        }

        if (!isNullOrEmpty(updatedMovie.getCast())) {
            existingMovie.setCast(updatedMovie.getCast());
        }

        if (!isNullOrEmpty(updatedMovie.getDirector())) {
            existingMovie.setDirector(updatedMovie.getDirector());
        }

        if (!isNullOrEmpty(updatedMovie.getReleaseDate())) {
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        }

        if (!isNullOrEmpty(updatedMovie.getAwards())) {
            existingMovie.setAwards(updatedMovie.getAwards());
        }
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    // Helper method for null/empty string check
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
