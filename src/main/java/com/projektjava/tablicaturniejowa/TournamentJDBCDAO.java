package com.projektjava.tablicaturniejowa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TournamentJDBCDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public TournamentJDBCDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void add(TournamentBean tournamentBean) {
        try {
            String queryString = "INSERT INTO tournament(name, is_open, players) VALUES(?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, tournamentBean.getName());
            ptmt.setBoolean(2, true);
            ptmt.setInt(3, 0);
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

    }

//    public void update(TournamentBean tournamentBean) {
//
//        try {
//            String queryString = "UPDATE tournament SET Name=? WHERE RollNo=?";
//            connection = getConnection();
//            ptmt = connection.prepareStatement(queryString);
//            ptmt.setString(1, studentBean.getName());
//            ptmt.setInt(2, studentBean.getRollNo());
//            ptmt.executeUpdate();
//            System.out.println("Table Updated Successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ptmt != null)
//                    ptmt.close();
//                if (connection != null)
//                    connection.close();
//            }
//
//            catch (SQLException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//        }
//    }

//    public void delete(int rollNo) {
//
//        try {
//            String queryString = "DELETE FROM student WHERE RollNo=?";
//            connection = getConnection();
//            ptmt = connection.prepareStatement(queryString);
//            ptmt.setInt(1, rollNo);
//            ptmt.executeUpdate();
//            System.out.println("Data deleted Successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ptmt != null)
//                    ptmt.close();
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    public ArrayList<TournamentBean> findAll() {
        ArrayList<TournamentBean> tournaments = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM tournament";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                tournaments.add(new TournamentBean(resultSet.getInt("id_t"), resultSet.getString("name"), resultSet.getBoolean("is_open"), resultSet.getInt("players")));
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
        return tournaments;
    }
    public void closeRegister(String name) {

        try {
            String queryString = "UPDATE tournament SET is_open=false WHERE name=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, name);
            ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            }

            catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public ArrayList<TournamentBean> findAllOpen() {
        ArrayList<TournamentBean> tournaments = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM tournament WHERE is_open = 1";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                tournaments.add(new TournamentBean(resultSet.getInt("id_t"), resultSet.getString("name"), resultSet.getBoolean("is_open"), resultSet.getInt("players")));
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
        return tournaments;
    }
}
