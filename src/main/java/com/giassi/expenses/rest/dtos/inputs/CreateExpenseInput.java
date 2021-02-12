package com.giassi.expenses.rest.dtos.inputs;

import lombok.Data;

/**
 * Create a new expense
 */
@Data
public class CreateExpenseInput {

    private Long categoryId;
    private double price;
    private String voice;

}
