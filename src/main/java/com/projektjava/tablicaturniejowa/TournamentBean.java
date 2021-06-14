package com.projektjava.tablicaturniejowa;

import java.io.Serializable;

public class TournamentBean implements Serializable {
    int idTournament;
    String name;
    boolean isOpen;
    int players;

    public TournamentBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TournamentBean(int idTournament, String name, boolean isOpen, int players) {
        this.name=name;
        this.idTournament = idTournament;
        this.isOpen = isOpen;
        this.players = players;
    }

    public int getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(int idTournament) {
        this.idTournament = idTournament;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
