package StreamX.DigitalVideoStoreBackend.service;

import StreamX.DigitalVideoStoreBackend.model.TvShowModel;
import StreamX.DigitalVideoStoreBackend.repository.TvShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TvShowService {
    private final TvShowRepository tvShowRepository;

    public TvShowService(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public TvShowModel create(TvShowModel tvShow) {
        return tvShowRepository.save(tvShow);
    }

    public List<TvShowModel> getAll() {
        return tvShowRepository.findAll();
    }

    public Optional<TvShowModel> getById(String id) {
        return tvShowRepository.findById(id);
    }

    public List<TvShowModel> searchByTitle(String title) {
        return tvShowRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<TvShowModel> getFeatured() {
        return tvShowRepository.findByFeatured(true);
    }

    public TvShowModel update(String id, TvShowModel updatedTvShow) {
        TvShowModel existingTvShow = tvShowRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TV show not found with id: " + id));

        // Validate and update the fields of existingTvShow with updatedTvShow
        if (!isNullOrEmpty(updatedTvShow.getTitle())) {
            existingTvShow.setTitle(updatedTvShow.getTitle());
        }

        if (!isNullOrEmpty(updatedTvShow.getGenre())) {
            existingTvShow.setGenre(updatedTvShow.getGenre());
        }

        if(updatedTvShow.getReleaseYear() !=0) {
            existingTvShow.setReleaseYear(updatedTvShow.getReleaseYear());
        }

        if (!isNullOrEmpty(updatedTvShow.getOverview())) {
            existingTvShow.setOverview(updatedTvShow.getOverview());
        }

        if (!isNullOrEmpty(updatedTvShow.getSmallPoster())) {
            existingTvShow.setSmallPoster(updatedTvShow.getSmallPoster());
        }

        if (!isNullOrEmpty(updatedTvShow.getLargePoster())) {
            existingTvShow.setLargePoster(updatedTvShow.getLargePoster());
        }

        if(updatedTvShow.getRentPrice() !=0) {
            existingTvShow.setRentPrice(updatedTvShow.getRentPrice());
        }

        if(updatedTvShow.getBuyPrice() !=0) {
            existingTvShow.setBuyPrice(updatedTvShow.getBuyPrice());
        }

        if(updatedTvShow.getFeatured() != null) {
            existingTvShow.setFeatured(updatedTvShow.getFeatured());
        }

        if(!isNullOrEmpty(updatedTvShow.getCast())) {
            existingTvShow.setCast(updatedTvShow.getCast());
        }

        if(!isNullOrEmpty(updatedTvShow.getDirector())) {
            existingTvShow.setDirector(updatedTvShow.getDirector());
        }

        if(!isNullOrEmpty(updatedTvShow.getReleaseDate())) {
            existingTvShow.setReleaseDate(updatedTvShow.getReleaseDate());
        }

        if(!isNullOrEmpty(updatedTvShow.getEpisodes())) {
            existingTvShow.setEpisodes(updatedTvShow.getEpisodes());
        }
            return tvShowRepository.save(existingTvShow);
    }

    public void delete(String id) {
        tvShowRepository.deleteById(id);
    }

    // Helper method for null/empty string check
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}