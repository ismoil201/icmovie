package kr.dev.icmovie.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kr.dev.icmovie.room.entity.Music;

@Dao
public interface MusicDao {

    @Insert
    void insertMusic(Music music);

    @Query("select * from musics")
    List<Music> getAllMusics();


    @Query("select * from musics where isSaved = 1")
    List<Music> getAllSavedMusic();

    @Query("UPDATE musics SET isSaved = 1 WHERE id = :musicId")
    void saveMusic(int musicId);
    @Update
    void updateMusic(Music music);


}
