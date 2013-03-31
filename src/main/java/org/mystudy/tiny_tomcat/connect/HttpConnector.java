package org.mystudy.tiny_tomcat.connect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class HttpConnector implements Runnable {
    private final Logger logger = Logger.getLogger(getClass());

    private boolean stopped;
    private final String scheme = "http";
    private final int port = 8080;

    public String getScheme() {
        return scheme;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getLocalHost());
        } catch (IOException e) {
            logger.error("server socket error.", e);
            System.exit(1);
        }

        while (!stopped) {
            Socket socket = null;

            try {
                socket = serverSocket.accept();
            } catch (Exception e) {
                logger.error("server socket error.", e);
                continue;
            }

            HttpProcessor httpProcessor = new HttpProcessor(this);
            httpProcessor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

}
