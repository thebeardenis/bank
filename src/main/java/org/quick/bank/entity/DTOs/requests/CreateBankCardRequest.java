package org.quick.bank.entity.DTOs.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBankCardRequest {

    @NotBlank(message = "Name must be.")
    @Size(min = 2, max = 50, message = "Name must have 2-50 symbols.")
    private String name;

    @NotBlank(message = "User id must be.")
    private Long userId;
}