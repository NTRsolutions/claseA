package com.apreciasoft.admin.remisclasea.Util;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.apreciasoft.admin.remisclasea.Http.HttpConexion;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Admin on 08/01/2017.
 */

public class WsTravel extends Activity {

    //public  WebSocketClient mWebSocketClient;
    public static Socket mWebSocketClient;
    public static String URL_SOCKET;
    public static String MY_EVENT = "message";

    public GlovalVar gloval;

    public  void connectWebSocket(int idUser) {

        try{
        /* Instance object socket */

            WsTravel.URL_SOCKET =  HttpConexion.PROTOCOL+"://"+HttpConexion.ip+":"+HttpConexion.portWsCliente+"?idUser="+idUser+"&uri="+ HttpConexion.base;

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            IO.setDefaultSSLContext(sc);
            //HttpsURLConnection.setDefaultHostnameVerifier(new RelaxedHostNameVerifier());


            IO.Options options = new IO.Options();
            options.sslContext = sc;
            options.secure = true;
            options.port = HttpConexion.portWsCliente;

            if(mWebSocketClient == null) {
                Log.d("SOCKET IO","va a conectar: "+URL_SOCKET);
                mWebSocketClient = IO.socket(URL_SOCKET, options);


            }






            mWebSocketClient.on(Socket.EVENT_CONNECT, new Emitter.Listener(){
                @Override
                public void call(Object... args) {
                /* Our code */
                    Log.d("SOCKET IO","CONECT");


                    // variable global //
                   // gloval = ((GlovalVar) HomeClientActivity.gloval);

                    //gloval.setLocationDriverFromClient("");

                }
            }).on(mWebSocketClient.EVENT_DISCONNECT, new Emitter.Listener(){
                @Override
                public void call(Object... args) {
                /* Our code */
                    Log.d("SOCKET IO","DISCONESCT");
                }
            }).on(Socket.EVENT_RECONNECT_ERROR, new Emitter.Listener(){
                @Override
                public void call(Object... args) {
             /* Our code */
                    Log.d("SOCK IO","EVENT_RECONNECT_ERROR");


                }
            }).on(MY_EVENT, new Emitter.Listener(){
                @Override
                public void call(Object... args) {
                        /* Our code */
                    Log.d("SOCK IO","NOTIFICO");


                    Intent intent = new Intent("update-loaction-driver");
                    LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);


                }
            });




            mWebSocketClient.connect();

        }catch (URISyntaxException e){
            Log.d("SOCK IO",e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.d("SOCK IO",e.getMessage());
        } catch (KeyManagementException e) {
            Log.d("SOCK IO",e.getMessage());
        }


    }

    private TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[] {};
        }

        public void checkClientTrusted(X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }
    } };



    public void coseWebSocket() {
        mWebSocketClient.disconnect();
        mWebSocketClient.close();
        mWebSocketClient = null;
        Log.d("SOCKET IO","closee");
    }


}
