package com.alphamedia.rutilahu;

import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

        ImageView fotopenerima = (ImageView) findViewById(R.id.fotopenerima);
        TextView textView = (TextView) findViewById(R.id.nama);
        final TextView ktp = (TextView) findViewById(R.id.ktp);

        //EditText ktp = (EditText) findViewById(R.id.ktp);
        realmConfiguration = new RealmConfiguration.Builder(this).build();

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        jpegCallback = new PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    // System.currentTimeMillis()
                    outStream = new FileOutputStream(Config.FOTO_DIR + String.format("%s-fotopenerima.jpg", ktp.getText().toString()));
                    outStream.write(data);
                    outStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
                Toast.makeText(getApplicationContext(), "Picture Saved", Toast.LENGTH_LONG).show();
                refreshCamera();
            }
        };

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
        iv = (ImageView) findViewById(R.id.fotopenerima);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bitmap bp = (Bitmap) data.getExtras().get("data");
        //iv.setImageBitmap(bp);
    }

    /*
    PictureCallback jpegCallback = new PictureCallback() {
        long tt;

        public void onPictureTaken(byte[] data, Camera camera) {
            FileOutputStream outStream = null;
            if (data != null)
            {
                //Intent mIntent = new Intent();
                try {
                    tt = System.currentTimeMillis();
                    String photoPath = String.format("/sdcard/DCIM/%d.jpg", tt);
                    outStream = new FileOutputStream(photoPath);
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 7;
                    Bitmap e = BitmapFactory.decodeByteArray(data, 0, data.length, opt);
                    Bitmap thumb = Bitmap.createScaledBitmap (e, e.getWidth(), e.getHeight(), false);
                    thumb.compress(Bitmap.CompressFormat.JPEG, 70, outStream);
                } catch (FileNotFoundException e) {
                    //e.printStackTrace();
                } catch (IOException e) {
                    //e.printStackTrace();
                } finally {
                }
                SystemClock.sleep(200);
                camera.startPreview();
                //setResult(FOTO_MODE,mIntent);
                Log.d(TAG, "onPictureTaken - jpeg");
            }
        }

    };
    */

}

