package snotepad.helper;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author pc-i3 gen4
 */
public class APICall {

    String END_POINT_URL = "http://snpad.fgroupindonesia.com/post/";
    String FINAL_RESULT_URL = null;

    // we obtain the string of translation
    // post it anonymously to web serv
    public void post(File f) {
        
        // 1. we send the upload process by file content
        // with the end point is :
        // snpad.fgroupindonesia/post/ [attached-file]
        // 2. then the server will respond with URL
        // snpad.fgroupindonesia/view/q=7-digit-random

        try {

            URL serverUrl = new URL(END_POINT_URL);
            HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            FileInputStream fileInputStream = new FileInputStream(f);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.flush();
            int responseCode = connection.getResponseCode();
            System.out.println("Server response code: " + responseCode);
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error while uploading into Server End Point! Error code #49.");
        }

    }

}
