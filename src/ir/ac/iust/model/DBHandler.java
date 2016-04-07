package ir.ac.iust.model;



import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

/**
 * Created by meraj on 3/11/16.
 */
public class DBHandler {
    private static Connection connection;
    private static Statement statement;
    private static String URL = "jdbc:mysql://localhost/Zigbee?charset=utf8";
    private static String Username="Zigbee";
    private static String Password="Zigbee";
    private static Driver myDriver;

    private static DBHandler instance;

    private DBHandler() throws SQLException {
        myDriver = new com.mysql.jdbc.Driver();
    }

    public static DBHandler getInstance() throws SQLException {
        if (instance == null){
            instance = new DBHandler();
        }
        return instance;
    }

    public void initDb() throws SQLException {
        DriverManager.registerDriver(myDriver);
        connection = DriverManager.getConnection(URL, Username, Password);
        statement = connection.createStatement();
        try(BufferedReader br = new BufferedReader(new FileReader("db.sql"))) {
            for(String line; (line = br.readLine()) != null; ) {
                statement.execute(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        statement.close();
        connection.close();
    }

    public void executeSql(String sql) throws SQLException {
        connection = DriverManager.getConnection(URL, Username, Password);
        statement = connection.createStatement();
        statement.execute(sql);
    }

    public ResultSet query(String sql) throws SQLException {
        connection = DriverManager.getConnection(URL, Username, Password);
        statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
