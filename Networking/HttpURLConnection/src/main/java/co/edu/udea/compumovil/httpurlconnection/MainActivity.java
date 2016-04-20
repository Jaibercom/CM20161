package co.edu.udea.compumovil.httpurlconnection;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView1);
    }

    public void onClick(View v){

        HttpGetTask task = new HttpGetTask();
        String request = null;

        switch(v.getId()){

            case R.id.buttonJSON:
                    request = "earthquakesJSON";

                break;

            case R.id.buttonXML:
                    request = "earthquakes";
                break;
        }
        task.execute(request);
    }

    private class HttpGetTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "HttpGetTask";

        // Get your own user name at http://www.geonames.org/login
        private static final String USER_NAME = "aporter";
        private static final String PARAMS = "?north=20&south=-20&east=-60&west=-80&username=" + USER_NAME;;
        private String URL = null;

        @Override
        protected String doInBackground(String... params) {
            String data = "";
            HttpURLConnection httpUrlConnection = null;
            String request = params[0];
            URL = "http://api.geonames.org/"+request+PARAMS;

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

        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
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

}
