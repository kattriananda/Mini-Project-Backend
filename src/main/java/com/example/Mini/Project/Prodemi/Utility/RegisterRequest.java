package com.example.Mini.Project.Prodemi.Utility;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotEmpty(message = "FullName must not be empty")
    @NotNull(message = "FullName must not be null")
    private String fullName;

    @NotEmpty(message = "Address must not be empty")
    @NotNull(message = "Address must not be null")
    private String address;

    private Integer age;

    @Email (message = "Email must be a well-formed email address")
    @NotNull(message = "Email must not be null")
    @NotEmpty (message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @NotNull (message = "Password must not be null")
    //@PasswordValidation
    private String password;
}
