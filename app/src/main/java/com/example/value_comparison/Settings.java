package com.example.value_comparison;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFragment settingsFragment = new SettingsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, settingsFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onButtonBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}