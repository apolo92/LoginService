package com.test.web.domain.factory;

import com.test.web.domain.Repository;
import com.test.web.repository.RepositoryUsers;

/**
 * Created by alejandro on 30/09/2016.
 */
public class FactoryRepository {
    private static Repository repository;

    public static Repository getRepositoryInstance(){
        if (repository == null){
            repository = new RepositoryUsers();
        }
        return repository;
    }
}
