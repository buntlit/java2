package gb.lesson6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8189;
    private static final String NAME_FROM = "Client: ";

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started");
            try (Socket socket = server.accept()) {
                System.out.println("Client connected");
                Logic logic = new Logic(socket);
                logic.getMessage(NAME_FROM);
                logic.sendMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
