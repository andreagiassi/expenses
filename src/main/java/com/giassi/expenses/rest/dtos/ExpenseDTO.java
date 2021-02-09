package com.giassi.expenses.rest.dtos;

import com.giassi.expenses.rest.entities.Expense;
import lombok.Data;

@Data
public class ExpenseDTO {

    public ExpenseDTO() {
        // empty impl
    }

    public ExpenseDTO(final Expense expense) {
        if (expense == null) {
            return;
        }

        this.id = expense.getId();
        this.category = expense.getCategory().getCategory();
        this.voice = expense.getVoice();
        this.price = expense.getPrice();
    }

    private Long id;
    private String category;
    private String voice;
    private double price;

}
