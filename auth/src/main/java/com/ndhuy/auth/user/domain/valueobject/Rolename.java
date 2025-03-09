package com.ndhuy.auth.user.domain.valueobject;

public enum Rolename {
    EIDTOR("Editor"), ADMIN("Admin"), USER("User");

    private final String desc;

    

    Rolename(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }
}
