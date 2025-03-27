package br.com.ufape.petshare.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	public static void validate(String email) throws InvalidEmailException {
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new InvalidEmailException ("E-mail inválido " + email);
		}
	}
}