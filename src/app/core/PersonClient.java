package app.core;

import net.client.TcpClient;
import protocol.ProtocolResponse;

import java.io.IOException;

public class PersonClient extends TcpClient {

    public PersonClient(String host) throws IOException {
        super(host, PersonApi.PORT);
    }

    public String sendAddPersonRequest(Person data) {
        ProtocolResponse response = sendRequest(PersonApi.RequestType.ADD_PERSON, data.toStrData());
        return response.getData();
    }

    public String sendRemovePerson(int id) {
        ProtocolResponse response = sendRequest(PersonApi.RequestType.REMOVE_PERSON, String.valueOf(id));
        return response.getData();
    }

    public String sendGetPersonById(int id) {
        //TODO need optimization
        ProtocolResponse response = sendRequest(PersonApi.RequestType.GET_BY_ID, String.valueOf(id));
        String res = response.getCode() == ProtocolResponse.Code.OK ? Person.fromString(response.getData()).toString() : response.getData();
        return res;
    }

}
