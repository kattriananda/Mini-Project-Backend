package com.example.Mini.Project.Prodemi.Controller;


import com.example.Mini.Project.Prodemi.Validation.ResponseData;
import com.example.Mini.Project.Prodemi.Dto.TransactionDetailDto;
import com.example.Mini.Project.Prodemi.Dto.TransactionResponseDto;
import com.example.Mini.Project.Prodemi.Dto.TransactionsDto;
import com.example.Mini.Project.Prodemi.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/pos/api")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping ("/addtransaction")
    public ResponseEntity<ResponseData<TransactionsDto>> addTransaction (@Valid @RequestBody TransactionsDto transactionsDto, Errors errors){
        ResponseData<TransactionsDto> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus("failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        transactionService.addTransaction(transactionsDto);
        responseData.setStatus("ok");
        responseData.setMessages(Collections.singletonList("success"));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/listtransaction")
    public ResponseEntity<List<TransactionResponseDto>> listTransaction (){
        List<TransactionResponseDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/listtransaksidetail/{id}")
    public ResponseEntity <List<TransactionDetailDto>> listTransactionDetail(@PathVariable int id){
        List<TransactionDetailDto> transactionDetail = transactionService.getTransactionDetailById(id);
        return ResponseEntity.ok(transactionDetail);
    }
}
