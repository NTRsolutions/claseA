package com.apreciasoft.admin.asremis.Fracments;

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

import com.apreciasoft.admin.asremis.Entity.client;
import com.apreciasoft.admin.asremis.Http.HttpConexion;
import com.apreciasoft.admin.asremis.R;
import com.apreciasoft.admin.asremis.Services.ServicesLoguin;
import com.apreciasoft.admin.asremis.Util.GlovalVar;
import com.apreciasoft.admin.asremis.Util.RequestHandler;
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
 * Created by usario on 29/4/2017.
 */

public class ProfileClientFr extends Fragment {

    public Bitmap bitmapImage = null;
    public  Bitmap bitmap = null;
    public GlovalVar gloval;
    private View myView;
    public static final String UPLOAD_URL =  HttpConexion.BASE_URL+HttpConexion.base+"/Frond/safeimgDriver.php";
    public static final String UPLOAD_KEY = "image";
    ServicesLoguin daoAuth = null;
    public ProgressDialog loading;
    public boolean changePick = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_profile_company, container, false);

        // BUSCAR FOTO EN GALERIA //
        Button btnAction = (Button) myView.findViewById(R.id.btnPic);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
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


        getPick();

        return myView;
    }


    // METODO OBTENER FOTO DE CLIENTE //
    public void getPick()
    {
        final EditText title1 = (EditText) myView.findViewById(R.id.txt_name);
        final EditText title2 = (EditText) myView.findViewById(R.id.txt_dni);
        final EditText title3 = (EditText) myView.findViewById(R.id.txt_mail);
        final EditText title4 = (EditText) myView.findViewById(R.id.txt_phone);
        final EditText title1_1 = (EditText) myView.findViewById(R.id.txt_last_name);

        title1.setText(gloval.getGv_clientinfo().getFirtNameClient());
        title2.setText(gloval.getGv_clientinfo().getDniClient());
        title3.setText(gloval.getGv_clientinfo().getMailClient());
        title4.setText(gloval.getGv_clientinfo().getPhoneClient());
        title1_1.setText(gloval.getGv_clientinfo().getLastNameClient());


        ProfileClientFr.DowloadImg dwImg = new ProfileClientFr.DowloadImg();
        dwImg.execute(HttpConexion.BASE_URL+HttpConexion.base+"/Frond/img_users/"+gloval.getGv_user_id());

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
        final EditText title1 = (EditText) myView.findViewById(R.id.txt_name);
        final EditText title1_1 = (EditText) myView.findViewById(R.id.txt_last_name);
        final EditText title2 = (EditText) myView.findViewById(R.id.txt_dni);
        final EditText title3 = (EditText) myView.findViewById(R.id.txt_mail);
        final EditText title4 = (EditText) myView.findViewById(R.id.txt_phone);



        if (this.daoAuth == null) { this.daoAuth = HttpConexion.getUri().create(ServicesLoguin.class); }

        client cr =  new client(gloval.getGv_id_cliet(),title1.getText().toString(),title1_1.getText().toString(),
                title2.getText().toString(),title4.getText().toString(),title3.getText().toString(),gloval.getGv_user_id());


        loading = ProgressDialog.show(getActivity(), "Actualizando", "Espere...", true, false);


        try {

            Call<client> call = this.daoAuth.updateClientLiteMobil(cr);
            Log.d("Call request", call.request().toString());
            Log.d("Call request header", call.request().headers().toString());


            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(cr));

            call.enqueue(new Callback<client>() {
                @Override
                public void onResponse(Call<client> call, Response<client> response) {
                    //  GsonBuilder builder = new GsonBuilder();
                    // Gson gson = builder.create();
                    // System.out.println(gson.toJson(response));
                    if (response.code() == 200) {

                        client rs = response.body();


                        gloval.setGv_clientinfo(rs);
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
                public void onFailure(Call<client> call, Throwable t) {
                    loading.dismiss();
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("ERROR");
                    alertDialog.setMessage(t.getMessage());

                    Log.d("ERRO", String.valueOf(t));
                    Log.d("ERRO", String.valueOf(t.getLocalizedMessage()));

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();


                }

            });

        }finally {
            this.daoAuth = null;
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
