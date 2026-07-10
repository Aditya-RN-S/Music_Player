package com.music.musicplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


import java.io.File;

public class PlayerEngine extends Application {

    MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play button");
        Button stopButton = new Button("Stop Button");
        Button pauseButton = new Button("Pause Button");

        String musicFilePath = "song.mp3";

        try{
            Media media = new Media(new File(musicFilePath).toURI().toString());

            mediaPlayer = new MediaPlayer(media);

        }catch (Exception e){
            System.out.println("Error loading media : make sure 'song.mp3 exists!" );
            e.printStackTrace();
        }

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.play();
                System.out.println("playing music...");
            }
        });

        pauseButton.setOnAction(event->{
            mediaPlayer.pause();
        });

        stopButton.setOnAction(e ->{
            mediaPlayer.stop();
            System.out.println("stopped");
        });

        root.getChildren().add(playButton);
        root.getChildren().add(pauseButton);
        root.getChildren().add(stopButton);

        Scene scene = new Scene(root ,400,300);

        stage.setTitle("Javafx music player ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
