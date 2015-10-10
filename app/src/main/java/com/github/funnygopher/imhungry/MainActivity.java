package com.github.funnygopher.imhungry;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the toolbar as the primary actionbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("I'm Hungry");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        setSupportActionBar(mToolbar);

        // Sets up the item click listener for the drawer
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                mDrawerLayout.closeDrawers();

                switch(menuItem.getItemId()) {
                    case R.id.im_hungry_drawer_item:
                        Toast.makeText(getApplicationContext(), "I'm Hungry opened!", Toast.LENGTH_SHORT).show();
                        // Create new fragment
                        ImHungryFragment newFragment = new ImHungryFragment();
                        swapFragment(newFragment);
                        return true;
                }

                return false;
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(
                        this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer
                );

        mDrawerLayout.setDrawerListener(drawerToggle); // Sets the
        drawerToggle.syncState(); // Causes the hamburger icon to show

        ImHungryFragment newFragment = new ImHungryFragment();
        swapFragment(newFragment);
    }

    private void swapFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
