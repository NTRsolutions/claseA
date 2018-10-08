package com.apreciasoft.mobile.remisclasea.Fracments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.apreciasoft.mobile.remisclasea.Entity.dataAddPlusDriverEntity;
import com.apreciasoft.mobile.remisclasea.Entity.driverAdd;
import com.apreciasoft.mobile.remisclasea.Entity.fleet;
import com.apreciasoft.mobile.remisclasea.Entity.fleetType;
import com.apreciasoft.mobile.remisclasea.Entity.modelDetailEntity;
import com.apreciasoft.mobile.remisclasea.Entity.modelEntity;
import com.apreciasoft.mobile.remisclasea.Entity.responseFilterVehicle;
import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;
import com.apreciasoft.mobile.remisclasea.R;
import com.apreciasoft.mobile.remisclasea.Services.ServicesDriver;
import com.apreciasoft.mobile.remisclasea.Util.GlovalVar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by usario on 31/7/2017.
 */

public class NewFormDriver extends AppCompatActivity implements VerticalStepperForm,AdapterView.OnItemSelectedListener {



    private VerticalStepperFormLayout verticalStepperForm;
    public  EditText name;
    public  EditText nrDriver;
    public  EditText phone;
    public  EditText mail;
    public  EditText pass;


    private  int ID_MODEL;
    private  int ID_MODEL_DETAIL;
    private  int ID_CATEGORY;

    ServicesDriver apiDriver = null;

    /*VEHICULOS*/
    public Spinner spinner1;
    public Spinner spinner2;
    public Spinner spinner3;
    public  EditText txtDomain;



    public  List<modelEntity> listModel = null;
    public  List<fleetType>   listFlet  = null;
    public  List<modelDetailEntity>   listModelDetail  = null;


    private static String[] VEHYCLEMARCK = new String[0];
    private static String[] VEHYCLEMODEL = new String[0];
    private static String[] VEHYCLETYPE = new String[0];


    public ProgressDialog loading;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_steps_driver);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nuevo Chofer!");




        String[] mySteps = {"Nombre/Apellido ","Nro.Chofer","Telefono","Vehiculo" ,"Email", "Contraseña"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .hideKeyboard(false)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();

        /*SETEAR SPINERS */
        spinner1 = (Spinner) findViewById(R.id.spinner_marca);
        spinner2 = (Spinner) findViewById(R.id.spinner_modelo);
        spinner3 = (Spinner) findViewById(R.id.spinner_category);

        serviceCallFilter();
        serviceCallFilterVehicleType();



        /*------------------*/
    }



    public void serviceCallFilter() {


        if(this.apiDriver == null){this.apiDriver = HttpConexion.getUri().create(ServicesDriver.class);}
        Call<List<modelEntity>> call = this.apiDriver.filterForm();

        // Log.d("***",call.request().body().toString());

        call.enqueue(new Callback<List<modelEntity>>() {
            @Override
            public void onResponse(Call<List<modelEntity>> call, Response<List<modelEntity>> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    listModel = (List<modelEntity>) response.body();

                } else if (response.code() == 404) {

                    Toast.makeText(getBaseContext(),
                            "No Exiten , Modelos Agregado", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(NewFormDriver.this).create();
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
            public void onFailure(Call<List<modelEntity>> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }

    public void serviceCallFilterDetailModel() {


        GlovalVar gloval = ((GlovalVar) getApplicationContext());

        if (this.apiDriver == null) { this.apiDriver = HttpConexion.getUri().create(ServicesDriver.class); }
        Call<responseFilterVehicle> call = this.apiDriver.getModelDetail(ID_MODEL);

        // Log.d("***",call.request().body().toString());

        call.enqueue(new Callback<responseFilterVehicle>() {
            @Override
            public void onResponse(Call<responseFilterVehicle> call, Response<responseFilterVehicle> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    responseFilterVehicle r  = (responseFilterVehicle) response.body();


                    listModelDetail = r.getListModel();
                    _setSpinersModel();
                } else if (response.code() == 404) {

                    Toast.makeText(getBaseContext(),
                            "No Exiten , Marcas de este Modelo Agregado", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(NewFormDriver.this).create();
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
            public void onFailure(Call<responseFilterVehicle> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Log.d("form", "onitemselected");

        Log.d("form", String.valueOf(parent.getId()));
        switch (parent.getId()) {


            case R.id.spinner_marca:
                this.ID_MODEL =  listModel.get(position).getIdVehicleBrand();
                serviceCallFilterDetailModel();
                break;
            case R.id.spinner_modelo:
                this.ID_MODEL_DETAIL =  listModelDetail.get(position).getIdVehicleModel();
                break;
            case R.id.spinner_category:
                this.ID_CATEGORY =  listFlet.get(position).getIdVehicleType();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void serviceCallFilterVehicleType() {


        Call<List<fleetType>> call = this.apiDriver.filterFormfleetType();

        // Log.d("***",call.request().body().toString());

        call.enqueue(new Callback<List<fleetType>>() {
            @Override
            public void onResponse(Call<List<fleetType>> call, Response<List<fleetType>> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    listFlet = (List<fleetType>) response.body();


                    //
                } else if (response.code() == 404) {

                    Toast.makeText(getBaseContext(),
                            "No Exiten , Modelos Agregado", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(NewFormDriver.this).create();
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
            public void onFailure(Call<List<fleetType>> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }

    public void _setSpiners()
    {


        //*************************************//
        //***********    MARCAS    ************//

        List<String> list = new ArrayList<String>();

        if(listModel != null) {

            VEHYCLEMARCK = new String[listModel.size()];


            for (int i = 0; i < listModel.size(); i++) {
                list.add("Tipo De Vehiculo: " + listModel.get(i).getNameVehicleBrand());
            }
            list.toArray(VEHYCLEMARCK);

            // Spinner click listener
            spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            // Drp down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner1.setAdapter(dataAdapter);
            //************************************//


            //*************************************//
            //***********    CATEGORIAS    ************//

            List<String> list2 = new ArrayList<String>();

            VEHYCLETYPE = new String[listFlet.size()];


            for (int i = 0; i < listFlet.size(); i++) {
                list2.add("Categoria De Vehiculo: " + listFlet.get(i).getVehiclenType());
            }
            list2.toArray(VEHYCLETYPE);

            // Spinner click listener
            spinner3.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
            // Drp down layout style - list view with radio button
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner3.setAdapter(dataAdapter1);
            //************************************//

        }

    }
    public void _setSpinersModel() {


        //*************************************//
        //***********    MODELO    ************//

        List<String> list2 = new ArrayList<String>();

        if(listModelDetail!= null) {

            VEHYCLEMODEL = new String[listModelDetail.size()];


            for (int i = 0; i < listModelDetail.size(); i++) {
                list2.add("Modelos: " + listModelDetail.get(i).getNameVehicleModel());
            }
            list2.toArray(VEHYCLEMODEL);

            // Spinner click listener
            spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
            // Drp down layout style - list view with radio button
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner2.setAdapter(dataAdapter1);
            //************************************//
        }

    }


    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createNameStep();
                break;
            case 1:
                view = createNrDriverStep();
                break;
            case 2:
                view = createPhoneStep();
                break;
            case 3:
                view = createViewVehicle();

                break;
            case 4:
                view = createMailStep();
                break;
            case 5:
                view = createPassStep();
                break;

        }
        return view;
    }

    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                checkInput();
                break;
            case 1:
                checkInput();
                break;
            case 2:
                checkInput();
                break;
            case 3:
                checkInput();
                _setSpiners();
                break;
            case 4:
                checkInput();
                break;
            case 5:
                checkInput();
                break;
            case 6:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(1);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
        }
    }

    @Override
    public void sendData() {

        safeDriver();

    }


    public  void  safeDriver()
    {

        if(this.apiDriver == null){this.apiDriver = HttpConexion.getUri().create(ServicesDriver.class);}

        try {
            loading = ProgressDialog.show(this, "Registrando Chofer", "Espere unos Segundos...", true, false);


            dataAddPlusDriverEntity data =  new dataAddPlusDriverEntity(
                    new driverAdd(
                            name.getText().toString()
                            ,nrDriver.getText().toString()
                            ,mail.getText().toString()
                            ,pass.getText().toString()
                            ,phone.getText().toString(),1,1),
                    new fleet(ID_MODEL,ID_MODEL_DETAIL,ID_CATEGORY,txtDomain.getText().toString())
            );

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(data));

            Call<Integer> call = this.apiDriver.addPluDriver(data);
            Log.d("Call request", call.request().toString());
            Log.d("Call request header", call.request().headers().toString());

            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {

                    Log.d("Response request", call.request().toString());
                    Log.d("Response request header", call.request().headers().toString());
                    Log.d("Response raw header", response.headers().toString());
                    Log.d("Response raw", String.valueOf(response.raw().body()));
                    Log.d("Response code", String.valueOf(response.code()));


                    if (response.code() == 200) {


                        Toast.makeText(getBaseContext(),
                                "Chofer Registrado!", Toast.LENGTH_SHORT)
                                .show();

                        finish();

                    }  else if (response.code() == 201) {


                        AlertDialog alertDialog = new AlertDialog.Builder(NewFormDriver.this).create();
                        alertDialog.setTitle("Correo ya Registrado");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        loading.dismiss();

                   } else if(response.code()==203) {


                    AlertDialog alertDialog = new AlertDialog.Builder(NewFormDriver.this).create();
                    alertDialog.setTitle("Numero De Chofer Ya Registardo");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        loading.dismiss();


                    }else
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(NewFormDriver.this).create();
                        alertDialog.setTitle("ERROR"+response.code());
                        alertDialog.setMessage(response.message());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        loading.dismiss();
                                    }
                                });
                        alertDialog.show();
                        loading.dismiss();
                    }

            }



                public void onFailure(Call<Integer> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                    loading.dismiss();
                }
            });

        } finally {
            this.apiDriver = null;

        }

    }



    private View createViewVehicle() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout vehicleLayoutContent = (LinearLayout) inflater.inflate(R.layout.stemps_vehicle_register_driver, null, false);
        txtDomain = (EditText) vehicleLayoutContent.findViewById(R.id.driver_domain);

        return vehicleLayoutContent;
    }



    private View createNameStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        name = new EditText(this);
        name.setSingleLine(true);
        return name;
    }

    private View createNrDriverStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        nrDriver = new EditText(this);
        nrDriver.setSingleLine(true);
        return nrDriver;
    }
    private View createPhoneStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        phone = new EditText(this);
        phone.setSingleLine(true);
        return phone;
    }

    private View createMailStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        mail = new EditText(this);
        mail.setSingleLine(true);
        return mail;
    }

    private View createPassStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        pass = new EditText(this);
        pass.setSingleLine(true);
        pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        return pass;
    }


    private void checkInput() {


            if (name.length() >= 0 ) {
                verticalStepperForm.setActiveStepAsCompleted();
            } else {
                // This error message is optional (use null if you don't want to display an error message)
                String errorMessage = "El Nombre no puede estar Vacio";
                verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
            }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}