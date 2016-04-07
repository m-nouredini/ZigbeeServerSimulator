package ir.ac.iust.home_automation;

import com.google.protobuf.InvalidProtocolBufferException;
import ir.ac.iust.AbstractClientHandler;
import ir.ac.iust.IMessageProcessor;
import ir.ac.iust.Packet;
import ir.ac.iust.model.Endpoint;

/**
 * Created by meraj on 3/11/16.
 */
public class HAMessageProcessor implements IMessageProcessor{
    AbstractClientHandler handler;

    public HAMessageProcessor(AbstractClientHandler handler) {
        this.handler = handler;
    }

    public void process(Packet packet) throws InvalidProtocolBufferException {
        switch (packet.header.cmdId){
            case hagatewayPb.gwCmdId_t.DEV_SET_ONOFF_STATE_REQ_VALUE:
                hagatewayPb.DevSetOnOffStateReq message = hagatewayPb.DevSetOnOffStateReq.parseFrom(packet.data);
                long ieeeAddr = message.getDstAddress().getIeeeAddr();
                int endpointNo = message.getDstAddress().getEndpointId();
                int status = message.getState().getNumber();
                Endpoint.SetEndpointStatus(ieeeAddr, endpointNo, status);

                final hagatewayPb.GwAttributeReportingInd.Builder
                        gwAttributeReportingInd = hagatewayPb.GwAttributeReportingInd.newBuilder();


                break;
            default:
                System.out.println("unknown message received");
        }
    }
}
