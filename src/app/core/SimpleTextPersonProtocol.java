package app.core;

import protocol.Protocol;
import protocol.ProtocolRequest;
import protocol.ProtocolResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SimpleTextPersonProtocol implements Protocol {
    private Map<String, Function<ProtocolRequest, ProtocolResponse>> mapper;
    private Map<Integer, Person> persons;

    public SimpleTextPersonProtocol() {
        mapper = new HashMap<>();
        persons = new HashMap<>();
        mapper.put(PersonApi.RequestType.ADD_PERSON, this::addPerson);
        mapper.put(PersonApi.RequestType.REMOVE_PERSON, this::removeById);
        mapper.put(PersonApi.RequestType.GET_BY_ID, this::getById);
    }

    @Override
    public ProtocolResponse getResponse(ProtocolRequest request) {
        try {
            String type = request.getType();
            Function<ProtocolRequest, ProtocolResponse> mappedFunction = mapper.get(type);
            if (mappedFunction == null) {
                throw new Exception("Wrong request type " + type);
            }
            return mappedFunction.apply(request);
        } catch (Exception e) {
            return ProtocolResponse.of(ProtocolResponse.Code.BAD_REQUEST.toString(), e.getMessage());
        }
    }


    private ProtocolResponse getById(ProtocolRequest protocolRequest) {
        int id = Integer.parseInt(protocolRequest.getData());
        Person toFind = persons.get(id);
        if (toFind == null) {
            return ProtocolResponse.of(ProtocolResponse.Code.NOT_FOUND.toString(), String.format("No such person with id %d", id));
        }
        return ProtocolResponse.of(ProtocolResponse.Code.OK.toString(), toFind.toString());
    }

    private ProtocolResponse removeById(ProtocolRequest protocolRequest) {
        int id = Integer.parseInt(protocolRequest.getData());
        if (persons.remove(id) == null) {
            return ProtocolResponse.of(ProtocolResponse.Code.NOT_FOUND.toString(), String.format("No such person with id %d", id));
        }
        return ProtocolResponse.of(ProtocolResponse.Code.OK.toString(), String.format("Person with id %d was removed!", id));
    }

    private ProtocolResponse addPerson(ProtocolRequest protocolRequest) {
        Person curr = Person.fromString(protocolRequest.getData());
        if (persons.containsKey(curr.getId())) {
            return ProtocolResponse.of(ProtocolResponse.Code.BAD_REQUEST.toString(), String.format("Person with id %d already added!", curr.getId()));
        }
        return ProtocolResponse.of(ProtocolResponse.Code.OK.toString(), "Person added!");
    }
}
