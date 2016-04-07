package ir.ac.iust;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by meraj on 3/11/16.
 */
public interface IMessageProcessor {
    void process(Packet packet) throws InvalidProtocolBufferException;
}
