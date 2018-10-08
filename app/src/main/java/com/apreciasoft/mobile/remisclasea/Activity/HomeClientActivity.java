package com.apreciasoft.mobile.remisclasea.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.apreciasoft.mobile.remisclasea.Entity.DestinationEntity;
import com.apreciasoft.mobile.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.mobile.remisclasea.Entity.OriginEntity;
import com.apreciasoft.mobile.remisclasea.Entity.PreviewTravel;
import com.apreciasoft.mobile.remisclasea.Entity.TravelBodyEntity;
import com.apreciasoft.mobile.remisclasea.Entity.TravelEntity;
import com.apreciasoft.mobile.remisclasea.Entity.reason;
import com.apreciasoft.mobile.remisclasea.Entity.reasonEntity;
import com.apreciasoft.mobile.remisclasea.Entity.reporte;
import com.apreciasoft.mobile.remisclasea.Entity.resp;
import com.apreciasoft.mobile.remisclasea.Entity.token;
import com.apreciasoft.mobile.remisclasea.Entity.tokenFull;
import com.apreciasoft.mobile.remisclasea.Entity.valuesTravelPreview;
import com.apreciasoft.mobile.remisclasea.Fracments.HistoryTravelDriver;
import com.apreciasoft.mobile.remisclasea.Fracments.HomeClientFragment;
import com.apreciasoft.mobile.remisclasea.Fracments.ListTypeCarLayout;
import com.apreciasoft.mobile.remisclasea.Fracments.NotificationsFrangment;
import com.apreciasoft.mobile.remisclasea.Fracments.PaymentFormClient;
import com.apreciasoft.mobile.remisclasea.Fracments.ProfileClientFr;
import com.apreciasoft.mobile.remisclasea.Fracments.ReservationsFrangment;
import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;
import com.apreciasoft.mobile.remisclasea.R;
import com.apreciasoft.mobile.remisclasea.Services.ServicesLoguin;
import com.apreciasoft.mobile.remisclasea.Services.ServicesTravel;
import com.apreciasoft.mobile.remisclasea.Util.CallbackActivity;
import com.apreciasoft.mobile.remisclasea.Util.GlovalVar;
import com.apreciasoft.mobile.remisclasea.Util.GooglePlacesAutocompleteAdapter;
import com.apreciasoft.mobile.remisclasea.Util.WsTravel;
import com.crashlytics.android.Crashlytics;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorge gutierrez on 07/03/2017.
 */

public class HomeClientActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AdapterView.OnItemClickListener,View.OnClickListener ,AdapterView.OnItemSelectedListener,CallbackActivity {

    private static String ReservationName;
    ServicesTravel apiService = null;
    List<reason> list = null;

    Integer motivo = 0;

    PlaceAutocompleteFragment autocompleteFragment;
    public String TAG = "HOME_CLIENT_ACTIVITY";
    protected PowerManager.WakeLock wakelock;
    public static GlovalVar gloval;
    int isFleetTravelAssistance = 0;
    public ServicesTravel daoTravel = null;
    public static InfoTravelEntity currentTravel;
    public ServicesLoguin daoLoguin = null;
    public WsTravel ws = null;
    public ListTypeCarLayout dialogFragment = null;
    public static final int PROFILE_DRIVER_ACTIVITY = 2;
    public static  ArrayList resultList = null;
    public static  ArrayList resultListPlaceID = null;
    public List<Integer> listCatgoryId = new ArrayList<Integer>();
    public static String location = "";
    public String lat = "";
    public String lon = "";
    public Button btnrequertReser;
    public Button btnrequetTravelNow;
    public  AutoCompleteTextView autoCompView,autoCompView2;

    public static String destination = "";
    public static String latDestination = "";
    public static String lonDestination = "";

    public  RatingBar rating;
    public Spinner spinner;
    public Spinner spinner2;
    public String dateTravel= "";

    public boolean isReervation = false;

    /* GOOGLE PALCE   */
    private static final String LOG_TAG = "Google Places Autocomplete";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAIL = "/details";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyApZ1embZ2bhcI4Ir8NepmyjTfNvGvjUas";

    /*DATE*/
    private EditText fromDateEtxt;
    private EditText fromTimeEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog fromTimePickerDialog;
    private SimpleDateFormat dateFormatter;

    private static String[] VEHYCLETYPE = new String[0];

    public FloatingActionMenu materialDesignFAM;
    public FloatingActionButton floatingActionButton1, floatingActionButton2;
    public SharedPreferences.Editor editor;


    private EditText hoursAribo;
    private EditText terminal;
    private EditText airlineCompany;
    private EditText flyNumber;
    private CheckBox isFly,isFleetTravel;

    public ProgressDialog loading,loadingGloval;
    private Integer idTypeVehicle;


    public  static  double distanceTravel = 0;
    public  static  double amountStimate = 0;
    public  static  double distanceLabel = 0;


    public   NumberPicker np;

    @SuppressLint("InvalidWakeLockTag")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiverLoadTodays, new IntentFilter("update-message"));
        //LocalBroadcastManager.getInstance(this).registerReceiver(recibeNotifiacionSocket, new IntentFilter("update-loaction-driver"));


        SharedPreferences pref = getApplicationContext().getSharedPreferences(HttpConexion.instance, 0); // 0 - for private mode
        editor = pref.edit();

        //evitar que la pantalla se apague
        final PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        this.wakelock=pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();

        setContentView(R.layout.activity_client_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // variable global //
        gloval = ((GlovalVar)getApplicationContext());

        String token = FirebaseInstanceId.getInstance().getToken();
        // Log.d(TAG, token);
        enviarTokenAlServidor(token,gloval.getGv_user_id());

        btnrequertReser = (Button) findViewById(R.id.btnrequertReser);
        btnrequertReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    requestTravel();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        });

        btnrequetTravelNow = (Button) findViewById(R.id.btn_requetTravelNow);
        btnrequetTravelNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    requestTravel();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        });




        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked


                if(isReervation)
                {
                    LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
                    contentInfoReervation.setVisibility(LinearLayout.INVISIBLE);
                    isReervation = false;

                }
                else
                {
                    LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
                    contentInfoReervation.setVisibility(LinearLayout.VISIBLE);
                    isReervation = true;

                }

                materialDesignFAM.close(true);


            }
        });


        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                contetRequestTravelVisible(true);
                materialDesignFAM.close(true);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager fr =  getFragmentManager();
        fr.beginTransaction().replace(R.id.content_frame_client, new HomeClientFragment()).commit();



        // HEADER MENU //
        View header = navigationView.getHeaderView(0);
        TextView name = (TextView)header.findViewById(R.id.username);
        TextView email = (TextView)header.findViewById(R.id.email);
        name.setText(gloval.getGv_user_name());
        email.setText(gloval.getGv_user_mail());


        currentTravel = gloval.getGv_travel_current();
        controlViewTravel();

        // WEB SOCKET //
        ws = new WsTravel(this);
        ws.connectWebSocket(gloval.getGv_user_id());



        /*GOOGLE PACE*/
        autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(this, R.layout.list_item));

        autoCompView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > adapter, View view, final int position, long arg){
                // TODO Auto-generated method stub



                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        placeDetail(resultListPlaceID.get(position).toString());
                    }
                });

                thread.start();


                String str = (String) adapter.getItemAtPosition(position);

                HomeClientActivity.ReservationName = String.valueOf(str);
                Log.d(TAG,str);

            }

        });


        autoCompView2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        autoCompView2.setAdapter(new GooglePlacesAutocompleteAdapter(this, R.layout.list_item));

        autoCompView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > adapter, View view, final int position, long arg){
                // TODO Auto-generated method stub



                // this.location = String.valueOf(GooglePlacesAutocompleteAdapter.getItemByIndex(position));
                // this.location = String.valueOf(adapterView.getAdapter().getClass().getName();


                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        placeDetail(resultListPlaceID.get(position).toString());
                    }
                });

                thread.start();

                String str = (String) adapter.getItemAtPosition(position);

                HomeClientActivity.destination = str;
                Log.d(TAG,str);

            }

        });


        contetRequestTravelVisible(false);


        /*EDIT TEX DATE */
        dateFormatter = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        findViewsById();
        setDateTimeField();

        /*SETEAR CATEGORIAS */
        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        _setCategory();
        _setCategory2();
        /*------------------*/


        _setEditPlaceHolder();


        getPick(gloval.getGv_user_id());






        int PARAM_35 =  Integer.parseInt(gloval.getGv_param().get(34).getValue());// PUEDE SOLICITAR  RESERVA
        int PARAM_36 =  Integer.parseInt(gloval.getGv_param().get(35).getValue());// PUEDE SOLICITAR  VIAJES

        if(PARAM_35 != 1){

            floatingActionButton1.setEnabled(false);
        }

        if(PARAM_36 != 1){
            floatingActionButton2.setEnabled(false);
        }





        checkPermision();

        isFleetTravel = (CheckBox) findViewById(R.id.isFleetTravel);


        isFly = (CheckBox) findViewById(R.id.isFly);
        hoursAribo = (EditText) findViewById(R.id.txt_hoursAribo);
        terminal = (EditText) findViewById(R.id.txt_terminalnew);
        airlineCompany = (EditText) findViewById(R.id.txt_airlineCompany);
        flyNumber = (EditText) findViewById(R.id.txt_flyNumber);


        setTitle(R.string.app_name);
        toolbar.setSubtitle("Bienvenido!");

        this.getCurrentTravelByIdClient();


        //Set a value change listener for NumberPicker
        np = (NumberPicker) findViewById(R.id.isFleetTravelAssistance);
        np.setMinValue(0);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(true);



        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected value from picker
                isFleetTravelAssistance = newVal;
            }
        });


        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager key = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            key.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


    }

    public void checkPermision()
    {
        LocationManager locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        // Comprobamos si está disponible el proveedor GPS.
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();
            alertDialog.setTitle("GPS INACTIVO!");
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setMessage("Active el GPS para continuar!");


            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }// end if.

       /* if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            Toast.makeText(this, "This version is not Android 6 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();

        } else {

            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);

            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[] {Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);

                Toast.makeText(this, "Requesting permissions", Toast.LENGTH_LONG).show();

            }else if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();
                openCamera();

            }

        }

        return;*/
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.isFly:
                if (checked){
                    LinearLayout btnTravelNew = (LinearLayout) findViewById(R.id.content_fly);
                    btnTravelNew.setVisibility(View.VISIBLE);
                }
                else{
                    LinearLayout btnTravelNew = (LinearLayout) findViewById(R.id.content_fly);
                    btnTravelNew.setVisibility(View.GONE);
                }
                break;
            case R.id.isFleetTravel:
                if (checked){
                    LinearLayout btnTravelNew1 = (LinearLayout) findViewById(R.id.content_flete);
                    btnTravelNew1.setVisibility(View.VISIBLE);
                }
                else{
                    LinearLayout btnTravelNew2 = (LinearLayout) findViewById(R.id.content_flete);
                    btnTravelNew2.setVisibility(View.GONE);
                }

                break;

        }
    }

    public void  _setEditPlaceHolder()
    {

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint("A Donde Vas?");

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("Place: ", "Place: " + place.getName());//get place details here

                HomeClientActivity.destination = (String) place.getName();
                HomeClientActivity.latDestination = String.valueOf(place.getLatLng().latitude);
                HomeClientActivity.lonDestination = String.valueOf(place.getLatLng().longitude);

                getPriceTrave();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("Place: ", "An error occurred: " + status);
            }
        });
    }


    // CALCULAMOS EL PRECIO ESTIMADO DEL VIAJE //
    public  void getPriceTrave(){


        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }

        try {



            distanceTravel = this.checkDistanceSucces();

            PreviewTravel ptravel =   new PreviewTravel();
            ptravel.setDistance(distanceTravel);
            ptravel.setIdUser(gloval.getGv_user_id());

            valuesTravelPreview travel = new valuesTravelPreview();
            travel.setValues(ptravel);



            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(travel));

            Call<Double> call = null;
            call = this.daoTravel.amountStimate(travel);


            Log.d("Response request", call.request().toString());
            Log.d("Response request header", call.request().headers().toString());


            call.enqueue(new Callback<Double>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<Double> call, Response<Double> response) {
                    Log.d("Response raw header", response.headers().toString());
                    Log.d("Response raw", String.valueOf(response.raw().body()));
                    Log.d("Response code", String.valueOf(response.code()));

                    TextView  txtdistanceTravel= (TextView) findViewById(R.id.distanceTravel);
                    txtdistanceTravel.setText(String.valueOf(distanceTravel)+"Km");

                    TextView  txtamountEstimate= (TextView) findViewById(R.id.amountEstimate);
                    amountStimate = (Double) response.body();
                    txtamountEstimate.setText("$"+String.valueOf(amountStimate));




                }

                public void onFailure(Call<Double> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                }
            });

        } finally {
            this.daoTravel = null;
        }


    }


    public double checkDistanceSucces() {

        try {


            Location locationA = new Location(HomeClientFragment.nameLocation);
            locationA.setLatitude(HomeClientFragment.getmLastLocation().getLatitude());
            locationA.setLongitude(HomeClientFragment.getmLastLocation().getLongitude());

            Location locationB = new Location(HomeClientActivity.destination);
            locationB.setLatitude(Double.parseDouble(HomeClientActivity.latDestination));
            locationB.setLongitude(Double.parseDouble(HomeClientActivity.lonDestination));

            float distance = locationA.distanceTo(locationB) / 1000;
            Log.d("distance", String.valueOf(distance));

            return round(distance,2);

        } catch (Exception e) {

            Log.d("ERROR", e.getMessage());
            return 0;

        }


    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.txtdateReervation);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);

        fromTimeEtxt = (EditText) findViewById(R.id.txtTimeReervation);
        fromTimeEtxt.setInputType(InputType.TYPE_NULL);

    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));



        fromTimeEtxt.setOnClickListener(this);

        fromTimePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        fromTimeEtxt.setText(hourOfDay + ":" + minute);

                    }
                },newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);


    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        }else
        if(view == fromTimeEtxt) {
            fromTimePickerDialog.show();

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setInfoTravel() {

        if (currentTravel != null) {
            HomeClientFragment.setInfoTravel(currentTravel);
        } else {
            HomeClientFragment.clearInfo();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onResume() {
        super.onResume();


        if(currentTravel !=  null) {
            currentTravel = gloval.getGv_travel_current();
            // controlViewTravel();
        }
    }


    public void _setCategory2()
    {

        List<String> list = new ArrayList<String>();

        VEHYCLETYPE = new String[gloval.getGv_listvehicleType().size()];

        for (int i =0 ;i< gloval.getGv_listvehicleType().size();i++)
        {
            list.add("Tipo De Vehiculo: "+gloval.getGv_listvehicleType().get(i).getVehiclenType());
            listCatgoryId.add(gloval.getGv_listvehicleType().get(i).getIdVehicleType());
        }


        list.toArray(VEHYCLETYPE);

        spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter);

        /*MULTI AUTO COMPLETE*/
        /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_dropdown_item_1line, VEHYCLETYPE);
         AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.txtCatVehicleReervation);
         textView.setAdapter(adapter);
         textView.setOnItemClickListener(this);*/


    }


    public void _setCategory()
    {

        try {

            List<String> list = new ArrayList<String>();

            VEHYCLETYPE = new String[gloval.getGv_listvehicleType().size()];

            for (int i = 0; i < gloval.getGv_listvehicleType().size(); i++) {
                list.add("Tipo De Vehiculo: " + gloval.getGv_listvehicleType().get(i).getVehiclenType());
                listCatgoryId.add(gloval.getGv_listvehicleType().get(i).getIdVehicleType());
            }


            list.toArray(VEHYCLETYPE);


            // Spinner click listener
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

            /*MULTI AUTO COMPLETE*/
        /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_dropdown_item_1line, VEHYCLETYPE);
         AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.txtCatVehicleReervation);
         textView.setAdapter(adapter);
         textView.setOnItemClickListener(this);*/
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
        }


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        this.idTypeVehicle =  listCatgoryId.get(position);

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
/*
        String str = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();



        Log.d("lopo",String.valueOf(adapterView.getAdapter().getClass().getName()));
        Log.d("lopo",String.valueOf(resultListPlaceID.get(position).toString()));
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                placeDetail(resultListPlaceID.get(position).toString());
            }
        });

        thread.start();*/


        // if(adapterView.getAdapter().getClass().getName()
        //         == "com.apreciasoft.admin.asremis.Util.GooglePlacesAutocompleteAdapter")// GOOGLE PLACE
        //{

       /* Log.d("form", String.valueOf(view.getId()));

        switch (itemSearch) {

            case 1:
                this.location = String.valueOf(adapterView.getAdapter().getClass().getName());
                break;
            case 2:
                this.destination = String.valueOf(adapterView.getAdapter().getClass().getName());
                break;
        }*/



        //  Log.d("lopo",GooglePlacesAutocompleteAdapter.getItemByIndex(position).toString());
        // this.location = String.valueOf(GooglePlacesAutocompleteAdapter.getItemByIndex(position));
        // this.location = String.valueOf(adapterView.getAdapter().getClass().getName();
        // }


    }

    public  ArrayList<Double> placeDetail(String input) {

        ArrayList<Double> resultList1 = null;

        HttpURLConnection conn1 = null;
        StringBuilder jsonResults1 = new StringBuilder();
        try {
            StringBuilder sb1 = new StringBuilder(PLACES_API_BASE + TYPE_DETAIL + OUT_JSON);
            sb1.append("?placeid=" + URLEncoder.encode(input, "utf8"));
            sb1.append("&key=" + API_KEY);

            URL url1 = new URL(sb1.toString());
            //Log.e("url", url.toString());
            System.out.println("URL: "+url1);
            System.out.println("******************************* connexion au serveur *****************************************");

            conn1 = (HttpURLConnection) url1.openConnection();
            InputStreamReader in = new InputStreamReader(conn1.getInputStream());

            // Load the results into a StringBuilder
            int read1;
            char[] buff1 = new char[1024];
            while ((read1 = in.read(buff1)) != -1) {
                jsonResults1.append(buff1, 0, read1);
            }
        } catch (MalformedURLException e) {
            Log.d(TAG," Error processing Places API URL "+ String.valueOf(e));
            return resultList1;
        } catch (IOException e) {
            Log.d(TAG,"Error connecting to Places API" + String.valueOf(e));
            return resultList1;
        } finally {
            if (conn1 != null) {
//                conn1.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            //Log.e("creation du fichier Json", "creation du fichier Json");
            System.out.println("fabrication du Json Objet");
            JSONObject jsonObj1 = new JSONObject(jsonResults1.toString());
            //JSONArray predsJsonArray = jsonObj.getJSONArray("html_attributions");
            JSONObject result1 = jsonObj1.getJSONObject("result").getJSONObject("geometry").getJSONObject("location");
            System.out.println("la chaine Json "+result1);
            Double longitude1  = result1.getDouble("lng");
            Double latitude1 =  result1.getDouble("lat");


            this.lat = String.valueOf(latitude1);
            this.lon = String.valueOf(longitude1);
            this.location = jsonObj1.getJSONObject("result").getString("name");

            System.out.println("longitude et latitude "+ longitude1+latitude1);
            resultList1 = new ArrayList<Double>(result1.length());
            resultList1.add(result1.getDouble("lng"));
            resultList1 .add(result1.getDouble("lat"));
            System.out.println("les latitude dans le table"+resultList1);

        } catch (JSONException e) {
            ///Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList1;
    }

    public static ArrayList autocomplete(String input) {


        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            // sb.append("&components=country:gr");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.d("Error processing Places API URL", String.valueOf(e));
            return resultList;
        } catch (IOException e) {
            Log.d("Error connecting to Places API", String.valueOf(e));
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList(predsJsonArray.length());
            resultListPlaceID = new ArrayList(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
                resultListPlaceID.add(predsJsonArray.getJSONObject(i).getString("place_id"));

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    public void activeGif(boolean active,String sms)
    {
        // GifImageView GIF =   (GifImageView) findViewById(R.id.gif_auto);
        //  ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(active)
        {


            loading = new ProgressDialog(this);
            loading.setMessage(sms);
            loading.show();


            loading.setContentView(R.layout.custom_progressdialog);


            //TextView text = (TextView) loading.findViewById(R.id.smsDialog);
            //text.setText("Presiona para Cancelar!");


            loading.setCancelable(false);

            CardView car = (CardView) loading.findViewById(R.id.car_notifications_from_client);
            car.getBackground().setAlpha(200);

            Button btnCnacel = (Button) loading.findViewById(R.id.car_notifications_cancel_client);
            btnCnacel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                        activeGif(false,"");
                        serviceAllTravel();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


            });


        }
        else
        {

            if(loading != null)
            {
                loading.dismiss();
            }



            //progressBar.setIndeterminate(false);
            //  GIF.setVisibility(View.INVISIBLE);
            //btnTravelNew.setVisibility(View.VISIBLE);
        }
    }

    public void activeGifMotivos(boolean active,String sms)
    {

        if(active)
        {

            loading = new ProgressDialog(this);
            loading.setMessage(sms);
            loading.show();

            loading.setContentView(R.layout.custom_modal);

            loading.setCancelable(false);

            CardView car = (CardView) loading.findViewById(R.id.car_notifications_from_client_cancelar);
            car.getBackground().setAlpha(200);

            RadioGroup rg = (RadioGroup) loading.findViewById(R.id.radio_group);

            for(int i=0;i<list.size();i++){
                RadioButton rb=new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
                rb.setText(list.get(i).getReason().toString());
                rg.addView(rb);
            }

            if(list.size() == 0){
                Toast.makeText(getApplicationContext(), "Agencia sin Motivos de cancelacion configurados" , Toast.LENGTH_LONG).show();
            }

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int childCount = group.getChildCount();
                    for (int x = 0; x < childCount; x++) {
                        RadioButton btn = (RadioButton) group.getChildAt(x);
                        if (btn.getId() == checkedId) {
                            //Log.e("selected RadioButton->",btn.getText().toString());

                            motivo = Integer.valueOf((checkedId));
                            //Toast.makeText(getApplicationContext(), String.valueOf(checkedId) , Toast.LENGTH_LONG).show();

                        }
                    }
                }
            });

            Button btnCnacel = (Button) loading.findViewById(R.id.btn_motivo);
            btnCnacel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                        if (motivo == 0){
                            Toast.makeText(getApplicationContext(), "Debe seleccionar algun motivo" , Toast.LENGTH_LONG).show();
                        }
                        else{
                            cancelTravelByCliet();
                        }

                        //Toast.makeText(getApplicationContext(), mot.toString(), Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            });

        }
        else
        {

            if(loading != null)
            {
                loading.dismiss();
            }

        }
    }

    public void activeGifMotivosFalla(boolean active,String sms)
    {

        if(active)
        {

            loading = new ProgressDialog(this);
            loading.setMessage(sms);
            loading.show();
            loading.setContentView(R.layout.custom_modal_reporte);
            loading.setCancelable(false);

            CardView car = (CardView) loading.findViewById(R.id.car_notifications_from_client_cancelar);
            car.getBackground().setAlpha(200);

            final EditText razon = (EditText) loading.findViewById(R.id.et_motivo);
            final EditText descripcion = (EditText) loading.findViewById(R.id.et_descripcion);
            final EditText email = (EditText) loading.findViewById(R.id.et_email);

            Button btnCnacel = (Button) loading.findViewById(R.id.btn_motivo);
            btnCnacel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                        ReportarByCliet();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            });

        }
        else
        {

            if(loading != null)
            {
                loading.dismiss();
            }

        }
    }

    //Esta es la funcion para enviar el reporte de falla MUESTRAME EL ERRO
    public void ReportarByCliet()
    {

        if (this.daoLoguin == null) { this.daoLoguin = HttpConexion.getUri().create(ServicesLoguin.class); }

        try {

            int id = 0;
            String nombre = "Leandro";
            String correo = "Leandro";
            String correo2 = "Leandro";
            String razon = "Leandro";
            String company = "Leandro";
            String mensaje = "Leandro";
            int istravel = 1;

            reporte datos = new reporte(id,nombre,correo,correo2,razon,company,mensaje,istravel);

            /*esto imprime el json quue se va a amandar prueba*/
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(datos));

            Call<reporte> call = this.daoLoguin.reporteFalla(datos);

            Log.d(TAG, call.request().toString());
            Log.d(TAG, call.request().headers().toString());

            call.enqueue(new Callback<reporte>() {
                @Override
                public void onResponse(Call<reporte> call, Response<reporte> response) {

                    reporte rs = response.body();

                    Toast.makeText(getApplicationContext(), "Datos Enviados", Toast.LENGTH_SHORT).show();

                    loading.dismiss();

                }

                public void onFailure(Call<reporte> call, Throwable t) {
                    loading.dismiss();
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }


            });

        } finally {
            this.daoTravel = null;
        }

    }

    public void cancelTravelByCliet()
    {

        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }

        try {
            loadingGloval = ProgressDialog.show(HomeClientActivity.this, "Cancelar", "Espere unos Segundos...", true, false);


            motivo = motivo - 1;
            Call<Boolean> call = this.daoTravel.cancelByClient(gloval.getGv_user_id(), motivo);

            Log.d(TAG, call.request().toString());
            Log.d(TAG, call.request().headers().toString());

            call.enqueue(new Callback<Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    loadingGloval.dismiss();

                    Toast.makeText(getApplicationContext(), " VIAJE CANCELADO!", Toast.LENGTH_LONG).show();

                    // cerramo el dialog //
                    activeGifMotivos(false,"");


                    materialDesignFAM.setVisibility(View.VISIBLE);
                    materialDesignFAM.close(true);

                    LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
                    contentInfoReervation.setVisibility(LinearLayout.INVISIBLE);

                    isReervation =  false;
                    contetRequestTravelVisible(false);

                    HomeClientFragment.clearInfo();

                }

                public void onFailure(Call<Boolean> call, Throwable t) {
                    loadingGloval.dismiss();

                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }


            });

        } finally {
            this.daoTravel = null;
        }

    }

    public  void  confirmCancelByClient(int idTravel)
    {

        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }


        try {
            Call<Boolean> call = this.daoTravel.confirmCancelByClient(idTravel);

            Log.d("fatal", call.request().toString());
            Log.d("fatal", call.request().headers().toString());

            call.enqueue(new Callback<Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {


                    currentTravel = null;
                    setInfoTravel();

                }

                public void onFailure(Call<Boolean> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }


            });

        } finally {
            this.daoTravel = null;
        }
    }

    public  void  confirmAceptaByClient(int idTravel)
    {

        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }


        try {
            Call<Boolean> call = this.daoTravel.confirmAceptaByClient(idTravel);

            Log.d("fatal", call.request().toString());
            Log.d("fatal", call.request().headers().toString());

            call.enqueue(new Callback<Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {



                    materialDesignFAM.setVisibility(View.VISIBLE);
                    currentTravel = null;
                    materialDesignFAM.setVisibility(View.VISIBLE);
                    gloval.setGv_travel_current(null);
                    HomeClientFragment.clearInfo();
                    activeGif(false, "");
                    setInfoTravel();


                }

                public void onFailure(Call<Boolean> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }


            });

        } finally {
            this.daoTravel = null;
        }
    }

    // Mostramos listado de detalles de el CATEGORIAS //
    public  void showModalCategory() {
        try {

            FragmentManager fm = getFragmentManager();
            dialogFragment = new ListTypeCarLayout();
            dialogFragment.show(fm, "Sample Fragment");
            // this.requestTravel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Mostramos listado de detalles de el viajes //
    public  void preRequestTravel()
    {
       /* try {

            FragmentManager fm = getFragmentManager();
             dialogFragment = new ListTypeCarLayout ();
            dialogFragment.show(fm, "Sample Fragment");
            // this.requestTravel();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    // Enviar token al servidor //
    private void enviarTokenAlServidor(String str_token,int idUser) {


        if (this.daoLoguin == null) { this.daoLoguin = HttpConexion.getUri().create(ServicesLoguin.class); }

        try {
            token T = new token();
            T.setToken(new tokenFull(str_token, idUser,gloval.getGv_id_driver(),MainActivity.version));

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            Log.d(TAG,gson.toJson(T));

            Call<Boolean> call = this.daoLoguin.token(T);

            call.enqueue(new Callback<Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                }

                public void onFailure(Call<Boolean> call, Throwable t) {


                    //Log.d(TAG,t.getMessage());
                }
            });

        } finally {
            this.daoTravel = null;
        }


    }

    @Override
    public void onBackPressed() {



        LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
        contentInfoReervation.setVisibility(LinearLayout.INVISIBLE);
        contetRequestTravelVisible(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();


        LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
        contentInfoReervation.setVisibility(LinearLayout.INVISIBLE);
        contetRequestTravelVisible(false);
        isReervation =  false;


        if (id == R.id.nav_camera) {
            controlViewTravel();
            fm.beginTransaction().replace(R.id.content_frame_client,new HomeClientFragment()).commit();

        } else if (id == R.id.nav_gallery) {

            FloatingActionMenu btnTravelNew = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            btnTravelNew.setVisibility(View.INVISIBLE);

            HistoryTravelDriver verifi = new HistoryTravelDriver();
            verifi.ver = 1;
            fm.beginTransaction().replace(R.id.content_frame_client,new HistoryTravelDriver()).commit();
            btnFlotingVisible(false);

        } else if (id == R.id.nav_slideshow) {
            btnFlotingVisible(false);

        }else if (id == R.id.nav_pay_form_client) {


            FloatingActionMenu btnTravelNew = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            btnTravelNew.setVisibility(View.INVISIBLE);
            getFragmentManager().beginTransaction().replace(R.id.content_frame_client,new PaymentFormClient()).commit();
            btnFlotingVisible(false);


        }else if (id == R.id.nav_manage) {

            FloatingActionMenu btnTravelNew = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            btnTravelNew.setVisibility(View.INVISIBLE);
            fm.beginTransaction().replace(R.id.content_frame_client,new NotificationsFrangment()).commit();
            btnFlotingVisible(false);

        } else if (id == R.id.nav_reservations) {
            FloatingActionMenu btnTravelNew = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            btnTravelNew.setVisibility(View.INVISIBLE);
            btnFlotingVisible(false);

        } else if (id == R.id.nav_send) {

            FloatingActionMenu btnTravelNew = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            btnTravelNew.setVisibility(View.INVISIBLE);
            btnFlotingVisible(false);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // CONONTRO BOTON FLOTANTE //
    public  void  btnFlotingVisible(boolean isVisible)
    {
        FloatingActionMenu btnService = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);

        if(!isVisible)
        {
            btnService.setVisibility(View.INVISIBLE);
        }else
        {
            btnService.setVisibility(View.VISIBLE);
        }

    }

    // Recibios notificacion //
    private BroadcastReceiver broadcastReceiverLoadTodays = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onReceive(Context context, Intent intent) {


            currentTravel = gloval.getGv_travel_current();

            if(gloval.getGv_id_profile() == 2 || gloval.getGv_id_profile() == 5) {
                getCurrentTravelByIdClient();
            }
            Log.d("TRAVEL","LLEGO NOTIFICCION");


        }
    };



    public  void  getCurrentTravelByIdClient()
    {

        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }


        try {

            Call<InfoTravelEntity> call = null;
            if(gloval.getGv_id_profile() == 2){
                call = this.daoTravel.getCurrentTravelByIdClient(gloval.getGv_id_cliet());

            }else  if (gloval.getGv_id_profile() == 5){
                call = this.daoTravel.getCurrentTravelByIdUserCompany(gloval.getGv_user_id());
            }



            call.enqueue(new Callback<InfoTravelEntity>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<InfoTravelEntity> call, Response<InfoTravelEntity> response) {


                    Log.d("VIAJE", response.toString());
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    System.out.println("VIAJE"+gson.toJson(response));

                    InfoTravelEntity TRAVEL = (InfoTravelEntity) response.body();
                    gloval.setGv_travel_current(TRAVEL);
                    currentTravel = gloval.getGv_travel_current();
                    controlViewTravel();


                }

                public void onFailure(Call<InfoTravelEntity> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }


            });

        } finally {
            this.daoTravel = null;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void controlViewTravel()
    {


        try {




            Log.d("VIAJE PASO", String.valueOf(gloval.getGv_travel_current()));

            //  cliaerNotificationAndoid();
            currentTravel = gloval.getGv_travel_current();

            if (currentTravel != null) {
                activeGif(false, "");

                materialDesignFAM.setVisibility(View.INVISIBLE);


                Log.d("VIAJE ESTATUS ", String.valueOf(currentTravel.getIdSatatusTravel()));


                if (currentTravel.getIdSatatusTravel() == 0
                        || currentTravel.getIdSatatusTravel() == 4
                        || currentTravel.getIdSatatusTravel() == 5
                        || currentTravel.getIdSatatusTravel() == 6
                        || currentTravel.getIdSatatusTravel() == 7) {

                    if (currentTravel.getIdSatatusTravel() == 4) {


                        AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();
                        alertDialog.setTitle("Viaje aceptado por el chofer");
                        alertDialog.setMessage("Chofer en camino..!, ");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                        alertDialog.setCancelable(false);
                        if(alertDialog != null) {
                            alertDialog.show();
                        }



                    } else if (currentTravel.getIdSatatusTravel() == 5) {
                        Toast.makeText(getApplicationContext(), "Viaje En Curso, Feliz Viaje!", Toast.LENGTH_SHORT).show();


                    } else if (currentTravel.getIdSatatusTravel() == 6) {
                        //Toast.makeText(getApplicationContext(), "Viaje Finalizado, Gracias por Preferirnos", Toast.LENGTH_SHORT).show();


                        Log.d("start", String.valueOf(currentTravel.start));

                        if(currentTravel.start == 0) {

                            this.ShowDialogStarts();
                        }else
                        {
                            currentTravel = null;
                            materialDesignFAM.setVisibility(View.VISIBLE);
                            gloval.setGv_travel_current(null);
                            HomeClientFragment.clearInfo();
                        }


                        gloval.setLocationDriverFromClient(null);




                    } else if (currentTravel.getIdSatatusTravel() == 7) {

                        final int idTravel = currentTravel.getIdTravel();

                        AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();
                        alertDialog.setTitle("Viaje Reachazado!");
                        alertDialog.setMessage("Viaje Reachazado por el Chofer!");
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        confirmCancelByClient(idTravel);
                                    }
                                });
                        alertDialog.show();

                        currentTravel = null;
                        materialDesignFAM.setVisibility(View.VISIBLE);
                        gloval.setGv_travel_current(null);
                        HomeClientFragment.clearInfo();

                    } else if (currentTravel.getIdSatatusTravel() == 0) {


                        final int idTravel = currentTravel.getIdTravel();

                        AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();



                        if(currentTravel.getIdTypeTravelKf() == 1){
                            alertDialog.setTitle("Viaje rechazado!");
                        }

                        if(currentTravel.getIdTypeTravelKf() == 2){
                            alertDialog.setTitle("Reserva rechazada!");
                        }

                        alertDialog.setMessage(currentTravel.getReason());
                        alertDialog.setCancelable(false);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        confirmCancelByClient(idTravel);
                                    }
                                });
                        alertDialog.show();


                        currentTravel = null;
                        materialDesignFAM.setVisibility(View.VISIBLE);
                        gloval.setGv_travel_current(null);
                        HomeClientFragment.clearInfo();
                        activeGif(false, "");


                    }


                    setInfoTravel();

                } else if (currentTravel.getIdSatatusTravel() == 1 ) {


                    activeGif(false, "");

                    final int idTravel = currentTravel.getIdTravel();

                    if (currentTravel.getIdTypeTravelKf() == 1) //VIAJE
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();
                        alertDialog.setTitle("Viaje Aceptado");
                        alertDialog.setCancelable(false);
                        alertDialog.setMessage("El Viaje  (" + currentTravel.getCodTravel() + ") ha sido aceptado por la Agencia!.. y se le asignara un chofer de inmediato..!, ");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                        materialDesignFAM.setVisibility(View.INVISIBLE);
                        HomeClientFragment.setInfoTravel(currentTravel);
                    } else {

                        if(currentTravel.getIsConfirTravelAppFromWeb() == 1) {

                            AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();
                            alertDialog.setTitle("Reserva aceptada");
                            alertDialog.setCancelable(false);
                            alertDialog.setMessage("Su reserva (" + currentTravel.getCodTravel() + ") ha sido aceptada con éxito ");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            confirmAceptaByClient(idTravel);
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            currentTravel = null;
                            gloval.setGv_travel_current(null);
                            HomeClientFragment.clearInfo();
                        }
                        else {
                            materialDesignFAM.setVisibility(View.VISIBLE);
                            currentTravel = null;
                            materialDesignFAM.setVisibility(View.VISIBLE);
                            gloval.setGv_travel_current(null);
                            HomeClientFragment.clearInfo();
                            activeGif(false, "");
                        }


                    }




                }
                else if (currentTravel.getIdSatatusTravel() == 2) {// viaje solicitado sin asignacion aun

                    materialDesignFAM.close(true);

                    LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
                    contentInfoReervation.setVisibility(LinearLayout.INVISIBLE);
                    isReervation = false;
                    contetRequestTravelVisible(false);
                    btnrequertReser.setEnabled(true);
                    btnrequetTravelNow.setEnabled(true);

                }

            } else {
                materialDesignFAM.setVisibility(View.VISIBLE);
                currentTravel = null;
                materialDesignFAM.setVisibility(View.VISIBLE);
                gloval.setGv_travel_current(null);
                HomeClientFragment.clearInfo();
                activeGif(false, "");
            }
        }catch (Exception e)
        {
            Log.d("ERROR",e.getMessage());
        }
    }

    /// LLAMAMOS A EL SERVICIO DE SOLICITAR REMIS //
    public void requestTravel() throws JSONException {

        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }

        try {



            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date = new Date();

            String currentDate = dateFormat.format(date).toString();

            TravelEntity travel = new TravelEntity();


            if (this.location != "" &&
                    this.lat != "" &&
                    this.lon != "") {
            } else {
                this.location = HomeClientFragment.nameLocation;
                this.lat = String.valueOf(HomeClientFragment.getmLastLocation().getLatitude());
                this.lon = String.valueOf(HomeClientFragment.getmLastLocation().getLongitude());
            }




            if (!fromTimeEtxt.getText().toString().matches("") && !fromDateEtxt.getText().toString().matches("")) {

                Log.d("** TRAVEL ** ",fromDateEtxt.getText().toString());
                this.dateTravel = fromDateEtxt.getText().toString() + " " + fromTimeEtxt.getText().toString();
            } else {
                this.dateTravel = currentDate;
            }

            int idUserCompany = 0;
            boolean isTravelComany = false;
            if (gloval.getGv_id_profile() == 5) {
                isTravelComany = true;
                idUserCompany = gloval.getGv_user_id();
            }

            if (HomeClientActivity.ReservationName != null) {
                if (HomeClientActivity.ReservationName.length() > 0) {
                    this.location = HomeClientActivity.ReservationName.toString();
                }
            }


            // INFORMACION DEL FLETE ///
            int _isFleetTravelAssistance = 0;
            boolean _isFleetTravel = false;


            if(isFleetTravel.isChecked()){
                _isFleetTravelAssistance = isFleetTravelAssistance;
                _isFleetTravel = true;
            }
            //--------------------//


            // INFORMACION DEL VUELO ///
            String _hoursAribo = "";
            String _terminal = "";
            String _airlineCompany = "";
            String _flyNumber = "";

            if(isFly.isChecked()){
                _hoursAribo = hoursAribo.getText().toString();
                _terminal = terminal.getText().toString();
                _airlineCompany = airlineCompany.getText().toString();
                _flyNumber = flyNumber.getText().toString();
            }
            //--------------------//

            travel.setTravelBody(
                    new TravelBodyEntity(
                            gloval.getGv_id_cliet(),
                            isTravelComany,
                            new OriginEntity(
                                    this.lat,
                                    this.lon,
                                    this.location),
                            new DestinationEntity(
                                    this.latDestination,
                                    this.lonDestination,
                                    this.destination
                            )
                            , this.dateTravel, idTypeVehicle, true, idUserCompany,
                            _hoursAribo,_terminal,_airlineCompany,_flyNumber,_isFleetTravelAssistance,_isFleetTravel,
                            this.distanceLabel,
                            this.distanceTravel
                    )
            );


            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(travel));


            // VALIDAMOS RESERVA O VIAJE //
            boolean validateRequired = true;
            if (isReervation) {


                if(travel.getTravelBody().getOrigin().getNameOrigin().length() > 0 &&
                        travel.getTravelBody().getmDestination().getNameDestination().length() >0 &&
                        !fromTimeEtxt.getText().toString().matches("") &&
                        !fromDateEtxt.getText().toString().matches("")){
                    validateRequired = true;
                }else {
                    validateRequired = false;
                }

            }

            Log.d("isReervation", String.valueOf(isReervation));



            if (validateRequired) {
                btnrequertReser.setEnabled(false);
                btnrequetTravelNow.setEnabled(false);

                Call<resp> call = this.daoTravel.addTravel(travel);

                loading = ProgressDialog.show(this, "Enviado Solicitud", "Espere unos Segundos...", true, false);


                call.enqueue(new Callback<resp>() {
                    @Override
                    public void onResponse(Call<resp> call, Response<resp> response) {
                        loading.dismiss();

                        Log.d("Response raw header", response.headers().toString());
                        Log.d("Response raw", String.valueOf(response.raw().body()));
                        Log.d("Response code", String.valueOf(response.code()));


                        if (response.code() == 200) {

                            resp responseBody = (resp) response.body();
                            String responseBodyString = responseBody.getResponse().toString();

                            if (isReervation) {
                                Toast.makeText(getApplicationContext(), "Reserva Solicitada!", Toast.LENGTH_SHORT).show();
                                activeGif(false, "");
                                location = "";
                                destination = "";



                            } else {
                                Toast.makeText(getApplicationContext(), "Viaje Solicitado!", Toast.LENGTH_SHORT).show();
                                activeGif(true, "Buscando Chofer...");
                            }

                            materialDesignFAM.close(true);

                            LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
                            contentInfoReervation.setVisibility(LinearLayout.INVISIBLE);
                            isReervation = false;
                            contetRequestTravelVisible(false);
                            btnrequertReser.setEnabled(true);
                            btnrequetTravelNow.setEnabled(true);

                        } else {

                            AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();
                            alertDialog.setTitle("ERROR" + "(" + response.code() + ")");
                            alertDialog.setMessage(response.errorBody().source().toString());
                            btnrequertReser.setEnabled(true);
                            btnrequetTravelNow.setEnabled(true);


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
                    public void onFailure(Call<resp> call, Throwable t) {
                        loading.dismiss();

                        Snackbar.make(findViewById(android.R.id.content),
                                "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                        btnrequertReser.setEnabled(true);
                        btnrequetTravelNow.setEnabled(true);


                    }
                });

            }

        } finally{
            this.daoTravel = null;
            fromDateEtxt.setText("");
            fromTimeEtxt.setText("");
            autoCompView.setText("");
            autoCompView2.setText("");
            autocompleteFragment.setText("");
            isFly.setChecked(false);
            isFleetTravel.setChecked(false);
            hoursAribo.setText("");
            terminal.setText("");
            airlineCompany.setText("");
            flyNumber.setText("");
            LinearLayout viewFly = (LinearLayout) findViewById(R.id.content_fly);
            viewFly.setVisibility(View.GONE);
            np.setValue(0);
            LinearLayout viewFlete = (LinearLayout) findViewById(R.id.content_flete);
            viewFlete.setVisibility(View.GONE);


        }




    }


    public  void  contetRequestTravelVisible(boolean visible)
    {

        TextView  txtdistanceTravel= (TextView) findViewById(R.id.distanceTravel);
        txtdistanceTravel.setText("0Km");

        TextView  txtamountEstimate= (TextView) findViewById(R.id.amountEstimate);
        txtamountEstimate.setText("$0");



        isReervation = false;
        if(visible)
        {
            CardView btnTravelNew = (CardView) findViewById(R.id.contetRequestTravel);
            btnTravelNew.setVisibility(View.VISIBLE);
        }
        else
        {
            CardView btnTravelNew = (CardView) findViewById(R.id.contetRequestTravel);
            btnTravelNew.setVisibility(View.INVISIBLE);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        LinearLayout contentInfoReervation = (LinearLayout) findViewById(R.id.contentInfoReervation);
        contentInfoReervation.setVisibility(LinearLayout.INVISIBLE);
        contetRequestTravelVisible(false);
        isReervation =  false;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            try {
                fn_exit();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }else if (id == R.id.action_notifications) {

            fn_gotonotification();
            btnFlotingVisible(false);

            return true;
        }
        else if (id == R.id.action_reervations) {

            fn_gotoreservation();
            btnFlotingVisible(false);

            return true;
        }
        else if (id == R.id.action_refhesh) {

            fn_refhesh();
            btnFlotingVisible(false);

            return true;
        }
        else if (id == R.id.action_profile) {
            fn_gotoprofile();
            btnFlotingVisible(false);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void  fn_refhesh() {
        getCurrentTravelByIdClient();
    }

    public  void  fn_gotoprofile()
    {

        try
        {
            FragmentManager fm = getFragmentManager();

            FloatingActionMenu btnTravelNew = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
            btnTravelNew.setVisibility(View.INVISIBLE);
            fm.beginTransaction().replace(R.id.content_frame_client,new ProfileClientFr()).commit();


        }catch (Exception e)
        {
            Log.d("e",e.getMessage());
        }
    }

    // SALIR DE ACTIVITY //
    public void fn_exit() throws IOException {


        currentTravel = null;
        gloval.setGv_logeed(false);
        new GlovalVar();

        enviarTokenAlServidor("",gloval.getGv_user_id());
        if(ws != null) {
            ws.coseWebSocket();
        }
        HomeClientFragment.timerblink.cancel();




        SharedPreferences preferences = getApplicationContext().getSharedPreferences(HttpConexion.instance, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit(); // commit changes



        // LAMAMOS A EL MAIN ACTIVITY//
        Intent main = new Intent(HomeClientActivity.this, MainActivity.class);
        startActivity(main);

        finish();



    }


    // FIRMAAAA //
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode) {

            case PROFILE_DRIVER_ACTIVITY:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    // METODO OBTENER FOTO DE CHOFER //
    public void getPick(int idUserDriver)
    {
        DowloadImg dwImg = new DowloadImg();
        dwImg.execute(HttpConexion.BASE_URL+HttpConexion.base+"/Frond/img_users/"+idUserDriver);

    }


    // interfaz de ptro metodo de la actividad
    @Override
    public void doSomething() {
        serviceAllTravel();
    }

    public class DowloadImg extends AsyncTask<String, Void, Bitmap> {



        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.i("doInBackground" , "Entra en doInBackground");
            String url = params[0]+".JPEG";
            Bitmap imagen = descargarImagen(url);
            return imagen;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            try {
                CircleImageView your_imageView = (CircleImageView) findViewById(R.id.imageViewUser);

                if (result != null) {
                    your_imageView.setImageBitmap(result);

                }
            }catch (Exception e)
            {

            }



        }

        private Bitmap descargarImagen (String imageHttpAddress){
            URL imageUrl = null;
            Bitmap imagen = null;
            try{
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                imagen = BitmapFactory.decodeStream(conn.getInputStream());
            }catch(IOException ex){
                ex.printStackTrace();
            }

            return imagen;
        }

    }


    public void cliaerNotificationAndoid()
    {
        NotificationManager notifManager= (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancelAll();
    }

    public void ShowDialogStarts()
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);

        LinearLayout linearLayout = new LinearLayout(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT

        );



        lp.gravity = Gravity.CENTER;

        rating = new RatingBar(this);
        rating.setLayoutParams(lp);

        rating.setNumStars(5);
        rating.setStepSize(1);
        //add ratingBar to linearLayout
        linearLayout.addView(rating);


        popDialog.setIcon(android.R.drawable.star_big_on);
        popDialog.setTitle("Dale una Puntuacion al Chofer!");
        popDialog.setCancelable(false);
        popDialog.setMessage("Chofer del Viaje "+currentTravel.getCodTravel()+
                " Fecha:"+currentTravel.getMdate()+
                " Origen:"+currentTravel.getNameOrigin()+
                " Destino:"+currentTravel.getNameDestination());

        //add linearLayout to dailog

        linearLayout.setGravity(Gravity.CENTER);
        popDialog.setView(linearLayout);


        // Button OK
        popDialog.setPositiveButton("Enviar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        rating.getProgress();
                        dialog.dismiss();
                        servicesCalificateDriver(rating.getProgress());
                    }

                })

                // Button Cancel
                .setNegativeButton("Omitir",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                servicesCalificateDriver(-1);
                            }
                        });

        if(popDialog != null) {
            popDialog.create();
            popDialog.show();
        }

    }


    public void servicesCalificateDriver(int start){

        if(currentTravel != null) {

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {
                Call<Boolean> call = this.daoTravel.calificateDriver(currentTravel.idTravel, start);


                Log.d("Call request", call.request().toString());

                call.enqueue(new Callback<Boolean>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {


                        Toast.makeText(getApplicationContext(), "Puntuacion Enviada!", Toast.LENGTH_LONG).show();
                        currentTravel = null;
                        materialDesignFAM.setVisibility(View.VISIBLE);
                        gloval.setGv_travel_current(null);
                        HomeClientFragment.clearInfo();
                        // HomeClientFragment.timerblink.cancel();

                    }

                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Snackbar.make(findViewById(android.R.id.content),
                                "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                    }


                });

            } finally {
                this.daoTravel = null;
            }
        }

    }


    public void serviceAllTravel() {

        this.apiService = HttpConexion.getUri().create(ServicesTravel.class);
        Call<reasonEntity> call = this.apiService.obtIdMotivo(2);

        Log.d("Call request", call.request().toString());

        call.enqueue(new Callback<reasonEntity>() {
            @Override
            public void onResponse(Call<reasonEntity> call, Response<reasonEntity> response) {

                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));

                if (response.code() == 200) {


                    list = (List<reason>) response.body().getReason();
                    activeGifMotivos(true,"");



                } else if (response.code() == 404) {

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(HomeClientActivity.this).create();
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
            public void onFailure(Call<reasonEntity> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content),
                        "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
            }
        });


    }

    //Notificacioens
    public void fn_gotonotification()
    {

        //btnFlotingVisible(false);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame_client,new NotificationsFrangment()).commit();
    }

    public void fn_gotoreservation()
    {

        //btnFlotingVisible(false);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame_client,new ReservationsFrangment()).commit();
    }
}