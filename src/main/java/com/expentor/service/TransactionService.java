package com.expentor.service;

import com.expentor.dto.TransactionRequest;
import com.expentor.entity.Transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions(String userId);

    Transaction getTransaction(long id, String userId);

    Transaction persistTransaction(String userId, TransactionRequest request);

    void deleteTransaction(long id, String userId);

    Transaction updateTransaction(long id, String userId, TransactionRequest request);

    double getTotalCreditedByDateRange(String userId, LocalDate start, LocalDate end);

    double getTotalDebitedByDateRange(String userId, LocalDate start, LocalDate end);

    double getTotalMonthlyCredited(String userId, Month month, String year);

    double getTotalMonthlyDebited(String userId, Month month, String year);
}
