package com.apreciasoft.mobile.remisclasea.Fracments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.apreciasoft.mobile.remisclasea.Entity.DriverFull;
import com.apreciasoft.mobile.remisclasea.Entity.driver;
import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;
import com.apreciasoft.mobile.remisclasea.R;
import com.apreciasoft.mobile.remisclasea.Services.ServicesDriver;
import com.apreciasoft.mobile.remisclasea.Util.GlovalVar;
import com.apreciasoft.mobile.remisclasea.Util.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by usario on 27/4/2017.
 */

public class ProfileDriverFr  extends Fragment {

    public  Bitmap bitmapImage = null;
    public  Bitmap bitmap = null;
    public GlovalVar gloval;
    private View myView;
    public static final String UPLOAD_URL =  HttpConexion.BASE_URL+HttpConexion.base+"/Frond/safeimgDriver.php";
    public static final String UPLOAD_KEY = "image";
    ServicesDriver daoDriver = null;
    public ProgressDialog loading;
    public boolean changePick = false;
    public EditText title1;
    public EditText title2;
    public EditText title3;
    public EditText title4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_profile_driver, container, false);

        // BUSCAR FOTO EN GALERIA //
        /*Button btnAction = (Button) myView.findViewById(R.id.btnPic);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGallery();

            }
        });*/


        ImageView img = (ImageView) myView.findViewById(R.id.imgView);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGallery();
            }
        });


        // ENVIAR FOTO A EL SERVIDOR //
        Button btnSafePick = (Button) myView.findViewById(R.id.btnSafePick);
        btnSafePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                safePick();

            }
        });

        // ACTUALIZAR INFOR //
        Button btnSafeInfo = (Button) myView.findViewById(R.id.safe_info);
        btnSafeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                safeInfo();

            }
        });

        // variable global //
        gloval = ((GlovalVar)getActivity().getApplicationContext());


        title1 = (EditText) myView.findViewById(R.id.txt_name);
        title2 = (EditText) myView.findViewById(R.id.txt_dni);
        title3 = (EditText) myView.findViewById(R.id.txt_mail);
        title4 = (EditText) myView.findViewById(R.id.txt_phone);


        getPick();

        return myView;
    }


    public  void    getInfoDriver()
    {

        if (this.daoDriver == null) { this.daoDriver = HttpConexion.getUri().create(ServicesDriver.class); }


        try {

            Call<DriverFull> call = this.daoDriver.find(gloval.getGv_id_driver());
            Log.d("Call request", call.request().toString());
            Log.d("Call request header", call.request().headers().toString());

            call.enqueue(new Callback<DriverFull>() {
                @Override
                public void onResponse(Call<DriverFull> call, Response<DriverFull> response) {

                    Log.d("Response request", call.request().toString());
                    Log.d("Response request header", call.request().headers().toString());
                    Log.d("Response raw header", response.headers().toString());
                    Log.d("Response raw", String.valueOf(response.raw().body()));
                    Log.d("Response code", String.valueOf(response.code()));



                    if (response.code() == 200) {
                        DriverFull rs = (DriverFull) response.body();
                        title1.setText(rs.getDriver().getFisrtNameDriver());
                        title2.setText(rs.getDriver().getDniDriver());
                        title3.setText(rs.getDriver().getEmailDriver());
                        title4.setText(rs.getDriver().getPhoneNumberDriver());
                    }


                }

                public void onFailure(Call<DriverFull> call, Throwable t) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();
                }
            });

        } finally {
            this.daoDriver = null;

        }

    }


    // METODO OBTENER FOTO DE CHOFER //
    public void getPick()
    {

        try {

            DowloadImg dwImg = new DowloadImg();
            dwImg.execute(HttpConexion.BASE_URL + HttpConexion.base + "/Frond/img_users/" + gloval.getGv_user_id());
        }catch (Exception E)
        {
            Log.d("ERROR", String.valueOf(E));
        }

        getInfoDriver();

    }

    // METODO PARA ACTUALIZAR LA FOTO //
    public void safePick()
    {
        if(changePick == true)
        {
            uploadImage();
         }


    }

    // METODO PARA EDITAR LA IFORMACION//
    public void safeInfo()
    {



        if (this.daoDriver == null) { this.daoDriver = HttpConexion.getUri().create(ServicesDriver.class); }

        driver dr =  new driver(gloval.getGv_id_driver(),title1.getText().toString(),
                title2.getText().toString(),title4.getText().toString(),title3.getText().toString(),gloval.getGv_user_id());


        loading = ProgressDialog.show(getActivity(), "Actualizando", "Espere...", true, false);


        try {

            Call<driver> call = this.daoDriver.updateLiteMobil(dr);
            Log.d("Call request", call.request().toString());
            Log.d("Call request header", call.request().headers().toString());


            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(dr));

            call.enqueue(new Callback<driver>() {
                @Override
                public void onResponse(Call<driver> call, Response<driver> response) {
                    //  GsonBuilder builder = new GsonBuilder();
                    // Gson gson = builder.create();
                    // System.out.println(gson.toJson(response));
                    if (response.code() == 200) {

                        driver rs = response.body();


                        title1.setText(rs.getFisrtNameDriver());
                        title2.setText(rs.getDniDriver());
                        title3.setText(rs.getEmailDriver());
                        title4.setText(rs.getPhoneNumberDriver());

                        Toast.makeText(getActivity(), "Datos Actualizados", Toast.LENGTH_SHORT).show();


                        loading.dismiss();
                    }

                    else if (response.code() == 400)  {

                        loading.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
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
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
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

                @Override
                public void onFailure(Call<driver> call, Throwable t) {
                    loading.dismiss();
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "ERROR ("+t.getMessage()+")", Snackbar.LENGTH_LONG).show();


                }

            });

        }finally {
            this.daoDriver = null;
        }
    }


    private void startGallery() {


        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
            changePick = true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 1000) {


                Uri returnUri = data.getData();

                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageView your_imageView = (ImageView) myView.findViewById(R.id.imgView);
                your_imageView.setImageBitmap(bitmapImage);
            }
        }
    }


    /* SERVICIO QUE GUARDA LA FOTO */
    private void uploadImage()
    {
        Log.d("upload img","up img");

        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Actualizando Foto", "Espere unos Segundos...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Log.d("error",s);
                Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {


                String uploadImage = getStringImage(bitmapImage);

                HashMap<String, String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);


                data.put("filename", String.valueOf(gloval.getGv_user_id()));

                String result = rh.sendPostRequest(UPLOAD_URL, data);

                return result;
            }
        }


        UploadImage ui = new UploadImage();
        ui.execute(bitmap);

    }

    public String getStringImage(Bitmap bmp)
    {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    /* DESCARGAR IMAGEN */
    public class DowloadImg extends AsyncTask<String, Void, Bitmap> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Cargando Imagen");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

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


            ImageView your_imageView = (ImageView) myView.findViewById(R.id.imgView);

           if(result != null)
           {
               your_imageView.setImageBitmap(result);

           }else
           {
               your_imageView.setImageResource(R.drawable.noimg);
           }

            pDialog.dismiss();

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
    /*******************/
}
