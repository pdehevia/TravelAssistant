package proyect.travelassistant.activitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import proyect.travelassistant.R;
import proyect.travelassistant.sqlite.RecomsDB;

public class RestoreActivity extends BaseActivity {

    private Context context;
    private LinearLayout container;
    private Button btnHome;
    private Button btnRecoms;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_restore);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item5));
        context = this;

        container = (LinearLayout) findViewById(R.id.restoreContainer);

        new AlertDialog.Builder(context)
                .setTitle(getString(R.string.title_alert_del_BBDD))
                .setMessage(getString(R.string.text_alert_del_BBDD))
                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        RecomsDB recomsDB = new RecomsDB(context);
                        recomsDB.open();
                        recomsDB.resetDatabase(context);
                        recomsDB.close();
                        container.setVisibility(View.VISIBLE);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(RestoreActivity.this, IntroActivity.class));
                        finish();
                    }
                })
                .show();

        btnHome = (Button) findViewById(R.id.buttonRestoreHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestoreActivity.this, IntroActivity.class));
                finish();
            }
        });

        btnRecoms = (Button) findViewById(R.id.buttonRestoreEdit);
        btnRecoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestoreActivity.this, RecomCustomActivity.class));
                finish();
            }
        });

        btnSearch = (Button) findViewById(R.id.buttonRestoreSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestoreActivity.this, NewQueryActivity.class));
                finish();
            }
        });
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_restore_recommendations;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RestoreActivity.this, IntroActivity.class));
        finish();
    }
}
