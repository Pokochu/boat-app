package com.simple.boat.config;

import com.simple.boat.model.Boat;
import com.simple.boat.model.UserEntity;
import com.simple.boat.repository.BoatRepository;
import com.simple.boat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initStartupData(UserRepository userRepository, BoatRepository boatRepository) {
        return args -> {
            if (userRepository.findByUsername("user").isEmpty()) {
                UserEntity user = new UserEntity();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRole("USER");
                userRepository.save(user);
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            if (boatRepository.findAll().isEmpty()) {
                Boat speedyGonzalez = Boat.builder()
                        .name("Speedy Gonzalez")
                        .description("Fast as speed of light")
                        .build();
                Boat titanic = Boat.builder()
                        .name("Titanic")
                        .description("Won't make it till the end")
                        .build();
                Boat blackPearl = Boat.builder()
                        .name("Black Pearl")
                        .description("You don't want to meet with this one")
                        .build();
                boatRepository.save(speedyGonzalez);
                boatRepository.save(titanic);
                boatRepository.save(blackPearl);
            }
        };
    }
}
