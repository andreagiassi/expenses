package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.CategoryDTO;
import com.giassi.expenses.rest.dtos.CreateExpense;
import com.giassi.expenses.rest.dtos.ExpenseDTO;
import com.giassi.expenses.rest.services.CategoryService;
import com.giassi.expenses.rest.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseRestController {

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

    @PostMapping("/")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody CreateExpense createExpense) {
        return ResponseEntity.ok(new ExpenseDTO(expenseService.saveExpense(createExpense)));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Long> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(expenseId, HttpStatus.OK);
    }

}
