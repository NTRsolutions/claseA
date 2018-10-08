package com.apreciasoft.mobile.remisclasea.Fracments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.apreciasoft.mobile.remisclasea.Activity.HomeActivity;
import com.apreciasoft.mobile.remisclasea.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadopago.constants.Sites;
import com.mercadopago.core.MercadoPagoCheckout;
import com.mercadopago.model.Item;
import com.mercadopago.model.PaymentData;
import com.mercadopago.preferences.CheckoutPreference;
import com.mercadopago.util.JsonUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by usario on 1/7/2017.
 */

public class PaymentCreditCar extends AppCompatActivity {



    // Set the supported payment method types
    protected List<String> mSupportedPaymentTypes;
    private WebView mWebview ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_credit_car);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Pago Con Tarjeta");


        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview .loadUrl(HomeActivity.mp_jsonPaymentCard);
        setContentView(mWebview );




                        /*

        mSupportedPaymentTypes =  new ArrayList<String>();
        mSupportedPaymentTypes.add("credit_card");
        mSupportedPaymentTypes.add("debit_card");
        mSupportedPaymentTypes.add("prepaid_card");

        final TextView txt_mount2 = (TextView) findViewById(R.id.txt_mount2);

        txt_mount2.setText("$"+Double.toString(round(HomeActivity.totalFinal,2)));
                                    */



    }



    private void startMercadoPagoCheckout(CheckoutPreference checkoutPreference) {
        new MercadoPagoCheckout.Builder()
                .setActivity(this)
                .setPublicKey(String.valueOf(HomeActivity.PARAM_69))
                .setCheckoutPreference(checkoutPreference)
                .startForPaymentData();
    }

    // Método ejecutado al hacer clic en el botón
    public void submit(View view) {



        CheckoutPreference checkoutPreference = new CheckoutPreference.Builder()
                .addItem(new Item("PAGO REMIS ASREMIS", new BigDecimal(HomeActivity.totalFinal)))
                .setSite(Sites.ARGENTINA)
                .build();
        startMercadoPagoCheckout(checkoutPreference);




    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {

        if (requestCode == MercadoPagoCheckout.CHECKOUT_REQUEST_CODE) {
            if (resultCode == MercadoPagoCheckout.PAYMENT_DATA_RESULT_CODE) {
                PaymentData paymentData = JsonUtil.getInstance().fromJson(data.getStringExtra("paymentData"), PaymentData.class);
                //Done!
               // if(paymentData.getStatus().contains("approved")){




                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    Log.d("paymentData",gson.toJson(paymentData));

                    HomeActivity.mp_jsonPaymentCard = gson.toJson(paymentData);
                    HomeActivity.mp_paymentMethodId = paymentData.getPaymentMethod().getId();
                    HomeActivity.mp_paymentTypeId = paymentData.getPaymentMethod().getPaymentTypeId();
                    HomeActivity.mp_paymentstatus = paymentData.getTransactionAmount().toEngineeringString();
                    HomeActivity.mp_cardIssuerId = paymentData.getIssuer() == null ? null : paymentData.getIssuer().getId();
                    HomeActivity.mp_installment  = paymentData.getPayerCost() == null ? null : paymentData.getPayerCost().getInstallments();
                    HomeActivity.mp_cardToken = paymentData.getToken() == null ? null : paymentData.getToken().getId();
                    HomeActivity.mp_campaignId = paymentData.getDiscount() == null ? null : paymentData.getDiscount().getId();
                    HomeActivity._PAYCREDITCAR_OK = true;

                    Log.d("mp_cardToken",HomeActivity.mp_cardToken);


                    this.finish();




               // }
            } else if (resultCode == RESULT_CANCELED) {
                if (data != null && data.getStringExtra("mercadoPagoError") != null) {
                    //Resolve error in checkout
                   Log.d("ERROR"," PROCESANDO PAGO");
                } else {
                    //Resolve canceled checkout
                }
            }
        }


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Si tu operacion fue exitosa presiona continuar")
                        .setCancelable(false)
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HomeActivity._PAYCREDITCAR_OK = true;
                                finish();

                            }
                        })
                        .setNegativeButton("Intentar con otro metodo de Pago", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                HomeActivity._PAYCREDITCAR_OK = false;
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {



    }


}


