package proyect.travelassistant.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import proyect.travelassistant.R;
import proyect.travelassistant.beans.AuxiliarData;
import proyect.travelassistant.fragments.FragmentIntro;
import proyect.travelassistant.utils.RestClient;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_intro);
        setFirstLevelToolbar(getResources().getString(R.string.app_name));

        Intent intent = getIntent();
        int idItem = intent.getIntExtra("NotifID",-1);
        AuxiliarData.getSingletonInstance().setItemId(idItem);

        FragmentIntro mFragment = new FragmentIntro();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.intro_container, mFragment, "fragment_intro");
        ft.addToBackStack(null).commit();
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_intro;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
