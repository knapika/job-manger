package com.example.javaserver.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userID")
    private Integer userID;

    @Column(unique=true, nullable = false)
    private String login;

    @Column(unique=true, nullable = false)
    private String password;

    @Column()
    private Date registrationDate;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer id) {
        this.userID = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}

