import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6666);
             Socket socket = serverSocket.accept();
             DataInputStream din = new DataInputStream(socket.getInputStream());
             DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
             Scanner input = new Scanner(System.in)) {

            String sendData = "";
            String receiveData = "";

            while (!receiveData.equalsIgnoreCase("stop")) {
                receiveData = din.readUTF();
                System.out.println("CLIENT SAYS: " + receiveData);

                if (!receiveData.equalsIgnoreCase("stop")) {
                    System.out.print("TO CLIENT: ");
                    sendData = input.nextLine();
                    dout.writeUTF(sendData);
                }
            }

            System.out.println("Chat ended by client.");

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6666);
             DataInputStream din = new DataInputStream(socket.getInputStream());
             DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
             Scanner input = new Scanner(System.in)) {

            String sendData = "";
            String receiveData = "";

            while (!sendData.equalsIgnoreCase("stop")) {
                System.out.print("TO SERVER: ");
                sendData = input.nextLine();
                dout.writeUTF(sendData);

                if (!sendData.equalsIgnoreCase("stop")) {
                    receiveData = din.readUTF();
                    System.out.println("SERVER SAYS: " + receiveData);
                }
            }

            System.out.println("Chat ended by client.");

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
