package com.mphantom.autotext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by wushaorong on 16-10-17.
 */

public class LayoutPreference extends Preference {

    private View mRootView;

    public LayoutPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, com.android.internal.R.styleable.Preference, 0, 0);
        int layoutResource = a.getResourceId(com.android.internal.R.styleable.Preference_layout,
                0);
        if (layoutResource == 0) {
            throw new IllegalArgumentException("LayoutPreference requires a layout to be defined");
        }
        // Need to create view now so that findViewById can be called immediately.
        final View view = LayoutInflater.from(getContext())
                .inflate(layoutResource, null, false);
        setView(view);
    }

    public LayoutPreference(Context context, int resource) {
        this(context, LayoutInflater.from(context).inflate(resource, null, false));
    }

    public LayoutPreference(Context context, View view) {
        super(context);
        setView(view);
    }

    private void setView(View view) {
        setLayoutResource(R.layout.layout_preference_frame);
        setSelectable(false);
        final ViewGroup allDetails = (ViewGroup) view.findViewById(R.id.all_details);
        if (allDetails != null) {
            Utils.forceCustomPadding(allDetails, true /* additive padding */);
        }
        mRootView = view;
        setShouldDisableView(false);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder view) {
        FrameLayout layout = (FrameLayout) view.itemView;
        layout.removeAllViews();
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        layout.addView(mRootView);
    }

    public View findViewById(int id) {
        return mRootView.findViewById(id);
    }

}