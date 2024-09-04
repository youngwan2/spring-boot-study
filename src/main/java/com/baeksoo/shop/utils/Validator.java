package com.baeksoo.shop.utils;

import java.util.regex.Pattern;

public class Validator {

    static public boolean emailValidator(String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    static public boolean passwordValidator(String password) {
        // 최소 8자 이상, 하나 이상의 숫자, 하나 이상의 대문자, 하나 이상의 소문자, 하나 이상의 특수 문자 포함
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }
}
