package ir.ac.iust.network_manager;

import ir.ac.iust.home_automation.HAClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Meraj Nouredini on 3/11/16.
 */
public class NetworkManagerServer implements Runnable {
    private ServerSocket nmServerSocket;

    /**
     * Network Manager Port Number
     */
    final private int NM_PORT = 2540;


    private NetworkManagerServer() throws IOException{
        this.nmServerSocket = new ServerSocket(NM_PORT);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket nmClient = nmServerSocket.accept();
                NMClientHandler handler = new NMClientHandler(nmClient);
                handler.listenAndHandleRequests();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
