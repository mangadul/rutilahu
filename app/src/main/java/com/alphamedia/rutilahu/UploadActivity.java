package com.alphamedia.rutilahu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alphamedia.rutilahu.api.ApiService;
import com.alphamedia.rutilahu.api.CountingTypedFile;
import com.alphamedia.rutilahu.api.FileType;
import com.alphamedia.rutilahu.api.ProgressListener;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import io.realm.internal.IOException;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


public class UploadActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String filePath = null;
    private ProgressBar progressBar;
    private TextView txtPercentage;

    long totalSize = 0;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Upload Data");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        try {
            SendFileTask sendFile = new SendFileTask(Config.DATA_DIR, FileType.JSON);
            if (Download.isConnected(this))
            {
                sendFile.execute();
            } else
            {
                Toast.makeText(getApplicationContext(), "Kesalahan: Tidak ada jaringan Internet", Toast.LENGTH_LONG).show();
                Log.e("Error","Tidak ada jaringan internet");
            }
        } catch (IOException e)
        {
            Log.e("Error: ", e.getMessage());
        }

        /*
        FileUploadService service = ServiceGenerator.createService(FileUploadService.class, Config.FILE_UPLOAD_URL);
        TypedFile typedFile = new TypedFile("application/json", new File(Config.DATA_DIR));
        String description = "Upload Data JSON dari Android";

        service.upload(typedFile, description, new Callback<String>() {

            @Override
            public void success(String s, Response response) {
                showAlert(response.toString());
                Log.e("Upload", "success " + response.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                showAlert(error.getMessage());
                Log.e("Upload", "error "+error.getMessage());
            }

        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upload, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SendFileTask extends AsyncTask<String, Integer, String> {
        private ProgressListener listener;
        private String filePath;
        private FileType fileType;

        public SendFileTask(String filePath, FileType fileType) {
            this.filePath = filePath;
            this.fileType = fileType;
        }

        @Override
        protected String doInBackground(String... params) {
            File file = new File(filePath);
            totalSize = file.length();
            Log.d("Upload FileSize[%d]", Long.toString(totalSize));
            listener = new ProgressListener() {
                @Override
                public void transferred(long num) {
                    publishProgress((int) ((num / (float) totalSize) * 100));
                }
            };

            //String _fileType = FileType.JSON.equals(fileType) ? "application/json" : (FileType.JPEG.equals(fileType) ? "image/jpeg" : "*/*");
            return String.valueOf(MyRestAdapter.getService(ApiService.class, Config.FILE_UPLOAD_URL).uploadFile(new CountingTypedFile("application/json", file, listener), "/Mobile Uploads"));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("progress[%d]", Integer.toString(values[0]));
            mProgressDialog.setProgress(values[0]);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mProgressDialog.setMessage("Upload selesai...");
            mProgressDialog.dismiss();
            finish();
        }

    }

    private static class MyRestAdapter {
        public static <S> S getService(Class<S> serviceClass, String baseUrl) {
            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(baseUrl)
                    .setClient(new OkClient(new OkHttpClient()));

            RestAdapter adapter = builder.build();

            return adapter.create(serviceClass);
        }

    }
}
