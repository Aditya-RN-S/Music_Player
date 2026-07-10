package com.music.musicplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Ui  extends Application {

    private PlayList playList = new PlayList();
    private PlayerEngine1 playerEngine1 = new PlayerEngine1();

    //Ui Element
    private Label nowPlayingLabel = new Label("No track loaded");
    private ListView<Track> playListView  = new ListView<>();

    private Button playButton = new Button("▶ Play");
    private Button pauseButton = new Button("▶ pause");
    private Button stopButton  = new Button("⏸ stop");
    private Button nextButton = new Button("⏭ Next");
    private Button prevButton = new Button("⏮ Prev");
    private Button importButton =  new Button("📂 Import ");
    private Label  banner =  new Label(" Your playList: ");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Music Player");



        importButton.setOnAction(e->{

            FileChooser fileChooser = new FileChooser();

            List<File> selectedFiles =  fileChooser.showOpenMultipleDialog(stage);


             if (selectedFiles!= null){
                 for (File file: selectedFiles){
                     Track track = new Track(file);
                     playList.add(track);
                     playListView.getItems().add(track);
                 }

                 if (playList.getCurrentTrack()!= null && nowPlayingLabel.getText().equals("No track loaded")){
                    playTrack(playList.getCurrentTrack());
                 }
             }
        });

        playListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount()==2){
                    Track selectedTrack = playListView.getSelectionModel().getSelectedItem();
                    if (selectedTrack!= null){
                        int index = playListView.getSelectionModel().getSelectedIndex();
                        playList.setCurrentIndex(index);
                        playTrack(selectedTrack);
                    }
                }
            }
        });



        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                playerEngine1.play();
            }
        });

        pauseButton.setOnAction(e-> playerEngine1.pause());

        stopButton.setOnAction(e->playerEngine1.stop());

        nextButton.setOnAction(e-> {
           Track next = playList.nextTrack();
            if(next != null ) playTrack(next);
        });

        prevButton.setOnAction(e->{
            Track prev = playList.previousTract();
            if (prev!= null) playTrack(prev);
        });


        //layout interface
        String buttonStyle = "-fx-background-color:#ffffff; -fx-font-size: 12px; -fx-text-fill : black; -fx-background-radius: 100;";

        importButton.setStyle("-fx-background-color: #14e33a; -fx-text-fill: white; -fx-font-weight: bold;");
        banner.setStyle("-fx-background-color:#14e33a; -fx-text-fill: white; -fx-font-weight : bold; -fx-font-size:18px;");
        banner.setAlignment(Pos.CENTER);
        playButton.setStyle("-fx-background-color: #ffffff ; -fx-text-fill : black;-fx-font-weight: bold;-fx-background-radius: 100;");
        stopButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;-fx-background-radius: 100;");
        pauseButton.setStyle(buttonStyle);
        nextButton.setStyle(buttonStyle);
        prevButton.setStyle(buttonStyle);

        HBox controlPanel = new HBox(15,prevButton,playButton,pauseButton,stopButton);
        controlPanel.getChildren().add(nextButton);

        controlPanel.setAlignment(Pos.CENTER);

        VBox mainlayout = new VBox(15,importButton , banner,playListView,nowPlayingLabel,controlPanel);
        mainlayout.setAlignment(Pos.CENTER);
        mainlayout.setStyle("-fx-padding: 20; -fx-background-color:#022e0a;");

        Scene scene = new Scene(mainlayout , 450 ,450);
        stage.setScene(scene);
        stage.show();

    }

    private void playTrack(Track track){

        playerEngine1.loadTrack(track);
        playListView.getSelectionModel().select(track);
        nowPlayingLabel.setText("Now Playing: "+ track.getTitle());
        playerEngine1.play();

        playerEngine1.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                Track next = playList.nextTrack();
                if (next!= null){
                    playTrack(next);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
