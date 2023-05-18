package com.expentor.repository;

import com.expentor.entity.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

import static com.expentor.entity.Transaction.Type.CREDITED;
import static com.expentor.entity.Transaction.Type.DEBITED;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {

    public List<Transaction> listAllByUserId(String userId) {
        return list("userId", userId);
    }

    public double countCreditedByDateRange(String userId, LocalDate start, LocalDate end) {
        return find("select sum(amount) as total from Transaction where userId = ?1 and date between ?2 in ?3 and type = ?4", userId, start, end, CREDITED).project(Double.class).singleResult();
    }

    public double countDebitedByDateRange(String userId, LocalDate start, LocalDate end) {
        return find("select sum(amount) as total from Transaction where userId = ?1 and date between ?2 in ?3 and type = ?4", userId, start, end, DEBITED).project(Double.class).singleResult();
    }

}
