package proyect.travelassistant.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import proyect.travelassistant.R;

public class SplashActivity extends Activity {

    private final int DURACION_SPLASH = 3000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SaltarAHome(DURACION_SPLASH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void SaltarAHome(int duration){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(getApplicationContext(), IntroActivity.class);
                startActivity(intent);
                finish();
            };
        }, duration);
    }

}