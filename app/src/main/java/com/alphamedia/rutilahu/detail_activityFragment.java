package com.alphamedia.rutilahu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphamedia.rutilahu.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class detail_activityFragment extends Fragment {

    public detail_activityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_activity, container, false);
    }
}
