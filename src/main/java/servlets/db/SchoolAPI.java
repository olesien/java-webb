package servlets.db;

import models.Students;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SchoolAPI {

    private SchoolAPI() { }

    public static ArrayList<Students> getStudents() {
        ArrayList<Students> students = new ArrayList<>();
        try {
            Statement statement = Database.getConnection().createStatement();
            String query = "SELECT * FROM students";
            System.out.println(query);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {

                Students student = new Students(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("town"),
                        result.getString("hobby"));

                students.add(student);
            }
        } catch(SQLException ex) {
            Database.PrintSQLException(ex);
        }
        return students;
    }
}
