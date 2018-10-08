package com.apreciasoft.mobile.remisclasea.Fracments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.apreciasoft.mobile.remisclasea.R;

/**
 * Created by usario on 19/7/2017.
 */

public class PaymentFormClient  extends Fragment {

    private View myView;
    public static final int CREDIT_CAR_ACTIVITY = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.payment_form_client, container, false);


        // ACTUALIZAR INFOR //
        Button btnSafeInfo = (Button) myView.findViewById(R.id.btn_config_card);
        btnSafeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configCard();

            }
        });


        return myView;
    }


    public  void  configCard()
    {
        Intent intent = new Intent(getActivity().getApplicationContext(), activity_card.class);
        startActivityForResult(intent, CREDIT_CAR_ACTIVITY);
    }

}
