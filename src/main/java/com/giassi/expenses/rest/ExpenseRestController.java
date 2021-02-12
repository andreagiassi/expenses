package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.CategoryDTO;
import com.giassi.expenses.rest.dtos.inputs.CreateExpenseInput;
import com.giassi.expenses.rest.dtos.ExpenseDTO;
import com.giassi.expenses.rest.entities.User;
import com.giassi.expenses.rest.services.CategoryService;
import com.giassi.expenses.rest.services.ExpenseService;
import com.giassi.expenses.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<ArrayList<CategoryDTO>> getCategoryList() {
        ArrayList<CategoryDTO> list = new ArrayList<>();
        categoryService.getCategoryList().forEach( c-> list.add(new CategoryDTO(c)));
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(Principal principal, @RequestBody CreateExpenseInput createExpenseInput) {
        User user = userService.getUserByUsername(principal.getName());
        return ResponseEntity.ok(new ExpenseDTO(expenseService.saveExpense(user, createExpenseInput)));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Long> deleteExpense(Principal principal, @PathVariable Long expenseId) {
        User user = userService.getUserByUsername(principal.getName());
        expenseService.deleteExpense(user, expenseId);
        return new ResponseEntity<>(expenseId, HttpStatus.OK);
    }

}
