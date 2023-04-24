package edu.tcu.cs.superfrogscheduler.superfroguser.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(Integer id,
                      @NotEmpty(message = "email is required.")
                      String email,

                      String username,
                      @jakarta.validation.constraints.NotEmpty(message = "username is required.") boolean active,
                      @NotEmpty(message = "roles are required.")
                      String roles) {
}