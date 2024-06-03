package com.usmobileassessment.userservice.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
