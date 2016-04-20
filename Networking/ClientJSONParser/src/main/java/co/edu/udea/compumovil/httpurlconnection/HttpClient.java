package co.edu.udea.compumovil.httpurlconnection;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jaiber on 20/04/16.
 */
public class HttpClient {

    private final String TAG = "HttpClient";

    // Get your own user name at http://www.geonames.org/login
    private final String USER_NAME = "aporter";
    private final String URL = "http://api.geonames.org/earthquakesJSON?north=20&south=-20&east=-60&west=-80&username="
            + USER_NAME;


    public String getJSONData() {

        String data = "";
        HttpURLConnection httpUrlConnection = null;

        try {
            httpUrlConnection = (HttpURLConnection) new URL(URL)
                    .openConnection();

            InputStream in = new BufferedInputStream(
                    httpUrlConnection.getInputStream());

            data = readStream(in);

        } catch (MalformedURLException exception) {
            Log.e(TAG, "MalformedURLException");
        } catch (IOException exception) {
            Log.e(TAG, "IOException");
        } finally {
            if (null != httpUrlConnection)
                httpUrlConnection.disconnect();
        }
        return data;

    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer("");
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }


}
