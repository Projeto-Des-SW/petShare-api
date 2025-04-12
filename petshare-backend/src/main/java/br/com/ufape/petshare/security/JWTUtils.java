package br.com.ufape.petshare.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.ufape.petshare.model.enums.Profile;
import br.com.ufape.petshare.services.exceptions.AuthenticationException;


@Service
public class JWTUtils {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${project.issuer}")
	private String projectIssuer;

	public String generateLoginToken(AuthUser user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			if (!user.getRoles().contains(Profile.USER))
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não aprovado");
			String token = JWT.create().withIssuer(projectIssuer).withSubject(user.getEmail())
					.withExpiresAt(new Date(System.currentTimeMillis() + expiration)).sign(algorithm);
			return token;

		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while generating token ", exception);
		}
	}
	
	public String generateLoginToken(String username) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create().withIssuer(projectIssuer).withSubject(username)
					.withExpiresAt(new Date(System.currentTimeMillis() + expiration)).sign(algorithm);
			return token;

		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while generating token ", exception);
		}
	}
	
	public String generatePasswordRecoveryToken(String username, String password) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withIssuer(projectIssuer).withSubject(username + ">ZZh$~B344" + password)
				.withExpiresAt(new Date(System.currentTimeMillis() + expiration)).sign(algorithm);
	}

	
	public String recoverEmailByToken(String token) {
		String subject = validateToken(token);
		if(subject.isEmpty()) {
			throw new AuthenticationException("Token inválido e/ou expirado");
		}
		return subject.substring(0, 14);
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer(projectIssuer).build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			return "";
		}
	}
}
