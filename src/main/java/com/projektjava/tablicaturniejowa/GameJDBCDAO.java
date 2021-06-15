package com.projektjava.tablicaturniejowa;

import org.springframework.data.jpa.repository.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameJDBCDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public GameJDBCDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }




    public boolean add(Game gameBean) {
        try {
            String queryString = "INSERT INTO game(id_t, id_p1, id_p2) VALUES(?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, gameBean.getIdTournament());
            ptmt.setInt(2, gameBean.getIdPlayer1());
            ptmt.setInt(3, gameBean.getIdPlayer2());
            ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    public ArrayList<Game> findAllGames(int id) {
        ArrayList<Game> games = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM game WHERE id_p1 = ? AND result_p1 IS NULL";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                System.out.println("Tutaj");
                games.add(new Game(resultSet.getInt("id_g"), resultSet.getInt("id_t"), resultSet.getInt("id_p1"), resultSet.getInt("result_p1"),resultSet.getInt("id_p2"), resultSet.getInt("result_p2"), resultSet.getInt("game_result")));
            }
            queryString = "SELECT * FROM game WHERE id_p2 = ? AND result_p2 IS NULL";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                games.add(new Game(resultSet.getInt("id_g"), resultSet.getInt("id_t"), resultSet.getInt("id_p1"), resultSet.getInt("result_p1"),resultSet.getInt("id_p2"), resultSet.getInt("result_p2"), resultSet.getInt("game_result")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return games;
    }

    public void updategameresult(int idUser, int idGame, String result) {
        try {
            String queryString = "UPDATE game SET result_p1=? WHERE id_p1=? AND id_g=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            if(result.equals("przegrana"))
                ptmt.setInt(1, 0);
            else if(result.equals("remis"))
                ptmt.setInt(1, 1);
            else if(result.equals("wygrana"))
                ptmt.setInt(1, 3);
            ptmt.setInt(2, idUser);
            ptmt.setInt(3, idGame);
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
            queryString = "UPDATE game SET result_p2=? WHERE id_p2=? AND id_g=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            if(result.equals("przegrana"))
                ptmt.setInt(1, 0);
            else if(result.equals("remis"))
                ptmt.setInt(1, 1);
            else if(result.equals("wygrana"))
                ptmt.setInt(1, 3);
            ptmt.setInt(2, idUser);
            ptmt.setInt(3, idGame);
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
