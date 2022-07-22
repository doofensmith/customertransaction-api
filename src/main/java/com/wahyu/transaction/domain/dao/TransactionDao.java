package com.wahyu.transaction.domain.dao;

import com.wahyu.transaction.domain.common.BaseDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "m_transaction")
public class TransactionDao extends BaseDao implements Serializable {

    private static final long serialVersionUID = -1293539513152240373L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne
    private AccountDao account;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false, length = 50)
    private String description;

    @Column(name = "debit_credit", nullable = false, length = 1)
    private String debitCreditStatus;

    @Column(nullable = false)
    private Double amount;

}
