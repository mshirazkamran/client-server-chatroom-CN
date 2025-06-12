import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            // Read prompt from server
            System.out.print(reader.readLine());
            String name = console.readLine();
            writer.write(name + "\n");
            writer.flush();

            // Thread to listen to messages from server
            new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = reader.readLine()) != null) {
                        System.out.println(serverMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Main thread sends messages
            String input;
            while ((input = console.readLine()) != null) {
                writer.write(input + "\n");
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
