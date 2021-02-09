package com.giassi.expenses.rest.repositories;

import com.giassi.expenses.rest.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "select * from categories order by category ASC", nativeQuery = true)
    List<Category> getCategoryList();

}
