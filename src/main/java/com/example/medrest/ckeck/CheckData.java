package com.example.medrest.ckeck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {

    public static boolean checkNames(String name){

        Pattern pattern = Pattern.compile("^[А-ЯЁ][а-яё]*([-][А-ЯЁ][а-яё]*)?\\s[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static boolean checkText(String name){

        Pattern pattern = Pattern.compile("^((([А-ЯЁ]|[а-яё]*)(\\s)*))*$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static boolean checkDate(String name){

        Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[-\\.](0[1-9]|1[012])[-\\.](19|20)\\d\\d$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static boolean checkIdentificationNumber(String name){

        Pattern pattern = Pattern.compile("^[0-9]{7}[A-Z]{1}[0-9]{3}[A-Z]{2}[0-9]{1}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}


