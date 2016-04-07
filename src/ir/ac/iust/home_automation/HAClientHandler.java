package ir.ac.iust.home_automation;

import ir.ac.iust.AbstractClientHandler;
import ir.ac.iust.Packet;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by meraj on 3/11/16.
 */
public class HAClientHandler extends AbstractClientHandler {

    public HAClientHandler(Socket socket) throws IOException{
        super(socket);
        this.messageProcessor = new HAMessageProcessor();
    }
}
