package com.rockets.tests;

import java.io.*;
import java.net.Socket;

public class TestClient {

    public static void main(String argv[]) {

        String line = "";

        try {
            Socket socket = new Socket("localhost", 8080);

            OutputStream outputSocket = socket.getOutputStream();
            PrintWriter writerSocket = new PrintWriter(outputSocket, true);

            InputStream inputSocket = socket.getInputStream();
            BufferedReader readerSocket = new BufferedReader(new InputStreamReader(inputSocket));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            line = stdIn.readLine();
            while (!line.equals("stop")) {


                writerSocket.println(line);
                String messages = "";
                line = "";
                while(!line.equals("]")) {
                    messages = messages + line;
                    System.out.println("blocked");
                    line = readerSocket.readLine();
                    System.out.println("here");
                }
                System.out.println("MESSAGES2: "+messages+"]");
                line = stdIn.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
