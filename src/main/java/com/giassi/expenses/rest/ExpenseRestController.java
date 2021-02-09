package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.CreateExpense;
import com.giassi.expenses.rest.dtos.ExpenseDTO;
import com.giassi.expenses.rest.entities.Expense;
import com.giassi.expenses.rest.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseRestController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody CreateExpense createExpense) {
        return ResponseEntity.ok(new ExpenseDTO(expenseService.saveExpense(createExpense)));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Long> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(expenseId, HttpStatus.OK);
    }

    // TODO: today, week, monthly list
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<ExpenseDTO>> getUserById(@PathVariable Long userId) {
        ArrayList<ExpenseDTO> expenses = new ArrayList<>();
        expenseService.getUserExpensesList(userId).forEach(e -> expenses.add(new ExpenseDTO(e)));
        return ResponseEntity.ok(expenses);
    }

}
