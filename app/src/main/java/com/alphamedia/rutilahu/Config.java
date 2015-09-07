package com.alphamedia.rutilahu;

import android.os.Environment;

public class Config {
    public static final String BASE_URL = "http://demo.alphamdia.web.id/fieldreport/";
    public static final String FILE_UPLOAD_URL = "http://demo.alphamdia.web.id/fieldreport/fu.php";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    public static final String APP_DIR = Environment.getExternalStorageDirectory().getPath() + "/fieldreport";
    public static final String FILE_DIR = APP_DIR + "/file/";
    public static final String FOTO_DIR = APP_DIR + "/foto/";
    public static final String DATA_DIR = APP_DIR + "/file/data.json";
}