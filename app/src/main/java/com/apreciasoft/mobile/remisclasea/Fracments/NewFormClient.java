package com.apreciasoft.mobile.remisclasea.Fracments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.apreciasoft.mobile.remisclasea.Entity.ClientEntityAdd;
import com.apreciasoft.mobile.remisclasea.Entity.Company;
import com.apreciasoft.mobile.remisclasea.Entity.RequetClient;
import com.apreciasoft.mobile.remisclasea.Entity.acountCompany;
import com.apreciasoft.mobile.remisclasea.Entity.costCenterCompany;
import com.apreciasoft.mobile.remisclasea.Entity.resp;
import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;
import com.apreciasoft.mobile.remisclasea.R;
import com.apreciasoft.mobile.remisclasea.Services.ServicesDriver;
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
 * Created by usario on 2/8/2017.
 */

public class NewFormClient extends AppCompatActivity implements VerticalStepperForm,AdapterView.OnItemSelectedListener {



    private VerticalStepperFormLayout verticalStepperForm;
    public EditText fisrtName;
    public  EditText latName;
    public  EditText mail;
    public  EditText pass;
    public  EditText phone;

    public  int idCompanyKf;
    public  int idCostCenter;
    public  int idCompanyAcount;
    public Spinner spinner0;
    public Spinner spinner1;
    public Spinner spinner2;

    public List<acountCompany> listAcountCompany = null;
    public  List<costCenterCompany>   listCostCenter  = null;
    public  List<Company>   listCompany  = null;

    private static String[] COSTCENTER = new String[0];
    private static String[] COMPANY = new String[0];
    private static String[] ACOUNT = new String[0];



    ServicesDriver apiDriver = null;


    public ProgressDialog loading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_steps_client);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nuevo Cliente");


        String[] mySteps = {"Nombre ","Apellido","Telefono","Email", "Contraseña","CUENTA/C.COSTO"};
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


        spinner0 = (Spinner) findViewById(R.id.spinner_company);
        spinner1 = (Spinner) findViewById(R.id.spinner_cuenta);
        spinner2 = (Spinner) findViewById(R.id.spinner_ccosto);


    }


    public void _setSpinersCostCenter() {


        //*************************************//
        //***********    C.Costo    ************//

        List<String> list2 = new ArrayList<String>();

        if(listCostCenter!= null) {

            COSTCENTER = new String[listCostCenter.size()];


            for (int i = 0; i < listCostCenter.size(); i++) {
                list2.add("C.Costo: " + listCostCenter.get(i).getCostCenter());
            }
            list2.toArray(COSTCENTER);

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


    public void serviceValidatorDomaint() {



        if (this.apiDriver == null) { this.apiDriver = HttpConexion.getUri().create(ServicesDriver.class); }



        String mailTxt = mail.getText().toString();

        if(mailTxt.contains("@")) {
            //String[] parts = mailTxt.split("@");

            try
            {

                Call<List<Company>> call = this.apiDriver.validatorDomaint(mailTxt);

                call.enqueue(new Callback<List<Company>>() {
                    @Override
                    public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {

                        Log.d("Call request", call.request().toString());
                        Log.d("Call request header", call.request().headers().toString());
                        Log.d("Response raw header", response.headers().toString());
                        Log.d("Response raw", String.valueOf(response.raw().body()));
                        Log.d("Response code", String.valueOf(response.code()));


                        if (response.code() == 200) {

                            //the response-body is already parseable to your ResponseBody object
                            List<Company> r = (List<Company>) response.body();


                            listCompany = r;
                            _setSpinersCompany();
                        } else if (response.code() == 404) {

                           /* Toast.makeText(getBaseContext(),
                                    "No Exiten , Cuentas de este dominio", Toast.LENGTH_SHORT)
                                    .show();*/

                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(NewFormClient.this).create();
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
                    public void onFailure(Call<List<Company>> call, Throwable t) {


                        Log.d("ERROR", t.getMessage());

                    }
                });

            }catch (Exception E)
            {

            }


        }

    }


    public void serviCecostCenterByidAcount() {




        if (this.apiDriver == null) { this.apiDriver = HttpConexion.getUri().create(ServicesDriver.class); }
        Call<List<costCenterCompany>> call = this.apiDriver.costCenterByidAcount(idCompanyAcount);

        call.enqueue(new Callback<List<costCenterCompany>>() {
            @Override
            public void onResponse(Call<List<costCenterCompany>> call, Response<List<costCenterCompany>> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    List<costCenterCompany> r  = (List<costCenterCompany>) response.body();
                    listCostCenter = r;
                    _setSpinersCostCenter();
                } else if (response.code() == 404) {

                    Toast.makeText(getBaseContext(),
                            "No Exiten , Centros de Costos de esta cuenta", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(NewFormClient.this).create();
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
            public void onFailure(Call<List<costCenterCompany>> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }




    public void serviceGetAcountByidCompany() {




        if (this.apiDriver == null) { this.apiDriver = HttpConexion.getUri().create(ServicesDriver.class); }
        Call<List<acountCompany>> call = this.apiDriver.getAcountByidCompany(idCompanyKf);

        call.enqueue(new Callback<List<acountCompany>>() {
            @Override
            public void onResponse(Call<List<acountCompany>> call, Response<List<acountCompany>> response) {

                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));


                if (response.code() == 200) {

                    //the response-body is already parseable to your ResponseBody object
                    List<acountCompany> r  = (List<acountCompany>) response.body();
                    listAcountCompany = r;
                    _setSpinersAcaunt();
                } else if (response.code() == 404) {

                    Toast.makeText(getBaseContext(),
                            "No Exiten , Centros de Costos de esta cuenta", Toast.LENGTH_SHORT)
                            .show();

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(NewFormClient.this).create();
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
            public void onFailure(Call<List<acountCompany>> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }



    public void _setSpinersAcaunt() {


        //*************************************//
        //***********    CUENTA    ************//

        List<String> list2 = new ArrayList<String>();

        if(listAcountCompany!= null) {

            ACOUNT = new String[listAcountCompany.size()];


            for (int i = 0; i < listAcountCompany.size(); i++) {
                list2.add("Cuenta: " + listAcountCompany.get(i).getNrAcount());
            }
            list2.toArray(ACOUNT);

            // Spinner click listener
            spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
            // Drp down layout style - list view with radio button
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner1.setAdapter(dataAdapter1);
            //************************************//
        }

    }


    public void _setSpinersCompany() {


        //**************************************//
        //***********    EMPRESA    ************//

        List<String> list2 = new ArrayList<String>();

        if(listCompany!= null) {

            COMPANY = new String[listCompany.size()];


            for (int i = 0; i < listCompany.size(); i++) {
                list2.add(listCompany.get(i).getNameClientCompany());
            }
            list2.toArray(COMPANY);

            // Spinner click listener
            spinner0.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
            // Drp down layout style - list view with radio button
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner0.setAdapter(dataAdapter1);
            //************************************//
        }

    }



    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createfisrtNameStep();
                break;
            case 1:
                view = createlatNameStep();
                break;
            case 2:
                view = createlatPhoneStep();
                break;
            case 3:
                view = createmailStep();
                break;
            case 4:
                view = createpassStep();
                break;
            case 5:

                view = createViewAcountComapny();
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



    private void checkInput() {


        if (mail.length() >= 0 ) {
            verticalStepperForm.setActiveStepAsCompleted();
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            String errorMessage = "El email no puede estar Vacio";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }

    }



    private View createViewAcountComapny() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout vehicleLayoutContent = (LinearLayout) inflater.inflate(R.layout.step_cliente_acount, null, false);
        return vehicleLayoutContent;
    }


    private View createfisrtNameStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        fisrtName = new EditText(this);
        fisrtName.setSingleLine(true);
        return fisrtName;
    }

    private View createlatNameStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        latName = new EditText(this);
        latName.setSingleLine(true);
        return latName;
    }

    private View createlatPhoneStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        phone = new EditText(this);
        phone.setSingleLine(true);
        return phone;
    }

    private View createmailStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        mail = new EditText(this);
        mail.setSingleLine(true);


        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //here is your code
                serviceValidatorDomaint();

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        return mail;
    }

    private View createpassStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        pass = new EditText(this);
        pass.setSingleLine(true);
        pass.requestFocus();
        return pass;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId()) {


            case R.id.spinner_cuenta:
                this.idCompanyAcount =  listAcountCompany.get(position).getIdCompanyAcount();
                serviCecostCenterByidAcount();
                break;
            case R.id.spinner_ccosto:
                this.idCostCenter =  listCostCenter.get(position).getIdCostCenter();
                break;
            case R.id.spinner_company:
                this.idCompanyKf =  listCompany.get(position).getIdClientKf();
                serviceGetAcountByidCompany();
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void sendData() {

        safeClient();

    }


    public  void  safeClient()
    {

        if(this.apiDriver == null){this.apiDriver = HttpConexion.getUri().create(ServicesDriver.class);}

        try {
            loading = ProgressDialog.show(this, "Registrando Cliente", "Espere unos Segundos...", true, false);




            RequetClient client = new RequetClient(
                    new ClientEntityAdd(
                            fisrtName.getText().toString(),
                            latName.getText().toString(),
                            mail.getText().toString(),
                            pass.getText().toString(),
                            1,idCompanyAcount,phone.getText().toString(),idCostCenter,idCompanyKf
                    )
            );

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(client));

            Call<resp> call = this.apiDriver.addClient(client);
            Log.d("Call request", call.request().toString());
            Log.d("Call request header", call.request().headers().toString());

            call.enqueue(new Callback<resp>() {
                @Override
                public void onResponse(Call<resp> call, Response<resp> response) {

                    Log.d("Response request", call.request().toString());
                    Log.d("Response request header", call.request().headers().toString());
                    Log.d("Response raw header", response.headers().toString());
                    Log.d("Response raw", String.valueOf(response.raw().body()));
                    Log.d("Response code", String.valueOf(response.code()));


                    if (response.code() == 200) {


                        Toast.makeText(getBaseContext(),
                                "Cliente Registrado!", Toast.LENGTH_SHORT)
                                .show();

                        finish();

                    }  else if (response.code() == 404) {


                        AlertDialog alertDialog = new AlertDialog.Builder(NewFormClient.this).create();
                        alertDialog.setTitle("Cliente ya Existe");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        loading.dismiss();

                    }

                }



                public void onFailure(Call<resp> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                    loading.dismiss();
                }
            });

        } finally {
            this.apiDriver = null;

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
