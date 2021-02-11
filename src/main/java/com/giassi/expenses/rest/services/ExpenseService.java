package com.giassi.expenses.rest.services;

import com.giassi.expenses.rest.dtos.CreateExpense;
import com.giassi.expenses.rest.dtos.DateFilter;
import com.giassi.expenses.rest.dtos.UserSummaryDTO;
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
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
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

    @Transactional
    public Expense saveExpense(final CreateExpense createExpense) {
        if (createExpense == null) {
            throw new InvalidDataException("create expense cannot be null");
        }

        // check user
        User user = userService.getUserById(createExpense.getUserId());

        // check category
        Category category = categoryService.getCategoryById(createExpense.getCategoryId());

        // create expense
        Expense expense = new Expense();
        expense.setCreationDt(LocalDateTime.now());
        expense.setCategory(category);
        expense.setUser(user);
        expense.setVoice(createExpense.getVoice());
        expense.setPrice(createExpense.getPrice());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(final Long expenseId) {
        Expense expense = getExpenseById(expenseId);
        expenseRepository.delete(expense);
    }

    public List<Expense> getUserExpensesList(final Long userId) {
        return expenseRepository.getExpenseListByUserId(userId);
    }

    public UserSummaryDTO getUserSummary(final Long userId, final DateFilter dateFilter) {
        // check user
        userService.getUserById(userId);

        // date filter validation
        if (!DateUtil.isValidDateFilter(dateFilter)) {
            throw new InvalidDataException("Invalid date filter");
        }

        // sum expenses
        Double total = expenseRepository.getTotalByYearAndMonth(userId, dateFilter.getYear(), dateFilter.getMonth());
        if (total == null) {
            total = 0d;
        }

        UserSummaryDTO summaryDTO = new UserSummaryDTO();
        summaryDTO.setYear("" + dateFilter.getYear());
        summaryDTO.setMonth(Month.of(dateFilter.getMonth()).getDisplayName(TextStyle.FULL, Locale.getDefault()));
        summaryDTO.setTotal(total);

        return summaryDTO;
    }

    public List<Expense> getUserExpensesListFiltered(final Long userId, final DateFilter dateFilter) {
        // date filter validation
        if (!DateUtil.isValidDateFilter(dateFilter)) {
            throw new InvalidDataException("Invalid date filter");
        }

        return expenseRepository.getExpenseListByUserIdFiltered(userId, dateFilter.getYear(), dateFilter.getMonth());
    }

}
