package br.com.cuiateam.ajudame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Marcos on 14/09/2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}