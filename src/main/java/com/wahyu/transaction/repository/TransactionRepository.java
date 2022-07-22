package com.wahyu.transaction.repository;

import com.wahyu.transaction.domain.dao.TransactionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long> {

    @Query("select t from TransactionDao t " +
            "where t.account.accountId = ?1 and t.transactionDate between ?2 and ?3 " +
            "order by t.transactionDate")
    List<TransactionDao> findByAccount_AccountIdAndTransactionDateBetweenOrderByTransactionDateAsc(Integer accountId, LocalDateTime transactionDateStart, LocalDateTime transactionDateEnd);

}
