package dev.anzala.eventmanagementsystem.Configuration;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DatabaseConfig {
//    String csvFile = "https://drive.google.com/file/d/1sZXyOT_V1NcZj3dDQIKY9Ea_W7XdGum_/view";
//    String line;
//    String csvSplitBy = ",";
//
//    // JDBC URL, username, and password of MySQL server
//    String url = "jdbc:mysql://localhost:3306/EventManagementSystem";
//    String user = "your_username";
//    String password = "your_password";
//
//    public void loadData() {
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//
//            // SQL query to insert event data
//            String sql = "INSERT INTO events (event_name, city_name, date, time, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            while ((line = br.readLine()) != null) {
//                // Split the line by comma
//                String[] data = line.split(csvSplitBy);
//
//                // Extract information
//                String eventName = data[0];
//                String cityName = data[1];
//                String date = data[2];
//                String time = data[3];
//                double latitude = Double.parseDouble(data[4]);
//                double longitude = Double.parseDouble(data[5]);
//
//                // Set parameters for the prepared statement
//                statement.setString(1, eventName);
//                statement.setString(2, cityName);
//                statement.setString(3, date);
//                statement.setString(4, time);
//                statement.setDouble(5, latitude);
//                statement.setDouble(6, longitude);
//
//                // Execute the SQL statement
//                statement.executeUpdate();
//            }
//            System.out.println("Data imported successfully.");
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public DatabaseConfig() throws SQLException, FileNotFoundException, IOException {
//        loadData();
//    }
}
