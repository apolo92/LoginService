package com.test.web.domain;

import com.test.web.domain.factory.Factory;
import com.test.web.domain.issues.InvalidUsser;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.issues.PermissionDenied;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.model.constants.Roles;
import com.test.web.domain.services.LoginService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 30/09/2016.
 */
public class LoginServiceTest {

    private UserLogin user = new UserLogin("test", "test", null);

    @Before
    public void setUp() {
        List<Roles> role1 = new ArrayList();
        role1.add(Roles.ROLE1);
        Factory.getRepositoryInstance().loadUser(new UserLogin("test","test",role1));
    }

    @Test
    public void verifyUserAndAccesPage() throws LoginError {
        LoginService login = new LoginService();

        UserLogin userLogin = login.loginUser(user);

        Assert.assertEquals(userLogin.getRole().get(0).getUrl()[0], "http://localhost:8000/page1");
    }

    @Test(expected = LoginError.class)
    public void verifyUserAndReturnErrorPage() throws LoginError {
        LoginService login = new LoginService();
        UserLogin user = new UserLogin("test2", "test2", null);

        login.loginUser(user);
    }

    @Test
    public void generateValidJWT() throws InvalidUsser, PermissionDenied {
        LoginService login = new LoginService();

        String jwt = login.generateJWT(user);
        login.validateJWTAmdPermissions(jwt,"http://localhost:8000/page1");
    }

    @Test(expected = InvalidUsser.class)
    public void generateInvalidJWT() throws InvalidUsser, PermissionDenied {
        LoginService login = new LoginService();

        String jwt = login.generateJWT(user);
        jwt += "error";
        login.validateJWTAmdPermissions(jwt,"http://localhost:8000/page1");
    }
}
