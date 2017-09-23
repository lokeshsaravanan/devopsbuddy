package com.devopsbuddy.enums;

public enum RolesEnum {

    /**
     * Enums for Roles
     */
    BASIC(1,"ROLE_BASIC"),
    PRO(2,"ROLE_PRO"),
    ADMIN(3,"ROLE_ADMIN");

    private int id;
    private String roleName;

    RolesEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
