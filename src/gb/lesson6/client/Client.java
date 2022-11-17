package gb.lesson6.client;

import gb.lesson6.server.Logic;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final int PORT = 8189;
    private static final String SERVER_ADDRESS = "localhost";
    private static final String NAME_FROM = "Server: ";

    public static void main(String[] args) {

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            System.out.println("Connected to server");
            Logic logic = new Logic(socket);
            logic.getMessage(NAME_FROM);
            logic.sendMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
