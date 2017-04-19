package alejandro.com.mylogin.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import alejandro.com.mylogin.R;
import alejandro.com.mylogin.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText etEmail;
    private EditText etPassword;
    private Switch swRemember;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindUI();

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        setCredentialsIfExist();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (login(email, password)){
                    goToMain();
                    saveOnPPreferences(email,password);
                }
            }
        });
    }

    private void setCredentialsIfExist(){
        String email = Util.getUserMailPref(prefs);
        String password = Util.getPassMailPref(prefs);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            etEmail.setText(email);
            etPassword.setText(password);
        }
    }

    private void bindUI(){
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        swRemember = (Switch) findViewById(R.id.swRemember);
        btLogin = (Button) findViewById(R.id.btLogin);
    }

    private boolean login(String email, String password){
        if (!isValidEmail(email)){
            Toast.makeText(this,"Email is not valid, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)){
            Toast.makeText(this,"Password is not valid, 4 characters or more, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void saveOnPPreferences(String email, String password){
        if(swRemember.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email",email);
            editor.putString("pass",password);
            editor.apply();
        }
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        return password.length() >= 4;
    }

    private void goToMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}