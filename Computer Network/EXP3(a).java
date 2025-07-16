import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner input = new Scanner(System.in);
            String senddata = "";
            String receivedata = "";
            while (!receivedata.equals("stop")) {
                receivedata = din.readUTF();
                System.out.println("CLIENT SAYS: " + receivedata);
                System.out.println("TO CLIENT:");
                senddata = input.nextLine();
                dout.writeUTF(senddata);
            }
            din.close();
            dout.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    public static void main(String arg[]) {
        try {
            Socket s = new Socket("localhost", 6666);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner input = new Scanner(System.in);
            String senddata = "";
            String receivedata = "";
            while (!senddata.equals("stop")) {
                System.out.println("TO SERVER:");
                senddata = input.nextLine();
                dout.writeUTF(senddata);
                receivedata = din.readUTF();
                System.out.println("SERVER SAYS: " + receivedata);
            }
            din.close();
            dout.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
