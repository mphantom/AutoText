package com.mphantom.autotext;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mphantom.autotext.database.Expandhelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ExpandAdapter adapter;
    List<ExpandModle> list;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ExpandAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((v, postion) -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("expand", (Parcelable) v.getTag());
            ExpandActivity.start(MainActivity.this, bundle);
        });
        fab.setOnClickListener(v ->
                ExpandActivity.start(MainActivity.this)
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(Expandhelper.getInstance().getExpands());
        adapter.notifyDataSetChanged();
    }
}
