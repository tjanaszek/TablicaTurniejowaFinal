package com.projektjava.tablicaturniejowa;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUser;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String surname;

    @Column(nullable = false, unique = true)
    public String user_name;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public int admin;

    @Column(nullable = true)
    public Integer idTournament;




    /*public enum admin {
        zawodnik, administrator;
    }

    int adminvalue = admin.administrator.ordinal();
    int playervalue = admin.zawodnik.ordinal();

    public admin adm = admin.zawodnik;*/


    /*int idTournament;
    int points;
    int admin;*/

    public User() {
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

    public boolean hasIdTournament() {
        if(idTournament==0 || idTournament==null)
            return false;
        return true;
    }

    public void setIdTournament(int idTournament) {
        this.idTournament = idTournament;
    }

    public User(String name, String surname, String user_name, String password, int idUser, int admin, int idTournament ) {
        this.name = name;
        this.surname = surname;
        this.user_name = user_name;
        this.password = password;
        this.idUser=idUser;
        this.admin=admin;
        this.idTournament=idTournament;
    }


    public int getidUser() {
        return idUser;
    }

    public void setidUser(int idUser) {
        this.idUser = idUser;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getsurname() {
        return surname;
    }

    public void setsurname(String surname) {
        this.surname = surname;
    }

    public String getuser_name() {
        return user_name;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public int getadmin(){return admin;}

    public void setadmin(int admin){this.admin=admin;}

    /*public int getIdTournament() {
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

    public void setAdmin(int ad){this.admin=ad;}*/
}
