package StreamX.DigitalVideoStoreBackend.controller;

import StreamX.DigitalVideoStoreBackend.model.TvShowModel;
import StreamX.DigitalVideoStoreBackend.service.TvShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tvshows")
public class TvShowController {

    private final TvShowService tvShowService;

    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    // Endpoint to create a new TV show
    @PostMapping
    public ResponseEntity<?> createTvShow(@RequestBody TvShowModel tvShow) {

        // Validate TV show input
        if (isNullOrEmpty(tvShow.getTitle()) ||
                isNullOrEmpty(tvShow.getGenre()) ||
                tvShow.getReleaseYear() == 0 ||
                isNullOrEmpty(tvShow.getOverview()) ||
                isNullOrEmpty(tvShow.getSmallPoster()) ||
                isNullOrEmpty(tvShow.getLargePoster()) ||
                tvShow.getRentPrice() == 0 ||
                tvShow.getBuyPrice() == 0 ||
                tvShow.getFeatured() == null || 
                isNullOrEmpty(tvShow.getCast()) ||
                isNullOrEmpty(tvShow.getDirector()) ||
                isNullOrEmpty(tvShow.getReleaseDate()) ||
                isNullOrEmpty(tvShow.getEpisodes())) {

            return ResponseEntity.badRequest().body(Map.of("message", "All fields are required and must be valid."));
        }
        return ResponseEntity.status(201).body(Map.of("message", "Tv show successfully created", "tvShow", tvShowService.create(tvShow)));
    }

    // Endpoint to get all TV shows
    @GetMapping
    public ResponseEntity<List<TvShowModel>> getAllTvShows() {
        return ResponseEntity.ok(tvShowService.getAll());
    }

    // Endpoint to search TV shows by title
    @GetMapping("/search")
    public ResponseEntity<List<TvShowModel>> searchTvShows(@RequestParam String title) {
        return ResponseEntity.ok(tvShowService.searchByTitle(title));
    }

    // Endpoint to get featured TV shows
    @GetMapping("/featured")
    public ResponseEntity<List<TvShowModel>> getFeaturedTvShows() {
        return ResponseEntity.ok(tvShowService.getFeatured());
    }

    // Endpoint to get a TV show by ID
    @GetMapping("/id")
    public ResponseEntity<TvShowModel> getTvShowById(@RequestParam String id) {
        return tvShowService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to update a TV show by ID
    @PutMapping("/id")
    public ResponseEntity<?> updateTvShow(@RequestParam String id, @RequestBody TvShowModel tvShow) {
        try {
            TvShowModel updatedTvShow = tvShowService.update(id, tvShow);
            return ResponseEntity.ok(Map.of("message", "TV show updated successfully", "tvShow", updatedTvShow));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Error updating TV show", "error", e.getMessage()));
        }
    }

    // Endpoint to delete a TV show by ID
    @DeleteMapping("/id")
    public ResponseEntity<?> deleteTvShow(@RequestParam String id) {
        tvShowService.delete(id);
        return ResponseEntity.ok(Map.of("message", "TV show deleted successfully"));
    }

    // Helper method for null/empty string check
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
