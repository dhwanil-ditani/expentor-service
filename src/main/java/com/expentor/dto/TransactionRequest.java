package com.expentor.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TransactionRequest {

    private String name;

    private String description;

    private double amount;

    private String from;

    private String to;

    private String type;

    private LocalDate date;

}
