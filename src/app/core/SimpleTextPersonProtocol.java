package app.core;

import protocol.Protocol;
import protocol.ProtocolRequest;
import protocol.ProtocolResponse;

import java.util.Map;

public class SimpleTextPersonProtocol implements Protocol {
    private Map<Integer, Person> persons;
    @Override
    public ProtocolResponse getResponse(ProtocolRequest request) {
        return null;
    }
}
