package com.giassi.expenses.rest.services;

import com.giassi.expenses.rest.entities.Category;
import com.giassi.expenses.rest.exceptions.CategoryNotFoundException;
import com.giassi.expenses.rest.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (!categoryOptional.isPresent()) {
            log.error(String.format("Category not found = %d", categoryId));
            throw new CategoryNotFoundException(String.format("Category doens't exists! for id = %d", categoryId));
        }
        return categoryOptional.get();
    }

    // return the category list ordered by category ASC
    public List<Category> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

}
