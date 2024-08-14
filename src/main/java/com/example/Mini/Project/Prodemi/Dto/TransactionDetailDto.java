package com.example.Mini.Project.Prodemi.Dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDetailDto {
    private Integer transactionId;
    private Integer totalAmount;
    private Integer totalPay;
    private Date transactionDate;
    @NotNull@NotEmpty
    private Integer productId;
    private String productName;
    private Integer productPrice;
    @NotNull@NotEmpty
    private Integer quantity;
    @NotNull@NotEmpty
    private Integer subtotal;
}
