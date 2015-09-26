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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DetailActivity extends ActionBarActivity {

    private RealmConfiguration realmConfiguration;

    private Realm realm;

    Camera camera;

    SurfaceHolder surfaceHolder;

    private String e_ktp, e_status,
            e_nama, e_alamat,
            e_kecamatan, e_kabupaten;

    private Integer id_penerima;

    EditText txtloclong, txtloclat;

    private LocationManager lm;
    private LocationListener locationListener;
    Double loclong = null, loclat = null;
    private String imgloc;

    private int CAMERA_REQUEST = 3;
    private static final int IMG_COMPRESSIONRATIO = 80;
    private static final int IMG_INSAMPLESIZE = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id_penerima = extras.getInt("id_penerima");
            e_ktp = extras.getString("ktp");
            e_status = extras.getString("status");
            e_nama = extras.getString("nama");
            e_alamat = extras.getString("alamat");
            e_kecamatan = extras.getString("kecamatan");
            e_kabupaten = extras.getString("kabupaten");
        }

        TextView nama = (TextView) findViewById(R.id.nama);
        TextView status = (TextView) findViewById(R.id.status);
        TextView ktp = (TextView) findViewById(R.id.ktp);
        TextView alamat = (TextView) findViewById(R.id.alamat);
        TextView kecamatan = (TextView) findViewById(R.id.kecamatan);
        TextView kabupaten = (TextView) findViewById(R.id.kabupaten);

        txtloclong = (EditText) findViewById(R.id.loclong);
        txtloclat = (EditText) findViewById(R.id.loclat);

        ktp.setText(e_ktp);
        nama.setText(id_penerima.toString()+" / "+e_nama);
        status.setText(e_status);
        alamat.setText(e_alamat);
        kecamatan.setText(e_kecamatan);
        kabupaten.setText(e_kabupaten);
        set_id(id_penerima);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        EditText devid = (EditText) findViewById(R.id.devid);
        devid.setText(deviceId);

        final EditText etPenerima = (EditText) findViewById(R.id.file_foto_penerima);

        ImageButton btnPenerima = (ImageButton) findViewById(R.id.btn_foto_penerimaatas);
        btnPenerima.setOnClickListener(new fotoClick(etPenerima));

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

        Button btnBelakang = (Button) findViewById(R.id.btn_foto_belakang);
        final EditText etBelakang = (EditText) findViewById(R.id.foto_belakang);
        btnBelakang.setOnClickListener(new fotoClick(etBelakang));

        final EditText etDevid = (EditText) findViewById(R.id.devid);
        final EditText etloclat = (EditText) findViewById(R.id.loclat);
        final EditText etloclong = (EditText) findViewById(R.id.loclong);

        final EditText editNama = (EditText) findViewById(R.id.edit_nama);
        final EditText editKtp = (EditText) findViewById(R.id.edit_ktp);
        final EditText editKK = (EditText) findViewById(R.id.edit_kk);

        if(!e_nama.isEmpty())
        {
            editNama.setText(e_nama);
            editNama.setKeyListener(null);
        }

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
                    String sNama = (editNama.getText().toString().length() > 0) ? editNama.getText().toString() : "";
                    String sKTP = (editKtp.getText().toString().length() > 0) ? editKtp.getText().toString() : "";
                    String sKK = (editKK.getText().toString().length() > 0) ? editKK.getText().toString() : "";

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

                    String sbelakang = (etBelakang.getText().toString().length() > 0) ? etBelakang.getText().toString() : "";

                    if(fp.equals("") || fsdepan.equals("") || fssamping1.equals("") || fssamping2.equals("") || fsdapur.equals("")
                            || fsjamban.equals("") || fssumberair.equals("") || sbelakang.equals("") || sKK.equals("")
                            || sNama.equals("") || sKTP.equals("") || fslong.equals("") || fslat.equals("") || sdevid.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),
                                "Silahkan lengkapi isian terlebih dahulu!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            RealmResults<Penerima> results = realm.where(Penerima.class).equalTo("id_penerima", id_penerima).findAll();

                            Log.d("Data", Integer.toString(results.size()));

                            List<Penerima> list = new ArrayList<>();
                            list.addAll(results);
                            realm.beginTransaction();
                            for (Penerima obj : list) {
                                obj.setId_penerima(id_penerima);
                                obj.setNamalengkap(sNama);
                                obj.setKtp(sKTP);
                                obj.setKk(sKK);
                                obj.setImg_foto_penerima(fp);
                                obj.setImg_tampak_depan_rumah(fsdepan);
                                obj.setImg_tampak_samping_1(fssamping1);
                                obj.setImg_tampak_samping_2(fssamping2);
                                obj.setImg_tampak_dapur(fsdapur);
                                obj.setImg_tampak_jamban(fsjamban);
                                obj.setImg_tampak_sumber_air(fssumberair);
                                obj.setImg_tampak_belakang(sbelakang);
                                obj.setLongitude(fslong);
                                obj.setLatitude(fslat);
                                obj.setTgl_update(fsDate);
                                obj.setTgl_catat(fsDate);
                                obj.setDeviceID(sdevid);
                                obj.setIs_catat(true);
                            }
                            realm.commitTransaction();

                            Log.d("KTP", sKTP);
                            Log.d("KK", sKK);

                            Toast.makeText(getApplicationContext(),
                                    new StringBuilder().append("Data ID ").append(id_penerima.toString())
                                            .append(" Nama ")
                                            .append(e_nama)
                                            .append(" berhasil diupdate").toString(),Toast.LENGTH_SHORT).show();

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
        int id = item.getItemId();
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
            fid = this.tv.getHint().toString().replace(" ", "_");
            Log.i("Ambil Gambar Manual", "fotoClick.onClick()" );
            startCameraActivity(this.tv, setfname(fid));
        }
    }

    protected void startCameraActivity(EditText txtimgname, String fn)
    {
        createDirFoto();
        txtimgname.setText("");
        String dirfoto = get_id().toString();
        String _spath = Config.FOTO_DIR + dirfoto + "/" + fn + ".jpg";
        File file = new File(_spath);
        setPhotoLoc(file.getAbsolutePath());
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        txtimgname.setText(fn + ".jpg");
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private Bitmap decodeFile(String path) {
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            return BitmapFactory.decodeFile(path, o);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK ) {
            Log.d("Perkecil foto", "Proses...");
            if(data != null) {
                Bitmap bitmap = data.getExtras().getParcelable("data");
                Log.i("Data photo", Integer.toString(bitmap.getWidth()));
            } else {
                String photoPath = getPhotoLoc();
                File file = new File(photoPath);
                Uri outputFileUri = Uri.fromFile(file);
                Log.i("photoPath", photoPath);
                Log.i("photoPathURI", outputFileUri.getPath());
                BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                Bitmap bmpPic = BitmapFactory.decodeFile(outputFileUri.getPath(), bmpOptions);
                bmpOptions.inSampleSize = 1;
                while ((bmpPic.getWidth() >= 1024) && (bmpPic.getHeight() >= 1024)) {
                    bmpOptions.inSampleSize++;
                    bmpPic = BitmapFactory.decodeFile(outputFileUri.getPath(), bmpOptions);
                }
                OutputStream imagefile = null;
                try {
                    bmpPic = BitmapFactory.decodeFile(outputFileUri.getPath());
                    imagefile = new FileOutputStream(outputFileUri.getPath());
                    bmpPic.compress(Bitmap.CompressFormat.JPEG, IMG_COMPRESSIONRATIO, imagefile);
                    Log.i("compressPhoto", "Photo berhasil dikompress...");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setPhotoLoc(String f){
        this.imgloc = f;
    }

    private String getPhotoLoc()
    {
        return this.imgloc;
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

    private void set_id(Integer id_penerima)
    {
        this.id_penerima = id_penerima;
    }

    private Integer get_id()
    {
        return this.id_penerima;
    }

    private void createDirFoto()
    {
        Integer idp = get_id();
        File fr = new File(Config.FOTO_DIR + idp.toString());
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

/* 
* copy result to another list
* ref: http://stackoverflow.com/questions/32559473/android-realm-iterators-exception 
 */
