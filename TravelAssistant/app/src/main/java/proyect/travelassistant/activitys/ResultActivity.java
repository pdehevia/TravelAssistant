package proyect.travelassistant.activitys;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import proyect.travelassistant.R;
import proyect.travelassistant.adapters.ViewPagerAdapter;
import proyect.travelassistant.beans.worldweather.Response;
import proyect.travelassistant.fragments.FragmentTabResultsActual;
import proyect.travelassistant.fragments.FragmentTabResultsHistorical;
import proyect.travelassistant.fragments.FragmentTabResultsRecom;

public class ResultActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public Response response;
    public String criterio1;
    public String criterio2;
    public String criterio3;
    public String criterio4;
    public String destino;
    public double lat;
    public double lon;
    public boolean consultaExistente;
    public long idConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_result);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item2));

        Intent intent = getIntent();
        response = (Response) intent.getSerializableExtra("Response");
        criterio1 = intent.getStringExtra("Duracion");
        criterio2 = intent.getStringExtra("Motivo");
        criterio3 = intent.getStringExtra("Alojamiento");
        criterio4 = intent.getStringExtra("Transporte");
        destino = intent.getStringExtra("Destino");
        lat = intent.getDoubleExtra("Lat",0);
        lon = intent.getDoubleExtra("Lon",0);
        consultaExistente = intent.getBooleanExtra("ConsultaExiste",false);
        if(consultaExistente){
            idConsulta = intent.getLongExtra("ConsultaID",-99);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //setupTabIcons();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //Ocultar teclado si está visible
                InputMethodManager imm = (InputMethodManager) tabLayout.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tabLayout.getWindowToken(), 0);

                switch (tab.getPosition()){
                    case 0:
                        //tab.setIcon(R.drawable.check_sel);
                        tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(),R.color.tab_unselect),ContextCompat.getColor(getApplicationContext(),R.color.tab_select));
                        break;
                    case 1:
                        //tab.setIcon(R.drawable.weather_1_sel);
                        tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(),R.color.tab_unselect),ContextCompat.getColor(getApplicationContext(),R.color.tab_select));
                        break;
                    case 2:
                        //tab.setIcon(R.drawable.weather_2_sel);
                        tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(),R.color.tab_unselect),ContextCompat.getColor(getApplicationContext(),R.color.tab_select));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /*
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.drawable.check);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.weather_1);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.weather_2);
                        break;
                }
                */
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_new_query;
    }

    /*
    private void setupTabIcons() {
        tabLayout.getTabAt(2).setIcon(R.drawable.check);
        tabLayout.getTabAt(0).setIcon(R.drawable.weather_1_sel);
        tabLayout.getTabAt(1).setIcon(R.drawable.weather_2);

    }
    */


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentTabResultsRecom(), getResources().getString(R.string.title_tab3));
        adapter.addFragment(new FragmentTabResultsActual(), getResources().getString(R.string.title_tab2));
        adapter.addFragment(new FragmentTabResultsHistorical(), getResources().getString(R.string.title_tab1));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this, IntroActivity.class));
        finish();
    }
}
