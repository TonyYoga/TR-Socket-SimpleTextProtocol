package app.core;

import net.client.TcpClient;

import java.io.IOException;

public class PersonClient extends TcpClient {

    public PersonClient(String host) throws IOException {
        super(host, PersonApi.PORT);
    }

    public String sendAddPersonRequest(Person data) {
        return sendRequest(PersonApi.RequestType.ADD_PERSON, data.toString());
    }

    public String sendRemovePerson(int id) {
        return sendRequest(PersonApi.RequestType.REMOVE_PERSON, String.valueOf(id));
    }

    public Person sendGetPersonById(int id) {
        return sendRequest(PersonApi.RequestType.GET_BY_ID, String.valueOf(id));
    }

}
