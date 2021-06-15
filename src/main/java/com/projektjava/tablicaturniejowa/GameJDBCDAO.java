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
    ResultSet resultSetTemp = null;

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
            String queryString = "SELECT * FROM game WHERE id_p1 = ? AND result_p1 IS NULL AND game_result IS NULL";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                if (!(isTournamentOpen(resultSet.getInt("id_t")))) {
                    games.add(new Game(resultSet.getInt("id_g"), resultSet.getInt("id_t"), resultSet.getInt("id_p1"), resultSet.getInt("result_p1"), resultSet.getInt("id_p2"), resultSet.getInt("result_p2"), resultSet.getInt("game_result")));
                }
            }
            queryString = "SELECT * FROM game WHERE id_p2 = ? AND result_p2 IS NULL AND game_result IS NULL";
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

    public ArrayList<Game> findAllGamesAdmin() {
        ArrayList<Game> games = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM game WHERE game_result IS NULL";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
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
                ptmt.setInt(1, -1);
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
                ptmt.setInt(1, -1);
            else if(result.equals("remis"))
                ptmt.setInt(1, 1);
            else if(result.equals("wygrana"))
                ptmt.setInt(1, 3);
            ptmt.setInt(2, idUser);
            ptmt.setInt(3, idGame);
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
            addgameresult(idGame);
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

    public void addgameresult(int idGame){
        try {
            String queryString = "SELECT * FROM game WHERE id_g=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, idGame);
            resultSetTemp = ptmt.executeQuery();
            resultSetTemp.next();
            Integer idWygrany;
            if(resultSetTemp.getInt("result_p1")==3 && resultSetTemp.getInt("result_p2")==-1){
                idWygrany=resultSetTemp.getInt("id_p1");
                queryString = "UPDATE game SET game_result=? WHERE id_g=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setInt(1, idWygrany);
                ptmt.setInt(2, idGame);
                ptmt.executeUpdate();
                System.out.println("Table Updated Successfully");
            }
            else if(resultSetTemp.getInt("result_p1")==-1 && resultSetTemp.getInt("result_p2")==3){
                idWygrany=resultSetTemp.getInt("id_p2");
                queryString = "UPDATE game SET game_result=? WHERE id_g=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setInt(1, idWygrany);
                ptmt.setInt(2, idGame);
                ptmt.executeUpdate();
                System.out.println("Table Updated Successfully");
            }
            else if(resultSetTemp.getInt("result_p1")==1 && resultSetTemp.getInt("result_p2")==1){
                idWygrany=1;
                queryString = "UPDATE game SET game_result=? WHERE id_g=?";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setInt(1, idWygrany);
                ptmt.setInt(2, idGame);
                ptmt.executeUpdate();
                System.out.println("Table Updated Successfully");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSetTemp != null)
                    resultSetTemp.close();
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

    public void updategameresultAdmin(int idGame, int idUser) {
        try {
            String queryString = "UPDATE game SET game_result=? WHERE id_g=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, idUser);
            ptmt.setInt(2, idGame);
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

    public Game findGameId(int id) {
        Game game = new Game();
        try {
            String queryString = "SELECT * FROM game WHERE id_g=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            game=(new Game(resultSet.getInt("id_g"), resultSet.getInt("id_t"), resultSet.getInt("id_p1"),resultSet.getInt("id_p2"), resultSet.getInt("result_p1"), resultSet.getInt("result_p2"), resultSet.getInt("game_result")));

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
        return game;
    }

    public boolean isTournamentOpen(int id){
        boolean isopen=false;
        try {
            String queryString = "SELECT * FROM tournament WHERE id_t=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            resultSetTemp = ptmt.executeQuery();
            resultSetTemp.next();
            isopen=resultSetTemp.getBoolean("is_open");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSetTemp != null)
                    resultSetTemp.close();
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
        return isopen;
    }

}
