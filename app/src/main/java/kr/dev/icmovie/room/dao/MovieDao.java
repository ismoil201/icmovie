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

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    Movie getMovieById(int id);

    @Query("SELECT COUNT(*) FROM movies")
    int getCount();

    // 🔽 isSaved = 0 qilib barchasini tozalash
    @Query("UPDATE movies SET isSaved = 0")
    void clearAllSavedMovies();

    // 🔽 Faqat bittasini isSaved = 1 qilish
    @Query("UPDATE movies SET isSaved = 1 WHERE id = :movieId")
    void saveMovie(int movieId);
}
