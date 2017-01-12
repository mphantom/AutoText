package com.mphantom.autotext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mphantom.autotext.database.Expandhelper;
import com.mphantom.realmhelper.ExpandModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class ExpandActivity extends AppCompatActivity {
    @BindView(R.id.activity_expand)
    RelativeLayout rlcontainer;
    @BindView(R.id.fab)
    FloatingActionButton fab;
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
        context.startActivity(intent, bundle);
    }

    public static void start(Context context, Bundle bundle, Bundle commpend) {
        Intent intent = new Intent(context, ExpandActivity.class);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent, commpend);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        ButterKnife.bind(this);
        setupEnterAnimation();
        setupExitAnimation();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.change);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        initWithParams();
    }

    private void initViews() {
        new Handler(Looper.getMainLooper())
                .post(() -> {
                    Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
                    animation.setDuration(100);
                    toolbar.startAnimation(animation);
                    etKey.startAnimation(animation);
                    etValue.startAnimation(animation);
                    btnSubmit.startAnimation(animation);
                    toolbar.setVisibility(View.VISIBLE);
                    etKey.setVisibility(View.VISIBLE);
                    etValue.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.VISIBLE);
                });
    }

    private void initWithParams() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ExpandModle expandModle = bundle.getParcelable("expand");
            etKey.setText(expandModle.getKey());
            etValue.setText(expandModle.getValue());
        }
    }


    //退场动画
    private void setupExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(300);
    }

    //入场动画
    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void animateRevealShow() {
        GuiUtils.animateRevealShow(
                this, rlcontainer,
                fab.getWidth() / 2, R.color.colorAccent,
                new GuiUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        initViews();
                    }


                });
    }

    @Override
    public void onBackPressed() {
        GuiUtils.animateRevealHide(
                this, rlcontainer,
                fab.getWidth() / 2, R.color.colorAccent,
                new GuiUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        defaultBackPressed();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }

    private void defaultBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_submit)
    public void submit(View view) {
        if (TextUtils.isEmpty(etKey.getText().toString().trim())) {
            return;
        }
        if (TextUtils.isEmpty(etValue.getText().toString().trim())) {
            return;
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ExpandModel expand = realm.createObject(ExpandModel.class);
        expand.setKey(etKey.getText().toString());
        expand.setValue(etValue.getText().toString());
        realm.commitTransaction();
        Expandhelper.getInstance()
                .insertExpand(new ExpandModle(etKey.getText().toString(),
                        etValue.getText().toString()));
        finish();
    }


}
