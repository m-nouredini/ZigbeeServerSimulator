package ir.ac.iust.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by meraj on 3/11/16.
 */
public class Endpoint {
    public int id;
    public long deviceIEEEAddress;
    public int endpointNumber;
    public int status;
    public List<Cluster> clusters;

    public Endpoint(long deviceIEEEAddress, int endpointNumber) {
        this.deviceIEEEAddress = deviceIEEEAddress;
        this.endpointNumber = endpointNumber;
    }

    public Endpoint() {
    }

    public static void newEndpoint(long deviceIEEEAddress, int endpointNumber) {
        try {
            DBHandler.getInstance()
                    .executeSql("INSERT INTO Endpoint VALUES(" + deviceIEEEAddress +
                            ","+endpointNumber+",0)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void SetEndpointStatus(long deviceIEEEAddress, int endpointNumber, int status){
        try {
            DBHandler.getInstance().executeSql("UPDATE Endpoint SET "
                    + "Status=" + status
                    + "WHERE IEEEAddress="+deviceIEEEAddress
                    + "AND EndpointNumber="+endpointNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
