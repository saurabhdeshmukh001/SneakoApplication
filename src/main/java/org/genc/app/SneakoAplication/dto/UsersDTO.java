package org.genc.app.SneakoAplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private Long id;

    @NotBlank(message = "Enter the user name")
    private String userName;

    @NotBlank(message = "Enter the email")
    @Email(message = "email should be in proper format")
    private String email;

    @NotBlank(message = "Enter the address")
    private String address;

    @NotNull(message = "Enter the phone number")
    private Long phoneNumber;

    // Required for INPUT (Registration)
    @NotNull(message = "Enter the password")
    @Size(min = 5, max = 72, message = "Password should have more than 5 characters")
    private String password;

    // Single role name for input (e.g., "ADMIN" or "USER")
    private String rolename;

    // Role names for output
    private List<String> roles;
}
