package kr.dev.icmovie.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    int id;
    int filmImage;


    String name;
    String rating;
    String genre;
    String description;
    boolean isSaved;

    int movie;

    public Movie(int filmImage,
                 String name, String rating, String genre,
                 String description, int movie,boolean isSaved) {
        this.filmImage = filmImage;
        this.name = name;
        this.rating = rating;
        this.genre = genre;
        this.description = description;
        this.isSaved = isSaved;
        this.movie = movie;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmImage() {
        return filmImage;
    }

    public void setFilmImage(int filmImage) {
        this.filmImage = filmImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }
}
