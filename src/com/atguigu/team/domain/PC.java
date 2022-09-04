package com.atguigu.team.domain;

public class PC implements Equipment{

    private String model;//macchine model
    private String display;//screen name

    public PC(String model, String display) {
        this.model = model;
        this.display = display;
    }

    public PC() {
    }

    @Override
    public String getDescription() {
        return model+"("+display+")";
    }
}
