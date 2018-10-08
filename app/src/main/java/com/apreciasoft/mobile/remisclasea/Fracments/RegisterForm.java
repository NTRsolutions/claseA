package com.apreciasoft.mobile.remisclasea.Fracments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;
import com.apreciasoft.mobile.remisclasea.R;

/**
 * Created by usario on 31/7/2017.
 */

public class RegisterForm extends AppCompatActivity {

    public static final int REGISTER_DRIVER_ACTIVITY = 1;
    public static final int REGISTER_CLIENT_ACTIVITY = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Registro de Usuario!");

        //HttpConexion.setBase("as_remis_web");
        HttpConexion.setBase(HttpConexion.instance);

        final ImageButton btn_new_driver = (ImageButton) findViewById(R.id.btn_new_driver);
        btn_new_driver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(getApplicationContext(), NewFormDriver.class);
                startActivityForResult(intent, REGISTER_DRIVER_ACTIVITY);
            }
        });

        final ImageButton btn_new_client = (ImageButton) findViewById(R.id.btn_new_client);
        btn_new_client.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
               Intent intent = new Intent(getApplicationContext(), NewFormClient.class);
                startActivityForResult(intent, REGISTER_CLIENT_ACTIVITY);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                HttpConexion.setBase(HttpConexion.instance);
                this.finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onBackPressed() {
        HttpConexion.setBase(HttpConexion.instance);
        finish();

    }
}
