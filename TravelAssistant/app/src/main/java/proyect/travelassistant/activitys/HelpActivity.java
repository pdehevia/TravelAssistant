package proyect.travelassistant.activitys;

import android.content.Intent;
import android.os.Bundle;

import proyect.travelassistant.R;

public class HelpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_help);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item6));
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_help;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HelpActivity.this, IntroActivity.class));
        finish();
    }
}
