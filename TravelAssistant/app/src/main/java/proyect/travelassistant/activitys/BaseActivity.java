package proyect.travelassistant.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import proyect.travelassistant.R;

public abstract class BaseActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private Context context;
    private boolean firstLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        // Drawer Toggle Object Made
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        initNavigationView();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void setContentChildView(int id) {
        FrameLayout layout = (FrameLayout) findViewById(R.id.activity_container);
        View view = getLayoutInflater().inflate(id, null, false);
        layout.addView(view);
    }

    private void showToolbarLogo() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvTitle.setVisibility(View.GONE);
        ImageView ivLogo = (ImageView) findViewById(R.id.iv_toolbar_logo);
        ivLogo.setVisibility(View.VISIBLE);
    }

    private void showToolbarTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvTitle.setText(title);
        tvTitle.setVisibility(View.VISIBLE);
        ImageView ivLogo = (ImageView) findViewById(R.id.iv_toolbar_logo);
        ivLogo.setVisibility(View.GONE);
    }

    private void initNavigationView(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.action_menu_intro) {
                    startActivity(new Intent(BaseActivity.this, IntroActivity.class));
                    finish();
                } else if (itemId == R.id.action_menu_new_query) {
                    startActivity(new Intent(BaseActivity.this, NewQueryActivity.class));
                    finish();
                } else if (itemId == R.id.action_menu_historical) {
                    startActivity(new Intent(BaseActivity.this, HistoricalActivity.class));
                    finish();
                } else if (itemId == R.id.action_menu_edit_recommendations) {
                    startActivity(new Intent(BaseActivity.this, RecomCustomActivity.class));
                    finish();
                } else if (itemId == R.id.action_menu_restore_recommendations) {
                    startActivity(new Intent(BaseActivity.this, RestoreActivity.class));
                    finish();
                }else if (itemId == R.id.action_menu_help) {
                    startActivity(new Intent(BaseActivity.this, HelpActivity.class));
                    finish();
                }else if (itemId == R.id.action_menu_contact) {
                    startActivity(new Intent(BaseActivity.this, ContactActivity.class));
                    finish();
                }

                mDrawerLayout.closeDrawers();
                return true;
            }

        };

        navigationView.setNavigationItemSelectedListener(listener);
    }

    public void setFirstLevelToolbar(String title) {
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        showToolbarTitle(title);
        firstLevel = true;
        mDrawerToggle.syncState();
    }

    public void setSecondLevelToolbar(View.OnClickListener onBackListener) {
        setSecondLevelToolbar(onBackListener, null, -1);
        firstLevel = false;
    }

    protected void setSecondLevelToolbar(View.OnClickListener onBackListener, Menu menu, int idMenu) {
        if (idMenu != -1) {
            getMenuInflater().inflate(idMenu, menu);
        }
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle.setToolbarNavigationClickListener(onBackListener);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Menu menu = navigationView.getMenu();
        updateNavigationDrawerState();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    /* NAVIGATION DRAWER MENU */
    private void updateNavigationDrawerState(){
        int actionId = getNavigationDrawerMenuItemId();
        selectNavigationDrawerItem(actionId);
    }

    void selectNavigationDrawerItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }else{
            }
        }
    }

    abstract int getNavigationDrawerMenuItemId();
}



