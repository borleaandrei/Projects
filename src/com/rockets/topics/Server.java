package com.rockets.topics;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    class WorkerRunnable implements Runnable{

        Socket clientSocket;

        WorkerRunnable(Socket clientSocket){
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {

                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = bufferedReader.readLine();
                System.out.println("RECEIVED: "+line);
                String[] args = line.split(" ");
                if(args[0].equals("getMessages")){
                    OutputStream outputStream = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    System.out.println(topics.getMessages("topic1",-1));
                    writer.println(topics.getMessages("topic1",-1));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Topics topics = new Topics();
    private int          serverPort   = 8080;
    private ServerSocket serverSocket = null;
    private boolean      isStopped    = false;

    public Server(int port){
        this.serverPort = port;
    }

    public void run(){

        topics.publishMessage(new TopicMessage("50", "cotent1 ", "topic1"));
        topics.publishMessage(new TopicMessage("50", "cotent2 ", "topic1"));
        topics.publishMessage(new TopicMessage("50", "cotent3 ", "topic1"));

        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                e.printStackTrace();
            }
            new Thread(
                new WorkerRunnable(clientSocket)
            ).start();
        }

    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){

        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
