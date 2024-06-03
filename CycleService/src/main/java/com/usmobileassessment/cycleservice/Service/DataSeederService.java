package com.usmobileassessment.cycleservice.Service;

import com.usmobileassessment.cycleservice.Client.UserClient;
import com.usmobileassessment.cycleservice.Dto.UserDTO;
import com.usmobileassessment.cycleservice.Model.Cycle;
import com.usmobileassessment.cycleservice.Repository.CycleRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataSeederService implements CommandLineRunner {

    @Autowired
    private CycleRepository cycleRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public void run(String... args) throws Exception {
        seedCycles();
    }

    public void seedCycles() {
        if(cycleRepository.count() == 0) {
            Faker faker = new Faker();
            List<Cycle> cycles = new ArrayList<>();
            List<UserDTO> users = userClient.getAllUsers();
            for(UserDTO user : users) {
                String mdnForCurrentUser = faker.phoneNumber().phoneNumber();
                for(int i = 0; i < 2; i++) {
                    LocalDate startLocalDate = LocalDate.now().minusMonths(i + 1);
                    LocalDate endLocalDate = startLocalDate.plusMonths(1).minusDays(1);
                    Date startDate = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date endDate = Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Cycle cycle = Cycle.builder()
                            .mdn(mdnForCurrentUser)
                            .startDate(startDate)
                            .endDate(endDate)
                            .userId(user.getId())
                            .build();
                    cycles.add(cycle);
                }
            }
            cycleRepository.saveAll(cycles);
        }
    }
}
