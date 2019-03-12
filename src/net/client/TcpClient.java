package net.client;

import protocol.ProtocolRequest;
import protocol.ProtocolResponse;

import java.io.*;
import java.net.Socket;

public class TcpClient implements Closeable {
    private Socket socket;
    BufferedWriter bw;
    BufferedReader br;

    public TcpClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        System.out.println("Client starting on port " + socket.getLocalPort());
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    protected ProtocolResponse sendRequest(String type, String data) {
        ProtocolRequest request = ProtocolRequest.of(type, data);
        try {
            bw.write(request.getType());
            bw.newLine();
            bw.write(request.getData());
            bw.newLine();
            bw.flush();
            String responseCode = br.readLine();
            String responseData = br.readLine();
            ProtocolResponse response = ProtocolResponse.of(responseCode, responseData);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        br.close();
        bw.close();
        socket.close();
    }
}
