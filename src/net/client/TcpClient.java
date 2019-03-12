package net.client;

import protocol.ProtocolRequest;
import protocol.ProtocolResponse;

import java.io.*;
import java.net.Socket;

public class TcpClient implements Closeable {
    private Socket socket;
    private InputStreamReader input;
    private OutputStreamWriter output;

    public TcpClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        System.out.println("Client starting on port " + socket.getLocalPort());
        output = new OutputStreamWriter(socket.getOutputStream());
        input = new InputStreamReader(socket.getInputStream());

    }

    protected <T> T sendRequest(String type, String data) {
        ProtocolRequest request = ProtocolRequest.of(type, data);
        try (BufferedWriter bw = new BufferedWriter(output);
             BufferedReader br = new BufferedReader(input))
        {
            bw.write(request.getType());
            bw.newLine();
            bw.write(request.getData());
            bw.newLine();
            ProtocolResponse response = ProtocolResponse.of(br.readLine(), br.readLine());
            return (T) response.getData();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}
