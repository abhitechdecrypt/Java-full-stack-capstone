package com.emovies.userMS.Entity;

import com.emovies.userMS.Util.UserRole;
import com.emovies.userMS.Util.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo {

    @Id
    private String userID;

    @NotBlank(message = "User Name can't be Empty")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password Field can't be Empty")
    private String password;

    @Email(message = "Please Enter the valid Email Id")
    @NotBlank(message = "Email Field can't be empty")
    private String email;

    @NotBlank(message = "Phone Number Filed can't be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Please enter a valid 10-digit mobile number")
    private String phoneNumber;

    @NotBlank(message = "Please Enter the address field")
    private String address;

    @NotNull(message = "Please enter the date of Birth")
    @Past
    private LocalDateTime dateOfBirth;

    @NotNull(message = "Please Select the user Role") // Changed to NotNull
    private UserRole userRole;

    @NotNull(message = "Please Select the user account Status") // Changed to NotNull
    private UserStatus accountStatus;

    private String profilePicture;

}
