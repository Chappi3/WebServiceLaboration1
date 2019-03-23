package se.iths.httpserver;

import java.io.File;
import java.net.Socket;

public class Server implements Runnable {

    static final File WEB_ROOT = new File(".");
    static final String DEFAULT_FILE = "index.html";
    static final String FILE_NOT_FOUND = "404.html";
    static final String METHOD_NOT_SUPPORTED = "not_supported.html";
    static final String HELLO = "/hello";
    static final int PORT = 9000;
    static final boolean verbose = true;
    private Socket socket;

    public Server() {

    }

    public static void main(String[] args) {

    }

    @Override
    public void run() {

    }
}
