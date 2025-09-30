package org.example;

import java.util.ArrayList;

public class Library {
    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;

    public Library() {
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
        System.out.println(song.getTitle()+ " added to library");
    }

    public void displayAllSongs() {
        if(songs.isEmpty()) {
            System.out.println("No songs in the library");
        }else{
            System.out.println("Songs in the library:");
            int i=1;
            for(Song song : songs) {
                System.out.println(i++ +". "+song);
            }
        }
    }

    public void searchSongsInLibrary(String keyword) {
        boolean found = false;
        for(Song song : songs) {
           if(song.getTitle().toLowerCase().contains(keyword.toLowerCase()) || song.getArtist().toLowerCase().contains(keyword.toLowerCase()) || song.getAlbum().toLowerCase().contains(keyword.toLowerCase())) {
               System.out.println(song.getTitle()+ " found in library");
               found = true;
           }
        }
        if(!found) {
            System.out.println("No songs in this library");
        }
    }

    public Song findSongByTitle(String title) {
        for(Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    public boolean deleteSongFromLibrary(String title) {
        Song song = findSongByTitle(title);
        if(song != null) {
            songs.remove(song);
            System.out.println(song.getTitle()+ " removed from library");
            return true;
        }else{
            System.out.println("Song not found in this library");
            return false;
        }
    }

    public void createPlaylist(String name) {
        playlists.add(new Playlist(name));
        System.out.println("Playlist " + name + " created in library");
    }

    public Playlist getPlaylist(String name) {
        for(Playlist playlist : playlists) {
            if(playlist.getName().equalsIgnoreCase(name)) {
                return playlist;
            }
        }
        return null;
    }

    public void displayAllPlaylists() {
        if(playlists.isEmpty()) {
            System.out.println("No playlists in the library");
        }else{
            System.out.println("Playlists in the library:");
            for(Playlist playlist : playlists) {
                System.out.println(playlist.getName());
            }
        }
    }

    public boolean deleteSongFromPlaylist(String title) {
        for(Song song : songs) {
            if(song.getTitle().equalsIgnoreCase(title)) {
                songs.remove(song);
                System.out.println(song.getTitle()+ " removed from playlist ");
                return true;
            }
        }
        return false;
    }
}
