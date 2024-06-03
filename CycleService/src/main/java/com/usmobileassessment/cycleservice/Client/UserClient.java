package com.usmobileassessment.cycleservice.Client;

import com.usmobileassessment.cycleservice.Dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "UserService")
public interface UserClient {
    @GetMapping("/")
    List<UserDTO> getAllUsers();
}
