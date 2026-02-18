package org.quick.bank.entity.DTOs.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotNull(message = "Id should be.")
    @Min(value = 1)
    private Long cardIdFrom;

    @NotNull(message = "Id should be.")
    @Min(value = 1)
    private Long cardIdTo;

    @NotNull(message = "Amount should be.")
    @DecimalMin(value = "0.01", message = "Amount should be more than zero.")
    private BigDecimal amount;
}
