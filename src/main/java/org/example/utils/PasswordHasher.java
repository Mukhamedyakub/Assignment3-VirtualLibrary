package org.example.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher {

    public static String hashPassword(String password){
        return  BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, password.toCharArray());
    }

    public static boolean verifyPassword(String password, String hashedPassword){
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword.toCharArray()).verified;
    }
}
