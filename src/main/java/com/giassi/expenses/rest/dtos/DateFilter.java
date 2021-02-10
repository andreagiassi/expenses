package com.giassi.expenses.rest.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateFilter {

    public DateFilter() {
        LocalDateTime now = LocalDateTime.now();
        this.year = now.getYear();
        this.month = now.getMonthValue();
    }

    public DateFilter(int year, int month) {
        this.year = year;
        this.month = month;
    }

    private int year;
    private int month;

}
