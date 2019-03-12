package protocol;

public class ProtocolResponse {
    public enum Code {
        OK, BAD_REQUEST, NOT_FOUND
    }
    Code code;
    String data;

    public static ProtocolResponse of(String code, String data) {
        ProtocolResponse res = new ProtocolResponse();
        res.code = Code.valueOf(code);
        res.data = data;
        return res;
    }

    public Code getCode() {
        return code;
    }

    public String getData() {
        return data;
    }
}
