package in.ghostreborn.test2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainServerAsync extends AsyncTask<Void, Void, ArrayList<String>> {

    ArrayList<String> servers = new ArrayList<>();

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        try{
            URL url = new URL(Constants.HLS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                servers.add(line);
            }
            return servers;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> test) {
        super.onPostExecute(test);
        MainActivity.testText.setText("");

        int i=0;
        test.remove(0);
        while (i<test.size()){
            test.remove(i);
            i++;
        }

        Constants.animeServers = test;
    }
}
