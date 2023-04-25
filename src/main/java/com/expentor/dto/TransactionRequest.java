package com.expentor.dto;

import lombok.Data;

@Data
public class TransactionRequest {

    String name;

    String description;

    double amount;

    String from;

    String to;

    String type;

}
