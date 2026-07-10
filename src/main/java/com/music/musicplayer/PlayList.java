package com.music.musicplayer;

import java.util.ArrayList;
import java.util.List;

public class PlayList {

     List<Track> trackArrayList ;

     private int currentIndex;

     public PlayList(){
         trackArrayList = new ArrayList<>();
         this.currentIndex =-1;

     }

     public void add(Track track){
         trackArrayList.add(track);
         if (currentIndex == -1){
             currentIndex=0;

         }

     }

    public List<Track> getTrackArrayList() {
        return trackArrayList;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }



    public void setTrackArrayList(List<Track> trackArrayList) {
        this.trackArrayList = trackArrayList;
    }


    public Track getCurrentTrack(){
         if (currentIndex>=0 && currentIndex<trackArrayList.size()){
             return trackArrayList.get(currentIndex);
         }
         return null;
    }

    public Track nextTrack(){
         if (trackArrayList.isEmpty()) return null;
         currentIndex = (currentIndex+1)% trackArrayList.size();
         return getCurrentTrack();
    }

    public Track previousTract(){
         if (trackArrayList.isEmpty()) return null;

         currentIndex = (currentIndex-1 + trackArrayList.size())%trackArrayList.size();
         return getCurrentTrack();
    }

    public void clear(){
         trackArrayList.clear();
         currentIndex = -1;
    }

    public void removeById(int index){

         trackArrayList.remove(index);
     }
     public void removeByObject(Track track){

         trackArrayList.remove(track);
     }
}
