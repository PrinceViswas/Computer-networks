import java.io.*;
import java.net.*;
import java.util.*;

class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(1309);
            System.out.println("Server is running...");

            // DNS-like mapping
            Map<String, String> dnsMap = new HashMap<>();
            dnsMap.put("165.165.80.80", "www.aptitudeguru.com");
            dnsMap.put("165.165.79.1", "www.downloadcyclone.blogspot.com");

            while (true) {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receiver = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                server.receive(receiver);

                String query = new String(receiver.getData(), 0, receiver.getLength()).trim();
                InetAddress clientAddress = receiver.getAddress();
                int clientPort = receiver.getPort();

                String response = "Not found";

                if (dnsMap.containsKey(query)) {
                    response = dnsMap.get(query);
                } else if (dnsMap.containsValue(query)) {
                    for (Map.Entry<String, String> entry : dnsMap.entrySet()) {
                        if (entry.getValue().equals(query)) {
                            response = entry.getKey();
                            break;
                        }
                    }
                }

                byte[] sendBuffer = response.getBytes();
                DatagramPacket sender = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                server.send(sender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}