package com.apreciasoft.admin.remisclasea.Dialog;

import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apreciasoft.admin.remisclasea.Activity.HomeActivity;
import com.apreciasoft.admin.remisclasea.Adapter.CustomExpandableListAdapter;
import com.apreciasoft.admin.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.admin.remisclasea.R;
import com.apreciasoft.admin.remisclasea.Util.ExpandableListDataPump;
import com.apreciasoft.admin.remisclasea.Util.GlovalVar;
import com.apreciasoft.admin.remisclasea.Util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by usario on 26/4/2017.
 */

public class TravelDialog extends DialogFragment {


    public static InfoTravelEntity currentTravel;
    public GlovalVar gloval;

    ExpandableListView expandableListView,expandableListView2,expandableListView3;
    ExpandableListAdapter expandableListAdapter,expandableListAdapter2,expandableListAdapter3;
    List<String> expandableListTitle,expandableListTitle2,expandableListTitle3;
    HashMap<String, List<String>> expandableListDetail,expandableListDetail2,expandableListDetail3;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.travel_dialog, container, false);
        //Quitamos barra de titulo de la aplicacion
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        // variable global //
        gloval = ((GlovalVar)getActivity().getApplicationContext());

        currentTravel = gloval.getGv_travel_current();

        final TextView title1 = (TextView) rootView.findViewById(R.id.txt_title);
        final TextView title2 = (TextView) rootView.findViewById(R.id.txt_title2);
        final TextView telefono = (TextView) rootView.findViewById(R.id.txt_calling_info);

        final TextView titleOrigin = (TextView) rootView.findViewById(R.id.txt_origin);
        final TextView titleDestination = (TextView) rootView.findViewById(R.id.txt_detination);

        final TextView txt_monto = (TextView) rootView.findViewById(R.id.txt_monto);
        final TextView txt_distancia = (TextView) rootView.findViewById(R.id.txt_distancia);

        final TextView txt_date_info = (TextView) rootView.findViewById(R.id.txt_date_info);


        final TextView txt_lote = (TextView) rootView.findViewById(R.id.txt_lote_dialog);
        final TextView txt_flete = (TextView) rootView.findViewById(R.id.txt_flete_dialog);

        final TextView txt_piso_dialog = (TextView) rootView.findViewById(R.id.txt_piso_dialog);
        final TextView txt_dpto_dialog = (TextView) rootView.findViewById(R.id.txt_dpto_dialog);

        final TextView txt_observation = (TextView) rootView.findViewById(R.id.txt_observation);//is multi destination


        int param49 = Integer.parseInt(gloval.getGv_param().get(48).getValue());// SE PUEDE VER TELEFONO DE PASAJEROS

        if(currentTravel.getPhoneNumber() !=null && param49 == 1) {
        telefono.setText(currentTravel.getPhoneNumber());
        }

        title1.setText(currentTravel.getCodTravel());
        title2.setText(currentTravel.getClient());
        titleOrigin.setText(currentTravel.getNameOrigin());
        titleDestination.setText(currentTravel.getNameDestination());



        int param25 = Integer.parseInt(gloval.getGv_param().get(25).getValue());
        if(param25 == 1){
            txt_monto.setText(currentTravel.getAmountCalculate());
        }
        else {
            txt_monto.setText("0.0");
        }


        txt_distancia.setText(currentTravel.getDistanceLabel());

        txt_date_info.setText(currentTravel.getMdate());

        txt_lote.setText(currentTravel.getLot());
        txt_flete.setText(String.valueOf(currentTravel.getIsFleetTravelAssistance()));



        txt_piso_dialog.setText(currentTravel.getFLOOR());
        txt_dpto_dialog.setText(currentTravel.getDepartment());


        txt_observation.setText(currentTravel.getObservationFromDriver());

        final Button btnRefut = (Button) rootView.findViewById(R.id.btn_refut);

        btnRefut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // RECHAZAR VIAJE
                ((HomeActivity)getActivity()).cancelTravel(currentTravel.getIdTravel());
            }
        });

        if(HomeActivity.PARAM_66 == 1){
            btnRefut.setEnabled(true);
        }else {
            btnRefut.setEnabled(false);
        }


        final Button btnAcep = (Button) rootView.findViewById(R.id.btn_acept);

        btnAcep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // ACEPTAR VIAJE
                ((HomeActivity)getActivity()).verifickTravelCancel(currentTravel.getIdTravel());
            }
        });


        /*
        PASAJEROS
         */
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);

        List<String> subItem = new ArrayList<String>();
        if(currentTravel.getPassenger1() != ""){subItem.add(currentTravel.getPassenger1());}
        if(currentTravel.getPassenger2() != ""){ subItem.add(currentTravel.getPassenger2());}
        if(currentTravel.getPassenger3() != ""){subItem.add(currentTravel.getPassenger3());}
        if(currentTravel.getPassenger4() != ""){subItem.add(currentTravel.getPassenger4());}



        expandableListDetail = ExpandableListDataPump.getData("Pasajeros",subItem);
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Utils.setListViewHeight(parent, groupPosition);
                return false;
            }
        });

      //****************//

           /*
        MULTI DESTINO
         */

        expandableListView2 = (ExpandableListView) rootView.findViewById(R.id.expandableListView2);

        List<String> subItem2 = new ArrayList<String>();
        if(currentTravel.getMultiDestination() != ""){subItem2.add(currentTravel.getMultiDestination());}


        expandableListDetail2 = ExpandableListDataPump.getData("Multi Destino",subItem2);
        expandableListTitle2 = new ArrayList<String>(expandableListDetail2.keySet());
        expandableListAdapter2 = new CustomExpandableListAdapter(getActivity(), expandableListTitle2, expandableListDetail2);
        expandableListView2.setAdapter(expandableListAdapter2);


        expandableListView2.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Utils.setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        //****************//

         /*
        MULTI ORIGEN
         */

        expandableListView3 = (ExpandableListView) rootView.findViewById(R.id.expandableListView3);

        List<String> subItem3 = new ArrayList<String>();
        if(currentTravel.getOriginMultipleDesc1() != ""){subItem3.add(currentTravel.getOriginMultipleDesc1());}
        if(currentTravel.getOriginMultipleDesc2() != ""){subItem3.add(currentTravel.getOriginMultipleDesc2());}
        if(currentTravel.getOriginMultipleDesc3() != ""){subItem3.add(currentTravel.getOriginMultipleDesc3());}
        if(currentTravel.getOriginMultipleDesc4() != ""){subItem3.add(currentTravel.getOriginMultipleDesc4());}


        expandableListDetail3 = ExpandableListDataPump.getData("Multi Origen",subItem3);
        expandableListTitle3 = new ArrayList<String>(expandableListDetail3.keySet());
        expandableListAdapter3 = new CustomExpandableListAdapter(getActivity(), expandableListTitle3, expandableListDetail3);
        expandableListView3.setAdapter(expandableListAdapter3);


        expandableListView3.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Utils.setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        //****************//



        return rootView;
    }




}
