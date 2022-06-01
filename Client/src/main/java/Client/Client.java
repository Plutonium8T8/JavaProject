package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String serverAddress = "127.0.0.1"; // The server's IP address
    private int PORT = 8100; // The server's port

    public String sendMessage(String message) throws IOException {
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Send a request to the server
            out.println(message);
            out.flush();
            System.out.println(message);
            String returnMessage = in.readLine();
            System.out.println(returnMessage);
            return returnMessage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

