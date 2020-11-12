package com.andersen.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 3443;
    private static int clients_count;
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = clientSocket;
            this.outMessage = new PrintWriter(clientSocket.getOutputStream());
            this.inMessage = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            server.sendMessageToAllClients("Новый участник вошёл в чат!");
            server.sendMessageToAllClients("Клиентов в чате = " + clients_count);

            while (true) {
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();
                    if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }
                    System.out.println(clientMessage);
                    server.sendMessageToAllClients(clientMessage);
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }

    public void sendMessage(String message) {
        try {
            outMessage.println(message);
            outMessage.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Количество участников: " + clients_count);
    }
}
