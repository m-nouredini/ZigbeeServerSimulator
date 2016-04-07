package ir.ac.iust;

/**
 * Created by meraj on 3/11/16.
 */
public class Packet {
    public Header header;
    public byte[] data;
    private static final short PKT_HEADER_LEN_FIELD = 0;
    private static final short PKT_HEADER_SUBSYS_FIELD = 2;
    private static final short PKT_HEADER_CMDID_FIELD = 3;
    private static final short PKT_HEADER_DATA_FIELD = 4;

    public Packet(byte[] buffer, int readLenght) {
        header = new Header();
        int highByte = buffer[PKT_HEADER_LEN_FIELD + 1] & 0xFF;
        int lowByte = buffer[PKT_HEADER_LEN_FIELD] & 0xFF;
        header.packetLength = (short) ( lowByte + (highByte<< 8));

        if (header.packetLength != readLenght) {
            System.out
                    .println("parseIncoming: packet header len does not match number of bytes read from the socket");
            return;
        }

        header.subSys = buffer[PKT_HEADER_SUBSYS_FIELD];
        header.cmdId = buffer[PKT_HEADER_CMDID_FIELD];

        for (int pktIdx = 0; pktIdx < header.packetLength; pktIdx++) {
            data[pktIdx] = buffer[PKT_HEADER_DATA_FIELD + pktIdx];
        }
    }

    public class Header{
        public int packetLength;
        public byte subSys;
        public byte cmdId;
    }
}
