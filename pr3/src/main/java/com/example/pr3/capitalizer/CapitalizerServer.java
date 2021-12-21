package com.example.pr3.capitalizer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;

public class CapitalizerServer {

    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(59898)) {
            System.out.println("Сервер запущен");
            List<Socket> list = new ArrayList<>();
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                Socket s = listener.accept();
                list.add(s);
                pool.execute(new Capitalizer(s, list));
            }
        }
    }

    private static class Capitalizer implements Runnable {
        final Socket socket;
        Scanner in;
        PrintWriter out = null;
        ArrayList<String> messages = new ArrayList<>();
        private final List<Socket> list;

        Capitalizer(Socket socket, List<Socket> list) throws IOException {
            this.socket = socket;
            in = new Scanner(socket.getInputStream());
            this.list = list;
        }

        @Override
        public void run() {
            try {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (!messages.isEmpty()) {
                            for (Socket s : list) {
                                try {
                                    out = new PrintWriter(s.getOutputStream(), true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                out.println(messages);
                            }
                            messages.clear();
                        }
                    }
                }, 0, 5000);
                while (in.hasNextLine()) {
                    messages.add(in.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Error: " + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {

                }
                System.out.println("Connection closed: " + socket);
            }
        }
    }
}

