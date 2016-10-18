package com.mphantom.autotext;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mphantom.autotext.database.Expandhelper;
import com.mphantom.autotext.perference.SettingsActivity;

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
//        list.add(new ExpandModle("key", "value"));
//        list.add(new ExpandModle("key", "value"));
//        list.add(new ExpandModle("key", "value"));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExpandActivity.class));
            }
        });

        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(Expandhelper.getInstance().getExpands());
        adapter.notifyDataSetChanged();
    }
}
