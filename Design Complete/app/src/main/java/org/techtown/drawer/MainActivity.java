package org.techtown.drawer;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment4 fragment4;
    Fragment5 fragment5;
    Fragment6 fragment6;
    Fragment7 fragment7;

    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        fragment6 = new Fragment6();
        fragment7 = new Fragment7();

        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu1) {
            Toast.makeText(this, "홈", Toast.LENGTH_LONG).show();
            onFragmentSelected(0, null);
        } else if (id == R.id.menu2) {
            Toast.makeText(this, "상태", Toast.LENGTH_LONG).show();
            onFragmentSelected(1, null);
        } else if (id == R.id.menu4) {
            Toast.makeText(this, "밝기", Toast.LENGTH_LONG).show();
            onFragmentSelected(3, null);
        } else if (id == R.id.menu5) {
            Toast.makeText(this, "먹이", Toast.LENGTH_LONG).show();
            onFragmentSelected(4, null);
        } else if (id == R.id.menu6) {
            Toast.makeText(this, "관찰", Toast.LENGTH_LONG).show();
            onFragmentSelected(5, null);
        } else if (id == R.id.menu7) {
            Toast.makeText(this, "캘린더", Toast.LENGTH_LONG).show();
            onFragmentSelected(6, null);
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;

        if (position == 0) {
            curFragment = fragment1;
            toolbar.setTitle("홈");
        } else if (position == 1) {
            curFragment = fragment2;
            toolbar.setTitle("상태");
        } else if (position == 3) {
            toolbar.setTitle("밝기");
            curFragment = fragment4;
        } else if (position == 4) {
            toolbar.setTitle("먹이");
            curFragment = fragment5;
        } else if (position == 5) {
            toolbar.setTitle("관찰");
            curFragment = fragment6;
        } else if (position == 6) {
            toolbar.setTitle("캘린더");
            curFragment = fragment7;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }

}
