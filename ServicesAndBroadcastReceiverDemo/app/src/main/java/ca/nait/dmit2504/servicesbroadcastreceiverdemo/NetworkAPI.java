package ca.nait.dmit2504.servicesbroadcastreceiverdemo;

import android.net.Uri;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

// https://developer.android.com/reference/java/net/HttpURLConnection?hl=en
public class NetworkAPI {

    public void postFormData(String urlString, Map<String, String> formData) throws IOException {
        // Step 1: Create a URL object
        URL url = new URL(urlString);
        // Step 2: Open a connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            // Step 3: Set the request method
            connection.setRequestMethod("POST");
            // Step 4: Set the Request content-type header parameter
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // Step 5: Set the Response format type
            connection.setRequestProperty("Accept","text/plain");
            // Step 6: Enable the connection to send output
            connection.setDoOutput(true);
//            connection.setChunkedStreamingMode(0);    // do this when the content length is not known in advance
            // Step 7: Create the request body
            // Convert the key-value pairs from the formData map to a query string in the format "paramName1=param1Value&paramName2=param2Value"
            StringBuilder requestBodyBuilder = new StringBuilder();
            // Lambdas expressions requires Java 8 support and API 24+
//            formData.forEach((key, value) -> {
//                if (requestBodyBuilder.length() > 0) {
//                    requestBodyBuilder.append("&");
//                }
//                requestBodyBuilder.append(String.format("%s=%s", key, Uri.encode(value, ,"utf-8")));
//            });
            for (Map.Entry<String, String> entry : formData.entrySet()) {
                if (requestBodyBuilder.length() > 0) {
                    requestBodyBuilder.append("&");
                }
                requestBodyBuilder.append(String.format("%s=%s", entry.getKey(), Uri.encode(entry.getValue(), "utf-8")));
            }

            String requestBody = requestBodyBuilder.toString(); //String.format("LOGIN_NAME=%s&DATA=%s", loginName, data);

            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            out.write(requestBody.getBytes("utf-8"));
            out.close();

            int responseCode = connection.getResponseCode();
            if (responseCode!= HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage()
                        + " with " + urlString);
            }

            // Step 8: Read the response from input stream
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ( (responseLine = reader.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            if (responseLine != null) {
//                System.out.println(response.toString());
//            }

        } finally {
            connection.disconnect();
        }
    }

    public byte[] getUrlResponseBytes(String urlString)
            throws IOException {
        // Step 1: Create a URL object
        URL url = new URL(urlString);
        // Step 2: Ope a connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            // Step 3: Set the request method
            connection.setRequestMethod("GET");
            // Step 4: Set the Request content-type header parameter
            connection.setRequestProperty("Content-Type","text/plain, text/html");
            // Step 5: Set the Response format type
            connection.setRequestProperty("Accept","text/plain");
            // Step 6: Read the response from the input stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            int responseCode = connection.getResponseCode();
            if (responseCode!= HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage()
                        + " with " + urlString);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();

            return out.toByteArray();

        } finally {
            connection.disconnect();
        }
    }


    public String getUrlResponseString(String urlString) throws IOException {
        return new String( getUrlResponseBytes(urlString) );
    }

}
