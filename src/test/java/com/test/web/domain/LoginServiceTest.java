package com.test.web.domain;

import com.test.web.domain.factory.FactoryRepository;
import com.test.web.domain.issues.LoginError;
import com.test.web.domain.model.UserLogin;
import com.test.web.domain.model.UserRole;
import com.test.web.domain.model.constants.Roles;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alejandro on 30/09/2016.
 */
public class LoginServiceTest {

    @Before
    public void setUp() {
        FactoryRepository.getRepositoryInstance().loadUser(new UserLogin("test","test", Roles.ROLE1));
    }

    @Test
    public void verifyUserAndAccesPage() throws LoginError {
        LoginService login = new LoginService();
        UserLogin user = new UserLogin("test", "test", null);

        UserLogin userLogin = login.loginUser(user);

        Assert.assertEquals(userLogin.getRole().getUrl(), "http://localhost:8000/page1");
    }

    @Test(expected = LoginError.class)
    public void verifyUserAndReturnErrorPage() throws LoginError {
        LoginService login = new LoginService();
        UserLogin user = new UserLogin("test2", "test2", null);

        login.loginUser(user);
    }
}
