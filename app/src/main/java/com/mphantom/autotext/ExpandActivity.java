package com.mphantom.autotext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.mphantom.autotext.database.Expandhelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.et_value)
    EditText etValue;
    @BindView(R.id.btn_submit)
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
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        initWithParams();
    }

    @OnClick(R.id.btn_submit)
    public void submit() {
        if (TextUtils.isEmpty(etKey.getText().toString().trim())) {
            return;
        }
        if (TextUtils.isEmpty(etValue.getText().toString().trim())) {
            return;
        }
        Expandhelper.getInstance()
                .insertExpand(new ExpandModle(etKey.getText().toString(),
                        etValue.getText().toString()));
        finish();
    }

    private void initWithParams() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ExpandModle expandModle = bundle.getParcelable("expand");
            etKey.setText(expandModle.getKey());
            etValue.setText(expandModle.getValue());
        }
    }
}
