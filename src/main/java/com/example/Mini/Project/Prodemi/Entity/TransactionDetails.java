package com.example.Mini.Project.Prodemi.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction_details")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "quantity")
    private Integer quantity;

    @Column(name = "subtotal")
    private Integer subtotal;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transactions transactions;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
