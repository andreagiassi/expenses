package com.giassi.expenses.rest.dtos;

import com.giassi.expenses.rest.entities.Category;
import com.giassi.expenses.rest.entities.Expense;
import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.services.UserServiceTest;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test ExpenseDTOTest
 */
public class ExpenseDTOTest {

    @Test
    public void createEmptyExpenseDTO() {
        ExpenseDTO expenseDTO = new ExpenseDTO();

        assertEquals(null, expenseDTO.getId());
        assertEquals(null, expenseDTO.getCategory());
        assertEquals(null, expenseDTO.getVoice());
        assertEquals(0.0, expenseDTO.getPrice());
    }

    @Test
    public void createExpenseDTO() {
        // user
        User user = UserServiceTest.getDemoUserData();

        // category
        Category category = new Category();
        category.setId(1L);
        category.setCategory("Food");

        // expense
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setCategory(category);
        expense.setPrice(15.5);
        expense.setUser(user);
        expense.setVoice("Bus ticket");

        ExpenseDTO expenseDTO = new ExpenseDTO(expense);

        assertEquals(1L, expenseDTO.getId());
        assertEquals("Food", expenseDTO.getCategory());
        assertEquals("Bus ticket", expenseDTO.getVoice());
        assertEquals(15.5, expenseDTO.getPrice());
    }

}
