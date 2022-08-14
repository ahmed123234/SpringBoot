package com.example.loginregisterationbackend.registration;

import java.util.function.Predicate;

public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        TODO: Regex to validate username (email).
        return true;
    }
}
