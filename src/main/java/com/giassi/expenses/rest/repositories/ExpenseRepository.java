package com.giassi.expenses.rest.repositories;

import com.giassi.expenses.rest.entities.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    @Query(value = "select * from expenses where user_id = ?1 order by creation_dt DESC", nativeQuery = true)
    List<Expense> getExpenseListByUserId(Long userId);

}
