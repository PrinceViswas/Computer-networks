import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            System.out.println("Server is waiting for client...");
            Socket s = ss.accept();
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner input = new Scanner(System.in);

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
            din.close();
            dout.close();
            s.close();
            ss.close();

        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6666);
            System.out.println("Connected to server.");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner input = new Scanner(System.in);

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
            din.close();
            dout.close();
            s.close();

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}