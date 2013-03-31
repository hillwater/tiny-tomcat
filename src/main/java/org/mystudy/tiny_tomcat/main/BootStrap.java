package org.mystudy.tiny_tomcat.main;

import org.mystudy.tiny_tomcat.connect.HttpConnector;

public class BootStrap {

    /**
     * @param args
     */
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();

    }

}
