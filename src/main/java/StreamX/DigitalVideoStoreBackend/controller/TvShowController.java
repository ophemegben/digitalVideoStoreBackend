package StreamX.DigitalVideoStoreBackend.controller;

import StreamX.DigitalVideoStoreBackend.model.TvShowModel;
import StreamX.DigitalVideoStoreBackend.service.TvShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tvshows")
public class TvShowController {

    private final TvShowService tvShowService;

    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    // Endpoint to create a new TV show
    @PostMapping
    public ResponseEntity<TvShowModel> createTvShow(@RequestBody TvShowModel tvShow) {
        return ResponseEntity.status(201).body(tvShowService.create(tvShow));
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
    public ResponseEntity<TvShowModel> updateTvShow(@RequestParam String id, @RequestBody TvShowModel tvShow) {
        return ResponseEntity.ok(tvShowService.update(id, tvShow));
    }

    // Endpoint to delete a TV show by ID
    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteTvShow(@RequestParam String id) {
        tvShowService.delete(id);
        return ResponseEntity.noContent().build();
    }
}