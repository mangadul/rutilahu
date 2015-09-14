package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.alphamedia.rutilahu.Penerima;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnType;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PenerimaRealmProxy extends Penerima
    implements RealmObjectProxy {

    private static long INDEX_ID_PENERIMA;
    private static long INDEX_NO_URUT;
    private static long INDEX_KTP;
    private static long INDEX_KK;
    private static long INDEX_NAMALENGKAP;
    private static long INDEX_JENIS_KELAMIN;
    private static long INDEX_TEMPAT_LAHIR;
    private static long INDEX_TGL_LAHIR;
    private static long INDEX_JALAN_DESA;
    private static long INDEX_RT;
    private static long INDEX_RW;
    private static long INDEX_DESA;
    private static long INDEX_KECAMATAN;
    private static long INDEX_KABUPATEN;
    private static long INDEX_PROVINSI;
    private static long INDEX_IMG_FOTO_PENERIMA;
    private static long INDEX_IMG_TAMPAK_DEPAN_RUMAH;
    private static long INDEX_IMG_TAMPAK_SAMPING_1;
    private static long INDEX_IMG_TAMPAK_SAMPING_2;
    private static long INDEX_IMG_TAMPAK_DAPUR;
    private static long INDEX_IMG_TAMPAK_JAMBAN;
    private static long INDEX_IMG_TAMPAK_SUMBER_AIR;
    private static long INDEX_LONGITUDE;
    private static long INDEX_LATITUDE;
    private static long INDEX_TAHUN_TERIMA;
    private static long INDEX_KETERANGAN;
    private static long INDEX_TGL_UPDATE;
    private static long INDEX_TGL_CATAT;
    private static long INDEX_IS_CATAT;
    private static long INDEX_DEVICEID;
    private static Map<String, Long> columnIndices;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id_penerima");
        fieldNames.add("no_urut");
        fieldNames.add("ktp");
        fieldNames.add("kk");
        fieldNames.add("namalengkap");
        fieldNames.add("jenis_kelamin");
        fieldNames.add("tempat_lahir");
        fieldNames.add("tgl_lahir");
        fieldNames.add("jalan_desa");
        fieldNames.add("rt");
        fieldNames.add("rw");
        fieldNames.add("desa");
        fieldNames.add("kecamatan");
        fieldNames.add("kabupaten");
        fieldNames.add("provinsi");
        fieldNames.add("img_foto_penerima");
        fieldNames.add("img_tampak_depan_rumah");
        fieldNames.add("img_tampak_samping_1");
        fieldNames.add("img_tampak_samping_2");
        fieldNames.add("img_tampak_dapur");
        fieldNames.add("img_tampak_jamban");
        fieldNames.add("img_tampak_sumber_air");
        fieldNames.add("longitude");
        fieldNames.add("latitude");
        fieldNames.add("tahun_terima");
        fieldNames.add("keterangan");
        fieldNames.add("tgl_update");
        fieldNames.add("tgl_catat");
        fieldNames.add("is_catat");
        fieldNames.add("deviceID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    @Override
    public long getId_penerima() {
        realm.checkIfValid();
        return (long) row.getLong(INDEX_ID_PENERIMA);
    }

    @Override
    public void setId_penerima(long value) {
        realm.checkIfValid();
        row.setLong(INDEX_ID_PENERIMA, (long) value);
    }

    @Override
    public long getNo_urut() {
        realm.checkIfValid();
        return (long) row.getLong(INDEX_NO_URUT);
    }

    @Override
    public void setNo_urut(long value) {
        realm.checkIfValid();
        row.setLong(INDEX_NO_URUT, (long) value);
    }

    @Override
    public String getKtp() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_KTP);
    }

    @Override
    public void setKtp(String value) {
        realm.checkIfValid();
        row.setString(INDEX_KTP, (String) value);
    }

    @Override
    public String getKk() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_KK);
    }

    @Override
    public void setKk(String value) {
        realm.checkIfValid();
        row.setString(INDEX_KK, (String) value);
    }

    @Override
    public String getNamalengkap() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_NAMALENGKAP);
    }

    @Override
    public void setNamalengkap(String value) {
        realm.checkIfValid();
        row.setString(INDEX_NAMALENGKAP, (String) value);
    }

    @Override
    public String getJenis_kelamin() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_JENIS_KELAMIN);
    }

    @Override
    public void setJenis_kelamin(String value) {
        realm.checkIfValid();
        row.setString(INDEX_JENIS_KELAMIN, (String) value);
    }

    @Override
    public String getTempat_lahir() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_TEMPAT_LAHIR);
    }

    @Override
    public void setTempat_lahir(String value) {
        realm.checkIfValid();
        row.setString(INDEX_TEMPAT_LAHIR, (String) value);
    }

    @Override
    public String getTgl_lahir() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_TGL_LAHIR);
    }

    @Override
    public void setTgl_lahir(String value) {
        realm.checkIfValid();
        row.setString(INDEX_TGL_LAHIR, (String) value);
    }

    @Override
    public String getJalan_desa() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_JALAN_DESA);
    }

    @Override
    public void setJalan_desa(String value) {
        realm.checkIfValid();
        row.setString(INDEX_JALAN_DESA, (String) value);
    }

    @Override
    public String getRt() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_RT);
    }

    @Override
    public void setRt(String value) {
        realm.checkIfValid();
        row.setString(INDEX_RT, (String) value);
    }

    @Override
    public String getRw() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_RW);
    }

    @Override
    public void setRw(String value) {
        realm.checkIfValid();
        row.setString(INDEX_RW, (String) value);
    }

    @Override
    public String getDesa() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_DESA);
    }

    @Override
    public void setDesa(String value) {
        realm.checkIfValid();
        row.setString(INDEX_DESA, (String) value);
    }

    @Override
    public String getKecamatan() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_KECAMATAN);
    }

    @Override
    public void setKecamatan(String value) {
        realm.checkIfValid();
        row.setString(INDEX_KECAMATAN, (String) value);
    }

    @Override
    public String getKabupaten() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_KABUPATEN);
    }

    @Override
    public void setKabupaten(String value) {
        realm.checkIfValid();
        row.setString(INDEX_KABUPATEN, (String) value);
    }

    @Override
    public String getProvinsi() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_PROVINSI);
    }

    @Override
    public void setProvinsi(String value) {
        realm.checkIfValid();
        row.setString(INDEX_PROVINSI, (String) value);
    }

    @Override
    public String getImg_foto_penerima() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_IMG_FOTO_PENERIMA);
    }

    @Override
    public void setImg_foto_penerima(String value) {
        realm.checkIfValid();
        row.setString(INDEX_IMG_FOTO_PENERIMA, (String) value);
    }

    @Override
    public String getImg_tampak_depan_rumah() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_IMG_TAMPAK_DEPAN_RUMAH);
    }

    @Override
    public void setImg_tampak_depan_rumah(String value) {
        realm.checkIfValid();
        row.setString(INDEX_IMG_TAMPAK_DEPAN_RUMAH, (String) value);
    }

    @Override
    public String getImg_tampak_samping_1() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_IMG_TAMPAK_SAMPING_1);
    }

    @Override
    public void setImg_tampak_samping_1(String value) {
        realm.checkIfValid();
        row.setString(INDEX_IMG_TAMPAK_SAMPING_1, (String) value);
    }

    @Override
    public String getImg_tampak_samping_2() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_IMG_TAMPAK_SAMPING_2);
    }

    @Override
    public void setImg_tampak_samping_2(String value) {
        realm.checkIfValid();
        row.setString(INDEX_IMG_TAMPAK_SAMPING_2, (String) value);
    }

    @Override
    public String getImg_tampak_dapur() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_IMG_TAMPAK_DAPUR);
    }

    @Override
    public void setImg_tampak_dapur(String value) {
        realm.checkIfValid();
        row.setString(INDEX_IMG_TAMPAK_DAPUR, (String) value);
    }

    @Override
    public String getImg_tampak_jamban() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_IMG_TAMPAK_JAMBAN);
    }

    @Override
    public void setImg_tampak_jamban(String value) {
        realm.checkIfValid();
        row.setString(INDEX_IMG_TAMPAK_JAMBAN, (String) value);
    }

    @Override
    public String getImg_tampak_sumber_air() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_IMG_TAMPAK_SUMBER_AIR);
    }

    @Override
    public void setImg_tampak_sumber_air(String value) {
        realm.checkIfValid();
        row.setString(INDEX_IMG_TAMPAK_SUMBER_AIR, (String) value);
    }

    @Override
    public String getLongitude() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_LONGITUDE);
    }

    @Override
    public void setLongitude(String value) {
        realm.checkIfValid();
        row.setString(INDEX_LONGITUDE, (String) value);
    }

    @Override
    public String getLatitude() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_LATITUDE);
    }

    @Override
    public void setLatitude(String value) {
        realm.checkIfValid();
        row.setString(INDEX_LATITUDE, (String) value);
    }

    @Override
    public int getTahun_terima() {
        realm.checkIfValid();
        return (int) row.getLong(INDEX_TAHUN_TERIMA);
    }

    @Override
    public void setTahun_terima(int value) {
        realm.checkIfValid();
        row.setLong(INDEX_TAHUN_TERIMA, (long) value);
    }

    @Override
    public String getKeterangan() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_KETERANGAN);
    }

    @Override
    public void setKeterangan(String value) {
        realm.checkIfValid();
        row.setString(INDEX_KETERANGAN, (String) value);
    }

    @Override
    public String getTgl_update() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_TGL_UPDATE);
    }

    @Override
    public void setTgl_update(String value) {
        realm.checkIfValid();
        row.setString(INDEX_TGL_UPDATE, (String) value);
    }

    @Override
    public String getTgl_catat() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_TGL_CATAT);
    }

    @Override
    public void setTgl_catat(String value) {
        realm.checkIfValid();
        row.setString(INDEX_TGL_CATAT, (String) value);
    }

    @Override
    public boolean getIs_catat() {
        realm.checkIfValid();
        return (boolean) row.getBoolean(INDEX_IS_CATAT);
    }

    @Override
    public void setIs_catat(boolean value) {
        realm.checkIfValid();
        row.setBoolean(INDEX_IS_CATAT, (boolean) value);
    }

    @Override
    public String getdeviceID() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_DEVICEID);
    }

    @Override
    public void setDeviceID(String value) {
        realm.checkIfValid();
        row.setString(INDEX_DEVICEID, (String) value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Penerima")) {
            Table table = transaction.getTable("class_Penerima");
            table.addColumn(ColumnType.INTEGER, "id_penerima");
            table.addColumn(ColumnType.INTEGER, "no_urut");
            table.addColumn(ColumnType.STRING, "ktp");
            table.addColumn(ColumnType.STRING, "kk");
            table.addColumn(ColumnType.STRING, "namalengkap");
            table.addColumn(ColumnType.STRING, "jenis_kelamin");
            table.addColumn(ColumnType.STRING, "tempat_lahir");
            table.addColumn(ColumnType.STRING, "tgl_lahir");
            table.addColumn(ColumnType.STRING, "jalan_desa");
            table.addColumn(ColumnType.STRING, "rt");
            table.addColumn(ColumnType.STRING, "rw");
            table.addColumn(ColumnType.STRING, "desa");
            table.addColumn(ColumnType.STRING, "kecamatan");
            table.addColumn(ColumnType.STRING, "kabupaten");
            table.addColumn(ColumnType.STRING, "provinsi");
            table.addColumn(ColumnType.STRING, "img_foto_penerima");
            table.addColumn(ColumnType.STRING, "img_tampak_depan_rumah");
            table.addColumn(ColumnType.STRING, "img_tampak_samping_1");
            table.addColumn(ColumnType.STRING, "img_tampak_samping_2");
            table.addColumn(ColumnType.STRING, "img_tampak_dapur");
            table.addColumn(ColumnType.STRING, "img_tampak_jamban");
            table.addColumn(ColumnType.STRING, "img_tampak_sumber_air");
            table.addColumn(ColumnType.STRING, "longitude");
            table.addColumn(ColumnType.STRING, "latitude");
            table.addColumn(ColumnType.INTEGER, "tahun_terima");
            table.addColumn(ColumnType.STRING, "keterangan");
            table.addColumn(ColumnType.STRING, "tgl_update");
            table.addColumn(ColumnType.STRING, "tgl_catat");
            table.addColumn(ColumnType.BOOLEAN, "is_catat");
            table.addColumn(ColumnType.STRING, "deviceID");
            table.addSearchIndex(table.getColumnIndex("ktp"));
            table.setPrimaryKey("ktp");
            return table;
        }
        return transaction.getTable("class_Penerima");
    }

    public static void validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Penerima")) {
            Table table = transaction.getTable("class_Penerima");
            if (table.getColumnCount() != 30) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 30 but was " + table.getColumnCount());
            }
            Map<String, ColumnType> columnTypes = new HashMap<String, ColumnType>();
            for (long i = 0; i < 30; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            columnIndices = new HashMap<String, Long>();
            for (String fieldName : getFieldNames()) {
                long index = table.getColumnIndex(fieldName);
                if (index == -1) {
                    throw new RealmMigrationNeededException(transaction.getPath(), "Field '" + fieldName + "' not found for type Penerima");
                }
                columnIndices.put(fieldName, index);
            }
            INDEX_ID_PENERIMA = table.getColumnIndex("id_penerima");
            INDEX_NO_URUT = table.getColumnIndex("no_urut");
            INDEX_KTP = table.getColumnIndex("ktp");
            INDEX_KK = table.getColumnIndex("kk");
            INDEX_NAMALENGKAP = table.getColumnIndex("namalengkap");
            INDEX_JENIS_KELAMIN = table.getColumnIndex("jenis_kelamin");
            INDEX_TEMPAT_LAHIR = table.getColumnIndex("tempat_lahir");
            INDEX_TGL_LAHIR = table.getColumnIndex("tgl_lahir");
            INDEX_JALAN_DESA = table.getColumnIndex("jalan_desa");
            INDEX_RT = table.getColumnIndex("rt");
            INDEX_RW = table.getColumnIndex("rw");
            INDEX_DESA = table.getColumnIndex("desa");
            INDEX_KECAMATAN = table.getColumnIndex("kecamatan");
            INDEX_KABUPATEN = table.getColumnIndex("kabupaten");
            INDEX_PROVINSI = table.getColumnIndex("provinsi");
            INDEX_IMG_FOTO_PENERIMA = table.getColumnIndex("img_foto_penerima");
            INDEX_IMG_TAMPAK_DEPAN_RUMAH = table.getColumnIndex("img_tampak_depan_rumah");
            INDEX_IMG_TAMPAK_SAMPING_1 = table.getColumnIndex("img_tampak_samping_1");
            INDEX_IMG_TAMPAK_SAMPING_2 = table.getColumnIndex("img_tampak_samping_2");
            INDEX_IMG_TAMPAK_DAPUR = table.getColumnIndex("img_tampak_dapur");
            INDEX_IMG_TAMPAK_JAMBAN = table.getColumnIndex("img_tampak_jamban");
            INDEX_IMG_TAMPAK_SUMBER_AIR = table.getColumnIndex("img_tampak_sumber_air");
            INDEX_LONGITUDE = table.getColumnIndex("longitude");
            INDEX_LATITUDE = table.getColumnIndex("latitude");
            INDEX_TAHUN_TERIMA = table.getColumnIndex("tahun_terima");
            INDEX_KETERANGAN = table.getColumnIndex("keterangan");
            INDEX_TGL_UPDATE = table.getColumnIndex("tgl_update");
            INDEX_TGL_CATAT = table.getColumnIndex("tgl_catat");
            INDEX_IS_CATAT = table.getColumnIndex("is_catat");
            INDEX_DEVICEID = table.getColumnIndex("deviceID");

            if (!columnTypes.containsKey("id_penerima")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id_penerima'");
            }
            if (columnTypes.get("id_penerima") != ColumnType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'id_penerima'");
            }
            if (!columnTypes.containsKey("no_urut")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'no_urut'");
            }
            if (columnTypes.get("no_urut") != ColumnType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'no_urut'");
            }
            if (!columnTypes.containsKey("ktp")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'ktp'");
            }
            if (columnTypes.get("ktp") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'ktp'");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("ktp")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'ktp'");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("ktp"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'ktp'");
            }
            if (!columnTypes.containsKey("kk")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'kk'");
            }
            if (columnTypes.get("kk") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'kk'");
            }
            if (!columnTypes.containsKey("namalengkap")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'namalengkap'");
            }
            if (columnTypes.get("namalengkap") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'namalengkap'");
            }
            if (!columnTypes.containsKey("jenis_kelamin")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'jenis_kelamin'");
            }
            if (columnTypes.get("jenis_kelamin") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'jenis_kelamin'");
            }
            if (!columnTypes.containsKey("tempat_lahir")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tempat_lahir'");
            }
            if (columnTypes.get("tempat_lahir") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tempat_lahir'");
            }
            if (!columnTypes.containsKey("tgl_lahir")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tgl_lahir'");
            }
            if (columnTypes.get("tgl_lahir") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tgl_lahir'");
            }
            if (!columnTypes.containsKey("jalan_desa")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'jalan_desa'");
            }
            if (columnTypes.get("jalan_desa") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'jalan_desa'");
            }
            if (!columnTypes.containsKey("rt")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'rt'");
            }
            if (columnTypes.get("rt") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'rt'");
            }
            if (!columnTypes.containsKey("rw")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'rw'");
            }
            if (columnTypes.get("rw") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'rw'");
            }
            if (!columnTypes.containsKey("desa")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'desa'");
            }
            if (columnTypes.get("desa") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'desa'");
            }
            if (!columnTypes.containsKey("kecamatan")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'kecamatan'");
            }
            if (columnTypes.get("kecamatan") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'kecamatan'");
            }
            if (!columnTypes.containsKey("kabupaten")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'kabupaten'");
            }
            if (columnTypes.get("kabupaten") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'kabupaten'");
            }
            if (!columnTypes.containsKey("provinsi")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'provinsi'");
            }
            if (columnTypes.get("provinsi") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'provinsi'");
            }
            if (!columnTypes.containsKey("img_foto_penerima")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'img_foto_penerima'");
            }
            if (columnTypes.get("img_foto_penerima") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'img_foto_penerima'");
            }
            if (!columnTypes.containsKey("img_tampak_depan_rumah")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'img_tampak_depan_rumah'");
            }
            if (columnTypes.get("img_tampak_depan_rumah") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'img_tampak_depan_rumah'");
            }
            if (!columnTypes.containsKey("img_tampak_samping_1")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'img_tampak_samping_1'");
            }
            if (columnTypes.get("img_tampak_samping_1") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'img_tampak_samping_1'");
            }
            if (!columnTypes.containsKey("img_tampak_samping_2")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'img_tampak_samping_2'");
            }
            if (columnTypes.get("img_tampak_samping_2") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'img_tampak_samping_2'");
            }
            if (!columnTypes.containsKey("img_tampak_dapur")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'img_tampak_dapur'");
            }
            if (columnTypes.get("img_tampak_dapur") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'img_tampak_dapur'");
            }
            if (!columnTypes.containsKey("img_tampak_jamban")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'img_tampak_jamban'");
            }
            if (columnTypes.get("img_tampak_jamban") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'img_tampak_jamban'");
            }
            if (!columnTypes.containsKey("img_tampak_sumber_air")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'img_tampak_sumber_air'");
            }
            if (columnTypes.get("img_tampak_sumber_air") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'img_tampak_sumber_air'");
            }
            if (!columnTypes.containsKey("longitude")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'longitude'");
            }
            if (columnTypes.get("longitude") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'longitude'");
            }
            if (!columnTypes.containsKey("latitude")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'latitude'");
            }
            if (columnTypes.get("latitude") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'latitude'");
            }
            if (!columnTypes.containsKey("tahun_terima")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tahun_terima'");
            }
            if (columnTypes.get("tahun_terima") != ColumnType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'tahun_terima'");
            }
            if (!columnTypes.containsKey("keterangan")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'keterangan'");
            }
            if (columnTypes.get("keterangan") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'keterangan'");
            }
            if (!columnTypes.containsKey("tgl_update")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tgl_update'");
            }
            if (columnTypes.get("tgl_update") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tgl_update'");
            }
            if (!columnTypes.containsKey("tgl_catat")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'tgl_catat'");
            }
            if (columnTypes.get("tgl_catat") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'tgl_catat'");
            }
            if (!columnTypes.containsKey("is_catat")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'is_catat'");
            }
            if (columnTypes.get("is_catat") != ColumnType.BOOLEAN) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'boolean' for field 'is_catat'");
            }
            if (!columnTypes.containsKey("deviceID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'deviceID'");
            }
            if (columnTypes.get("deviceID") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'deviceID'");
            }
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Penerima class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Penerima";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Map<String,Long> getColumnIndices() {
        return columnIndices;
    }

    public static Penerima createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Penerima obj = null;
        if (update) {
            Table table = realm.getTable(Penerima.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("ktp")) {
                long rowIndex = table.findFirstString(pkColumnIndex, json.getString("ktp"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new PenerimaRealmProxy();
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            obj = realm.createObject(Penerima.class);
        }
        if (!json.isNull("id_penerima")) {
            obj.setId_penerima((long) json.getLong("id_penerima"));
        }
        if (!json.isNull("no_urut")) {
            obj.setNo_urut((long) json.getLong("no_urut"));
        }
        if (json.has("ktp")) {
            if (json.isNull("ktp")) {
                obj.setKtp("");
            } else {
                obj.setKtp((String) json.getString("ktp"));
            }
        }
        if (json.has("kk")) {
            if (json.isNull("kk")) {
                obj.setKk("");
            } else {
                obj.setKk((String) json.getString("kk"));
            }
        }
        if (json.has("namalengkap")) {
            if (json.isNull("namalengkap")) {
                obj.setNamalengkap("");
            } else {
                obj.setNamalengkap((String) json.getString("namalengkap"));
            }
        }
        if (json.has("jenis_kelamin")) {
            if (json.isNull("jenis_kelamin")) {
                obj.setJenis_kelamin("");
            } else {
                obj.setJenis_kelamin((String) json.getString("jenis_kelamin"));
            }
        }
        if (json.has("tempat_lahir")) {
            if (json.isNull("tempat_lahir")) {
                obj.setTempat_lahir("");
            } else {
                obj.setTempat_lahir((String) json.getString("tempat_lahir"));
            }
        }
        if (json.has("tgl_lahir")) {
            if (json.isNull("tgl_lahir")) {
                obj.setTgl_lahir("");
            } else {
                obj.setTgl_lahir((String) json.getString("tgl_lahir"));
            }
        }
        if (json.has("jalan_desa")) {
            if (json.isNull("jalan_desa")) {
                obj.setJalan_desa("");
            } else {
                obj.setJalan_desa((String) json.getString("jalan_desa"));
            }
        }
        if (json.has("rt")) {
            if (json.isNull("rt")) {
                obj.setRt("");
            } else {
                obj.setRt((String) json.getString("rt"));
            }
        }
        if (json.has("rw")) {
            if (json.isNull("rw")) {
                obj.setRw("");
            } else {
                obj.setRw((String) json.getString("rw"));
            }
        }
        if (json.has("desa")) {
            if (json.isNull("desa")) {
                obj.setDesa("");
            } else {
                obj.setDesa((String) json.getString("desa"));
            }
        }
        if (json.has("kecamatan")) {
            if (json.isNull("kecamatan")) {
                obj.setKecamatan("");
            } else {
                obj.setKecamatan((String) json.getString("kecamatan"));
            }
        }
        if (json.has("kabupaten")) {
            if (json.isNull("kabupaten")) {
                obj.setKabupaten("");
            } else {
                obj.setKabupaten((String) json.getString("kabupaten"));
            }
        }
        if (json.has("provinsi")) {
            if (json.isNull("provinsi")) {
                obj.setProvinsi("");
            } else {
                obj.setProvinsi((String) json.getString("provinsi"));
            }
        }
        if (json.has("img_foto_penerima")) {
            if (json.isNull("img_foto_penerima")) {
                obj.setImg_foto_penerima("");
            } else {
                obj.setImg_foto_penerima((String) json.getString("img_foto_penerima"));
            }
        }
        if (json.has("img_tampak_depan_rumah")) {
            if (json.isNull("img_tampak_depan_rumah")) {
                obj.setImg_tampak_depan_rumah("");
            } else {
                obj.setImg_tampak_depan_rumah((String) json.getString("img_tampak_depan_rumah"));
            }
        }
        if (json.has("img_tampak_samping_1")) {
            if (json.isNull("img_tampak_samping_1")) {
                obj.setImg_tampak_samping_1("");
            } else {
                obj.setImg_tampak_samping_1((String) json.getString("img_tampak_samping_1"));
            }
        }
        if (json.has("img_tampak_samping_2")) {
            if (json.isNull("img_tampak_samping_2")) {
                obj.setImg_tampak_samping_2("");
            } else {
                obj.setImg_tampak_samping_2((String) json.getString("img_tampak_samping_2"));
            }
        }
        if (json.has("img_tampak_dapur")) {
            if (json.isNull("img_tampak_dapur")) {
                obj.setImg_tampak_dapur("");
            } else {
                obj.setImg_tampak_dapur((String) json.getString("img_tampak_dapur"));
            }
        }
        if (json.has("img_tampak_jamban")) {
            if (json.isNull("img_tampak_jamban")) {
                obj.setImg_tampak_jamban("");
            } else {
                obj.setImg_tampak_jamban((String) json.getString("img_tampak_jamban"));
            }
        }
        if (json.has("img_tampak_sumber_air")) {
            if (json.isNull("img_tampak_sumber_air")) {
                obj.setImg_tampak_sumber_air("");
            } else {
                obj.setImg_tampak_sumber_air((String) json.getString("img_tampak_sumber_air"));
            }
        }
        if (json.has("longitude")) {
            if (json.isNull("longitude")) {
                obj.setLongitude("");
            } else {
                obj.setLongitude((String) json.getString("longitude"));
            }
        }
        if (json.has("latitude")) {
            if (json.isNull("latitude")) {
                obj.setLatitude("");
            } else {
                obj.setLatitude((String) json.getString("latitude"));
            }
        }
        if (!json.isNull("tahun_terima")) {
            obj.setTahun_terima((int) json.getInt("tahun_terima"));
        }
        if (json.has("keterangan")) {
            if (json.isNull("keterangan")) {
                obj.setKeterangan("");
            } else {
                obj.setKeterangan((String) json.getString("keterangan"));
            }
        }
        if (json.has("tgl_update")) {
            if (json.isNull("tgl_update")) {
                obj.setTgl_update("");
            } else {
                obj.setTgl_update((String) json.getString("tgl_update"));
            }
        }
        if (json.has("tgl_catat")) {
            if (json.isNull("tgl_catat")) {
                obj.setTgl_catat("");
            } else {
                obj.setTgl_catat((String) json.getString("tgl_catat"));
            }
        }
        if (!json.isNull("is_catat")) {
            obj.setIs_catat((boolean) json.getBoolean("is_catat"));
        }
        if (json.has("deviceID")) {
            if (json.isNull("deviceID")) {
                obj.setDeviceID("");
            } else {
                obj.setDeviceID((String) json.getString("deviceID"));
            }
        }
        return obj;
    }

    public static Penerima createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Penerima obj = realm.createObject(Penerima.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id_penerima") && reader.peek() != JsonToken.NULL) {
                obj.setId_penerima((long) reader.nextLong());
            } else if (name.equals("no_urut")  && reader.peek() != JsonToken.NULL) {
                obj.setNo_urut((long) reader.nextLong());
            } else if (name.equals("ktp")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setKtp("");
                    reader.skipValue();
                } else {
                    obj.setKtp((String) reader.nextString());
                }
            } else if (name.equals("kk")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setKk("");
                    reader.skipValue();
                } else {
                    obj.setKk((String) reader.nextString());
                }
            } else if (name.equals("namalengkap")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setNamalengkap("");
                    reader.skipValue();
                } else {
                    obj.setNamalengkap((String) reader.nextString());
                }
            } else if (name.equals("jenis_kelamin")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setJenis_kelamin("");
                    reader.skipValue();
                } else {
                    obj.setJenis_kelamin((String) reader.nextString());
                }
            } else if (name.equals("tempat_lahir")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setTempat_lahir("");
                    reader.skipValue();
                } else {
                    obj.setTempat_lahir((String) reader.nextString());
                }
            } else if (name.equals("tgl_lahir")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setTgl_lahir("");
                    reader.skipValue();
                } else {
                    obj.setTgl_lahir((String) reader.nextString());
                }
            } else if (name.equals("jalan_desa")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setJalan_desa("");
                    reader.skipValue();
                } else {
                    obj.setJalan_desa((String) reader.nextString());
                }
            } else if (name.equals("rt")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setRt("");
                    reader.skipValue();
                } else {
                    obj.setRt((String) reader.nextString());
                }
            } else if (name.equals("rw")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setRw("");
                    reader.skipValue();
                } else {
                    obj.setRw((String) reader.nextString());
                }
            } else if (name.equals("desa")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setDesa("");
                    reader.skipValue();
                } else {
                    obj.setDesa((String) reader.nextString());
                }
            } else if (name.equals("kecamatan")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setKecamatan("");
                    reader.skipValue();
                } else {
                    obj.setKecamatan((String) reader.nextString());
                }
            } else if (name.equals("kabupaten")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setKabupaten("");
                    reader.skipValue();
                } else {
                    obj.setKabupaten((String) reader.nextString());
                }
            } else if (name.equals("provinsi")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setProvinsi("");
                    reader.skipValue();
                } else {
                    obj.setProvinsi((String) reader.nextString());
                }
            } else if (name.equals("img_foto_penerima")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setImg_foto_penerima("");
                    reader.skipValue();
                } else {
                    obj.setImg_foto_penerima((String) reader.nextString());
                }
            } else if (name.equals("img_tampak_depan_rumah")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setImg_tampak_depan_rumah("");
                    reader.skipValue();
                } else {
                    obj.setImg_tampak_depan_rumah((String) reader.nextString());
                }
            } else if (name.equals("img_tampak_samping_1")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setImg_tampak_samping_1("");
                    reader.skipValue();
                } else {
                    obj.setImg_tampak_samping_1((String) reader.nextString());
                }
            } else if (name.equals("img_tampak_samping_2")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setImg_tampak_samping_2("");
                    reader.skipValue();
                } else {
                    obj.setImg_tampak_samping_2((String) reader.nextString());
                }
            } else if (name.equals("img_tampak_dapur")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setImg_tampak_dapur("");
                    reader.skipValue();
                } else {
                    obj.setImg_tampak_dapur((String) reader.nextString());
                }
            } else if (name.equals("img_tampak_jamban")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setImg_tampak_jamban("");
                    reader.skipValue();
                } else {
                    obj.setImg_tampak_jamban((String) reader.nextString());
                }
            } else if (name.equals("img_tampak_sumber_air")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setImg_tampak_sumber_air("");
                    reader.skipValue();
                } else {
                    obj.setImg_tampak_sumber_air((String) reader.nextString());
                }
            } else if (name.equals("longitude")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setLongitude("");
                    reader.skipValue();
                } else {
                    obj.setLongitude((String) reader.nextString());
                }
            } else if (name.equals("latitude")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setLatitude("");
                    reader.skipValue();
                } else {
                    obj.setLatitude((String) reader.nextString());
                }
            } else if (name.equals("tahun_terima")  && reader.peek() != JsonToken.NULL) {
                obj.setTahun_terima((int) reader.nextInt());
            } else if (name.equals("keterangan")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setKeterangan("");
                    reader.skipValue();
                } else {
                    obj.setKeterangan((String) reader.nextString());
                }
            } else if (name.equals("tgl_update")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setTgl_update("");
                    reader.skipValue();
                } else {
                    obj.setTgl_update((String) reader.nextString());
                }
            } else if (name.equals("tgl_catat")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setTgl_catat("");
                    reader.skipValue();
                } else {
                    obj.setTgl_catat((String) reader.nextString());
                }
            } else if (name.equals("is_catat")  && reader.peek() != JsonToken.NULL) {
                obj.setIs_catat((boolean) reader.nextBoolean());
            } else if (name.equals("deviceID")) {
                if (reader.peek() == JsonToken.NULL) {
                    obj.setDeviceID("");
                    reader.skipValue();
                } else {
                    obj.setDeviceID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Penerima copyOrUpdate(Realm realm, Penerima object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        Penerima realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(Penerima.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (object.getKtp() == null) {
                throw new IllegalArgumentException("Primary key value must not be null.");
            }
            long rowIndex = table.findFirstString(pkColumnIndex, object.getKtp());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new PenerimaRealmProxy();
                realmObject.realm = realm;
                realmObject.row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static Penerima copy(Realm realm, Penerima newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        Penerima realmObject = realm.createObject(Penerima.class, newObject.getKtp());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setId_penerima(newObject.getId_penerima());
        realmObject.setNo_urut(newObject.getNo_urut());
        realmObject.setKtp(newObject.getKtp() != null ? newObject.getKtp() : "");
        realmObject.setKk(newObject.getKk() != null ? newObject.getKk() : "");
        realmObject.setNamalengkap(newObject.getNamalengkap() != null ? newObject.getNamalengkap() : "");
        realmObject.setJenis_kelamin(newObject.getJenis_kelamin() != null ? newObject.getJenis_kelamin() : "");
        realmObject.setTempat_lahir(newObject.getTempat_lahir() != null ? newObject.getTempat_lahir() : "");
        realmObject.setTgl_lahir(newObject.getTgl_lahir() != null ? newObject.getTgl_lahir() : "");
        realmObject.setJalan_desa(newObject.getJalan_desa() != null ? newObject.getJalan_desa() : "");
        realmObject.setRt(newObject.getRt() != null ? newObject.getRt() : "");
        realmObject.setRw(newObject.getRw() != null ? newObject.getRw() : "");
        realmObject.setDesa(newObject.getDesa() != null ? newObject.getDesa() : "");
        realmObject.setKecamatan(newObject.getKecamatan() != null ? newObject.getKecamatan() : "");
        realmObject.setKabupaten(newObject.getKabupaten() != null ? newObject.getKabupaten() : "");
        realmObject.setProvinsi(newObject.getProvinsi() != null ? newObject.getProvinsi() : "");
        realmObject.setImg_foto_penerima(newObject.getImg_foto_penerima() != null ? newObject.getImg_foto_penerima() : "");
        realmObject.setImg_tampak_depan_rumah(newObject.getImg_tampak_depan_rumah() != null ? newObject.getImg_tampak_depan_rumah() : "");
        realmObject.setImg_tampak_samping_1(newObject.getImg_tampak_samping_1() != null ? newObject.getImg_tampak_samping_1() : "");
        realmObject.setImg_tampak_samping_2(newObject.getImg_tampak_samping_2() != null ? newObject.getImg_tampak_samping_2() : "");
        realmObject.setImg_tampak_dapur(newObject.getImg_tampak_dapur() != null ? newObject.getImg_tampak_dapur() : "");
        realmObject.setImg_tampak_jamban(newObject.getImg_tampak_jamban() != null ? newObject.getImg_tampak_jamban() : "");
        realmObject.setImg_tampak_sumber_air(newObject.getImg_tampak_sumber_air() != null ? newObject.getImg_tampak_sumber_air() : "");
        realmObject.setLongitude(newObject.getLongitude() != null ? newObject.getLongitude() : "");
        realmObject.setLatitude(newObject.getLatitude() != null ? newObject.getLatitude() : "");
        realmObject.setTahun_terima(newObject.getTahun_terima());
        realmObject.setKeterangan(newObject.getKeterangan() != null ? newObject.getKeterangan() : "");
        realmObject.setTgl_update(newObject.getTgl_update() != null ? newObject.getTgl_update() : "");
        realmObject.setTgl_catat(newObject.getTgl_catat() != null ? newObject.getTgl_catat() : "");
        realmObject.setIs_catat(newObject.getIs_catat());
        realmObject.setDeviceID(newObject.getdeviceID() != null ? newObject.getdeviceID() : "");
        return realmObject;
    }

    static Penerima update(Realm realm, Penerima realmObject, Penerima newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setId_penerima(newObject.getId_penerima());
        realmObject.setNo_urut(newObject.getNo_urut());
        realmObject.setKk(newObject.getKk() != null ? newObject.getKk() : "");
        realmObject.setNamalengkap(newObject.getNamalengkap() != null ? newObject.getNamalengkap() : "");
        realmObject.setJenis_kelamin(newObject.getJenis_kelamin() != null ? newObject.getJenis_kelamin() : "");
        realmObject.setTempat_lahir(newObject.getTempat_lahir() != null ? newObject.getTempat_lahir() : "");
        realmObject.setTgl_lahir(newObject.getTgl_lahir() != null ? newObject.getTgl_lahir() : "");
        realmObject.setJalan_desa(newObject.getJalan_desa() != null ? newObject.getJalan_desa() : "");
        realmObject.setRt(newObject.getRt() != null ? newObject.getRt() : "");
        realmObject.setRw(newObject.getRw() != null ? newObject.getRw() : "");
        realmObject.setDesa(newObject.getDesa() != null ? newObject.getDesa() : "");
        realmObject.setKecamatan(newObject.getKecamatan() != null ? newObject.getKecamatan() : "");
        realmObject.setKabupaten(newObject.getKabupaten() != null ? newObject.getKabupaten() : "");
        realmObject.setProvinsi(newObject.getProvinsi() != null ? newObject.getProvinsi() : "");
        realmObject.setImg_foto_penerima(newObject.getImg_foto_penerima() != null ? newObject.getImg_foto_penerima() : "");
        realmObject.setImg_tampak_depan_rumah(newObject.getImg_tampak_depan_rumah() != null ? newObject.getImg_tampak_depan_rumah() : "");
        realmObject.setImg_tampak_samping_1(newObject.getImg_tampak_samping_1() != null ? newObject.getImg_tampak_samping_1() : "");
        realmObject.setImg_tampak_samping_2(newObject.getImg_tampak_samping_2() != null ? newObject.getImg_tampak_samping_2() : "");
        realmObject.setImg_tampak_dapur(newObject.getImg_tampak_dapur() != null ? newObject.getImg_tampak_dapur() : "");
        realmObject.setImg_tampak_jamban(newObject.getImg_tampak_jamban() != null ? newObject.getImg_tampak_jamban() : "");
        realmObject.setImg_tampak_sumber_air(newObject.getImg_tampak_sumber_air() != null ? newObject.getImg_tampak_sumber_air() : "");
        realmObject.setLongitude(newObject.getLongitude() != null ? newObject.getLongitude() : "");
        realmObject.setLatitude(newObject.getLatitude() != null ? newObject.getLatitude() : "");
        realmObject.setTahun_terima(newObject.getTahun_terima());
        realmObject.setKeterangan(newObject.getKeterangan() != null ? newObject.getKeterangan() : "");
        realmObject.setTgl_update(newObject.getTgl_update() != null ? newObject.getTgl_update() : "");
        realmObject.setTgl_catat(newObject.getTgl_catat() != null ? newObject.getTgl_catat() : "");
        realmObject.setIs_catat(newObject.getIs_catat());
        realmObject.setDeviceID(newObject.getdeviceID() != null ? newObject.getdeviceID() : "");
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Penerima = [");
        stringBuilder.append("{id_penerima:");
        stringBuilder.append(getId_penerima());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{no_urut:");
        stringBuilder.append(getNo_urut());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ktp:");
        stringBuilder.append(getKtp());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{kk:");
        stringBuilder.append(getKk());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{namalengkap:");
        stringBuilder.append(getNamalengkap());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{jenis_kelamin:");
        stringBuilder.append(getJenis_kelamin());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tempat_lahir:");
        stringBuilder.append(getTempat_lahir());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tgl_lahir:");
        stringBuilder.append(getTgl_lahir());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{jalan_desa:");
        stringBuilder.append(getJalan_desa());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rt:");
        stringBuilder.append(getRt());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rw:");
        stringBuilder.append(getRw());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{desa:");
        stringBuilder.append(getDesa());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{kecamatan:");
        stringBuilder.append(getKecamatan());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{kabupaten:");
        stringBuilder.append(getKabupaten());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{provinsi:");
        stringBuilder.append(getProvinsi());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{img_foto_penerima:");
        stringBuilder.append(getImg_foto_penerima());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{img_tampak_depan_rumah:");
        stringBuilder.append(getImg_tampak_depan_rumah());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{img_tampak_samping_1:");
        stringBuilder.append(getImg_tampak_samping_1());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{img_tampak_samping_2:");
        stringBuilder.append(getImg_tampak_samping_2());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{img_tampak_dapur:");
        stringBuilder.append(getImg_tampak_dapur());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{img_tampak_jamban:");
        stringBuilder.append(getImg_tampak_jamban());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{img_tampak_sumber_air:");
        stringBuilder.append(getImg_tampak_sumber_air());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{longitude:");
        stringBuilder.append(getLongitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{latitude:");
        stringBuilder.append(getLatitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tahun_terima:");
        stringBuilder.append(getTahun_terima());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{keterangan:");
        stringBuilder.append(getKeterangan());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tgl_update:");
        stringBuilder.append(getTgl_update());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tgl_catat:");
        stringBuilder.append(getTgl_catat());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{is_catat:");
        stringBuilder.append(getIs_catat());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{deviceID:");
        stringBuilder.append(getdeviceID());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PenerimaRealmProxy aPenerima = (PenerimaRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aPenerima.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aPenerima.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aPenerima.row.getIndex()) return false;

        return true;
    }

}
