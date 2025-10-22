package org.genc.app.SneakoAplication.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
// NOTE: Removed @AllArgsConstructor because it conflicts with custom constructors
// and the desire to manage which fields are present for input vs. output.
public class UsersDTO {

    @Nullable
    private Long userID;

    @NotBlank(message = "Enter the user name")
    private String userName;

    @NotBlank(message = "Enter the email")
    @Email(message = "email should be in proper format")
    private String email;

    @NotBlank(message = "Enter the address")
    private String address;

    @NotNull(message = "Enter the phone number")
    // Note: @Size is for Collections/Arrays, typically inappropriate for Long.
    // Assuming you have custom validation or are using it for constraints.
    private Long phoneNumber;

    // Required for INPUT (Registration)
    @NotNull(message = "Enter the password")
    @Size(min = 5,max = 15,message = "Password should have more than 5 Character")
    private String password;

    // Required for INPUT (Registration)
    @NotNull(message = "Select the role")
    private String rolename;

    // Required for OUTPUT (Response) - Will be null/empty on input
    private List<RolesDTO> roles;


    public UsersDTO(Long userID, String userName, String email, String address,String password,
                    Long phoneNumber, String rolename,List<RolesDTO> roles) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.password=password;
        this.phoneNumber = phoneNumber;
        this.rolename=rolename;
        this.roles = roles;
    }


}