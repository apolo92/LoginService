package com.test.web.repository;

import com.test.web.domain.Repository;
import com.test.web.domain.model.UserLogin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alejandro on 30/09/2016.
 */
public class RepositoryUsers implements Repository {

    private Map<String,UserLogin> userStorege = new HashMap<String,UserLogin>();

    @Override
    public UserLogin searchUserStored(UserLogin userverify) {
        return userStorege.get(userverify.getUserName());
    }

    @Override
    public void loadUsers(List<UserLogin> saveUsers){
        for (UserLogin saveUser: saveUsers) {
            loadUser(saveUser);
        }
    }

    @Override
    public void loadUser(UserLogin userLogin) {
        //TODO cripto
        userStorege.put(userLogin.getUserName(),userLogin);
    }
}
