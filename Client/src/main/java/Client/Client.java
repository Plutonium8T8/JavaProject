package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void sendMessage(String message) {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out =
                     new PrintWriter(socket.getOutputStream(), true)) {
            // Send a request to the server
            out.println(message);
        } catch (IOException e) {
            System.err.println("No server listening... " + e);
        }
    }

    public String recieveMessage() {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (Socket socket = new Socket(serverAddress, PORT);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {
            String returnMessage = in.readLine();
            return returnMessage;
        } catch (IOException e) {
            System.err.println("No server listening... " + e);
        }
        return null;
    }
}

