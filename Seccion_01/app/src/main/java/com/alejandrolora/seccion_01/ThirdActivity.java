package com.alejandrolora.seccion_01;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText etPhone;
    private EditText etWeb;
    private ImageButton ibPhone;
    private ImageButton ibWeb;
    private ImageButton ibCamera;

    private final int PHONE_CALL_CODE = 100;
    private final int PICTURE_FROM_CAMERA = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPhone = (EditText) findViewById(R.id.ud_phone);
        etWeb = (EditText) findViewById(R.id.et_web);
        ibPhone = (ImageButton) findViewById(R.id.ib_phone);
        ibWeb = (ImageButton) findViewById(R.id.ib_web);
        ibCamera = (ImageButton) findViewById(R.id.ib_camera);
        ibPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = etPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        if(CheckPermission(Manifest.permission.CALL_PHONE)) {

                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                            if (ActivityCompat.checkSelfPermission(ThirdActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                            startActivity(i);
                        }
                        else {

                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {

                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            }else {

                                Toast.makeText(ThirdActivity.this, "Please, enable the request permission", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }
                    } else {
                        OlderVersions(phoneNumber);
                    }
                }
                else {
                    Toast.makeText(ThirdActivity.this, "Insert a phone number", Toast.LENGTH_SHORT).show();
                }
            }

            private void OlderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ibWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = etWeb.getText().toString();
                String email = "pruebao@gmail.com";

                if (url != null && !url.isEmpty()) {
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));

                    Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));

                    Intent intentMailTo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+email));

                    Intent intentMail = new Intent(Intent.ACTION_SEND, Uri.parse(email));
                    intentMail.setType("plain/text");
                    intentMail.putExtra(Intent.EXTRA_SUBJECT, "Mail's title");
                    intentMail.putExtra(Intent.EXTRA_TEXT, "Hi there, I love MyForm app, but... ");
                    intentMail.putExtra(Intent.EXTRA_EMAIL, new String[] {"fernando@gmail.com", "antonio@gmail.com"});


                    Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:666111222"));


                    startActivity(intentMail);
                }
            }
        });

        ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCamera, PICTURE_FROM_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PICTURE_FROM_CAMERA:

                if (resultCode == Activity.RESULT_OK) {
                    String result = data.toUri(0);
                    Toast.makeText(this, "Result: "+result, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "There was an error with the picture, try again.", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {

                    if (result == PackageManager.PERMISSION_GRANTED) {

                        String phoneNumber = etPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                        startActivity(intentCall);
                    }
                    else {

                        Toast.makeText(ThirdActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
                    }
                }break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean CheckPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
