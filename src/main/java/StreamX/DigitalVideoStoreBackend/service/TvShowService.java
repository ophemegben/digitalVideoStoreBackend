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

    public TvShowModel update(String id, TvShowModel tvShow) {
        tvShow.setId(id);
        return tvShowRepository.save(tvShow);
    }

    public void delete(String id) { 
        tvShowRepository.deleteById(id); 
    }
}