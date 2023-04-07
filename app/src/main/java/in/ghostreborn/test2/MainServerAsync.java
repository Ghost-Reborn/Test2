package in.ghostreborn.test2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    Context context;

    public MainServerAsync(Context context){
        this.context = context;
    }

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

        Button button = new Button(context);
        button.setText("SERVER 1");
        button.setOnClickListener(view -> {
            Log.e("TAG", "SERVER: " + Constants.animeServers.get(0));
        });

        MainActivity.testLinearLayout.addView(button);

    }
}
