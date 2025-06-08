package kr.dev.icmovie.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "musics")
public class Music {

    @PrimaryKey(autoGenerate = true)
    int id;
    int musicImage;
    String  accountName;
    String viewNum;

    String musicName;
    int accountImage;

    int musicVideo;
    boolean isSaved;




    public Music( int musicImage, String accountName, String viewNum,
                  String musicName, int accountImage, int musicVideo, boolean isSaved) {
        this.musicImage = musicImage;
        this.accountName = accountName;
        this.viewNum = viewNum;
        this.musicName = musicName;
        this.accountImage = accountImage;
        this.musicVideo = musicVideo;
        this.isSaved = isSaved;
    }


    public boolean isSaved() {
        return isSaved;
    }


    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMusicVideo() {
        return musicVideo;
    }

    public void setMusicVideo(int musicVideo) {
        this.musicVideo = musicVideo;
    }

    public int getMusicImage() {
        return musicImage;
    }

    public void setMusicImage(int musicImage) {
        this.musicImage = musicImage;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(int accountImage) {
        this.accountImage = accountImage;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }



    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }
}
