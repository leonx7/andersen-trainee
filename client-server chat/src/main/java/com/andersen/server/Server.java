package com.andersen.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int PORT = 3443;
    private List<ClientHandler> clients = new ArrayList<>();

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("The Server is running");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("The Server is stopped");
        }
    }

    public void sendMessageToAllClients(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
