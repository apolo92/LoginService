package com.test.web.domain.services;

import com.test.web.domain.factory.FactoryRepository;
import com.test.web.domain.issues.InvalidUsser;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.issues.PermissionDenied;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.model.constants.Roles;
import io.jsonwebtoken.*;

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
        if (userStorage == null || !userStorage.getPassword().equals(user.getPassword())) {
            throw new LoginError();
        }
        return userStorage;
    }

    public String generateJWT(UserLogin userStorage) {
        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(t + (5 * ONE_MINUTE_IN_MILLIS));
        String jwt = Jwts.builder().setSubject(userStorage.getUserName())
                .setExpiration(afterAddingFiveMins)
                .signWith(SignatureAlgorithm.HS256, getSignatureKey())
                .compact();
        return jwt;
    }

    private byte[] getSignatureKey() {
        String secret = "aosidaPIAn1";
        return secret.getBytes();
    }

    public String validateJWTAmdPermissions(String jwt, String urlAccess) throws InvalidUsser, PermissionDenied {
        try {
            String userToken = Jwts.parser().setSigningKey(getSignatureKey()).parseClaimsJws(jwt).getBody().getSubject();

            UserLogin userStored = repository.searchUserStored(userToken);
            if (userStored == null) {
                throw new PermissionDenied();
            }
            for (Roles rol : userStored.getRole()) {
                for (String url :rol.getUrl()){
                    if (url.equals(urlAccess)) {
                        return userStored.getUserName();
                    }
                }
            }
            throw new PermissionDenied();
        } catch (SignatureException e) {
            throw new InvalidUsser();
        } catch (ExpiredJwtException e) {
            throw new InvalidUsser();
        }

    }
}
