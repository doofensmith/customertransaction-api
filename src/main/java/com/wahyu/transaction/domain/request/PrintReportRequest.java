package com.wahyu.transaction.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PrintReportRequest implements Serializable {

    private static final long serialVersionUID = -7118200700546804643L;

    private Integer accountId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private transient Object startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private transient Object endDate;

}
