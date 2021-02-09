package com.giassi.expenses.rest.services;

import com.giassi.expenses.rest.dtos.CreateExpense;
import com.giassi.expenses.rest.entities.Category;
import com.giassi.expenses.rest.entities.Expense;
import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.exceptions.ExpenseNotFoundException;
import com.giassi.expenses.rest.exceptions.InvalidDataException;
import com.giassi.expenses.rest.repositories.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    public Expense getExpenseById(final Long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (!expenseOptional.isPresent()) {
            log.error(String.format("Expense not found = %d", expenseId));
            throw new ExpenseNotFoundException(String.format("Expense doens't exists! for id = %d", expenseId));
        }
        return expenseOptional.get();
    }

    public Expense saveExpense(final CreateExpense createExpense) {
        if (createExpense == null) {
            throw new InvalidDataException("create expense cannot be null");
        }

        return storeExpense(createExpense.getUserId(), createExpense.getCategoryId(), createExpense.getPrice(),
                createExpense.getVoice());
    }

    @Transactional
    public Expense storeExpense(final Long userId, final Long categoryId, final double price, final String voice) {
        // check user
        User user = userService.getUserById(userId);

        // check category
        Category category = categoryService.getCategoryById(categoryId);

        // create expense
        Expense expense = new Expense();
        expense.setCreationDt(LocalDateTime.now());
        expense.setCategory(category);
        expense.setUser(user);
        expense.setVoice(voice);
        expense.setPrice(price);

        return expenseRepository.save(expense);
    }

    public void deleteExpense(final Long expenseId) {
        Expense expense = getExpenseById(expenseId);
        expenseRepository.delete(expense);
    }

    public List<Expense> getUserExpensesList(final Long userId) {
        return expenseRepository.getExpenseListByUserId(userId);
    }

}
