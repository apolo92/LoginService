package com.test.web.domain.services;

import com.test.web.domain.model.UserLogin;
import com.test.web.dto.UserAPIDTO;

import java.util.List;

/**
 * Created by alejandro on 30/09/2016.
 */
public interface Repository {

    public UserLogin searchUserStored(UserLogin userverify);

    public UserLogin searchUserStored(String userName);

    public void loadUsers(List<UserLogin> saveUsers);

    public void loadUser(UserLogin userLogin);

    public List<UserLogin> searchAllUsersStored();

    public void modifyUser(UserAPIDTO userLogin);
}
