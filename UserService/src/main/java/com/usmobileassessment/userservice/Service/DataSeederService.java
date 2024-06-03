package com.usmobileassessment.userservice.Service;

import com.usmobileassessment.userservice.Model.User;
import com.usmobileassessment.userservice.Repository.UserRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeederService implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveDummyUsers();
    }

    private void saveDummyUsers() {
        if(userRepository.count() == 0) {
            Faker faker = new Faker();
            List<User> users = new ArrayList<>();

            for(int i = 0; i < 20; i++) {
                User user = User.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password())
                        .build();
                users.add(user);
            }
            userRepository.saveAll(users);
        }
    }
}
