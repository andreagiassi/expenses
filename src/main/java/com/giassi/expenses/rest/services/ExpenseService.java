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
        User user = userService.getUserById(userId);

        // TODO: date filter validation

        // sum expenses
        LocalDateTime now = LocalDateTime.now();
        Double total = expenseRepository.getTotalByYearAndMonth(userId, now.getYear(), now.getMonthValue());
        if (total == null) {
            total = 0d;
        }

        UserSummaryDTO summaryDTO = new UserSummaryDTO();
        summaryDTO.setYear("" + now.getYear());
        summaryDTO.setMonth(now.getMonth().name());
        summaryDTO.setTotal(total);

        log.info(summaryDTO.toString());

        return summaryDTO;
    }

    public List<Expense> getUserExpensesListFiltered(final Long userId, final DateFilter dateFilter) {
        // TODO: date filter validation
        return expenseRepository.getExpenseListByUserIdFiltered(userId, dateFilter.getYear(), dateFilter.getMonth());
    }
}
