package com.example.examenfinal.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.examenfinal.WebServices.HttpRequest.HttpRequestException;
import org.json.JSONException;
import java.util.Map;
public class WebService extends AsyncTask<String, Long, String> {
    private Map<String, String> datos;
    private String url= "http://192.168.1.46/codigobarras/";
    private Context actividad;
    private String xml=null;
    private Asynchtask callback=null;
    public Asynchtask getCallback() {
        return callback;
    }
    public void setCallback(Asynchtask callback) {
        this.callback = callback;
    }
    ProgressDialog progDailog;
    public  WebService(String urlWebService,Map<String, String> data, Context activity, Asynchtask callback) {
        this.url=urlWebService;
        this.datos=data;
        this.actividad=activity;
        this.callback=callback;
    }
    public WebService() {
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progDailog = new ProgressDialog(actividad);
        progDailog.setMessage("Cargando Web Service...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            HttpRequest h=new HttpRequest(this.url,params[0]);
            for (int k=1;k< params.length;k+=2)
            {
                h.header(params[k],params[k+1]);
            }
            String r=  h.form(this.datos).body();
            return r;
        } catch (HttpRequestException exception) {
            Log.e("doInBackground", exception.getMessage());
            return "Error HttpRequestException";
        } catch (Exception e) {
            Log.e("doInBackground", e.getMessage());
            return "Error Exception " +  e.getMessage();
        }
    }
    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        this.xml=response;
        progDailog.dismiss();
        //Retorno los datos
        try {
            callback.processFinish(this.xml);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public Map<String, String> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, String> datos) {
        this.datos = datos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Context getActividad() {
        return actividad;
    }

    public void setActividad(Context actividad) {
        this.actividad = actividad;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public ProgressDialog getProgDailog() {
        return progDailog;
    }

    public void setProgDailog(ProgressDialog progDailog) {
        this.progDailog = progDailog;
    }
}