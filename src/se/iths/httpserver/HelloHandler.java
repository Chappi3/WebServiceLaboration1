package se.iths.httpserver;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static se.iths.httpserver.Server.parseQuery;

public class HelloHandler {

    public void handle(String method,String request,BufferedReader in, PrintWriter out, BufferedOutputStream dataOut,int contentLength) throws IOException {

        Map<String, Object> parameters = new HashMap<>();
        String response = "";
        String name = "";

        if (method.equals("GET") && request.contains("?")) {
            parseQuery(request, parameters);
            for (String key : parameters.keySet()) {
                response += key;
                if (response.contains("name")) {
                    name = (String)parameters.get(key);
                }
            }
        }

        if (method.equals("POST")) {
            while (true) {
                String nextHeader = in.readLine();
                if (nextHeader.equals(""))
                    break;
            }
            char[] chars = new char[contentLength];
            contentLength= in.read(chars);
            for (int i = 0; i < contentLength;i++) {
                name = name + chars[i];
            }
            name = name.replaceAll("name=", "");
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
