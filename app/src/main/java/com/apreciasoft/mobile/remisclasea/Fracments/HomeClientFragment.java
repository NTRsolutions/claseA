package com.apreciasoft.mobile.remisclasea.Fracments;

import android.Manifest;
        import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
        import android.content.Context;
import android.content.Intent;
        import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
        import android.location.Location;
import android.os.AsyncTask;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.annotation.Nullable;
        import android.support.annotation.RequiresApi;
        import android.support.design.widget.Snackbar;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apreciasoft.mobile.remisclasea.Entity.InfoTravelEntity;
        import com.apreciasoft.mobile.remisclasea.Entity.TravelLocationEntity;
import com.apreciasoft.mobile.remisclasea.Entity.reason;
import com.apreciasoft.mobile.remisclasea.Http.HttpConexion;
        import com.apreciasoft.mobile.remisclasea.R;
        import com.apreciasoft.mobile.remisclasea.Services.ServicesTravel;
import com.apreciasoft.mobile.remisclasea.Util.CallbackActivity;
import com.apreciasoft.mobile.remisclasea.Util.DataParser;
        import com.apreciasoft.mobile.remisclasea.Util.GlovalVar;
import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.location.LocationListener;
        import com.google.android.gms.location.LocationRequest;
        import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.BitmapDescriptor;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
        import com.kofigyan.stateprogressbar.StateProgressBar;
import org.json.JSONObject;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Timer;
        import java.util.TimerTask;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        import static com.apreciasoft.mobile.remisclasea.Activity.HomeClientActivity.currentTravel;
        import static com.apreciasoft.mobile.remisclasea.Activity.HomeClientActivity.gloval;


/**
 * Created by Admin on 04/01/2017.
 */

public class HomeClientFragment extends Fragment implements  OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {

    static GoogleMap mGoogleMap;
    LocationRequest mLocationRequest;
    static GoogleApiClient mGoogleApiClient;
    public static Location mLastLocation;
    List<reason> list = null;
    Integer motivo = 0;
    public  static  String nameLocation;
    public static PolylineOptions options;
    Marker mCurrLocationMarker;
    public static   boolean isFistLocation,visible_progress = true;
    public static ArrayList<LatLng> MarkerPoints;
    public static   Timer timerblink;
    public static Location getmLastLocation() {
        return mLastLocation;
    }
    public static View view;
    public static TextView txtStatus,txtStatus2 = null;
    public static TextView txt_client_info = null;
    public static TextView txt_destination_info = null;
    public static TextView txt_origin_info = null;
    public static TextView txt_km_info = null;
    public static TextView txt_calling_info = null;
    public static TextView txt_domain = null;
    public static TextView txt_amount_info = null;
    public static TextView txt_date_info = null;
    public static ProgressBar progressBarTravel = null;
    public static StateProgressBar stateProgressBar;
    public static  Button btnCnacel;

    public ServicesTravel daoTravel = null;

    public static String[] satusTravel;
    CallbackActivity iCallback;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_home,container,false);





        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_home_client,container,false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }



        blink(); // ACTIVAR EFECTO BLINK



        return view;




    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fr = (MapFragment)getChildFragmentManager().findFragmentById(R.id.gmap);

        if(fr == null) {
            MapFragment  mMap = ((MapFragment) this.getFragmentManager().findFragmentById(R.id.gmap));
            mMap.getMapAsync(this);



        }
        else {
            fr.getMapAsync(this);
        }

        HomeClientFragment.txt_date_info = (TextView) getActivity().findViewById(R.id.txt_date_info);
        HomeClientFragment.txtStatus = (TextView) getActivity().findViewById(R.id.txtStatus);
        HomeClientFragment.txtStatus2 = (TextView) getActivity().findViewById(R.id.txtStatus2);
        HomeClientFragment.txt_client_info = (TextView) getActivity().findViewById(R.id.txt_client_info);
        HomeClientFragment.txt_destination_info = (TextView) getActivity().findViewById(R.id.txt_destination_info);
        HomeClientFragment.txt_origin_info = (TextView) getActivity().findViewById(R.id.txt_origin_info);
        HomeClientFragment.txt_km_info = (TextView) getActivity().findViewById(R.id.txt_km_info);
        HomeClientFragment.txt_amount_info = (TextView) getActivity().findViewById(R.id.txt_amount_info);
        HomeClientFragment.txt_calling_info = (TextView) getActivity().findViewById(R.id.txt_calling_info);
        HomeClientFragment.txt_domain = (TextView) getActivity().findViewById(R.id.txt_domain);


        txtStatus2.setText("SERVICIO ACTIVO");

        stateProgressBar = (StateProgressBar) getActivity().findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.enableAnimationToCurrentState(true);

        satusTravel = new String[]{"Aceptado", "Chofer en camino", "En Curso"};
        stateProgressBar.setStateDescriptionData(satusTravel);

        HomeClientFragment.progressBarTravel = (ProgressBar) getActivity().findViewById(R.id.progressBarTravel);



            setVisibleprogressTravel(false);



        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(recibeNotifiacionSocket, new IntentFilter("update-loaction-driver"));



        btnCnacel = (Button) getActivity().findViewById(R.id.car_notifications_cancel_client2);
        btnCnacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    iCallback.doSomething();
                } catch (Exception e) {
                    e.printStackTrace();
    }


            }


        });


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            iCallback = (CallbackActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
    }




    private BroadcastReceiver recibeNotifiacionSocket = new BroadcastReceiver() {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onReceive(Context context, Intent intent) {
            getDriverMapBidUserSocket();


        }
    };




    public static void setVisibleprogressTravel(Boolean visible){

        HomeClientFragment.visible_progress = visible;


    }


    public static void clearInfo() {
       try{



        HomeClientFragment.txt_client_info.setText("No se cargo informacion");
        HomeClientFragment.txt_calling_info.setText("No se cargo informacion");
        HomeClientFragment.txt_domain.setText("No se cargo informacion");
        HomeClientFragment.txt_destination_info.setText("No se cargo informacion");
        HomeClientFragment.txt_origin_info.setText("No se cargo informacion");
        HomeClientFragment.txt_km_info.setText("0.0Km");
        HomeClientFragment.txt_amount_info.setText("0.0$");
        HomeClientFragment.txt_date_info.setText("--/--/----");
        getPick(-1);

        HomeClientFragment.txtStatus.setVisibility(View.INVISIBLE);
        btnCnacel.setVisibility(View.INVISIBLE);

        setVisibleprogressTravel(false);

        }catch (Exception E){
           Log.d("ERRO",E.getMessage());
       }

    }

    public static void setInfoTravel(InfoTravelEntity currentTravel)
    {



        if(currentTravel != null) {

            if (view != null) {

                setVisibleprogressTravel(true);


                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();


                HomeClientFragment.txt_client_info.setText(currentTravel.getDriver());
                HomeClientFragment.txt_calling_info.setText(currentTravel.getPhoneNumberDriver());
                HomeClientFragment.txt_domain.setText(currentTravel.getInfocar());
                HomeClientFragment.txt_date_info.setText(currentTravel.getMdate().toString());
                HomeClientFragment.txt_destination_info.setText(currentTravel.getNameDestination());
                HomeClientFragment.txt_origin_info.setText(currentTravel.getNameOrigin());
                HomeClientFragment.txt_km_info.setText(currentTravel.getDistanceLabel());
                HomeClientFragment.txtStatus.setText(currentTravel.getNameStatusTravel());


                if (currentTravel.getIdSatatusTravel() == 4 || currentTravel.getIdSatatusTravel() == 5 ) {
                getPick(currentTravel.getIdUserDriver());// PASAMOS EL ID DE EL USUARIO DE EL CHOFER
                }

                HomeClientFragment.txtStatus.setTextColor(Color.parseColor(currentTravel.getClassColorTwo()));

                if (currentTravel.getIdSatatusTravel() == 4 || currentTravel.getIdSatatusTravel() == 1) {// VIAJE EN BUSQUEDA DE CLIENTE
                    btnCnacel.setVisibility(View.VISIBLE);
                }else {
                    btnCnacel.setVisibility(View.INVISIBLE);

                }

                if (currentTravel.getIdSatatusTravel() == 6) {
                    if (currentTravel.getAmountCalculate() != null) {
                        HomeClientFragment.txt_amount_info.setText(currentTravel.getTotalAmount() + "$");
                    }
                } else {

                    if (currentTravel.getAmountCalculate() != null) {
                        HomeClientFragment.txt_amount_info.setText(currentTravel.getAmountCalculate() + "$");
                    }
                }


                switch (currentTravel.getIdSatatusTravel()) {
                    case 1:

                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                        break;
                    case 4:

                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        break;
                    case 5:


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);

                            }
                        }, 2000);


                        break;
                    case 6:
                        setVisibleprogressTravel(false);
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);

                        break;
                    default:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                        setVisibleprogressTravel(false);

                }


            }
            else {
                setVisibleprogressTravel(false);

            }
        }


    }

    // METODO OBTENER FOTO DE CHOFER //
    public static void getPick(int idUserDriver)
    {
           // HomeClientFragment.DowloadImg dwImg = new HomeClientFragment.DowloadImg();
            //dwImg.execute(HttpConexion.BASE_URL + HttpConexion.base + "/Frond/img_users/" + idUserDriver);


    }

    public static class DowloadImg extends AsyncTask<String, Void, Bitmap> {



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

            ImageView your_imageView = (ImageView) view.findViewById(R.id.img_face_client);
           // ImageView your_imageView2 = (ImageView) view.findViewById(R.id.img_face_client2);

            if(result != null)
            {
                your_imageView.setImageBitmap(result);
             //   your_imageView2.setImageBitmap(result);

            }else
            {
                your_imageView.setImageResource(R.drawable.noimg);
                //your_imageView2.setImageResource(R.drawable.noimg);
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



    @Override
    public void onPause() {
        super.onPause();

       /* try {
            //stop location updates when Activity is no longer active
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }

        }catch (Exception e)
        {
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }*/


    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {



            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMapToolbarEnabled(false);


                if (MarkerPoints != null) {
                    MarkerPoints.clear();

                   if(mGoogleMap != null)
                   {
                       mGoogleMap.clear();
                   }

                }

            } else {
                //Request Location Permission
                checkLocationPermission();
                if (MarkerPoints != null) {
                    MarkerPoints.clear();

                    if(mGoogleMap != null)
                    {
                        mGoogleMap.clear();
                    }

                }
            }

        }
        else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMapToolbarEnabled(false);


            if (MarkerPoints != null) {
                MarkerPoints.clear();

                if(mGoogleMap != null)
                {
                    mGoogleMap.clear();
                }

            }
        }

        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }


        // Setting onclick event listener for the map
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {


            }
        });






    }



    private static String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    protected synchronized void buildGoogleApiClient() {

        try
        {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }catch (Exception e)
        {
            Log.d("Error",e.getMessage());
        }

    }

    @Override
    public void onConnected(Bundle bundle) {
        try {


            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(1000);
            mLocationRequest.setFastestInterval(1000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);



                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                if (mLastLocation != null) {

                    LatLng loc = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(18));

                }



            }
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }



    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    @Override
    public void onLocationChanged(Location location)
    {

        Log.d("onLocationChanged","onLocationChanged");

        if(getActivity() != null) {

            Geocoder gCoder = new Geocoder(getActivity());
            List<android.location.Address> addresses = null;


            try {
                addresses = gCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

/*
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
*/
                android.location.Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                String strAdd = returnedAddress.getAddressLine(0);
                HomeClientFragment.nameLocation = strAdd.toString();



                mLastLocation = location;
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


                if (isFistLocation) {
                    //move map camera
                    isFistLocation = false;

                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
                }


                addDriverMap();// actualizamos el la ubicacion del chofer



            } catch (IOException e) {
                Log.d("ERROR", e.getMessage());
            }
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getActivity().getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);

                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity().getApplicationContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public    void addDriverMap()
    {

        try {


         GlovalVar gloval;

        // variable global //
            gloval = ((GlovalVar) getActivity().getApplicationContext());

        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        if (mGoogleMap != null) {
            mGoogleMap.clear();
        }

         if (gloval.getLocationDriverFromClient() != null) {

                Log.d("addDriverMap","addDriverMap");



             TravelLocationEntity info = gloval.getLocationDriverFromClient();


             //Place current location marker
             LatLng latLng = new LatLng(Double.parseDouble(info.getLatLocation()),
                     Double.parseDouble(info.getLongLocation()));
             MarkerOptions markerOptions = new MarkerOptions();
             markerOptions.position(latLng);
                markerOptions.title(info.getLocation());


             int height = 45;
             int width = 40;
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.auto);
                Bitmap b = bitmapdraw.getBitmap();
             Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

            /* BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.auto);*/
             markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

             mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
         }

            if (mLastLocation != null) {


            //Place current location marker
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Usted esta Aqui ");


            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.persona);
            markerOptions.icon(icon);

            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
        }
        }catch (Exception e){
            Log.d("EROR",e.getMessage());
    }
                }



    public static float calculateMiles() {
        float totalDistance = 0;



        Log.d("valuePrama", String.valueOf(options.getPoints().size()));
        for(int i = 1; i < options.getPoints().size(); i++) {


            Log.d("valuePrama", String.valueOf(i));
            Location currLocation = new Location("this");
            currLocation.setLatitude(options.getPoints().get(i).latitude);
            currLocation.setLongitude(options.getPoints().get(i).longitude);

            Location lastLocation = new Location("this");
            lastLocation.setLatitude(options.getPoints().get(i-1).latitude);
            lastLocation.setLongitude(options.getPoints().get(i-1).longitude);

            totalDistance += lastLocation.distanceTo(currLocation);
        }
        return totalDistance;
    }

    // Fetches data from url passed
    private static class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        /**
         * A method to download json data from url
         */
        private String downloadUrl(String strUrl) throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                urlConnection.connect();

                // Reading data from url
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();
                Log.d("downloadUrl", data.toString());
                br.close();

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            } finally {
                iStream.close();
                urlConnection.disconnect();
            }
            return data;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }

    }

    private static class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.BLUE);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mGoogleMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }


    private void blink(){

        try {

            timerblink = new Timer();
            timerblink.schedule(new TimerTask() {
                @Override
                public void run() {


                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                if (getActivity() != null) {

                                    if (getActivity().findViewById(R.id.txtStatus) != null &&
                                            getActivity().findViewById(R.id.txtStatus) != null) {

                                        TextView txt = (TextView) getActivity().findViewById(R.id.txtStatus);
                                        TextView txt2 = (TextView) getActivity().findViewById(R.id.txtStatus2);

                                        if (HomeClientFragment.visible_progress) {

                                            if (txt.getVisibility() == View.VISIBLE) {
                                                txt.setVisibility(View.INVISIBLE);
                                            } else {
                                                txt.setVisibility(View.VISIBLE);
                                            }
                                            txt2.setVisibility(View.INVISIBLE);
                                            HomeClientFragment.stateProgressBar.setVisibility(View.VISIBLE);
                                            HomeClientFragment.progressBarTravel.setVisibility(View.VISIBLE);
                                        }


                                        if (!HomeClientFragment.visible_progress) {

                                            if (txt2 != null) {

                                                if (txt2.getVisibility() == View.VISIBLE) {
                                                    txt2.setVisibility(View.INVISIBLE);
                                                } else {
                                                    txt2.setVisibility(View.VISIBLE);
                                                }
                                            }

                                            if (txt != null) {
                                                txt.setVisibility(View.INVISIBLE);
                                            }
                                            stateProgressBar.setVisibility(View.INVISIBLE);
                                            progressBarTravel.setVisibility(View.INVISIBLE);

                                        }
                                    }
                                }
                            }
                        });
                    }


                }
            }, 0, 1000);
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
        }


    }


    public  void  getDriverMapBidUserSocket()
    {

        if (this.daoTravel == null) { this.daoTravel = HttpConexion.getUri().create(ServicesTravel.class); }


        try {

            if(currentTravel != null) {

            Call<TravelLocationEntity> call = null;
            call = this.daoTravel.getDriverMapBiIdTravel(currentTravel.getIdTravel());


            call.enqueue(new Callback<TravelLocationEntity>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<TravelLocationEntity> call, Response<TravelLocationEntity> response) {


                    TravelLocationEntity TRAVEL = (TravelLocationEntity) response.body();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    gloval.setLocationDriverFromClient(TRAVEL);
                    addDriverMap();


                }

                public void onFailure(Call<TravelLocationEntity> call, Throwable t) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                                "ERROR (" + t.getMessage() + ")", Snackbar.LENGTH_LONG).show();
                }


            });
            }

        } finally {
            this.daoTravel = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            //stop location updates when Activity is no longer active
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }
        }catch (Exception e) {
            Log.d("ERROR", e.getMessage());
        }
    }



}