package com.apreciasoft.admin.remisclasea.Activity;

        import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.graphics.Color;
        import android.graphics.Typeface;
import android.net.Uri;
        import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
        import android.support.design.widget.Snackbar;
        import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

        import com.apreciasoft.admin.remisclasea.Entity.VehicleType;
        import com.apreciasoft.admin.remisclasea.Entity.login;
        import com.apreciasoft.admin.remisclasea.Entity.paramEntity;
        import com.apreciasoft.admin.remisclasea.Entity.user;
import com.apreciasoft.admin.remisclasea.Entity.userFull;
import com.apreciasoft.admin.remisclasea.Fracments.RegisterForm;
import com.apreciasoft.admin.remisclasea.Http.HttpConexion;
import com.apreciasoft.admin.remisclasea.R;
import com.apreciasoft.admin.remisclasea.Services.ServicesLoguin;
import com.apreciasoft.admin.remisclasea.Util.GlovalVar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
        import com.google.gson.reflect.TypeToken;
        import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.apreciasoft.admin.remisclasea.Util.Utils.verificaConexion;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "NOTICIAS";
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    protected PowerManager.WakeLock wakelock;
    public static String version = "2.0.13";
    public ProgressDialog loading;
    ServicesLoguin apiService = null;
    public  GlovalVar gloval = null;
    public static final int REGISTER_ACTIVITY = 1;
    public  SharedPreferences.Editor editor;
    public   SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        this.gloval = ((GlovalVar) getApplicationContext());
        pref = getApplicationContext().getSharedPreferences(HttpConexion.instance, 0);



        if( checkAndRequestPermissions() == true) {


            //  mamelow
           // isStoragePermissionGranted();

            //

            if (pref.getBoolean("isLoged", false) == true) {



                gloval.setGv_logeed(true);
                gloval.setGv_user_id(pref.getInt("user_id", 0));
                gloval.setGv_idResourceSocket(pref.getString("is_resourceSocket", ""));
                gloval.setGv_id_cliet(pref.getInt("client_id", 0));
                gloval.setGv_id_profile(pref.getInt("profile_id", 0));
                gloval.setGv_id_driver(pref.getInt("driver_id", 0));
                gloval.setGv_user_mail(pref.getString("user_mail", ""));
                gloval.setGv_user_name(pref.getString("user_name", ""));
                gloval.setGv_base_intance(pref.getString("instance", ""));
                gloval.setGv_nr_driver(pref.getString("nrDriver", ""));
                Gson gson = new Gson();

                TypeToken<List<paramEntity>> token3 = new TypeToken<List<paramEntity>>() {};
                List<paramEntity> listParam = gson.fromJson(pref.getString("param", ""), token3.getType());
                gloval.setGv_param(listParam);

                TypeToken<List<VehicleType>> token2 = new TypeToken<List<VehicleType>>() {};
                List<VehicleType> vehicleTypenew = gson.fromJson(pref.getString("list_vehichle", ""), token2.getType());
                gloval.setGv_listvehicleType(vehicleTypenew);


                HttpConexion.setBase(pref.getString("instance", ""));


            }
        }

        if(gloval.getGv_logeed() == true )
        {



            if(gloval.getGv_id_profile() == 2 || gloval.getGv_id_profile() == 5)
            {
                // LAMAMOS A EL SEGUNDO ACTIVITY DE HOME CIENT//
                Intent homeClient = new Intent(MainActivity.this, HomeClientActivity.class);
                startActivity(homeClient);
            }else if(gloval.getGv_id_profile() == 3)
            {
                // LAMAMOS A EL SEGUNDO ACTIVITY//
                Intent home = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(home);
            }
        }else
        {
            setContentView(R.layout.activity_main);

            if (!verificaConexion(this)) {
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


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                // only for gingerbread and newer versions

                checkAndRequestPermissions();
            }


            //evitar que la pantalla se apague
            final PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
            this.wakelock=pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
            wakelock.acquire();


            // ESTO PERMITE QUE CUANDO LA APP ESTE EN SEGUNDO PLADONO Y LA NOTIFICACION LLEGUE SI LÑE DA CLICK ABRAE LA APP EN HOME ACTIVITY
            if (getIntent().getExtras() != null) {
                // Call your NotificationActivity here..
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
            else
            {
                HttpConexion.setBase(HttpConexion.instance);



                final Button btnLogin = (Button) findViewById(R.id.btn_login);
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        checkVersion();
                    }
                });

                final Button btn_newacount =  findViewById(R.id.btn_newacount);
                btn_newacount.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        openFromRegister();
                    }
                });


                TextView txtVersion = findViewById(R.id.lbl_version);
                txtVersion.setText("V: "+version);
            }



        }



    }


    private  boolean checkAndRequestPermissions() {
        int camerapermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int writepermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionLocation = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionRecordAudio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);


        List<String> listPermissionsNeeded = new ArrayList<>();

        /*if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
            Log.d("P-)","01");
        }*/
        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            Log.d("P-)","02");
        }
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            Log.d("P-)","03");
        }
      /* if (permissionRecordAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
            Log.d("P-)","04");
        }*/
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d(TAG, "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "sms & location services permission granted");
                        // process the normal flow
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d(TAG, "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                            showDialogOK("Service Permissions are required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                    finish();
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            //explain("Necesitamos algunos permisos Para esta Aplicacion");
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }
    private void explain(String msg){
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        //  permissionsclass.requestPermission(type,code);
                        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.apreciasoftdemo.parsaniahardik.marshmallowpermission")));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }

    public  void  checkVersion()
    {

        if (this.apiService == null) { this.apiService = HttpConexion.getUri().create(ServicesLoguin.class); }


        try {
            loading = ProgressDialog.show(MainActivity.this, "Autentificando", "Espere unos Segundos...", true, false);



            Call<Boolean> call = this.apiService.checkVersion(version);
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
                            loguin();
                        } else {
                            loading.dismiss();
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Existe Una Nueva version!, Debe Atualizar para poder Disfrutar de los Nuevos Beneficios!");

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

                            final Button btnLogin = (Button) findViewById(R.id.btn_login);
                            btnLogin.setEnabled(false);

                        }

                    } else {

                        loading.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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

                    loading.dismiss();
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }
            });

        } finally {
            this.apiService = null;

        }

    }

    public void loguin() {

        if (this.apiService == null) { this.apiService = HttpConexion.getUri().create(ServicesLoguin.class); }

        EditText mUser = (EditText) findViewById(R.id.user_txt);
        EditText mPass = (EditText) findViewById(R.id.pass_text);

        login auth = new login(new user(mUser.getText().toString(), mPass.getText().toString(),2));

        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(auth));

            Call<userFull> call = this.apiService.getUser(auth);
            Log.d("Call request", call.request().toString());
            Log.d("Call request header", call.request().headers().toString());



            call.enqueue(new Callback<userFull>() {
                @Override
                public void onResponse(Call<userFull> call, Response<userFull> response) {
                    //  GsonBuilder builder = new GsonBuilder();
                    // Gson gson = builder.create();
                    // System.out.println(gson.toJson(response));
                    if (response.code() == 200) {

                        userFull userLogued = response.body();

                        if(userLogued.response.getUser().getIdProfileUser() == 2 ||
                                userLogued.response.getUser().getIdProfileUser() == 5 ||
                                userLogued.response.getUser().getIdProfileUser() == 3){

                        if (!userLogued.response.isDriverInactive()) {


                            gloval.setGv_user_id(userLogued.response.getUser().getIdUser());
                            gloval.setGv_user_mail(userLogued.response.getUser().getEmailUser());

                            gloval.setGv_user_name(userLogued.response.getUser().getFirstNameUser() + " "
                                    + userLogued.response.getUser().getLastNameUser());

                            gloval.setGv_id_cliet(userLogued.response.getUser().getIdClient());
                            gloval.setGv_id_driver(userLogued.response.getUser().getIdDriver());
                            gloval.setGv_id_profile(userLogued.response.getUser().getIdProfileUser());
                            gloval.setGv_id_vehichle(userLogued.response.getUser().getIdVeichleAsigned());
                            gloval.setGv_travel_current(userLogued.response.getCurrentTravel());
                            gloval.setGv_param(userLogued.response.getParam());
                            gloval.setGv_logeed(true);
                            //gloval.setGv_driverinfo(userLogued.response.getDriver());
                            gloval.setGv_clientinfo(userLogued.response.getClient());
                            gloval.setGv_listvehicleType(userLogued.response.getListVehicleType());

                            gloval.setGv_idResourceSocket(userLogued.response.getUser().getIdResourceSocket());

                            HttpConexion.setBase(userLogued.response.getInstance());
                            // SETEAMOS LA INTANCIA PARA AUTENTICARNOS


                            if (userLogued.response.getUser().getIdProfileUser() == 2
                                    || userLogued.response.getUser().getIdProfileUser() == 5) {
                                // LAMAMOS A EL SEGUNDO ACTIVITY DE HOME CIENT//
                                Intent homeClient = new Intent(MainActivity.this, HomeClientActivity.class);
                                startActivity(homeClient);
                            } else {

                                gloval.setGv_srviceActive(userLogued.response.getDriver().getIdStatusDriverTravelKf());
                                gloval.setGv_nr_driver(userLogued.response.getDriver().getNrDriver());


                                if (userLogued.response.getCurrentTravel() != null) {
                                    gloval.setGv_travel_current(userLogued.response.getCurrentTravel());
                                }
                                // LAMAMOS A EL SEGUNDO ACTIVITY//
                                Intent home = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(home);
                            }


                            /** PREFERENCIAS LOCALES **/
                            pref = getApplicationContext().getSharedPreferences(HttpConexion.instance, 0); // 0 - for private mode
                            editor = pref.edit();

                            Gson gson = new Gson();

                            editor.putBoolean("isLoged", true);
                            editor.putInt("user_id", gloval.getGv_user_id());
                            editor.putString("is_resourceSocket", gloval.getGv_idResourceSocket());
                            editor.putInt("client_id", gloval.getGv_id_cliet());
                            editor.putInt("profile_id", gloval.getGv_id_profile());
                            editor.putInt("driver_id", gloval.getGv_id_driver());
                            editor.putString("user_mail", gloval.getGv_user_mail());
                            editor.putString("user_name", gloval.getGv_user_name());
                            editor.putString("instance", gloval.getGv_base_intance());
                            editor.putString("param", gson.toJson(gloval.getGv_param()));
                            editor.putString("list_vehichle", gson.toJson(gloval.getGv_listvehicleType()));
                            editor.putString("nrDriver", gloval.getGv_nr_driver());
                            editor.putInt("time_slepp", 0);
                            editor.commit(); // commit changes
                            /************************/

                            loading.dismiss();

                        } else {
                            loading.dismiss();
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Informacion");
                            alertDialog.setMessage("Usuario/Inactivo");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            HttpConexion.setBase(HttpConexion.instance);

                        }

                    }else {
                            loading.dismiss();
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("Informacion");
                            alertDialog.setMessage("Usuario/contraseña Invalida");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            HttpConexion.setBase(HttpConexion.instance);

                        }



                    }  else if (response.code() == 203)  {
                        loading.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Informacion");
                        alertDialog.setMessage("Usuario/contraseña Invalida");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        HttpConexion.setBase(HttpConexion.instance);


                }  else if (response.code() == 212)  {
                    loading.dismiss();
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Informacion");
                    alertDialog.setMessage("Usuario Inactivado");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                        HttpConexion.setBase(HttpConexion.instance);

                }
                    else if (response.code() == 400)  {

                        loading.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Informacion" + "(" + response.code() + ")");
                        alertDialog.setMessage(response.errorBody().source().toString());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }else {

                        loading.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("ERROR" + "(" + response.code() + ")");
                        alertDialog.setMessage(response.errorBody().source().toString());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        HttpConexion.setBase(HttpConexion.instance);
                    }
                }

                @Override
                public void onFailure(Call<userFull> call, Throwable t) {
                    loading.dismiss();
                    Snackbar.make(findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                    HttpConexion.setBase(HttpConexion.instance);


                }

            });

        }finally {
            this.apiService = null;
        }
    }

    @Override
    public void onBackPressed() {
    }

    public  void  openFromRegister()
    {
        /*Intent intent = new Intent(getApplicationContext(), RegisterCliente.class);
        startActivityForResult(intent, REGISTER_ACTIVITY);*/

        Intent intent = new Intent(getApplicationContext(), RegisterForm.class);
        startActivityForResult(intent, REGISTER_ACTIVITY);
    }
}
