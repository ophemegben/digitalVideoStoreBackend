package StreamX.DigitalVideoStoreBackend.repository;

import StreamX.DigitalVideoStoreBackend.model.MovieModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<MovieModel, String> {
    List<MovieModel> findByTitleContainingIgnoreCase(String title);
    List<MovieModel> findByFeatured(boolean featured);
}