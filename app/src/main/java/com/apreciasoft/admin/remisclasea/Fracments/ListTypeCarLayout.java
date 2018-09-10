package com.apreciasoft.admin.remisclasea.Fracments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apreciasoft.admin.remisclasea.Entity.VehicleType;
import com.apreciasoft.admin.remisclasea.R;
import com.apreciasoft.admin.remisclasea.Util.GlovalVar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge gutierrez on 29/03/2017.
 */

public class ListTypeCarLayout extends DialogFragment {

    public  List<VehicleType> list =  new ArrayList<VehicleType>();
    int idType = 0;
    String nameType = "";
    public static GlovalVar gloval;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.layaud_list_type_car, container, false);

        // variable global //
        gloval = ((GlovalVar) getActivity().getApplicationContext());

         /*SETEAR CATEGORIAS */
         _setCategory();
        final RadioGroup addRadioGroup = (RadioGroup) rootView.findViewById(R.id.listRadioTipeCar);


        for (int i = 0;i<list.size(); i ++)
        {
            RadioButton radioButton = new RadioButton(this.getActivity());
            radioButton.setText(list.get(i).getVehiclenType());
            radioButton.setId(list.get(i).getIdVehicleType());//set radiobutton id and store it somewhere
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            addRadioGroup.addView(radioButton, params);
            addRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    idType = checkedId;

                    for(int i=0; i<group.getChildCount(); i++) {
                        RadioButton btn = (RadioButton) group.getChildAt(i);
                        if(btn.getId() == checkedId) {
                            nameType = (String) btn.getText();
                            // do something with text
                            return;
                        }
                    }
                }
            });
        }

        final Button btnLogin = (Button) rootView.findViewById(R.id.dismiss);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
              /*  try {
                   // ((HomeClientActivity)getActivity()).savePreferences(idType,nameType);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });






        getDialog().setTitle("Tipo de Vehiculo");
        return rootView;
    }


    public void _setCategory()
    {

        this.list = gloval.getGv_listvehicleType();






    }



}
