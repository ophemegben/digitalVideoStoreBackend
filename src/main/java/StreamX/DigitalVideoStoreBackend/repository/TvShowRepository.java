package StreamX.DigitalVideoStoreBackend.repository;

import StreamX.DigitalVideoStoreBackend.model.TvShowModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TvShowRepository extends MongoRepository<TvShowModel, String> {
    List<TvShowModel> findByTitleContainingIgnoreCase(String title);
    List<TvShowModel> findByFeatured(boolean featured);
}