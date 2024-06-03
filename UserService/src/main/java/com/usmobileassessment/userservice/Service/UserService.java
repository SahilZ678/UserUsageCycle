package com.usmobileassessment.userservice.Service;

import com.usmobileassessment.userservice.Dto.UserRequestDTO;
import com.usmobileassessment.userservice.Dto.UserResponseDTO;
import com.usmobileassessment.userservice.Exception.UserNotFoundException;
import com.usmobileassessment.userservice.Model.User;
import com.usmobileassessment.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditService auditService;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email " + email));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        auditService.log("CREATE", user, user.getId());
        return userRepository.save(user);
    }

    public User updateUser(String userId, UserRequestDTO user) {
        auditService.log("UPDATE", user, userId);
        Optional<User> currentUser = userRepository.findById(userId);
        if(currentUser.isPresent()) {
            User userToUpdate= currentUser.get();
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            return userRepository.save(userToUpdate);
        }else {
            throw new UserNotFoundException("User not found with id " + userId);
        }
    }

    public UserResponseDTO convertToDto(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
