package testapp;

import app.core.PersonApi;
import app.core.SimpleTextPersonProtocol;
import net.server.TcpServer;

import java.io.IOException;

public class serverApp {
    public static void main(String[] args) throws IOException {
        TcpServer personServer = new TcpServer(PersonApi.PORT, new SimpleTextPersonProtocol());
        personServer.run();
    }
}
