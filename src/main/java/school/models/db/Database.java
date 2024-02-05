package school.models.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    /*
        org.example.project1.servlets.db.Database Configuration
     */
    static String url = "localhost";
    static int port = 3310;
    static String database = "grit";
    static String username = "root";
    static String password = "your_strong_pass";

    /*
        Private variables
     */
    private static Database db;

    private MysqlDataSource dataSource;

    private Database() { }

    private void initializeDataSource() {
        //try {
        dataSource = new MysqlDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);

        //Added convertToNull to prevent db errors when retrieving empty dates. UTF 8 for consistency.
        dataSource.setURL("jdbc:mysql://" + url + ":" + port + "/" + database + "?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8");
    }

    private Connection createConnection() {
        try {
            return dataSource.getConnection();
        } catch(SQLException ex) {
            PrintSQLException(ex);
            return null;
        }
    }

    public static Connection getConnection() {
        if (db == null) {
            db = new Database();
            db.initializeDataSource();
        }
        return db.createConnection();
    }


    public static void PrintSQLException(SQLException sqle) {
        PrintSQLException(sqle, false);
    }
    public static void PrintSQLException(SQLException sqle, Boolean printStackTrace) {
        while(sqle != null) {
            System.out.println("\n---SQLException Caught---\n");
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("ErrorCode: " + sqle.getErrorCode());
            System.out.println("Message: " + sqle.getMessage());
            if(printStackTrace) sqle.printStackTrace();
            sqle = sqle.getNextException();
        }
    }
}