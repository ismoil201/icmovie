package kr.dev.icmovie.room.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kr.dev.icmovie.room.entity.Movie;

@Dao
public interface MovieDao {

    @Insert
    void insertMovie(Movie movie);


    @Query("select * from movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    Movie getMovieById(int id);

    @Query("SELECT COUNT(*) FROM movies")
    int getCount();


}
