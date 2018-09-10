package com.apreciasoft.admin.remisclasea.Fracments;

import android.app.Fragment;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.apreciasoft.admin.remisclasea.Adapter.NorificationAdapter;
import com.apreciasoft.admin.remisclasea.Entity.notification;
import com.apreciasoft.admin.remisclasea.Http.HttpConexion;
import com.apreciasoft.admin.remisclasea.R;
import com.apreciasoft.admin.remisclasea.Services.ServicesNotification;
import com.apreciasoft.admin.remisclasea.Util.GlovalVar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by usario on 25/4/2017.
 */



public class NotificationsFrangment extends Fragment  {

    public static final int INFO_ACTIVITY = 1;
    public ProgressDialog loading;

    ServicesNotification apiService = null;
    View myView;
    List<notification> list = null;
    NorificationAdapter adapter = null;
    RecyclerView rv = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.apiService = HttpConexion.getUri().create(ServicesNotification.class);

        serviceAllNotification();

        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.history_travel_driver, container, false);

        return myView;
    }




    // this is just for demonstration, not real code!
    private void refreshContent(){

        rv = (RecyclerView) myView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        adapter = new NorificationAdapter(list,NotificationsFrangment.this,myView.getContext(),
                new NorificationAdapter.OnItemClickListener() {
                    @Override public void onItemClick(notification item) {
                        //  Toast.makeText(myView.getContext(), "Item Clicked", Toast.LENGTH_LONG).show();

                        event_confirm(item.getIdNotification());

                    }
                });
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

    }

    public void serviceAllNotification() {


        final GlovalVar gloval = ((GlovalVar)getActivity().getApplicationContext());
        Call<List<notification>> call = this.apiService.getNotifications(gloval.getGv_user_id());


        loading = ProgressDialog.show(getActivity(), "Buscado Notificaciones", "Espere unos Segundos...", true, false);

        call.enqueue(new Callback<List<notification>>() {
            @Override
            public void onResponse(Call<List<notification>> call, Response<List<notification>> response) {

                loading.dismiss();

                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    list = (List<notification>) response.body();
                    gloval.setGv_listNotifications(list);

                   // Toast.makeText(getActivity().getApplicationContext(), "Notificacion Confirmada!", Toast.LENGTH_SHORT).show();
                    refreshContent();

                    //
                } else if (response.code() == 404) {

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("ERROR" + "(" + response.code() + ")");
                    alertDialog.setMessage(response.errorBody().source().toString());

                   // Toast.makeText(getActivity().getApplicationContext(), "Sin Notificaciones!", Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<List<notification>> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                loading.dismiss();

            }
        });


    }



    public void event_confirm(int IdNotification) {


        final GlovalVar gloval = ((GlovalVar)getActivity().getApplicationContext());
        Call<List<notification>> call =
                this.apiService.readNotifications(IdNotification,gloval.getGv_user_id());

        // Log.d("***",call.request().body().toString());

        call.enqueue(new Callback<List<notification>>() {
            @Override
            public void onResponse(Call<List<notification>> call, Response<List<notification>> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    list = (List<notification>) response.body();
                    gloval.setGv_listNotifications(list);
                    refreshContent();

                    //
                } else if (response.code() == 404) {
                    //the response-body is already parseable to your ResponseBody object
                    list = (List<notification>) response.body();
                    gloval.setGv_listNotifications(list);
                    refreshContent();

                    //
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
            public void onFailure(Call<List<notification>> call, Throwable t) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("ERROR");
                alertDialog.setMessage(t.getMessage());

                Log.d("**", t.getMessage());

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });


    }



    /*@Override
    public void recyclerViewListClicked(View v, int position) {

    }*/
}
