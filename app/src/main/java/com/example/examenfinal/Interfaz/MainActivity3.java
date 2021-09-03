package com.example.examenfinal.Interfaz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenfinal.R;
import com.example.examenfinal.RecyclerViewAdaptador.RecyclerViewAdaptadorCategoria;
import com.example.examenfinal.Secciones.Edicion;
import com.example.examenfinal.Secciones.CategoriasCarreras;
import org.json.JSONArray;
import org.json.JSONException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity3 extends AppCompatActivity{
    RecyclerView rvItem;
    LinearLayoutManager layoutManager;
    Bundle bundle;
    List<Edicion> edicionList;
    String sectionid,section;
    ImageView imagencategoria;
    Intent intent;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        rvItem =  findViewById(R.id.rv_editorial);
        //imagencategoria=findViewById(R.id.img);
        rvItem.setLayoutManager(new LinearLayoutManager(MainActivity3.this));
        queue= Volley.newRequestQueue(MainActivity3.this);
        bundle=this.getIntent().getExtras();
        layoutManager= new LinearLayoutManager(MainActivity3.this);
        id=bundle.getString("id");
        handleSSLHandshake();
        gsoncategoria(id);
    }
    private RequestQueue queue;
    List<CategoriasCarreras> categoriaList;

    private void gsoncategoria(String sectionid){
        final String urllg="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+sectionid;
        intent= new Intent(MainActivity3.this, com.example.examenfinal.Secciones.Edicion.class);
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, urllg, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonlista= new JSONArray(response);
                        edicionList=Edicion.JsonObjectsBuild(jsonlista);
                        categoriaList = CategoriasCarreras.JsonObjectBuild(edicionList);
                        RecyclerViewAdaptadorCategoria recyclerViewAdaptadorCategoria = new RecyclerViewAdaptadorCategoria (categoriaList);
                        rvItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bundle=new Bundle();
                                bundle.putString("submissID",   edicionList.get(rvItem.getChildAdapterPosition(v)).getsubmiss());
                                bundle.putString("issueID",    id);
                                intent.putExtras(bundle);
                                Toast.makeText(getApplicationContext(),"Seleccione Edicion: " + edicionList.get(rvItem.getChildAdapterPosition(v)).gettittle(),Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }
                        });
                        rvItem.setAdapter(recyclerViewAdaptadorCategoria);

                    }catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG);
                }
            });
            queue.add(stringRequest);
        }
        catch (Exception EX){
            String s;
            s=EX.getMessage();
        }
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
 }