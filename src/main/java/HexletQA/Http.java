package HexletQA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Http {

    public static void main(String[] args) {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("https://example.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            response.append("Response Code : " + responseCode + "\n");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            System.out.println(response);
            // => Response Code : 200
            // =>  <!doctype html><html><head>    <title>Example Domain</title> и т.д.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showUrl() {
        try {
            URL url = new URL("https://www.example.com");
            System.out.println("URL: " + url);
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("File: " + url.getFile());
            System.out.println("Ext: " + url.toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
