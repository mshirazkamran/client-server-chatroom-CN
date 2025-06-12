import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {

    private static int PORT;
    //Thread safe CopyOnWriteArrayList is used from java.util.concurrent package
    private static final List<ClientModeller> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a port number to run server");
        PORT = scan.nextInt();
        System.out.println("Server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());

                // Create a thread for each client
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handleClient(socket);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

            // Read client's name
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(bos));

            writer.write("Enter your name:\n");
            writer.flush();

            String name = reader.readLine();
            ClientModeller client = new ClientModeller(socket, bis, bos, name);
            clients.add(client);

            System.out.println(name + " joined the chat.");

            // Notify all users
            broadcast(name + " has joined the chat.", client);

            String message;
            while ((message = reader.readLine()) != null) {
                broadcast(name + ": " + message, client);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }

    private static void broadcast(String message, ClientModeller sender) {
        for (ClientModeller client : clients) {
            try {
                if (sender.equals(client)) {
                    continue;
                }
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getBos()));
                writer.write(message + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
