package snotepad.helper;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import raw.Entry;

/**
 * Project : SNotepad
 * File : APICall.java
 * @author FGroupIndonesia
 */
public class APICall {

    private String respond;

    public static void main(String[] args) {
        //APICall testing = new APICall();
        //String end = testing.call();
        //System.out.println(end);
    }

    private String call() {

        String data = "";
        String url = "http://192.168.0.11/test";

        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }

            connection.disconnect();
            data = response.toString();

        } catch (Exception ex) {

        }

        return data;

    }

    //String END_POINT_URL = "http://snpad.fgroupindonesia.com/post";
    String END_POINT_URL = "http://127.0.0.1/post";
    String FINAL_RESULT_URL = null;

    // we obtain the string of translation
    // post it anonymously to web serv
    public void post(File f) {

        // 1. we send the upload process by file content
        // with the end point is :
        // snpad.fgroupindonesia/post/ [attached-file]
        // 2. then the server will respond with URL
        // snpad.fgroupindonesia/view/q=7-digit-random
        String charset = "UTF-8";
        String param = "value";

        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.
        URLConnection connection = null;
        try {
            connection = new URL(END_POINT_URL).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
            // Send normal param.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(param).append(CRLF).flush();

            // Send text file.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"dokumen\"; filename=\"" + f.getName() + "\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
            writer.append(CRLF).flush();
            Files.copy(f.toPath(), output);
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();
            // Request is lazily fired whenever you need to obtain information about response.
            int responseCode = ((HttpURLConnection) connection).getResponseCode();
            System.out.println(responseCode);

            InputStream inputStream;
            StringBuffer response = new StringBuffer();

            if (responseCode == 200) {
                inputStream = connection.getInputStream();

                // Convert the input stream to a string
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

            } else {
                inputStream = ((HttpURLConnection) connection).getErrorStream();
            }

            System.out.println(response);

            this.setRespond(response.toString());

        } catch (Exception ex) {
            System.err.println("Akh!");
            ex.printStackTrace();
        }

    }

    /**
     * @return the respond
     */
    public String getRespond() {
        return respond;
    }

    public Entry getAsRespondObject() {
        Gson gson = new Gson();
        Entry data = gson.fromJson(this.getRespond(), Entry.class);
        return data;
    }

    /**
     * @param respond the respond to set
     */
    public void setRespond(String respond) {
        this.respond = respond;
    }

}
