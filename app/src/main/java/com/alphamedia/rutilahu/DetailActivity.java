package com.alphamedia.rutilahu;

import android.app.Dialog;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends ActionBarActivity implements SurfaceHolder.Callback {

    private RealmConfiguration realmConfiguration;

    private Realm realm;

    Camera camera;

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    PictureCallback jpegCallback;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*
        TextView textView = (TextView) findViewById(R.id.nama);
        final TextView ktp = (TextView) findViewById(R.id.ktp);
        */

        //EditText ktp = (EditText) findViewById(R.id.ktp);
        realmConfiguration = new RealmConfiguration.Builder(this).build();

        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("SUDAH DICATAT");
        spinnerArray.add("BELUM DICATAT");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(spinnerArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        realm = Realm.getInstance(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
        //Realm.deleteRealm(realmConfiguration);
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

    public void btnFotoPenerima(View view) {
    }

    public void fotoTampakDepan(View view) {
    }

    public void fotoTampakSamping1(View view) {
    }

    public void fotoTampakSamping2(View view) {
    }

    public void fotoDapur(View view) {
    }

    public void fotoJamban(View view) {
    }

    public void fotoSumberAir(View view) {
    }

    public void btnSimpan(View view) {
    }

    public void btnBack(View view) {
    }

    public void captureImage(View v) throws IOException {
        camera.takePicture(null, null, jpegCallback);
    }

    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            camera.stopPreview();
        }

        catch (Exception e) {
        }

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }
        catch (Exception e) {
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            camera = Camera.open();
        }

        catch (RuntimeException e) {
            System.err.println(e);
            return;
        }

        Camera.Parameters param;
        param = camera.getParameters();
        param.setPreviewSize(352, 288);
        camera.setParameters(param);

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }

        catch (Exception e) {
            System.err.println(e);
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public void fotoPenerima(View view) {
        /*
        iv = (ImageView) findViewById(R.id.fotopenerima);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bitmap bp = (Bitmap) data.getExtras().get("data");
        //iv.setImageBitmap(bp);
    }

    private void showImage(String fld, String img, String txt)
    {
        final Dialog dialog = new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.imageview);
        dialog.setTitle("Display Image "+ txt);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(txt);

        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        String imguri = Config.FOTO_DIR + fld + img;
        image.setImageURI(Uri.parse(imguri));

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}

