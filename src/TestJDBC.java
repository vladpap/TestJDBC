import java.sql.*;

/**
 * Created by vladimirpapin on 02.07.16.
 */
public class TestJDBC {

//    JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/article";

//    Databse credentioals
    static final String USER = "root";
    static final String PASS = "flamingo";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connectiong to  database");

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement ....");
            statement = connection.createStatement();
            String sqlQuery;
            sqlQuery = "select room_number from table3 where phone_id=(select phone_id from table2 where user_id=(select user_id from table1 where username='qux'))";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

//            Print result
            while (resultSet.next()) {
//                int id             = resultSet.getInt("user_id");
//                String userName    = resultSet.getString("user_name");
//                String userSurname = resultSet.getString("user_surname");
//                String userLogin   = resultSet.getString("user_login");
//                String userPasswd  = resultSet.getString("user_passwd");
//                String userEmail   = resultSet.getString("user_email");
//                System.out.println("ID: " + id + "\tuser name: " + userName + "\tuser surname: " + userSurname +
//                "\t\tuser login: " + userLogin + "\t\tuser password: " + userPasswd + "\t\tuser email: " + userEmail);
                System.out.println("ID: " + resultSet.getInt("room_number"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se3) {
                se3.printStackTrace();
            }
        }
    }
}

