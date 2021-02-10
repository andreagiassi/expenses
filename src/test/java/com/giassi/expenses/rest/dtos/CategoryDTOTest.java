package com.giassi.expenses.rest.dtos;

import com.giassi.expenses.rest.entities.Category;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test CategoryDTO
 */
public class CategoryDTOTest {

    @Test
    public void createEmptyCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();

        assertEquals(null, categoryDTO.getId());
        assertEquals(null, categoryDTO.getCategory());
    }

    @Test
    public void createCategoryDTO() {
        Category category = new Category();
        category.setId(1L);
        category.setCategory("Food");

        CategoryDTO categoryDTO = new CategoryDTO(category);

        assertEquals(1L, categoryDTO.getId());
        assertEquals("Food", categoryDTO.getCategory());
    }

}
