package com.music.musicplayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerEngine1 {

    private MediaPlayer mediaPlayer;

    public void loadTrack(Track track){
        if (mediaPlayer!=null){
            mediaPlayer.stop();//// Stop any existing song before starting a new one
        }

        Media media = new Media(track.getFilePath());
        mediaPlayer = new MediaPlayer(media);
    }

    public void play(){
        if (mediaPlayer!=null) mediaPlayer.play();
    }

    public void stop(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    public void pause(){
        if (mediaPlayer!=null) mediaPlayer.pause();
    }


    public void setOnEndOfMedia(Runnable action){
        if (mediaPlayer!=null){
            mediaPlayer.setOnEndOfMedia(action);
        }
    }
}
