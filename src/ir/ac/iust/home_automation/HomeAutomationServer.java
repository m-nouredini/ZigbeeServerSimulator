package ir.ac.iust.home_automation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by meraj on 3/11/16.
 */
public class HomeAutomationServer implements Runnable
{
    private ServerSocket haServerSocket;

    /**
     * Home Automation Port Number
     */
    final private int HA_PORT = 2541;

    private HomeAutomationServer() throws IOException {
        this.haServerSocket = new ServerSocket(HA_PORT);
    }


    @Override
    public void run() {
        try {
            while (true) {
                Socket haClient = haServerSocket.accept();
                HAClientHandler handler = new HAClientHandler(haClient);
                handler.listenAndHandleRequests();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

