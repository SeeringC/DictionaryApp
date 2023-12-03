package Manager;

import Singleton.Singleton;
import com.example.translatetest1.EnViWord;
import com.example.translatetest1.MyDictionary;

import java.sql.*;

public class DataBaseManager extends Singleton<DataBaseManager> {

    private Connection connection = null;

    public void init() throws SQLException {
        try {
            connectToDatabase();
            // create statement
            Statement stmt = connection.createStatement();

            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from dictionary");

            // show data
            while (rs.next()) {
                EnViWord newEnViWord = new EnViWord(rs.getString(2), rs.getString(3));
                MyDictionary.getIns(MyDictionary.class).loadWordFromSQL(newEnViWord);
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Close connection to MYSQL database.
     *
     * @param connection Connection variable
     */
    private void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the PreparedStatement ps.
     *
     * @param ps PreparedStatement needed to close
     */
    private void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the ResultSet rs.
     *
     * @param rs ResultSet needed to be close
     */
    private void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectToDatabase() throws SQLException {
        System.out.println("Connecting to database...");
        String DB_URL = "jdbc:mysql://localhost:3306/mydictionary";
        String USER_NAME = "root";
        String PASSWORD = "12345678";
        connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        System.out.println("Database connected!\n");
    }

    public void addWordtoSQL(String target, String definition) {
        final String SQL_QUERY = "INSERT INTO dictionary (target, definition) VALUES (?, ?)";
        try {
            connectToDatabase();
            PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
            ps.setString(1, target);
            ps.setString(2, definition);
            try {
                ps.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException e) {
                // `word` is already in database
                System.out.println(e.getMessage());
            } finally {
                close(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWordSQL(String target) {
        final String SQL_QUERY = "DELETE FROM dictionary WHERE target = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
            ps.setString(1, target);
            try {
                int deletedRows = ps.executeUpdate();
                if (deletedRows == 0) {

                }
            } finally {
                close(ps);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public String lookUpWord(String target) {
        final String SQL_QUERY = "SELECT definition FROM dictionary WHERE target = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
            ps.setString(1, target);
            try {
                ResultSet rs = ps.executeQuery();
                try {
                    if (rs.next()) {
                        return rs.getString("definition");
                    } else {
                        return "404";
                    }
                } finally {
                    close(rs);
                }
            } finally {
                close(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "404";
    }

}





