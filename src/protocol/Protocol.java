package protocol;

public interface Protocol {
    ProtocolResponse getResponse(ProtocolRequest request);
}
