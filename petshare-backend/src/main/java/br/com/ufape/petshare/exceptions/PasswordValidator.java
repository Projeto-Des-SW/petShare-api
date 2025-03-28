package br.com.ufape.petshare.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    
    public static void validate(String senha) throws InvalidPasswordException {
        Matcher matcher = PASSWORD_PATTERN.matcher(senha);
        if (!matcher.matches()) {
            throw new InvalidPasswordException ("Senha inválida: deve ter no mínimo 8 caracteres, incluindo pelo menos um caractere maiúsculo, um minúsculo, um número e um caractere especial.");
        }
    }
}
