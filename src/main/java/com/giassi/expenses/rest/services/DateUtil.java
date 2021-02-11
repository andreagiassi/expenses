package com.giassi.expenses.rest.services;

import com.giassi.expenses.rest.dtos.DateFilter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

@Slf4j
public class DateUtil {

    public static boolean isValidDateFilter(final DateFilter dateFilter) {
        return isValidDate(dateFilter.getYear() + "-" + dateFilter.getMonth() + "-01");
    }

    // ref: https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
    public static boolean isValidDate(final String date) {
        try {
            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

            return true;
        } catch (DateTimeParseException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }

        return false;
    }

}
