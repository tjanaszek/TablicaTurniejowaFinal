package com.projektjava.tablicaturniejowa;

import java.io.Serializable;

public class UserBean implements Serializable {
    int idUser;
    String name;
    String surname;
    String user_name;
    String password;
    int idTournament;
    int points;
    int admin;

    public UserBean() {
    }

    /*public UserBean(int idUser, String name, String surname, String user_name, String password, int idTournament, int points, int adm) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.user_name = user_name;
        this.password = password;
        this.idTournament = idTournament;
        this.points = points;
        this.admin = adm;
    }*/

    public UserBean(String name, String surname, String user_name, String password) {
        this.name = name;
        this.surname = surname;
        this.user_name = user_name;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(int idTournament) {
        this.idTournament = idTournament;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAdmin(){return admin;}

    public void setAdmin(int ad){this.admin=ad;}
}


