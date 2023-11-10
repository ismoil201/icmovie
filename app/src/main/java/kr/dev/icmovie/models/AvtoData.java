package kr.dev.icmovie.models;

public class AvtoData {
    int musicImage;
    String  accountName;
    String viewNum;

    String musicName;
    int accountImage;



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

    public AvtoData(int image, String accountName, String viewNum, String musicName, int accountImage) {
        this.musicImage = image;
        this.accountName = accountName;
        this.viewNum = viewNum;
        this.musicName = musicName;
        this.accountImage = accountImage;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }
}
