package com.music.musicplayer;

import java.io.File;

public class Track {

    String title;
    String artist;
    String duration;
    String filePath;


    public Track(File file) {
        this.title = file.getName().replace(".mp3","").replace(".wav","").replace(".m4a","");
        this.artist = "Unknown Artist";
        this.filePath = file.toURI().toString();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title+" - "+artist;
    }
}
