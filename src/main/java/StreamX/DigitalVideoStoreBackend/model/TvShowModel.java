package StreamX.DigitalVideoStoreBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tvshows")
public class TvShowModel {
    @Id
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private String overview;
    private String smallPoster;
    private String largePoster;
    private double rentPrice;
    private double buyPrice;
    private Boolean featured;
    private String cast;
    private String director;
    private String releaseDate;
    private String episodes;
}