package com.example.Mini.Project.Prodemi.Repository;

import com.example.Mini.Project.Prodemi.Entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepo extends JpaRepository<TransactionDetails, Integer> {
    List<TransactionDetails> findByTransactions_Id(int transactionId);

    boolean existsByProductId(int productId); //memeriksa produk ada di detail transaksi
}
