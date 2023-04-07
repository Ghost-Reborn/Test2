package in.ghostreborn.test2;

import android.os.AsyncTask;

public class MainServerAsync extends AsyncTask<Void, Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        MainActivity.testText.setText("DONE!");
    }
}
