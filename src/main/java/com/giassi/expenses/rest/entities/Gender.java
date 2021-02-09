package com.giassi.expenses.rest.entities;

import com.giassi.expenses.rest.exceptions.InvalidGenderException;

public enum Gender {

    MALE(0), FEMALE(1);

    private int gender;

    Gender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public static Gender getValidGender(String genderName) {
        Gender gender;
        try {
            gender = Gender.valueOf(genderName);
        } catch(IllegalArgumentException ex) {
            throw new InvalidGenderException(String.format("Invalid gender string %s. Are supported only: MALE or FEMALE strings", genderName));
        }
        return gender;
    }

}
