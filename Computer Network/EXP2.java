import java.io.*;
import java.net.*;

public class SocketHTTPClient {
    public static void main(String[] args) {
        try {
            // Connect to the web server on port 80
            Socket socket = new Socket("www.martinbroadhurst.com", 80);

            // Send HTTP GET request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET / HTTP/1.1");
            out.println("Host: www.martinbroadhurst.com");
            out.println("Connection: close");
            out.println(); // Blank line to end the request

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

            // Close resources
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
