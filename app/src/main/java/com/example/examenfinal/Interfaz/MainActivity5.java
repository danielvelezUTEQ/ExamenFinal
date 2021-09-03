package com.example.examenfinal.Interfaz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.examenfinal.R;
import com.example.examenfinal.RecyclerViewAdaptador.RecyclerViewAdaptadorEdicion;
import com.example.examenfinal.Secciones.Edicion;
import com.example.examenfinal.WebServices.Asynchtask;
import com.example.examenfinal.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity5 extends AppCompatActivity implements Asynchtask{
    private RecyclerView recyclerView;
    private RecyclerViewAdaptadorEdicion recyclerViewAdapter;

    Bundle b;
    String id;
    Intent intent;
    ArrayList<Edicion> EdicionList;
    LinearLayoutManager layoutManager;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        b = this.getIntent().getExtras();
        recyclerView = (RecyclerView)findViewById(R.id.rv_editorial);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        id=b.getString("id");
        handleSSLHandshake();
        gsonrevistas(id);
    }

    private void gsonrevistas(String idIs) {
        String url ="https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+idIs;
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url, datos, MainActivity5.this, MainActivity5.this);
        ws.execute("GET");
    }

    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    @Override
    public void processFinish(String result) throws JSONException
    {
        try {
            intent= new Intent(MainActivity5.this, MainActivity5.class);
            b = new Bundle();
            JSONArray jsonlista= new JSONArray(result);
            EdicionList = Edicion.JsonObjectsBuild(jsonlista);
            recyclerViewAdapter= new RecyclerViewAdaptadorEdicion(getApplicationContext(), EdicionList);
            recyclerViewAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    URL =  EdicionList.get(recyclerView.getChildAdapterPosition(v)).getdoi();
                    Uri uri = Uri.parse(URL);
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);
                }
            });
            recyclerView.setAdapter(recyclerViewAdapter);
        }catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }

}