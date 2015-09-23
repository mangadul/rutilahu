package com.alphamedia.rutilahu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class Backup extends Activity {

    //Realm realm;

    String filename;
    String fileZip;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DATE);

        filename = new StringBuilder().append(Config.FILE_DIR).append("backup-")
                .append(Integer.toString(date))
                .append(Integer.toString(month))
                .append(Integer.toString(year)).append(".json").toString();

        fileZip= new StringBuilder().append(Config.FILE_DIR).append("backup-")
                .append(Integer.toString(date))
                .append(Integer.toString(month))
                .append(Integer.toString(year)).append(".zip").toString();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog = new ProgressDialog(Backup.this);
        mProgressDialog.setMessage("Backup Data");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        BackupFile bckFile = new BackupFile();
        bckFile.execute(filename);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Realm realm = Realm.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //realm.close();
    }

    private class PenerimaSerializer implements JsonSerializer<Penerima> {
        @Override
        public JsonElement serialize(Penerima src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("id_penerima", src.getId_penerima());
            object.addProperty("tahun_terima", src.getTahun_terima());
            object.addProperty("provinsi", src.getProvinsi());
            object.addProperty("kabupaten", src.getKabupaten());
            object.addProperty("kecamatan", src.getKecamatan());
            object.addProperty("desa", src.getDesa());
            object.addProperty("no_urut", src.getKeterangan());
            object.addProperty("namalengkap", src.getNamalengkap());
            object.addProperty("jalan_desa", src.getJalan_desa());
            object.addProperty("rt", src.getRt());
            object.addProperty("rw", src.getRw());
            object.addProperty("ktp", src.getKtp());
            object.addProperty("kk", src.getKk());
            object.addProperty("latitude", src.getLatitude());
            object.addProperty("longitude", src.getLongitude());
            object.addProperty("img_foto_penerima", src.getImg_foto_penerima());
            object.addProperty("img_tampak_depan_rumah", src.getImg_tampak_depan_rumah());
            object.addProperty("img_tampak_samping_1", src.getImg_tampak_samping_1());
            object.addProperty("img_tampak_samping_2", src.getImg_tampak_samping_2());
            object.addProperty("img_tampak_belakang", src.getImg_tampak_belakang());
            object.addProperty("img_tampak_dapur", src.getImg_tampak_dapur());
            object.addProperty("img_tampak_jamban", src.getImg_tampak_jamban());
            object.addProperty("img_tampak_sumber_air", src.getImg_tampak_sumber_air());
            object.addProperty("keterangan", src.getKeterangan());
            object.addProperty("kode_desa", src.getKode_desa());
            object.addProperty("kode_kec", src.getKode_kec());
            object.addProperty("kode_kab", src.getKode_kab());
            object.addProperty("is_catat", src.getIs_catat());
            object.addProperty("tgl_update", src.getTgl_update());
            object.addProperty("tempat_lahir", src.getTempat_lahir());
            object.addProperty("tgl_lahir", src.getTgl_lahir());
            object.addProperty("jenis_kelamin", src.getJenis_kelamin());
            object.addProperty("devid", src.getdeviceID());
            return object;
        }

    }

    private void zip(String srcFilename, File targetFilename) throws IOException
    {
        FileInputStream fis = null;
        FileOutputStream os = new FileOutputStream(targetFilename);
        ZipOutputStream zos = new ZipOutputStream(os);
        int length;
        try {
            byte[] buffer = new byte[8192];
            fis = new FileInputStream(new File(srcFilename));
            ZipEntry entry = new ZipEntry(srcFilename);
            zos.putNextEntry(entry);
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        } catch (IOException eio) {
            eio.printStackTrace();
        } finally {
            zos.close();
        }
    }

    private class BackupFile extends AsyncTask<String, Integer, String> {

        Realm realm;

        @Override
        protected String doInBackground(String... namafile) {
            int count;
            try {
                realm = Realm.getInstance(getApplicationContext());
            } catch (RealmMigrationNeededException ex) {
                ex.printStackTrace();
            }

            RealmResults<Penerima> result = null;

            try {
                RealmResults<Penerima> p = null;
                result = (RealmResults<Penerima>) loadPenerima();
                Log.i("Result size : ", Integer.toString(result.size()));
                p = result.where().findAll();
                // Create Gson builder
                GsonBuilder gsonBuilder = new GsonBuilder();

                gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });

                // Register adapter to builder
                try {
                    gsonBuilder.registerTypeAdapter(Class.forName("io.realm.PenerimaRealmProxy"), new PenerimaSerializer());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // Create gson
                Gson gson = gsonBuilder.create();
                String json = gson.toJson(p);
                try {
                    mProgressDialog.setMessage("Create file...");
                    FileWriter writer = new FileWriter(filename);
                    publishProgress(50);
                    writer.write(json);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... args){
            //mProgressDialog.setProgress(args[0]);
            mProgressDialog.setProgress(100);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mProgressDialog.setMessage("Compress file...");
            try {
                zip(filename, new File(fileZip));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mProgressDialog.dismiss();
            SystemClock.sleep(5000);
            mProgressDialog.setMessage("Compress finish...");
            finish();
        }

        public List<Penerima> loadPenerima() throws IOException {
            return realm.allObjects(Penerima.class);
        }

    }

}

/*
* Ref: https://github.com/realm/realm-java/issues/1127
* */