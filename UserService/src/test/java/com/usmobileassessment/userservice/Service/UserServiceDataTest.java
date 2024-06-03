package com.usmobileassessment.userservice.Service;

import com.usmobileassessment.userservice.Configuration.EmbeddedMongoConfig;
import com.usmobileassessment.userservice.Dto.UserRequestDTO;
import com.usmobileassessment.userservice.Exception.UserNotFoundException;
import com.usmobileassessment.userservice.Model.User;
import com.usmobileassessment.userservice.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import({EmbeddedMongoConfig.class, UserService.class})
class UserServiceDataTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll(); // Clean the database before each test

        // Create and save some mock data
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        userRepository.save(user);
    }

    @Test
    void createUser_Success() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password456");

        User result = userService.createUser(user);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("jane.doe@example.com", result.getEmail());
    }

    @Test
    void updateUser_Success() {
        Optional<User> existingUserOpt = userRepository.findByEmail("john.doe@example.com");
        assert existingUserOpt.isPresent();
        User existingUser = existingUserOpt.get();
        existingUser.setFirstName("JohnUpdated");
        existingUser.setLastName("DoeUpdated");
        existingUser.setEmail("john.updated@example.com");

        UserRequestDTO requestDTO = UserRequestDTO.builder()
                .firstName(existingUser.getFirstName())
                .lastName(existingUser.getLastName())
                .email(existingUser.getEmail())
                .build();

        User result = userService.updateUser(existingUser.getId(), requestDTO);

        assertEquals("JohnUpdated", result.getFirstName());
        assertEquals("DoeUpdated", result.getLastName());
        assertEquals("john.updated@example.com", result.getEmail());
    }

    @Test
    void updateUser_NotFound() {
        User user = new User();
        user.setFirstName("NonExistent");
        user.setLastName("User");
        user.setEmail("nonexistent@example.com");
        user.setPassword("password789");

        UserRequestDTO requestDTO = UserRequestDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser("invalid_user_id", requestDTO);
        });
    }
}
