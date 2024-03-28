package org.wecancodeit.virtualpet4.Repositories;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ClientHttp {

    private final String baseUrlString;

    public ClientHttp(String baseUrlString) {
        this.baseUrlString = baseUrlString;
    }

    protected String getUrl(String urlString) throws Exception { // protected - only an inherited class can use this
        HttpURLConnection connection = null; // if it errors out, it will skip try/catch and go to the exception
        StringBuffer response = new StringBuffer(); // just storing vars up here

        try {
            // create a URL object w/ the API endpoint
            URL url = new URL(baseUrlString + urlString);
            connection = (HttpURLConnection) url.openConnection();

            // set request method
            connection.setRequestMethod("GET");

            // set request header if needed
            connection.setRequestProperty("Content-Type", "application/json");

            // get response code
            int responseCode = connection.getResponseCode();

            // if response code is ok (200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // create buffered reader to read response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                // read the response line by line and append to buffer
                while ((inputLine = in.readLine()) != null) { // read the response line by line
                    response.append(inputLine); // and append to buffer
                }
                in.close();

            } else {
                // lets user know of error
                throw new Exception(responseCode + " Response Code from getUrl");

            }

            // close the connection
            connection.disconnect();

        } catch (Exception ex) { // IF it errors out up top, it will skip the whole block of code and come to
                                 // line 57
            ex.printStackTrace();
        } finally {
            if (connection != null) {
            }
            // close the connection
            connection.disconnect();
        }
        return response.toString();
    }

}
