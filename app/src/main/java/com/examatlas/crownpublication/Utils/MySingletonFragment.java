package com.examatlas.crownpublication.Utils;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingletonFragment {
    private static MySingletonFragment mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    // Private constructor to prevent instantiation from other classes
    private MySingletonFragment(Context context) {
        mContext = context.getApplicationContext(); // Use application context to avoid memory leaks
        mRequestQueue = getRequestQueue();
    }

    public static synchronized MySingletonFragment getInstance(Fragment fragment) {
        if (mInstance == null) {
            mInstance = new MySingletonFragment(fragment.requireActivity());
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
