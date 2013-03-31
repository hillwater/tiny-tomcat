package org.mystudy.tiny_tomcat.connect;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.mystudy.tiny_tomcat.container.ServletProcessor;
import org.mystudy.tiny_tomcat.http.HttpRequest;
import org.mystudy.tiny_tomcat.http.HttpResponse;

public class HttpProcessor {
    private final Logger logger = Logger.getLogger(getClass());

    private final HttpConnector httpConnector;
    private HttpRequest request;
    private HttpResponse response;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {
        InputStream input = null;
        OutputStream output = null;

        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();

            // create HttpRequest object and parse
            request = new HttpRequest(input);
            response = new HttpResponse(output);

            response.setRequest(request);

            response.setHeader("Server", "Pyrmont Servlet Container");

            parseRequest(input, output);
            parseHeader(input);

            ServletProcessor processor = new ServletProcessor();
            processor.process(request, response);

            socket.close();

        } catch (Exception e) {
            logger.error("process error.", e);
        }
    }

    private void parseRequest(InputStream inputStream, OutputStream outputStream) {

    }

    private void parseHeader(InputStream inputStream) {

    }
}
