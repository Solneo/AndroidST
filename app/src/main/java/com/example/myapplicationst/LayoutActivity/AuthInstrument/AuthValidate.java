package com.example.myapplicationst.LayoutActivity.AuthInstrument;

/**
 * Created by Ыщвф on 04.12.2018.
 */

public class AuthValidate {
    public boolean validate(String login, String pswd) {
        boolean valid = isPasswordStrong(pswd);
        return valid;
    }

    public static boolean isPasswordStrong(String pswd) {
        return (!(pswd.length() < 7) &&
                pswd.matches(".*[A-Z].*") &&
                pswd.matches(".*[0-9].*"));

    }
}
