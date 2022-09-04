package com.atguigu.team.service;

/**
 * @Desciption
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -3387515229948L;
    public TeamException(){
        super();
    }

    public TeamException(String message) {
        super(message);
    }
}
