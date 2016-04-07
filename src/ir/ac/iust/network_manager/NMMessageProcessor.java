package ir.ac.iust.network_manager;

import ir.ac.iust.IMessageProcessor;
import ir.ac.iust.Packet;
import ir.ac.iust.model.Device;

/**
 * Created by meraj on 3/11/16.
 */
public class NMMessageProcessor implements IMessageProcessor {
    @Override
    public void process(Packet packet) {
        switch (packet.header.cmdId){
            case nwkmgrPb.nwkMgrCmdId_t.NWK_SET_PERMIT_JOIN_REQ_VALUE:
                long addr = Device.newDevice();
                break;
            default:
                System.out.println("unknown message received");
        }
    }
}
