package com.example.Mini.Project.Prodemi.Validation;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class AuthenticationData <T>{
    private String status;
    private List<String> messages = new ArrayList<>();
    private T payload;
}
