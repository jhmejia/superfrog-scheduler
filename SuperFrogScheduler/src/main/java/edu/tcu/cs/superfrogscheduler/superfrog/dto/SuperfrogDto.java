package edu.tcu.cs.superfrogscheduler.superfrog.dto;
import jakarta.validation.constraints.NotEmpty;

public record SuperfrogDto(int id, @NotEmpty(message = "Username is required") String username, @NotEmpty(message = "First name is required") String firstName, @NotEmpty(message = "Last name is required") String lastName, @NotEmpty(message = "Email is required") String email, 
@NotEmpty(message = "Password is required") String password, boolean active) {
    
}
