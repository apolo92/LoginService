package com.test.web.domain.services;

import com.test.web.domain.factory.Factory;
import com.test.web.domain.issues.InvalidUsser;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.issues.PermissionDenied;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.model.constants.Roles;
import io.jsonwebtoken.ExpiredJwtException;
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
        repository = Factory.getRepositoryInstance();
    }

    public UserLogin loginUser(UserLogin user) throws LoginError {
        UserLogin userStorage = repository.searchUserStored(user);
        if (!userStorage.getPassword().equals(user.getPassword())) {
            throw new LoginError("Login error password incorrect");
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
            String userToken = validateJwt(jwt);
            UserLogin userStored = repository.searchUserStored(userToken);
            for (Roles rol : userStored.getRole()) {
                for (String url :rol.getUrl()){
                    if (url.equals(urlAccess)) {
                        return userStored.getUserName();
                    }
                }
            }
            throw new PermissionDenied("User not have permission to acces this page");
    }

    private String validateJwt(String jwt) throws InvalidUsser {
        try {
        return Jwts.parser().setSigningKey(getSignatureKey()).parseClaimsJws(jwt).getBody().getSubject();
        } catch (SignatureException e) {
            throw new InvalidUsser("Token no valid");
        } catch (ExpiredJwtException e) {
            throw new InvalidUsser("Token expired");
        }catch (Exception e){
            throw new InvalidUsser("Token no valid");
        }
    }
}
