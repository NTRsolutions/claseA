package com.apreciasoft.mobile.remisclasea.Fracments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apreciasoft.mobile.remisclasea.Adapter.AdapterRows;
import com.apreciasoft.mobile.remisclasea.Entity.DriverCurrentAcountEntity;
import com.apreciasoft.mobile.remisclasea.Entity.LiquidationEntity;
import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;
import com.apreciasoft.mobile.remisclasea.R;
import com.apreciasoft.mobile.remisclasea.Services.ServicesDriver;
import com.apreciasoft.mobile.remisclasea.Util.GlovalVar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorge gutierrez on 13/04/2017.
 */

public class AcountDriver extends Fragment{


    ServicesDriver apiService = null;
    View myView;
    DriverCurrentAcountEntity currentAcountDriver = null;
    AdapterRows adapter = null;
    RecyclerView rv = null;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_new_acount_adriver,container,false);

         myView = inflater.inflate(R.layout.fragment_new_acount_adriver,container,false);
        return myView;
    }



    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.apiService = HttpConexion.getUri().create(ServicesDriver.class);
        serviceAllTravel();

    }


    public void  loadTable()
    {



        rv = (RecyclerView) myView.findViewById(R.id.recycler_view_list);
        rv.setHasFixedSize(true);

         List<LiquidationEntity> LIST_NEW = new ArrayList<LiquidationEntity>();
        if(currentAcountDriver.getLiquidation() != null ) {
            LIST_NEW.addAll(currentAcountDriver.getLiquidation());
        }


        if(currentAcountDriver.getAdvance() != null) {
             LIST_NEW.addAll(currentAcountDriver.getAdvance());
         }

        if(currentAcountDriver.getPay() != null){
            LIST_NEW.addAll(currentAcountDriver.getPay());
        }

        Log.d("LIST_NEW", String.valueOf(LIST_NEW));
        adapter = new AdapterRows(LIST_NEW);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        /*


        TableEntity tabla = new TableEntity(this.getActivity(), (TableLayout) myView.findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);


        // LIQUIDACIONES //

        if(currentAcountDriver.getLiquidation() != null) {
            for (int i = 0; i < currentAcountDriver.getLiquidation().size(); i++) {
                ArrayList<String> elementos = new ArrayList<String>();

                elementos.add(currentAcountDriver.getLiquidation().get(i).dateTrasaction);


                if (currentAcountDriver.getLiquidation().get(i).getIdTipeOperation() == 1) {
                    elementos.add("Liquidacion:" + currentAcountDriver.getLiquidation().get(i).getCodeCardx());
                    elementos.add("$" + currentAcountDriver.getLiquidation().get(i).totalLiquidation);
                    elementos.add("$0");
                } else if (currentAcountDriver.getLiquidation().get(i).getIdTipeOperation() == 2) {
                    elementos.add("Pago de Liq:" + currentAcountDriver.getLiquidation().get(i).getCodeCardx());
                    elementos.add("$0");
                    elementos.add("$" + currentAcountDriver.getLiquidation().get(i).totalLiquidation);

                }

                tabla.agregarFilaTabla(elementos);
            }
        }

        // ADELANTOS //
        if( currentAcountDriver.getAdvance() !=  null) {
            for (int i = 0; i < currentAcountDriver.getAdvance().size(); i++) {
                ArrayList<String> elementos = new ArrayList<String>();

                elementos.add(currentAcountDriver.getAdvance().get(i).dateTrasaction);

                elementos.add("Adelanto:" + currentAcountDriver.getAdvance().get(i).getIdAdvanceDiver());
                elementos.add("$0");
                elementos.add("$" + currentAcountDriver.getAdvance().get(i).getAmountAdvance());


                tabla.agregarFilaTabla(elementos);
            }
        }*/
    }



    public void serviceAllTravel() {


        GlovalVar gloval = ((GlovalVar)getActivity().getApplicationContext());
        Call<DriverCurrentAcountEntity> call = this.apiService.listLiquidationDriver(gloval.getGv_id_driver());

        // Log.d("***",call.request().body().toString());

        call.enqueue(new Callback<DriverCurrentAcountEntity>() {
            @Override
            public void onResponse(Call<DriverCurrentAcountEntity> call, Response<DriverCurrentAcountEntity> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    currentAcountDriver = (DriverCurrentAcountEntity) response.body();



                    System.out.println(currentAcountDriver.getTotal().getIngreso());

                    TextView txt_totalpagar = (TextView) myView.findViewById(R.id.txt_totalpagar);
                    TextView txt_totalfavor  = (TextView) myView.findViewById(R.id.txt_totalfavor);
                    TextView saldo = (TextView) myView.findViewById(R.id.txt_saldo);
                    TextView pay = (TextView) myView.findViewById(R.id.txt_total_pay);


                    txt_totalpagar.setText("Total Monto A Favor: "+currentAcountDriver.getTotal().getIngreso());
                    txt_totalfavor.setText("Total Monto A Pagar: "+currentAcountDriver.getTotal().getEgreso());


                    double saldoValue =
                            Double.parseDouble(currentAcountDriver.getTotal().getIngreso())-
                                    Double.parseDouble(currentAcountDriver.getTotal().getEgreso());
                    saldo.setText("Saldo Cuenta: "+saldoValue);

                    pay.setText("Total Pagos: "+currentAcountDriver.getTotal().getIngresoProcess());

                    if(saldoValue >= 0)
                    {
                        saldo.setTextColor(Color.GREEN);
                    }else
                    {
                        saldo.setTextColor(Color.RED);
                    }





                    if(currentAcountDriver.getLiquidation() != null  || currentAcountDriver.getAdvance() != null || currentAcountDriver.getPay() != null )
                    {
                        loadTable();
                    }



                    //
                } else if (response.code() == 404) {

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("ERROR" + "(" + response.code() + ")");
                    alertDialog.setMessage(response.errorBody().source().toString());
                    Log.w("***", response.errorBody().source().toString());


                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }


            }

            @Override
            public void onFailure(Call<DriverCurrentAcountEntity> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }
}
