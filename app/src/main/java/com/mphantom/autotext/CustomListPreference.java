package com.mphantom.autotext;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v14.preference.ListPreferenceDialogFragment;
import android.support.v7.preference.ListPreference;
import android.util.AttributeSet;

/**
 * Created by wushaorong on 16-10-17.
 */

public class CustomListPreference extends ListPreference {

    public CustomListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListPreference(Context context, AttributeSet attrs, int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onPrepareDialogBuilder(AlertDialog.Builder builder,
                                          DialogInterface.OnClickListener listener) {
    }

    protected void onDialogClosed(boolean positiveResult) {
    }

    protected void onDialogCreated(Dialog dialog) {
    }

    protected boolean isAutoClosePreference() {
        return true;
    }

    protected void onDialogStateRestored(Dialog dialog, Bundle savedInstanceState) {
    }

    public static class CustomListPreferenceDialogFragment extends ListPreferenceDialogFragment {

        private static final java.lang.String KEY_CLICKED_ENTRY_INDEX
                = "settings.CustomListPrefDialog.KEY_CLICKED_ENTRY_INDEX";

        private int mClickedDialogEntryIndex;

        public static ListPreferenceDialogFragment newInstance(String key) {
            final ListPreferenceDialogFragment fragment = new CustomListPreferenceDialogFragment();
            final Bundle b = new Bundle(1);
            b.putString(ARG_KEY, key);
            fragment.setArguments(b);
            return fragment;
        }

        private CustomListPreference getCustomizablePreference() {
            return (CustomListPreference) getPreference();
        }

        @Override
        protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
            super.onPrepareDialogBuilder(builder);
            mClickedDialogEntryIndex = getCustomizablePreference()
                    .findIndexOfValue(getCustomizablePreference().getValue());
            getCustomizablePreference().onPrepareDialogBuilder(builder, getOnItemClickListener());
            if (!getCustomizablePreference().isAutoClosePreference()) {
                builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CustomListPreferenceDialogFragment.this.onClick(dialog,
                                DialogInterface.BUTTON_POSITIVE);
                        dialog.dismiss();
                    }
                });
            }
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            if (savedInstanceState != null) {
                mClickedDialogEntryIndex = savedInstanceState.getInt(KEY_CLICKED_ENTRY_INDEX,
                        mClickedDialogEntryIndex);
            }
            getCustomizablePreference().onDialogCreated(dialog);
            return dialog;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt(KEY_CLICKED_ENTRY_INDEX, mClickedDialogEntryIndex);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            getCustomizablePreference().onDialogStateRestored(getDialog(), savedInstanceState);
        }

        protected DialogInterface.OnClickListener getOnItemClickListener() {
            return new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    setClickedDialogEntryIndex(which);


                    if (getCustomizablePreference().isAutoClosePreference()) {
                        /*
                         * Clicking on an item simulates the positive button
                         * click, and dismisses the dialog.
                         */
                        CustomListPreferenceDialogFragment.this.onClick(dialog,
                                DialogInterface.BUTTON_POSITIVE);
                        dialog.dismiss();
                    }
                }
            };
        }

        protected void setClickedDialogEntryIndex(int which) {
            mClickedDialogEntryIndex = which;
        }

        @Override
        public void onDialogClosed(boolean positiveResult) {
            getCustomizablePreference().onDialogClosed(positiveResult);
            final ListPreference preference = getCustomizablePreference();
            if (positiveResult && mClickedDialogEntryIndex >= 0 &&
                    preference.getEntryValues() != null) {
                String value = preference.getEntryValues()[mClickedDialogEntryIndex].toString();
                if (preference.callChangeListener(value)) {
                    preference.setValue(value);
                }
            }
        }
    }
}