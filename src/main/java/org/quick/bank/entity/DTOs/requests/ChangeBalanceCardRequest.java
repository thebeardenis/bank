package org.quick.bank.entity.DTOs.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBalanceCardRequest {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long cardId;
}
