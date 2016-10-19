package com.mphantom.autotext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.mphantom.autotext.database.Expandhelper;

public class ExpandActivity extends AppCompatActivity {
    EditText etKey, etValue;
    Button btnSubmit;

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ExpandActivity.class);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        ExpandModle expandModle = getIntent().getExtras().getParcelable("expand");
        etKey = (EditText) findViewById(R.id.et_key);
        etValue = (EditText) findViewById(R.id.et_value);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(v -> {
            Expandhelper.getInstance()
                    .insertExpand(new ExpandModle("wushaorong", "thanks"));
            finish();
        });
        etKey.setText(expandModle.getKey());
        etValue.setText(expandModle.getValue());
    }
}
