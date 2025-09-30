package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

public  class Playlist {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {return name;}
    public ArrayList<Song> getSongs() {return songs;}

    public void addSong(Song song) {
        songs.add(song);
        saveToFile(song);
        System.out.println(song+"added to playlist");
    }

    public void saveToFile(Song song) {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("playlist.txt",true))) {
            bw.write(song.getTitle()+" | "+song.getArtist()+" | "+song.getAlbum()+" | "+song.getDuration());
            bw.newLine();
        }catch(IOException e){
            System.out.println("Error in saving song to file" + e.getMessage());
        }
    }

    public void displaySongs() {
        System.out.println("\nPlaylist: " + name);
        if(songs.isEmpty()){
            System.out.println("No songs in this playlist");
        }else {
            int i=1;
            for (Song song : songs) {
                System.out.println(i++ + ". " + song);
            }
        }
    }

    public void searchSong(String keyword) {
        boolean found = false;
        for(Song song : songs) {
            if(song.getTitle().toLowerCase().contains(keyword.toLowerCase()) || song.getArtist().toLowerCase().contains(keyword.toLowerCase()) || song.getAlbum().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(song + "found in playlist" + name);
                found = true;
            }
        }
        if(!found) {
            System.out.println("No songs in this playlist");
        }
    }
}