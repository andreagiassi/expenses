package com.giassi.expenses.rest;

import com.giassi.expenses.rest.dtos.CategoryDTO;
import com.giassi.expenses.rest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<ArrayList<CategoryDTO>> getCategoryList() {
        ArrayList<CategoryDTO> list = new ArrayList<>();
        categoryService.getCategoryList().forEach( c-> list.add(new CategoryDTO(c)));
        return ResponseEntity.ok(list);
    }

}
