package com.example.Mini.Project.Prodemi.Service;

import com.example.Mini.Project.Prodemi.Dto.TransactionDetailDto;
import com.example.Mini.Project.Prodemi.Dto.TransactionResponseDto;
import com.example.Mini.Project.Prodemi.Dto.TransactionsDto;
import com.example.Mini.Project.Prodemi.Entity.Product;
import com.example.Mini.Project.Prodemi.Entity.TransactionDetails;
import com.example.Mini.Project.Prodemi.Entity.Transactions;
import com.example.Mini.Project.Prodemi.Exception.ProductNotFoundException;
import com.example.Mini.Project.Prodemi.Exception.ResouceNotFoundException;
import com.example.Mini.Project.Prodemi.Repository.ProductRepository;
import com.example.Mini.Project.Prodemi.Repository.TransactionDetailRepo;
import com.example.Mini.Project.Prodemi.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailRepo transactionDetailRepo;
    private final ProductRepository productRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionDetailRepo transactionDetailRepo,
            ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionDetailRepo = transactionDetailRepo;
        this.productRepository = productRepository;
    }

    java.util.Date dateUtil = new java.util.Date();

    // Konversi java.util.Date ke java.sql.Timestamp
    java.sql.Timestamp timestamp = new java.sql.Timestamp(dateUtil.getTime());

    public void addTransaction(TransactionsDto transactionsDto) {
        Transactions transactions = new Transactions();

        transactions.setTotalAmount(transactionsDto.getTotalAmount());

        if (transactionsDto.getTotalPay() < transactionsDto.getTotalAmount()) {
            throw new IllegalArgumentException("Total pay must be greater than or equal to total amount");
        } else {
            transactions.setTotalPay(transactionsDto.getTotalPay());
        }

        transactions.setDate(timestamp);
        transactionRepository.save(transactions);

        for (TransactionDetailDto detailDto : transactionsDto.getTransactionDetails()) {
            TransactionDetails details = new TransactionDetails();
            details.setTransactions(transactions);

            Product product = productRepository.findById(detailDto.getProductId()).orElse(null);
            if (product == null) {
                throw new ProductNotFoundException("");
            }
            details.setProduct(product);
            details.setQuantity(detailDto.getQuantity());

            double subtotal = product.getPrice() * detailDto.getQuantity();
            if (detailDto.getSubtotal() != subtotal) {
                throw new ResouceNotFoundException("sub total salah");
            } else {
                details.setSubtotal(detailDto.getSubtotal());
            }

            transactionDetailRepo.save(details);
        }
    }

    public List<TransactionResponseDto> getAllTransactions() {
        List<Transactions> transactions = transactionRepository.findAll();
        List<TransactionResponseDto> responseDto = new ArrayList<>();
        for (Transactions transaction : transactions) {
            TransactionResponseDto responseDtos = new TransactionResponseDto();
            responseDtos.setId(transaction.getId());
            responseDtos.setTotalAmount(transaction.getTotalAmount());
            responseDtos.setTotalPay(transaction.getTotalPay());
            responseDtos.setTransactionDate(transaction.getDate());
            responseDto.add(responseDtos);
        }
        return responseDto;
    }

    public List<TransactionDetailDto> getTransactionDetailById(int id) {
        List<TransactionDetails> transactionDetails = transactionDetailRepo.findByTransactions_Id(id);
        List<TransactionDetailDto> detailDtos = new ArrayList<>();
        for (TransactionDetails transactionDetail : transactionDetails) {
            TransactionDetailDto detailDto = new TransactionDetailDto();
            detailDto.setTransactionId(transactionDetail.getTransactions().getId());
            detailDto.setTotalAmount(transactionDetail.getTransactions().getTotalAmount());
            detailDto.setTotalPay(transactionDetail.getTransactions().getTotalPay());
            detailDto.setTransactionDate(transactionDetail.getTransactions().getDate());
            detailDto.setProductId(transactionDetail.getProduct().getId());
            detailDto.setProductName(transactionDetail.getProduct().getTitle());
            detailDto.setProductPrice(transactionDetail.getProduct().getPrice());
            detailDto.setQuantity(transactionDetail.getQuantity());
            detailDto.setSubtotal(transactionDetail.getSubtotal());
            detailDtos.add(detailDto);
        }
        return detailDtos;
    }

    public boolean isTotalCorrect(int transactionId) {
        Transactions transactions = transactionRepository.findById(transactionId).orElse(null);
        if (transactions == null) {
            return false;
        }
        double calculatedTotal = transactions.getTransactionDetails().stream()
                .mapToDouble(TransactionDetails::getSubtotal).sum();
        return calculatedTotal == transactions.getTotalAmount();
    }

}
