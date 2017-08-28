package proyect.travelassistant.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import proyect.travelassistant.R;
import proyect.travelassistant.fragments.FragmentIntro;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_intro);
        setFirstLevelToolbar(getResources().getString(R.string.app_name));

        Intent intent = getIntent();
        int idItem = -1;//intent.getIntExtra("NotifID",-1);

        FragmentIntro mFragment = new FragmentIntro();
        mFragment.idItem = idItem;
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
