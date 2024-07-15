package com.mycinemalist;

import com.mycinemalist.entity.Movie;
import com.mycinemalist.entity.MovieList;
import com.mycinemalist.entity.User;
import com.mycinemalist.repository.MovieListRepository;
import com.mycinemalist.repository.MovieRepository;
import com.mycinemalist.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final MovieListRepository movieListRepository;
    private final PasswordEncoder passwordEncoder; // Add PasswordEncoder as a dependency

    private final String PASSWORD = "admin";

    public DataLoader(UserRepository userRepository, MovieRepository movieRepository, MovieListRepository movieListRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.movieListRepository = movieListRepository;
        this.passwordEncoder = passwordEncoder; // Initialize PasswordEncoder
    }

    @Override
    public void run(String... args) throws Exception {
        loadTestUsers();
        loadTestMovies();
        loadTestMovieLists();
    }

    private void loadTestUsers() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setUsername("testuser1");
            user1.setPassword(passwordEncoder.encode(PASSWORD)); // Encode password
            user1.setEmail("testuser1@example.com");
            user1.setFriends(Arrays.asList("testuser2", "testuser3"));

            User user2 = new User();
            user2.setUsername("testuser2");
            user2.setPassword(passwordEncoder.encode(PASSWORD)); // Encode password
            user2.setEmail("testuser2@example.com");
            user2.setFriends(Arrays.asList("testuser1"));

            User user3 = new User();
            user3.setUsername("testuser3");
            user3.setPassword(passwordEncoder.encode(PASSWORD)); // Encode password
            user3.setEmail("testuser3@example.com");
            user3.setFriends(Arrays.asList("testuser1"));

            userRepository.saveAll(Arrays.asList(user1, user2, user3));
        }
    }

    private void loadTestMovies() {
        if (movieRepository.count() == 0) {
            Movie movie1 = new Movie();
            movie1.setTitle("testmovie1");
            movie1.setDescription("Description for testmovie1");
            movie1.setLength("120");
            movie1.setDate("2023-01-01");
            movie1.setGenres(Arrays.asList("Genre1"));
            movie1.setActors(Arrays.asList("Actor1"));
            movie1.setDirectors(Arrays.asList("Director1"));
            movie1.setPosterUrl("http://example.com/testmovie1.jpg");

            Movie movie2 = new Movie();
            movie2.setTitle("testmovie2");
            movie2.setDescription("Description for testmovie2");
            movie2.setLength("130");
            movie2.setDate("2023-02-01");
            movie2.setGenres(Arrays.asList("Genre2"));
            movie2.setActors(Arrays.asList("Actor2"));
            movie2.setDirectors(Arrays.asList("Director2"));
            movie2.setPosterUrl("http://example.com/testmovie2.jpg");

            Movie movie3 = new Movie();
            movie3.setTitle("testmovie3");
            movie3.setDescription("Description for testmovie3");
            movie3.setLength("140");
            movie3.setDate("2023-03-01");
            movie3.setGenres(Arrays.asList("Genre3"));
            movie3.setActors(Arrays.asList("Actor3"));
            movie3.setDirectors(Arrays.asList("Director3"));
            movie3.setPosterUrl("http://example.com/testmovie3.jpg");

            movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));
        }
    }

    private void loadTestMovieLists() {
        if (movieListRepository.count() == 0) {
            MovieList list1 = new MovieList();
            list1.setName("testmovielist1");
            list1.setCreatedByUserId("testuser1");
            list1.setMovieIds(Arrays.asList("testmovie1", "testmovie2", "testmovie3"));
            list1.setSharedWith(Arrays.asList("testuser2", "testuser3"));

            MovieList list2 = new MovieList();
            list2.setName("testmovielist2");
            list2.setCreatedByUserId("testuser2");
            list2.setMovieIds(Arrays.asList("testmovie2", "testmovie3"));
            list2.setSharedWith(Arrays.asList("testuser1"));

            MovieList list3 = new MovieList();
            list3.setName("testmovielist3");
            list3.setCreatedByUserId("testuser3");
            list3.setMovieIds(Arrays.asList("testmovie1"));
            list3.setSharedWith(Arrays.asList("testuser1"));

            movieListRepository.saveAll(Arrays.asList(list1, list2, list3));
        }
    }
}
