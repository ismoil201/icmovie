package kr.dev.icmovie.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import kr.dev.icmovie.room.dao.MovieDao;
import kr.dev.icmovie.room.dao.MusicDao;
import kr.dev.icmovie.room.entity.Movie;
import kr.dev.icmovie.room.entity.Music;

@Database(entities = {Movie.class, Music.class}, version = 4)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;

    public  abstract MovieDao movieDao();
    public  abstract MusicDao musicDao();

    public static synchronized AppDataBase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
