package com.expentor.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    public enum Type {
        CREDITED,
        DEBITED
    }

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;

    private double amount;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "`from`")
    private String from;

    @Column(name = "`to`")
    private String to;

    private String userId;

    private LocalDate date;

}
