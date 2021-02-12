package com.giassi.expenses.rest.dtos;

import com.giassi.expenses.rest.entities.Category;
import lombok.Data;

// An expense category
@Data
public class CategoryDTO {

    public CategoryDTO() {
        // empty impl
    }

    public CategoryDTO(final Category category) {
        if (category == null) {
            return;
        }

        this.id = category.getId();
        this.category = category.getCategory();
    }

    private Long id;
    private String category;

}
