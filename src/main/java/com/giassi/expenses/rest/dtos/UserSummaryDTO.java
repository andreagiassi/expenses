package com.giassi.expenses.rest.dtos;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserSummaryDTO {

    private String year;
    private String month;
    private double total;

}
