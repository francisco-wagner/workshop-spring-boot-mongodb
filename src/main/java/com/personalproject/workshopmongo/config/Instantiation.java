package com.personalproject.workshopmongo.config;

import com.personalproject.workshopmongo.domain.Post;
import com.personalproject.workshopmongo.domain.User;
import com.personalproject.workshopmongo.dto.AuthorDTO;
import com.personalproject.workshopmongo.repositories.PostRepository;
import com.personalproject.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null,
                            sdf.parse("21/03/2018"),
                            "Partiu Viagem",
                            "Vou viajar para São Paulo. Abraços!",
                             new AuthorDTO(maria));

        Post post2 = new Post(null,
                            sdf.parse("23/03/2018"),
                            "Bom dia",
                            "Acordei Feliz hoje!",
                            new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);

    }
}
