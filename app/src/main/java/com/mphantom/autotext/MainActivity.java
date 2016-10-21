package com.mphantom.autotext;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.accessibility.AccessibilityManager;

import com.mphantom.autotext.database.Expandhelper;
import com.mphantom.autotext.perference.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public final static String TAG = MainActivity.class.getName();
    RecyclerView recyclerView;
    ExpandAdapter adapter;
    Toolbar toolbar;
    List<ExpandModle> list;
    FloatingActionButton fab;
    SearchView searchView;
    NavigationView navigationView;
    SubMenu subMenu;
//    ImageView imgNavMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.getHeaderView(0).findViewById(R.id.img_navigate_menu)
                .setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
        subMenu = navigationView.getMenu()
                .addSubMenu(R.string.custom);
        subMenu.setGroupCheckable(0, true, true);
        addMenuItem("gold");
        addMenuItem("google");
        addMenuItem("ithome");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ExpandAdapter(list);
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        adapter.setOnItemClickListener((v, postion) -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("expand", (Parcelable) v.getTag());
            ExpandActivity.start(MainActivity.this, bundle);
        });
        fab.setOnClickListener(v ->
                ExpandActivity.start(MainActivity.this)
        );
        if (!checkAccessibilityenable()) {
            Snackbar.make(this.findViewById(R.id.activity_main),
                    R.string.please_open_accessbility, Snackbar.LENGTH_INDEFINITE).setAction(R.string.setting,
                    v -> startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), 0))
                    .show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview, menu);
        MenuItem menuItem = menu.findItem(R.id.search);//
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);//加载searchview
        searchView.setOnQueryTextListener(this);//为搜索框设置监听事件
        searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        searchView.setQueryHint(getString(R.string.search));//设置提示信息
        searchView.setIconifiedByDefault(true);//设置搜索默认为图标
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(Expandhelper.getInstance().getExpands());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private boolean checkAccessibilityenable() {
        AccessibilityManager am = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
//        boolean enable = am.isEnabled();
//        boolean touchEnabled = am.isTouchExplorationEnabled();
        List<AccessibilityServiceInfo> list = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
        for (AccessibilityServiceInfo info : list) {
            if (info.getId().equals("com.mphantom.autotext/.DetectService")) {
                return true;
            }
        }
        return false;
    }

    private void addMenuItem(String title) {
        subMenu.add(title).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setCheckable(true);
                item.setChecked(true);
                select(title);
                return true;
            }
        });
    }

    private void select(String packagename) {

    }
}
