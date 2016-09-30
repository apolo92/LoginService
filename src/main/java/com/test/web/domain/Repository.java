package com.test.web.domain;

import com.test.web.domain.model.UserLogin;

import java.util.List;

/**
 * Created by alejandro on 30/09/2016.
 */
public interface Repository {

    public UserLogin searchUserStored(UserLogin userverify);

    public void loadUsers(List<UserLogin> saveUsers);

    public void loadUser(UserLogin userLogin);
}
