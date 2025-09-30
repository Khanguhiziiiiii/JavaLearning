package org.example;

public class Song {
    private String title;
    private String artist;
    private String album;
    private int minutes;
    private int seconds;

    public Song(String title, String artist, String album, int minutes, int seconds) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getTitle() {return title;}
    public String getArtist() {return artist;}
    public String getAlbum() {return album;}
    public String getDuration() {
        return minutes + ":" + seconds;}

    @Override
    public String toString() {
        return title + " - " + artist + " - " + album + " - " + getDuration();
    }
}
