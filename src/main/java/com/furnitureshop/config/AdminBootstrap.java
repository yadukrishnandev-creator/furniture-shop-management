package com.furnitureshop.config;

import com.furnitureshop.user.CreateUserRequest;
import com.furnitureshop.user.Role;
import com.furnitureshop.user.UserRepository;
import com.furnitureshop.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.nio.charset.StandardCharsets;

@Configuration
@Profile("bootstrap-admin")
public class AdminBootstrap {

    @Bean
    public CommandLineRunner createFirstAdmin(
            UserRepository userRepository,
            UserService userService,
            Environment environment
    ) {
        return args -> {
            String username =
                    environment.getRequiredProperty("bootstrap.admin.username");

            String password =
                    environment.getRequiredProperty("bootstrap.admin.password");

            if (username.isBlank() || username.length() > 100) {
                throw new IllegalArgumentException("Invalid admin username");
            }

            if (password.isBlank()
                    || password.length() < 8
                    || password.getBytes(StandardCharsets.UTF_8).length > 72) {
                throw new IllegalArgumentException(
                        "Admin password must have at least 8 characters "
                                + "and at most 72 UTF-8 bytes"
                );
            }

            if (userRepository.existsByUsername(username)) {
                throw new IllegalStateException(
                        "Username already exists; no account was changed"
                );
            }

            userService.createUser(new CreateUserRequest(
                    username,
                    password,
                    Role.ADMIN,
                    null
            ));
        };
    }
}