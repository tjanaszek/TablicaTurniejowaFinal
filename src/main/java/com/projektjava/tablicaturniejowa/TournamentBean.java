package com.projektjava.tablicaturniejowa;

import java.io.Serializable;

public class TournamentBean implements Serializable {
    int idTournament;
    boolean isOpen;
    int players;

    public TournamentBean() {
    }

    public TournamentBean(int idTournament, boolean isOpen, int players) {
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
