package com.test.web.domain;

import com.test.web.domain.factory.FactoryRepository;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.model.UserLogin;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by alejandro on 30/09/2016.
 */
public class LoginService {

    private static final int ONE_MINUTE_IN_MILLIS = 60000;
    private Repository repository;

    public LoginService() {
        repository = FactoryRepository.getRepositoryInstance();
    }

    public UserLogin loginUser(UserLogin user) throws LoginError {
        UserLogin userStorage = repository.searchUserStored(user);
        if (userStorage == null) {
            throw new LoginError();
        }
        String jwt = generateJWT();
        return userStorage;
    }

    private String generateJWT() {
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(t + (5 * ONE_MINUTE_IN_MILLIS));
        byte[] key = getSignatureKey();
        String jwt = Jwts.builder().setIssuer("http://trustyapp.com/")
                        .setSubject("users/1300819380")
                        .setExpiration(afterAddingFiveMins)
                        .signWith(SignatureAlgorithm.HS256, key)
                        .compact();
        return jwt;
    }

    private byte[] getSignatureKey() {
        String secret = "aosidaPIAn1";
        return secret.getBytes();
    }

    public void validateJWT(String jwt) {
        try {
            Jws jwtClaims = Jwts.parser().setSigningKey(getSignatureKey()).parseClaimsJws(jwt);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }
}
