package com.example.Mini.Project.Prodemi.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Data
public class TransactionResponseDto {
    @NotNull
    @NotEmpty
    private Integer id;
    private Integer totalAmount;
    private Integer totalPay;
    private Date transactionDate;

}
