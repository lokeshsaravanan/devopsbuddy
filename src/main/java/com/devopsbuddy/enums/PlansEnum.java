package com.devopsbuddy.enums;

public enum PlansEnum {

    BASIC(1,"BASIC"),
    PRO(2,"PRO");


    private int id;
    private String planName;

    PlansEnum(int id, String planName) {
        this.id = id;
        this.planName = planName;
    }

    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }
}
