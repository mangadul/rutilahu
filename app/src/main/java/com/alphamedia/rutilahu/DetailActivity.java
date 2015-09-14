package com.alphamedia.rutilahu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends ActionBarActivity {

    private RealmConfiguration realmConfiguration;

    private Realm realm;

    Camera camera;

    SurfaceHolder surfaceHolder;

    private String e_ktp,
            e_nama, e_alamat,
            e_kecamatan, e_kabupaten;

    EditText txtloclong, txtloclat;

    private LocationManager lm;
    private LocationListener locationListener;
    Double loclong = null, loclat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            e_ktp = extras.getString("ktp");
            e_nama = extras.getString("nama");
            e_alamat = extras.getString("alamat");
            e_kecamatan = extras.getString("kecamatan");
            e_kabupaten = extras.getString("kabupaten");
        }

        TextView nama = (TextView) findViewById(R.id.nama);
        TextView ktp = (TextView) findViewById(R.id.ktp);
        TextView alamat = (TextView) findViewById(R.id.alamat);
        TextView kecamatan = (TextView) findViewById(R.id.kecamatan);
        TextView kabupaten = (TextView) findViewById(R.id.kabupaten);

        txtloclong = (EditText) findViewById(R.id.loclong);
        txtloclat = (EditText) findViewById(R.id.loclat);

        ktp.setText(e_ktp);
        nama.setText(e_nama);
        alamat.setText(e_alamat);
        kecamatan.setText(e_kecamatan);
        kabupaten.setText(e_kabupaten);
        set_ktp(e_ktp);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        EditText devid = (EditText) findViewById(R.id.devid);
        devid.setText(deviceId);

        final EditText etPenerima = (EditText) findViewById(R.id.file_foto_penerima);

        ImageButton btnPenerima = (ImageButton) findViewById(R.id.btn_foto_penerimaatas);
        btnPenerima.setOnClickListener(new fotoClick(etPenerima));

        final String imguri = Config.FOTO_DIR + e_ktp + etPenerima.getText().toString();
        Bitmap bitmap = BitmapFactory.decodeFile(imguri);
        btnPenerima.setImageBitmap(bitmap);
        //btnPenerima.setImageURI(Uri.parse(imguri));

        ImageButton imgFotoPenerima = (ImageButton) findViewById(R.id.img_foto_penerima);
        imgFotoPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(imguri, "Foto Penerima");
            }
        });

        Button btnfotoPenerima = (Button) findViewById(R.id.foto_penerima);
        btnfotoPenerima.setOnClickListener(new fotoClick(etPenerima));

        Button btnfotoDepan = (Button) findViewById(R.id.btn_foto_depan);
        final EditText etDepan = (EditText) findViewById(R.id.et_tampak_depan);
        btnfotoDepan.setOnClickListener(new fotoClick(etDepan));

        Button btnfotoSamping1 = (Button) findViewById(R.id.btn_foto_tampak_samping1);
        final EditText etSamping1 = (EditText) findViewById(R.id.foto_tampak_samping1);
        btnfotoSamping1.setOnClickListener(new fotoClick(etSamping1));

        Button btnfotoSamping2 = (Button) findViewById(R.id.btn_foto_tampak_samping2);
        final EditText etSamping2 = (EditText) findViewById(R.id.foto_tampak_samping2);
        btnfotoSamping2.setOnClickListener(new fotoClick(etSamping2));

        Button btnfotoDapur = (Button) findViewById(R.id.btn_foto_dapur);
        final EditText etDapur = (EditText) findViewById(R.id.foto_dapur);
        btnfotoDapur.setOnClickListener(new fotoClick(etDapur));

        Button btnfotoJamban = (Button) findViewById(R.id.btn_foto_jamban);
        final EditText etJamban = (EditText) findViewById(R.id.foto_jamban);
        btnfotoJamban.setOnClickListener(new fotoClick(etJamban));

        Button btnSumberAir = (Button) findViewById(R.id.btn_foto_sumber_air);
        final EditText etSumberAir = (EditText) findViewById(R.id.foto_sumber_air);
        btnSumberAir.setOnClickListener(new fotoClick(etSumberAir));

        final EditText etDevid = (EditText) findViewById(R.id.devid);
        final EditText etloclat = (EditText) findViewById(R.id.loclat);
        final EditText etloclong = (EditText) findViewById(R.id.loclong);

        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("SUDAH DICATAT");
        spinnerArray.add("BELUM DICATAT");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(spinnerArrayAdapter);

        /*
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        */
        try {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationListener = new MyLocationListener();
            lm.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Button btnSimpan = (Button) findViewById(R.id.btnsimpan);
        btnSimpan.setOnClickListener(
            new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    String fsDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                    Realm realm = Realm.getDefaultInstance();

                    // define value
                    String fp = (etPenerima.getText().toString().length() > 0) ? etPenerima.getText().toString() : "";
                    String fsdepan = (etDepan.getText().toString().length() > 0) ? etDepan.getText().toString() : "";
                    String fssamping1 = (etSamping1.getText().toString().length() > 0) ? etSamping1.getText().toString() : "";
                    String fssamping2 = (etSamping2.getText().toString().length() > 0) ? etSamping2.getText().toString() : "";
                    String fsdapur = (etDapur.getText().toString().length() > 0) ? etDapur.getText().toString() : "";
                    String fsjamban = (etJamban.getText().toString().length() > 0) ? etJamban.getText().toString() : "";
                    String fssumberair = (etSumberAir.getText().toString().length() > 0) ? etSumberAir.getText().toString() : "";
                    String fslong = (etloclong.getText().toString().length() > 0) ? etloclong.getText().toString() : "";
                    String fslat = (etloclat.getText().toString().length() > 0) ? etloclat.getText().toString() : "";
                    String sdevid = (etDevid.getText().toString().length() > 0) ? etDevid.getText().toString() : "";

                    if(fp.equals("") || fsdepan.equals("") || fssamping1.equals("") || fssamping2.equals("") || fsdapur.equals("")
                            || fsjamban.equals("") || fssumberair.equals("") || fslong.equals("") || fslat.equals("") || sdevid.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),
                                "Silahkan lengkapi isian terlebih dahulu!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Penerima p = new Penerima();
                        try {
                            realm.beginTransaction();

                            // update realm object
                            p.setKtp(e_ktp);
                            p.setImg_foto_penerima(fp);
                            p.setImg_tampak_depan_rumah(fsdepan);
                            p.setImg_tampak_samping_1(fssamping1);
                            p.setImg_tampak_samping_2(fssamping2);
                            p.setImg_tampak_dapur(fsdapur);
                            p.setImg_tampak_jamban(fsjamban);
                            p.setLongitude(fslong);
                            p.setLatitude(fslat);
                            p.setTgl_update(fsDate);
                            p.setTgl_catat(fsDate);
                            p.setDeviceID(sdevid);
                            p.setIs_catat(true);

                            realm.copyToRealmOrUpdate(p);
                            realm.commitTransaction();

                            Toast.makeText(getApplicationContext(),
                                    new StringBuilder().append("Data ").append(e_ktp).append(" berhasil diupdate").toString(),Toast.LENGTH_SHORT).show();

                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        );

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bitmap bp = (Bitmap) data.getExtras().get("data");
        //iv.setImageBitmap(bp);
    }

    private void showImage(String fileloc, String txt)
    {
        final Dialog dialog = new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.imageview);
        dialog.setTitle("Display Image " + txt);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(txt);

        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        String imguri = Config.FOTO_DIR + fileloc;
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


    private class fotoClick implements View.OnClickListener
    {
        EditText tv;
        String fid = "";
        public fotoClick(EditText tv) {
            this.tv = tv;
        }

        @Override
        public void onClick( View view ){
            if(this.tv.getText().toString().length() > 0 ) this.tv.setText("");
            //fid = this.tv.getText().toString().replace(" ", "_");
            fid = this.tv.getHint().toString().replace(" ", "_");
            Log.i("Ambil Gambar Manual", "fotoClick.onClick()" );
            startCameraActivity(this.tv, setfname(fid));
        }
    }

    protected void startCameraActivity(EditText txtimgname, String fn)
    {
        createDirFoto();
        txtimgname.setText("");
        String dirfoto = get_ktp();
        String _spath = Config.FOTO_DIR + dirfoto + fn + ".jpg";
        Log.i("AmbilGambar", "startCameraActivity()");
        File file = new File(_spath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        //setFotoid(txtimgname.getText().toString());
        txtimgname.setText(fn + ".jpg");
        startActivityForResult( intent, 0 );
    }

    protected String setfname(final String ctn)
    {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DATE);
        int tm = c.get(Calendar.HOUR);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        String pewaktu = Integer.toString(month)+ Integer.toString(year);
        String jam = Integer.toString(date) + "-" + Integer.toString(tm) + Integer.toString(mm) + Integer.toString(ss);
        String fimg = ctn + "-" + pewaktu.toString() + "-" + jam.toString();
        return fimg;
    }

    private void set_ktp(String ktp)
    {
        this.e_ktp = ktp;
    }

    private String get_ktp()
    {
        return this.e_ktp;
    }

    private void createDirFoto()
    {
        String ktp = get_ktp();
        File fr = new File(Config.FOTO_DIR + ktp);
        if(!fr.exists()) {
            fr.mkdirs();
        } else
            Log.d("Error: ", "dir foto already exists");
    }


    private class MyLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
                loclong = loc.getLongitude();
                loclat = loc.getLatitude();
                txtloclat.setText(String.format("%s", loclong.toString()));
                txtloclong.setText(String.format("%s", loclat.toString()));
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    }

}