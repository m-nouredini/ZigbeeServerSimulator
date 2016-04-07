package ir.ac.iust;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by meraj on 3/11/16.
 */
public abstract class AbstractClientHandler {

    protected Socket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected IMessageProcessor messageProcessor;

    public AbstractClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }


    public void listenAndHandleRequests() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(socket.getLocalPort() + " connected");
                byte[] buffer = new byte[4096];
                try {
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    int bytesRead = 0;
                    while (bytesRead != -1) {
                        bytesRead = dataInputStream.read(buffer);
                        if(bytesRead != -1) {
                            processIncomingMessage(buffer, bytesRead);
                        }
                    }
                    System.out.println(socket.getLocalPort() + " closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void processIncomingMessage(byte[] buffer, int bytesRead){
        Packet packet = new Packet(buffer, bytesRead);
        try {
            messageProcessor.process(packet);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(final byte[] message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.write(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

