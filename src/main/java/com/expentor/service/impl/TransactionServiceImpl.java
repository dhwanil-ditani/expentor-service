package com.expentor.service.impl;

import com.expentor.dto.TransactionRequest;
import com.expentor.entity.Transaction;
import com.expentor.repository.TransactionRepository;
import com.expentor.service.TransactionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@ApplicationScoped
public class TransactionServiceImpl implements TransactionService {

    @Inject
    TransactionRepository repository;

    @Override
    public List<Transaction> getAllTransactions(String userId) {
        return repository.listAllByUserId(userId);
    }

    @Override
    public Transaction getTransaction(long id, String userId) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Transaction persistTransaction(String userId, TransactionRequest request) {
        Transaction transaction = Transaction.builder()
                .name(request.getName())
                .description(request.getDescription())
                .amount(request.getAmount())
                .to(request.getTo())
                .from(request.getFrom())
                .type(Transaction.Type.valueOf(request.getType()))
                .userId(userId)
                .date(request.getDate())
                .build();
        repository.persist(transaction);
        if (!repository.isPersistent(transaction)) {
            return null;
        }
        return transaction;
    }

    @Transactional
    @Override
    public void deleteTransaction(long id, String userId) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public Transaction updateTransaction(long id, String userId, TransactionRequest request) {
        Transaction transaction = repository.findById(id);
        transaction.setUserId(userId);
        transaction.setName(request.getName());
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setTo(request.getTo());
        transaction.setFrom(request.getFrom());
        transaction.setDate(request.getDate());
        repository.persist(transaction);
        if (!repository.isPersistent(transaction)) {
            return null;
        }
        return transaction;
    }

    @Override
    public double getTotalCreditedByDateRange(String userId, LocalDate start, LocalDate end) {
        return repository.countCreditedByDateRange(userId, start, end);
    }

    @Override
    public double getTotalDebitedByDateRange(String userId, LocalDate start, LocalDate end) {
        return repository.countDebitedByDateRange(userId, start, end);
    }

    @Override
    public double getTotalMonthlyCredited(String userId, Month month, String year) {
        LocalDate start = LocalDate.of(LocalDate.now().getYear(), month,
                1);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), month,
                LocalDate.now().lengthOfMonth());
        return getTotalCreditedByDateRange(userId, start, end);
    }

    @Override
    public double getTotalMonthlyDebited(String userId, Month month, String year) {
        LocalDate start = LocalDate.of(LocalDate.now().getYear(), month,
                1);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), month,
                LocalDate.now().lengthOfMonth());
        return getTotalDebitedByDateRange(userId, start, end);
    }
}
