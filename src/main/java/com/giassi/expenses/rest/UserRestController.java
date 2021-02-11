package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.ExpenseDTO;
import com.giassi.expenses.rest.dtos.inputs.DateFilterInput;
import com.giassi.expenses.rest.dtos.UserDTO;
import com.giassi.expenses.rest.dtos.UserSummaryDTO;
import com.giassi.expenses.rest.services.ExpenseService;
import com.giassi.expenses.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "userId") Long userId) {
        UserDTO userDTO = new UserDTO(
                userService.getUserById(userId)
        );
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{userId}/summary")
    public ResponseEntity<UserSummaryDTO> getUserCurrentMonthlySummary(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(expenseService.getUserSummary(userId, new DateFilterInput()));
    }

    @PostMapping("/{userId}/summary/filter")
    public ResponseEntity<UserSummaryDTO> getUserSummaryFiltered(@PathVariable(value = "userId") Long userId,
                                                                 @RequestBody DateFilterInput dateFilterInput) {
        return ResponseEntity.ok(expenseService.getUserSummary(userId, dateFilterInput));
    }

    @GetMapping("/{userId}/expenses")
    public ResponseEntity<List<ExpenseDTO>> getUserExpensesList(@PathVariable Long userId) {
        ArrayList<ExpenseDTO> expenses = new ArrayList<>();
        expenseService.getUserExpensesList(userId).forEach(e -> expenses.add(new ExpenseDTO(e)));
        return ResponseEntity.ok(expenses);
    }

    @PostMapping("/{userId}/expenses/filter")
    public ResponseEntity<List<ExpenseDTO>> getUserExpensesListFiltered(@PathVariable Long userId,
                                                                        @RequestBody DateFilterInput dateFilterInput) {
        ArrayList<ExpenseDTO> expenses = new ArrayList<>();
        expenseService.getUserExpensesListFiltered(userId, dateFilterInput).forEach(e -> expenses.add(new ExpenseDTO(e)));
        return ResponseEntity.ok(expenses);
    }

}
