package com.apreciasoft.admin.remisclasea.Fracments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.apreciasoft.admin.remisclasea.Adapter.ReservationsAdapter;
import com.apreciasoft.admin.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.admin.remisclasea.Http.HttpConexion;
import com.apreciasoft.admin.remisclasea.R;
import com.apreciasoft.admin.remisclasea.Services.ServicesTravel;
import com.apreciasoft.admin.remisclasea.Util.GlovalVar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JORGE GUTTIERREZ on 25/4/2017.
 */

public class ReservationsFrangment extends Fragment  {


    public ProgressDialog loading;
    public static final int INFO_ACTIVITY = 1;
    public static final int RESULT_OK = 2;
    ServicesTravel apiService = null;
    View myView;
    ReservationsAdapter adapter = null;
    RecyclerView rv = null;
    List<InfoTravelEntity> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.apiService = HttpConexion.getUri().create(ServicesTravel.class);

        serviceAllNotification();

        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.notification_reservations_driver, container, false);
        return myView;
    }


    private  void refreshContent(){

        rv = (RecyclerView) myView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        adapter = new ReservationsAdapter(list, ReservationsFrangment.this,myView.getContext(),
                new ReservationsAdapter.OnItemClickListener() {
            @Override public void onItemClick(InfoTravelEntity item) {
              //  Toast.makeText(myView.getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( myView.getContext(), InfoDetailTravelAc.class);
                intent.putExtra("TRAVEL",item);
                startActivityForResult(intent, INFO_ACTIVITY);

            }
        });
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

    }

    public void serviceAllNotification() {


        loading = ProgressDialog.show(getActivity(), "Buscado reservas", "Espere unos Segundos...", true, false);


        final GlovalVar gloval = ((GlovalVar)getActivity().getApplicationContext());

        Call<List<InfoTravelEntity>> call = null;
        if(gloval.getGv_id_profile() ==  3){
            call =  this.apiService.getReservations(gloval.getGv_id_driver(),0,3);
        }
        else if(gloval.getGv_id_profile() ==  4 || gloval.getGv_id_profile() ==  5) {

            call =  this.apiService.getReservations(0,gloval.getGv_user_id(),gloval.getGv_id_profile());

        } else if (gloval.getGv_id_profile() ==  2) {
            call =  this.apiService.getReservations(0,gloval.getGv_id_cliet(),gloval.getGv_id_profile());

        }


        call.enqueue(new Callback<List<InfoTravelEntity>>() {
            @Override
            public void onResponse(Call<List<InfoTravelEntity>> call, Response<List<InfoTravelEntity>> response) {

                loading.dismiss();
                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    list = (List<InfoTravelEntity>) response.body();
                    gloval.setGv_lisReservations(list);

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
                loading.dismiss();

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

               this.serviceAllNotification();

    }



}
