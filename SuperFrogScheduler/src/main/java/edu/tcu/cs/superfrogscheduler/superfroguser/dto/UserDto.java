package edu.tcu.cs.superfrogscheduler.superfroguser.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(Integer id,


                      @NotEmpty(message = "username is required.")
                      String username,
                      boolean active,
                      @NotEmpty(message = "roles are required.")
                      String roles) {
}