package com.usmobileassessment.dailyusageservice.Service;

import com.usmobileassessment.dailyusageservice.Client.CycleClient;
import com.usmobileassessment.dailyusageservice.Dto.CycleDto;
import com.usmobileassessment.dailyusageservice.Model.DailyUsage;
import com.usmobileassessment.dailyusageservice.Repository.DailyUsageRepository;
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
    private DailyUsageRepository dailyUsageRepository;

    @Autowired
    private CycleClient cycleClient;

    @Override
    public void run(String... args) throws Exception {
        seedDailyUsages();
    }

    public void seedDailyUsages() {
        if (dailyUsageRepository.count() == 0) {
            Faker faker = new Faker();
            List<DailyUsage> dailyUsages = new ArrayList<>();
            List<CycleDto> cycles = cycleClient.getAllCycles();
            for (CycleDto cycle : cycles) {
                LocalDate startLocalDate = cycle.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endLocalDate = cycle.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate usageDate = startLocalDate;
                while(!usageDate.isAfter(endLocalDate)) {
                    Date usageDateAsDate = Date.from(usageDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Double usedInMb = (double) faker.number().numberBetween(1, 500);
                    DailyUsage dailyUsage = DailyUsage.builder()
                            .mdn(cycle.getMdn())
                            .userId(cycle.getUserId())
                            .usageDate(usageDateAsDate)
                            .usedInMb(usedInMb)
                            .build();
                    dailyUsages.add(dailyUsage);
                    usageDate = usageDate.plusDays(1);
                }
            }
            dailyUsageRepository.saveAll(dailyUsages);
        }
    }
}
