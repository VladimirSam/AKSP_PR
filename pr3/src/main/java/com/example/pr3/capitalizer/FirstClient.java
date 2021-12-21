package com.example.pr3.capitalizer;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FirstClient {
    public static void main(String[] args) {
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner scan = new Scanner(System.in);
        try{
            clientSocket = new Socket("127.0.0.1", 59898);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while (true){
                        msg = scan.nextLine();
                        out.println(msg);
                        out.flush();
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
                        while(msg!=null){
                            System.out.println("Server: " + msg);
                            msg = in.readLine();
                        }
                        System.out.println("Server didn't response");
                        out.close();
                        clientSocket.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            receiver.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
