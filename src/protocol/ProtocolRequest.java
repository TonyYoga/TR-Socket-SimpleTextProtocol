package protocol;

public class ProtocolRequest {
    String type;
    String data;

    public static ProtocolRequest of(String type, String data) {
        ProtocolRequest res = new ProtocolRequest();
        res.data = data;
        res.type = type;
        return res;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
