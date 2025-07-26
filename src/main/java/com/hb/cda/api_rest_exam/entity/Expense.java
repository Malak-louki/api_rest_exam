package com.hb.cda.api_rest_exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String label;
    private BigDecimal amount;
    private LocalDate expenseDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private Set<ExpenseShare> shares = new HashSet<>();

    @ManyToOne
    private Group group;


}

