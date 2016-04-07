package ir.ac.iust.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by meraj on 3/11/16.
 */
public class Device {
    public int id;
    public long IEEEAddress;
    public List<Endpoint> endpoints;

    public static long newDevice(){
        long randomAddress = System.currentTimeMillis();
        try {
            DBHandler.getInstance().executeSql("INSERT INTO Device VALUES("+randomAddress+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Endpoint.newEndpoint(randomAddress, 8);
        return randomAddress;
    }

}
