package com.usmobileassessment.cycleservice.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
