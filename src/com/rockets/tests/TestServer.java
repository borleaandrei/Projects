package com.rockets.tests;

import com.rockets.topics.Server;

public class TestServer {

    public static void main(String[] argv){

        Server server = new Server(8080);
        Thread runServer = new Thread(server);
        runServer.start();
    }
}
