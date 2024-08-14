package com.example.Mini.Project.Prodemi.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;


@Data
public class TransactionsDto {

    @NotNull
    private Integer totalAmount;

    private Integer totalPay;
    @NotNull
    @NotEmpty
    private List<TransactionDetailDto> transactionDetails;

}
