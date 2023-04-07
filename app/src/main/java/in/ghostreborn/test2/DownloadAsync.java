package in.ghostreborn.test2;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

public class DownloadAsync extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        DownloadManager downloadManager = new DownloadManager();
        try {
            downloadManager.download(Constants.subServers.get(0), "/sdcard/subServer.mp4", (bytesRead, contentLength, done) -> {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    double progress = ((double) bytesRead / contentLength) * 100;
                    Log.e("PROGRESS","PROGRESS: " + progress);
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
