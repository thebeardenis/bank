package org.quick.bank.entity.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotBlank(message = "Sender id must be.")
    private Long userIdFrom;

    @NotBlank(message = "Consumer must be.")
    private Long userIdTo;

    @NotBlank(message = "Amount must be.")
    private BigDecimal amount;
}
