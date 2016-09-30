package com.test.web.domain.model.constants;

/**
 * Created by alejandro on 30/09/2016.
 */
public enum Roles {
    ROLE1("http://localhost:8000/page1"),
    ROLE2("http://localhost:8000/page2"),
    ROLE3("http://localhost:8000/page3");
    private String url;
    Roles(String url){
        this.url= url;
    }

    public String getUrl() {
        return url;
    }
}
