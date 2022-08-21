package com.example.carrentalservice.registerationprocess.regex_validation;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class PasswordValidator implements Predicate<String> {
    private static final  String PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";

    private final Pattern pattern = Pattern.compile(PATTERN);

    @Override
    public boolean test(String password) {

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
