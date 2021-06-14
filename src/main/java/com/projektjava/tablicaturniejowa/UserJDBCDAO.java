package com.projektjava.tablicaturniejowa;

import org.springframework.data.jpa.repository.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJDBCDAO {
    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public UserJDBCDAO() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    UserRepository rep;
    User findlogin(String login){
        User temp = rep.findByLogin(login);
        return temp;
    }

    /*public void add(UserBean userBean) {
        try {
            String queryString = "INSERT INTO users(name, surname, user_name, password, admin) VALUES(?,?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, userBean.getName());
            ptmt.setString(2, userBean.getSurname());
            ptmt.setString(3, userBean.getUserName());
            ptmt.setString(4, userBean.getPassword());
            ptmt.setInt(5, userBean.getAdmin());
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

    }*/

    /*public void add(UserBean userBean) {*/
    public boolean add(User userBean) {
        User temp = findUserName(userBean.user_name);
        if (temp.user_name == null) {
            try {
                String queryString = "INSERT INTO users(name, surname, user_name, password, admin) VALUES(?,?,?,?,?)";
                connection = getConnection();
                ptmt = connection.prepareStatement(queryString);
                ptmt.setString(1, userBean.getname());
                ptmt.setString(2, userBean.getsurname());
                ptmt.setString(3, userBean.getuser_name());
                ptmt.setString(4, userBean.getpassword());
                ptmt.setInt(5, userBean.getadmin());
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
        else {
            return false;
        }
    }

    public void findAll() {
        try {
            String queryString = "SELECT * FROM users";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                System.out.println("Imie " + resultSet.getString("name")
                        + ", nazwisko " + resultSet.getString("surname") + ", nick "
                        + resultSet.getString("user_name") + ", haslo "
                        + resultSet.getString("password") + ", ID "
                        + resultSet.getInt("id_u"));
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
    }

    public User findUserName(String userName) {
        User user = new User();
        try {
            String queryString = "SELECT * FROM users where user_name = '"+ userName+ "'";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            user = new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("user_name"), resultSet.getString("password"), resultSet.getInt("id_u"),resultSet.getInt("admin") );
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
        return user;
    }

    public void updatetournamentforuser(String userName, String id) {
        try {
            String queryString = "UPDATE users SET id_t=? WHERE user_name=?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, Integer.parseInt(id));
            ptmt.setString(2, userName);
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
        //return user;
    }
}
