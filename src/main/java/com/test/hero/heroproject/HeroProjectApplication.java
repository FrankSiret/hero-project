package com.test.hero.heroproject;

import com.test.hero.heroproject.domain.Authority;
import com.test.hero.heroproject.domain.User;
import com.test.hero.heroproject.repository.AuthorityRepository;
import com.test.hero.heroproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class HeroProjectApplication {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    HeroProjectApplication(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HeroProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {

        };
    }

}
