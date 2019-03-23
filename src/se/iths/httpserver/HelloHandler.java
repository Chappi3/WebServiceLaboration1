package se.iths.httpserver;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static se.iths.httpserver.Server.parseQuery;

public class HelloHandler {

    public void handle(String request, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

        Map<String, Object> parameters = new HashMap<>();
        parseQuery(request, parameters);

        String response = "";
        String name = null;
        for (String key : parameters.keySet()) {
            response += key;
            if (response.contains("name")) {
                name = (String)parameters.get(key);
            }
        }
        if (name == null) {
            response = "Hello, World!";
        }
        else {
            response = "Hello, "+name+"!";
        }

        out.println("HTTP/1.1 200 OK");
        out.println("Date: " + new Date());
        out.println("Content-type: " + "application/json");
        out.println("Content-length: " + response.length());
        out.println();
        out.flush();

        OutputStream os = dataOut;
        os.write(response.getBytes());
        os.close();
    }
}
