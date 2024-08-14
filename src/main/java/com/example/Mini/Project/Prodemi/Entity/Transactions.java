package com.example.Mini.Project.Prodemi.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table (name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "transaction_date")
    private Timestamp date;

    @Column (name = "total_amount")
    private Integer totalAmount;

    @Column (name = "total_pay")
    private Integer totalPay;

    @OneToMany(mappedBy = "transactions")
    private List<TransactionDetails> transactionDetails;


}
