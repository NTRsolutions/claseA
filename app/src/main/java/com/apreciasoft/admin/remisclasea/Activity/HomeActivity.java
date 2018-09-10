package com.apreciasoft.admin.remisclasea.Activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;

import com.apreciasoft.admin.remisclasea.Entity.DestinationEntity;
import com.apreciasoft.admin.remisclasea.Entity.OriginEntity;
import com.apreciasoft.admin.remisclasea.Entity.TravelBodyEntity;
import com.apreciasoft.admin.remisclasea.Entity.TravelEntity;
import com.apreciasoft.admin.remisclasea.Entity.resp;
import com.github.clans.fab.FloatingActionButton;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apreciasoft.admin.remisclasea.Dialog.TravelDialog;
import com.apreciasoft.admin.remisclasea.Entity.BeneficioEntity;
import com.apreciasoft.admin.remisclasea.Entity.InfoTravelEntity;
import com.apreciasoft.admin.remisclasea.Entity.RemisSocketInfo;
import com.apreciasoft.admin.remisclasea.Entity.TraveInfoSendEntity;
import com.apreciasoft.admin.remisclasea.Entity.TravelLocationEntity;
import com.apreciasoft.admin.remisclasea.Entity.notification;
import com.apreciasoft.admin.remisclasea.Entity.paramEntity;
import com.apreciasoft.admin.remisclasea.Entity.token;
import com.apreciasoft.admin.remisclasea.Entity.tokenFull;
import com.apreciasoft.admin.remisclasea.Fracments.AcountDriver;
import com.apreciasoft.admin.remisclasea.Fracments.HistoryTravelDriver;
import com.apreciasoft.admin.remisclasea.Fracments.HomeFragment;
import com.apreciasoft.admin.remisclasea.Fracments.NotificationsFrangment;
import com.apreciasoft.admin.remisclasea.Fracments.PaymentCreditCar;
import com.apreciasoft.admin.remisclasea.Fracments.ProfileClientFr;
import com.apreciasoft.admin.remisclasea.Fracments.ProfileDriverFr;
import com.apreciasoft.admin.remisclasea.Fracments.ReservationsFrangment;
import com.apreciasoft.admin.remisclasea.Http.HttpConexion;
import com.apreciasoft.admin.remisclasea.R;
import com.apreciasoft.admin.remisclasea.Services.ServicesLoguin;
import com.apreciasoft.admin.remisclasea.Services.ServicesTravel;
import com.apreciasoft.admin.remisclasea.Util.FloatingWidgetService;
import com.apreciasoft.admin.remisclasea.Util.GlovalVar;
import com.apreciasoft.admin.remisclasea.Util.RequestHandler;
import com.apreciasoft.admin.remisclasea.Util.ServicesSleep;
import com.apreciasoft.admin.remisclasea.Util.Signature;
import com.apreciasoft.admin.remisclasea.Util.Tiempo;
import com.apreciasoft.admin.remisclasea.Util.Utils;
import com.apreciasoft.admin.remisclasea.Util.WsTravel;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final String UPLOAD_URL = HttpConexion.BASE_URL + HttpConexion.base + "/Frond/safeimg.php";
    public static final String UPLOAD_KEY = "image";
    public static   File f;
    public GlovalVar gloval;

    public ServicesTravel daoTravel = null;
    public ServicesLoguin daoLoguin = null;

    public ProgressDialog loading;
    public static double totalFinal = 0;
    public static double priceFlet = 0;

    boolean _NOCONEXION = false;

    public FloatingActionButton floatingActionButton1;


    public Timer timer, timerConexion;
    public static final int SIGNATURE_ACTIVITY = 1;
    public static final int PROFILE_DRIVER_ACTIVITY = 2;
    public static final int CREDIT_CAR_ACTIVITY = 3;

    ServicesLoguin apiService = null;

    public static InfoTravelEntity currentTravel;

    public double km_total = 0.001;
    public double m_total = 0;
    public double kilometros_total = m_total * km_total;

    public double km_vuelta = 0.001;
    public double m_vuelta = 0;
    public double kilometros_vuelta = m_vuelta * km_vuelta;

    public double km_ida = 0.001;
    public double m_ida = 0;
    public double kilometros_ida = m_vuelta * km_vuelta;
    public double  bandera = 0;


    /*
    PAGOS DE TARJETA
     */
    public static String mp_jsonPaymentCard = "";
    public static Long mp_cardIssuerId;
    public static int mp_installment;
    public static String mp_cardToken;
    public static Long mp_campaignId;
    public static String mp_paymentMethodId = "";
    public static String mp_paymentTypeId = "";
    public static String mp_paymentstatus = "";
    public static boolean _PAYCREDITCAR_OK = false;

    private static final String TAG = "AnimationStarter";
    private ObjectAnimator textColorAnim;


    public DecimalFormat df = new DecimalFormat("####0.00");
    public double amounCalculateGps;
    protected PowerManager.WakeLock wakelock;


    public boolean viewAlert = false;


    private DatabaseReference databaseReference;//defining a database reference
    public Bitmap bitmap;
    private Uri filePath;
    public String path_image;
    public WsTravel ws = null;
    public int tiempoTxt = 0;
    public int idPaymentFormKf = 0;

    public Button btnPreFinish;
    public Button btnReturn;
    public static int isRoundTrip;
    double extraTime = 0;

    public TextView textTiempo;
    public View parentLayout = null;

    int PARAM_3 = 0; // CIERRE DE VIAJE EMPRESA EN WEB
    int PARAM_67 = 0; // CIERRE DE VIAJE PARTICULARES Y EXPRESSS EN WEB
    public static int PARAM_39, PARAM_66 = 0; // ACTIVAR BOTON DE VUELTA
    public static int param25 = 0;
    public static double PARAM_1, PARAM_6 = 0;
    public static int PARAM_68 = 0; // ACTIVAR PAGO CON TARJETA
    public static String PARAM_69 = ""; // ACTIVAR PAGO CON TARJETA
    public static String PARAM_79 = ""; // ACTIVAR PAGO CON TARJETA

    public FloatingActionMenu materialDesignFAM;


    /*DIALOG*/
    public TravelDialog dialogTravel = null;


    public Button btnFinishCar;
    public Button btnFinishVo;
    public Button btnFinishCash;
    public SharedPreferences.Editor editor;
    public static SharedPreferences pref;


    public DrawerLayout drawer;


    public CircleImageView your_imageView;

    public Button btnLogin;
    public Button btnInitSplep;
    public Button btn_return;
    public Button btn_init;
    public Button btn_cancel;
    public EditText peajes_txt;


    public TextView txtMount;
    public TextView distance_txt;
    public TextView distance_return_txt;
    public TextView txtTimeslep;
    public TextView txtamounFlet;


    /*  Permission request code to draw over other apps  */
    private static final int DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE = 1222;
    private String location;
    private String lat;
    private String lon;
    private String dateTravel;


    @SuppressLint("WrongViewCast")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiverLoadTodays, new IntentFilter("update-message"));

        checkVersion(); // VERIFICAMOS LA VERSION

        pref = getApplicationContext().getSharedPreferences(HttpConexion.instance, 0); // 0 - for private mode
        editor = pref.edit();

        //evitar que la pantalla se apague
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();

        setContentView(R.layout.activity_home);
        parentLayout = findViewById(R.id.content_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // variable global //
        gloval = ((GlovalVar) getApplicationContext());

        gloval.setGv_user_id(pref.getInt("user_id", 0));
        gloval.setGv_idResourceSocket(pref.getString("is_resourceSocket", ""));
        gloval.setGv_id_cliet(pref.getInt("client_id", 0));
        gloval.setGv_id_profile(pref.getInt("profile_id", 0));
        gloval.setGv_id_driver(pref.getInt("driver_id", 0));
        gloval.setGv_user_mail(pref.getString("user_mail", ""));
        gloval.setGv_user_name(pref.getString("user_name", ""));
        gloval.setGv_base_intance(pref.getString("instance", ""));
        gloval.setGv_nr_driver(pref.getString("nrDriver", ""));


        // UI INICIALIZACION //
        this.your_imageView = findViewById(R.id.imageViewUser);
        this.drawer = findViewById(R.id.drawer_layout);
        this.btnLogin = findViewById(R.id.btn_pre_finish);
        this.btnInitSplep = findViewById(R.id.btn_iniTimeSlep);
        this.btn_return = findViewById(R.id.btn_return);
        this.btn_init = findViewById(R.id.btn_init);
        this.btn_cancel = findViewById(R.id.btn_cancel);

        this.txtMount = findViewById(R.id.txt_mount);
        this.distance_txt = findViewById(R.id.distance_txt);
        this.distance_return_txt = findViewById(R.id.distance_return_txt);
        this.txtTimeslep = findViewById(R.id.txtTimeslep);
        this.txtamounFlet = findViewById(R.id.amount_flet_txt);
        this.peajes_txt = findViewById(R.id.peajes_txt);


        //


        TypeToken<List<paramEntity>> token3 = new TypeToken<List<paramEntity>>() {};
        Gson gson = new Gson();
        List<paramEntity> listParam = gson.fromJson(pref.getString("param", ""), token3.getType());

        gloval.setGv_param(listParam);

        setParamLocal();


        // BOTON PARA PRE FINALIZAR UN VIAJE //
        btnPreFinish = findViewById(R.id.btn_pre_finish);
        btnPreFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                try {
                    showFinshTravel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // BOTON PARA PONER RETORNO DE  UN VIAJE //
        btnReturn = findViewById(R.id.btn_return);

        if (PARAM_39 == 1) {
            btnReturn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click

                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                    builder.setMessage("Presione 'Retornar'  Confirmar el  Retorno del Viaje!")
                            .setCancelable(false)
                            .setPositiveButton("Retornar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    isRoundTrip();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });
        } else {
            btnReturn.setEnabled(false);
        }


        // BOTON PARA FINALIZAR UN VIAJE TARJETA //
        btnFinishCar = findViewById(R.id.bnt_pay_car);


        // BOTON PARA FINALIZAR UN VIAJE VOUCHER //
        btnFinishVo = findViewById(R.id.btn_firm_voucher);
        btnFinishVo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                idPaymentFormKf = 5;
                finishTravelVouche();
            }
        });


        // BOTON PARA FINALIZAR UN VIAJE CASH //
        btnFinishCash = findViewById(R.id.btn_pay_cash);
        btnFinishCash.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                idPaymentFormKf = 4;
                verifickTravelFinish();
            }
        });

        // BOTON PARA INICIAR UN VIAJE //
        final Button btnInit = findViewById(R.id.btn_init);
        btnInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                verifickTravelCancelInit();
            }
        });

        // BOTON PARA CANCELAR UN VIAJE //
        final Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                cancelTravel(-1);
            }
        });


        // BOTON PARA INICIAR TIEMPO DE ESPERA DE UN VIAJE //
        final Button btnIniTimeSlep = findViewById(R.id.btn_iniTimeSlep);
        btnIniTimeSlep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                iniTimeSlep();
            }
        });



        /* KEY UP PEAJE  */
        TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                Log.d("afterTextChanged", "afterTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("beforeTextChanged", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged", "onTextChanged");
                try {
                    pay();
                } catch (Exception e) {
                    Log.d("ERRROR", e.getMessage());
                }

            }

            private boolean filterLongEnough() {
                return peajes_txt.getText().toString().trim().length() > 2;
            }
        };
        peajes_txt.addTextChangedListener(fieldValidatorTextWatcher);



        /* KEY UP ESTACIONAMIENTO   */
        final EditText parkin_txt = findViewById(R.id.parkin_txt);
        TextWatcher fieldValidatorTextWatcher2 = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                Log.d("afterTextChanged", "afterTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("beforeTextChanged", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged", "onTextChanged");
                pay();

            }

            private boolean filterLongEnough() {
                return peajes_txt.getText().toString().trim().length() > 2;
            }
        };
        parkin_txt.addTextChangedListener(fieldValidatorTextWatcher2);


        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fr = getFragmentManager();
        fr.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();


        final LinearLayout lg = findViewById(R.id.payment);
        lg.setVisibility(View.INVISIBLE);

        // HEADER MENU //
        View header = navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.username);
        TextView email = header.findViewById(R.id.email);


        name.setText(gloval.getGv_user_name());
        email.setText(gloval.getGv_user_mail());

        //btFinishVisible(false);


        // Llamamos que control si tenemos un viaje //
        //  controlVieTravel();

        //ACTIVAMOS EL TOkEN FIRE BASE//
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token: ", token);
        enviarTokenAlServidor(token, gloval.getGv_user_id());


        textTiempo = findViewById(R.id.textTiempo);
        textTiempo.setVisibility(View.INVISIBLE);


        //LLAMAMOS A EL METODO PARA HABILITAR PERMISOS//
        checkPermision();


        getPick(gloval.getGv_user_id());


        ws = new WsTravel();
        ws.connectWebSocket(pref.getInt("user_id", 0));


        btPreFinishVisible(false);
        btInitVisible(false);
        btCancelVisible(false);


        setTitle(gloval.getGv_nr_driver());
        toolbar.setLogo(R.drawable.ic_directions_car_black_24dp);
        toolbar.setSubtitle("Chofer");


        _activeTimer();


        tiempoTxt = pref.getInt("time_slepp", 0);
        if(tiempoTxt > 0){
            textTiempo.setVisibility(View.VISIBLE);
            textTiempo.setText(Utils.covertSecoungToHHMMSS(tiempoTxt));
        }else {
            textTiempo.setVisibility(View.INVISIBLE);

        }


        if(ServicesSleep.mRunning){
            btnInitSplep.setText("PAUSAR");
            textColorAnim = ObjectAnimator.ofInt(this.btnInitSplep, "textColor", Color.parseColor("#ffffff"),  Color.parseColor("#26c281"));
            textColorAnim.setDuration(1000);
            textColorAnim.setEvaluator(new ArgbEvaluator());
            textColorAnim.setRepeatCount(ValueAnimator.INFINITE);
            textColorAnim.setRepeatMode(ValueAnimator.REVERSE);
            textColorAnim.start();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (textColorAnim != null) {
                    textColorAnim.pause();
                }
            }

        }

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu_chofer);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item_chofer);

        materialDesignFAM.setIconAnimated(false);
        materialDesignFAM.getMenuIconView().setImageResource(R.drawable.ic_nature_people_black_24dp);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                materialDesignFAM.close(true);
                try {
                    requestTravel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    /// LLAMAMOS A EL SERVICIO DE SOLICITAR VIAJE //
    public void requestTravel() throws JSONException {

        getCurrentTravelByIdDriver();
        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }

        try {

            if(currentTravel == null) {

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date date = new Date();

                String currentDate = dateFormat.format(date).toString();

                TravelEntity travel = new TravelEntity();


                this.location = HomeFragment.nameLocation;
                this.lat = String.valueOf(HomeFragment.getmLastLocation().getLatitude());
                this.lon = String.valueOf(HomeFragment.getmLastLocation().getLongitude());
                this.dateTravel = currentDate;


                int idUserCompany = 0;
                boolean isTravelComany = false;
                idUserCompany = 0;


                travel.setTravelBody(
                        new TravelBodyEntity(
                                gloval.getGv_id_cliet(),
                                isTravelComany,
                                new OriginEntity(
                                        this.lat,
                                        this.lon,
                                        this.location),
                                new DestinationEntity(
                                        "",
                                        "",
                                        ""
                                )
                                , this.dateTravel, -1, true, idUserCompany,
                                "", "", "", "", 0, false,
                                0.0,
                                0.0,
                                gloval.getGv_id_driver()
                        )
                );


                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                System.out.println(gson.toJson(travel));


                // VALIDAMOS RESERVA O VIAJE //
                boolean validateRequired = true;
                validateRequired = true;


                if (validateRequired) {
                    floatingActionButton1.setEnabled(false);

                    Call<resp> call = this.daoTravel.addTravel(travel);

                    loading = ProgressDialog.show(this, "Enviando viaje", "Espere unos Segundos...", true, false);


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


                                Toast.makeText(getApplicationContext(), "Viaje Solicitado!", Toast.LENGTH_SHORT).show();


                                materialDesignFAM.close(true);

                                materialDesignFAM.setVisibility(View.INVISIBLE);
                                floatingActionButton1.setEnabled(false);

                                getCurrentTravelByIdDriver();
                            } else {

                                loading.dismiss();

                                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                                alertDialog.setTitle("ERROR" + "(" + response.code() + ")");
                                alertDialog.setMessage(response.errorBody().source().toString());


                                Log.w("***", response.errorBody().source().toString());


                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                materialDesignFAM.setVisibility(View.VISIBLE);
                                                floatingActionButton1.setEnabled(true);
                                            }
                                        });
                                alertDialog.show();

                            }


                        }

                        @Override
                        public void onFailure(Call<resp> call, Throwable t) {
                            loading.dismiss();

                            Snackbar.make(findViewById(android.R.id.content),
                                    "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                            materialDesignFAM.setVisibility(View.INVISIBLE);
                            floatingActionButton1.setEnabled(false);


                        }
                    });

                }
            }else {
                Snackbar.make(findViewById(android.R.id.content),"Ya posee un viaje asignado", Snackbar.LENGTH_LONG).show();
            }

        } finally{
            this.daoTravel = null;



    }




    }



    /*  start floating widget service  */
    public void createFloatingWidget(View view) {
        //Check if the application has draw over other apps permission or not?
        //This permission is by default available for API<23. But for API > 23
        //you have to ask for the permission in runtime.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE);
        } else{
            //If permission is granted start floating widget service
            startFloatingWidgetService();
        }

    }

    /*  Start Floating widget service and finish current activity */
    private void startFloatingWidgetService() {
        startService(new Intent(HomeActivity.this, FloatingWidgetService.class));
        finish();
    }


    private void setParamLocal() {
        try {
        param25 = Integer.parseInt(gloval.getGv_param().get(25).getValue());// SE PUEDE VER PRECIO EN VIAJE EN APP
        PARAM_66 = Integer.parseInt(gloval.getGv_param().get(65).getValue());// SE PUEDE VER PRECIO EN VIAJE EN APP
        PARAM_68 = Integer.parseInt(gloval.getGv_param().get(67).getValue());// SE PAGAR CON TARJETA


            PARAM_69 = gloval.getGv_param().get(68).getValue();//
            PARAM_79 = gloval.getGv_param().get(78).getValue();//

        } catch (IndexOutOfBoundsException e) {
            PARAM_69 = "";
            PARAM_79 = "";
        } catch (Exception e){
            Log.d("error",e.getMessage());
        }
        PARAM_3 = Integer.parseInt(gloval.getGv_param().get(2).getValue());
        PARAM_39 = Integer.parseInt(gloval.getGv_param().get(38).getValue());
        PARAM_67 = Integer.parseInt(gloval.getGv_param().get(66).getValue());


    }


    public void asigndTravelDriver() {




            loading = ProgressDialog.show(HomeActivity.this, "Verificando Viaje", "Espere unos Segundos...", true, false);


            if (Utils.verificaConexion(this) == false) {
                showAlertNoConexion();
            } else { // VERIFICADOR DE CONEXION

                if (this.daoTravel == null) {
                    this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
                }


                try {


                    TravelEntity travel = new TravelEntity(new TravelBodyEntity(
                            gloval.getGv_id_driver(), currentTravel.getIdTravel(), gloval.getGv_user_id()
                    ));


                    Call<InfoTravelEntity> call = this.daoTravel.asigned(travel);

                    Log.d("fatal", call.request().toString());
                    Log.d("fatal", call.request().headers().toString());

                    call.enqueue(new Callback<InfoTravelEntity>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResponse(Call<InfoTravelEntity> call, Response<InfoTravelEntity> response) {


                            if (response.code() == 200) {
                                InfoTravelEntity result = response.body();
                                loading.cancel();
                                getCurrentTravelByIdDriver();
                                verifickTravelCancelInit();

                            } else {

                                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                                alertDialog.setTitle("ERROR" + "(" + response.code() + ")");
                                alertDialog.setMessage(response.errorBody().source().toString());
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();

                            }
                        }

                        public void onFailure(Call<InfoTravelEntity> call, Throwable t) {
                            loading.cancel();
                            Snackbar.make(findViewById(android.R.id.content),
                                    "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                        }


                    });


                } finally {
                    this.daoTravel = null;
                }

            }

    }


    public boolean checkDistanceSucces() {

        try {


            int param30 = Integer.parseInt(gloval.getGv_param().get(29).getValue());
            if (param30 > 0) {

                Location locationA = new Location(HomeFragment.nameLocation);
                locationA.setLatitude(HomeFragment.getmLastLocation().getLatitude());
                locationA.setLongitude(HomeFragment.getmLastLocation().getLongitude());

                Location locationB = new Location(currentTravel.getNameOrigin());
                locationB.setLatitude(Double.parseDouble(currentTravel.getLatOrigin()));
                locationB.setLongitude(Double.parseDouble(currentTravel.getLonOrigin()));

                float distance = locationA.distanceTo(locationB) / 1000;
                ;


                Log.d("distance", String.valueOf(distance));
                Log.d("distance", String.valueOf(param30));


                if (distance <= param30) {

                    return true;

                } else {
                    return false;
                }


            }
            return false;


        } catch (Exception e) {

            Log.d("ERROR", e.getMessage());
            return false;

        }


    }

    public boolean checkPermision() {
        try {
            LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            // Comprobamos si está disponible el proveedor GPS.
            if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                alertDialog.setTitle("GPS INACTIVO!");
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);
                alertDialog.setMessage("Active el GPS para continuar!");


                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ACTIVAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                            }
                        });
                alertDialog.show();

                return false;

            } else {
                return true;
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "GPS ERROR:" + e.getMessage(), Toast.LENGTH_LONG).show();
            return false;

        }
    }

    public void showDialogTravel() {
        try {


            if (dialogTravel != null) {
                dialogTravel.dismiss();
            }


            if (currentTravel.getIdSatatusTravel() == 0) {

                Toast.makeText(getApplicationContext(), "Viaje Cancelado!", Toast.LENGTH_LONG).show();
                btPreFinishVisible(false);

                btInitVisible(false);
                btCancelVisible(false);

                viewAlert = false;
                currentTravel = null;
                HomeFragment.MarkerPoints = null;
                gloval.setGv_travel_current(null);
                setInfoTravel();

                tiempoTxt = 0;
                extraTime = 0;
                editor.putInt("time_slepp", 0);
                editor.commit(); // commit changes

                textTiempo.setVisibility(View.INVISIBLE);

                // _activeTimer();
            } else if (currentTravel.getIdSatatusTravel() == 8) {
                Toast.makeText(getApplicationContext(), "Viaje Cancelado por Cliente!", Toast.LENGTH_LONG).show();
                btPreFinishVisible(false);

                btInitVisible(false);
                btCancelVisible(false);

                viewAlert = false;
                currentTravel = null;
                HomeFragment.MarkerPoints = null;
                gloval.setGv_travel_current(null);
                setInfoTravel();

                tiempoTxt = 0;
                extraTime = 0;
                editor.putInt("time_slepp", 0);
                editor.commit(); // commit changes

                textTiempo.setVisibility(View.INVISIBLE);

                //  _activeTimer();
            } else if (currentTravel.getIdSatatusTravel() == 6) {
                Toast.makeText(getApplicationContext(), "Viaje Finalizado desde la Web, por el Operador!", Toast.LENGTH_LONG).show();

                btPreFinishVisible(false);
                viewAlert = false;

                currentTravel = null;
                HomeFragment.MarkerPoints = null;
                gloval.setGv_travel_current(null);
                setInfoTravel();

                tiempoTxt = 0;
                extraTime =0;
                editor.putInt("time_slepp", 0);
                editor.commit(); // commit changes

                textTiempo.setVisibility(View.INVISIBLE);

                //_activeTimer();
            } else {

                if (viewAlert == false) {
                    if (currentTravel.getIdSatatusTravel() == 2) {
                        viewAlert = true;


                        FragmentManager fm = getFragmentManager();
                        dialogTravel = new TravelDialog();
                        dialogTravel.show(fm, "Sample Fragment");
                        dialogTravel.setCancelable(false);

                        // this.cliaerNotificationAndoid();
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void fn_exit() throws IOException {
        // LAMAMOS A EL MAIN ACTIVITY//

        currentTravel = null;
        gloval.setGv_logeed(false);
        new GlovalVar();

        enviarTokenAlServidor("", gloval.getGv_user_id());

        if (ws != null) {
            ws.coseWebSocket();
        }

        if (timer != null) {
            timer.cancel();
        }

        if (timerConexion != null) {
            timerConexion.cancel();
        }


        if (HomeFragment.SPCKETMAP != null) {
            HomeFragment.SPCKETMAP.disconnect();
            HomeFragment.SPCKETMAP.close();
            HomeFragment.SPCKETMAP = null;

        }


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(HttpConexion.instance, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit(); // commit changes
        finish();


        Intent main = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(main);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void controlVieTravel() {
        try {



            Log.d("currentTravel", "CONTROL");


            if (currentTravel == null) {
                currentTravel = gloval.getGv_travel_current();
            }
            Log.d("VIAJE ESTATUS ", String.valueOf(currentTravel));


            if (currentTravel != null) {


                Log.d("VIAJE ESTATUS ", String.valueOf(currentTravel.getIdSatatusTravel()));


                HomeActivity.isRoundTrip = currentTravel.isRoundTrip;

                // CHOFER BUSQUEDA DE CLIENTE //
                if (currentTravel.getIdSatatusTravel() == 4) {
                    btInitVisible(true);
                    btCancelVisible(true);
                    btPreFinishVisible(false);
                    setInfoTravel();


                    Log.d("VIAJE ESTATUS ", "1");


                    // EN CURSO //
                } else if (currentTravel.getIdSatatusTravel() == 5) {
                    //activeTimerInit();
                    btPreFinishVisible(true);
                    btInitVisible(false);
                    btCancelVisible(false);
                    setInfoTravel();

                    // POR ACEPTAR//
                } else if (currentTravel.getIdSatatusTravel() == 2) {

                    viewAlert = false;
                    setNotification(currentTravel);
                    btInitVisible(false);
                    btCancelVisible(false);
                    btPreFinishVisible(false);
                    textTiempo.setVisibility(View.INVISIBLE);
                    // POR RECHAZADO POR OTRO CHOFER//
                } else if (currentTravel.getIdSatatusTravel() == 7) {
                    setNotification(currentTravel);
                    btInitVisible(false);
                    btCancelVisible(false);
                    btPreFinishVisible(false);
                    textTiempo.setVisibility(View.INVISIBLE);

                } else if (currentTravel.getIdSatatusTravel() == 0) {


                    if(dialogTravel != null) {
                        dialogTravel.dismiss();
                    }

                    final int idTravel = currentTravel.getIdTravel();



                    Snackbar snackbar = Snackbar.make(
                            findViewById(android.R.id.content),
                            "Viaje Cancelado! " + currentTravel.getCodTravel()+" - "+currentTravel.getReason(),
                            30000);
                    snackbar.setActionTextColor(Color.RED);
                    View snackbarView = snackbar.getView();
                    TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.WHITE);
                    textView.setTypeface(null, Typeface.BOLD);
                    snackbar.setAction("Confirmar", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                    confirmCancelByDriver(idTravel);
                                }
                            });

                    snackbar.show();



                    btInitVisible(false);
                    btCancelVisible(false);
                    btPreFinishVisible(false);

                    materialDesignFAM.setVisibility(View.VISIBLE);
                    floatingActionButton1.setEnabled(true);

                }


            } else {


                if (dialogTravel != null) {
                    dialogTravel.dismiss();
                }
                btInitVisible(false);
                btCancelVisible(false);
                btPreFinishVisible(false);
                textTiempo.setVisibility(View.INVISIBLE);
                currentTravel = null;
                // _activeTimer();
            }


            if(currentTravel == null){
                materialDesignFAM.setVisibility(View.VISIBLE);
                floatingActionButton1.setEnabled(true);
            }else {
                materialDesignFAM.setVisibility(View.INVISIBLE);
                floatingActionButton1.setEnabled(false);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmCancelByDriver(int idTravel) {

        if (this.daoTravel == null) {
            this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
        }


        try {
            Call<Boolean> call = this.daoTravel.confirmCancelByDriver(idTravel);

            call.enqueue(new Callback<Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {


                    currentTravel = null;
                    setInfoTravel();
                    materialDesignFAM.setVisibility(View.VISIBLE);
                    floatingActionButton1.setEnabled(true);

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

    // Enviar token al servidor //
    private void enviarTokenAlServidor(String str_token, int idUser) {


        if (idUser > 0) {


            if (this.daoLoguin == null) {
                this.daoLoguin = HttpConexion.getUri().create(ServicesLoguin.class);
            }

            try {
                token T = new token();
                T.setToken(new tokenFull(str_token, idUser, gloval.getGv_id_driver(), MainActivity.version));

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Log.d("JSON TOKEN ", gson.toJson(T));

                Call<Boolean> call = this.daoLoguin.token(T);

                call.enqueue(new Callback<Boolean>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Log.d("Response request", call.request().toString());
                        Log.d("Response request header", call.request().headers().toString());
                        Log.d("Response raw header", response.headers().toString());
                        Log.d("Response raw", String.valueOf(response.raw().body()));
                        Log.d("Response code", String.valueOf(response.code()));

                    }

                    public void onFailure(Call<Boolean> call, Throwable t) {


                        Log.d("ERROR", t.getMessage());
                    }
                });

            } finally {
                this.daoTravel = null;
            }
        }


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void iniTimeSlep() {


        if(ServicesSleep.mRunning) {
            btnPreFinish.setEnabled(true);
            btnReturn.setEnabled(true);
            // btnInitSplep.setBackgroundColor(getResources().getColor(R.color.CLEAR));

            stopService(new Intent(this, ServicesSleep.class));
            isWait(1);
            tiempoTxt =  pref.getInt("time_slepp", 0);
            textTiempo.setVisibility(View.VISIBLE);
            textTiempo.setText(Utils.covertSecoungToHHMMSS(tiempoTxt));

            Toast.makeText(getApplicationContext(), "Espera Desactivada", Toast.LENGTH_LONG).show();
            btnInitSplep.setText("ESPERAR");
            btnInitSplep.setTextColor(Color.parseColor("#ffffff"));
            textColorAnim.pause();

        }else {
            btnPreFinish.setEnabled(false);
            btnReturn.setEnabled(false);
            startService(new Intent(this, ServicesSleep.class));
            // btnInitSplep.setBackgroundColor(getResources().getColor(R.color.succes));
            isWait(0);
            Toast.makeText(getApplicationContext(), "Espera Activada", Toast.LENGTH_LONG).show();
            btnInitSplep.setText("PAUSAR");
            textColorAnim = ObjectAnimator.ofInt(this.btnInitSplep, "textColor", Color.parseColor("#ffffff"),  Color.parseColor("#26c281"));
            textColorAnim.setDuration(1000);
            textColorAnim.setEvaluator(new ArgbEvaluator());
            textColorAnim.setRepeatCount(ValueAnimator.INFINITE);
            textColorAnim.setRepeatMode(ValueAnimator.REVERSE);
            textColorAnim.start();

        }

        /*isWait(1);

        loadingCronometro = new ProgressDialog(HomeActivity.this);
        loadingCronometro.setTitle("Calculando Minutos de Espera");
        loadingCronometro.setMessage("Sumando Minutos...");
        loadingCronometro.setCancelable(false);
        loadingCronometro.setButton(DialogInterface.BUTTON_NEGATIVE, "Seguir con Viaje", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopTime();
            }
        });
        loadingCronometro.show();*/


        //btnPreFinish.setEnabled(false);
    }

    public void stopTime() {
        stopService(new Intent(this, ServicesSleep.class));

        /*loadingCronometro.dismiss();


        tiempoTxt =  pref.getInt("time_slepp", 0) + ServicesSleep.getTimeSleep();
        btnPreFinish.setEnabled(true);
        editor.putInt("time_slepp", tiempoTxt);
        editor.commit(); // commit changes
        textTiempo.setVisibility(View.VISIBLE);
        textTiempo.setText(Utils.covertSecoungToHHMMSS(tiempoTxt));

        isWait(0);*/

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onResume() {

        this.getParam();

        if (dialogTravel != null) {
            dialogTravel.dismiss();
        }

        super.onResume();


        if (currentTravel != null) {
            Log.d("currentTravel", "onResume" + String.valueOf(currentTravel.getIdSatatusTravel()));

            controlVieTravel();

        } else {

            getCurrentTravelByIdDriver();
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();


        //  if(HomeFragment.SPCKETMAP !=null) {
        //    HomeFragment.SPCKETMAP.disconnect();
        //}
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("onStop", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //createFloatingWidget(parentLayout);


    }






    private BroadcastReceiver broadcastReceiverLoadTodays = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("BroadcastReceiver 1-", String.valueOf(gloval.getGv_travel_current_lite()));

            getCurrentTravelByIdDriver();

            Log.d("BroadcastReceiver 2-", String.valueOf(ws));


        }
    };

    // tiempo de espera //
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    private void updateUI(Intent intent) {
        String counter = intent.getStringExtra("counter");
        String time = intent.getStringExtra("time");
        Log.d(TAG, counter);
        Log.d(TAG, time);

        // TextView txtDateTime = (TextView) findViewById(R.id.txtDateTime);
        //TextView txtCounter = (TextView) findViewById(R.id.txtCounter);
        //txtDateTime.setText(time);
        //txtCounter.setText(counter);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setInfoTravel()
    {

        if(currentTravel != null)
        {
            HomeFragment.setInfoTravel(currentTravel);
        }
        else
        {
            HomeFragment.clearInfo();
            viewAlert = false;
            if(dialogTravel != null) {
                dialogTravel.dismiss();
            }
        }
        drawer.closeDrawer(GravityCompat.START);

    }



    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first
        Log.d("onRestart", "onRestart");

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("uri", true);

    }



    public void showFinshTravel() throws IOException {

        pay();
        final LinearLayout lg = (LinearLayout) findViewById(R.id.payment);
        lg.setVisibility(View.VISIBLE);




    }


    public void pay()
    {

        try{

            /* VERIFICAMOS METODOS DE PAGO DISPONIBLES */
            if(currentTravel != null)
            {
                if(currentTravel.getIsTravelComany() == 1)// EMPRESA
                {
                    if (currentTravel.isPaymentCash != 1) {
                        btnFinishCash.setEnabled(false);
                    } else {
                        btnFinishCash.setEnabled(true);
                    }
                    btnFinishVo.setEnabled(true);

                }else {
                    btnFinishCash.setEnabled(true);
                    btnFinishVo.setEnabled(false);

                }
            }else {
                btnFinishCash.setEnabled(true);
            }
            /* */


            PARAM_1  = Double.parseDouble(gloval.getGv_param().get(0).getValue());// PRECIO DE LISTA
            double PARAM_5  = Double.parseDouble(gloval.getGv_param().get(4).getValue());// PRECIO LISTA TIEMPO DE ESPERA
            PARAM_6  = Double.parseDouble(gloval.getGv_param().get(5).getValue());// PRECIO LISTA TIEMPO DE VUELTA
            double PARAM_16  = Double.parseDouble(gloval.getGv_param().get(15).getValue());// VALOR MINIMO DE VIAJE
            int param25 = Integer.parseInt(gloval.getGv_param().get(25).getValue());// SE PUEDE VER PRECIO EN VIAJE EN APP
            int param78 = Integer.parseInt(gloval.getGv_param().get(77).getValue());// BAJADA DE BANDERA
            double PARAM_74  = Double.parseDouble(gloval.getGv_param().get(73).getValue());// PRECIO POR HORA AYUDANTES
            double numAsiste  = currentTravel.isFleetTravelAssistance;//   AYUDANTES
            double hor;
            double min = 0;


            btPreFinishVisible(false);

            Log.d("-TRAVEL DistanceSave-", String.valueOf( currentTravel.getDistanceSave()));


            /* DITANCIA TOTAL RECORRIDA */
            //m_total  = HomeFragment.calculateMiles(false)[0];//BUSCAMOS LA DISTANCIA TOTLA
            m_total  = HomeFragment.getDistanceSafe(currentTravel.getIdTravel(),-1);//BUSCAMOS LA DISTANCIA TOTLA


            Log.d("-TRAVEL totalDistance-", String.valueOf( m_total));

            /// if(m_total > 0){
            kilometros_total = m_total;//LO CONVERTIMOS A KILOMETRO y sumamos la distancia salvada


            /* DITANCIA TOTAL VULETA */
            m_vuelta  = HomeFragment.getDistanceSafe(currentTravel.getIdTravel(),1);//BUSCAMOS LA DISTANCIA VUELTA
            kilometros_vuelta = m_vuelta ;//LO CONVERTIMOS A KILOMETRO
            //**************************//

            /* DITANCIA TOTAL IDA */
            m_ida  = HomeFragment.getDistanceSafe(currentTravel.getIdTravel(),0);//BUSCAMOS LA DISTANCIA VUELTA
            kilometros_ida = Utils.round(m_ida,2) ;//LO CONVERTIMOS A KILOMETRO
            //**************************//


            if(kilometros_vuelta > 0) {
                if (kilometros_ida < kilometros_vuelta) {
                    kilometros_vuelta = Utils.round(kilometros_vuelta - kilometros_ida,2);
                } else {
                    kilometros_vuelta = Utils.round(kilometros_ida - kilometros_vuelta,2);
                }
            }else {
                kilometros_vuelta = 0;
            }




            /* DIFERENCIAR TIPO DE CLIENTE */
            Log.d("-TRAVEL isCompany-", String.valueOf(currentTravel.getIsTravelComany()));
            if(currentTravel.getIsTravelComany() == 1)// EMPRESA
            {

                /*VERIFICAMOS SI ES VIAJE POR HORA*/
                if(currentTravel.getIsTravelByHour() == 1) {
                    int hours = new Time(System.currentTimeMillis()).getHours();
                    int newHours = hours - gloval.getGv_hour_init_travel();

                    if(newHours <1 || gloval.getGv_hour_init_travel() == 0) {
                        newHours = 1;
                    }


                    Log.d("-hours- vehicleMinHourService", String.valueOf(hours));


                    if(currentTravel.getKmex() >0 && kilometros_total > currentTravel.getKmex()){ // KILOMETROS EXEDENTES

                        double kilometros_extra = currentTravel.getKmex() - kilometros_total;
                        amounCalculateGps = (newHours * currentTravel.getPriceHour()) +  (kilometros_extra * currentTravel.getPricePerKmex());

                    }else {
                        amounCalculateGps = newHours * currentTravel.getPriceHour();

                    }



                }
                else{

                    /*VERIFICAMOS SI ESTA ACTIVO EL CAMPO BENEFICIO POR KILOMETRO PARA ESA EMPRESA*/
                    Log.d("-TRAVEL Beneficio-", String.valueOf(currentTravel.getIsBenefitKmList()));
                    if (currentTravel.getIsBenefitKmList() == 1
                            && currentTravel.getListBeneficio() != null
                            && currentTravel.getListBeneficio().size() > 0) {

                        Log.d("-TRAVEL kilometros_total-", String.valueOf(kilometros_total));
                        Log.d("-TRAVEL BenefitsToKm-", String.valueOf(currentTravel.getBenefitsToKm()));
                        Log.d("-TRAVEL BenefitsFromKm-", String.valueOf(currentTravel.getBenefitsFromKm()));


                        /* VERIFICAMOS SI ESTA DENTRO DE EL RANGO DE EL BENEFICIO ESTABLECIDO */
                        amounCalculateGps = this.getPriceBylistBeneficion(currentTravel.getListBeneficio(),kilometros_ida);

                        // VERIFICAMOS SI HAY RETORNO //
                        if (HomeActivity.isRoundTrip == 1) {
                            amounCalculateGps = amounCalculateGps + this.getPriceReturnBylistBeneficion(currentTravel.getListBeneficio(),kilometros_vuelta);
                        }

                        if(amounCalculateGps <1){
                            amounCalculateGps = kilometros_total * currentTravel.getPriceDitanceCompany();
                            Log.d("-TRAVEL amounCalculateGps (2)-", String.valueOf(amounCalculateGps));
                        }


                    } else {
                        amounCalculateGps = kilometros_total * currentTravel.getPriceDitanceCompany();// PARA CLIENTES EMPREA BUSCAMOS EL PRECIO DE ESA EMPRESA

                        Log.d("-TRAVEL amounCalculateGps (3)-", String.valueOf(amounCalculateGps));

                        if (HomeActivity.isRoundTrip == 1) {
                            Log.d("-TRAVEL isRoundTrip -", String.valueOf(HomeActivity.isRoundTrip));
                            amounCalculateGps = kilometros_ida * currentTravel.getPriceDitanceCompany();
                            Log.d("-TRAVEL amounCalculateGps (4)-", String.valueOf(amounCalculateGps));
                            amounCalculateGps = amounCalculateGps + kilometros_vuelta * currentTravel.getPriceReturn();
                            Log.d("-TRAVEL amounCalculateGps (5)-", String.valueOf(amounCalculateGps));
                        }
                    }
                }



            }
            else // PARTICULARES
            {



                // VERIFICAMOS EL BEMEFICIO POR KM PARA CLIENTES PARTICULARES //

                if(currentTravel.getIsBenefitKmClientList() == 1
                        && currentTravel.getListBeneficio() != null
                        && currentTravel.getListBeneficio().size() > 0) {

                    amounCalculateGps = this.getPriceBylistBeneficion(currentTravel.getListBeneficio(),kilometros_ida);

                    if(HomeActivity.isRoundTrip == 1) {
                        Log.d("-TRAVEL amounCalculateGps (7)-", String.valueOf(HomeActivity.isRoundTrip));

                        //if (currentTravel.getIsBenefitKmList() == 1) {
                            amounCalculateGps = amounCalculateGps + this.getPriceReturnBylistBeneficion(currentTravel.getListBeneficio(), kilometros_vuelta);
                        //}
                    }

                    if(amounCalculateGps <1){
                        amounCalculateGps = kilometros_total * PARAM_1;// PARA CLIENTES PARTICULARES BUSCAMOS EL PRECIO DE LISTA

                    }

                }else {


                    Log.d("-TRAVEL amounCalculateGps (6)-", String.valueOf(amounCalculateGps));
                    Log.d("-TRAVEL amounCalculateGps (7)-", String.valueOf(HomeActivity.isRoundTrip));

                    if(HomeActivity.isRoundTrip == 1)
                    {
                        amounCalculateGps = kilometros_ida * PARAM_1;
                        Log.d("-TRAVEL amounCalculateGps (8)-", String.valueOf(amounCalculateGps));
                        amounCalculateGps =  amounCalculateGps + kilometros_vuelta * PARAM_6;
                        Log.d("-TRAVEL amounCalculateGps (9)-", String.valueOf(amounCalculateGps));

                    }else{
                        amounCalculateGps = kilometros_total * PARAM_1;// PARA CLIENTES PARTICULARES BUSCAMOS EL PRECIO DE LISTA

                    }

                }


            }


            // SUMA EL ORIGEN PACTADO //
            amounCalculateGps =  amounCalculateGps + currentTravel.getAmountOriginPac();
            Log.d("-TRAVEL amounCalculateGps (10)-", String.valueOf(amounCalculateGps));



            // VALIDAMOS VIAJES PUTO A PUNTO
            if(currentTravel.getIsPointToPoint() == 1){
                Log.d("getIsPointToPoint", String.valueOf(currentTravel.getIsPointToPoint()));
                amounCalculateGps = currentTravel.pricePoint; // sumamos el precio del punto a punto
                kilometros_total = 0;
            }


            //VALIDAMOS SI EL VIAJE NO SUPERA EL MINUMO//
            if(currentTravel.getIsTravelComany() == 1)// PARA EMPRESA
            {
                if(amounCalculateGps <  currentTravel.getPriceMinTravel()){
                    amounCalculateGps = currentTravel.getPriceMinTravel();
                    Log.d("-TRAVEL amounCalculateGps (11)-", String.valueOf(amounCalculateGps));
                }
            }else {// PARA PARTICULARES
                if(amounCalculateGps <  PARAM_16){
                    amounCalculateGps = PARAM_16;
                    Log.d("-TRAVEL amounCalculateGps (12)-", String.valueOf(amounCalculateGps));
                }

            }



            hor=min/3600;
            min=(tiempoTxt-(3600*hor))/60;//  BUSCAMOS SI REALIZO ESPERA

            Log.d("-TRAVEL min espera -", String.valueOf(min));


            /* DIFERENCIAR TIPO DE CLIENTE */
            if(currentTravel.getIsTravelComany() == 1)// EMPRESA
            {
                if(min > 0) {
                    extraTime = min * currentTravel.getPriceMinSleepCompany();
                }else if(min > 0.1) {
                    extraTime = 1 * currentTravel.getPriceMinSleepCompany();
                }

                Log.d("-TRAVEL min extraTime -", String.valueOf(extraTime));
            }
            else // PARTICULARES
            {
                if(min > 0) {
                    extraTime = min * PARAM_5;
                }else if(min > 0.1) {
                    extraTime = 1 * PARAM_5;
                }
            }

            if(param25 == 1) {
                txtTimeslep.setText(Utils.covertSecoungToHHMMSS(tiempoTxt) + " - $" + Double.toString(round(extraTime, 2)));
            }else {
                txtTimeslep.setText(Utils.covertSecoungToHHMMSS(tiempoTxt));
            }


            if(param25 == 1) {
                distance_txt.setText(df.format(kilometros_ida) + " Km");
                distance_return_txt.setText(df.format(kilometros_vuelta) + " Km");



            }else {
                distance_txt.setText(df.format(kilometros_ida) + " Km ");
                distance_return_txt.setText(df.format(kilometros_vuelta) + " Km");


            }


            priceFlet = 0;
            if(currentTravel.getIsFleetTravelAssistance() > 0){

                int hours = new Time(System.currentTimeMillis()).getHours();
                int newHours = hours - gloval.getGv_hour_init_travel();

                if(newHours <1 || gloval.getGv_hour_init_travel() == 0){
                    newHours = 1;
                }

                priceFlet = (PARAM_74 * newHours) * numAsiste;
                txtamounFlet.setText("$"+df.format(priceFlet));
            }else {
                txtamounFlet.setText("$0.0");
            }






            double myDouble;
            String myString = peajes_txt.getText().toString();
            if (myString != null && !myString.equals("")) {
                myDouble = Double.valueOf(myString);
            } else {
                myDouble = 0;
            }

            double parkin;
            String parkin_txt = ((EditText) findViewById(R.id.parkin_txt)).getText().toString();
            if (parkin_txt != null && !parkin_txt.equals("")) {
                parkin = Double.valueOf(parkin_txt);
            } else {
                parkin = 0;
            }




            if(param78 > 0) {
                if(currentTravel.getIsTravelComany() == 1){// EMPRESA
                    if(param78 == 1){
                        bandera = currentTravel.getPriceMinTravel();
                    }else if(param78 == 2) {
                        if(amounCalculateGps <  currentTravel.getPriceMinTravel()) {
                            bandera = currentTravel.getPriceMinTravel();

                        }
                    }

                }else{
                    if(param78 == 1) {
                        bandera = PARAM_16;
                    }
                    else if(param78 == 2) {
                        if(amounCalculateGps <  PARAM_16) {
                            bandera = PARAM_16;
                        }
                    }
                }

            }





            totalFinal =  amounCalculateGps + extraTime + myDouble + parkin + bandera + priceFlet;
            Log.d("-TRAVEL  totalFinal -", String.valueOf(totalFinal));


            if(param25 == 1){
                txtMount.setText("$"+Double.toString(round(totalFinal,2)));
            }
            else {
                txtMount.setText("---");
            }

        }catch (Exception e){
            Log.d("ERRROR",e.getMessage());
        }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirma para salir de la App?")
                .setCancelable(false)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            fn_exit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private int mNotificationsCount = 0;
    private int mNotificationsReservationsCount = 0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);


        // ETO NOS PERMITE CARGAR EL ICONO DE MENU CON NOTIFICACIONBES //
        MenuItem item = menu.findItem(R.id.action_notifications);
        LayerDrawable icon = (LayerDrawable) item.getIcon();

        // ETO NOS PERMITE CARGAR EL ICONO DE MENU CON NOTIFICACIONBES DE RESERVAS //
        MenuItem item2 = menu.findItem(R.id.action_reervations);
        LayerDrawable icon2 = (LayerDrawable) item2.getIcon();

        // ACTUALIZAMOS EL NUMERO DE NOTIFICCIONES
        Utils.setBadgeCount(this, icon, mNotificationsCount,R.id.ic_badge);
        Utils.setBadgeCount(this, icon2, mNotificationsReservationsCount,R.id.ic_badge2);

        return true;
    }

    /*  modificar notificacione  */
    private void updateNotificationsBadge(int couterNotifications,int couterReservations) {
        mNotificationsCount = couterNotifications;
        mNotificationsReservationsCount = couterReservations;
        invalidateOptionsMenu();

        if(couterReservations >0) {
            Toast.makeText(getApplicationContext(), "Tienes (" + couterReservations + ") Reservas!", Toast.LENGTH_LONG).show();
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            try {
                fn_exit();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }
        else if (id == R.id.action_notifications) {

            fn_gotonotification();
            return true;
        }
        else if (id == R.id.action_reervations) {

            fn_gotoreservation();
            return true;
        }
        else if (id == R.id.action_refhesh) {

            fn_refhesh();
            return true;
        }
        else if (id == R.id.action_profile) {

            fn_gotoprofile();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }


    private void fn_verificateConexion() {

        if( Utils.verificaConexion(this) == true) {
            _NOCONEXION = false;

        }else
        {

            if(_NOCONEXION == false) {
                showAlertNoConexion();
                _NOCONEXION = true;
            }


        }
    }


    private void fn_refhesh() {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION
            this.getParam();
            this.getCurrentTravelByIdDriver();
        }
    }

    public  void  fn_gotoprofile()
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            FragmentManager fm = getFragmentManager();

            // VERIFICAMO SI E UN CHOFER //
            if (gloval.getGv_id_profile() == 3) {
                fm.beginTransaction().replace(R.id.content_frame, new ProfileDriverFr()).commit();
            }
            // VERIFICAMO SI E UN CLIENTE PÁRTICULAR//
            else if (gloval.getGv_id_profile() == 2) {
                fm.beginTransaction().replace(R.id.content_frame, new ProfileClientFr()).commit();
            }
        }
    }


    public void fn_gotonotification()
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new NotificationsFrangment()).commit();
        }
    }

    public void fn_gotoreservation()
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new ReservationsFrangment()).commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();

        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION
            if (id == R.id.nav_camera) {
                fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();

            } else if (id == R.id.nav_gallery) {
                fm.beginTransaction().replace(R.id.content_frame, new HistoryTravelDriver()).commit();

            } else if (id == R.id.nav_slideshow) {
                fm.beginTransaction().replace(R.id.content_frame, new AcountDriver()).commit();


            } else if (id == R.id.nav_manage) {
                fm.beginTransaction().replace(R.id.content_frame, new NotificationsFrangment()).commit();

            } else if (id == R.id.nav_reservations) {
                fn_gotoreservation();

            } else if (id == R.id.nav_send) {

            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void _activeTimer()
    {


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TIMER","TIMER 1");
                        setLocationVehicheDriver();

                    }
                });

            }
        }, 0, 120000);


        timerConexion = new Timer();
        timerConexion.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                Log.d("TIMER","TIMER 2");
                                fn_verificateConexion();

                            }
                        });

            }
        }, 0, 30000);

    }

    public  void setLocationVehicheDriver()
    {

        if (currentTravel != null ) {
            if (currentTravel.getIdSatatusTravel() == 5) {


                if (this.daoTravel == null) {
                    this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
                }

                try {

                    String lat = "";
                    String lon = "";
                    String add = "";


                    if (HomeFragment.getmLastLocation() != null) {
                        lat = String.valueOf(HomeFragment.getmLastLocation().getLatitude());
                        lon = String.valueOf(HomeFragment.getmLastLocation().getLongitude());
                        add = HomeFragment.nameLocation;
                    }

                    Log.d("setLocationVehicheDriver 00", String.valueOf(HomeFragment.nameLocation));
                    Log.d("setLocationVehicheDriver  01", "**" + add);

                    if (add != "") {

                        // Log.d("setLocationVehicheDriver  ", "PASO 1");
                        int idTrave = 0;
                        int idClientKf = 0;

                        if (currentTravel != null) {
                            idTrave = currentTravel.getIdTravel();
                            idClientKf = currentTravel.getIdClientKf();
                        }

                        TraveInfoSendEntity travel =
                                new TraveInfoSendEntity(new
                                        TravelLocationEntity(
                                        gloval.getGv_user_id(),
                                        idTrave,
                                        add,
                                        lon,
                                        lat,
                                        gloval.getGv_id_driver(),
                                        gloval.getGv_id_vehichle(),
                                        idClientKf,
                                        HomeFragment.calculateMiles(false)[0],
                                        kilometros_vuelta

                                )
                                );


                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        Log.d("setLocationVehicheDriver", gson.toJson(travel));


                        Call<RemisSocketInfo> call = this.daoTravel.sendPosition(travel);

                        call.enqueue(new Callback<RemisSocketInfo>() {
                            @Override
                            public void onResponse(Call<RemisSocketInfo> call, Response<RemisSocketInfo> response) {

                                Log.d("setLocationVehicheDriver Response raw header", response.headers().toString());
                                Log.d("setLocationVehicheDriver Response raw", String.valueOf(response.raw().body()));
                                Log.d("setLocationVehicheDriver Response code", String.valueOf(response.code()));

                                if (response.code() == 200) {

                                    RemisSocketInfo list = (RemisSocketInfo) response.body();
                                    notificate(list.getListNotification(), list.getListReservations());
                                    fn_refhesh();


                                }

                            }

                            @Override
                            public void onFailure(Call<RemisSocketInfo> call, Throwable t) {
                                Log.d("**ERROR**", t.getMessage());
                                // Toast.makeText(getApplicationContext(), "ERROR ENVIADO UBICACION!", Toast.LENGTH_LONG).show();

                            }
                        });

                    }


                } catch (Exception e) {
                    Log.d("setLocationVehicheDriver", e.getMessage());
                } finally {
                    this.daoTravel = null;
                }

            }
        }
    }


    public void notificate(List<notification> list,List<InfoTravelEntity> listReservations)
    {

        int couterNotifications = 0;
        int couterRervations = 0;

        if(list != null)
        {
            couterNotifications = list.size();
        }

        if(listReservations != null)
        {
            couterRervations = listReservations.size();
        }

        updateNotificationsBadge(couterNotifications,couterRervations);
    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setNotification(final InfoTravelEntity travel)
    {
        Log.d("currentTravel  full", String.valueOf(viewAlert));

        if(viewAlert == false)
        {
            currentTravel =  travel;
            gloval.setGv_travel_current(currentTravel);

            showDialogTravel();// MOSTRAMO EL FRAGMENT DIALOG

        }


    }

    // RECHAZAR VIAJE //
    public void cancelTravel(int idTravel)
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            if (idTravel == -1) {
                idTravel = gloval.getGv_travel_current().idTravel;
            }

            try {

                loading = ProgressDialog.show(HomeActivity.this, "Enviado", "Espere unos Segundos...", true, false);


                Call<InfoTravelEntity> call = this.daoTravel.refuse(idTravel);


                call.enqueue(new Callback<InfoTravelEntity>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<InfoTravelEntity> call, Response<InfoTravelEntity> response) {

                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "VIAJE RECHAZADO!", Toast.LENGTH_LONG).show();


                        if (dialogTravel != null) {
                            dialogTravel.dismiss();
                        }

                        cliaerNotificationAndoid();
                        // viewAlert = false;
                        gloval.setGv_travel_current(null);
                        currentTravel = null;
                        btCancelVisible(false);
                        btInitVisible(false);
                        HomeFragment.clearInfo();
                        viewAlert = false;


                    }

                    public void onFailure(Call<InfoTravelEntity> call, Throwable t) {
                        loading.dismiss();

                        Snackbar.make(findViewById(android.R.id.content),
                                "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                    }


                });

            } finally {
                this.daoTravel = null;
            }
        }
    }

    public  void btCancelVisible(boolean b)
    {


        if(b)
        {
            btn_cancel.setVisibility(View.VISIBLE);
            if(PARAM_66 == 1){
                btn_cancel.setEnabled(true);
            }else {
                btn_cancel.setEnabled(false);
            }

        }else {
            btn_cancel.setVisibility(View.INVISIBLE);
        }
    }

    public  void btInitVisible(boolean b)
    {

        if(b)
        {
            btn_init.setVisibility(View.VISIBLE);
        }else {
            btn_init.setVisibility(View.INVISIBLE);
        }
    }

    public  void btPreFinishVisible(boolean b)
    {

        if(b)
        {
            this.btnLogin.setVisibility(View.VISIBLE);
            this.btnInitSplep.setVisibility(View.VISIBLE);
            this.btn_return.setVisibility(View.VISIBLE);
        }else {
            this.btnLogin.setVisibility(View.INVISIBLE);
            this.btnInitSplep.setVisibility(View.INVISIBLE);
            this.btn_return.setVisibility(View.INVISIBLE);
        }
    }


    /* SERVICIO PARA RETORNAR UN VIAJE EN EL MONITOR DDE VIAJES */
    public  void  isRoundTrip()
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {
                Call<Boolean> call = this.daoTravel.isRoundTrip(currentTravel.idTravel);

                Log.d("fatal", call.request().toString());
                Log.d("fatal", call.request().headers().toString());

                call.enqueue(new Callback<Boolean>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        HomeActivity.isRoundTrip = 1;
                        Toast.makeText(getApplicationContext(), "Vuelta Activada!", Toast.LENGTH_LONG).show();
                        currentTravel.setIsRoundTrip(1);
                        setInfoTravel();

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

    /* SERVICIO PARA VERIFICA SI EL VIAJE NO SE FINALIZON ANTES  */
    public  void  verifickTravelFinish()
    {

        loading = ProgressDialog.show(HomeActivity.this, "Verificando Viaje", "Espere unos Segundos...", true, false);


        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {

                if(currentTravel != null) {

                    Call<Boolean> call = this.daoTravel.verifickTravelFinish(currentTravel.idTravel);

                    Log.d("fatal", call.request().toString());
                    Log.d("fatal", call.request().headers().toString());

                    call.enqueue(new Callback<Boolean>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            boolean result = response.body();
                            if (result) {
                                loading.cancel();
                                final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(HomeActivity.this);
                                dialog.setMessage("Viaje ya fue finalizado previamente")
                                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                                clearFinish();
                                            }
                                        }) ;
                                dialog.show();
                            } else {
                                loading.cancel();
                                finishTravel();// FINALIZAMOS EL VIAJE
                            }

                        }

                        public void onFailure(Call<Boolean> call, Throwable t) {
                            loading.cancel();
                            Snackbar.make(findViewById(android.R.id.content),
                                    "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                        }


                    });
                }else {

                    loading.cancel();

                    final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
                    dialog.setMessage("Viaje ya fue finalizado previamente")
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    clearFinish();
                                }
                            }) ;
                    dialog.show();

                }

            } finally {
                this.daoTravel = null;
            }
        }
    }

    /* SERVICIO PARA VERIFICA SI EL VIAJE NO SE CANCELO ANTES  */
    public  void  verifickTravelCancel(final int idTravel)
    {

        loading = ProgressDialog.show(HomeActivity.this, "Verificando Viaje", "Espere unos Segundos...", true, false);


        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {

                if(currentTravel != null) {

                    Call<Boolean> call = this.daoTravel.verifickTravelCancel(idTravel);

                    Log.d("fatal", call.request().toString());
                    Log.d("fatal", call.request().headers().toString());

                    call.enqueue(new Callback<Boolean>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            boolean result = response.body();
                            if (result) {
                                loading.cancel();
                                final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(HomeActivity.this);
                                dialog.setMessage("Viaje fue Cancelado previamente")
                                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                                fn_refhesh();
                                            }
                                        }) ;
                                dialog.show();
                            } else {
                                loading.cancel();
                                aceptTravel(idTravel);// ACEPTAMOS EL VIAJE
                            }

                        }

                        public void onFailure(Call<Boolean> call, Throwable t) {
                            loading.cancel();
                            Snackbar.make(findViewById(android.R.id.content),
                                    "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                        }


                    });
                }else {

                    loading.cancel();

                    final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
                    dialog.setMessage("Viaje ya fue finalizado previamente")
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    clearFinish();
                                }
                            }) ;
                    dialog.show();

                }

            } finally {
                this.daoTravel = null;
            }
        }
    }


    /* SERVICIO PARA VERIFICA SI EL VIAJE NO SE CANCELO ANTES para poder iniciar  */
    public  void  verifickTravelCancelInit()
    {

        loading = ProgressDialog.show(HomeActivity.this, "Verificando Viaje", "Espere unos Segundos...", true, false);


        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {

                if(currentTravel != null) {

                    Call<Boolean> call = this.daoTravel.verifickTravelCancel(currentTravel.idTravel);

                    Log.d("fatal", call.request().toString());
                    Log.d("fatal", call.request().headers().toString());

                    call.enqueue(new Callback<Boolean>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            boolean result = response.body();
                            if (result) {
                                loading.cancel();
                                final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(HomeActivity.this);
                                dialog.setMessage("Viaje fue Cancelado previamente")
                                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                                fn_refhesh();
                                            }
                                        }) ;
                                dialog.show();
                            } else {
                                loading.cancel();
                                initTravel();// INICIAMOS EL VIAJE
                            }

                        }

                        public void onFailure(Call<Boolean> call, Throwable t) {
                            loading.cancel();
                            Snackbar.make(findViewById(android.R.id.content),
                                    "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                        }


                    });
                }else {

                    loading.cancel();

                    final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
                    dialog.setMessage("Viaje ya fue finalizado previamente")
                            .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    clearFinish();
                                }
                            }) ;
                    dialog.show();

                }

            } finally {
                this.daoTravel = null;
            }
        }
    }



    public void clearFinish(){
        btInitVisible(false);
        btCancelVisible(false);
        btPreFinishVisible(false);


        currentTravel = null;
        HomeFragment.MarkerPoints = null;
        if (HomeFragment.options != null) {
            HomeFragment.options.getPoints().clear();
        }
        gloval.setGv_travel_current(null);
        setInfoTravel();
        viewAlert = false;


        tiempoTxt = 0;
        textTiempo.setVisibility(View.INVISIBLE);
        extraTime = 0;
        editor.putInt("time_slepp", 0);
        editor.commit(); // commit changes


        final LinearLayout lg = (LinearLayout) findViewById(R.id.payment);
        lg.setVisibility(View.INVISIBLE);

        gloval.setGv_hour_init_travel(0);// GUARDAMOS LA HORA QUE LO INICIO

    }



    /* SERVICIO PARA INDICAR ESPERA DE UN VIAJE EN EL MONITOR DDE VIAJES */
    public  void  isWait(int value)
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {
                Call<Boolean> call = this.daoTravel.isWait(currentTravel.idTravel, value);

                Log.d("fatal", call.request().toString());

                call.enqueue(new Callback<Boolean>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        setInfoTravel();

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

    public  void  getCurrentTravelByIdDriver()
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION
            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {

                int idDriver = gloval.getGv_id_driver();

                if (idDriver == 0) {
                    idDriver = pref.getInt("driver_id", 0);
                    gloval.setGv_id_driver(idDriver);
                }




                Call<InfoTravelEntity> call = this.daoTravel.getCurrentTravelByIdDriver(idDriver);

                Log.d("Response request", call.request().toString());
                Log.d("Response request header", call.request().headers().toString());

                call.enqueue(new Callback<InfoTravelEntity>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<InfoTravelEntity> call, Response<InfoTravelEntity> response) {



                        InfoTravelEntity TRAVEL = (InfoTravelEntity) response.body();

                        gloval.setGv_travel_current(TRAVEL);
                        currentTravel = gloval.getGv_travel_current();
                        controlVieTravel();

                    }

                    public void onFailure(Call<InfoTravelEntity> call, Throwable t) {
                        Snackbar.make(findViewById(android.R.id.content),
                                "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();

                    }


                });

            } finally {
                this.daoTravel = null;
            }
        }
    }

    /* METODO PARA ACEPATAR EL VIAJE*/
    public  void  aceptTravel(int idTravel)
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            try {
                loading = ProgressDialog.show(HomeActivity.this, "Enviado", "Espere unos Segundos...", true, false);

                Call<InfoTravelEntity> call = this.daoTravel.accept(idTravel);

                Log.d("fatal", call.request().toString());
                Log.d("fatal", call.request().headers().toString());

                call.enqueue(new Callback<InfoTravelEntity>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(Call<InfoTravelEntity> call, Response<InfoTravelEntity> response) {
                        loading.dismiss();

                        Toast.makeText(getApplicationContext(), "VIAJE ACEPTADO...", Toast.LENGTH_LONG).show();
//                        Log.d("fatal", response.body().toString());


                        btInitVisible(true);
                        btCancelVisible(true);
                        cliaerNotificationAndoid();
                        viewAlert = true;
                        dialogTravel.dismiss();
                        currentTravel = response.body();
                        setInfoTravel();

                        materialDesignFAM.setVisibility(View.INVISIBLE);
                        floatingActionButton1.setEnabled(false);

                    }

                    public void onFailure(Call<InfoTravelEntity> call, Throwable t) {
                        loading.dismiss();

                        Snackbar.make(findViewById(android.R.id.content),
                                "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                    }


                });

            } finally {

                this.daoTravel = null;
            }
        }

    }

    public  void  initTravel()
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }


            if (this.checkPermision()) {
                if (this.checkDistanceSucces()) {

                    try {
                        loading = ProgressDialog.show(HomeActivity.this, "Enviado", "Espere unos Segundos...", true, false);

                        Call<InfoTravelEntity> call = this.daoTravel.init(currentTravel.getIdTravel());

                        call.enqueue(new Callback<InfoTravelEntity>() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onResponse(Call<InfoTravelEntity> call, Response<InfoTravelEntity> response) {

                                loading.dismiss();
                                btInitVisible(false);
                                btCancelVisible(false);
                                btPreFinishVisible(true);

                                currentTravel = response.body();
                                setInfoTravel();


                                //activeTimerInit();
                            }

                            public void onFailure(Call<InfoTravelEntity> call, Throwable t) {
                                loading.dismiss();
                                Snackbar.make(findViewById(android.R.id.content),
                                        "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                            }
                        });
                        int hours = new Time(System.currentTimeMillis()).getHours();
                        Log.d("hours", String.valueOf(hours));
                        gloval.setGv_hour_init_travel(hours);// GUARDAMOS LA HORA QUE LO INICIO
                    } finally {
                        this.daoTravel = null;
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content),
                            "No puedes iniciar este viaje, debe aproximarse mas al punto de origen", Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }


    /*LLAMAMOS A LA PATALLA DE BOUCHE*/
    public void finishTravelVouche()
    {
        if(HomeActivity.currentTravel != null){
            if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION
                Intent intent = new Intent(getApplicationContext(), Signature.class);
                startActivityForResult(intent, SIGNATURE_ACTIVITY);
            }
        }else {
            clearFinish();
        }

    }


    /*LLAMAMOS A LA PATALLA DE MERCADO  PAGO */
    public void finishTravelCreditCar()
    {
        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Presiona continuar para realizar el pago con Tarjetas")
                    .setCancelable(false)
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            idPaymentFormKf = 3;
                            //finishTravel();

                            //   Intent intent = new Intent(getApplicationContext(), PaymentCreditCar.class);
                            // intent.putExtra("TotalAmount",this.totalFinal);

                            //startActivityForResult(intent, CREDIT_CAR_ACTIVITY);


                            preFinihMpago();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }


    }


    /* ALERTA SIN CONEXION*/
    public void showAlertNoConexion(){
        Snackbar snackbar = Snackbar.make(
                findViewById(android.R.id.content),
                "¡Problema de conexion a Internet!",
                30000);
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(null, Typeface.BOLD);

        snackbar.setAction("Verificar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
            }
        });

        snackbar.show();
    }


    /* METODO PARA FINALIZAR O PREFINALIZAR  UN VIAJE*/
    public  void  finishTravel() {

        if( Utils.verificaConexion(this) == false) {showAlertNoConexion();}else { // VERIFICADOR DE CONEXION

            if (this.daoTravel == null) {
                this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class);
            }

            try {
                loading = ProgressDialog.show(HomeActivity.this, "Finalizando Viaje", "Espere unos Segundos...", true, false);


                if (currentTravel != null) {

                    String lat = "";
                    String lon = "";
                    String add = "";

                    if (HomeFragment.getmLastLocation() != null) {
                        lat = String.valueOf(HomeFragment.getmLastLocation().getLatitude());
                        lon = String.valueOf(HomeFragment.getmLastLocation().getLongitude());
                        add = HomeFragment.nameLocation;
                    }

                    // BUSCAMOS EL VALOR DE EL PEAJE //

                    double myDouble;
                    String myString = peajes_txt.getText().toString();
                    if (myString != null && !myString.equals("")) {
                        myDouble = Double.valueOf(myString);
                    } else {
                        myDouble = 0;
                    }
                    peajes_txt.setText("");

                    double parkin;
                    String parkin_txt = ((EditText) findViewById(R.id.parkin_txt)).getText().toString();
                    if (parkin_txt != null && !parkin_txt.equals("")) {
                        parkin = Double.valueOf(parkin_txt);
                    } else {
                        parkin = 0;
                    }
                    ((EditText) findViewById(R.id.parkin_txt)).setText("");


                    double _RECORIDO_TOTAL = 0;
                    if (kilometros_total > 0) {
                        _RECORIDO_TOTAL = Utils.round(kilometros_total, 2);
                    }


                    TraveInfoSendEntity travel =
                            new TraveInfoSendEntity(new
                                    TravelLocationEntity
                                    (
                                            currentTravel.getIdTravel(),
                                            amounCalculateGps+bandera,
                                            //Double.parseDouble(val),
                                            _RECORIDO_TOTAL,
                                            df.format(kilometros_total),
                                            add,
                                            lon,
                                            lat,
                                            myDouble,
                                            parkin,
                                            extraTime,
                                            Utils.covertSecoungToHHMMSS(tiempoTxt),
                                            idPaymentFormKf,
                                            mp_jsonPaymentCard,
                                            mp_paymentMethodId,
                                            mp_paymentTypeId,
                                            mp_paymentstatus,
                                            priceFlet,
                                            Utils.round(kilometros_vuelta,2)

                                    )
                            );


                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();


                    Call<InfoTravelEntity> call = null;
                    /* VERIFICAMOS I ESTA HABILITADO EL CIERRE DE VIAJES DEDE LA APP O NO*/


                    int evaluationOperator;
                    final int isTravelComany = currentTravel.isTravelComany;
                    if (isTravelComany == 1) {
                        evaluationOperator = PARAM_3;
                    } else {
                        evaluationOperator = PARAM_67;
                    }

                    if (evaluationOperator == 0) {
                        call = this.daoTravel.finishPost(travel);
                    } else {
                        call = this.daoTravel.preFinishMobil(travel);
                    }

                    Log.d("Response request", call.request().toString());
                    Log.d("Response request header", call.request().headers().toString());
                    //System.out.println(gson.toJson(travel));


                    call.enqueue(new Callback<InfoTravelEntity>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResponse(Call<InfoTravelEntity> call, Response<InfoTravelEntity> response) {
                            loading.dismiss();
                            Log.d("Response raw header", response.headers().toString());
                            Log.d("Response raw", String.valueOf(response.raw().body()));
                            Log.d("Response code", String.valueOf(response.code()));


                            int evaluationOperator;
                            if (isTravelComany == 1) {
                                evaluationOperator = PARAM_3;
                            } else {
                                evaluationOperator = PARAM_67;
                            }


                            if (evaluationOperator == 0) {
                                Toast.makeText(HomeActivity.this, "VIAJE  FINALIZADO", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(HomeActivity.this, "VIAJE ENVIADO PARA SU APROBACION", Toast.LENGTH_SHORT).show();
                            }


                            btInitVisible(false);
                            btCancelVisible(false);
                            btPreFinishVisible(false);


                            currentTravel = null;
                            HomeFragment.MarkerPoints = null;
                            if (HomeFragment.options != null) {
                                HomeFragment.options.getPoints().clear();
                            }
                            gloval.setGv_travel_current(null);
                            setInfoTravel();
                            viewAlert = false;


                            tiempoTxt = 0;
                            textTiempo.setVisibility(View.INVISIBLE);
                            extraTime = 0;
                            editor.putInt("time_slepp", 0);
                            editor.commit(); // commit changes


                            final LinearLayout lg = (LinearLayout) findViewById(R.id.payment);
                            lg.setVisibility(View.INVISIBLE);

                            gloval.setGv_hour_init_travel(0);// GUARDAMOS LA HORA QUE LO INICIO

                            materialDesignFAM.setVisibility(View.VISIBLE);
                            floatingActionButton1.setEnabled(true);
                        }





                        public void onFailure(Call<InfoTravelEntity> call, Throwable t) {
                            loading.dismiss();
                            Snackbar.make(findViewById(android.R.id.content),
                                    "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                        }
                    });
                } else {
                    loading.dismiss();
                    this.getCurrentTravelByIdDriver();
                }
            } finally {
                this.daoTravel = null;
            }
        }

    }

    public void cliaerNotificationAndoid()
    {
        NotificationManager notifManager= (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancelAll();
    }

    // FIRMA O TARJETA //
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        try {
            switch (requestCode) {
                case SIGNATURE_ACTIVITY:
                    if (resultCode == RESULT_OK) {

                        Bundle bundle = data.getExtras();
                        String status = bundle.getString("status");
                        path_image = bundle.getString("image");
                        filePath = Uri.parse(path_image);

                        if (status.equalsIgnoreCase("done")) {
                            Toast.makeText(this, "Firma Guardada", Toast.LENGTH_SHORT).show();


                            try {
                                //Getting the Bitmap from Gallery
                                // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                                //Setting the Bitmap to ImageView

                                f=new File(path_image, "asremis.jpg");
                                bitmap = BitmapFactory.decodeStream(new FileInputStream(f));

                                postImageData();
                                f.delete();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                case PROFILE_DRIVER_ACTIVITY:
                    super.onActivityResult(requestCode, resultCode, data);
                    break;
                case CREDIT_CAR_ACTIVITY:
                    super.onActivityResult(requestCode, resultCode, data);
                    if (HomeActivity._PAYCREDITCAR_OK) {
                        verifickTravelFinish();
                    }
                    break;
           /* case DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE:
                //Check if the permission is granted or not.
                if (resultCode == RESULT_OK)
                    //If permission granted start floating widget service
                    startFloatingWidgetService();
                else
                    //Permission is not available then display toast
                    Toast.makeText(this,
                            getResources().getString(R.string.draw_other_app_permission_denied),
                            Toast.LENGTH_SHORT).show();*/


            }
        }catch (Exception e){
            Log.d("e",e.getMessage());
        }

    }



    public void preFinihMpago(){

        loading = ProgressDialog.show(HomeActivity.this, "Cargando pago", "Espere unos Segundos...", true, false);
        new SendPostRequest().execute();


/*

        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUriMpago().create(ServicesTravel.class); }

        try {
            loading = ProgressDialog.show(HomeActivity.this, "Enviando pago", "Espere unos Segundos...", true, false);


            PagoEntity PAGO = new PagoEntity(
                    "6182935257674284",
                    "R6nTqEiH4GO0p5b8Z68rUyD7FmQgeAh7",
                    "ARG",
                    totalFinal
            );


            Call<ResponseBody> call = this.daoTravel.payMpago(PAGO);
            Log.d("Response request", call.request().toString());
            Log.d("Response request header", call.request().headers().toString());

            call.enqueue(new Callback<ResponseBody>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    loading.dismiss();

                      GsonBuilder builder = new GsonBuilder();
                     Gson gson = builder.create();
                     System.out.println(gson.toJson(response));

                    ResponseBody result  = (ResponseBody) response.body();
                    HomeActivity.URL_MPAGO = String.valueOf(result);
                    Log.d("URL_MPAGO",HomeActivity.URL_MPAGO);

                }

                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    loading.dismiss();

                    Log.d("ERROR", t.getMessage());
                    Log.d("ERROR", t.getLocalizedMessage());
                    Log.d("ERROR", String.valueOf(call));

                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                }
            });

        }finally {
            this.daoTravel = null;
        }
        */

    }


    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(HttpConexion.BASE_URL+"as_mpago/index.php"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("clienteid", HomeActivity.PARAM_69);
                postDataParams.put("clientesecret", HomeActivity.PARAM_79);
                postDataParams.put("currency_id", HttpConexion.COUNTRY);
                postDataParams.put("unit_price", totalFinal);
                postDataParams.put("agency", gloval.getGv_base_intance());
                postDataParams.put("idTravel", currentTravel.getIdTravel());


                Log.d("postDataParams", String.valueOf(postDataParams));



                String message = postDataParams.toString();

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setFixedLengthStreamingMode(message.getBytes().length);



                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                writer.write(message);
                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {



                    InputStream inputStream = conn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine())!=null){
                        response+= line;
                    }

                    bufferedReader.close();
                    inputStream.close();
                    conn.disconnect();
                    os.close();
                    return response.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {

            loading.dismiss();
            Intent intent = new Intent(getApplicationContext(), PaymentCreditCar.class);
            HomeActivity.mp_jsonPaymentCard = result;
            startActivityForResult(intent, CREDIT_CAR_ACTIVITY);


        }
    }




    public void postImageData()
    {

        uploadImage();
    }

    public String getStringImage(Bitmap bmp)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    /* SERVICIO QUE GUARDA LA FIRMA */
    private void uploadImage()
    {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(HomeActivity.this, "Procesando Firma", "Espere unos Segundos...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

                idPaymentFormKf = 5;
                verifickTravelFinish();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);

                // Get the Image's file name
                String fileNameSegments[] = path_image.split("/");
                String fileName = fileNameSegments[fileNameSegments.length - 1];
                // Put file name in Async Http Post Param which will used in Php web app
                data.put("filename", String.valueOf(currentTravel.getIdTravel()));

                String result = rh.sendPostRequest(UPLOAD_URL, data);

                return result;
            }
        }


        UploadImage ui = new UploadImage();
        ui.execute(bitmap);

    }



    // METODO OBTENER FOTO DE CHOFER //
    public void getPick(int idUserDriver)
    {
        DowloadImg dwImg = new DowloadImg();
        dwImg.execute(HttpConexion.BASE_URL+HttpConexion.base+"/Frond/img_users/"+idUserDriver);

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

            Bitmap imagen = null;
            try{


                Log.i("doInBackground" , "Entra en doInBackground");
                String url = params[0]+".JPEG";
                imagen = descargarImagen(url);
                return imagen;

            }catch (Exception e){
                Log.d("ERROR",e.getMessage());
                return imagen;
            }


        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);


            try {
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

    public double getPriceReturnBylistBeneficion(List<BeneficioEntity> list,double distance){
        double value = 0;

        int indexBrack = 0;
        for (int i =0;i < list.size();i++){

            indexBrack++;

            // EVALUAMOS LOS DISTINTOS BENEFICIOS //
            if(distance >=  Double.parseDouble(list.get(i).getBenefitsFromKm())){
                value = value + Double.parseDouble(list.get(i).getBenefitPreceReturnKm());
            }
        }

        // VALIDAMOS SI EL TOTAL KM ES MAYOR A //
        if(distance > Double.parseDouble(list.get(indexBrack-1).getBenefitsToKm())){
            double distanceExtraBeneficio = distance - Double.parseDouble(list.get(indexBrack-1).getBenefitsToKm());



            double amountExtra =0;
            // ES VIAJE A EMPRESA //
            if(currentTravel.getIsTravelComany() == 1){// EMPRESA
                amountExtra =   distanceExtraBeneficio * currentTravel.getPriceReturn();
            }else{
                amountExtra = distanceExtraBeneficio * PARAM_6;

            }
            value = amountExtra + value;
        }

        return  value;
    }

    public double getPriceBylistBeneficion(List<BeneficioEntity> list,double distance){
        double value = 0;

        int indexBrack = 0;
        for (int i =0;i < list.size();i++){

            indexBrack++;

            // EVALUAMOS LOS DISTINTOS BENEFICIOS //
            if(distance >=  Double.parseDouble(list.get(i).getBenefitsFromKm())){
                value  = value + Double.parseDouble(list.get(i).getBenefitsPreceKm());
            }
            else {
                break;
            }
        }

        // VALIDAMOS SI EL TOTAL KM ES MAYOR A //
        if(distance > Double.parseDouble(list.get(indexBrack-1).getBenefitsToKm())){
            double distanceExtraBeneficio = distance - Double.parseDouble(list.get(indexBrack-1).getBenefitsToKm());


            double amountExtra =0;
            // ES VIAJE A EMPRESA //
            if(currentTravel.getIsTravelComany() == 1){// EMPRESA
                amountExtra = distanceExtraBeneficio * currentTravel.getPriceDitanceCompany();
            }else{
                amountExtra = distanceExtraBeneficio * PARAM_1;

            }

            value = amountExtra + value;
        }

        return  value;
    }

    public  void  checkVersion()
    {


        HttpConexion.setBase(HttpConexion.instance);
        if (this.daoTravel == null) {
            this.apiService = HttpConexion.getUri().create(ServicesLoguin.class);
        }


        try {

            Call<Boolean> call = this.apiService.checkVersion(MainActivity.version);
            Log.d("Call request", call.request().toString());
            Log.d("Call request header", call.request().headers().toString());

            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    Log.d("Response request", call.request().toString());
                    Log.d("Response request header", call.request().headers().toString());
                    Log.d("Response raw header", response.headers().toString());
                    Log.d("Response raw", String.valueOf(response.raw().body()));
                    Log.d("Response code", String.valueOf(response.code()));


                    if (response.code() == 200) {
                        boolean rs = (boolean) response.body();


                        if (!rs) {
                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                            alertDialog.setTitle("Existe Una Nueva version!, Debe Atualizar para poder Disfrutar de los Nuevos Beneficios!");
                            alertDialog.setCancelable(false);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Actualizar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            // Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                            //       Uri.parse("http://as-nube.com/apk.apk"));
                                            //startActivity(browserIntent);

                                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                            } catch (android.content.ActivityNotFoundException anfe) {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                            }
                                        }
                                    });
                            alertDialog.show();


                        }

                    } else {

                        AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                        alertDialog.setTitle("ERROR" + "(" + response.code() + ")");
                        alertDialog.setMessage(response.errorBody().source().toString());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    }


                }

                public void onFailure(Call<Boolean> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                }
            });

        } finally {
            this.apiService = null;

        }


    }

    public void getParam(){
        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }

        Log.d("PARAM_69", "GET PARAM");



        try {

            Call<List<paramEntity>> call = this.daoTravel.getparam();

            Log.d("PARAM_69", call.request().toString());
            Log.d("PARAM_69", call.request().headers().toString());


            call.enqueue(new Callback<List<paramEntity>>() {
                @Override
                public void onResponse(Call<List<paramEntity>> call, Response<List<paramEntity>> response) {

                    Log.d("PARAM_69", response.headers().toString());
                    Log.d("PARAM_69", String.valueOf(response.raw().body()));
                    Log.d("PARAM_69", String.valueOf(response.code()));



                    if (response.code() == 200) {

                        //the response-body is already parseable to your ResponseBody object
                        List<paramEntity> listParam = (List<paramEntity>) response.body();
                        gloval.setGv_param(listParam);
                        setParamLocal();

                        refreshButomPermision();


                    }


                }

                @Override
                public void onFailure(Call<List<paramEntity>> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }
            });

        } finally {
            this.daoTravel = null;

        }
    }

    public void  refreshButomPermision(){
        Log.d("PARAM_79", String.valueOf(PARAM_69.isEmpty()));
        Log.d("PARAM_79", String.valueOf(PARAM_79.isEmpty()));


        if(PARAM_69.isEmpty()  && PARAM_79.isEmpty()){
            Snackbar.make(findViewById(android.R.id.content),
                    "MERCADO PAGO NO CONFIGURADO, LA AGENCIA DEBE CONFIGURAR EL MOTOR DE PAGO!",
                    Snackbar.LENGTH_LONG)
                    .setDuration(9000).show();
        }

        if(PARAM_68 == 1 && PARAM_69.length() > 0){
            btnFinishCar.setEnabled(true);
            btnFinishCar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    idPaymentFormKf = 3;
                    finishTravelCreditCar();
                }
            });
        }else {
            btnFinishCar.setEnabled(false);
        }
    }




}

