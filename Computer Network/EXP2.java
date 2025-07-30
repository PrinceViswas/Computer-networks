import java.io.*;
import java.net.*;

public class SocketHTTPClient {
    public static void main(String[] args) {
        String host = "www.martinbroadhurst.com";
        int port = 80;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send HTTP GET request
            out.println("GET / HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: close");
            out.println(); // Blank line to end the request

            // Read and print the response
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

        } catch (IOException e) {
            System.err.println("Error communicating with the server:");
            e.printStackTrace();
        }
    }
}