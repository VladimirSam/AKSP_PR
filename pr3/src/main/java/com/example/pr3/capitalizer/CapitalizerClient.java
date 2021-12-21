package com.example.pr3.capitalizer;


import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CapitalizerClient {
    public static void main(String[] args) throws Exception {
        try (var socket = new Socket(args[0], 59898)) {
            System.out.println("Введите строки текста, затем Ctrl + D или Ctrl + C, чтобы выйти");
            var scanner = new Scanner(System.in);
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                out.println(scanner.nextLine());
                if (in.hasNextLine())
                    System.out.println(in.nextLine());
            }
        }
    }
}
