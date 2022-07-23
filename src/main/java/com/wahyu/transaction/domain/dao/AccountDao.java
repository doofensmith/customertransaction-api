package com.wahyu.transaction.domain.dao;

import com.wahyu.transaction.domain.common.BaseDaoSoftDelete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "m_account")
@SQLDelete(sql = "update m_account set is_deleted=true, deleted_at=current_timestamp where account_id=?")
@Where(clause = "is_deleted=false")
public class AccountDao extends BaseDaoSoftDelete implements Serializable {

    private static final long serialVersionUID = -5428574388184874795L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @OneToMany(mappedBy = "account")
    private List<TransactionDao> transactions;

}
