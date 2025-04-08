package StreamX.DigitalVideoStoreBackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
public class MovieModel {
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
    private boolean featured; 
    private MovieDetails details; // Additional details about the movie e.g., director, cast, releaseDate.
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class MovieDetails {
    private String cast;
    private String director;
    private String releaseDate;
    private String awards;
}
