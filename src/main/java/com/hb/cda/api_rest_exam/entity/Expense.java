package com.hb.cda.api_rest_exam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String label;
    @NotNull
    private BigDecimal amount;
    @Temporal(TemporalType.DATE)
    private LocalDate expenseDate;
    @ManyToOne
    @NotNull
    private User user;
    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExpenseShare> shares = new HashSet<>();
    @ManyToOne
    @NotNull
    private Group group;

}

