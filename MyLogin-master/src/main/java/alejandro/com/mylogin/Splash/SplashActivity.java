package alejandro.com.mylogin.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import alejandro.com.mylogin.Activities.LoginActivity;
import alejandro.com.mylogin.Activities.MainActivity;
import alejandro.com.mylogin.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);

        if(!TextUtils.isEmpty(Util.getUserMailPref(prefs)) &&
                !TextUtils.isEmpty(Util.getPassMailPref(prefs))){
            startActivity(intentMain);
        } else{
            startActivity(intentLogin);
        }
        finish();
    }

}
