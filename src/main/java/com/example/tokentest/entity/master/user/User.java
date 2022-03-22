package com.example.tokentest.entity.master.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    @Id
    @Column(columnDefinition = "varchar(100)", nullable = false, name = "username")
    private String username;
    @Column(columnDefinition = "varchar(255)", nullable = false, name = "password")
    private String password;
    @Column(columnDefinition = "varchar(100)", nullable = false, name = "name_th")
    private String nameTH;
    @Column(columnDefinition = "varchar(100)", nullable = false, name = "lastname_th")
    private String lastnameTH;
    @Column(columnDefinition = "varchar(30)", nullable = false, name = "name_en")
    private String nameEN;
    @Column(columnDefinition = "varchar(30)", nullable = false, name = "lastname_en")
    private String lastnameEN;
    @Column(columnDefinition = "varchar(50)", nullable = false, name = "email")
    private String email;
    @Column(columnDefinition = "varchar(10)", nullable = false, name = "phone_no")
    private String phoneNO;
    @Column(columnDefinition = "DATE", nullable = false, name = "date")
    private String date;

    public User() {

    }

    public User(String username, String password, String nameTH, String lastnameTH, String nameEN, String lastnameEN, String email, String phoneNO, String date) {
        this.username = username;
        this.password = password;
        this.nameTH = nameTH;
        this.lastnameTH = lastnameTH;
        this.nameEN = nameEN;
        this.lastnameEN = lastnameEN;
        this.email = email;
        this.phoneNO = phoneNO;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameTH() {
        return nameTH;
    }

    public void setNameTH(String nameTH) {
        this.nameTH = nameTH;
    }

    public String getLastnameTH() {
        return lastnameTH;
    }

    public void setLastnameTH(String lastnameTH) {
        this.lastnameTH = lastnameTH;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getLastnameEN() {
        return lastnameEN;
    }

    public void setLastnameEN(String lastnameEN) {
        this.lastnameEN = lastnameEN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
