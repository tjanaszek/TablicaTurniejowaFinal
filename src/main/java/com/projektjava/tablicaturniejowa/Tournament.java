package com.projektjava.tablicaturniejowa;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTournament;

    @Column(nullable = false, unique = true)
    public String name;

    @Column(nullable = false)
    public Boolean isOpen;

    @Column(nullable = false)
    int players;


    public Tournament() {
    }



    public Tournament(int idTournament, String name, Boolean isOpen, int players ) {
        this.idTournament = idTournament;
        this.name = name;
        this.isOpen = isOpen;
        this.players = players;
    }

    public int getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(int idTournament) {
        this.idTournament = idTournament;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    
}
