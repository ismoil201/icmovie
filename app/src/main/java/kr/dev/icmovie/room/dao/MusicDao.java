package kr.dev.icmovie.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kr.dev.icmovie.room.entity.Music;

@Dao
public interface MusicDao {

    @Insert
    void insertMusic(Music music);

    @Query("select * from musics")
    List<Music> getAllMusics();
}
