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

public class SubServerAsync extends AsyncTask<Void, Void, ArrayList<String>> {

    Context context;
    public SubServerAsync(Context context){
        this.context = context;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        try {
            URL url = new URL(Constants.animeServers.get(0));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                Constants.subServers.add(line);
            }
            return Constants.subServers;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);

        Constants.subServers.remove(0);
        Constants.subServers.remove(0);
        Constants.subServers.remove(0);
        Constants.subServers.remove(0);

        int i = 0;
        while (i < strings.size()) {
            Constants.subServers.remove(i);
            i++;
        }

        Button button = new Button(context);
        button.setText("Download TS");
        button.setOnClickListener(view -> new DownloadAsync().execute());
        MainActivity.testLinearLayout.addView(button);

    }
}
