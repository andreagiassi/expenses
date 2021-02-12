package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.ExpenseDTO;
import com.giassi.expenses.rest.dtos.UserDTO;
import com.giassi.expenses.rest.dtos.UserSummaryDTO;
import com.giassi.expenses.rest.dtos.inputs.DateFilterInput;
import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.services.ExpenseService;
import com.giassi.expenses.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    // test endpoint that return the current logged username
    @GetMapping("/test")
    public ResponseEntity<String> getTest(Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(username);
    }

    // return the user profile data as stored during the registration phase
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserById(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return ResponseEntity.ok(new UserDTO(user));
    }

    // return the summary section
    @GetMapping("/summary")
    public ResponseEntity<UserSummaryDTO> getUserCurrentMonthlySummary(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return ResponseEntity.ok(expenseService.getUserSummary(user.getId(), new DateFilterInput()));
    }

    // return the summary calculated based on the given date filter
    @PostMapping("/summary/filter")
    public ResponseEntity<UserSummaryDTO> getUserSummaryFiltered(Principal principal, @RequestBody DateFilterInput dateFilterInput) {
        User user = userService.getUserByUsername(principal.getName());
        return ResponseEntity.ok(expenseService.getUserSummary(user.getId(), dateFilterInput));
    }

    // return the expenses list
    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseDTO>> getUserExpensesList(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        ArrayList<ExpenseDTO> expenses = new ArrayList<>();
        expenseService.getUserExpensesList(user.getId()).forEach(e -> expenses.add(new ExpenseDTO(e)));
        return ResponseEntity.ok(expenses);
    }

    // return the expenses list of the given period as secifying in the date filter
    @PostMapping("/expenses/filter")
    public ResponseEntity<List<ExpenseDTO>> getUserExpensesListFiltered(Principal principal, @RequestBody DateFilterInput dateFilterInput) {
        User user = userService.getUserByUsername(principal.getName());
        ArrayList<ExpenseDTO> expenses = new ArrayList<>();
        expenseService.getUserExpensesListFiltered(user.getId(), dateFilterInput).forEach(e -> expenses.add(new ExpenseDTO(e)));
        return ResponseEntity.ok(expenses);
    }

}
