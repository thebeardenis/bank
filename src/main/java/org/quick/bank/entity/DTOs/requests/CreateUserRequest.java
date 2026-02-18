package org.quick.bank.entity.DTOs.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "Email must be.")
    @Email(message = "Incorrect email format.")
    @Size(max = 200, message = "Email shouldn't have more than 200 symbols.")
    private String email;

    @NotBlank(message = "Password must be.")
    @Size(min = 8, max = 50, message = "Password must have 8-50 symbols.")
    private String password;

    @NotBlank(message = "Name must be.")
    @Size(min = 2, max = 50, message = "Name must have 2-50 symbols.")
    private String name;
}
