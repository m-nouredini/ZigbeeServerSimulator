package ir.ac.iust.network_manager;

import ir.ac.iust.AbstractClientHandler;
import ir.ac.iust.Packet;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by meraj on 3/11/16.
 */
public class NMClientHandler extends AbstractClientHandler {

    public NMClientHandler(Socket socket) throws IOException {
        super(socket);
        this.messageProcessor = new NMMessageProcessor();
    }

}
