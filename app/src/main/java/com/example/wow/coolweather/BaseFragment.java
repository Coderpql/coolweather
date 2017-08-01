package com.example.wow.coolweather;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

/**
 * Created by wow on 2017/8/1.
 */

public class BaseFragment extends Fragment {
    private Activity activity;

    public Context getContext() {
        if (activity == null) {
            return MyApplication.getInstance();
        }
        return activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }
}
