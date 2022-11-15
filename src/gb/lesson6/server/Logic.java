package gb.lesson6.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Logic {
    final String END_KEY = "/end";
    private DataInputStream inSocket;
    private Scanner inConsole;
    private DataOutputStream out;

    public Logic(Socket socket) {
        try {
            inSocket = new DataInputStream(socket.getInputStream());
            inConsole = new Scanner(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage(String name) {
        new Thread(() -> {
            try {
                while (true) {
                    String strInSocket = inSocket.readUTF();
                    if (strInSocket.equals(END_KEY)) {
                        break;
                    }
                    System.out.println(name + strInSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage() {
          try {
                while (true) {
                    String strInConsole = inConsole.nextLine();
                    out.writeUTF(strInConsole);
                    out.flush();
                    if (strInConsole.equals(END_KEY)) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
