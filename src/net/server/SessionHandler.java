package net.server;

import protocol.Protocol;
import protocol.ProtocolRequest;
import protocol.ProtocolResponse;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SessionHandler {
    Socket socket;
    Protocol protocol;


    public SessionHandler(Socket socket, Protocol protocol) {
        this.socket = socket;
        this.protocol = protocol;
    }

    public void run() {
        try (Socket socket = this.socket;
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            while (true) {
                String type = br.readLine();
                String data = br.readLine();
                System.out.println("Client: " + socket.getRemoteSocketAddress() + "\t");
                ProtocolResponse response = protocol.getResponse(ProtocolRequest.of(type, data));
                System.out.println("\t" + response.getCode() + " :: " + response.getData());
                bw.write(response.getCode().toString());
                bw.newLine();
                bw.write(response.getData());
                bw.newLine();
                bw.flush();
            }
        } catch (SocketException ex) {
            System.out.println("Client close connection");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
