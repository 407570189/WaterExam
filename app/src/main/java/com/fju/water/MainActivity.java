package com.fju.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getSimpleName() ;
    private TextView month;
        private Button result;
    private double outcome;
    private String title;
    private Double outcome1;
    boolean isNext =false;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Log.d(TAG,"onCreate");
        month = findViewById(R.id.month);
                result = findViewById(R.id.summit);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Switch sw =findViewById(R.id.switch2);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isNext =isChecked;
                TextView tv =findViewById(R.id.type);
                tv.setText(isNext?getString(R.string.every_other_month):getString(R.string.monthly));
            }
        });
        //找到下拉選單得東西
        Spinner cities =findViewById(R.id.spinner);
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,getResources().getStringArray(R.array.cities)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button =findViewById(R.id.summit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = "";
                if (isNext==false) {
                    float monthn = Float.parseFloat(month.getText().toString());
                    outcome = 0;
                    if (monthn >= 1 && monthn <= 10) {
                        outcome = monthn * 7.35f;
                    } else if (monthn >= 11 && monthn <= 30) {
                        outcome = (monthn * 9.45f) - 21;
                    } else if (monthn >= 31 && monthn <= 50) {
                        outcome = (monthn * 11.55f) - 84;
                    } else if (monthn >= 51) {
                        outcome = (monthn * 12.075f) - 110.25f;
                    }
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra(getString(R.string.extra_fee), outcome);
                    startActivity(intent);
                }
                if (isNext){
                    float nextn = Float.parseFloat(month.getText().toString());
                    if (nextn>=1&&nextn<=20){
                        outcome=nextn*7.35f;
                    }
                    else if (nextn>=21&&nextn<=60){
                        outcome=nextn*9.45f-42;
                    } else if (nextn>=61&&nextn<=100) {
                        outcome = nextn * 11.55f - 168;
                    }
                    else {
                        outcome = nextn * 12.075f - 220.5f;
                    }
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("隔月抄表")
                            .setMessage("費用: "+outcome)
                            .setPositiveButton("ok",null)
                            .show();
                }

              /*  new AlertDialog.Builder(MainActivity.this)
                        .setTitle("每月抄表")
                        .setMessage(getString(R.string.fee)+outcome)
                        .setPositiveButton(getString(R.string.ok),null)
                        .show();*/
                }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
