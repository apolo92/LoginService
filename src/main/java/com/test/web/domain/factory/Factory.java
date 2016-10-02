package com.test.web.domain.factory;

import com.test.web.domain.services.LoginService;
import com.test.web.domain.services.Repository;
import com.test.web.repository.RepositoryUsers;

/**
 * Created by alejandro on 30/09/2016.
 */
public class Factory {
    private static Repository repository;
    private static LoginService login;

    public static Repository getRepositoryInstance(){
        if (repository == null){
            repository = new RepositoryUsers();
        }
        return repository;
    }

    public static LoginService getLoginServiceInstance(){
        if (login == null){
            login= new LoginService();
        }
        return login;
    }
}
