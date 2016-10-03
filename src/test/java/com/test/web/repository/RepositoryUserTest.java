package com.test.web.repository;

import com.test.web.domain.model.constants.Roles;
import com.test.web.domain.services.Repository;
import com.test.web.domain.model.UserLogin;
import com.test.web.dto.UserAPIDTO;
import com.test.web.repository.issues.UserNotExist;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro on 30/09/2016.
 */
public class RepositoryUserTest {

    @Test
    public void verifyUserExist(){
        Repository repositoryUsers = new RepositoryUsers();
        UserLogin userLogin = new UserLogin("test","test");

        repositoryUsers.loadUser(userLogin);
        UserLogin result = repositoryUsers.searchUserStored(userLogin);

        Assert.assertEquals(result,userLogin);
    }

    @Test
    public void verifyUsersExist(){
        Repository repositoryUsers = new RepositoryUsers();
        UserLogin userLogin = new UserLogin("test","test");
        UserLogin userLogin2 = new UserLogin("test2","test2");
        List<UserLogin> listusers = new ArrayList();
        listusers.add(userLogin);
        listusers.add(userLogin2);

        repositoryUsers.loadUsers(listusers);
        UserLogin resultUser1 = repositoryUsers.searchUserStored(userLogin);
        UserLogin resultUser2 =  repositoryUsers.searchUserStored(userLogin2);

        Assert.assertEquals(resultUser1,userLogin);
        Assert.assertEquals(resultUser2,userLogin2);
    }

    @Test
    public void verifyModifyUserExist(){
        Repository repository = new RepositoryUsers();
        UserLogin userLogin = new UserLogin("test","test");
        repository.loadUser(userLogin);
        List<Roles> roles = new ArrayList<>();
        roles.add(Roles.ROLE1);

        UserAPIDTO userDTO = new UserAPIDTO("test",roles ,"test2");
        repository.modifyUser(userDTO);

        UserLogin userVerify = repository.searchUserStored("test2");
        Assert.assertEquals(userDTO.getRole(),userVerify.getRole());
    }

    @Test(expected = UserNotExist.class)
    public void userNotExistAndReturnError(){
        Repository repositoryUsers = new RepositoryUsers();
        UserLogin userLogin = new UserLogin("test","test");

        UserLogin result = repositoryUsers.searchUserStored(userLogin);
    }
}
