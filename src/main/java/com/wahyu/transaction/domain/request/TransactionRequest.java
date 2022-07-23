package com.wahyu.transaction.domain.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionRequest implements Serializable {

    private static final long serialVersionUID = 2919238947196402950L;

    private Integer accountId;
    private LocalDateTime transactionDate;
    private String description;
    private String debitCreditStatus;
    private Double amount;

}
