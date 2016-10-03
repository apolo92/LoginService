package com.test.web.repository;

import com.test.web.domain.model.UserLogin;
import com.test.web.domain.services.Repository;
import com.test.web.dto.UserAPIDTO;
import com.test.web.repository.issues.UserNotExist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alejandro on 30/09/2016.
 */
public class RepositoryUsers implements Repository {

    private Map<String,UserLogin> userStorege = new HashMap<String,UserLogin>();
    private List<UserLogin> userStoregeList = new ArrayList<>();

    @Override
    public UserLogin searchUserStored(UserLogin userverify) {
        return searchUserStored(userverify.getUserName());
    }

    @Override
    public UserLogin searchUserStored(String userName) {
        UserLogin user = userStorege.get(userName);
        if (user==null){
            throw new UserNotExist("User not found in Database");
        }
        return user;
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
        userStoregeList.add(userLogin);
    }

    @Override
    public List<UserLogin> searchAllUsersStored() {
        return userStoregeList;
    }

    @Override
    public void modifyUser(UserAPIDTO userLogin) throws UserNotExist {
        UserLogin userStored = searchUserStored(userLogin.getUsername());
        //New user because password is not modify
        UserLogin userModified = new UserLogin(userLogin.getReplaceUserName(),userStored.getPassword(),userLogin.getRole());
        //Deleted old users
        userStorege.remove(userStored.getUserName());
        userStoregeList.remove(userStored);
        //Created new user modified
        loadUser(userModified);
    }
}
