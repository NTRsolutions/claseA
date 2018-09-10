package com.apreciasoft.admin.remisclasea.Fracments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.apreciasoft.admin.remisclasea.R;
import com.mercadopago.callbacks.Callback;
import com.mercadopago.core.MercadoPago;
import com.mercadopago.model.ApiException;
import com.mercadopago.model.IdentificationType;

import java.util.List;

/**
 * Created by usario on 19/7/2017.
 */

public class activity_card extends AppCompatActivity {

    private MercadoPago mMercadoPago;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Configurar Trajeta");


        // Init MercadoPago object with public key
        mMercadoPago = new MercadoPago.Builder()
                .setContext(this)
                .setPublicKey("TEST-ad365c37-8012-4014-84f5-6c895b3f8e0a")
                .build();

        // Get identification types
        getIdentificationTypesAsync();

    }

    private void getIdentificationTypesAsync() {
        mMercadoPago.getIdentificationTypes(new Callback<List<IdentificationType>>() {
            @Override
            public void success(List<IdentificationType> identificationTypes) {
                // Done, show the identification types to your users.
            }

            @Override
            public void failure(ApiException apiException) {

                if (apiException.getStatus() == 404) {
                    // No identification type for this country. Not necessary to ask for it.
                } else {
                    //TODO: Manage API Failure
                }
            }
        });
    }



}
