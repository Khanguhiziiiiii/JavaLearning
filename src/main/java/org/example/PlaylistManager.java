package org.example;

import java.util.Scanner;

public class PlaylistManager {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Library library = new Library();

        boolean exit=false;

        while(!exit){
            System.out.println("\n ----------Main Menu----------");
            System.out.println("1. Add Song to Library");
            System.out.println("2. Display All Songs in Library");
            System.out.println("3. Search Songs in Library");
            System.out.println("4. Delete Song from Library");
            System.out.println("5. Display All Playlists in Library");
            System.out.println("6. Create New Playlist");
            System.out.println("7. Add Song to Playlist");
            System.out.println("8. Display All Songs in Playlist");
            System.out.println("9. Search Song from Playlist");
            System.out.println("10. Delete Song from Playlist");
            System.out.println("11. Exit");
            System.out.println("Enter your choice: ");

            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1://Add Song to Library
                    System.out.println("Enter Song name: ");
                    String name=sc.nextLine();
                    System.out.println("Enter Artist name: ");
                    String artist=sc.nextLine();
                    System.out.println("Enter Album name: ");
                    String album=sc.nextLine();
                    System.out.println("Enter Song duration in minutes: ");
                    int minutes=sc.nextInt();
                    System.out.println("Enter Song duration in seconds: ");
                    int seconds=sc.nextInt();
                    sc.nextLine();

                    Song song=new Song(name,artist,album,minutes,seconds);
                    library.addSong(song);
                    break;

                case 2://Display All Songs in library
                    library.displayAllSongs();
                    break;

                case 3://Search songs in library
                    System.out.println("Enter keyword to search: ");
                    String keyword=sc.nextLine();
                    library.searchSongsInLibrary(keyword);
                    break;

                case 4://Delete song from library
                    System.out.println("Enter keyword to search song to delete: ");
                    String deleteFromLibraryKeyword=sc.nextLine();
                    library.deleteSongFromLibrary(deleteFromLibraryKeyword);
                    break;

                case 5://display playlists in library
                    library.displayAllPlaylists();
                    break;

                case 6://create new playlists
                    System.out.println("Enter playlist name: ");
                    String playlistName=sc.nextLine();
                    library.createPlaylist(playlistName);
                    break;

                case 7://add song to playlist
                    System.out.println("Enter playlist name: ");
                    String playlistName2=sc.nextLine();
                    Playlist playlist2=library.getPlaylist(playlistName2);

                    if(playlist2==null){
                        System.out.println("Playlist not found!");
                    }else{
                        System.out.println("Enter song title:");
                        String songTitle=sc.nextLine();
                        Song songFromLibrary=library.findSongByTitle(songTitle);
                        if(songFromLibrary==null){
                            System.out.println("Song not found!");
                        }else{
                            playlist2.addSong(songFromLibrary);
                            System.out.println("Song added successfully!");
                        }
                    }
                    break;

                case 8://display all songs in playlist
                    library.displayAllPlaylists();
                    break;

                case 9://search song from playlist
                    System.out.println("Enter playlist name: ");
                    String playlistName3=sc.nextLine();
                    Playlist playlist3=library.getPlaylist(playlistName3);
                    if(playlist3==null){
                        System.out.println("Playlist not found!");
                    }else{
                        System.out.println("Enter keyword to search: ");
                        String playlistKeyword=sc.nextLine();
                        playlist3.searchSong(playlistKeyword);
                    }
                    break;

                case 10://delete song from playlist
                    System.out.println("Enter playlist name: ");
                    String playlistName4=sc.nextLine();
                    Playlist delPlaylist=library.getPlaylist(playlistName4);
                    if(delPlaylist==null){
                        System.out.println("Playlist not found!");
                    }else{
                        System.out.println("Enter song title to delete from playlist: :");
                        String songTitle=sc.nextLine();
                        library.deleteSongFromPlaylist(songTitle);
                    }
                    break;

                case 11://exit
                    exit=true;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}
