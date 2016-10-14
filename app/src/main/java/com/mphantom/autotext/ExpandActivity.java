package com.mphantom.autotext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mphantom.autotext.database.Expandhelper;

public class ExpandActivity extends AppCompatActivity {
    EditText etKey, etValue;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        etKey = (EditText) findViewById(R.id.et_key);
        etValue = (EditText) findViewById(R.id.et_value);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expandhelper.getInstance()
                        .insertExpand(new ExpandModle("wushaorong", "thanks"));
                finish();
            }
        });
    }
}
