package com.giassi.expenses.rest.dtos;

import lombok.Data;

/**
 * Create a new expense
 */
@Data
public class CreateExpense {

    private Long userId;
    private Long categoryId;
    private double price;
    private String voice;

}
