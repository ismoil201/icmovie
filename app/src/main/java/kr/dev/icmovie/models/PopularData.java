package kr.dev.icmovie.models;

import java.io.Serializable;

public class PopularData implements Serializable {

    int filmImage;

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    boolean isSaved = false;
    String name;
    String rating;
    String genre;
    String about;

    public PopularData(int filmImage, String name, String rating, String genre, String about) {
        this.filmImage = filmImage;
        this.name = name;
        this.rating = rating;
        this.genre = genre;
        this.about = about;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
