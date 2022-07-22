package com.wahyu.transaction.repository;

import com.wahyu.transaction.domain.dao.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDao, Integer> {
}
