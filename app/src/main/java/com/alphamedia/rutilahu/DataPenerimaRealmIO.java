package com.alphamedia.rutilahu;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;


public class DataPenerimaRealmIO extends ActionBarActivity implements LoaderManager.LoaderCallbacks<String> {

    //private GridView mGridView;
    private DataPenerimaAdapter mAdapter;
    private ListView mListView;
    private Realm realm;
    RealmConfiguration realmConfiguration;

    private boolean mSearchOpened = false;
    private String mSearchQuery = "";
    private EditText mSearchEt;

    private MenuItem mSearchAction;

    private Drawable mIconCloseSearch;
    private Drawable mIconOpenSearch;

    RealmResults<Penerima> result;

    private static final int LOAD_NETWORK_A = 1;
    private static final int LOAD_NETWORK_B = 2;
    private static final int LOAD_NETWORK_C = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_penerima_realm_io);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mIconOpenSearch = getResources()
                .getDrawable(R.drawable.ic_search_black_18dp);
        mIconCloseSearch = getResources()
                .getDrawable(R.drawable.ic_clear_black_18dp);

        try {
            if (realm != null) {
                realm.close();
            }
            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
            Realm.deleteRealm(realmConfiguration);
            Realm.setDefaultConfiguration(realmConfiguration);
        } catch (RealmException e)
        {
            Log.e("Error: ", e.getMessage());
        }

        getLoaderManager().initLoader(LOAD_NETWORK_C, null, this).forceLoad();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            realm = Realm.getInstance(this);
        } catch (RealmException e) {
            Log.e("Error: ", e.getMessage());
            Realm.deleteRealmFile(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //realm.close();
        //Realm.deleteRealm(realmConfiguration);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        //menu.findItem(R.id.action_save).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_penerima_realm_io, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            item.setIcon(R.drawable.ic_settings_black_18dp);
            startActivity(new Intent(this, SettingsaActivity.class));
            return true;
        }

        if (id == R.id.action_search) {
            if (mSearchOpened) {
                closeSearchBar();
            } else {
                openSearchBar(mSearchQuery);
            }
            return true;
        }

        if (id == R.id.action_downloads) {
            Intent download = new Intent(getApplicationContext(),Download.class);
            startActivity(download);
            return true;
        }

        if (id == R.id.action_upload) {
            Intent upload = new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(upload);
            return true;
        }

        if (id == R.id.action_refresh) {
            refreshView();
            return true;
        }

        if (id == R.id.map) {
            Intent map = new Intent(getApplicationContext(),MapActivity.class);
            startActivity(map);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshView()
    {
        mAdapter = new DataPenerimaAdapter(this);
        if(result == null)
        {
            RealmResults<Penerima> result;
            try {
                result = (RealmResults<Penerima>) loadPenerima();
                result = realm.where(Penerima.class).findAll();
                result.sort("namalengkap", RealmResults.SORT_ORDER_ASCENDING);
                mAdapter.setData(result);
                mListView = (ListView) findViewById(R.id.custom_list);
                mListView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mListView.invalidate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
        {
            result = realm.where(Penerima.class).findAll();
            result.sort("namalengkap", RealmResults.SORT_ORDER_ASCENDING);
            mAdapter.setData(result);
            mListView = (ListView) findViewById(R.id.custom_list);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mListView.invalidate();
        }
        //realm.close();
    }

    private void openSearchBar(String queryText) {
        // Set custom view on action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.search_bar);
        // Search edit text field setup.
        mSearchEt = (EditText) actionBar.getCustomView()
                .findViewById(R.id.etSearch);
        mSearchEt.addTextChangedListener(new SearchWatcher());
        mSearchEt.setText(queryText);
        mSearchEt.requestFocus();
        mSearchAction.setIcon(mIconCloseSearch);
        mSearchOpened = true;
    }

    private void closeSearchBar() {
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        mSearchAction.setIcon(mIconOpenSearch);
        mSearchEt.setText("");
        mSearchOpened = false;
        refreshView();
    }

    /**
     * Responsible for handling changes in search edit text.
     */
    private class SearchWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence c, int i, int i2, int i3) {
            Log.i("Status TextWatcher: ", "beforeTextChanged");
        }

        @Override
        public void onTextChanged(CharSequence c, int i, int i2, int i3) {
            Log.i("Status TextWatcher: ", "onTextChanged");
        }

        @Override
        public void afterTextChanged(Editable editable) {
            mSearchQuery = mSearchEt.getText().toString();
            Log.i("Query Search: ", mSearchQuery);
            result = realm.where(Penerima.class)
                    .contains("namalengkap", mSearchQuery, false)
                    .or()
                    .contains("jalan_desa", mSearchQuery, false)
                    .or()
                    .contains("desa", mSearchQuery, false)
                    .or()
                    .contains("kecamatan", mSearchQuery, false)
                    .or()
                    .contains("kabupaten", mSearchQuery, false)
                    .findAll();
            result.sort("namalengkap", RealmResults.SORT_ORDER_ASCENDING);

            mAdapter = new DataPenerimaAdapter(getApplicationContext());
            mAdapter.setData(result);
            mListView = (ListView) findViewById(R.id.custom_list);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

    }

    public List<Penerima> loadPenerima() throws IOException {
        return realm.allObjects(Penerima.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        //return new ApiLoaderTask(this, DataPenerimaRealmIO.class, "C.json");
        switch (i) {
            case LOAD_NETWORK_C:
                return new ApiLoaderTask(this, DataPenerimaRealmIO.class, "data.json");
            default:
                return new ApiLoaderTask(this, DataPenerimaRealmIO.class, "data.json");
        }
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String string) {
        if(mAdapter == null) {
            List<Penerima> penerima = null;
            try {
                penerima = loadPenerima();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mAdapter = new DataPenerimaAdapter(this);
            mAdapter.setData(penerima);
            mListView = (ListView) findViewById(R.id.custom_list);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mListView.invalidate();

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.nama);
                    TextView ktp = (TextView) view.findViewById(R.id.ktp);
                    Toast.makeText(getApplicationContext(),
                            "Nama: " + textView.getText().toString() + " - " +
                            "KTP: " + ktp.getText().toString(),
                            Toast.LENGTH_SHORT)
                            .show();
                    Intent i = new Intent(DataPenerimaRealmIO.this, DetailActivity.class);
                    i.putExtra("ktp", ktp.getText().toString());
                    startActivity(i);
                }

            });

        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        try {
            realm = Realm.getInstance(this);
        }
        catch (IllegalStateException e) {
            Log.e("Error: ", e.getMessage());
        }
    }

    public static class ApiLoaderTask extends AsyncTaskLoader<String> {

        private String mFile;
        private Class mClass;
        private Realm realm;

        public ApiLoaderTask(Context context, Class klass, String file) {
            super(context);
            mFile = file;
            mClass = klass;
        }

        @Override
        public String loadInBackground() {
            Log.d("Loader", mFile);
            realm = Realm.getInstance(getContext());
            realm.beginTransaction();
            try {
                loadJsonFromStream(realm);
            } catch (IOException e) {
                e.printStackTrace();
                realm.cancelTransaction();
                realm.close();
            }
            realm.commitTransaction();
            //realm.close();
            return "";
        }

        @Override
        protected void onStartLoading() {
        }

        @Override
        protected void onStopLoading() {
            cancelLoad();
        }

        @Override
        protected void onReset() {
            super.onReset();
            onStopLoading();
        }

        private void loadJsonFromStream(Realm realm) throws IOException {
            File initialFile = new File(Config.DATA_DIR);
            if(initialFile.exists())
            {
                InputStream stream = new FileInputStream(initialFile);
                try {
                    realm.createAllFromJson(Penerima.class, stream);
                } catch (IOException e) {
                    Log.e("Error: ", e.getMessage() + " - getStackTrace: " + e.getStackTrace().toString());
                    realm.cancelTransaction();
                } finally {
                    if (stream != null) {
                        stream.close();
                    }
                }
            } else
            {
                Log.e("Error: ", "File tidak ditemukan");
            }
        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(DataPenerimaRealmIO.this).setMessage(
                R.string.exit_message).setTitle(
                R.string.exit_title).setCancelable(false)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                setResult(RESULT_CANCELED);
                                Intent i = new Intent();
                                quit(false,i);
                            }
                        }).setNeutralButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                    }
                }).show();
    }

    private void quit(boolean success, Intent i) {
        setResult((success) ? -1:0, i);
        this.finish();
    }


}
