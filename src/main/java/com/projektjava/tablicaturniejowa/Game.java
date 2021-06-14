package com.projektjava.tablicaturniejowa;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idGame;

    @Column(nullable = false)
    int idTournament;

    @Column(nullable = false)
    int idPlayer1;

    @Column(nullable = true)
    int resultPlayer1;

    @Column(nullable = false)
    int idPlayer2;

    @Column(nullable = true)
    int resultPlayer2;

    @Column(nullable = true)
    int result;

    public Game() {
    }

    public Game(int idGame, int idTournament, int idPlayer1, int idPlayer2, int resultPlayer1, int resultPlayer2, int result) {
        this.idGame = idGame;
        this.idTournament = idTournament;
        this.idPlayer1 = idPlayer1;
        this.idPlayer2 = idPlayer2;
        this.resultPlayer1 = resultPlayer1;
        this.resultPlayer2 = resultPlayer2;
        this.result = result;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(int idTournament) {
        this.idTournament = idTournament;
    }

    public int getIdPlayer1() {
        return idPlayer1;
    }

    public void setIdPlayer1(int idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    public void setIdPlayer2(int idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }

    public int getResultPlayer1() {
        return resultPlayer1;
    }

    public void setResultPlayer1(int resultPlayer1) {
        this.resultPlayer1 = resultPlayer1;
    }

    public int getResultPlayer2() {
        return resultPlayer2;
    }

    public void setResultPlayer2(int resultPlayer2) {
        this.resultPlayer2 = resultPlayer2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
