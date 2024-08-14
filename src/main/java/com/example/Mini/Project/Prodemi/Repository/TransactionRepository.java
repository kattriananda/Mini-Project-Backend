package com.example.Mini.Project.Prodemi.Repository;

import com.example.Mini.Project.Prodemi.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository <Transactions, Integer> {
}
