import java.io.*;
import java.net.*;
public class SocketHTTPClient{
    public static void main(String[] args){
        try{
            Socket socket = new Socket("www.martinbroadhurst.com",80);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("GET/HTTP/1.1");
            out.println("HOST:www.martinboardhurst.com");
            out.println("");
            Buffered Readerin = new Buffred Reader (new InputStream Reader (socket.getInputStream()));
            String response Line;
            while((response Line = in.readLine())! = null){
                System.out.println(response Line);
            }
            in.close();
            out.close();
            socket.close();
        } catch(Exception e){
            e.printStackTrace():
        }
    }
}