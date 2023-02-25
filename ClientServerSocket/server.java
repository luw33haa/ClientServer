import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started!");

            while (true) {
                try (Socket socket = server.accept()) {
                    try (BufferedReader writer =
                                 new BufferedReader(
                                         new OutputStreamWriter(
                                                 socket.getOutputStream()))) {
                        try (BufferedReader reader =
                                     new BufferedReader(
                                             new InputStreamReader(
                                                     socket.getInputStream()))) {
                            String reguest = reader.readLine();
                            System.out.println("Request: " + reguest);
                            String response = "HELLO FROM SERVER: " + reguest.length();
                            System.out.println("Response: " + response);
                            writer.writer(response);
                            writer.newLine();
                            writer.flush();

                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}