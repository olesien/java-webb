package models.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseAdmin {
    /*
        models.db.Database Configuration
     */
    static String url = "localhost";
    static int port = 3307;
    static String database = "gritacademy";
    static String username = "grit";
    static String password = "grit";

    /*
        Private variables
     */
    private static DatabaseAdmin db;

    private MysqlDataSource dataSource;

    private DatabaseAdmin() { }

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
            db = new DatabaseAdmin();
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