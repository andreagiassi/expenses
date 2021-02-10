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

    @Query(value = "select sum(price) from expenses where user_id = ?1 and YEAR(creation_dt)= ?2 and MONTH(creation_dt) = ?3", nativeQuery = true)
    Double getTotalByYearAndMonth(Long userId, int year, int monthValue);

    @Query(value = "select * from expenses where user_id = ?1 and YEAR(creation_dt)= ?2 and MONTH(creation_dt) = ?3", nativeQuery = true)
    List<Expense> getExpenseListByUserIdFiltered(Long userId, int year, int month);

}
