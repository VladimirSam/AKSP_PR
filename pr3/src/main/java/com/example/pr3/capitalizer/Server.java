package com.example.pr3.capitalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        final Socket clientSocket;
        final ServerSocket serverSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner scan = new Scanner(System.in);

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(59898);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread sender = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while (true) {
                        if (list2.size() < list1.size()) {
                            list2.clear();
                            list2.addAll(list1);
                            out.println(list2);
                            out.flush();
                        } else out.println();
                    }
                }
            });
            sender.start();

            Thread receiver = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        list1.add(msg);
                        while (msg != null) {
                            msg = in.readLine();
                            list1.add(msg);
                        }
                        System.out.println("Client disconnected");
                        out.close();
                        clientSocket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
