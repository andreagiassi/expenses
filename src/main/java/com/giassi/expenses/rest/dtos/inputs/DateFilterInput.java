package com.giassi.expenses.rest.dtos.inputs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateFilterInput {

    public DateFilterInput() {
        LocalDateTime now = LocalDateTime.now();
        this.year = now.getYear();
        this.month = now.getMonthValue();
    }

    public DateFilterInput(int year, int month) {
        this.year = year;
        this.month = month;
    }

    private int year;
    private int month;

}
