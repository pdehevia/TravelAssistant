package proyect.travelassistant.activitys;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import proyect.travelassistant.R;

public class ContactActivity extends BaseActivity {

    private TextView tvEmail;
    private TextView tvPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentChildView(R.layout.activity_contact);
        setFirstLevelToolbar(getResources().getString(R.string.menu_item7));

        tvEmail = (TextView) findViewById(R.id.textViewMailContact);
        tvEmail.setPaintFlags(tvEmail.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmail();
            }
        });

        tvPhone = (TextView) findViewById(R.id.textViewPhoneContact);
        tvPhone.setPaintFlags(tvPhone.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhone();
            }
        });
    }

    @Override
    int getNavigationDrawerMenuItemId() {
        return R.id.action_menu_contact;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ContactActivity.this, IntroActivity.class));
        finish();
    }

    private void openEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.contact_mail));
        //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void openPhone(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + getResources().getString(R.string.contact_phone)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
