package com.apreciasoft.admin.remisclasea.Fracments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apreciasoft.admin.remisclasea.Adapter.MyAdapter;
import com.apreciasoft.admin.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.admin.remisclasea.Http.HttpConexion;
import com.apreciasoft.admin.remisclasea.R;
import com.apreciasoft.admin.remisclasea.Services.ServicesDriver;
import com.apreciasoft.admin.remisclasea.Util.GlovalVar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 19/1/2017.
 */

public class HistoryTravelDriver extends Fragment {

    public static GlovalVar gloval;
    ServicesDriver apiService = null;
    View myView;
    List<InfoTravelEntity> list = null;
    MyAdapter adapter = null;
    RecyclerView rv = null;

    public int ver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // myView = inflater.inflate(R.layout.history_travel_driver,container,false);
        //return myView;
        this.apiService = HttpConexion.getUri().create(ServicesDriver.class);
        gloval = ((GlovalVar)getActivity().getApplicationContext());


            if(gloval.getGv_id_profile() == 3){
                serviceAllTravel();
            }else {
            serviceAllTravelClient();
            }



        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.history_travel_driver, container, false);


        return myView;
    }

    // this is just for demonstration, not real code!
    private void refreshContent(){

        rv = (RecyclerView) myView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);



        adapter = new MyAdapter(list);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

    }

    public void serviceAllTravel() {


        GlovalVar gloval = ((GlovalVar)getActivity().getApplicationContext());
        Call<List<InfoTravelEntity>> call = this.apiService.getAllTravel(gloval.getGv_id_driver());

        // Log.d("***",call.request().body().toString());

        call.enqueue(new Callback<List<InfoTravelEntity>>() {
            @Override
            public void onResponse(Call<List<InfoTravelEntity>> call, Response<List<InfoTravelEntity>> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                   list = (List<InfoTravelEntity>) response.body();

                    refreshContent();

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
            public void onFailure(Call<List<InfoTravelEntity>> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }

    public void serviceAllTravelClient() {

        GlovalVar gloval = ((GlovalVar)getActivity().getApplicationContext());
        Call<List<InfoTravelEntity>> call = this.apiService.getAllTravelClient(gloval.getGv_user_id());

        // Log.d("***",call.request().body().toString());

        call.enqueue(new Callback<List<InfoTravelEntity>>() {
            @Override
            public void onResponse(Call<List<InfoTravelEntity>> call, Response<List<InfoTravelEntity>> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));

                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    list = (List<InfoTravelEntity>) response.body();

                    //Toast.makeText(getActivity(), "trae datos", Toast.LENGTH_SHORT).show();
                    refreshContent();

                    //
                } else if (response.code() == 404) {

                    //Toast.makeText(getActivity(), "No tiene registros", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<List<InfoTravelEntity>> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }


}
