package controllers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerController {

    public enum RequestType {
        GET("GET", false),
        POST("POST", true),
        PUT("PUT", true);

        private String requestString;
        private Boolean outputFlag;

        RequestType(String requestString, Boolean outputFlag) {
            this.requestString = requestString;
            this.outputFlag = outputFlag;
        }
    }

    private String rootURL = "http://zipcode.rocks:8085";
    private static ServerController svr = new ServerController();

    private ServerController() {
    }

    public static ServerController getInstance() {
        return svr;
    }

    public String MakeURLCall(String path, RequestType t, String jsonStr) {
        if (t == RequestType.GET) {
            return this.get(path);
        } else if (t == RequestType.POST) {
            return this.post(path, jsonStr);
        } else if (t == RequestType.PUT) {
            return this.put(path, jsonStr);
        }
        return null;
    }

    public String get(String path) {
        return this.readResponse(
                openConnection(path, RequestType.GET)
        );
    }

    public String post(String path, String jsonString) {
        HttpURLConnection connection = this.sendRequest(
                openConnection(path, RequestType.POST),
                jsonString
        );
        return this.readResponse(connection);
    }

    public String put(String path, String jsonString) {
        HttpURLConnection connection = this.sendRequest(
                openConnection(path, RequestType.PUT),
                jsonString
        );
        return this.readResponse(connection);
    }

    public HttpURLConnection openConnection(String path, RequestType t) {
        try {
            URL url = new URL(this.rootURL + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(t.outputFlag);
            connection.setRequestMethod(t.requestString);
            return connection;
        } catch (MalformedURLException m) {
            System.out.println("MalformedULRException in OpenConnection method");
        } catch (IOException i) {
            System.out.println("IO Exception in OpenConnection method");
        };

        return null;
    }

    public HttpURLConnection sendRequest(HttpURLConnection connection, String jsonString) {
        try {
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            byte[] input = jsonString.getBytes("UTF-8");
            out.write(input, 0, input.length);
            out.flush();
            out.close();
            return connection;
        } catch (IOException i) {
            System.out.println("IOException in sendRequest method");
        }
        return null;
    }

    public String readResponse(HttpURLConnection connection) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuffer content = new StringBuffer();
            content.append(in.readLine());
            in.close();
            return content.toString();
        } catch (IOException i) {
            System.out.println("IO Exception in readResponse method");
            i.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }


}